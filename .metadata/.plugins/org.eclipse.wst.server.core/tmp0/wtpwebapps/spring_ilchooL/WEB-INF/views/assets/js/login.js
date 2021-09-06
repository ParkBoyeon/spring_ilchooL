$(function() {
    /** 유효성 검사 플러그인이 ajaxForm보다 먼저 명시되어야 한다. */
    $('#login_form').validate({
        rules: {
            user_id: 'required',
            user_pw: 'required'
        },
        messages: {
            user_id: '아이디를 입력하세요.',
            user_pw: '비밀번호를 입력하세요.'
        },
    });

    $('#login_form').ajaxForm({
        // submit 전에 호출된다.
        beforeSubmit: function(arr, form, options) {
            // validation 플러그인을 수동으로 호출하여 결과를 리턴한다.
            // 검사규칙에 위배되어 false가 리턴될 경우 submit을 중단한다.
            return $(form).valid(); },
		 error: function(error) {
                var error_msg = '';
                var code = parseInt(error.status / 100);
                if (code == 5) {
                    error_msg = "아이디나 비밀번호를 확인하세요.\n";
                } else if (code == 4) {
                    error_msg = "탈퇴된 회원입니다.\n"
                }
                swal({
                    title: "에러",
                    text: error_msg,
                    type: "error"
                }).then(function(result) {
                    // 창이 닫히는 애니메이션의 시간이 있으므로,
                    // 0.1초의 딜레이 적용 후 포커스 이동
                    setTimeout(function() {

                    }, 100);
                }); // <-- 메시지 표시
                return false; // <-- 실행 중단
        },
        success: function(json) {
            swal({
                title: '알림',
                text: '로그인 하시겠습니까?',
                type: 'question',
                confirmButtonText: 'Yes', // 확인버튼 표시 문구
                showCancelButton: true, // 취소버튼 표시 여부
                cancelButtonText: 'No', // 취소버튼 표시 문구
            }).then(function(result) { // 버튼이 눌러졌을 경우의 콜백 연결
                if (result.value) { // 확인 버튼이 눌러진 경우
                    swal('확인', '성공적으로 로그인 되었습니다.', 'success');
                    if (!json.y_admin) {
                        window.location.href = getContextPath();
                    } else {
                        window.location.href = getContextPath() + "/admin/admin_dashboard.do";
                    }
                } else if (result.dismiss === 'cancel') { // 취소버튼이 눌러진 경우
                    swal('확인', '로그인이 취소되었습니다.', 'error');
                }
            });
        },
    }); // end ajaxForm

    function getContextPath() {
        var hostIndex = location.href.indexOf(location.host) +
            location.host.length;
        var contextPath = location.href.substring(hostIndex, location.href
            .indexOf('/', hostIndex + 1));
        return contextPath;
    }
});
 