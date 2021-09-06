<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html>

<head>
<c:import url="../assets/head.jsp" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/join.css">
</head>

<body>
	<div class="wrapper">
		<c:import url="../assets/header.jsp" />
	<div class="row">
	<!-- wrapper -->
	<div class="container">
		<!-- content-->
		<div id="inner_join">
			<div id="new_box">회원가입</div>
			<form id="join_form" class="form-horizontal" name="join_form" method="post" action="${pageContext.request.contextPath}/rest/account/join">
			<!-- ID -->
				<div>
					<div class="join">
						<label for="id">아이디</label>
					</div>
					<div class = "id">
						<span class="box4 new_id"> 
							<input type="text" id="user_id" class="new" name="user_id" maxlength="20">
						</span> 
						<span class="click">
							<button class="btn btn-primary outline" type="button" id="id_unique_check">중복검사</button>
						</span>
					</div>
				</div>
				
				<!-- PW1 -->
				<div>
					<div class="join">
						<label for="pswd">비밀번호</label>
					</div>
					<span class="box new_pass"> 
						<input type="password"id="user_pw" class="new" maxlength="20" name="user_pw"> 
							<span id="alertTxt">사용불가</span> 
								<img src="../assets/img/login_new_pass.png" id="user_pw_img1" class="user_pw_img">
					</span>
					<span class="error_next_box"></span>
				</div>

				<!-- PW2 -->
				<div>
					<div class="join">
						<label for="pswd_re">비밀번호 재확인</label>
					</div>
					<span class="box new_pass_check">
						<input type="password" id="user_pw_confirm" class="new" maxlength="20" name="user_pw_confirm">
							<img src="../assets/img/login_new_check_disable.png" id="user_pw_re_img1" class="user_pw_img">
					</span> 
					</div>
					<span class="error_next_box"></span>
				

				<!-- NAME -->
				<div>
					<div class="join">
						<label for="user_name">이름</label>
					</div>
					<span class="box int_name"> 
						<input type="text" id="user_name" class="new" maxlength="20" name="user_name">
					</span> 
					<span class="error_next_box"></span>
				</div>

				<!-- BIRTH -->
				<div>
					<div class="join">
						<label for="birthday">생년월일</label>
					</div>
					<span class="box birthday"> 
                        <input type="date" name="birthday" class="new" id="birthday" />
                    </span>
                </div>

				<!-- GENDER -->
				<div>
					<div class="join">
						<label for="gender">성별</label>
					</div>
					<span class="box gender_code"> 
						<select id="gender" name="gender" class="sel">
							<option>성별</option>
							<option value="M">남자</option>
							<option value="F">여자</option>
						</select>
					</span> 
					<span class="error_next_box">필수 정보입니다.</span>
				</div>

				<!-- EMAIL -->
				<div>
					<div class="join">
						<label for="email">이메일 주소</label>
					</div>
					<span class="box4 int_email"> 
					<input type="email" id="email" class="new" maxlength="100" name="email">
					</span> 
					<span class="click">
							<button class="btn btn-primary outline" type="button" id="email_unique_check">중복검사</button>
					</span>
				</div>

				<!-- MOBILE -->
				<div>
					<div class="join">
						<label for="phoneNo">연락처</label>
					</div>
					<span class="box int_mobile"> 
						<input type="tel" id="phone" class="new" maxlength="16" placeholder="전화번호 입력" name="phone">
					</span> 
					<span class="error_next_box"></span>
				</div>

				<!-- ADDRESS -->
				<div>
					<div class="join">
						<label for="address">거주 지역</label>
					</div>

					<div class="address1">
						<span class="box1 new_post "> 
							<input type="text" id="postcode" name="postcode" class="new_1" placeholder="우편번호" readonly>
						</span> 
						<span class="click"> 
							<input type="button" id="address_btn" class="btn btn-primary outline" onclick="new_execDaumPostcode()" value="우편번호 찾기">
						</span>
					</div>

					<span class="box2 new_address "> 
						<input type="text" id="addr1" class="new" name="addr1" placeholder="주소" readonly>
					</span> 
					<span class="box3 new_detailAddress "> 
						<input type="text" id="addr2" class="new" name="addr2" placeholder="상세주소">
					</span> 

					<!-- iOS에서는 position:fixed 버그가 있음, 적용하는 사이트에 맞게 position:absolute 등을 이용하여 top,left값 조정 필요 -->
					<div id="layer" style="display: none; position: absolute; overflow: hidden; z-index: 1; -webkit-overflow-scrolling: touch;">
						<img src="../assets/img/address_close.png" id="btnCloseLayer" style="cursor: pointer; position: absolute; right: -3px; top: -3px; z-index: 1" onclick="closeDaumPostcode()" alt="닫기 버튼">
					</div>
				</div>

				<!-- 프로필 사진 -->
				<div class="join">
				 <label for='profile_img' class="">프로필사진</label>
                    <div class="profile_img">
                        <div class="image-upload">
                            <input id="photo" type="file" name="photo" accept="image/jpg, image/gif, image/png" />
                        </div>
                    </div>
				</div>

				<!-- JOIN BTN-->
				<div class="btn_area">
					<button type="submit" id="btnJoin">
						가입하기
					</button>
				</div>
			</form>
		</div>
		<!-- content-->
		</div>
	</div>
	<!-- wrapper -->
	</div>
	<div id="footer">
		<c:import url="../assets/footer.jsp" />
	</div>

	<script src="${pageContext.request.contextPath}/assets/js/join.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/join2.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/address_1.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/address.js"></script>
	<!-- validate 플러그인 참조 -->
	<script src="${pageContext.request.contextPath}/assets/plugins/validate/jquery.validate.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/plugins/validate/additional-methods.min.js"></script>
</body>

</html>