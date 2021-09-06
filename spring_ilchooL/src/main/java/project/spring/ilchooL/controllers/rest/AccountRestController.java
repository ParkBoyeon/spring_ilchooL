package project.spring.ilchooL.controllers.rest;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import project.spring.ilchooL.helper.RegexHelper;
import project.spring.ilchooL.helper.UploadItem;
import project.spring.ilchooL.helper.WebHelper;
import project.spring.ilchooL.model.Members;
import project.spring.ilchooL.service.MembersService;

@RestController
public class AccountRestController {

    /** WebHelper 주입 */
    @Autowired
    WebHelper webHelper;

    /** RegexHelper 주입 */
    @Autowired
    RegexHelper regexHelper;

    /** Service 패턴 구현체 주입 */
    @Autowired
    MembersService membersService;
    @Value("#{servletContext.contextPath}")
    String contextPath;

    /** 아이디 중복검사 */
    @RequestMapping(value = "/rest/account/id_unique_check", method = RequestMethod.POST)
    public Map<String, Object> idUniqueCheck(
            // 아이디
            @RequestParam(value = "user_id", required = false) String user_id) {

        if (!regexHelper.isValue(user_id)) {
            return webHelper.getJsonWarning("아이디를 입력하세요.");
        }

        Members input = new Members();
        input.setUser_id(user_id);

        try {
            membersService.idUniqueCheck(input);
        } catch (Exception e) {
            return webHelper.getJsonError(e.getLocalizedMessage());
        }

        return webHelper.getJsonData();
    }

    /** 아이디 중복검사 (jQuery Form Validate 플러그인용) */
    // controller에서 out 객체의 출력결과를 웹브라우저에게 전달할 수 있게 하는 옵션
    @ResponseBody
    @RequestMapping(value = "/rest/account/id_unique_check_jquery", method = RequestMethod.POST)
    public void idUniqueCheckjQuery(HttpServletResponse response,
            // 아이디
            @RequestParam(value = "user_id", required = false) String user_id) {

        Members input = new Members();
        input.setUser_id(user_id);
        String result = "true";

        try {
            membersService.idUniqueCheck(input);
        } catch (Exception e) {
            result = "false";
        }

        // out객체를 생성하여 문자열을 직접 출력함
        try {
            response.getWriter().print(result);
        } catch (IOException e) {}
    }
    
    /** 이메일 중복검사 */
    @RequestMapping(value = "/rest/account/email_unique_check", method = RequestMethod.POST)
    public Map<String, Object> emailUniqueCheck(
            // 이메일
            @RequestParam(value = "email", required = false) String email) {

        if (!regexHelper.isValue(email)) {
            return webHelper.getJsonWarning("이메일 주소를 입력하세요.");
        }
        
        if (!regexHelper.isEmail(email)) {
            return webHelper.getJsonWarning("이메일 주소가 잘못되었습니다.");
        }
        
        Members input = new Members();
        input.setEmail(email);
        
        try {
            membersService.emailUniqueCheck(input);
        } catch (Exception e) {
            return webHelper.getJsonError(e.getLocalizedMessage());
        }

        return webHelper.getJsonData();
    }
    
    /** 이메일 중복검사 (jQuery Form Validate 플러그인용) */
    // controller에서 out 객체의 출력결과를 웹브라우저에게 전달할 수 있게 하는 옵션
    @ResponseBody
    @RequestMapping(value = "/rest/account/email_unique_check_jquery", method = RequestMethod.POST)
    public void emailUniqueCheckjQuery(HttpServletResponse response,
            // 이메일
            @RequestParam(value = "email", required = false) String email) {
        
        Members input = new Members();
        input.setEmail(email);
        String result = "true";
        
        try {
            membersService.emailUniqueCheck(input);
        } catch (Exception e) {
            result = "false";
        }

        // out객체를 생성하여 문자열을 직접 출력함
        try {
            response.getWriter().print(result);
        } catch (IOException e) {}
    }

    /** 회원가입 */
    @RequestMapping(value = "/rest/account/join", method = RequestMethod.POST)
    public Map<String, Object> join(
            @RequestParam(value = "user_id",        required = false) String user_id,
            @RequestParam(value = "user_pw",        required = false) String user_pw,
            @RequestParam(value = "user_pw_confirm",required = false) String user_pw_confirm,
            @RequestParam(value = "user_name",      required = false) String user_name,
            @RequestParam(value = "email",          required = false) String email,
            @RequestParam(value = "phone",          required = false) String phone,
            @RequestParam(value = "birthday",       required = false) String birthday,
            @RequestParam(value = "gender",         defaultValue = "M") String gender,
            @RequestParam(value = "postcode",       required = false) String postcode,
            @RequestParam(value = "addr1",          required = false) String addr1,
            @RequestParam(value = "addr2",          required = false) String addr2,
            @RequestParam(required = false)         MultipartFile photo) {

        /** 1) 유효성 검증 */
        // POSTMAN 등의 클라이언트 프로그램으로 백엔드에 직접 접속하는 경우를 방지하기 위해
        // REST컨트롤러에서도 프론트의 유효성 검증과 별개로 자체 유효성 검증을 수행해야 한다.
        if (!regexHelper.isValue(user_id)) { return webHelper.getJsonWarning("아이디를 입력하세요."); }
        if (!regexHelper.isEngNum(user_id)) { return webHelper.getJsonWarning("아이디는 영어,숫자만 입력 가능합니다."); }
        if (user_id.length() < 4 || user_id.length() > 30) { return webHelper.getJsonWarning("아이디는 4~30글자로 입력 가능합니다."); }

        if (!regexHelper.isValue(user_pw)) { return webHelper.getJsonWarning("비밀번호를 입력하세요."); }
        if (user_pw.length() < 4 || user_pw.length() > 30) { return webHelper.getJsonWarning("비밀번호는 4~30글자로 입력 가능합니다."); }
        if (!user_pw.equals(user_pw_confirm)) { return webHelper.getJsonWarning("비밀번호는 확인이 잘못되었습니다."); }
        
        if (!regexHelper.isValue(user_name)) { return webHelper.getJsonWarning("이름을 입력하세요."); } 
        if (!regexHelper.isKor(user_name)) { return webHelper.getJsonWarning("이름은 한글로만 입력하세요."); } 
        if (user_name.length() > 30) { return webHelper.getJsonWarning("이름은 최대 30글자로 입력 가능합니다."); } 
        
        if (!regexHelper.isValue(email)) { return webHelper.getJsonWarning("이메일을 입력하세요."); } 
        if (!regexHelper.isEmail(email)) { return webHelper.getJsonWarning("이메일이 잘못되었습니다."); }
        
        if (!this.regexHelper.isValue(phone)) { return this.webHelper.getJsonWarning("연락처를 입력하세요."); } 
        if (!regexHelper.isCellPhone(phone) && !regexHelper.isTel(phone)) { return webHelper.getJsonWarning("연락처가 잘못되었습니다."); }
        if (!regexHelper.isValue(birthday)) { return webHelper.getJsonWarning("생년월일을 입력하세요."); }
        if (!regexHelper.isValue(postcode)) { return webHelper.getJsonWarning("우편번호를 입력하세요."); }
        if (!regexHelper.isValue(addr1)) { return webHelper.getJsonWarning("도로명 주소를 입력하세요."); }
        if (!regexHelper.isValue(addr2)) { return webHelper.getJsonWarning("나머지 주소를 입력하세요."); }

        /** 2) 업로드 처리 */
        UploadItem item = null;
        try {
            item = webHelper.saveMultipartFile(photo);
        } catch (NullPointerException e) {
            // 업로드 된 파일이 없는 경우
            e.printStackTrace();
            photo = null;
        } catch (Exception e) {
            e.printStackTrace();
            return webHelper.getJsonError("업로드에 실패했습니다.");
        }

        /** 3) 데이터 저장 */
        Members input = new Members();
        input.setUser_id(user_id);
        input.setUser_pw(user_pw);
        input.setUser_name(user_name);
        input.setEmail(email);
        input.setPhone(phone);
        input.setBirthday(birthday);
        input.setGender(gender);
        input.setPostcode(postcode);
        input.setAddr1(addr1);
        input.setAddr2(addr2);
        input.setPhoto(item);
       

        try {
            membersService.addMembers(input);
        } catch (Exception e) {
            return webHelper.getJsonError(e.getLocalizedMessage());
        }

        /** 4) 결과 표시 */
        return webHelper.getJsonData();
    }

   /** 회원 정보 수정에 대한 action 페이지 */
	@RequestMapping(value = "/rest/mypage/mypage", method = RequestMethod.POST)
	public Map<String, Object> editMembers(HttpServletRequest request,
			@RequestParam(value = "user_id",        required = false) String user_id,
            @RequestParam(value = "user_name",      required = false) String user_name,
            @RequestParam(value = "user_pw",		required = false) String user_pw,	
            @RequestParam(value = "email",          required = false) String email,
            @RequestParam(value = "phone",          required = false) String phone,
            @RequestParam(value = "birthday",       required = false) String birthday,
            @RequestParam(value = "postcode",       required = false) String postcode,
            @RequestParam(value = "addr1",          required = false) String addr1,
            @RequestParam(value = "addr2",          required = false) String addr2,
            @RequestParam(required = false)         MultipartFile photo) {
		
		
			//세션값 받아오기
			HttpSession session = request.getSession();		
			Members loginSession = (Members) session.getAttribute("member");

			/** 1) 사용자가 입력한 파라미터에 대한 유효성 검사 */
				
			// 핸드폰 번호 유효성 검사
			if(phone.equals("")) {return webHelper.getJsonWarning("핸드폰 번호를 입력하세요.");}
			if(!regexHelper.isCellPhone(phone)) {return webHelper.getJsonWarning("핸드폰 번호를 - 없이 올바른 양식으로 입력해 주세요.");}

			// 주소 유효성 검사
			if(postcode.equals("")) {return webHelper.getJsonWarning("우편번호를 입력하세요.");}
			if(addr1.equals("")) {return webHelper.getJsonWarning("주소를 입력하세요.");}
			if(addr2.equals("")) {return webHelper.getJsonWarning("상세주소를 입력하세요.");}

		/** 2) 데이터 저장하기 */
		// 저장할 값들을 Beans에 담는다.
		Members input = new Members();
		
		// 세션 데이터에서 받아올 값 (수정 불가 값)
		input.setId(loginSession.getId());
		input.setUser_id(loginSession.getUser_id());
		input.setUser_pw(loginSession.getUser_pw());
		input.setEmail(loginSession.getEmail());
		input.setUser_name(loginSession.getUser_name());
		input.setGender(loginSession.getGender());
		input.setBirthday(loginSession.getBirthday());
		input.setLogin_date(loginSession.getLogin_date());
		
		
		// 수정 페이지에서 수정하는 값
		
		input.setPhone(phone);
		input.setPostcode(postcode);
        input.setAddr1(addr1);
        input.setAddr2(addr2);
        
        Members output = null;
        
		try {
			// 데이터 수정
			membersService.editMembers(input);
			output = membersService.getMembersItem(input);
		} catch (Exception e) {
			return webHelper.getJsonError(e.getLocalizedMessage());
		}
//		Members output = null;		 // 사용자가 수정한 정보로 DB를 조회한 결과를 받을 객체 준비
//		try {
//		      output = membersService.login(input);
//		    } catch (Exception e) {
//		      return this.webHelper.getJsonError(e.getLocalizedMessage());
//		    } 
		    webHelper.setSession("member", output);
		  
			/** ) 결과 표시 */
		    return webHelper.getJsonData();
	}
	
	/** 회원 비밀번호 수정에 대한 action 페이지 */
	@RequestMapping(value = "/rest/mypage/pw", method = RequestMethod.POST)
	public Map<String, Object> editMembersPw(HttpServletRequest request,
			@RequestParam(value = "user_id",        required = false) String user_id,
            @RequestParam(value = "user_name",      required = false) String user_name,
            @RequestParam(value = "user_pw",		required = false) String user_pw,
            @RequestParam(value = "user_pw_re",     required = false) String user_pw_re,
            @RequestParam(value = "email",          required = false) String email,
            @RequestParam(value = "phone",          required = false) String phone,
            @RequestParam(value = "birthday",       required = false) String birthday,
            @RequestParam(value = "postcode",       required = false) String postcode,
            @RequestParam(value = "addr1",          required = false) String addr1,
            @RequestParam(value = "addr2",          required = false) String addr2,
            @RequestParam(required = false)         MultipartFile photo) {
		
		
			//세션값 받아오기
			HttpSession session = request.getSession();		
			Members loginSession = (Members) session.getAttribute("member");

			/** 1) 사용자가 입력한 파라미터에 대한 유효성 검사 */
				
			// 비밀번호+비밀번호 확인 유효성 검사
			if(user_pw.equals("")) {return webHelper.getJsonWarning("새로운 비밀번호를 입력하세요.");}
			if (user_pw.length() < 4 || user_pw.length() > 30) { return webHelper.getJsonWarning("비밀번호는 4~30글자로 입력 가능합니다."); }
			if (!user_pw.equals(user_pw_re)) { return webHelper.getJsonWarning("비밀번호는 확인이 잘못되었습니다."); }
			if (user_pw_re.length() < 4 || user_pw_re.length() > 30) { return webHelper.getJsonWarning("비밀번호는 4~30글자로 입력 가능합니다."); }

		/** 2) 데이터 저장하기 */
		// 저장할 값들을 Beans에 담는다.
		Members input = new Members();
		
		// 세션 데이터에서 받아올 값 (수정 불가 값)
		input.setId(loginSession.getId());
		input.setUser_id(loginSession.getUser_id());
		input.setEmail(loginSession.getEmail());
		input.setUser_name(loginSession.getUser_name());
		input.setGender(loginSession.getGender());
		input.setBirthday(loginSession.getBirthday());
		input.setPhone(loginSession.getPhone());
		input.setPostcode(loginSession.getPostcode());
        input.setAddr1(loginSession.getAddr1());
        input.setAddr2(loginSession.getAddr2());
		input.setLogin_date(loginSession.getLogin_date());
		
		
		// 수정 페이지에서 수정하는 값
		input.setUser_pw(user_pw);
		        
		try {
			// 데이터 수정
			membersService.editMembersPw(input);
		} catch (Exception e) {
			return webHelper.getJsonError(e.getLocalizedMessage());
		}
		Members output = null;		 // 사용자가 수정한 정보로 DB를 조회한 결과를 받을 객체 준비
		try {
		      output = membersService.login(input);
		    } catch (Exception e) {
		      return this.webHelper.getJsonError(e.getLocalizedMessage());
		    } 
		    webHelper.setSession("member", output);
		  
			/** ) 결과 표시 */
		    return webHelper.getJsonData();
	}
	
	/** 탈퇴하기 */
	@RequestMapping(value = "/rest/mypage/delete", method = RequestMethod.GET)
	public Map<String, Object> OutMembers() {
		// 세션값 받아오기
		Members input = (Members)webHelper.getSession("member");
		    try {
		      this.membersService.OutMembers(input);
		    } catch (Exception e) {
		      return this.webHelper.getJsonError(e.getLocalizedMessage());
		    } 
		    this.webHelper.removeAllSession();
		    return this.webHelper.getJsonData();
		  }
	}

    

