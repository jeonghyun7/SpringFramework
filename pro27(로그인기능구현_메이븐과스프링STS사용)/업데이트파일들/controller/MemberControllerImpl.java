package com.spring.pro27.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.pro27.member.service.MemberService;
import com.spring.pro27.member.vo.MemberVO;

@Controller("memberController")
public class MemberControllerImpl implements MemberController {
	private static final Logger logger =
								LoggerFactory.getLogger(MemberControllerImpl.class);
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberVO memberVO;
	
	@Override
	@RequestMapping(value="/member/listMembers.do" , method = RequestMethod.GET)
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String viewName = getViewName(request);
		String viewName = (String)request.getAttribute("viewName");
		logger.info("viewName: " + viewName);
		logger.debug("debug레벨 : viewName = "+ viewName);
		List membersList = memberService.listMembers();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("membersList", membersList);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/member/addMember.do" , method = RequestMethod.POST)
	public ModelAndView addMember(@ModelAttribute("member") MemberVO member, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		result = memberService.addMember(member);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}
	
	
	@Override
	@RequestMapping(value="/member/removeMember.do" , method = RequestMethod.GET)
	public ModelAndView removeMember(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");
		memberService.removeMember(id);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}
	
	//추가구현코드
	@Override
	@RequestMapping(value="/member/modMember.do" , method = RequestMethod.GET)
	public ModelAndView modMember(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String viewName = getViewName(request);
		String viewName = (String)request.getAttribute("viewName");
      	System.out.println(viewName);
      	MemberVO memberVO = memberService.modMember(id);
      	request.setAttribute("member", memberVO);
      	ModelAndView mav = new ModelAndView();
      	mav.setViewName(viewName);
      	return mav;
	}
	
	//추가구현코드
	 			//, @RequestParam("id") String id
   @Override
   @RequestMapping(value =  "/member/updateMember.do", method = RequestMethod.POST)
   public ModelAndView updateMember(@ModelAttribute("member") MemberVO memberVO , HttpServletRequest request, HttpServletResponse response) throws Exception {
//      String viewName = getViewName(request);
      request.setCharacterEncoding("utf-8");
//      System.out.println(viewName);
      System.out.println();
      System.out.println(memberVO.getName());
      System.out.println(memberVO.getPwd());
      System.out.println(memberVO.getEmail());
      
      int result = 0;
      result = memberService.updateMember(memberVO);
      ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
      return mav;      
   }

	
	
	@RequestMapping(value = { "/member/loginForm.do" , "/member/memberForm.do" }, method = RequestMethod.GET)
	//@RequestMapping(value = "/member/*Form.do" , method = RequestMethod.GET)
	public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName");
//		String viewName = getViewName(request);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	

	@Override
	@RequestMapping(value = "/member/login.do", method=RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("member") MemberVO member, // 로그인창에서 전송된 ID와 비밀번호를 MemberVO객체인 member에 저장합니다.
							RedirectAttributes rAttr, //RedirectAttributes클래스를 이용하여 로그인 실패시 다시 로그인창으로 리다이렉트하여 실패메시지를 전달합니다.
							HttpServletRequest request, HttpServletResponse response) throws Exception {
	ModelAndView mav = new ModelAndView();
	memberVO = memberService.login(member); //login()메서드를 호출하여 로그인 정보를 전달합니다.
	if(memberVO != null) {
		HttpSession session = request.getSession();
		session.setAttribute("member", memberVO); //세션에 회원정보를 저장합니다.
		session.setAttribute("isLogOn", true);	// 세션에 로그인 상태를 true로 설정합니다.
		mav.setViewName("redirect:/member/listMembers.do");	//memberVO로 반환된 값이 있으면 세션을 이용해 로그인상태를 true로 합니다.
	}else {
		rAttr.addAttribute("result", "loginFailed"); // 로그인 실패시 실패메시지를 로그인창으로 전달합니다.
		mav.setViewName("redirect:/member/loginForm.do");	//로그인 실패 시 다시 로그인창으로 리다이렉트합니다.
	}
	return mav;
	}

	@Override
	@RequestMapping(value = "/member/logout.do", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("member");	//로그아웃 요청 시 세션에 저장된 회원정보와 
		session.removeAttribute("isLogOn");	// 로그인정보를 삭제합니다.
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/member/listMembers.do");
		return mav;
	}
	
	@RequestMapping(value = "/member/*Form.do" , method = RequestMethod.GET)
	public ModelAndView form(@RequestParam(value= "result", required=false) String result, HttpServletRequest request, HttpServletResponse response) throws Exception {
								//로그인창 요청시 매개변수 result가 전송되면 변수 result에 값을 저장합니다. 최초로 로그인창을 요청할 때는 매개변수 result가 전송되지 않으므로 무시합니다.
//			String viewName = getViewName(request);
			String viewName = (String)request.getAttribute("viewName");
			ModelAndView mav = new ModelAndView();
			mav.addObject("result", result);
			
			System.out.println(result);
			
			mav.setViewName(viewName);
			return mav;
		}
	
		/*
		 * private String getViewName(HttpServletRequest request) throws Exception{
		 * 
		 * String contextPath = request.getContextPath(); String uri =
		 * (String)request.getAttribute("javax.servlet.include.request_uri");
		 * 
		 * if(uri == null || uri.trim().equals("")) { uri = request.getRequestURI();
		 * 
		 * }
		 * 
		 * //http://localhost:8080/member/listMember.do로 요청시 int begin = 0; //
		 * if(!((contextPath == null) || ("".equals(contextPath)))) { begin =
		 * contextPath.length(); //전체 요청명의 길이를 구함
		 * 
		 * }
		 * 
		 * int end; if(uri.indexOf(";")!=-1) { end=uri.indexOf(";"); //요청 uri에 ';'가 있을
		 * 경우 ';' 문자 위치를 구함 } else if(uri.indexOf("?")!=-1) { end=uri.indexOf("?"); //요청
		 * uri에 '?'가 있을 경우 '?' 문자 위치를 구함 } else { end=uri.length(); }
		 * 
		 * //http://localhost:8080/member/listMember.do로 요청시 먼저 '.do'를 제거한
		 * //http://localhost:8080/member/listMember를 구한 후, //다시
		 * http://localhost:8080/member/listMember에서 역순으로 첫번째 // '/' 위치를 구한 후, 그 뒤의
		 * listMember를 구한다. String viewName=uri.substring(begin,end);
		 * if(viewName.indexOf(".")!=-1) {
		 * viewName=viewName.substring(0,viewName.lastIndexOf(".")); //요청명에서 역순으로 최초
		 * '.'의 위치를 구한후, '.do' 앞에까지의 문자열을 구함 } if(viewName.lastIndexOf("/")!=-1) {
		 * viewName = viewName.substring(viewName.lastIndexOf("/",1),
		 * viewName.length()); //member/listMembers.do로 요청할 경우 member/listMember를 파일
		 * 이름으로 가져옵니다. } System.out.println(viewName); return viewName; }
		 */
	
}