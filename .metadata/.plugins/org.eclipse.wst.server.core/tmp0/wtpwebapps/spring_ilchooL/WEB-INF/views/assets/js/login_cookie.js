$(document).ready(function()
    {
        var userId = getCookie("cookieUserId"); 
        $("input[name='user_id']").val(userId); 
         
        if($("input[name='user_id']").val() != ""){ // Cookie에 만료되지 않은 아이디가 있어 입력됬으면 체크박스가 체크되도록 표시
            $("input[name='remember']").attr("checked", true);
        }
         
        $("button[type='submit']", $('#login_form')).click(function(){ // Login Form을 Submit할 경우,
            if($("input[name='remember']").is(":checked")){ // ID 기억하기 체크시 쿠키에 저장
                var userId = $("input[name='user_id']").val();
                setCookie("cookieUserId", userId, 7); // 7일동안 쿠키 보관
            } else {
                deleteCookie("cookieUserId");
            }
        });             
    })
 
    function setCookie(cookieName, value, exdays){
        var exdate = new Date();
        exdate.setDate(exdate.getDate()+exdays);
        var cookieValue = escape(value)+((exdays==null)? "": "; expires="+exdate.toGMTString());
        document.cookie = cookieName+"="+cookieValue;
    }
    function deleteCookie(cookieName){
        var expireDate = new Date();
        expireDate.setDate(expireDate.getDate()-1);
        document.cookie = cookieName+"= "+"; expires="+expireDate.toGMTString();
    }
    function getCookie(cookieName){
        cookieName = cookieName + '=';
        var cookieData = document.cookie;
        var start = cookieData.indexOf(cookieName);
        var cookieValue = '';
        if(start != -1){
            start += cookieName.length;
            var end = cookieData.indexOf(';', start);
            if(end == -1) end = cookieData.length;
            cookieValue = cookieData.substring(start, end);
        }
        return unescape(cookieValue);
         
    }