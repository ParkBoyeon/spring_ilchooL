<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/forecast.css" />
</head>
<body>
	<div class="carousel">
		<div id="carousel-example-generic" class="carousel slide" data-ride="carousel" data-interval="3000">
			<div class="carousel-inner" id="carouselImg">

				<!-- 캐러셀 항목 (1): 날씨 -->
				<div class="item active">

					<!-- 캐러셀 배경 : 날씨 -->
					<!-- 'PTY'하늘상태 - '0': 없음, '1': 비, '2': 비/눈, '3': 눈, '4': 소나기 -->
					<!-- 'SKY'하늘상태 - '1': 맑음, '3': 구름많음, '4': 흐림 -->
					<c:if test="${pty == 0}">
						<c:choose>
							<c:when test="${sky == 1}">
								<img src="${pageContext.request.contextPath}/assets/img/carouselbg/bg_sunny1.gif" />
							</c:when>

							<c:when test="${sky == 3}">
								<img src="${pageContext.request.contextPath}/assets/img/carouselbg/bg_cloud.gif" />
							</c:when>

							<c:when test="${sky == 4}">
								<img src="${pageContext.request.contextPath}/assets/img/carouselbg/bg_dark.gif" />
							</c:when>
						</c:choose>
					</c:if>
					<c:if test="${pty == 1}">
						<img src="${pageContext.request.contextPath}/assets/img/carouselbg/bg_rain1.gif" />
					</c:if>
					<c:if test="${pty == 2}">
						<img src="${pageContext.request.contextPath}/assets/img/carouselbg/bg_sleet.gif" />
					</c:if>
					<c:if test="${pty == 3}">
						<img src="${pageContext.request.contextPath}/assets/img/carouselbg/bg_snow.gif" />
					</c:if>
					<c:if test="${pty == 4}">
						<img src="${pageContext.request.contextPath}/assets/img/carouselbg/bg_rain1.gif" />
					</c:if>

					<!-- 캐러셀 내용 : 날씨 -->
					<div class="carousel-caption">
						<div class="container" id="weather_region">
							<div class="row forecast_region">
								<div class="col-md-12 text-center">
									<c:if test="${loc == null }">
										<p>서울시 강남구</p>
									</c:if>
									<c:if test="${loc != null }">
										<p>서울시 ${loc}</p>
									</c:if>
								</div>
							</div>
							<div></div>
							<div class="row row_forecast">
								<div class="col-sm-4 col-md-4 text-center" id="weatherImg">

									<c:if test="${pty == 0}">
										<c:choose>
											<c:when test="${sky == 1}">
												<img
													src="${pageContext.request.contextPath}/assets/img/icon_weather_Sunny.png" />
											</c:when>

											<c:when test="${sky == 3}">
												<img
													src="${pageContext.request.contextPath}/assets/img/icon_weather_Overcast.png" />
											</c:when>

											<c:when test="${sky == 4}">
												<img
													src="${pageContext.request.contextPath}/assets/img/icon_weather_Overcast.png" />
											</c:when>
										</c:choose>
									</c:if>
									<c:if test="${pty == 1}">
										<img
											src="${pageContext.request.contextPath}/assets/img/icon_weather_Hail_Heavy.png" />
									</c:if>
									<c:if test="${pty == 2}">
										<img
											src="${pageContext.request.contextPath}/assets/img/icon_weather_Sleet.png" />
									</c:if>
									<c:if test="${pty == 3}">
										<img
											src="${pageContext.request.contextPath}/assets/img/icon_weather_Snow.png" />
									</c:if>
									<c:if test="${pty == 4}">
										<img
											src="${pageContext.request.contextPath}/assets/img/icon_weather_Night_Rain.png" />
									</c:if>

								</div>
								<div class="col-sm-5 col-md-4 text-center" id="weatherBox">
									<p>${tmp}<span>&deg;&#67;</span>
									<p>
								</div>

								<div class="col-sm-3 col-md-4 text-center" id="weatherValue">
									<p>
										<span class="glyphicon glyphicon-arrow-up" aria-hidden="true"
											style="color: #F70D1A; margin: 5px 0 0 0; vertical-align: top;"></span>
										${tmx}&deg;&#67;
									</p>
									<p>
										<span class="glyphicon glyphicon-arrow-down"
											aria-hidden="true"
											style="color: #00AAEE; margin: 0 0 5px 5px; vertical-align: bottom;"></span>
										${tmn}&deg;&#67;
									</p>
								</div>

							</div>
							<div class="col-sm-12" id="weather_comment">

								<c:choose>
									<c:when test="${tmp <= -15}">
										<p>반도가 위험해</p>
									</c:when>

									<c:when test="${tmp > -15 && tmp <= -10}">
										<p>이불밖은 위험해</p>
									</c:when>

									<c:when test="${tmp > -10 && tmp <= -5}">
										<p>대장급 패딩 + 히트택</p>
									</c:when>

									<c:when test="${tmp > -5 && tmp <= 0}">
										<p>
											사람이 나갈 수 없는 날씨<br />(대장급 패딩 출격허가)
										</p>
									</c:when>

									<c:when test="${tmp > 0 && tmp <= 10}">
										<p>
											초겨울에서 겨울날씨<br />(패딩, 겨울코트 출격허가)
										</p>
									</c:when>

									<c:when test="${tmp > 10 && tmp <= 15}">
										<p>
											완연한 봄&amp;가을 날씨<br />(트렌치코트, 가죽자켓, 울자켓 출격허가)
										</p>
									</c:when>

									<c:when test="${tmp > 15 && tmp <= 20}">
										<p>
											애매한 봄&amp;가을 날씨<br />(긴팔티, 맨투맨, 니트 출격허가)
										</p>
									</c:when>

									<c:when test="${tmp > 20 && tmp <=25}">
										<p>
											살짝 더운 초여름 날씨<br />(긴팔티, 반팔티 혼용기간)
										</p>
									</c:when>

									<c:when test="${tmp > 25 && tmp <= 30}">
										<p>
											여름 날씨<br />(반팔, 반바지 출격허가)
										</p>
									</c:when>

									<c:when test="${tmp > 30 && tmp <= 35}">
										<p>사람이 나갈 수 있는 날씨</p>
									</c:when>

									<c:when test="${tmp > 35 && tmp <= 40}">
										<p>이게 바로 헬반도</p>
									</c:when>

									<c:otherwise>
										<p>이민</p>
									</c:otherwise>
								</c:choose>

							</div>
						</div>
					</div>
				</div>
				<!-- 캐러셀 항목 (2): 미세먼지 -->
				<div class="item">
				
					<!-- 캐러셀 배경 : 미세먼지 -->
					<c:choose>
						<c:when test="${pm10 <= 30}">
							<img src="${pageContext.request.contextPath}/assets/img/carouselbg/dust_bg_best.gif" />
						</c:when>

						<c:when test="${pm10 > 30 && pm10 <= 80}">
							<img src="${pageContext.request.contextPath}/assets/img/carouselbg/dust_bg_good1.gif" />
						</c:when>

						<c:when test="${pm10 > 80 && pm10 <= 150}">
							<img src="${pageContext.request.contextPath}/assets/img/carouselbg/dust_bg_bad.gif" />
						</c:when>

						<c:otherwise>
							<img src="${pageContext.request.contextPath}/assets/img/carouselbg/dust_bg_danger.gif" />
						</c:otherwise>
					</c:choose>

					<!-- 캐러셀 내용 : 미세먼지 -->
					<div class="carousel-caption">

						<div class="container" id="dust_region">
							<div class="row forecast_region">
								<div class="col-md-12 text-center">
									<c:if test="${loc == null }">
										<p>서울시 강남구</p>
									</c:if>
									<c:if test="${loc != null }">
										<p>서울시 ${loc}</p>
									</c:if>
								</div>
							</div>
							<div></div>

							<div class="row row_forecast" id="dustImg">

								<!-- 미세먼지 아이콘 얼굴 시작 -->
								<!-- 'pm10': ~30:좋음, ~80:보통, ~150:나쁨, ~:매우나쁨 -->
								<div class="col-sm-12 col-md-4 text-center">
									<c:choose>
										<c:when test="${pm10 <= 30}">
											<img
												src="${pageContext.request.contextPath}/assets/img/icon_face_love.png" />
										</c:when>

										<c:when test="${pm10 > 30 && pm10 <= 80}">
											<img
												src="${pageContext.request.contextPath}/assets/img/icon_face_smile.png" />
										</c:when>

										<c:when test="${pm10 > 80 && pm10 <= 150}">
											<img
												src="${pageContext.request.contextPath}/assets/img/icon_face_sad.png" />
										</c:when>

										<c:otherwise>
											<img
												src="${pageContext.request.contextPath}/assets/img/icon_face_bad.png" />
										</c:otherwise>
									</c:choose>
								</div>
								<!-- 미세먼지 아이콘 얼굴 종료 -->

								<!-- 미세먼지 값 시작 -->
								<div class="col-sm-12 col-md-4 text-center" id="dustBox">
									<p>
										<c:choose>
											<c:when test="${pm10 <= 30}">
												<span>좋음</span>
											</c:when>

											<c:when test="${pm10 > 30 && pm10 <= 80}">
												<span>보통</span>
											</c:when>

											<c:when test="${pm10 > 80 && pm10 <= 150}">
												<span>나쁨</span>
											</c:when>

											<c:otherwise>
												<span>위험</span>
											</c:otherwise>
										</c:choose>
									<p>
								</div>
								<!-- 미세먼지 값 종료 -->

								<!-- 미세먼지/초미세먼지 수치 시작 -->
								<!-- 'pm25': ~15:좋음, ~35:보통, ~75:나쁨, ~:매우나쁨 -->
								<div class="col-sm-12 col-md-4 text-center" id="dustValue">
									<p>
										미세먼지
										<c:choose>
											<c:when test="${pm10 <= 30}">
												<span class="dust" style="color: #00AAEE;">${pm10}</span>
											</c:when>

											<c:when test="${pm10 > 30 && pm10 <= 80}">
												<span class="dust" style="color: #A6D608;">${pm10}</span>
											</c:when>

											<c:when test="${pm10 > 80 && pm10 <= 150}">
												<span class="dust" style="color: #FF5F00;">${pm10}</span>
											</c:when>

											<c:otherwise>
												<span class="dust" style="color: #F70D1A;">${pm10}</span>
											</c:otherwise>
										</c:choose>
										㎍/㎥
									</p>
									<p>
										초미세먼지
										<c:choose>
											<c:when test="${pm25 <= 15}">
												<span class="dust" style="color: #00AAEE;">${pm25}</span>
											</c:when>

											<c:when test="${pm25 > 15 && pm25 <= 35}">
												<span class="dust" style="color: #A6D608;">${pm25}</span>
											</c:when>

											<c:when test="${pm25 > 35 && pm25 <= 75}">
												<span class="dust" style="color: #FF5F00;">${pm25}</span>
											</c:when>

											<c:otherwise>
												<span class="dust" style="color: #F70D1A;">${pm25}</span>
											</c:otherwise>
										</c:choose>
										㎍/㎥
									</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 좌우 이동 버튼 -->
			<a class="left carousel-control" href="#carousel-example-generic"
				data-slide="prev"> <span class="icon-prev"></span>
			</a> <a class="right carousel-control" href="#carousel-example-generic"
				data-slide="next"> <span class="icon-next"></span>
			</a>
		</div>
	</div>	<!-- // 캐러셀 영역 끝 -->
</body>
</html>