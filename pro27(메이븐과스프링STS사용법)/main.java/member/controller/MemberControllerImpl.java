package com.spring.pro27.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.pro27.member.service.MemberService;
import com.spring.pro27.member.vo.MemberVO;

@Controller("memberController")
public class MemberControllerImpl implements MemberController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberVO memberVO;
	
	@Override
	@RequestMapping(value="/member/listMembers.do" , method = RequestMethod.GET)
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
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
	

	@RequestMapping(value = "/member/*Form.do" , method = RequestMethod.GET)
	public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String viewName = getViewName(request);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	private String getViewName(HttpServletRequest request) throws Exception{
		
		String contextPath = request.getContextPath();
		String uri = (String)request.getAttribute("javax.servlet.include.request_uri");
		
		if(uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
			
		}
		
		//http://localhost:8080/member/listMember.do로 요청시
		int begin = 0; //
		if(!((contextPath == null) || ("".equals(contextPath)))) {
			begin = contextPath.length(); //전체 요청명의 길이를 구함
			
		}
		
		int end;
		if(uri.indexOf(";")!=-1) {
			end=uri.indexOf(";");	//요청 uri에 ';'가 있을 경우 ';' 문자 위치를 구함
		} else if(uri.indexOf("?")!=-1) {
			end=uri.indexOf("?"); //요청 uri에 '?'가 있을 경우 '?' 문자 위치를 구함
		} else {
			end=uri.length();
		}
		
		//http://localhost:8080/member/listMember.do로 요청시 먼저 '.do'를 제거한
		//http://localhost:8080/member/listMember를 구한 후,
		//다시 http://localhost:8080/member/listMember에서 역순으로 첫번째
		// '/' 위치를 구한 후, 그 뒤의 listMember를 구한다.
		String viewName=uri.substring(begin,end);
		if(viewName.indexOf(".")!=-1) {
			viewName=viewName.substring(0,viewName.lastIndexOf("."));
			//요청명에서 역순으로 최초 '.'의 위치를 구한후, '.do' 앞에까지의 문자열을 구함
		}
		if(viewName.lastIndexOf("/")!=-1) {
			viewName = viewName.substring(viewName.lastIndexOf("/",1), viewName.length());
			//member/listMembers.do로 요청할 경우 member/listMember를 파일 이름으로 가져옵니다.
		}
		System.out.println(viewName);
		return viewName;
	}
	
}