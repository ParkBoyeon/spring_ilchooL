$(document).on('click', '.postcode-finder', function(e) {
	const current = $(e.currentTarget);
	const postcode = $(current.data('postcode'));
	const addr1 = $(current.data('addr1'));
	const addr2 = $(current.data('addr2'));
	const frame = $(current.data('frame'));

	// 현재 scroll 위치를 저장해놓는다.
	var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);

	new daum.Postcode({
		oncomplete: function(data) {
			var addr = ''; // 주소 변수
			var extraAddr = ''; // 참고항목 변수

			if (data.userSelectedType === 'R') {
				addr = data.roadAddress;
			} else {
				addr = data.jibunAddress;
			}

			// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
			if (data.userSelectedType === 'R') {
				// 법정동명이 있을 경우 추가한다. (법정리는 제외)
				// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
				if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
					extraAddr += data.bname;
				}
				// 건물명이 있고, 공동주택일 경우 추가한다.
				if (data.buildingName !== '' && data.apartment === 'Y') {
					extraAddr += extraAddr !== '' ? ', ' + data.buildingName : data.buildingName;
				}
				// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
				if (extraAddr !== '') {
					extraAddr = ' (' + extraAddr + ')';
				}

				addr = addr + ' ' + extraAddr;
			}

			postcode.val(data.zonecode);
			addr1.val(addr);
			addr2.focus();
			frame.hide();
			document.body.scrollTop = currentScroll;
		},
		onresize: function(size) {
			frame.css('height', size.height + 'px');
		},
		width: '100%',
		height: '100%',
	}).embed(frame[0]);

	frame.show();
});