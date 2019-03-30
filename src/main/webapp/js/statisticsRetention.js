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
			// 반복문 클로저
			function renderChart() {
				for(var i=1;i<=5;i++){
					(function(j){	
						console.log(j);
				    	    var ctx = document.getElementById('myChart'+j).getContext('2d');
				  	        $('#di'+j).text(result[j-1].retention.toFixed(2)+'%');
				    	    var myDoughnutChart = new Chart(ctx, {
				    	        type: 'doughnut',
				    	        data: {
				    	        	labels: ['재방문', '첫 방문'] ,
				    	            datasets: [{
				    	                label: '재방문율',
				    	                data: [result[j-1].retention.toFixed(2), (100-result[j-1].retention.toFixed(2))],
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
				    					text: result[j-1].hptName
				    				},
				    				rotation: 1 * Math.PI,
				                    circumference: 1 * Math.PI
				    	        }
				    	    });
				    	    
					})(i);
					
				}
			}

	    	$(document).ready(renderChart());
			
	    	function smallMap2(){
	    		for(var i=1;i<=5;i++){
	    			(function(j){
	    				$('#retName'+j).click(function(){
    	    				console.log(j);
    	    				var name = result[j-1].hptName;
    	    				var phone = result[j-1].hptPhone;
    	    				var adrs = result[j-1].address;
    	    				
    	    				$('.mapName').text(name);
    	    	    		$('.mapAdrs').text(adrs);
    	    	    		$('.mapPhone').text(phone);
    	    	    		
    	    	    		// 카카오 지도
    	    	    		var mapContainer = document.getElementById('map2'), // 지도를 표시할 div 
    		    	    	    mapOption = {
    		    	    	        center: new daum.maps.LatLng(37.4120, 127.1240), // 지도의 중심좌표
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
    	    				
    	    			});
	    			})(i);
	    		}
    	    	
	    	}//smallMap2 끝
	    	$(document).ready(smallMap2());
		}
	});
} //statisticsRetention 끝