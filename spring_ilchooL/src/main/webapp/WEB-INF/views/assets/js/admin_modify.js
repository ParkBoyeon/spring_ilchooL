/**
 * 
 */

var userName = document.querySelector('#name');
var id = document.querySelector('#id');
var nickName = document.querySelector('#nickname');
var error = document.querySelectorAll('.error_next_box');

userName.addEventListener("focusout", checkName);
id.addEventListener("focusout", checkId);
nickName.addEventListener("focusout", checkNick);



function checkName() {
	var namePattern = /^[ㄱ-ㅎ가-힣]*$/;
	if (userName.value === "") {
		error[0].innerHTML = "필수 정보입니다.";
		error[0].style.display = "block";
	} else if (!namePattern.test(userName.value) || userName.value.indexOf(" ") > -1) {
		error[0].innerHTML = "한글만 사용하세요. (특수기호, 공백 사용 불가)";
		error[0].style.display = "block";
	} else {
		error[0].style.display = "none";
	}
}

function checkId() {
	var idPattern = /[a-zA-Z0-9_-]{5,20}/;
	if (id.value == "") {
		error[1].innerHTML = "필수 정보입니다.";
		error[1].style.display = "block";
	} else if (!idPattern.test(id.value)) {
		error[1].innerHTML = "5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.";
		error[1].style.display = "block";
	} else {
		error[1].innerHTML = "사용 가능 한 아이디 입니다.";
		error[1].style.color = "#08A600";
		error[1].style.display = "block";
	}
}


function checkNick() {
	var nickPattern = /"^[가-힣ㄱ-ㅎa-zA-Z0-9._ -]{2,}\$"/;
	if (nickName.value === "") {
		error[2].innerHTML = "필수 정보입니다.";
		error[2].style.display = "block";
	} else if (!nickPattern.test(nickName.value) || nickName.value.indexOf(" ") > -1) {
		error[2].innerHTML = "숫자, 영어, 한국어와 언더스코어, 공백을 허용하며 최소 2자 이상의 닉네임";
		error[2].style.display = "block";
	} else {
		error[2].style.display = "none";
	}
}



