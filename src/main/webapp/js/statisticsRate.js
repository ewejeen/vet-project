function statisticsRate(){
		var locArr = $('#sec2Region').text().split(' ');
		//var locArr = '경기도 성남시'.split(' ');
    	var province = locArr[0];
    	var city = locArr[1];
		
    	$.ajax({
    		url : 'statisticsRate.do',
    		type : 'POST',
    		data : {
    			'province' : province,
    			'city' : city
    		},
    		success : function(result){
    			//var result = JSON.stringify(data);
    			//console.log("rate호출"+result);
    			
    			// 각 텍스트에 정보 넣어 주기
    			for(var i=1;i<=5;i++){
	    			$('#rateName'+i).text(result[i-1].hptName);
	    			$('#rateRate'+i).text(result[i-1].rateAvg.toFixed(2)+' ('+result[i-1].reviewCnt+')');	//실수.toFixed(자릿수): 소수 부분을 자릿수까지 남겨두고 반올림				
    			}
    			
    			// 차트 만들기
    			function renderChart() {
    	    	    var ctx = document.getElementById("myChart").getContext('2d');
    	  	        
    	    	    var myChart = new Chart(ctx, {
    	    	        type: 'bar',
    	    	        data: {
    	    	            labels: [result[0].hptName, result[1].hptName, result[2].hptName, result[3].hptName, result[4].hptName] ,
    	    	            datasets: [
    	    	            {
    	    	                label: '평점',
    	    	                yAxesID: 'A',
    	    	                data: [result[0].rateAvg.toFixed(2), result[1].rateAvg.toFixed(2), result[2].rateAvg.toFixed(2), result[3].rateAvg.toFixed(2), result[4].rateAvg.toFixed(2)],
    	    	                backgroundColor: [	// borderColor, borderWidth
    	    						'rgb(255, 99, 132)',	//red
    	    						'rgb(54, 162, 235)',	//blue
    	    						'rgb(255, 205, 86)',	//yellow
    	    						'rgb(75, 192, 192)',	//green
    	    						'rgb(255, 159, 64)'		//orange
    	    						//'rgb(153, 102, 255)'	//purple
    	    					],
    	    	                borderWidth: 1,
    	    	            },
    	    	            {
    	    	                label: '후기 수',
    	    	                yAxisID : 'B',
    	    	                data: [result[0].reviewCnt, result[1].reviewCnt, result[2].reviewCnt, result[3].reviewCnt, result[4].reviewCnt],
    	    	            }]
    	    	                
    	    	        },
    	    	        
    	    	        options: {
    	    	        	responsive: true,
    						tooltips: {
    							mode: 'index',
    							intersect: true
    						},
    	    	            scales: {
    	    	                yAxes: [
    	    	                    {
    	    	                        id: 'A',
    	    	                        ticks: {
    	    	                            beginAtZero: true,
    	    	                            //suggestedMin: 2.0,
    	    	                            suggestedMax: 4.0, 
    	    	                            stepSize:0.5,
    	    	                            min: 2.5,
    	    	                        },
    	    	                    },
    	    	                    {
    	    	                        id: 'B',
    	    	                        position: 'right',
    	    	                        ticks: {
    	    	                            beginAtZero: true,
    	    	                        },
    	    	                        /* scaleLabel: {
    	    	                            display: true,
    	    	                            labelString: '후기 수'
    	    	                          } */
    	    	                    },
    	    	                ],
    	    	                xAxes: [{	/* 굵기 조절 */
      	    	                  categoryPercentage: 0.6,
      	    	                  barPercentage: 1,
      	    	                }, {
      	    	                  id: 'B',
      	    	                  type: 'category',
      	    	                  display: false,
      	    	                  categoryPercentage: 0.6,
      	    	                  barPercentage: 1,
      	    	                  barThickness: 20,
      	    	                  gridLines: {
      	    	                    offsetGridLines: true
      	    	                  }
      	    	                }],
      	    	              legend: {
      	    	                display: false
      	    	              }
    	    	            },
    	    	            
    	    	          
    	    	            
    	    	            
    	    	            
    	    	        }
    	    	        
    	    	        
    	    	    });
    	    	}

    	    	$(document).ready(renderChart());
    			
    	    	function smallMap(){
    	    		
    	    		/* 반복문 처리 될 것 같은데....*/
	    	    	$('#rateName1').click(function(){
	    	    		var name = result[0].hptName;
	    	    		var phone = result[0].hptPhone;    	    		
	    	    		var adrs = result[0].address;
	    	    		
	    	    		$('.mapName').text(name);
	    	    		$('.mapAdrs').text(adrs);
	    	    		$('.mapPhone').text(phone);
	    	    		
	    	    		// 카카오 지도
	    	    		var mapContainer = document.getElementById('map1'), // 지도를 표시할 div 
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
	    	    	$('#rateName2').click(function(){
	    	    		var name = result[1].hptName;
	    	    		var phone = result[1].hptPhone;    	    		
	    	    		var adrs = result[1].address;
	    	    		
	    	    		$('.mapName').text(name);
	    	    		$('.mapAdrs').text(adrs);
	    	    		$('.mapPhone').text(phone);
	    	    		
	    	    		// 카카오 지도
	    	    		var mapContainer = document.getElementById('map1'), // 지도를 표시할 div 
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
	    	    	$('#rateName3').click(function(){
	    	    		var name = result[2].hptName;
	    	    		var phone = result[2].hptPhone;    	    		
	    	    		var adrs = result[2].address;
	    	    		
	    	    		$('.mapName').text(name);
	    	    		$('.mapAdrs').text(adrs);
	    	    		$('.mapPhone').text(phone);
	    	    		
	    	    		// 카카오 지도
	    	    		var mapContainer = document.getElementById('map1'), // 지도를 표시할 div 
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
	    	    	$('#rateName4').click(function(){
	    	    		var name = result[3].hptName;
	    	    		var phone = result[3].hptPhone;    	    		
	    	    		var adrs = result[3].address;
	    	    		
	    	    		$('.mapName').text(name);
	    	    		$('.mapAdrs').text(adrs);
	    	    		$('.mapPhone').text(phone);
	    	    		
	    	    		// 카카오 지도
	    	    		var mapContainer = document.getElementById('map1'), // 지도를 표시할 div 
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
	    	    	$('#rateName5').click(function(){
	    	    		var name = result[4].hptName;
	    	    		var phone = result[4].hptPhone;    	    		
	    	    		var adrs = result[4].address;
	    	    		
	    	    		$('.mapName').text(name);
	    	    		$('.mapAdrs').text(adrs);
	    	    		$('.mapPhone').text(phone);
	    	    		
	    	    		// 카카오 지도
	    	    		var mapContainer = document.getElementById('map1'), // 지도를 표시할 div 
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
	    	    	
    	    	}//smallMap 끝
    	    	$(document).ready(smallMap());
    		}
    	});
    } //statisticsRate 끝