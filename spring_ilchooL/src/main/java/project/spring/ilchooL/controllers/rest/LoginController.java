package project.spring.ilchooL.controllers.rest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import project.spring.ilchooL.helper.MailHelper;
import project.spring.ilchooL.helper.RegexHelper;
import project.spring.ilchooL.helper.UploadItem;
import project.spring.ilchooL.helper.WebHelper;
import project.spring.ilchooL.model.Members;
import project.spring.ilchooL.service.MembersService;


@RestController
public class LoginController {
  @Autowired
  WebHelper webHelper;
  
  @Autowired
  MembersService membersService;
  
  @Autowired
  RegexHelper regexHelper;
  
  @Autowired
  MailHelper mailHelper;
  
  
  @Value("#{servletContext.contextPath}")
  String contextPath;
  
  /** 로그인 */
  @RequestMapping(value = "/rest/account/login", method = RequestMethod.POST)
  public Map<String, Object> login(
		  @RequestParam(value = "user_id", required = false) String user_id, 
		  @RequestParam(value = "user_pw", required = false) String user_pw) {
	  /** 1) 유효성 검증 */
      // POSTMAN 등의 클라이언트 프로그램으로 백엔드에 직접 접속하는 경우를 방지하기 위해
      // REST컨트롤러에서도 프론트의 유효성 검증과 별개로 자체 유효성 검증을 수행해야 한다.
      if (!regexHelper.isValue(user_id)) { return webHelper.getJsonWarning("아이디를 입력하세요."); }
      if (!regexHelper.isValue(user_pw)) { return webHelper.getJsonWarning("비밀번호를 입력하세요."); }

      /** 2) 데이터 조회 */
      Members input = new Members();
      input.setUser_id(user_id);
      input.setUser_pw(user_pw);
      
      /** 3) 로그인 */
      Members output = null;
      
      try {
          output = this.membersService.login(input);
          if (output.isIs_out())
            return webHelper.getJsonWarning("탈퇴한 회원 입니다."); 
    } catch (Exception e) {
      return webHelper.getJsonError(e.getLocalizedMessage());
    } 
      boolean y_admin = output.isIs_admin();
      
      Map<String, Object> map = new HashMap<>();
      
      map.put("y_admin", y_admin);
      
      if(y_admin) {
         return webHelper.getJsonData(map);
      }
      
      /** 4) 프로필 사진이 존재하는 경우 썸네일 이미지 생성 */
      UploadItem photo = output.getPhoto();

      if (photo != null) {
          try {
              String thumbPath = webHelper.createThumbnail(photo.getFilePath(), 150, 150, true);

              // 웹 상에서 접근할 수 있는 URL정보 등록
              photo.setFileUrl(webHelper.getUploadUrl(photo.getFilePath()));
              photo.setThumbnailUrl(webHelper.getUploadUrl(thumbPath));
          } catch (Exception e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
      }

      /** 5) 세션 생성 및 결과 표시 */
      webHelper.setSession("member", output);
      return webHelper.getJsonData();
  }
  
  /** 로그아웃 */
  @RequestMapping(value = "/rest/account/logout", method = RequestMethod.GET)
  public Map<String, Object> logout() {
      webHelper.removeAllSession();
      return webHelper.getJsonData();
  }
  
  
  /** 아이디 찾기 */
  @RequestMapping(value = {"/rest/account/id_search"}, method = RequestMethod.POST)
  public Map<String, Object> findId(
		  @RequestParam(value="user_name", required = false) String user_name,
		  @RequestParam(value="birthday",  required = false) String birthday,
		  @RequestParam(value="email", required = false) String email) {
    
	  if (!regexHelper.isValue(birthday))
		  return webHelper.getJsonWarning("이름을 입력하세요.");
	  if (!regexHelper.isValue(user_name))
		     return webHelper.getJsonWarning("생일을 입력하세요.");
	  if (!regexHelper.isValue(email))
	      return webHelper.getJsonWarning("이메일을 입력하세요."); 
	  
	  Members input = new Members();
	  
	  input.setUser_name(user_name);
	  input.setBirthday(birthday);
	  input.setEmail(email);
	  
	  Members output = null;
	  
	  try {
	      output = membersService.findId(input);
	      if (output.isIs_out())
	        return webHelper.getJsonWarning("탈퇴한 회원입니다."); 
	    } catch (Exception e) {
	      return webHelper.getJsonError(e.getLocalizedMessage());
	    } 
	    Map<String, Object> map = new HashMap<>();
	    map.put("output", output);
	    return webHelper.getJsonData(map);
	  }
  
  /** 비밀번호 찾기 */
  @RequestMapping(value = {"/rest/account/pw_search"}, method = RequestMethod.POST)
  public Map<String, Object> findPwPOST(
		  @RequestParam(value = "search_email", required = false) String search_email, 
		  @RequestParam(value = "user_id", required = false) String user_id) {
	  
    if (!regexHelper.isValue(search_email))
      return webHelper.getJsonWarning("이메일을 입력하세요."); 
    if (!regexHelper.isValue(user_id))
      return webHelper.getJsonWarning("아이디를 입력하세요."); 
    
    Members input = new Members();
    input.setEmail(search_email);
	input.setUser_id(user_id);
	
    Members output = null;
    
    try {
      output = membersService.findPw(input);
      if (output.isIs_out())
        return webHelper.getJsonWarning("탈퇴한 회원 입니다."); 
    } catch (Exception e) {
      return webHelper.getJsonError(e.getLocalizedMessage());
    } 
    
    String pw = "";
    for (int i = 0; i < 10; i++)
      pw = String.valueOf(pw) + (char)(int)(Math.random() * 26.0D + 97.0D); 
    output.setUser_pw(pw);
    
    try {
      membersService.editMembersPw(output);
    } catch (Exception e) {
      return webHelper.getJsonError(e.getLocalizedMessage());
    } 
    
    try {
      String mailSubjcet = "[ilchooL] 임시비밀번호 입니다.";
      String mailContent = String.valueOf(output.getUser_name()) + " 회원님의 임시 비밀번호는 " + pw + " 입니다."
    		  + "로그인 후 비밀번호를 변경하여 사용해 주시길 바랍니다.";
      mailHelper.sendMail(output.getEmail(), mailSubjcet, mailContent);
    } catch (Exception e) {
      e.printStackTrace();
      return webHelper.getJsonError(e.getLocalizedMessage());
    } 
    return webHelper.getJsonData();
  }
}

////세션값 받아오기
//HttpSession session = request.getSession();		
//Members loginSession = (Members) session.getAttribute("loginInfo");			
//								
////로그인 세션이 없을 경우 = 로그인되어있지 않을 경우 alert 발생
//if(loginSession !=null) { 
//	webHelper.redirect(null,"로그아웃 후 이용해주세요.");
//}		
