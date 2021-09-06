<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,minimum-scale=1.0,
  maximum-scale=1.0,user-scalable=no">
<title>Admin</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath }/assets/ico/favicon.png" />
<link rel="apple-touch-icon-precomposed"
	href="${pageContext.request.contextPath }/assets/ico/apple-touch-icon-144-precomposed.png" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="../assets/css/admin_header.css">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
* {
	font-family: NanumGothic;
}

body {
	padding-top: 50px;
}

.navbar-fixed-top {
	border: 0;
}

.sidebar {
	display: none;
}

@media ( min-width : 768px) {
	.sidebar {
		position: fixed;
		top: 51px;
		bottom: 0;
		left: 0;
		z-index: 1000;
		display: block;
		padding: 20px;
		overflow-x: hidden;
		overflow-y: auto;
		background-color: #f5f5f5;
		border-right: 1px solid #eee;
	}
}

.nav-sidebar {
	margin-right: -21px;
	margin-bottom: 20px;
	margin-left: -20px;
}

.nav-sidebar>li>a {
	padding-right: 20px;
	padding-left: 20px;
}

.nav-sidebar>.active>a, .nav-sidebar>.active>a:hover, .nav-sidebar>.active>a:focus
	{
	color: #fff;
	background-color: #428bca;
}

.main {
	padding: 20px;
}

@media ( min-width : 768px) {
	.main {
		padding-right: 40px;
		padding-left: 40px;
	}
}

.main .page-header {
	margin-top: 0;
}

.main {
	color: #333;
}
</style>
</head>

<body>

	<%@include file="admin_header.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li><a href="${pageContext.request.contextPath}/admin/admin_dashboard.do"><span
							class="glyphicon glyphicon-th"></span> 대시보드</a></li>
					<li class="active"><a href="${pageContext.request.contextPath}/admin/admin_list.do"><span
							class="glyphicon glyphicon-user"></span> 사용자</a></li>
				</ul>
			</div>
			<!-- contents -->
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
							<h2 class="page-header">${output.user_id}의 로그</h2>
				<div class="table-responsive">
					<table class="table table-hover">
					
						<thead>
							<tr>
								<th class="text-center">#</th>
								<th class="text-center">접속 일자</th>
								<th class="text-center">접속 시간</th>
								<th class="text-center">접속 디바이스</th>
								<th class="text-center">접속 페이지</th>
							</tr>
						</thead>
						        <tbody>

                    <%-- 조회 결과에 따른 반복 처리 --%>
                    <c:forEach var="item" items="${logoutput}" varStatus="status">
                        <%-- 출력을 위해 준비한 학과이름과 위치 --%>
                        <c:set var="log_date" value="${item.log_date}" />
                        <c:set var="log_time" value="${item.log_time}" />
                        <c:set var="log_device" value="${item.log_device}" />
                        <c:set var="log_page" value="${item.log_page}" />

                        <tr>
                            <td class="text-center">${item.log_id}</td>
                            <td class="text-center">${log_date}</td>
                            <td class="text-center">${log_time}</td>
                            <td class="text-center">${log_device}</td>
                            <td class="text-center">${log_page}</td>
                        </tr>
                    </c:forEach>
        </tbody>
					</table>
					<nav>
						<ul class="pager">
							<li><a href="#"><span>&laquo;</span></a></li>
							<li class="active"><a href="#">1</a></li>

							<li><a href="#"><span>&raquo;</span></a></li>
						</ul>
					</nav>
				</div>
			</div>
			<!-- contents -->
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		
	</script>
</body>

</html>