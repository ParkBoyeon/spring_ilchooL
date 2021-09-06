$(function() {
	
	function getContextPath() {
      var hostIndex = location.href.indexOf(location.host)
            + location.host.length;
      var contextPath = location.href.substring(hostIndex, location.href
            .indexOf('/', hostIndex + 1));
      return contextPath;
   }

	/** 플러그인의 기본 설정 옵션 추가 */
	jQuery.validator.setDefaults({
		onkeyup: false, // 키보드입력시 검사 안함
		onclick: false, // <input>태그 클릭시 검사 안함
		onfocusout: false, // 포커스가 빠져나올 때 검사 안함
		showErrors: function(errorMap, errorList) { // 에러 발생시 호출되는 함수 재정의
			// 에러가 있을 때만..
			if (this.numberOfInvalids()) {
				// 0번째 에러 메시지에 대한 javascript 기본 alert 함수 사용
				//alert(errorList[0].message);
				// 0번째 에러 발생 항목에 포커스 지정
				//$(errorList[0].element).focus();
				swal({
					title: "에러",
					text: errorList[0].message,
					type: "error"
				}).then(function(result) {
					// 창이 닫히는 애니메이션의 시간이 있으므로,
					// 0.1초의 딜레이 적용 후 포커스 이동
					setTimeout(function() {
						$(errorList[0].element).val('');
						$(errorList[0].element).focus();
					}, 100);
				});
			}
		}
	});
	
	/** 유효성 검사 추가 함수 */
	$.validator.addMethod("kor", function(value, element) {
		return this.optional(element) || /^[ㄱ-ㅎ가-힣]*$/i.test(value);
	});
	$.validator.addMethod("phone", function(value, element) {
		return this.optional(element) ||
			/^01(?:0|1|[6-9])(?:\d{3}|\d{4})\d{4}$/i.test(value) ||
			/^\d{2,3}\d{3,4}\d{4}$/i.test(value);
	});
	/** form태그에 부여한 id속성에 대한 유효성 검사 함수 호출 */
	$("#mypage_form").validate({
		/** 입력검사 규칙 */
		rules: {
			// [연락처] 필수
			phone: { required: true, phone: true },
			
			// [우편번호] 필수 입력
			postcode: 'required',
			// [주소1] 우편번호가 입력된 경우만 필수
			addr1: 'required',
			// [주소2] 우편번호가 입력된 경우만 필수
			addr2: 'required',
			// 허용할 확장자 명시
			profile_img: { extension: "jpg|gif|png" }
		},
		/** 규칙이 맞지 않을 경우의 메시지 */
		messages: {
			phone: {
				required: "연락처를 입력하세요.",
				phone: "연락처 형식이 잘못되었습니다."
			},
			postcode: '우편번호를 입력해 주세요.',
			addr1: '기본주소를 입력해 주세요.',
			addr2: '상세주소를 입력해 주세요.',
			profile_img: {
				extension: "프로필 사진은 jpg,png,gif 형식만 가능합니다."
			}
		}
	}); // end validate()
	$('#mypage_form').ajaxForm({
		// submit 전에 호출된다.
		beforeSubmit: function(arr, form, options) {
			// validation 플러그인을 수동으로 호출하여 결과를 리턴한다.
			// 검사규칙에 위배되어 false가 리턴될 경우 submit을 중단한다.
			return $(form).valid();
		},
		success: function(json) {
			swal({
				title: '알림',
				text: '수정 사항을 저장 하시겠습니까?',
				type: 'question',
				confirmButtonText: 'Yes', // 확인버튼 표시 문구
				showCancelButton: true, // 취소버튼 표시 여부
				cancelButtonText: 'No', // 취소버튼 표시 문구
		}).then(function(result) { // 버튼이 눌러졌을 경우의 콜백 연결
			if (result.value) { // 확인 버튼이 눌러진 경우
				swal('수정', '성공적으로 수정 되었습니다.', 'success');
				window.location.href = getContextPath();
			} else if (result.dismiss === 'cancel') { // 취소버튼이 눌러진 경우
				swal('수정', '수정이 취소되었습니다.', 'error');
			} 
				});
			},
		}); // end ajaxForm
	});