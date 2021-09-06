package project.spring.ilchooL.controllers;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import project.spring.ilchooL.helper.RegexHelper;
import project.spring.ilchooL.helper.WebHelper;
import project.spring.ilchooL.model.Members;
import project.spring.ilchooL.service.MembersService;

@Controller
public class AccountController {
	/** WebHelper 주입 */
	@Autowired
	WebHelper webHelper;

	/** RegexHelper 주입 */
	@Autowired
	RegexHelper regexHelper;

	/** Service 패턴 구현체 주입 */
	@Autowired
	MembersService memberService;
	
	/** 회원가입 페이지로 이동 */
	@RequestMapping(value = "/account/join.do", method = RequestMethod.GET)
	public ModelAndView join(Model model,
			HttpServletRequest request) {
				//세션값 받아오기
				HttpSession session = request.getSession();		
				Members loginSession = (Members) session.getAttribute("loginInfo");
						
				//로그인 세션이 있을 경우 = 로그인 되어 있을 경우 alert 발생
				if(loginSession!=null) { 
					return webHelper.redirect(null,"로그아웃 후 이용해주세요.");
				}
		return new ModelAndView("account/join");
	}
	
	/** 로그인 페이지로 이동 */
    @RequestMapping(value = "/account/login.do", method = RequestMethod.GET)
	public ModelAndView login(Model model,
			HttpServletRequest request) {
		//세션값 받아오기
		HttpSession session = request.getSession();		
		Members loginSession = (Members) session.getAttribute("member");
				
						
		//로그인 세션이 있을 경우 = 로그인 되어 있을 경우 alert 발생
		if(loginSession!=null) { 
			return webHelper.redirect(null,"로그아웃 후 이용해주세요.");
		}
				
		return new ModelAndView("account/login");
	}
    
    
	/** 이용약관 페이지 이동 컨트롤러 */
	@RequestMapping(value = "/account/join_terms", method = RequestMethod.GET)
	public ModelAndView join_terms(Model model,
			HttpServletRequest request) {
		//세션값 받아오기
		HttpSession session = request.getSession();		
		Members loginSession = (Members) session.getAttribute("loginInfo");
				
						
		//로그인 세션이 있을 경우 = 로그인 되어 있을 경우 alert 발생
		if(loginSession!=null) { 
			return webHelper.redirect(null,"로그아웃 후 이용해주세요.");
		}
				
		return new ModelAndView("account/join_terms");
	}
	
	
	/** 마이페이지 이동 컨트롤러 */
	@RequestMapping(value = "/mypage/mypage.do", method = RequestMethod.GET)
	public ModelAndView update_info(Model model,
			HttpServletRequest request) {
		
		//세션값 받아오기
		HttpSession session = request.getSession();		
		Members loginSession = (Members) session.getAttribute("member");
				
						
		//로그인 세션이 없을 경우 = 로그인되어있지 않을 경우 alert 발생
		if(loginSession==null) { 
			return webHelper.redirect("../account/login.do","로그인 후 이용해주세요.");
		} 
		model.addAttribute("user_name", loginSession.getUser_name());
		return new ModelAndView("mypage/mypage");
	}
	
	

	@RequestMapping(value = {"/account/id_pw_search.do"}, method = {RequestMethod.GET})
	  public ModelAndView id_pw_search(Model model, HttpServletRequest request) {
		
		//세션값 받아오기
		HttpSession session = request.getSession();		
		Members loginSession = (Members) session.getAttribute("member");
										
		//로그인 세션이 없을 경우 = 로그인되어있지 않을 경우 alert 발생
		if(loginSession!=null) { 
		return webHelper.redirect("../","로그아웃 후 이용해주세요.");
		} 
		
	    return new ModelAndView("account/id_pw_search");
	  }
	// 아이디 찾기
	@RequestMapping(value = "/account/id_search.do", method = RequestMethod.GET)
	public void findGET() throws Exception {
		
	}

	// 비밀번호 찾기
	@RequestMapping(value = "/account/pw_search.do", method = RequestMethod.GET)
	public void findPwGET() throws Exception {
		
	}
		
}
