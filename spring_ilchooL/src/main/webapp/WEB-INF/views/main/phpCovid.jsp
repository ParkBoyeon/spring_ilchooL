<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<c:import url="../assets/head.jsp" />
	<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/covid_graph.css" />
</head>
<body>
	<div id="covid_box" class="container">
		<div id="covid_title">
			<h1>COVID-19 Statistics</h1>
		</div>
		<div id="covid_count" class="row">
			<ul>
				<li style="text-align: left; padding-left: 30px;">
					데이터 기준 : 
					<fmt:parseDate value="${date }" var="parseDate" pattern="yyyymmdd" />
					<fmt:formatDate value="${parseDate }" pattern="yyyy-mm-dd" />
					(서울시)
				</li>
				<li id="today_title">오늘 확진자수<br/>
					<span>${confirmed_0}명</span>
				</li>
				<li class="col-md-3 col-xs-6">누적 확진자<br/>
					<span class="s_confirmed main_count">${confirmed_acc}</span>
					<br/>
					<span class="confirmed_box d_box">
					<c:choose>
						<c:when test="${confirmed_0 > 0 }">
							${confirmed_0 }&nbsp;<i class="glyphicon glyphicon-arrow-up" id="up"></i>
						</c:when>
						<c:otherwise>
							0
						</c:otherwise>
					</c:choose>
					</span>
				</li>
				<li class="col-md-3 col-xs-6">누적 격리해제<br/>
					<span class="s_released main_count">${released_acc}</span>
					<br/>
					<span class="released_box d_box">
					<c:choose>
						<c:when test="${released_0 > 0 }">
							${released_0}&nbsp;<i class="glyphicon glyphicon-arrow-up" id="up"></i>
						</c:when>
						<c:otherwise>
							0
						</c:otherwise>
					</c:choose>
					</span>
				</li>				
				<li class="col-md-3 col-xs-6">격리중<br/>
					<span class="s_active main_count">${active_0}</span>
					<br/>
					<span class="active_box d_box">
					<c:choose>
						<c:when test="${active_0 - active_1 > 0 }">
							${active_0 - active_1 }&nbsp;<i class="glyphicon glyphicon-arrow-up" id="up"></i>
						</c:when>
						<c:when test="${active_0 - active_1 < 0 }">
							${active_1 - active_0 }&nbsp;<i class="glyphicon glyphicon-arrow-down" id="down"></i>
						</c:when>
						<c:otherwise>
							0
						</c:otherwise>
					</c:choose>
					</span>
				</li>
				<li class="col-md-3 col-xs-6">누적 사망자<br/>
					<span class="s_death main_count">${death_acc}</span>
					<br/>
					<span class="death_box d_box">
					<c:choose>
						<c:when test="${death_0 > 0 }">
							${death_0}&nbsp;<i class="glyphicon glyphicon-arrow-up" id="up"></i>
						</c:when>
						<c:otherwise>
							0
						</c:otherwise>
					</c:choose>
					</span>
				</li>					
			</ul>
		</div>
		<div id="covid_graph" class="row">
			<div class="covid_graph_btn_box col-md-12 col-xs-12 row">
				<div class="btn_box col-md-3 col-xs-6">
					<button type="button" id="con_btn" class="btn btn-danger outline graph_btn">확진자</button>
				</div>
				<div class="btn_box col-md-3 col-xs-6">
					<button type="button" id="rel_btn" class="btn btn-primary outline graph_btn">격리해제</button>
				</div>
				<div class="btn_box col-md-3 col-xs-6">
					<button type="button" id="act_btn" class="btn btn-success outline graph_btn">격리중</button>
				</div>
				<div class="btn_box col-md-3 col-xs-6">
					<button type="button" id="dea_btn" class="btn btn-warning outline graph_btn">사망자</button>
				</div>
			</div>
			<div class="canvas_chart_container col-md-12 col-xs-12">
				<canvas id="covidChart"></canvas>
			</div>
		</div>
	</div>
	
	<script src="//cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js"></script>
	<script>
		// controller에서 전달한 데이터를 jstl문법을 사용하여 Chart.js 라이브러리에서 사용할 데이터 배열 생성
		let con_data = [ ${confirmed_6}, ${confirmed_5}, ${confirmed_4}, ${confirmed_3}, ${confirmed_2}, ${confirmed_1}, ${confirmed_0} ];
		let death_data = [ ${death_6}, ${death_5}, ${death_4}, ${death_3}, ${death_2}, ${death_1}, ${death_0} ];
		let rel_data = [ ${released_6}, ${released_5}, ${released_4}, ${released_3}, ${released_2}, ${released_1}, ${released_0} ];
		let act_data = [
			${active_6 - active_7}, ${active_5 - active_6}, ${active_4 - active_5}, ${active_3 - active_4},
			${active_2 - active_3}, ${active_1 - active_2}, ${active_0 - active_1} ];
		
		// Chart.js 라이브러리 -> 데이터 시각화 (선,막대 그래프 구현)
		var ctx = document.getElementById('covidChart').getContext('2d');
		var covidChart = new Chart(ctx, {
		    type: 'line',
		    data: {
		        labels: [ '${date_6}', '${date_5}', '${date_4}', '${date_3}', '${date_2}', '${date_1}', '${date_0}'],
		        datasets: [{
		            label: '일일 확진자 수',
		            data: con_data,
		            backgroundColor: [
		                'rgba(185, 44, 40, 0.2)'
		            ],
		            borderColor: [
		            	'rgba(185, 44, 40, 1)'
		            ],
		            borderWidth: 1,
		            lineTension: 0
		        }]
		    },
		    options: {
		        scales: {
		        	yAxes: {
		        		ticks: {
		                	beginAtZero: true
		        		}
		            },
		            xAxes: [{
			            offset: false
		            }]
		        },
			    title: {
		    		display: true,
		    		position: 'top',
		    		text: '확진자 추이',
		    		fontSize: 20 
		    	},
		    	maintainAspectRatio: false,
		        aspectRatio: 1
		    }
		});
		
		$(function() {
			$("#con_btn").click(function() {
				covidChart.config.type = 'line';
				covidChart.config.options.title.text = '확진자 추이';
				covidChart.config.options.scales.xAxes[0].offset = false;
				covidChart.config.data.datasets[0].label = '일일 확진자 수';
				covidChart.config.data.datasets[0].data = con_data;
				covidChart.config.data.datasets[0].backgroundColor = 'rgba(217, 83, 79, 0.2)';
				covidChart.config.data.datasets[0].borderColor = 'rgba(217, 83, 79, 1)';
				
				covidChart.update();
			});
			
			$("#rel_btn").click(function() {
				covidChart.config.type = 'line';
				covidChart.config.options.title.text = '격리해제 추이';
				covidChart.config.options.scales.xAxes[0].offset = false;
				covidChart.config.data.datasets[0].label = '일일 격리해제 수';
				covidChart.config.data.datasets[0].data = rel_data;
				covidChart.config.data.datasets[0].backgroundColor = 'rgba(66, 139, 202, 0.2)';
				covidChart.config.data.datasets[0].borderColor = 'rgba(66, 139, 202, 1)';
				
				covidChart.update();
			});
			
			$("#act_btn").click(function() {
				covidChart.config.type = 'bar';
				covidChart.config.options.title.text = '격리자 추이';
				covidChart.config.options.scales.xAxes[0].offset = true;
				covidChart.config.data.datasets[0].label = '전일 대비 격리자 수';
				covidChart.config.data.datasets[0].data = act_data;
				covidChart.config.data.datasets[0].backgroundColor = 'rgba(92, 184, 92, 0.2)';
				covidChart.config.data.datasets[0].borderColor = 'rgba(92, 184, 92, 1)';
				
				covidChart.update();
			});
			
			$("#dea_btn").click(function() {
				covidChart.config.type = 'bar';
				covidChart.config.options.title.text = '사망자 추이';
				covidChart.config.options.scales.xAxes[0].offset = true;
				covidChart.config.data.datasets[0].label = '일일 사망자 수';
				covidChart.config.data.datasets[0].data = death_data;
				covidChart.config.data.datasets[0].backgroundColor = 'rgba(240, 173, 78, 0.2)';
				covidChart.config.data.datasets[0].borderColor = 'rgba(240, 173, 78, 1)';
				
				covidChart.update();
			});
		});
	</script>
</body>
</html>