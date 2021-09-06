package project.spring.ilchooL.model;

import com.google.gson.Gson;

import lombok.Data;
import project.spring.ilchooL.helper.UploadItem;

@Data
/** `회원` 테이블의 POJO 클래스 */
public class Members {
    /** 일련번호, IS NOT NULL, PRI */
    private int id;

    /** 아이디, IS NOT NULL */
    private String user_id;

    /** 비밀번호(암호화저장), IS NOT NULL */
    private String user_pw;

    /** 회원이름, IS NOT NULL */
    private String user_name;

    /** 이메일, IS NOT NULL */
    private String email;

    /** 연락처, IS NOT NULL */
    private String phone;

    /** 생년월일, IS NOT NULL */
    private String birthday;

    /** 성별(M=남자,F=여자), IS NOT NULL */
    private String gender;

    /** 우편번호, IS NOT NULL */
    private String postcode;

    /** 검색된 주소, IS NOT NULL */
    private String addr1;

    /** 나머지 주소, IS NOT NULL */
    private String addr2;

    /** 프로필사진 정보{json=UploadItem}, IS NULL */
    private UploadItem photo;

    /** 탈퇴여부(Y/N), IS NOT NULL */
    private boolean is_out;

    /** 관리자 여부(Y/N), IS NOT NULL */
    private boolean is_admin;

    /** 마지막 로그인 일시, IS NULL */
    private String login_date;

    /** 등록일시, IS NOT NULL */
    private String reg_date;

    /** 변경일시, IS NOT NULL */
    private String edit_date;
    
    /** LIMIT 절에서 사용할 조회 시작 위치 */
    private static int offset;
    
    /** LIMIT 절에서 사용할 조회할 데이터 수 */
    private static int listCount;

    
    /** 일련번호, IS NOT NULL, PRI */
	public int getId() {
		return id;
	}
	
	/** 일련번호, IS NOT NULL, PRI */
	public void setId(int id) {
		this.id = id;
	}
	
	/** 아이디, IS NOT NULL */
	public String getUser_id() {
		return user_id;
	}
	
	/** 아이디, IS NOT NULL */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	/** 비밀번호(암호화저장), IS NOT NULL */
	public String getUser_pw() {
		return user_pw;
	}
	
	/** 비밀번호(암호화저장), IS NOT NULL */
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	
	/** 회원이름, IS NOT NULL */
	public String getUser_name() {
		return user_name;
	}
	
	/** 회원이름, IS NOT NULL */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	/** 이메일, IS NOT NULL */
	public String getEmail() {
		return email;
	}
	
	/** 이메일, IS NOT NULL */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/** 연락처, IS NOT NULL */
	public String getPhone() {
		return phone;
	}
	
	/** 연락처, IS NOT NULL */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/** 생년월일, IS NOT NULL */
	public String getBirthday() {
		return birthday;
	}
	
	/** 생년월일, IS NOT NULL */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	/** 성별(M=남자,F=여자), IS NOT NULL */
	public String getGender() {
		return gender;
	}

	/** 성별(M=남자,F=여자), IS NOT NULL */
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	/** 우편번호, IS NOT NULL */
	public String getPostcode() {
		return postcode;
	}

	/** 우편번호, IS NOT NULL */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	/** 검색된 주소, IS NOT NULL */
	public String getAddr1() {
		return addr1;
	}

	/** 검색된 주소, IS NOT NULL */
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	/** 나머지 주소, IS NOT NULL */
	public String getAddr2() {
		return addr2;
	}

	/** 나머지 주소, IS NOT NULL */
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	
	/** 프로필사진 정보{json=UploadItem}, IS NULL */
	public String getPhotoJson() {
		return new Gson().toJson(this.photo);
	}
	
	/** 프로필사진 정보{json=UploadItem}, IS NULL */
	public UploadItem getPhoto() {
		return photo;
	}
	
	/** 프로필사진 정보{json=UploadItem}, IS NULL */
	public void setPhotoJson(String photo) {
		this.photo = new Gson().fromJson(photo, UploadItem.class);
	}
	
	/** 프로필사진 정보{json=UploadItem}, IS NULL */
	public void setPhoto(UploadItem photo) {
		this.photo = photo;
	}
	
	/** 탈퇴여부(Y/N), IS NOT NULL */
	public boolean isIs_out() {
		return is_out;
	}
	
	/** 탈퇴여부(Y/N), IS NOT NULL */
	public void setIs_out(boolean is_out) {
		this.is_out = is_out;
	}
	
	/** 관리자 여부(Y/N), IS NOT NULL */
	public boolean isIs_admin() {
		return is_admin;
	}
	
	/** 관리자 여부(Y/N), IS NOT NULL */
	public void setIs_admin(boolean is_admin) {
		this.is_admin = is_admin;
	}
	
	
	/** 마지막 로그인 일시, IS NULL */
	public String getLogin_date() {
		return login_date;
	}
	
	/** 마지막 로그인 일시, IS NULL */
	public void setLogin_date(String login_date) {
		this.login_date = login_date;
	}
	
	/** 등록일시, IS NOT NULL */
	public String getReg_date() {
		return reg_date;
	}
	
	/** 등록일시, IS NOT NULL */
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	
	/** 변경일시, IS NOT NULL */
	public String getEdit_date() {
		return edit_date;
	}
	
	/** 변경일시, IS NOT NULL */
	public void setEdit_date(String edit_date) {
		this.edit_date = edit_date;
	}
	
	public static int getOffset() {
		return offset;
	}
	
	public static void setOffset(int offset) {
		Members.offset = offset;
	}

	public static int getListCount() {
		return listCount;
	}
	
	public static void setListCount(int listCount) {
		Members.listCount = listCount;
	}
	
    @Override
    public String toString() {
        String str = "\n[Members]\n";
        str += "id: " + this.id + " (일련번호, IS NOT NULL, PRI)\n";
        str += "user_id: " + this.user_id + " (아이디, IS NOT NULL)\n";
        str += "user_pw: " + this.user_pw + " (비밀번호(암호화저장), IS NOT NULL)\n";
        str += "user_name: " + this.user_name + " (회원이름, IS NOT NULL)\n";
        str += "email: " + this.email + " (이메일, IS NOT NULL)\n";
        str += "phone: " + this.phone + " (연락처, IS NOT NULL)\n";
        str += "birthday: " + this.birthday + " (생년월일, IS NOT NULL)\n";
        str += "gender: " + this.gender + " (성별(M=남자,F=여자), IS NOT NULL)\n";
        str += "postcode: " + this.postcode + " (우편번호, IS NOT NULL)\n";
        str += "addr1: " + this.addr1 + " (검색된 주소, IS NOT NULL)\n";
        str += "addr2: " + this.addr2 + " (나머지 주소, IS NOT NULL)\n";
        str += "photo: " + this.photo + " (프로필사진 정보{json=UploadItem}, IS NULL)\n";
        str += "is_out: " + this.is_out + " (탈퇴여부(Y/N), IS NOT NULL)\n";
        str += "is_admin: " + this.is_admin + " (관리자 여부(Y/N), IS NOT NULL)\n";
        str += "login_date: " + this.login_date + " (마지막 로그인 일시, IS NULL)\n";
        str += "reg_date: " + this.reg_date + " (등록일시, IS NOT NULL)\n";
        str += "edit_date: " + this.edit_date + " (변경일시, IS NOT NULL)\n";
        return str;
    }

	
}
