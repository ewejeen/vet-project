package egovframework.example.sample.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * 		AdminController.java
 *		관리자 페이지에 관련된 것들을 처리해 주는 컨트롤러
 *		(웹)
 *	
 */

@RequestMapping("/notrespassing")
@Controller
public class AdminController {
	
	/**
	 * 
	 *	관리자 로그인 페이지를 조회한다.
	 *
	 */
	@RequestMapping("/signinview.do")
	public String adminLoginView(){
		return "vetproject/adminLogin";
	}
	
	/**
	 * 
	 *	관리자 로그인을 한다.
	 *  
	 *  오류 없이 AJAX 로그인 로직 처리하기
	 *  1. jackson-databind dependency를 추가
	 *  2. controller에서 result를 담은 map을 return하는 방식 -> result를 담은 map을 modelandview에 add해서 modelandview를 return하는 방식
	 *  3. jquery ajax에서 받을 때 dataType : 'json' 추가
	 *
	 */
	@RequestMapping(value="/signin.do", method=RequestMethod.POST)
	public ModelAndView adminLogin(ModelAndView modelAndView, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		HttpSession session = request.getSession();
		String adminId = request.getParameter("adminId");
		String adminPw = request.getParameter("adminPw");
		
		int result = 0;
		// 로그인 정보가 맞으면 result값으로 1 반환 + 세션 아이디(30분) 설정
		if(adminId.equals("administrator") && adminPw.equals("drowssapnimda79#$")){
			result = 1;
			session.setAttribute("sessionId", adminId);
			session.setMaxInactiveInterval(60*30);
		}
		map.put("result", result);
		
		// modelandview에 map 추가
		modelAndView.addAllObjects(map);
		
		/* 
		 * setViewName에 들어갈 String 파라미터는 JsonView bean에 설정해줬던 id와 같아야 한다.
		 * (dispatcher-servlet.xml -> MappingJackson2JsonView -> jsonView)
		 * 
		 */
		modelAndView.setViewName("jsonView");
		
		return modelAndView;
	}
	

	/**
	 * 
	 *	관리자 콘솔 페이지를 조회한다.
	 *
	 */
	@RequestMapping("/adminconsole.do")
	public String adminConsole(){
		return "vetproject/adminConsole";
	}

	/**
	 * 
	 * 관리자 로그아웃을 한다.
	 * : 로그인과 같은 방식으로 modelAndView를 이용한다.
	 *
	 */
	@RequestMapping(value="/signout.do", method=RequestMethod.POST)
	public ModelAndView adminLogout(ModelAndView modelAndView, HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		HttpSession session = request.getSession();
		int result = 0;
		
		// 세션이 존재하면 invalidate 시키고 result로 1을 반환한다.
		if(session!=null){
			session.invalidate();
			result = 1;
			System.out.println("로그아웃 성공"); 
		}
		map.put("result", result);
		System.out.println(map.get("result"));
		modelAndView.addAllObjects(map);
		modelAndView.setViewName("jsonView");
		return modelAndView;
	}
}