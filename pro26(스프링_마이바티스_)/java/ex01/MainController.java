package com.spring.ex01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//@Controller애너테이션을 이용해 MainController클래스를 빈으로 자동변환합니다.빈id는 mainController입니다.
@Controller("mainController")
//@RequestMapping을 이용해 첫 번째 단계의 URL요청이 /test이면 mainController빈을 요청합니다.
@RequestMapping("/test")
public class MainController {
	
	/*@RequestMapping을 이용해 두번째단계의 URL요청이 /main1.do이면 mainController빈의
	  main1()매서드에게 요청합니다. method=RequestMethod.GET으로 지정하면 GET방식으로 요청시
	  해당메서드가 호출됩니다.*/
	@RequestMapping(value="/main1.do",method=RequestMethod.GET)
	public ModelAndView main1(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("msg", "main1"); //addObject("변수명","값")
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping(value="/main2.do",method=RequestMethod.GET)
	public ModelAndView main2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("msg", "main2"); //addObject("변수명","값")
		mav.setViewName("main");
		return mav;
	}

}
