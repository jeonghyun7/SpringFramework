package com.spring.ex01;

//simpleUrlController.java의 역할은 매핑된 요청에 대해 컨트롤러의 기능을 수행합니다.

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class SimpleUrlController implements Controller {
	
	@Override
	public ModelAndView handleRequest (HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("index.jsp");
	}
}