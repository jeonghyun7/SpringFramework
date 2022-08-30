package com.myboot02.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myboot02.member.dao.TestDAO;
import com.myboot02.member.vo.TestVO;

@Controller("testController")
public class TestControllerImpl implements TestController {
	private static final Logger logger =
								LoggerFactory.getLogger(TestControllerImpl.class);
	
	@Autowired
	private TestDAO testDAO;
	@Autowired
	private TestVO testVO;
	//pro30추가코드
	@RequestMapping(value = {"/","/proTest/testform.do"}, method = RequestMethod.GET)
	private ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String viewName = (String)request.getAttribute("viewName");
		String viewName = getViewName(request);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/proTest/test9.do" , method = RequestMethod.POST)
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = "listtest";
		System.out.println(viewName);
//		String viewName = (String)request.getAttribute("viewName");
		logger.info("viewName: " + viewName);
		logger.debug("debug레벨 : viewName = "+ viewName);
		List membersList = testDAO.listTests();
		ModelAndView mav = new ModelAndView("/proTest/listtest");
		mav.addObject("membersList", membersList);
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
