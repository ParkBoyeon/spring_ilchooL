$(function() {
	function getContextPath() {
      var hostIndex = location.href.indexOf(location.host)
            + location.host.length;
      var contextPath = location.href.substring(hostIndex, location.href
            .indexOf('/', hostIndex + 1));
      return contextPath;
   }

    /** 로그아웃 */
    $(".logout").click(function(e) {
        e.preventDefault();

        swal({
            title: '확인',
            text: '정말 로그아웃 하시겠습니까?',
            type: "question",
            showCancelButton: true
        }).then(function(result) {
            if (result.value) {
                $.get(getContextPath() + "/rest/account/logout", function() {
                    window.location = getContextPath();
                });
            }
        });
    });
}),

$(document).ready(function() {
			$(".hamburger").click(function() {
				$(this).toggleClass("is-active");
				$("#menuNav").toggleClass("is-active");
				$("body").toggleClass("is-active");
			});
		});
