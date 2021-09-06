<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/menu.css">
</head>
<header>
	<div id="header" class="container">
		<div class="hamburger" id="hamburger">
			<span class="line"></span>
			<span class="line"></span>
			<span class="line"></span>
		</div>
		<div id="menuNav" class="overlay">
			<div class="overlay-content">
				<a href="${pageContext.request.contextPath}">Home</a>
				<a href="${pageContext.request.contextPath}/contents/contents_transport.do">Transport</a>
				<a href="${pageContext.request.contextPath}/contents/contents_news.do">News</a>
				<a href="${pageContext.request.contextPath}/contents/contents_finance.do">Finance</a>
				  <c:choose>
                    <c:when test="${member == null}">
                            <a href="${pageContext.request.contextPath}/account/login.do" title="로그인" class="login" style="margin-top: 25px;" >
                                <i class="fas fa-sign-in-alt"></i>
                                <span>Login</span>
                            </a>
                    </c:when>
                    <c:otherwise>
                   		<c:if test="${fn:indexOf(path, 'account/logout') > -1}">class="active"</c:if>
                            <a href="${pageContext.request.contextPath}/mypage/mypage.do" title="마이페이지" class="mypage" style="margin-top: 25px;">
                                <i class="fas fa-sign-out-alt"></i>
                                <span>Mypage</span>
                            </a>
                        <c:if test="${fn:indexOf(path, 'account/logout') > -1}">class="active"</c:if>
                            <a href="${pageContext.request.contextPath}/rest/account/logout" title="로그아웃" class="logout">
                                <i class="fas fa-sign-out-alt"></i>
                                <span>Logout</span>
                            </a>
                    </c:otherwise>
                </c:choose>			
                </div>
		</div>
		
		<div id="logo">
			<a href="${pageContext.request.contextPath}"> <img alt="logo"
				src="${pageContext.request.contextPath}/assets/img/Logo(line).png"
				style="height: 30px;">
			</a>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/assets/js/header.js"></script>
</header>