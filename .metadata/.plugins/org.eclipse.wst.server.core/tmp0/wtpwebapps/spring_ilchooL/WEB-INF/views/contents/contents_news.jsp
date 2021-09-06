<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<c:import url="../assets/head.jsp" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/datepicker.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/datepicker.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/contents_news.css" />
<script src="https://cdn.anychart.com/releases/v8/js/anychart-base.min.js"></script>
<script src="https://cdn.anychart.com/releases/v8/js/anychart-tag-cloud.min.js"></script>
<script type="text/javascript">
	var rank0 = "${rank0}";
	var rank1 = "${rank1}";
	var rank2 = "${rank2}";
	var rank3 = "${rank3}";
	var rank4 = "${rank4}";
	
	var key = "${key}";
	
	var param;

		anychart.onDocumentReady(function() {
			var data = [
			    {"x": rank0, "value": 100, category: "5"},
			    {"x": rank1, "value": 100, category: "1"},
			    {"x": rank2, "value": 90, category: "9"},
			    {"x": rank3, "value": 90, category: "5"},
			    {"x": rank4, "value": 80, category: "9"}
			  ];
			
			// create a chart and set the data
		    var chart = anychart.tagCloud(data);
	
		    // enable HTML for tooltips
		    chart.tooltip().useHtml(true);
	
		    // configure tooltips
		    chart.tooltip().format(function() {
		      var percentOfTotal = (this.getData("value")*100)/this.getStat("sum");
		      if (percentOfTotal < 7)
		        return percentOfTotal.toFixed(1);
		      if (percentOfTotal > 15)
		        return "";
		      return "";
		    });
			
			chart.title('news');
			chart.angles([0]);
			
			chart.container("container");
			chart.draw();	
			
	
		    chart.listen("pointClick", function(e){
			param = e.point.get("x");
		
			var search = JSON.stringify(param);
			document.getElementById('search').value = search.substring(1, search.length-1);
			
		 	   });
		});
		
	</script>
</head>
<body>
	<div id="header">
		<c:import url="../assets/header.jsp" />
	</div>
	<div class="container" style="padding-bottom: 100px;">
		<div class="row">

			<div class="col-sm-12 news_graph">
				<div id="container">
					
				</div>
			</div>
			
			<div class="col-sm-12">
				<form id="form" action="${pageContext.request.contextPath}/contents/contents_search.do" method="post" class="form-inline">
					<div class="form-group col-xs-10" style="padding-left: 0px;">
    
						<input id="search" type="text" name="search" placeholder="키워드를 입력하세요" class="form-control" />
					</div>
					<input id="btn" type="submit" class="btn btn-default outline col-xs-2" value="검색" />
				</form>
			</div>
			
			<div class="col-xs-12 news_ex">
				<div id="line">
					<div class="circle_grey"></div>
					<div class="circle"></div>
					<p>키워드를 선택하시면 주요 뉴스를 확인할 수 있습니다.</p>
				</div>
				<h3>키워드를 입력하세요.</h3>
				
			</div>

		</div>

	</div>

	
	
	<c:import url="../assets/footer.jsp" />
	<script>
	$("#btn").click(function(){
		if($.trim($("#search").val())=='') {
			alert('검색어를 입력하세요.');
			return false;
		}
		$("#form").submit();
	});
	</script>
	
</body>
</html>