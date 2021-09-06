<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
	<c:import url="../assets/head.jsp" />
	 <style type="text/css">
   	  .p_container { padding: 30px 0; }
      #contents { text-align: center; overflow: hidden; padding-bottom: 100px; padding-top: 100px; }
      h1 { padding-bottom: 30px; }
      #contents_box { height: 500px; overflow: hidden !important; padding: 0 40px !important; }
      #contents_box span { position: absolute; font-size: 30px; margin: auto; color: white; z-index: 1;
            text-align: center; position: absolute; top: 50%; left: 50%; transform: translate( -50%, -50% ); }
      #contents_box a img { width: 100%; height: 100%; opacity: 0.6; text-decoration: none;
            transition: all 0.3s ease-in-out; }
      #contents_box a:hover img { opacity: 1; transform: scale(1.2); }
      #img_box { overflow: hidden; width: 100%; height: 100%; }
   </style>
	<script src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body>
	<c:import url="../assets/header.jsp" />
   
   	<c:import url="forecast.jsp"/>
   <div id="forecast_box" class="container p_container">
	<c:import url="forecast_graph.jsp" />
   </div>
   
   <div id="covid_box" class="container p_container">
      <c:import url="phpCovid.jsp" />
   </div>
   
   <div id="contents" class="container p_container">
      <h1>CONTENTS</h1>
      <div id="contents_list" class="row" style="margin-top: 30px;">
         <div id="contents_box" class="col-md-4">
            <div id="img_box">
               <a href="${pageContext.request.contextPath}/contents/contents_transport.do">
                  <span>교 통</span>
                  <img alt="" src="${pageContext.request.contextPath}/assets/img/transport.jpeg">         
               </a>            
            </div>
         </div>
         <div id="contents_box" class="col-md-4">
            <div id="img_box">
               <a href="${pageContext.request.contextPath}/contents/contents_news.do">
                  <span>뉴 스</span>
                  <img alt="" src="${pageContext.request.contextPath}/assets/img/news.jpeg">
               </a>
            </div>
         </div>
         <div id="contents_box" class="col-md-4">
            <div id="img_box">            
               <a href="${pageContext.request.contextPath}/contents/contents_finance.do">   
                  <span>금 융</span>
                  <img alt="stock" src="${pageContext.request.contextPath}/assets/img/stock.jpg">
               </a>
            </div>
         </div>
      </div>
   </div>
	<c:import url="../assets/footer.jsp" />
</body>
</html>