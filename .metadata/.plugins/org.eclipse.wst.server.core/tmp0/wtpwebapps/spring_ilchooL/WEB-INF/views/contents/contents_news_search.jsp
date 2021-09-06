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
	<div class="container">
		<div class="row">

			<div class="col-sm-12 news_graph">
				<div id="container">
					
				</div>
			</div>
			
			<div>
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
				<h3>${keyword}</h3>
			</div>

			<div class="row">
				<div class="col-md-12">
					<div class="col-lg-4 col-md-6 col-xs-12 news_box">
						<a data-toggle="modal" href="#news_modal1"> <span
							class="news_contents_box"> <span id="text"> ${str1}
							</span> <span id="text_date"> ${news_date1}
							</span>
						</span>
						</a>
					</div>
				
					<div class="col-lg-4 col-md-6 col-xs-12 news_box">
						<a data-toggle="modal" href="#news_modal2"> <span
							class="news_contents_box"> <span id="text"> ${str2}
							</span> <span id="text_date"> ${news_date2}
							</span>
						</span>
						</a>
					</div>
				
					<div class="col-lg-4 col-md-6 col-xs-12 news_box">
						<a data-toggle="modal" href="#news_modal3"> <span
							class="news_contents_box"> <span id="text"> ${str3}
							</span> <span id="text_date"> ${news_date3}
							</span>
						</span>
						</a>
					</div>
				
					<div class="col-lg-4 col-md-6 col-xs-12 news_box">
						<a data-toggle="modal" href="#news_modal4"> <span
							class="news_contents_box"> <span id="text"> ${str4}
							</span> <span id="text_date"> ${news_date4}
							</span>
						</span>
						</a>
					</div>
				
					<div class="col-lg-4 col-md-6 col-xs-12 news_box">
						<a data-toggle="modal" href="#news_modal5"> <span
							class="news_contents_box"> <span id="text"> ${str5}
							</span> <span id="text_date"> ${news_date5}
							</span>
						</span>
						</a>
					</div>
				
					<div class="col-lg-4 col-md-6 col-xs-12 news_box">
						<a data-toggle="modal" href="#news_modal6"> <span
							class="news_contents_box"> <span id="text"> ${str6}
							</span> <span id="text_date"> ${news_date6}
							</span>
						</span>
						</a>
					</div>
				
					<div class="col-lg-4 col-md-6 col-xs-12 news_box">
						<a data-toggle="modal" href="#news_modal7"> <span
							class="news_contents_box"> <span id="text"> ${str7}
							</span> <span id="text_date"> ${news_date7}
							</span>
						</span>
						</a>
					</div>
				
					<div class="col-lg-4 col-md-6 col-xs-12 news_box">
						<a data-toggle="modal" href="#news_modal8"> <span
							class="news_contents_box"> <span id="text"> ${str8}
							</span> <span id="text_date"> ${news_date8}
							</span>
						</span>
						</a>
					</div>
				
					<div class="col-lg-4 col-md-6 col-xs-12 news_box">
						<a data-toggle="modal" href="#news_modal9"> <span
							class="news_contents_box"> <span id="text"> ${str9}
							</span> <span id="text_date"> ${news_date9}
							</span>
						</span>
						</a>
					</div>
				</div>
			</div>
		</div>

	</div>

	<!-- .modal -->
	<div id="news_modal1" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="news_modalLabel" aria-hidden="true">
		<!-- .modal-dialog -->
		<div class="modal-dialog">
			<!-- .modal-content -->
			<div class="modal-content" id="modal-content">
				<!-- 제목 -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>

					<h3 class="modal-title" id="myModalLabel">${str1}</h3>
				</div>
				<!-- 내용 -->
				<div class="modal-body">
				
					${element1}
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- .modal -->
	<div id="news_modal2" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="news_modalLabel" aria-hidden="true">
		<!-- .modal-dialog -->
		<div class="modal-dialog">
			<!-- .modal-content -->
			<div class="modal-content" id="modal-content">
				<!-- 제목 -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>

					<h3 class="modal-title" id="myModalLabel">${str2}</h3>
				</div>
				<!-- 내용 -->
				<div class="modal-body">
					
					${element2}
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- .modal -->
	<div id="news_modal3" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="news_modalLabel" aria-hidden="true">
		<!-- .modal-dialog -->
		<div class="modal-dialog">
			<!-- .modal-content -->
			<div class="modal-content" id="modal-content">
				<!-- 제목 -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>

					<h3 class="modal-title" id="myModalLabel">${str3}</h3>
				</div>
				<!-- 내용 -->
				<div class="modal-body">
					
					${element3}
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- .modal -->
	<div id="news_modal4" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="news_modalLabel" aria-hidden="true">
		<!-- .modal-dialog -->
		<div class="modal-dialog">
			<!-- .modal-content -->
			<div class="modal-content" id="modal-content">
				<!-- 제목 -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>

					<h3 class="modal-title" id="myModalLabel">${str4}</h3>
				</div>
				<!-- 내용 -->
				<div class="modal-body">
					
					${element4}
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- .modal -->
	<div id="news_modal5" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="news_modalLabel" aria-hidden="true">
		<!-- .modal-dialog -->
		<div class="modal-dialog">
			<!-- .modal-content -->
			<div class="modal-content" id="modal-content">
				<!-- 제목 -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>

					<h3 class="modal-title" id="myModalLabel">${str5}</h3>
				</div>
				<!-- 내용 -->
				<div class="modal-body">
				
					${element5}
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- .modal -->
	<div id="news_modal6" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="news_modalLabel" aria-hidden="true">
		<!-- .modal-dialog -->
		<div class="modal-dialog">
			<!-- .modal-content -->
			<div class="modal-content" id="modal-content">
				<!-- 제목 -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>

					<h3 class="modal-title" id="myModalLabel">${str6}</h3>
				</div>
				<!-- 내용 -->
				<div class="modal-body">
				
					${element6}
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- .modal -->
	<div id="news_modal7" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="news_modalLabel" aria-hidden="true">
		<!-- .modal-dialog -->
		<div class="modal-dialog">
			<!-- .modal-content -->
			<div class="modal-content" id="modal-content">
				<!-- 제목 -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>

					<h3 class="modal-title" id="myModalLabel">${str7}</h3>
				</div>
				<!-- 내용 -->
				<div class="modal-body">
				
					${element7}
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- .modal -->
	<div id="news_modal8" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="news_modalLabel" aria-hidden="true">
		<!-- .modal-dialog -->
		<div class="modal-dialog">
			<!-- .modal-content -->
			<div class="modal-content" id="modal-content">
				<!-- 제목 -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>

					<h3 class="modal-title" id="myModalLabel">${str8}</h3>
				</div>
				<!-- 내용 -->
				<div class="modal-body">
				
					${element8}
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- .modal -->
	<div id="news_modal9" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="news_modalLabel" aria-hidden="true">
		<!-- .modal-dialog -->
		<div class="modal-dialog">
			<!-- .modal-content -->
			<div class="modal-content" id="modal-content">
				<!-- 제목 -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>

					<h3 class="modal-title" id="myModalLabel">${str9}</h3>
				</div>
				<!-- 내용 -->
				<div class="modal-body">
				
					${element9}
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	

	
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