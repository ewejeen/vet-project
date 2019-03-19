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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.bundle.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.js"></script>
    
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
									<li>평점이 높은 병원</li>
									<li>재방문율이 높은 병원</li>
								</ul>
							</div>
							<div class="statistics">
								<div class="left">
									<p>(통계)</p>
								</div>
								<div class="right">
									<p>(통계)</p>
									<canvas id="myChart" style="z-index:10; width:500px; height:500px; border:1px solid;"></canvas>
									<button id="randomizeData">Randomize Data</button>
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
    
    <script>
    function statisticsRate(province, city){
    	var locArr = $('#sec2Region').text().split(' ');
    	console.log('평점 도: '+locArr[0]);
    	console.log('평점 시: '+locArr[1]);
    	
    	$.ajax({
    		url : 'statisticsRate.do',
    		type : 'POST',
    		data : {
    			'province' : locArr[0],
    			'city' : locArr[1]
    		},
    		success : function(data){
    			var result = JSON.stringify(data);
    			console.log("rate호출"+result);
    			
    			
    			
    			
    			// 주소 나타내기 부분
    			/* for(var i=0;i<data.length;i++){
    				name = data[i].hptName;

					// 주소로 좌표를 검색합니다
					function search(name){
						geocoder.addressSearch(data[i].address, function(result, status) {
							
						    // 정상적으로 검색이 완료됐으면 
						     if (status === daum.maps.services.Status.OK) {
						        var coords = new daum.maps.LatLng(result[0].y, result[0].x);
				    			
						    	// 마커를 클릭했을 때 마커 위에 표시할 인포윈도우를 생성합니다
						        var iwContent = '<div style="padding:5px;font-size:13px">'+name+'</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
						            iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

						        // 인포윈도우를 생성합니다
						        var infowindow = new daum.maps.InfoWindow({
						            content : iwContent,
						            removable : iwRemoveable
						        });

						        // 결과값으로 받은 위치를 마커로 표시합니다
						        var marker = new daum.maps.Marker({
						            map: map,
						            position: coords,	// 마커를 표시할 위치
						            clickable: true  	// 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
						        });
						        
						        // 마커에 클릭이벤트를 등록합니다
						        daum.maps.event.addListener(marker, 'click', function() {
						              // 마커 위에 인포윈도우를 표시합니다
						              infowindow.open(map, marker);  
						        });

						    } 
						});   														
					} // search 끝
					search(name);
    			} */
    		}
    	});
    } //statisticsRate 끝
    
    setTimeout(function() {
	    statisticsRate();	// 현재 지역 정보 받아오기 위함
    }, 1000);
    </script>
    
    
    <script>
    window.chartColors = {
    		red: 'rgb(255, 99, 132)',
    		orange: 'rgb(255, 159, 64)',
    		yellow: 'rgb(255, 205, 86)',
    		green: 'rgb(75, 192, 192)',
    		blue: 'rgb(54, 162, 235)',
    		purple: 'rgb(153, 102, 255)',
    		grey: 'rgb(201, 203, 207)'
    	};

    	(function(global) {
    		var MONTHS = [
    			'January',
    			'February',
    			'March',
    			'April',
    			'May',
    			'June',
    			'July',
    			'August',
    			'September',
    			'October',
    			'November',
    			'December'
    		];

    		var COLORS = [
    			'#4dc9f6',
    			'#f67019',
    			'#f53794',
    			'#537bc4',
    			'#acc236',
    			'#166a8f',
    			'#00a950',
    			'#58595b',
    			'#8549ba'
    		];

    		var Samples = global.Samples || (global.Samples = {});
    		var Color = global.Color;

    		Samples.utils = {
    			// Adapted from http://indiegamr.com/generate-repeatable-random-numbers-in-js/
    			srand: function(seed) {
    				this._seed = seed;
    			},

    			rand: function(min, max) {
    				var seed = this._seed;
    				min = min === undefined ? 0 : min;
    				max = max === undefined ? 1 : max;
    				this._seed = (seed * 9301 + 49297) % 233280;
    				return min + (this._seed / 233280) * (max - min);
    			},

    			numbers: function(config) {
    				var cfg = config || {};
    				var min = cfg.min || 0;
    				var max = cfg.max || 1;
    				var from = cfg.from || [];
    				var count = cfg.count || 8;
    				var decimals = cfg.decimals || 8;
    				var continuity = cfg.continuity || 1;
    				var dfactor = Math.pow(10, decimals) || 0;
    				var data = [];
    				var i, value;

    				for (i = 0; i < count; ++i) {
    					value = (from[i] || 0) + this.rand(min, max);
    					if (this.rand() <= continuity) {
    						data.push(Math.round(dfactor * value) / dfactor);
    					} else {
    						data.push(null);
    					}
    				}

    				return data;
    			},

    			labels: function(config) {
    				var cfg = config || {};
    				var min = cfg.min || 0;
    				var max = cfg.max || 100;
    				var count = cfg.count || 8;
    				var step = (max - min) / count;
    				var decimals = cfg.decimals || 8;
    				var dfactor = Math.pow(10, decimals) || 0;
    				var prefix = cfg.prefix || '';
    				var values = [];
    				var i;

    				for (i = min; i < max; i += step) {
    					values.push(prefix + Math.round(dfactor * i) / dfactor);
    				}

    				return values;
    			},

    			months: function(config) {
    				var cfg = config || {};
    				var count = cfg.count || 12;
    				var section = cfg.section;
    				var values = [];
    				var i, value;

    				for (i = 0; i < count; ++i) {
    					value = MONTHS[Math.ceil(i) % 12];
    					values.push(value.substring(0, section));
    				}

    				return values;
    			},

    			color: function(index) {
    				return COLORS[index % COLORS.length];
    			},

    			transparentize: function(color, opacity) {
    				var alpha = opacity === undefined ? 0.5 : 1 - opacity;
    				return Color(color).alpha(alpha).rgbString();
    			}
    		};

    		// DEPRECATED
    		window.randomScalingFactor = function() {
    			return Math.round(Samples.utils.rand(-100, 100));
    		};

    		// INITIALIZATION

    		Samples.utils.srand(Date.now());

    		// Google Analytics
    		/* eslint-disable */
    		if (document.location.hostname.match(/^(www\.)?chartjs\.org$/)) {
    			(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
    			(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
    			m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
    			})(window,document,'script','//www.google-analytics.com/analytics.js','ga');
    			ga('create', 'UA-28909194-3', 'auto');
    			ga('send', 'pageview');
    		}
    		/* eslint-enable */

    	}(this));
    
		var barChartData = {
				labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
				datasets: [{
					label: 'Dataset 1',
					backgroundColor: [
						window.chartColors.red,
						window.chartColors.orange,
						window.chartColors.yellow,
						window.chartColors.green,
						window.chartColors.blue,
						window.chartColors.purple,
						window.chartColors.red
					],
					yAxisID: 'y-axis-1',
					data: [
						randomScalingFactor(),
						randomScalingFactor(),
						randomScalingFactor(),
						randomScalingFactor(),
						randomScalingFactor(),
						randomScalingFactor(),
						randomScalingFactor()
					]
				}, {
					label: 'Dataset 2',
					backgroundColor: window.chartColors.grey,
					yAxisID: 'y-axis-2',
					data: [
						randomScalingFactor(),
						randomScalingFactor(),
						randomScalingFactor(),
						randomScalingFactor(),
						randomScalingFactor(),
						randomScalingFactor(),
						randomScalingFactor()
					]
				}]

			};
		
			var ctx = document.getElementById('myChart').getContext('2d');
			window.myBar = new Chart(ctx, {
				type: 'bar',
				data: barChartData,
				options: {
					responsive: true,
					title: {
						display: true,
						text: 'Chart.js Bar Chart - Multi Axis'
					},
					tooltips: {
						mode: 'index',
						intersect: true
					},
					scales: {
						yAxes: [{
							type: 'linear', // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
							display: true,
							position: 'left',
							id: 'y-axis-1',
						}, {
							type: 'linear', // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
							display: true,
							position: 'right',
							id: 'y-axis-2',
							gridLines: {
								drawOnChartArea: false
							}
						}],
					}
				}
			});

			document.getElementById('randomizeData').addEventListener('click', function() {
				barChartData.datasets.forEach(function(dataset) {
					dataset.data = dataset.data.map(function() {
						return randomScalingFactor();
					});
				});
				window.myBar.update();
			});
	</script>
</body>
</html>