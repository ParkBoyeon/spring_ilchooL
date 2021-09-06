<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fnt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.css"/>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="../assets/css/admin_header.css">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="http://code.highcharts.com/highcharts.js"></script>

<!-- 차트 링크 --> 
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>


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

.placeholder {
	padding: 30px;
	text-align: left;
}
</style>
</head>

<body>

	<%@include file="admin_header.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li class="active"><a href="${pageContext.request.contextPath}/admin/admin_dashboard.do"><span
							class="glyphicon glyphicon-th"></span> 대시보드</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/admin_list.do"><span
							class="glyphicon glyphicon-user"></span> 사용자</a></li>
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h2 class="page-header">대시보드</h2>
				<div class="row placeholders">
					<div class="col-xs-6 col-md-4 placeholder">
						<h3 style="display: block; text-align: center;">서울시인원비율</h3>
						<canvas id="dashboardChart1" style="width: 100%"
							class="img-responsive"></canvas>
					</div>
					<div class="col-xs-6 col-md-4 placeholder">
						<h3 style="display: block; text-align: center;">연령비율</h3>
						<canvas id="dashboardChart2" style="width: 100%"
							class="img-responsive"></canvas>
					</div>
					<div class="col-xs-6 col-md-4 placeholder">
						<h3 style="display: block; text-align: center;">남녀비율</h3>
						<canvas id="dashboardChart3" style="width: 100%"
							class="img-responsive"></canvas>
					</div>
					
					
					
				</div>
				
				
				<h3 class="sub-header" style="margin-top: 50px; text-align: center;">주간접속인원</h3>
				<style="width: 100%", class="img-responsive" />
					<canvas id="myChart"></canvas>
				</div>
				
					
				</div>
			</div>
		
	
	<!-- chartjs cdn 참조 -->
	<script src="//cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js"></script>
	
	
	
	<!-- 데이터 시각화 구현 -->
	<script src="js/chart/Chart.js"></script>
<canvas id="myChart"></canvas>
<script>
var ctx = document.getElementById("myChart");
var myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"],
        datasets: [{
            data: [5, 2, 8, 7, 6, 5, 2],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(53, 53, 53, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255,99,132,1)',
                'rgba(25,25,25,1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero:true
                }
            }]
        }
    }
});
</script>

<script>
var ctx = document.getElementById("dashboardChart1");
var dashboardChart = new Chart(ctx, {
    type: 'doughnut',
    data: {
        labels: ["seoul", "Gyeonggi-do"],
        datasets: [{
            label: '# of Votes',
            data: ['${addr1}', '${addr2}'],
            backgroundColor: [
                '#F5dF4D',
                '#939597',
              
            ],
            borderColor: [
                'rgba(255, 255, 255 ,1)',
                'rgba(255, 255, 255 ,1)',
       
                
            ],
            borderWidth: 5
        }]
    },
    options: {
        rotation: 1 * Math.PI,
        circumference: 1 * Math.PI,
        legend: {
            display: false
        },
        tooltip: {
            enabled: false
        },
        cutoutPercentage: 50
    }
});
</script>

<script>
var ctx = document.getElementById("dashboardChart2");
var dashboardChart = new Chart(ctx, {
    type: 'doughnut',
    data: {
        labels: ["20-24세", "25-29세", "30-34세"],
        datasets: [{
            label: '# of Votes',
            data: ['${Old0}', '${Old1}', '${Old2}'],
            backgroundColor: [
            	'#a299ca',
                '#7ccaae',
                '#84c0e9'
            ],
            borderColor: [
            	'rgba(255, 255, 255 ,1)',
                'rgba(255, 255, 255 ,1)',
                'rgba(255, 255, 255 ,1)'
            ],
            borderWidth: 5
        }]
    },
    options: {
        rotation: 1 * Math.PI,
        circumference: 1 * Math.PI,
        legend: {
            display: false
        },
        tooltip: {
            enabled: false
        },
        cutoutPercentage: 50
    }
});
</script>

<script>
var ctx = document.getElementById("dashboardChart3");
var dashboardChart = new Chart(ctx, {
    type: 'doughnut',
    data: {
        labels: ["남", "녀"],
        datasets: [{
            label: '# of Votes',
            data: ['${genderM}', '${genderF}'],
            backgroundColor: [
                '#a8dff1',
                '#fed5d9'
                
            ],
            borderColor: [
                'rgba(255, 255, 255 ,1)',
                'rgba(255, 255, 255 ,1)'
             
            ],
            borderWidth: 5
        }]
    },
    options: {
        rotation: 1 * Math.PI,
        circumference: 1 * Math.PI,
        legend: {
            display: false
        },
        tooltip: {
            enabled: false
        },
        cutoutPercentage: 50
    }
});
</script>
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</body>

</html>