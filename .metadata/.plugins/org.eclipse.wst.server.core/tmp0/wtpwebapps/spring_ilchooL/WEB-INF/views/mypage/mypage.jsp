<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!doctype html>
<html>

<head>
<c:import url="../assets/head.jsp" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/mypage.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/flexboxgrid.min.css" />
	
</head>

<body>
	<div class="wrapper">
		<c:import url="../assets/header.jsp" />
		<div class="mypage_logo">
			<h1>
				<img src="../assets/img/mypage_logo.png">내 정보 수정
			</h1>
		</div>
		<div class="row">
			<!-- wrapper -->
			<div class="container">
				<!-- content-->
				<!-- content-->
				<div id="inner_join">
					<div id="new_box">
						<h2>비밀번호 변경</h2>
					</div>
					<div id="form_password" class="hidden"></div>
					<form class="form-horizontal" name="password_form" id="password_form" method="post" action="${pageContext.request.contextPath}/rest/mypage/pw">
						<div class="row center-sm">
							<div class="col-sm-12">
								<p>
									<button type="button" id='pw_btn' class='btn btn-primary outline'>비밀번호 변경</button>
								</p>
							</div>
						</div>
					</form>
					<form class="form-horizontal" name="mypage_form" id="mypage_form"
						method="post"
						action="${pageContext.request.contextPath}/rest/mypage/mypage">
						<!-- MOBILE -->
						<div id="new_box">
							<h2>연락처 변경</h2>
						</div>
						<div>
							<div class="mypage">
								<label for="phoneNo">연락처</label>
							</div>
							<span class="box int_mobile"> <input type="tel" id="phone"
								class="new" name="phone" maxlength="16"
								value="${member.getPhone()}" placeholder="전화번호 입력">
							</span> <span class="error_next_box"></span>
						</div>

						<!-- ADDRESS -->
						<div id="new_box">
							<h2>거주지역 변경</h2>
						</div>
						<div>
							<div class="mypage">
								<label for="address">거주 지역</label>
							</div>

							<div class="address1">
								<span class="box1 new_post "> <input type="text"
									id="postcode" name="postcode" class="new_1" placeholder="우편번호"
									value="${member.getPostcode()}" readonly>
								</span> <span class="click"> <input type="button"
									id="address_btn" class="btn btn-primary outline"
									onclick="new_execDaumPostcode()" value="우편번호 찾기">
								</span>
							</div>

							<span class="box2 new_address "> <input type="text"
								id="addr1" class="new" name="addr1" placeholder="주소"
								value="${member.getAddr1()}" readonly>
							</span> <span class="box3 new_detailAddress "> <input type="text"
								id="addr2" class="new" name="addr2" placeholder="상세주소"
								value="${member.getAddr2()}">
							</span>


							<!-- iOS에서는 position:fixed 버그가 있음, 적용하는 사이트에 맞게 position:absolute 등을 이용하여 top,left값 조정 필요 -->
							<div id="layer"
								style="display: none; position: absolute; overflow: hidden; z-index: 1; -webkit-overflow-scrolling: touch;">
								<img src="//t1.daumcdn.net/postcode/resource/images/close.png"
									id="btnCloseLayer"
									style="cursor: pointer; position: absolute; right: -3px; top: -3px; z-index: 1"
									onclick="closeDaumPostcode()" alt="닫기 버튼">
							</div>
						</div>

						<!-- 프로필 사진 -->
						<div class="join">
							<label for='profile_img' class="">프로필사진</label>
							<div class="">
								<input type="file" name="profile_img" id="profile_img" />
							</div>
						</div>

						<!-- Mypage BTN-->
						<div class="btn_area">
							<button type="submit" id="btnJoin" class="btn btn-primary outline">변경된 정보 저장</button>
						</div>
					</form>
				</div>
				<!-- content-->
				<form class="form-horizontal" name="delete_form" id="delete_form"
					method="GET"
					action="${pageContext.request.contextPath}/rest/mypage/delete">
					<div class="mypage_delete">
						<hr />
						<div class="user_delete">
							<h2>회원 탈퇴</h2>
							회원 탈퇴시
							<h5>${user_name}</h5>
							님의 데이터 사용 및 복구가 불가능하오니 신중하게 <br> 선택하시기 바랍니다. 그래도 탈퇴 하시겠습니까?
							<div class="check_delete">
								<input type="checkbox" name="delete" id="delete" value="delete" />
								<label for="delete" class="required">위 내용을 확인 하였으며, 이에
									동의 합니다.</label>
							</div>
							<div class="input_check">
								<button type="submit" id="next-button">회원탈퇴하기</button>
							</div>
						</div>
					</div>
				</form>
				<!-- content-->
			</div>
		</div>
		<!-- wrapper -->
	</div>
	<div id="footer">
		<c:import url="../assets/footer.jsp" />
	</div>

	<script src="../assets/js/mypage_pw.js"></script>
	<script src="../assets/js/mypage.js"></script>
	<script src="../assets/js/user_delete.js"></script>
	<script src="../assets/js/address_1.js"></script>
	<script src="../assets/js/address3.js"></script>

	<!-- <script src="../assets/plugins/sweetalert/sweetalert.min.js"></script> -->
	<!-- validate 플러그인 참조 -->
	<script src="../assets/plugins/validate/jquery.validate.min.js"></script>
	<script src="../assets/plugins/validate/additional-methods.min.js"></script>
</body>

</html>