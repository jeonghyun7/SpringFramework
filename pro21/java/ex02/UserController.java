package com.spring.ex02;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class UserController extends MultiActionController {

		public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			String userID = "";
			String passwd = "";
			ModelAndView mav = new ModelAndView();
			request.setCharacterEncoding("utf-8");
			userID = request.getParameter("userID");
			passwd = request.getParameter("passwd");
			String viewName=getViewName(request);
			
			mav.addObject("userID", userID);
			mav.addObject("passwd", passwd);
			//mav.setViewName("result");
			mav.setViewName(viewName);
			System.out.println("ViewName:" + viewName);
			return mav;
		}

		public ModelAndView memberInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			request.setCharacterEncoding("utf-8");
			ModelAndView mav = new ModelAndView();
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String viewName=getViewName(request);
			
			mav.addObject("id", id);	//회원가입창에서 전송된 회원정보를 addObject()메서드를 이용해 ModelAndView객체에 바인딩합니다.
			mav.addObject("pwd", pwd);
			mav.addObject("name", name);
			mav.addObject("email", email); //ModelAndView안의 메서드들을 활용
			//mav.setViewName("memberInfo");	//memberInfo.jsp로 포워딩합니다.
			mav.setViewName(viewName);
			System.out.println("ViewName:" + viewName);
			return mav;
		}
		/* 컨트롤러에서 ModelAndView 인자로 뷰이름이 반환되면 action-servlet의 
		  InternalResourceViewResolver의 프로퍼티 prefix 속성에서 지정된 
		  /test 폴더에서 modelAndView 인자로 넘어온 뷰이름에 해당되는 JSP를
		선택하여 DispatcherServlet으로 보냅니다.*/
		private String getViewName(HttpServletRequest request) throws Exception{
			
			String contextPath = request.getContextPath();
			String uri = (String)request.getAttribute("javax.servlet.include.request_uri");
			System.out.println("uri" + uri);
			if(uri == null || uri.trim().equals("")) {
				uri = request.getRequestURI();
				System.out.println("uri: " + uri);
			}
			
			//http://localhost:8080/member/listMember.do로 요청시
			int begin = 0; //
			if(!((contextPath == null) || ("".equals(contextPath)))) {
				begin = contextPath.length(); //전체 요청명의 길이를 구함
				System.out.println("begin: " + begin);
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
			String fileName=uri.substring(begin,end);
			if(fileName.indexOf(".")!=-1) {
				fileName=fileName.substring(0,fileName.lastIndexOf("."));
				//요청명에서 역순으로 최초 '.'의 위치를 구한후, '.do' 앞에까지의 문자열을 구함
			}
			if(fileName.lastIndexOf("/")!=-1) {
				fileName = fileName.substring(fileName.lastIndexOf("/"), fileName.length());
				//요청명에서 역순으로 최초 '/'의 위치를 구한후, '/' 다음부터의 문자열을 구함
			}
			return fileName;
		}
}
