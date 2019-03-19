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
					        <p>동물 병원 찾기 앱에서 확인해 주세요!</p>
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
									<li>통계1</li>
									<li>통계2</li>
									<li>통계3</li>
								</ul>
							</div>
							<div class="statistics">
								<div class="left">
									<p>(통계)</p>
								</div>
								<div class="right">
									<p>(통계)</p>
								</div>
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
</body>
</html>