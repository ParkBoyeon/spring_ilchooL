/*변수 선언*/


/** var id = document.querySelector('#user_id'); */

var pw1 = document.querySelector('#user_pw');
var pwMsg = document.querySelector('#alertTxt');
var pwImg1 = document.querySelector('#user_pw_img1');

var pw2 = document.querySelector('#user_pw_confirm');
var pwImg2 = document.querySelector('#user_pw_re_img1');
var pwMsgArea = document.querySelector('.new_pass');

var userName = document.querySelector('#user_name');

var email = document.querySelector('#email');

var mobile = document.querySelector('#phone');

var error = document.querySelectorAll('.error_next_box');



/*이벤트 핸들러 연결*/



pw1.addEventListener("focusout", checkPw);
pw2.addEventListener("focusout", comparePw);
userName.addEventListener("focusout", checkName);
email.addEventListener("focusout", isEmailCorrect);
mobile.addEventListener("focusout", checkPhoneNum);





/*콜백 함수*/

function checkPw() {
	var pwPattern = /[a-zA-Z0-9~!@#$%^&*()_+|<>?:{}]{8,16}/;
	if (pw1.value == "") {
		error[0].innerHTML = "필수 정보입니다.";
		error[0].style.display = "block";
	} else if (!pwPattern.test(pw1.value)) {
		error[0].innerHTML = "8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.";
		pwMsg.innerHTML = "사용불가";
		pwMsgArea.style.paddingRight = "93px";
		error[0].style.display = "block";

		pwMsg.style.display = "block";
		pwImg1.src = "../assets/img/login_new_not_use.png";
	} else {
		error[0].style.display = "none";
		pwMsg.innerHTML = "안전";
		pwMsg.style.display = "block";
		pwMsg.style.color = "#03c75a";
		pwImg1.src = "../assets/img/login_new_safe.png";
	}
}

function comparePw() {
	if (pw2.value == pw1.value && pw2.value != "") {
		pwImg2.src = "../assets/img/login_new_check_enable.png";
		error[1].style.display = "none";
	} else if (pw2.value !== pw1.value) {
		pwImg2.src = "../assets/img/login_new_check_not.png";
		error[1].innerHTML = "비밀번호가 일치하지 않습니다.";
		error[1].style.display = "block";
	}

	if (pw2.value == "") {
		error[1].innerHTML = "필수 정보입니다.";
		error[1].style.display = "block";
	}
}

function checkName() {
	var namePattern = /^[ㄱ-ㅎ가-힣]*$/;
	if (userName.value == "") {
		error[2].innerHTML = "필수 정보입니다.";
		error[2].style.display = "block";
	} else if (!namePattern.test(userName.value) || userName.value.indexOf(" ") > -1) {
		error[2].innerHTML = "한글만 사용하세요. (특수기호, 공백 사용 불가)";
		error[2].style.display = "block";
	} else {
		error[2].style.display = "none";
	}
}

function isEmailCorrect() {
	var emailPattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

	if (email.value == "") {
		error[4].innerHTML = "필수 정보입니다.";
		error[4].style.display = "block";
	} else if (!emailPattern.test(email.value)) {
		error[4].innerHTML = "이메일 주소를 다시 확인해주세요.";
		error[4].style.display = "block";
	} else {
		error[4].style.display = "none";
	}

}

function checkPhoneNum() {
	var isPhoneNum = /([01]{2})([01679]{1})([0-9]{4})([0-9]{4})/;

	if (mobile.value == "") {
		error[5].innerHTML = "필수 정보입니다.";
		error[5].style.display = "block";
	} else if (!isPhoneNum.test(mobile.value)) {
		error[5].innerHTML = "형식에 맞지 않는 번호입니다.";
		error[5].style.display = "block";
	} else {
		error[5].style.display = "none";
	}


}