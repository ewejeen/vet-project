function getLocation() {
    	 
	if (navigator.geolocation) { // GPS를 지원하면
		navigator.geolocation.getCurrentPosition(
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
			 	// 마우스 휠과 모바일 터치를 이용한 지도 확대, 축소를 막는다
				map.setZoomable(false);
			    
				// 주소-좌표 변환 객체를 생성합니다
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

			    var res ='';
			    var resArr = new Array();
				    
			    function displayCenterInfo(result, status) {
			        if (status === daum.maps.services.Status.OK) {
						var adrs = document.getElementById('address');
						var sec2Region = document.getElementById('sec2Region');

			            for(var i = 0; i < result.length; i++) {
			                // 행정동의 region_type 값은 'H' 이므로
			                if (result[i].region_type === 'H') {
			                	res = result[i].address_name;
			                	resArr = res.split(' ');
			                					                	
			                	var province = resArr[0];
			                	var city = resArr[1];
			                	adrs.innerHTML = city;	// 시군구  에는 N개의 병원이 있습니다.
			                	sec2Region.innerHTML = province+" "+city;	// 시도+시군구  
			                	
							    console.log('시도:' + province);
							    console.log('시군구: ' + city);
			                	
							    function getNameAdrs(province, city){
							    	var name='';
							    	$.ajax({
							    		url : 'selectNameAndAdrs.do',
							    		type : 'POST',
							    		data : {
							    			'province' : province,
							    			'city' : city
							    		},
							    		success : function(data){
							    			var positions = JSON.stringify(data);
							    			
							    			for(var i=0;i<data.length;i++){
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
												}
												search(name);
							    			}
							    		}
							    	});	
							    }
								    
							    getNameAdrs(province, city);
							   
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

$(document).ready(
		getLocation()
);	// geoLocation 호출