<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<c:import url="../assets/head.jsp" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/transport.css">
</head>
<body>
	<div id="header">
		<c:import url="../assets/header.jsp" />
	</div>
	
	<div class="trans_main_box">
		<div class="row box">
			<div id="left" class="col-md-3">
				<div id="findWay">
					<div id="search_findWayMode" class="well">
						<div id="input_box" class="row">
							<div id="input_start_box" class="input-group">
								<input type="text" id="startPlaces" class="form-control" placeholder="출발지" />
								<a href="#" class="input-group-addon" onclick="searchPlaces('startPlaces')">
									<i class="glyphicon glyphicon-search"></i>
								</a>
							</div>
							<div id="input_end_box" class="input-group" style="padding-top: 5px;">
								<input type="text" id="endPlaces" class="form-control" placeholder="도착지" />
								<a href="#" class="input-group-addon" onclick="searchPlaces('endPlaces')">
									<i class="glyphicon glyphicon-search"></i>
								</a>
							</div>
						</div>
						<a href="#" id="change" class="btn btn-block btn-primary outline btn_change" onclick="" style="margin-top: 5px;" onclick="">change</a>
						<a href="#" id="search" class="btn btn-block btn-primary outline btn_search" onclick="">길찾기</a>
					</div>

					<div id="result_findWayMode" style="overflow: auto; max-height: 628px;">
						<div id="trans">
							<ul id="placesList"></ul>
							<div id="pagination"></div>
						</div>
					</div>
				</div>
			</div>

			<div class="col-md-9">
				<div id="map" style="height: 837px;"></div>
			</div>
		</div>
	</div>
	
	<c:import url="../assets/footer.jsp" />

<!-- 카카오 Map API Javascript Key 입력  -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=415bf06cd326403404fe07cf75f2f283&libraries=services"></script>
<script src="${pageContext.request.contextPath}/assets/js/map.js"></script>
<script type="text/javascript">
	$(function() {
		$("#search").click(function() {
			searchPubTransPathAJAX();
		});
		
		$("#change").click(function() {
			var temps = $("#startPlaces").val();
			var tempe = $("#endPlaces").val();
			$("#startPlaces").val(tempe);
			$("#endPlaces").val(temps);
			inputChange();
		});
		
		$(document).on("click", "#search_list", function() {
			var target = $(this).find(".sr-only").html();
			listClick(target);
		});
	});
</script>
</body>
</html>