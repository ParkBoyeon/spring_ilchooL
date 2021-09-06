$(function(){
    	  function getContextPath() {
    	      var hostIndex = location.href.indexOf(location.host)
    	            + location.host.length;
    	      var contextPath = location.href.substring(hostIndex, location.href
    	            .indexOf('/', hostIndex + 1));
    	      return contextPath;
    	  	}
    	      /** 아이디 찾기 */
    	      $('#id_search_form').ajaxForm({
	 			// submit 전에 호출된다.
       			beforeSubmit: function(arr, form, options) {
            		// validation 플러그인을 수동으로 호출하여 결과를 리턴한다.
           			// 검사규칙에 위배되어 false가 리턴될 경우 submit을 중단한다.
            		return $(form).valid(); },
    	           error: function(error){
    	               var error_msg ='';
    	               var code = parseInt(error.status / 100);
    	               if (code == 5)  {
    	                   error_msg = "이름, 생년월일, 이메일을 확인하세요.\n";
    	               }
    	               else if(code == 4){
    	                   error_msg = "탈퇴된 회원입니다.\n";
    	               } 
    	               swal({
    	                   title : "에러",
    	                   text :error_msg,
    	                   type : "error"
    	               }).then(function(result) {
    	                   // 창이 닫히는 애니메이션의 시간이 있으므로,
    	                   // 0.1초의 딜레이 적용 후 포커스 이동
    	                   setTimeout(function() {

    	                   }, 100);
    	               }); 
    	               return false; 
    	           },
    	           success: function(json) {
    	               swal({
    	                   title : "성공",
    	                   text : json.output.user_name+" 님의 아이디는 "+ json.output.user_id+" 입니다.",
    	                   type : "success",
    	               }).then(function(result) {
    	                   // 창이 닫히는 애니메이션의 시간이 있으므로,
    	                   // 0.1초의 딜레이 적용 후 포커스 이동
    	                   setTimeout(function() {

    	                   }, 100);
    	                })
    	           }
    	       }); // end ajaxForm
    	       
    	      /** 비밀번호 찾기 */
				 $('#btnJoin').click(function(e) {
    	                var id_search_val = $("#user_id").val();

    	                if (!id_search_val) { // 입력되지 않았다면?
    	                    swal({
    	                        title : "에러",
    	                        text : "아이디를 입력해주세요.",
    	                        type : "error"
    	                    }).then(function(result) {
    	                        // 창이 닫히는 애니메이션의 시간이 있으므로,
    	                        // 0.1초의 딜레이 적용 후 포커스 이동
    	                        setTimeout(function() {

    	                        }, 100);
    	                    }); // 
    	                    $("#user_id").focus(); 
    	                    return false; 
    	                }
    	                var email_search_val = $("#search_email").val();

    	                if (!email_search_val) { 
    	                    swal({
    	                        title : "에러",
    	                        text : "이메일 주소를 입력해주세요.",
    	                        type : "error"
    	                    }).then(function(result) {
    	                        // 창이 닫히는 애니메이션의 시간이 있으므로,
    	                        // 0.1초의 딜레이 적용 후 포커스 이동
    	                        setTimeout(function() {

    	                        }, 100);
    	                    }); 
    	                    $("#search_email").focus(); 
    	                    return false; 
    	                }

    	            });//입력여부 검사 end
    	            $('#pw_search_form').ajaxForm({
    	                 
    	                error: function(error){
    	                    var error_msg ='';
    	                    var code = parseInt(error.status / 100);
    	                    if (code == 5)  {
    	                        error_msg = "아이디, 이메일을 확인하세요.\n";
    	                    }
    	                    else if(code == 4){
    	                        error_msg = "탈퇴된 회원입니다.\n";
    	                    } 
    	                    swal({
    	                        title : "에러",
    	                        text :error_msg,
    	                        type : "error"
    	                    }).then(function(result) {
    	                        // 창이 닫히는 애니메이션의 시간이 있으므로,
    	                        // 0.1초의 딜레이 적용 후 포커스 이동
    	                        setTimeout(function() {

    	                        }, 100);
    	                    }); 
    	                    return false; 
    	                },
    	                success: function(json) {
    	                    swal({
    	                        title : "성공",
    	                        text : "입력하신 주소로 임시 비밀번호가 발송되었습니다.",
    	                        type : "success"
    	                    }).then(function(result) {
    	                        // 창이 닫히는 애니메이션의 시간이 있으므로,
    	                        // 0.1초의 딜레이 적용 후 포커스 이동
    	                        setTimeout(function() {

    	                        }, 100);
    	                     })
    	                }
    	            }); // end ajaxForm
    	           
    	   });