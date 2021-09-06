$(function() {
	agree_CheckedChanged();
});

// 체크박스 체크 선택 또는 해제할 때 상태 업데이트
$("#delete").change(function() {
	agree_CheckedChanged();
});


function agree_CheckedChanged() {
	if ($("#delete").prop("checked")) {
		console.log("check");
		$("#next-button").prop("disabled", false);
	} else {
		console.log("uncheck");
		$("#next-button").prop("disabled", true);
	}


};

$(function() {
	
	function getContextPath() {
      var hostIndex = location.href.indexOf(location.host)
            + location.host.length;
      var contextPath = location.href.substring(hostIndex, location.href
            .indexOf('/', hostIndex + 1));
      return contextPath;
   }

	$("#delete_form").ajaxForm({
		// submit 전에 호출된다.
		beforeSubmit: function(arr, form, options) {
			// validation 플러그인을 수동으로 호출하여 결과를 리턴한다.
			// 검사규칙에 위배되어 false가 리턴될 경우 submit을 중단한다.
			return $(form).valid();
		},
			success: function(json) {
            	swal({
	                title: '경고', // 제목
	                text: "정말 탈퇴 하시겠습니까?", // 내용
	                type: 'warning', // 종류
	                confirmButtonText: 'Yes', // 확인버튼 표시 문구
	                showCancelButton: true, // 취소버튼 표시 여부
	                cancelButtonText: 'No', // 취소버튼 표시 문구
            }).then(function(result) { // 버튼이 눌러졌을 경우의 콜백 연결
                if (result.value) { // 확인 버튼이 눌러진 경우
                    swal('탈퇴', '성공적으로 탈퇴 되었습니다.', 'success');
					window.location.href = getContextPath();
                } else if (result.dismiss === 'cancel') { // 취소버튼이 눌러진 경우
                    swal('탈퇴', '탈퇴가 취소되었습니다.', 'error');
                }
           });
		},
	}); // end ajaxForm
});