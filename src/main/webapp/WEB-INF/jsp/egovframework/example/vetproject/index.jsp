<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>VET_PROJECT</title>
	<link rel="shortcut icon" href="">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/vetproject/index.css" media="all" />
	<!-- jQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<!-- CountUp -->
    <script src="<%=request.getContextPath() %>/js/jquery.waypoints.min.js"></script>
    <script src="<%=request.getContextPath() %>/js/jquery.counterup.min.js"></script>
    <!-- 카카오 지도 API -->
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=12e208177aac210838ca41d5bbf3716c&libraries=services,clusterer"></script>
    <!-- D3.js (시각화 라이브러리) -->
    <script src="https://d3js.org/d3.v5.min.js"></script>
    
    <!-- chart.js (차트 라이브러리) -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.js"></script>
    <%-- <script src="<%=request.getContextPath() %>/js/Chart.bundle.min.js"></script>
    <script src="<%=request.getContextPath() %>/js/Chart.min.js"></script> --%>
    
    <!-- 동물병원 총 숫자 countup 애니메이션  -->
    <script src="<%=request.getContextPath() %>/js/vetTotalCountUp.js"></script>
    <!-- 지도 불러오고 마커 표시 -->
    <script src="<%=request.getContextPath() %>/js/vetGeoLocation.js"></script>
    <!-- 지역별 동물병원 개수 delay 줘서 불러옴 (지도보다 늦게 가져와야 하는 값이므로) -->
    <script src="<%=request.getContextPath() %>/js/vetCountByCity.js"></script>

	


</head>
<body>
	<div id="wrap">
		<jsp:include page="header.jsp" />
		
		<div class="main">
			<div class="item">
				<ul>
					<li class="on" onclick="fnMove('0')"></li>
					<li onclick="fnMove('1')"><a href=""></a></li>
					<li onclick="fnMove('2')"></li>
					<li onclick="fnMove('3')"></li>
				</ul>
			</div>
			
			<div class="section sec0" id="sec0">
				<div class="container">
					<p class="l1">전국</p>
					<p class="l2"> <span class="counter"><fmt:formatNumber type="number" maxFractionDigits="3" value="${totalVet }" /></span>개의</p>
					<p class="l3">동물병원 정보를 손쉽게 얻어 보세요</p>
				</div>
			</div>
			
			<div class="section sec1" id="sec1">
				<div class="container">
					<p class="l1">내 주변의 동물병원을 찾아 보세요</p>
					<div id="map"></div>
					<p><span id="address">내 위치</span>에는 총 <span id="cntByCity">N</span>
					개의 동물병원이 있습니다.</p>
				</div>
			</div>
			
			<div class="section sec2" id="sec2">
				<div class="container">
					<div class="nofloat">
						
						<div id="myModal" class="modal">
					      <div class="modal-content">
					      	<div class="close">
					        	<span id="closeModal">&times;</span>
					        </div>                                                               
					        <p>FindVet 앱에서 확인해 주세요!</p>
					      </div>
					    </div>					
					
						<div class="top">
							<div class="l1">
								<p>원하시는 지역의 추천 동물병원을 살펴 보세요!</p>
								<span id="openModal">다른 지역 보기</span>
							</div>
							<div class="l2">
								<span>현재 지역: </span>
								<span id="sec2Region">현재 지역</span>
							</div>
						</div>
						<div class="con">
							<div class="gnb">
								<ul>
									<li id="st1">평점이 높은 병원</li>
									<li id="st2">재방문율이 높은 병원</li>
								</ul>
							</div>
							<div class="stats">
							<!-- 통계1 -->
							<div class="statistics st1">
								<div class="left">
									<ul>
										<li class="rateHeader">
											<span class="rateNH">순위</span>
											<span class="rateNameH">이름</span>
											<span class="rateRateH">평점(후기 수)</span>
										</li>
										<li>
											<span class="rateN">1</span>
											<span class="rateName" id="rateName1">OO병원</span>
											<span class="rateRate" id="rateRate1">0.0</span>
										</li>
										<li>
											<span class="rateN">2</span>
											<span class="rateName" id="rateName2"></span>
											<span class="rateRate" id="rateRate2"></span>
										</li>
										<li>
											<span class="rateN">3</span>
											<span class="rateName" id="rateName3"></span>
											<span class="rateRate" id="rateRate3"></span>
										</li>
										<li>
											<span class="rateN">4</span>
											<span class="rateName" id="rateName4"></span>
											<span class="rateRate" id="rateRate4"></span>
										</li>
										<li>
											<span class="rateN">5</span>
											<span class="rateName" id="rateName5"></span>
											<span class="rateRate" id="rateRate5"></span>
										</li>
									</ul>
								</div>
								<div class="right">
									<div class="chart">
										<canvas id="myChart"></canvas>
									</div>
									<div class="mapDiv">
										<div class="mapDetail">
											<ul>
												<li><span class="mapName">OO병원</span></li>
												<li>주소: <span class="mapAdrs">(정보 없음)</span></li>
												<li>전화번호: <span class="mapPhone">(정보 없음)</span> </li>
											</ul>
										</div>
										<div id="map1">
											지도
										</div>
									</div>
								</div>
							</div>
							<!-- 통계1 끝 -->
							
							<!-- 통계2 -->
							<div class="statistics st2">
								<div class="left">
									<ul>
										<li class="retHeader">
											<span class="retNH">순위</span>
											<span class="retNameH">이름</span>
											<span class="retRetH">재방문율</span>
										</li>
										<li>
											<span class="retN">1</span>
											<span class="retName" id="retName1">OO병원</span>
											<span class="retRet" id="retRet1">0%</span>
										</li>
										<li>
											<span class="retN">2</span>
											<span class="retName" id="retName2"></span>
											<span class="retRet" id="retRet2"></span>
										</li>
										<li>
											<span class="retN">3</span>
											<span class="retName" id="retName3"></span>
											<span class="retRet" id="retRet3"></span>
										</li>
										<li>
											<span class="retN">4</span>
											<span class="retName" id="retName4"></span>
											<span class="retRet" id="retRet4"></span>
										</li>
										<li>
											<span class="retN">5</span>
											<span class="retName" id="retName5"></span>
											<span class="retRet" id="retRet5"></span>
										</li>
									</ul>
								</div>
								<div class="right">
									<div class="chartLine">
										<div class="l1">
											<div class="chart1" >
												<canvas id="myChart1"></canvas>
												<div class="donutInner">
												    <h5 id="di1">50.00%</h5>
												</div>
											</div>
											<div class="chart2" >
												<canvas id="myChart2" ></canvas>
												<div class="donutInner">
												    <h5 id="di2"></h5>
												</div>
											</div>
										</div>
										<div class="l2">
											<div class="chart3" >
												<canvas id="myChart3"></canvas>
												<div class="donutInner2">
												    <h5 id="di3"></h5>
												</div>
											</div>
											<div class="chart4" >
												<canvas id="myChart4"></canvas>
												<div class="donutInner2">
												    <h5 id="di4"></h5>
												</div>
											</div>
											<div class="chart5" >
												<canvas id="myChart5"></canvas>
												<div class="donutInner2">
												    <h5 id="di5"></h5>
												</div>
											</div>
										</div>
									</div>
									<div class="mapDiv2">
										<div class="mapDetail2">
											<ul>
												<li><span class="mapName">OO병원</span></li>
												<li>주소: <span class="mapAdrs">(정보 없음)</span></li>
												<li>전화번호: <span class="mapPhone">(정보 없음)</span> </li>
											</ul>
										</div>
										<div id="map2">
											지도
										</div>
									</div>
								</div>
							</div>
							<!-- 통계2 끝 -->
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="section sec3" id="sec3">
				<div class="container">
					<p class="l1">더 많은 정보를 보고 싶으신가요?</p>
					<p>동물 병원 찾기 앱을 설치하세요!</p>
					<div class="imgs">
						<img src="<%=request.getContextPath() %>/images/vetproject/google_play.png" alt="안드로이드" id="getandroid" onclick="window.open('https://en.wikipedia.org/wiki/Veterinary_physician')"/>
						<img src="<%=request.getContextPath() %>/images/vetproject/qr_code.png" alt="QR코드" id="getqr"/>					
					</div>
				</div>
			</div>		
		</div>
		
		<div class="footer">
		
		</div>
	</div>
	
	<!-- 페이지 이동 휠 이벤트, 클릭 이벤트 -->
    <script src="<%=request.getContextPath() %>/js/openModal.js"></script>
    <!-- 앱 이용 유도 Modal 띄우기 -->
    <script src="<%=request.getContextPath() %>/js/wheelEvent.js"></script>
    
    <!-- 평점, 후기 통계 -->
    <script src="<%=request.getContextPath() %>/js/statisticsRate.js"></script>
    <!-- 재방문율 통계 -->
    <script src="<%=request.getContextPath() %>/js/statisticsRetention.js"></script>
    <script>
    
    setTimeout(function() {
	    statisticsRate();	// 현재 지역 정보 받아오기 위함
	    statisticsRetention();	// 현재 지역 정보 받아오기 위함
    }, 1000);
      
    </script>
    
    <script src="<%=request.getContextPath() %>/js/clickEvent.js"></script>
    
</body>
</html>