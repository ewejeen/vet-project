// 재방문 통계
function statisticsRetention(){
	var locArr = $('#sec2Region').text().split(' ');
	//var locArr = '경기도 성남시'.split(' ');
	var province = locArr[0];
	var city = locArr[1];
	
	$.ajax({
		url : 'statisticsRetention.do',
		type : 'POST',
		data : {
			'province' : province,
			'city' : city
		},
		success : function(result){
			// 각 텍스트에 정보 넣어 주기
			for(var i=1;i<=5;i++){
    			$('#retName'+i).text(result[i-1].hptName);
    			$('#retRet'+i).text(result[i-1].retention.toFixed(2)+'%');	//실수.toFixed(자릿수): 소수 부분을 자릿수까지 남겨두고 반올림				
			}
			
			// 차트 만들기
			function renderChart1() {
	    	    var ctx = document.getElementById('myChart1').getContext('2d');
	  	        $('#di1').text(result[0].retention.toFixed(2)+'%');
	    	    var myDoughnutChart = new Chart(ctx, {
	    	        type: 'doughnut',
	    	        data: {
	    	        	labels: ['재방문', '첫 방문'] ,
	    	            datasets: [{
	    	                label: '재방문율',
	    	                data: [result[0].retention.toFixed(2), (100-result[0].retention.toFixed(2))],
	    	                backgroundColor: [	// borderColor, borderWidth
	    						'rgb(255, 99, 132)',	//red
	    						'rgb(255, 159, 64)',	//orange
	    						'rgb(54, 162, 235)',	//blue
	    						'rgb(255, 205, 86)',	//yellow
	    						'rgb(75, 192, 192)' 	//green
	    						//'rgb(153, 102, 255)'	//purple
	    					],
	    	            }],
	    	        },
	    	        options: {
	    	        	title: {
	    					display: true,
	    					text: result[0].hptName
	    				},
	    				rotation: 1 * Math.PI,
	                    circumference: 1 * Math.PI
	    	        }
	    	    });
	    	    
	    	}
			function renderChart2() {
	    	    var ctx = document.getElementById('myChart2').getContext('2d');
	    	    $('#di2').text(result[1].retention.toFixed(2)+'%');
	    	    var myDoughnutChart = new Chart(ctx, {
	    	        type: 'doughnut',
	    	        data: {
	    	        	labels: ['재방문', '첫 방문'] ,
	    	            datasets: [{
	    	                label: '재방문율',
	    	                data: [result[1].retention.toFixed(2), (100-result[1].retention.toFixed(2))],
	    	                backgroundColor: [	// borderColor, borderWidth
	    						'rgb(255, 99, 132)',	//red
	    						'rgb(255, 159, 64)',	//orange
	    						'rgb(54, 162, 235)',	//blue
	    						'rgb(255, 205, 86)',	//yellow
	    						'rgb(75, 192, 192)' 	//green
	    						//'rgb(153, 102, 255)'	//purple
	    					],
	    	            }],
	    	        },
	    	        options: {
	    	        	title: {
	    					display: true,
	    					text: result[1].hptName
	    				},
	    				rotation: 1 * Math.PI,
	                    circumference: 1 * Math.PI
	    	        }
	    	    });
	    	    
	    	}
			function renderChart3() {
	    	    var ctx = document.getElementById('myChart3').getContext('2d');
	    	    $('#di3').text(result[2].retention.toFixed(2)+'%');
	    	    var myDoughnutChart = new Chart(ctx, {
	    	        type: 'doughnut',
	    	        data: {
	    	        	labels: ['재방문', '첫 방문'] ,
	    	            datasets: [{
	    	                label: '재방문율',
	    	                data: [result[2].retention.toFixed(2), (100-result[2].retention.toFixed(2))],
	    	                backgroundColor: [	// borderColor, borderWidth
	    						'rgb(255, 99, 132)',	//red
	    						'rgb(255, 159, 64)',	//orange
	    						'rgb(54, 162, 235)',	//blue
	    						'rgb(255, 205, 86)',	//yellow
	    						'rgb(75, 192, 192)' 	//green
	    						//'rgb(153, 102, 255)'	//purple
	    					],
	    	            }],
	    	        },
	    	        options: {
	    	        	title: {
	    					display: true,
	    					text: result[2].hptName
	    				},
	    				rotation: 1 * Math.PI,
	                    circumference: 1 * Math.PI
	    	        }
	    	    });
	    	    
	    	}
			function renderChart4() {
	    	    var ctx = document.getElementById('myChart4').getContext('2d');
	    	    $('#di4').text(result[3].retention.toFixed(2)+'%');
	    	    var myDoughnutChart = new Chart(ctx, {
	    	        type: 'doughnut',
	    	        data: {
	    	        	labels: ['재방문', '첫 방문'] ,
	    	            datasets: [{
	    	                label: '재방문율',
	    	                data: [result[3].retention.toFixed(2), (100-result[3].retention.toFixed(2))],
	    	                backgroundColor: [	// borderColor, borderWidth
	    						'rgb(255, 99, 132)',	//red
	    						'rgb(255, 159, 64)',	//orange
	    						'rgb(255, 205, 86)',	//yellow
	    						'rgb(54, 162, 235)',	//blue
	    						'rgb(75, 192, 192)' 	//green
	    						//'rgb(153, 102, 255)'	//purple
	    					],
	    	            }],
	    	        },
	    	        options: {
	    	        	title: {
	    					display: true,
	    					text: result[3].hptName
	    				},
	    				rotation: 1 * Math.PI,
	                    circumference: 1 * Math.PI
	    	        }
	    	    });
	    	    
	    	}
			function renderChart5() {
	    	    var ctx = document.getElementById('myChart5').getContext('2d');
	    	    $('#di5').text(result[4].retention.toFixed(2)+'%');
	    	    var myDoughnutChart = new Chart(ctx, {
	    	        type: 'doughnut',
	    	        data: {
	    	        	labels: ['재방문', '첫 방문'] ,
	    	            datasets: [{
	    	                label: '재방문율',
	    	                data: [result[4].retention.toFixed(2), (100-result[4].retention.toFixed(2))],
	    	                backgroundColor: [	// borderColor, borderWidth
	    						'rgb(255, 99, 132)',	//red
	    						'rgb(255, 159, 64)',	//orange
	    						'rgb(255, 205, 86)',	//yellow
	    						'rgb(54, 162, 235)',	//blue
	    						'rgb(75, 192, 192)' 	//green
	    						//'rgb(153, 102, 255)'	//purple
	    					],
	    	            }],
	    	        },
	    	        options:{
	    	        	title: {
	    					display: true,
	    					text: result[4].hptName
	    				},
	    				rotation: 1 * Math.PI,
	                    circumference: 1 * Math.PI
	    	        }
	    	    });
	    	    
	    	}

	    	$(document).ready(renderChart1());
	    	$(document).ready(renderChart2());
	    	$(document).ready(renderChart3());
	    	$(document).ready(renderChart4());
	    	$(document).ready(renderChart5());
			
	    	function smallMap2(){
	    		
	    		/* 반복문 처리 될 것 같은데....*/
    	    	$('#retName1').click(function(){
    	    		var name = result[0].hptName;
    	    		var phone = result[0].hptPhone;    	    		
    	    		var adrs = result[0].address;
    	    		
    	    		$('.mapName').text(name);
    	    		$('.mapAdrs').text(adrs);
    	    		$('.mapPhone').text(phone);
    	    		
    	    		// 카카오 지도
    	    		var mapContainer = document.getElementById('map2'), // 지도를 표시할 div 
	    	    	    mapOption = {
	    	    	        center: new daum.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
	    	    	        level: 3 // 지도의 확대 레벨
	    	    	    };  
	
	    	    	// 지도를 생성합니다    
	    	    	var map = new daum.maps.Map(mapContainer, mapOption); 
	
	    	    	// 주소-좌표 변환 객체를 생성합니다
	    	    	var geocoder = new daum.maps.services.Geocoder();
    	    	
	    	    	// 주소로 좌표를 검색합니다
	    	    	geocoder.addressSearch(adrs, function(result, status) {
	
	    	    	    // 정상적으로 검색이 완료됐으면 
	    	    	     if (status === daum.maps.services.Status.OK) {
	
	    	    	        var coords = new daum.maps.LatLng(result[0].y, result[0].x);
	
	    	    	        // 결과값으로 받은 위치를 마커로 표시합니다
	    	    	        var marker = new daum.maps.Marker({
	    	    	            map: map,
	    	    	            position: coords
	    	    	        });
	
	    	    	        // 인포윈도우로 장소에 대한 설명을 표시합니다
	    	    	        var infowindow = new daum.maps.InfoWindow({
	    	    	            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+name+'</div>'
	    	    	        });
	    	    	        infowindow.open(map, marker);
	
	    	    	        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	    	    	        map.setCenter(coords);
	    	    	    } 
	    	    	});// 주소로 좌표 검색 끝  
    	    		
    	    	});	//클릭 펑션 끝
    	    	$('#retName2').click(function(){
    	    		var name = result[1].hptName;
    	    		var phone = result[1].hptPhone;    	    		
    	    		var adrs = result[1].address;
    	    		
    	    		$('.mapName').text(name);
    	    		$('.mapAdrs').text(adrs);
    	    		$('.mapPhone').text(phone);
    	    		
    	    		// 카카오 지도
    	    		var mapContainer = document.getElementById('map2'), // 지도를 표시할 div 
	    	    	    mapOption = {
	    	    	        center: new daum.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
	    	    	        level: 3 // 지도의 확대 레벨
	    	    	    };  
	
	    	    	// 지도를 생성합니다    
	    	    	var map = new daum.maps.Map(mapContainer, mapOption); 
	
	    	    	// 주소-좌표 변환 객체를 생성합니다
	    	    	var geocoder = new daum.maps.services.Geocoder();
    	    	
	    	    	// 주소로 좌표를 검색합니다
	    	    	geocoder.addressSearch(adrs, function(result, status) {
	
	    	    	    // 정상적으로 검색이 완료됐으면 
	    	    	     if (status === daum.maps.services.Status.OK) {
	
	    	    	        var coords = new daum.maps.LatLng(result[0].y, result[0].x);
	
	    	    	        // 결과값으로 받은 위치를 마커로 표시합니다
	    	    	        var marker = new daum.maps.Marker({
	    	    	            map: map,
	    	    	            position: coords
	    	    	        });
	
	    	    	        // 인포윈도우로 장소에 대한 설명을 표시합니다
	    	    	        var infowindow = new daum.maps.InfoWindow({
	    	    	            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+name+'</div>'
	    	    	        });
	    	    	        infowindow.open(map, marker);
	
	    	    	        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	    	    	        map.setCenter(coords);
	    	    	    } 
	    	    	});// 주소로 좌표 검색 끝  
    	    		
    	    	});	//클릭 펑션 끝
    	    	$('#retName3').click(function(){
    	    		var name = result[2].hptName;
    	    		var phone = result[2].hptPhone;    	    		
    	    		var adrs = result[2].address;
    	    		
    	    		$('.mapName').text(name);
    	    		$('.mapAdrs').text(adrs);
    	    		$('.mapPhone').text(phone);
    	    		
    	    		// 카카오 지도
    	    		var mapContainer = document.getElementById('map2'), // 지도를 표시할 div 
	    	    	    mapOption = {
	    	    	        center: new daum.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
	    	    	        level: 3 // 지도의 확대 레벨
	    	    	    };  
	
	    	    	// 지도를 생성합니다    
	    	    	var map = new daum.maps.Map(mapContainer, mapOption); 
	
	    	    	// 주소-좌표 변환 객체를 생성합니다
	    	    	var geocoder = new daum.maps.services.Geocoder();
    	    	
	    	    	// 주소로 좌표를 검색합니다
	    	    	geocoder.addressSearch(adrs, function(result, status) {
	
	    	    	    // 정상적으로 검색이 완료됐으면 
	    	    	     if (status === daum.maps.services.Status.OK) {
	
	    	    	        var coords = new daum.maps.LatLng(result[0].y, result[0].x);
	
	    	    	        // 결과값으로 받은 위치를 마커로 표시합니다
	    	    	        var marker = new daum.maps.Marker({
	    	    	            map: map,
	    	    	            position: coords
	    	    	        });
	
	    	    	        // 인포윈도우로 장소에 대한 설명을 표시합니다
	    	    	        var infowindow = new daum.maps.InfoWindow({
	    	    	            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+name+'</div>'
	    	    	        });
	    	    	        infowindow.open(map, marker);
	
	    	    	        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	    	    	        map.setCenter(coords);
	    	    	    } 
	    	    	});// 주소로 좌표 검색 끝  
    	    		
    	    	});	//클릭 펑션 끝
    	    	$('#retName4').click(function(){
    	    		var name = result[3].hptName;
    	    		var phone = result[3].hptPhone;    	    		
    	    		var adrs = result[3].address;
    	    		
    	    		$('.mapName').text(name);
    	    		$('.mapAdrs').text(adrs);
    	    		$('.mapPhone').text(phone);
    	    		
    	    		// 카카오 지도
    	    		var mapContainer = document.getElementById('map2'), // 지도를 표시할 div 
	    	    	    mapOption = {
	    	    	        center: new daum.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
	    	    	        level: 3 // 지도의 확대 레벨
	    	    	    };  
	
	    	    	// 지도를 생성합니다    
	    	    	var map = new daum.maps.Map(mapContainer, mapOption); 
	
	    	    	// 주소-좌표 변환 객체를 생성합니다
	    	    	var geocoder = new daum.maps.services.Geocoder();
    	    	
	    	    	// 주소로 좌표를 검색합니다
	    	    	geocoder.addressSearch(adrs, function(result, status) {
	
	    	    	    // 정상적으로 검색이 완료됐으면 
	    	    	     if (status === daum.maps.services.Status.OK) {
	
	    	    	        var coords = new daum.maps.LatLng(result[0].y, result[0].x);
	
	    	    	        // 결과값으로 받은 위치를 마커로 표시합니다
	    	    	        var marker = new daum.maps.Marker({
	    	    	            map: map,
	    	    	            position: coords
	    	    	        });
	
	    	    	        // 인포윈도우로 장소에 대한 설명을 표시합니다
	    	    	        var infowindow = new daum.maps.InfoWindow({
	    	    	            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+name+'</div>'
	    	    	        });
	    	    	        infowindow.open(map, marker);
	
	    	    	        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	    	    	        map.setCenter(coords);
	    	    	    } 
	    	    	});// 주소로 좌표 검색 끝  
    	    		
    	    	});	//클릭 펑션 끝
    	    	$('#retName5').click(function(){
    	    		var name = result[4].hptName;
    	    		var phone = result[4].hptPhone;    	    		
    	    		var adrs = result[4].address;
    	    		
    	    		if(name!='' && name!=null)
    	    		$('.mapName').text(name);
    	    		if(phone!='' && phone!=null)
    	    		$('.mapPhone').text(phone);
    	    		if(adrs!='' && adrs!=null)
    	    		$('.mapAdrs').text(adrs);
    	    		
    	    		// 카카오 지도
    	    		var mapContainer = document.getElementById('map2'), // 지도를 표시할 div 
	    	    	    mapOption = {
	    	    	        center: new daum.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
	    	    	        level: 3 // 지도의 확대 레벨
	    	    	    };  
	
	    	    	// 지도를 생성합니다    
	    	    	var map = new daum.maps.Map(mapContainer, mapOption); 
	
	    	    	// 주소-좌표 변환 객체를 생성합니다
	    	    	var geocoder = new daum.maps.services.Geocoder();
    	    	
	    	    	// 주소로 좌표를 검색합니다
	    	    	geocoder.addressSearch(adrs, function(result, status) {
	
	    	    	    // 정상적으로 검색이 완료됐으면 
	    	    	     if (status === daum.maps.services.Status.OK) {
	
	    	    	        var coords = new daum.maps.LatLng(result[0].y, result[0].x);
	
	    	    	        // 결과값으로 받은 위치를 마커로 표시합니다
	    	    	        var marker = new daum.maps.Marker({
	    	    	            map: map,
	    	    	            position: coords
	    	    	        });
	
	    	    	        // 인포윈도우로 장소에 대한 설명을 표시합니다
	    	    	        var infowindow = new daum.maps.InfoWindow({
	    	    	            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+name+'</div>'
	    	    	        });
	    	    	        infowindow.open(map, marker);
	
	    	    	        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	    	    	        map.setCenter(coords);
	    	    	    } 
	    	    	});// 주소로 좌표 검색 끝  
    	    		
    	    	});	//클릭 펑션 끝
    	    	
	    	}//smallMap2 끝
	    	$(document).ready(smallMap2());
		}
	});
} //statisticsRetention 끝