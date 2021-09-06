<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<c:import url="../assets/head.jsp" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/contents_finance.css" />
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.css" />
</head>
<body>
	<div id="header">
		<c:import url="../assets/header.jsp" />
	</div>
	<div class="container">
		<div class="row">
			<div class="tabs col-md-12 col-sm-12">
				<div class="pull-right">
					<button type="button" id="kosdaq_btn" class="btn btn-default outline btn-lg">KOSDAQ</button>
				</div>
				<div class="pull-right">
					<button type="button" id="kospi_btn" class="btn btn-default outline btn-lg">KOSPI</button>
				</div>

				<!-- 그래프를 표시할 위치 -->
				<div id="canvasgraph" class="col-md-12 col-xs-12">
					<canvas id="kospiChart"></canvas>
				</div>
			</div>

			<div class="col-md-4 col-sm-12">
				<p id="title">해외 증시</p>
				<div class="finance md4 stock">
					<div class="clearfix">
						<p id="name">${strstock11}</p>
						<p id="name">${strstock13}</p>
						<p id="name">${strstock12}</p>
					</div>
					<div class="clearfix">
						<p id="name">${strstock21}</p>
						<p id="name">${strstock23}</p>
						<p id="name">${strstock22}</p>
					</div>
					<div class="clearfix">
						<p id="name">${strstock31}</p>
						<p id="name">${strstock33}</p>
						<p id="name">${strstock32}</p>
					</div>
					<div class="clearfix">
						<p id="name">${strstock41}</p>
						<p id="name">${strstock43}</p>
						<p id="name">${strstock42}</p>
					</div>
					<div class="clearfix">
						<p id="name">${strstock51}</p>
						<p id="name">${strstock53}</p>
						<p id="name">${strstock52}</p>
					</div>

				</div>
			</div>
			<div class="col-md-4 col-sm-12">
				<p id="title">인기 종목</p>
				<div class="finance md4 popular">
					<div class="clearfix">
						<p id="name">${strpopular11}</p>
						<p id="name">${strpopular13}</p>
						<p id="name">${strpopular12}</p>
					</div>
					<div class="clearfix">
						<p id="name">${strpopular21}</p>
						<p id="name">${strpopular23}</p>
						<p id="name">${strpopular22}</p>
					</div>
					<div class="clearfix">
						<p id="name">${strpopular31}</p>
						<p id="name">${strpopular33}</p>
						<p id="name">${strpopular32}</p>
					</div>
					<div class="clearfix">
						<p id="name">${strpopular41}</p>
						<p id="name">${strpopular43}</p>
						<p id="name">${strpopular42}</p>
					</div>
					<div class="clearfix">
						<p id="name">${strpopular51}</p>
						<p id="name">${strpopular53}</p>
						<p id="name">${strpopular52}</p>
					</div>
				</div>
			</div>


			<div class="col-md-4 col-sm-12">
				<p id="title">거래 상위</p>
				<div class="finance md4 top">
					<div class="clearfix">
						<p id="name">${strtop11}</p>
						<p id="name">${strtop14}</p>
						<p id="name">${strtop13}</p>
						<p id="name">${strtop12}</p>
					</div>
					<div class="clearfix">
						<p id="name">${strtop21}</p>
						<p id="name">${strtop24}</p>
						<p id="name">${strtop23}</p>
						<p id="name">${strtop22}</p>
					</div>
					<div class="clearfix">
						<p id="name">${strtop31}</p>
						<p id="name">${strtop34}</p>
						<p id="name">${strtop33}</p>
						<p id="name">${strtop32}</p>
					</div>
					<div class="clearfix">
						<p id="name">${strtop41}</p>
						<p id="name">${strtop44}</p>
						<p id="name">${strtop43}</p>
						<p id="name">${strtop42}</p>
					</div>
					<div class="clearfix">
						<p id="name">${strtop51}</p>
						<p id="name">${strtop54}</p>
						<p id="name">${strtop53}</p>
						<p id="name">${strtop52}</p>
					</div>
					
				</div>
			</div>
		</div> 
	</div>
	<c:import url="../assets/footer.jsp" />

	<!-- Optional JavaScript; choose one of the two! -->
	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script src="../assets/js/jquery-1.10.2.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js"></script>
	<!-- <script src="../assets/js/contents_finance.js"></script> -->
	<script type="text/javascript">
		/*  $(function() {
			stock();
			popular();
			top();
			
		}); 
		 */
		$(function() {
			// 해외 증시
			var stockud1 = "${stockud1}";
			var stockud2 = "${stockud2}";
			var stockud3 = "${stockud}";
			var stockud4 = "${stockud4}";
			var stockud5 = "${stockud5}";

			function stock() {
				if (stockud1 == "하락") {
					$(".stock #name:eq(1)").after("<p class='name'><img src='../assets/img/stockdown.png' class='name stock_img'/></p>");
					$(".stock #name:eq(1)").css("color", "blue");
					$(".stock #name:eq(2)").css("color", "blue");
				} else if (stockud1 == "상승") {
					$(".stock #name:eq(1)").after("<p class='name'><img src='../assets/img/stockup.png' class='name stock_img'/></p>");
					$(".stock #name:eq(1)").css("color", "red");
					$(".stock #name:eq(2)").css("color", "red");
				}

				if (stockud2 == "하락") {
					$(".stock #name:eq(4)").after("<p class='name'><img src='../assets/img/stockdown.png' class='name stock_img'/></p>");
					$(".stock #name:eq(4)").css("color", "blue");
					$(".stock #name:eq(5)").css("color", "blue");
				} else if (stockud2 == "상승") {
					$(".stock #name:eq(4)").after("<p class='name'><img src='../assets/img/stockup.png' class='name stock_img'/></p>");
					$(".stock #name:eq(4)").css("color", "red");
					$(".stock #name:eq(5)").css("color", "red");
				}

				if (stockud3 == "하락") {
					$(".stock #name:eq(7)").after("<p class='name'><img src='../assets/img/stockdown.png' class='name stock_img'/></p>");
					$(".stock #name:eq(7)").css("color", "blue");
					$(".stock #name:eq(8)").css("color", "blue");
				} else if (stockud3 == "상승") {
					$(".stock #name:eq(7)").after("<p class='name'><img src='../assets/img/stockup.png' class='name stock_img'/></p>");
					$(".stock #name:eq(7)").css("color", "red");
					$(".stock #name:eq(8)").css("color", "red");
				}

				if (stockud4 == "하락") {
					$(".stock #name:eq(10)").after("<p class='name'><img src='../assets/img/stockdown.png' class='name stock_img'/></p>");
					$(".stock #name:eq(10)").css("color", "blue");
					$(".stock #name:eq(11)").css("color", "blue");
				} else if (stockud4 == "상승") {
					$(".stock #name:eq(10)").after("<p class='name'><img src='../assets/img/stockup.png' class='name stock_img'/></p>");
					$(".stock #name:eq(10)").css("color", "red");
					$(".stock #name:eq(11)").css("color", "red");
				}

				if (stockud5 == "하락") {
					$(".stock #name:eq(13)").after("<p class='name'><img src='../assets/img/stockdown.png' class='name stock_img'/></p>");
					$(".stock #name:eq(13)").css("color", "blue");
					$(".stock #name:eq(14)").css("color", "blue");
				} else if (stockud5 == "상승") {
					$(".stock #name:eq(13)").after("<p class='name'><img src='../assets/img/stockup.png' class='name stock_img'/></p>");
					$(".stock #name:eq(13)").css("color", "red");
					$(".stock #name:eq(14)").css("color", "red");
				}
			};



			// 인기 종목 
			var popularup1 = "${popularup1}";
			var populardown1 = "${populardown1}";

			var popularup2 = "${popularup2}";
			var populardown2 = "${populardown2}";

			var popularup3 = "${popularup3}";
			var populardown3 = "${populardown3}";

			var popularup4 = "${popularup4}";
			var populardown4 = "${populardown4}";

			var popularup5 = "${popularup5}";
			var populardown5 = "${populardown5}";

			function popular() {
				if (popularup1 == '' || popularup1 == null) {
					$(".popular #name:eq(1)").after("<p class='name'><img src='../assets/img/stockdown.png' class='name stock_img'/></p>");
					$(".popular #name:eq(1)").css("color", "blue");
					$(".popular #name:eq(2)").css("color", "blue");
				} else if (populardown1 == '' || populardown1 == null) {
					$(".popular #name:eq(1)").after("<p class='name'><img src='../assets/img/stockup.png' class='name stock_img'/></p>");
					$(".popular #name:eq(1)").css("color", "red");
					$(".popular #name:eq(2)").css("color", "red");
				}

				if (popularup2 == '' || popularup2 == null) {
					$(".popular #name:eq(4)").after("<p class='name'><img src='../assets/img/stockdown.png' class='name stock_img'/></p>");
					$(".popular #name:eq(4)").css("color", "blue");
					$(".popular #name:eq(5)").css("color", "blue");
				} else if (populardown2 == '' || populardown2 == null) {
					$(".popular #name:eq(4)").after("<p class='name'><img src='../assets/img/stockup.png' class='name stock_img'/></p>");
					$(".popular #name:eq(4)").css("color", "red");
					$(".popular #name:eq(5)").css("color", "red");
				}

				if (popularup3 == '' || popularup3 == null) {
					$(".popular #name:eq(7)").after("<p class='name'><img src='../assets/img/stockdown.png' class='name stock_img'/></p>");
					$(".popular #name:eq(7)").css("color", "blue");
					$(".popular #name:eq(8)").css("color", "blue");
				} else if (populardown3 == '' || populardown3 == null) {
					$(".popular #name:eq(7)").after("<p class='name'><img src='../assets/img/stockup.png' class='name stock_img'/></p>");
					$(".popular #name:eq(7)").css("color", "red");
					$(".popular #name:eq(8)").css("color", "red");
				}

				if (popularup4 == '' || popularup4 == null) {
					$(".popular #name:eq(10)").after("<p class='name'><img src='../assets/img/stockdown.png' class='name stock_img'/></p>");
					$(".popular #name:eq(10)").css("color", "blue");
					$(".popular #name:eq(11)").css("color", "blue");
				} else if (populardown4 == '' || populardown4 == null) {
					$(".popular #name:eq(10)").after("<p class='name'><img src='../assets/img/stockup.png' class='name stock_img'/></p>");
					$(".popular #name:eq(10)").css("color", "red");
					$(".popular #name:eq(11)").css("color", "red");
				}

				if (popularup5 == '' || popularup5 == null) {
					$(".popular #name:eq(13)").after("<p class='name'><img src='../assets/img/stockdown.png' class='name stock_img'/></p>");
					$(".popular #name:eq(13)").css("color", "blue");
					$(".popular #name:eq(14)").css("color", "blue");
				} else if (populardown5 == '' || populardown5 == null) {
					$(".popular #name:eq(13)").after("<p class='name'><img src='../assets/img/stockup.png' class='name stock_img'/></p>");
					$(".popular #name:eq(13)").css("color", "red");
					$(".popular #name:eq(14)").css("color", "red");
				}
			};





			// 거래 상위
			var topud1 = "${topud1}";
			var topud2 = "${topud2}";
			var topud3 = "${topud3}";
			var topud4 = "${topud4}";
			var topud5 = "${topud5}";

			function top() {
				if (topud1 == "하락") {
				$(".top #name:eq(2)").after("<p class='name'><img src='../assets/img/stockdown.png' class='name stock_img'/></p>");
				$(".top #name:eq(1)").css("color", "blue");
				$(".top #name:eq(2)").css("color", "blue");
				$(".top #name:eq(3)").css("color", "blue");
			} else if (topud1 == "상승") {
				$(".top #name:eq(2)").after("<p class='name'><img src='../assets/img/stockup.png' class='name stock_img'/></p>");
				$(".top #name:eq(1)").css("color", "red");
				$(".top #name:eq(2)").css("color", "red");
				$(".top #name:eq(3)").css("color", "red");
			}

			if (topud2 == "하락") {
				$(".top #name:eq(6)").after("<p class='name'><img src='../assets/img/stockdown.png' class='name stock_img'/></p>");
				$(".top #name:eq(5)").css("color", "blue");
				$(".top #name:eq(6)").css("color", "blue");
				$(".top #name:eq(7)").css("color", "blue");
			} else if (topud2 == "상승") {
				$(".top #name:eq(6)").after("<p class='name'><img src='../assets/img/stockup.png' class='name stock_img'/></p>");
				$(".top #name:eq(5)").css("color", "red");
				$(".top #name:eq(6)").css("color", "red");
				$(".top #name:eq(7)").css("color", "red");
			}

			if (topud3 == "하락") {
				$(".top #name:eq(10)").after("<p class='name'><img src='../assets/img/stockdown.png' class='name stock_img'/></p>");
				$(".top #name:eq(9)").css("color", "blue");
				$(".top #name:eq(10)").css("color", "blue");
				$(".top #name:eq(11)").css("color", "blue");
			} else if (topud3 == "상승") {
				$(".top #name:eq(10)").after("<p class='name'><img src='../assets/img/stockup.png' class='name stock_img'/></p>");
				$(".top #name:eq(9)").css("color", "red");
				$(".top #name:eq(10)").css("color", "red");
				$(".top #name:eq(11)").css("color", "red");
			}

			if (topud4 == "하락") {
				$(".top #name:eq(14)").after("<p class='name'><img src='../assets/img/stockdown.png' class='name stock_img'/></p>");
				$(".top #name:eq(13)").css("color", "blue");
				$(".top #name:eq(14)").css("color", "blue");
				$(".top #name:eq(15)").css("color", "blue");
			} else if (topud4 == "상승") {
				$(".top #name:eq(14)").after("<p class='name'><img src='../assets/img/stockup.png' class='name stock_img'/></p>");
				$(".top #name:eq(13)").css("color", "red");
				$(".top #name:eq(14)").css("color", "red");
				$(".top #name:eq(15)").css("color", "red");
			}

			if (topud5 == "하락") {
				$(".top #name:eq(18)").after("<p class='name'><img src='../assets/img/stockdown.png' class='name stock_img'/></p>");
				$(".top #name:eq(17)").css("color", "blue");
				$(".top #name:eq(18)").css("color", "blue");
				$(".top #name:eq(19)").css("color", "blue");
			} else if (topud5 == "상승") {
				$(".top #name:eq(18)").after("<p class='name'><img src='../assets/img/stockup.png' class='name stock_img'/></p>");
				$(".top #name:eq(17)").css("color", "red");
				$(".top #name:eq(18)").css("color", "red");
				$(".top #name:eq(19)").css("color", "red");
			}

			

			};

			top();
			stock();
			popular();
			
			
		});
		 
		 
			/** 코스닥 코스피 데이터 */
			
			var kosdaq = ${kosdaq};
			var kospi = ${kospi};
			var kd_date = [];
			var kd_close = [];
			var kp_date = [];
			var kp_close = [];
			
			
			
			kosdaq = JSON.stringify(kosdaq);
			kospi = JSON.stringify(kospi);
			//console.log(kosdaq);
			//console.log(kospi);
			
			kosdaq = JSON.parse(kosdaq).kosdaq;
			kospi = JSON.parse(kospi).kospi;
			
			for (var i=0; i<"${kd_size.size()}"; i++) {
				kd_date.push(kosdaq[i].date);
				kd_close.push(kosdaq[i].kd_close);
			}
			
			for (var i=0; i<"${kp_size.size()}"; i++) {
				kp_date.push(kospi[i].date);
				kp_close.push(kospi[i].kp_close);
			}
			
			//console.log(kd_date);
			//console.log(kd_close);
			//console.log(kp_date);
			//console.log(kp_close);
			
			
			var ctx = document.getElementById('kospiChart').getContext('2d');
		      var kospiChart = new Chart(ctx, {
		         type: 'line',
		         data: {
		            labels: kp_date,         // 각각의 bar에 표시할 x축 텍스트들
		            datasets: [{                  
		               label: 'KOSPI',      // 범주
		               data: kp_close,         // 각 bar에 대한 y축 좌표 데이터 
		               backgroundColor: 'rgba(255, 99, 132, 0.2)',      // 각 bar의 배경 색상
		               borderColor: 'rgba(255, 99, 132, 1)',         // 각 bar의 테두리 색상
		               borderWidth: 1,                           // 각 bar의 테두리 굵기
		               lineTension: 0
		            }]
		         },
		         options: {
		        	 legend: {
		        	      display: false
		        	   },
		              scales: {
		                  xAxes: [{
		                     offset: false,
		                     gridLines: {
		                    	 display:false
		                     }
		                  }],
		                  yAxes: [{
		                	  gridLines: {
		                		  display:false
		                	  }
		                  }]
		              },
		             title: {
		                display: true,
		                position: 'top',
		                text: 'KOSPI',
		                fontSize: 24 
		             },
		             maintainAspectRatio: false,
		             aspectRatio: 1
		          }
		      });  
			

			
		      
		      
		      
		      $(function() {
		    	  
		    	  $("#kospi_btn").click(function() {
		    		 kospiChart.config.type='line';
		    		 kospiChart.config.options.title.text='KOSPI';
		    		 kospiChart.config.data.labels = kp_date;
		    		 kospiChart.config.data.datasets[0].data = kp_close;
		    		 kospiChart.update();
		    	  });
		    	  
		    	  $("#kosdaq_btn").click(function() {
		    		 kospiChart.config.type='line';
		    		 kospiChart.config.options.title.text='KOSDAQ';
		    		 kospiChart.config.data.labels = kd_date;
		    		 kospiChart.config.data.datasets[0].data = kd_close;
		    		 kospiChart.update(); 
					});
		    	
		      });
		      
		      
		      
			 
			
			

		</script>
		
		
	</script>

</body>
</html>