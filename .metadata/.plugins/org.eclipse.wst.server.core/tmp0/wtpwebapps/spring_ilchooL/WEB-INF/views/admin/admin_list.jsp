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

input {
	display: inline-block;
	height: 33px;
	font-size: 13px;
	color: #000;
	border: 2px solid#87CEFA;
	border-radius: 4px;
	vertical-align: middle;
	padding: 5px 10px ;
	outline: 0;
	-webkit-appearance: none;
	background-color: transparent;
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
				<h2 class="page-header">회원 리스트</h2>

				<div class="table-responsive">
   <!-- 검색폼 -->
    <form method="get" action="${pageContext.request.contextPath}/admin/admin_list.do">
        <label for="keyword" class="search">검색: </label>
        <input type="search" name="keyword" id="keyword" placeholder="회원 찾기" value="${keyword}" />
        <button type="submit" class="btn btn-info outline">검색</button>
    </form>

    <hr />

    <!-- 조회 결과 목록 -->
    <table class="table table-hover">
        <thead>
            <tr>
                <th class="text-center">#</th>
                <th class="text-center">아이디</th>
                <th class="text-center">이름</th>
                <th class="text-center">이메일</th>
                <th class="text-center">번호</th>
                <th class="text-center">활동</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <%-- 조회결과가 없는 경우 --%>
                <c:when test="${output == null || fn:length(output) == 0}">
                    <tr>
                        <td colspan="3" align="center">조회결과가 없습니다.</td>
                    </tr>
                </c:when>
                <%-- 조회결과가 있는  경우 --%>
                <c:otherwise>
                    <%-- 조회 결과에 따른 반복 처리 --%>
                    <c:forEach var="item" items="${output}" varStatus="status">
                        <%-- 출력을 위해 준비한 학과이름과 위치 --%>
                        <c:set var="user_id" value="${item.user_id}" />
                        <c:set var="user_name" value="${item.user_name}" />
                        <c:set var="email" value="${item.email}" />
                        <c:set var="phone" value="${item.phone}" />

                        <%-- 검색어가 있다면? --%>
                        <c:if test="${keyword != ''}">
                            <%-- 검색어에 <mark> 태그를 적용하여 형광팬 효과 준비 --%>
                            <c:set var="mark" value="<mark>${keyword}</mark>" />
                            <%-- 출력을 위해 준비한 학과이름과 위치에서 검색어와 일치하는 단어를 형광팬 효과로 변경 --%>
                            <c:set var="user_id" value="${fn:replace(user_id, keyword, mark)}" />
                            <c:set var="user_name" value="${fn:replace(user_name, keyword, mark)}" />
                            <c:set var="email" value="${fn:replace(email, keyword, mark)}" />
                            <c:set var="phone" value="${fn:replace(phone, keyword, mark)}" />
                        </c:if>
                        
                        <tr>
                            <td class="text-center">${item.id}</td>
                            <td class="text-center">${user_id}</td>
                            <td class="text-center">${user_name}</td>
                            <td class="text-center">${email}</td>
                            <td class="text-center">${phone}</td>
                            <td class="text-center"><a href="${pageContext.request.contextPath}/admin/admin_modify.do?id=${item.id}" class="btn btn-primary outline btn-sm">수정</a>
                            <a href="${pageContext.request.contextPath}/admin/delete_ok.do?id=${item.id}" class="btn btn-danger outline btn-sm">삭제</a>
                            <a href="${pageContext.request.contextPath}/admin/admin_log.do?id=${item.id}" class="btn btn-success outline btn-sm">로그</a></td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
    
        <!-- 페이지 번호 구현 -->
    <%-- 이전 그룹에 대한 링크 --%>
    <c:choose>
        <%-- 이전 그룹으로 이동 가능하다면? --%>
        <c:when test="${pageData.prevPage > 0}">
            <%-- 이동할 URL 생성 --%>
            <c:url value="/admin/admin_list.do" var="prevPageUrl">
                <c:param name="page" value="${pageData.prevPage}" />
                <c:param name="keyword" value="${keyword}" />
            </c:url>
            <a href="${prevPageUrl}">[이전]</a>
        </c:when>
        <c:otherwise>
            [이전]
        </c:otherwise>
    </c:choose>
    
    <%-- 페이지 번호 (시작 페이지 부터 끝 페이지까지 반복) --%>
    <c:forEach var="i" begin="${pageData.startPage}" end="${pageData.endPage}" varStatus="status">
        <%-- 이동할 URL 생성 --%>
        <c:url value="/admin/admin_list.do" var="pageUrl">
            <c:param name="page" value="${i}" />
            <c:param name="keyword" value="${keyword}" />
        </c:url>
        
        <%-- 페이지 번호 출력 --%>
        <c:choose>
            <%-- 현재 머물고 있는 페이지 번호를 출력할 경우 링크 적용 안함 --%>
            <c:when test="${pageData.nowPage == i}">
                <strong>[${i}]</strong>
            </c:when>
            <%-- 나머지 페이지의 경우 링크 적용함 --%>
            <c:otherwise>
                <a href="${pageUrl}">[${i}]</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    
    <%-- 다음 그룹에 대한 링크 --%>
    <c:choose>
        <%-- 다음 그룹으로 이동 가능하다면? --%>
        <c:when test="${pageData.nextPage > 0}">
            <%-- 이동할 URL 생성 --%>
            <c:url value="/admin/admin_list.do" var="nextPageUrl">
                <c:param name="page" value="${pageData.nextPage}" />
                <c:param name="keyword" value="${keyword}" />
            </c:url>
            <a href="${nextPageUrl}">[다음]</a>
        </c:when>
        <c:otherwise>
            [다음]
        </c:otherwise>
    </c:choose>

				</div>
			</div>
			<!-- contents -->
		</div>
	</div>
	<div class="modal fade" id="delete_user">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">회원 정보 삭제</h4>
				</div>
				<div class="modal-body">
					<p>회원 정보를 삭제하시겠습니까?</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
				</div>
			</div>
		</div>
	</div>
	<footer>
        <div class="container-fluid">
            <div class="col-md-12 text-right">
                <p>&copy;2021 <span class="text-primary">ilchooL.</span> All Rights Reserved.</p>
            </div>
        </div>
    </footer>
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</body>

</html>