package project.spring.ilchooL.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import project.spring.ilchooL.helper.PageData;
import project.spring.ilchooL.helper.WebHelper;
import project.spring.ilchooL.model.Members;
import project.spring.ilchooL.model.MembersLog;
import project.spring.ilchooL.service.AdminMembersService;
import project.spring.ilchooL.service.MembersLogService;
import project.spring.ilchooL.service.MembersService;

@Controller
public class AdminController {
	
    /** Service 패턴 구현체 주입 */
    @Autowired
    AdminMembersService adminMembersService;
    
    @Autowired
    MembersLogService memberLogService;
    
    @Autowired
	WebHelper webHelper;
	@Autowired
    MembersService membersService;
    
    /** "/프로젝트이름" 에 해당하는 ContextPath 변수 주입 */
    // --> import org.springframework.beans.factory.annotation.Value;
    @Value("#{servletContext.contextPath}")
    String contextPath;
    
    /** 목록 페이지 */
    @RequestMapping(value = "/admin/admin_list.do", method = RequestMethod.GET)
    public String list(Model model, HttpServletResponse response,
            // 검색어
            @RequestParam(value="keyword", required=false) String keyword,
            // 페이지 구현에서 사용할 현재 페이지 번호
            @RequestParam(value="page", defaultValue="1") int nowPage) {
        
        int totalCount = 0;         // 전체 게시글 수
        int listCount  = 10;        // 한 페이지당 표시할 목록 수
        int pageCount  = 5;         // 한 그룹당 표시할 페이지 번호 수
        
        // 조회에 필요한 조건값(검색어)를 Beans에 담는다.
        Members input = new Members();
        input.setUser_id(keyword);
        input.setUser_name(keyword);
        input.setEmail(keyword);
        input.setPhone(keyword);
        
        List<Members> output = null; // 조회결과가 저장될 객체
        PageData pageData = null;       // 페이지 번호를 계산한 결과가 저장될 객체

        try {
            // 전체 게시글 수 조회
            totalCount = adminMembersService.geMembersCount(input);
            // 페이지 번호 계산 --> 계산결과를 로그로 출력될 것이다.
            pageData = new PageData(nowPage, totalCount, listCount, pageCount);

            // SQL의 LIMIT절에서 사용될 값을 Beans의 static 변수에 저장
            Members.setOffset(pageData.getOffset());
            Members.setListCount(pageData.getListCount());
            
            // 데이터 조회하기
            output = adminMembersService.getMembersList(input);
        } catch (Exception e) { e.printStackTrace(); }

        // View 처리
        model.addAttribute("output", output);
        model.addAttribute("keyword", keyword);
        model.addAttribute("pageData", pageData);
        return "admin/admin_list";
    }

    
    /** 삭제 처리 구현 */
    @RequestMapping(value = "/admin/delete_ok.do", method = RequestMethod.GET)
    public void deleteOk(Model model, HttpServletResponse response,
            @RequestParam(value="id") int id) {
        // 데이터 삭제에 필요한 조건값을 Beans에 저장하기
        Members input = new Members();
        input.setId(id);

        try {
            // 데이터 삭제
        	adminMembersService.deleteMembers(input);
        } catch (Exception e) { e.printStackTrace(); }

        // 확인할 대상이 삭제된 상태이므로 목록 페이지로 이동
        try {
            response.sendRedirect(contextPath + "/admin/admin_list.do");
        } catch (IOException e) { e.printStackTrace(); }
    }
    
    /** 수정 폼 페이지 */
    @RequestMapping(value = "/admin/admin_modify.do", method = RequestMethod.GET)
    public String edit(Model model, HttpServletResponse response,
            @RequestParam(value="id") int id) {

        // 데이터 조회에 필요한 조건값을 Beans에 저장하기
    	Members input = new Members();
        input.setId(id);
        
        // 수정할 데이터의 원본 조회하기
        Members output = null;
        
        try {
            output = adminMembersService.getMembersItem(input);
        } catch (Exception e) { e.printStackTrace(); }
        
        // View 처리
        model.addAttribute("output", output);
        return "admin/admin_modify";
    }

    /** 수정 폼에 대한 action 페이지 */
    @RequestMapping(value = "/admin/edit_ok.do", method = RequestMethod.POST)
    public void editOk(Model model, HttpServletResponse response,
            @RequestParam(value="id", defaultValue="") int id,
            @RequestParam(value="user_id", defaultValue="") String user_id,
            @RequestParam(value="user_name", defaultValue="") String user_name,
            @RequestParam(value="email") String email,
            @RequestParam(value="phone") String phone,
            @RequestParam(value="gender") String gender,
            @RequestParam(value="birthday") String birthday) {
        
        // 수정할 값들을 Beans에 담는다.
        Members input = new Members();
        input.setId(id);
        input.setUser_id(user_id);
        input.setUser_name(user_name);
        input.setEmail(email);
        input.setPhone(phone);
        input.setGender(gender);
        input.setBirthday(birthday);

        try {
            // 데이터 수정
           adminMembersService.editMembers(input);
        } catch (Exception e) { e.printStackTrace(); }

        // 수정한 대상을 상세페이지에 알려주기 위해서 PK값을 전달해야 한다. 
        String redirectUrl = contextPath + "/admin/admin_list.do";
        try {
            response.sendRedirect(redirectUrl);
        } catch (IOException e) { e.printStackTrace(); }
    }


    
    /** 관리자 페이지 대쉬보드 컨트롤러 */
	@RequestMapping(value = "/admin/admin_dashboard.do", method = RequestMethod.GET)
	public String dashboard(Model model)
	{
		Members input = new Members();
        
	       input.setGender("F");
	       int result = 0;
	       
	       try {
	          result = adminMembersService.adminGenderCount(input);
	      } catch (Exception e) {
	    	  e.printStackTrace();
	      } 
	    model.addAttribute("genderF", result);
	    
	    input.setGender("M");
	       
	       try {
	          result = adminMembersService.adminGenderCount(input);
	      } catch (Exception e) {
	    	  e.printStackTrace();
	      } 
	    model.addAttribute("genderM", result);
	    
	    	input.setAddr1("서울");
	    
	       try {
		          result = adminMembersService.adminaddr(input);
		      } catch (Exception e) {
		    	  e.printStackTrace();
		      } 
	       
		    model.addAttribute("addr1", result);
		    
		    input.setAddr1("경기");
		       
		       try {
		          result = adminMembersService.adminaddr(input);
		      } catch (Exception e) {
		    	  e.printStackTrace();
		      } 
		    
		    model.addAttribute("addr2", result);
		    
		   
		    
		       try {
		    	   result = adminMembersService.adminOld20();
		      } catch (Exception e) {
		         e.printStackTrace();
		      }
		      
		      model.addAttribute("Old0", result);
		      
		      try {
		    	   result = adminMembersService.adminOld25();
		      } catch (Exception e) {
		         e.printStackTrace();
		      }
		      
		      model.addAttribute("Old1", result);
		      
		      try {
		    	   result = adminMembersService.adminOld30();
		      } catch (Exception e) {
		         e.printStackTrace();
		      }
		      
		      model.addAttribute("Old2", result);
		
		return "admin/admin_dashboard";
	}
	
    /** 로그페이지 */
    @RequestMapping(value = { "/admin/admin_log.do" }, method = { RequestMethod.GET })
    public String log(Model model, HttpServletResponse response,
            @RequestParam(value="id") int id) {

        // 데이터 조회에 필요한 조건값을 Beans에 저장하기
    	Members input = new Members();
    	MembersLog loginput = new MembersLog();
        input.setId(id);
        loginput.setId(id);
        
        // 수정할 데이터의 원본 조회하기
        Members output = null;
        List<MembersLog> logoutput = null; // 조회결과가 저장될 객체
        
        try {
            output = adminMembersService.getMembersItem(input);
            logoutput = memberLogService.getMembersLogList(loginput);
        } catch (Exception e) { e.printStackTrace(); }
        
        // View 처리
        model.addAttribute("output", output);
        model.addAttribute("logoutput", logoutput);
        return "admin/admin_log";
    }

}
