$(function() {

	function getContextPath() {
		var hostIndex = location.href.indexOf(location.host)
			+ location.host.length;
		var contextPath = location.href.substring(hostIndex, location.href
			.indexOf('/', hostIndex + 1));
		return contextPath;
	}
	
	$('#pw_btn').click(function() {
		swal({
			title: '비밀번호 변경',
			html:
				'<div class="mypage"><label for= "user_pw" > 새 비밀번호</label></div><span class="box new_pass"><input type="password" id="user_pw" class="new" name="user_pw" maxlength="20"><span id="alertTxt1">사용불가</span><img src="../assets/img/login_new_pass.png" id="new_pw_img1" class="new_pw_img"></span><span class="error_next_box"></span>' +
				'<div class="mypage"><label for="user_pw_re">비밀번호 재확인</label></div><span class="box new_pass_check"><input type="password" id="user_pw_re" name="user_pw_re" class="new" maxlength="20"><img src="../assets/img/login_new_check_disable.png" id="new_pw_re_img1" class="new_pw_img"></span> <span class="error_next_box"></span>',
			showCancelButton: true,
			cancelButtonText: '취소',
			confirmButtonText: '변경',
			showLoaderOnConfirm: true,
			focusConfirm: false,
			buttonsStyling: false,
			confirmButtonClass: 'btn btn-primary btn-lg',
			cancelButtonClass: 'btn btn-btn btn-danger btn-lg',
			allowOutsideClick: false,
			preConfirm: function() {
				return new Promise(function(result) {
					result([
						$('#user_pw').val(),
						$('#user_pw_re').val()
					])
				})
			}
		}).then(function(result) {
			$.ajax({
				type: 'POST',
				url: getContextPath() + '/rest/mypage/pw',
				data: {
					user_pw: document.getElementById('user_pw').value,
					user_pw_re: document.getElementById('user_pw_re').value
				},
				success: function(json) {
					if (result.value) { // 확인 버튼이 눌러진 경우
						swal('수정', '성공적으로 수정 되었습니다.', 'success');
						window.location.href = getContextPath();
					} else if (result.dismiss === 'cancel') { // 취소버튼이 눌러진 경우
						swal('수정', '수정이 취소되었습니다.', 'error');
					}
				}
			}); // end ajaxForm
		})
	});
});