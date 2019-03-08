<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>VET_PROJECT</title>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/vetproject/index.css" media="all" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<!-- CountUp -->
    <script src="<%=request.getContextPath() %>/js/jquery.waypoints.min.js"></script>
    <script src="<%=request.getContextPath() %>/js/jquery.counterup.min.js"></script>
    <!-- 카카오 지도 API -->
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=12e208177aac210838ca41d5bbf3716c&libraries=services,clusterer"></script>
    <script>
	    // 동물병원 총 숫자 countup 애니메이션
    	jQuery(document).ready(function($) {
	        $('.counter').counterUp({
	            delay: 10, // 숫자당 돌아가는 속도를 지연시켜주는 요소, 숫자가 높을 수록 돌아가는 속도가 느려짐.  
	            time: 1500 // 카운트업 해주는 애니메이션의 전체 지속시간 (1000==1초). 
	        });
	    });
	    
	    /* var pos = $('#address').text();
	    var posArr = pos.split(' ');
	    console.log(posArr); */
	    
    	
	</script>
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
					<p><span id="address">내 위치</span>에는 총 
						<%-- <c:choose>
							<c:when test="${cntByCity != null }"><span id="cntByCity">${cntByCity }</span></c:when>  
							<c:otherwise><span id="cntByCity">N</span></c:otherwise>
						</c:choose> --%>
						<%-- <span id="cntByCity"><%=(Integer)request.getAttribute("cntByCity") %></span> --%>
						<span id="cntByCity"><%=request.getAttribute("cntByCity") %></span>
					개의 동물병원이 있습니다.</p>
				</div>
			</div>
			
			<div class="section sec2" id="sec2">
				<div class="container">
					<p>원하시는 지역의 추천 동물병원을 살펴 보세요</p>
					<p>(통계)</p>
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
	
	
	<script>
		var win_h = $(window).height();
		
		var $section = $('.main .section');
		var $secSize = $section.length;
		var $item = $('.item li');
		
		window.onload=function(){
			$section.each(function(index){
				$(this).attr('data-index',win_h*index);
			});
			
			$section.on('mousewheel',function(e){
				var sectionPos = parseInt($(this).attr('data-index'));
				
				if(e.originalEvent.wheelDelta>=0){
					$('html,body').stop().animate({scrollTop:sectionPos-win_h});
					return false;
				} else if(e.originalEvent.wheelDelta<0){
					$('html,body').stop().animate({scrollTop:sectionPos+win_h});
					return false;
				}
			});
			
		}
		
		
		function fnMove(i){
			var offset=$('#sec'+i).offset();
			$('html, body').stop().animate({scrollTop:offset.top});
		}
		
		var $idx=$(this).index();
		
		$item.on('click',function(){
			var $idx=$(this).index();
			$item.eq($idx).addClass('on');
			$item.eq($idx).siblings().removeClass('on');
		});
		
		$(window).scroll(function(e){
			var windowTop=$(window).scrollTop();
			for(var i=0;i<$secSize;i++){
				var secTop=$section.eq(i).offset().top;
				if(windowTop>=secTop){
					//마우스 휠로 section이동 했을 때 오른쪽 버튼에 on 클래스 추가 / 삭제
					$item.eq(i).addClass('on');
					$item.eq(i).siblings().removeClass('on');
				}
			}
		});
		
	</script>
	
	
    <script>
    
    // 지오로케이션 + 카카오 지도
    function getLocation() {
    	 
		if (navigator.geolocation) { // GPS를 지원하면
			navigator.geolocation.watchPosition(
				function(position) {
					var myLat = position.coords.latitude;
					var myLng = position.coords.longitude;
					
					
					// 카카오 지도
				    var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
				    var options = { //지도를 생성할 때 필요한 기본 옵션
				    	center: new daum.maps.LatLng(myLat, myLng), //지도의 중심좌표
				    	level: 5 //지도의 레벨(확대, 축소 정도)
				    };
				
				    var map = new daum.maps.Map(container, options); //지도 생성 및 객체 리턴
					// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
				    var mapTypeControl = new daum.maps.MapTypeControl();

				    // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
				    // daum.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
				    map.addControl(mapTypeControl, daum.maps.ControlPosition.TOPRIGHT);

				    // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
				    var zoomControl = new daum.maps.ZoomControl();
				    map.addControl(zoomControl, daum.maps.ControlPosition.RIGHT);
				    
				    /*
				 	// 마커가 표시될 위치입니다 
				    var markerPosition  = new daum.maps.LatLng(myLat, myLng); 

				    // 마커를 생성합니다
				    var marker = new daum.maps.Marker({
				        position: markerPosition
				    });

				    // 마커가 지도 위에 표시되도록 설정합니다
				    marker.setMap(map);

				    // 아래 코드는 지도 위의 마커를 제거하는 코드입니다
				    // marker.setMap(null);    
				    */
				    
					///////////////////////////
				    var geocoder = new daum.maps.services.Geocoder();
				    
				    // 현재 지도 중심좌표로 주소를 검색해서 표시합니다
					searchAddrFromCoords(map.getCenter(), displayCenterInfo);
				    
				    function searchAddrFromCoords(coords, callback) {
				        // 좌표로 행정동 주소 정보를 요청합니다
				        geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);         
				    }

				    function searchDetailAddrFromCoords(coords, callback) {
				        // 좌표로 법정동 상세 주소 정보를 요청합니다
				        geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
				    }

				    function displayCenterInfo(result, status) {
				        if (status === daum.maps.services.Status.OK) {
							var adrs = document.getElementById('address');

				            for(var i = 0; i < result.length; i++) {
				                // 행정동의 region_type 값은 'H' 이므로
				                if (result[i].region_type === 'H') {
				                	var res = result[i].address_name;
				                	var resArr = res.split(' ');
				                	
				                	adrs.innerHTML = resArr[1];	// 시군구           	
								    console.log($('#address').text());
				                	
				                	

								    $.ajax({
								    	url : 'main.do',
								    	type : 'POST',
								    	data : {
								    		'city' : resArr[0]
								    	},
								    	success : function(data){
								    		console.log('데이터 보내기 성공');
								    	}
								    });
								    
								    
								    
				                    break;
				                }
				            }
				        }    
				    }
				    
				    // 카카오 지도 끝
				    
				    
				}, function(error) {
					console.error(error);
				}, {
					enableHighAccuracy : false,
					maximumAge : 0,
					timeout : Infinity
				}
			);
		} else {
			alert('GPS를 지원하지 않습니다');
		}
	}
    getLocation();
    // 지오로케이션 끝
    
	</script>
	
</body>
</html>