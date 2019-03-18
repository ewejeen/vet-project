<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script>
	

	/*
	 * 
	 * Random한 값 범위를 설정하기 위한 로직
	 * (int)(Math.random() * 최대값 - 최소값 + 1) + 최소값;
	 */

	function getRandomIntInclusive(min, max) {
		return Math.floor((Math.random() * (max - min + 1)) + min);
	}
	 
	function getRandomDate() {
        var maxDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
        
        var minMonth = 1;
        var maxMonth = 12;
        
        var randomMonth = getRandomIntInclusive(minMonth, maxMonth);
        var randomDay = Math.floor((Math.random() * (maxDays[randomMonth-1] -2) + 1));
        var randomYear = getRandomIntInclusive(2010,2019);
        
        var randomDate = randomYear+"-"+randomMonth+"-"+randomDay;
        return randomDate;
	}
	 
		
	
	function fn_insert_review(){
		/* var param = JSON.stringify({
			"hpt_id" : $('#hpt_id').val(),
			"hpt_rate" : $('#hpt_rate').val(),
			"rv_title" : $('#rv_title').val(),
			"rv_content" : $('#rv_content').val(),
			"pet_type" : $('#pet_type').val(),
			"visit_date" : $('#visit_date').val(),
			"visit_is_new" : $('input[name="visit_is_new"]:checked').val()
		});
		
		console.log(param); */
		
		var hpt_id = getRandomIntInclusive(1, 4565);
		var rateArr = [0.0, 0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0];
		var hpt_rate = rateArr[getRandomIntInclusive(0,10)];
		var rv_title = '방문 후기입니다.';
		var rv_content = '방문 후기의 내용입니다.';
		var typeArr = ['.jpg', '.png'];
		var image_type = typeArr[getRandomIntInclusive(0,1)];
		var rv_image = 'image'+ getRandomIntInclusive(0,50) + image_type;
		var petArr = ['강아지','고양이','조류','소','말','돼지','기타'];
		var pet_type = petArr[getRandomIntInclusive(0,6)];
		var visit_date = getRandomDate();
		var visit_is_new = getRandomIntInclusive(0,1);
		
		$.ajax({
			url : 'insertReview.do',
			method : 'POST',
			dataType : 'json',
			data : {
				"hpt_id" : hpt_id,
				"hpt_rate" : hpt_rate,
				"rv_title" : rv_title,
				"rv_content" : rv_content,
				"rv_image" : rv_image,
				"pet_type" : pet_type,
				"visit_date" : visit_date,
				"visit_is_new" : visit_is_new
			},
			success : function(data) {
				console.log('data'+data);
			},
			error : function(xhr, status, msg) {
				console.debug('xhr:\n ' + xhr);
				console.debug('status:\n ' + status);
				console.debug('msg:\n ' + msg);
			}
		});
        
	}
	/**
	 * form의 input 값을 JSON형태로 바꾸는 function.
	 */
	$.fn.serializeObject = function() {
	  var result = {}
	  var extend = function(i, element) {
	    var node = result[element.name]
	    if ("undefined" !== typeof node && node !== null) {
	      if ($.isArray(node)) {
	        node.push(element.value)
	      } else {
	        result[element.name] = [node, element.value]
	      }
	    } else {
	      result[element.name] = element.value
	    }
	  }
	  $.each(this.serializeArray(), extend)
	  return result
	}
	
	function fn_insert2(){
		//  form의 data를 JSON 형태로 변환
		var form = $('#reviewForm').serializeObject();
		console.log( 'serializeObject\n' +JSON.stringify(form));
		//  JSON형태로 변환된 form data를 AJAX로 서버에 POST 요청 함.
		$.ajax("insertReview.do" , {
		  headers: {
		    'Accept': 'application/json'
		  },
		  method: "POST",
		  dataType: "json",
		  data:{
			  form: JSON.stringify(form)
		  },
		  success: function ( data ) {
		    console.log( data );
		  },
		  error: function(xhr, status, msg) {
		    console.log('xhr:\n ' + xhr);
		    console.log('status:\n ' + status);
		    console.log('msg:\n ' + msg);
		  }
		});
	}
	
	function fn_insert3(){
		var param = {
			"hpt_id" : $('#hpt_id').val(),
			"hpt_rate" : $('#hpt_rate').val(),
			"rv_title" : $('#rv_title').val(),
			"rv_content" : $('#rv_content').val(),
			"pet_type" : $('#pet_type').val(),
			"visit_date" : $('#visit_date').val(),
			"visit_is_new" : $('input[name="visit_is_new"]:checked').val()
		};
		
		console.log(param);
		
		$.ajax({
		  url: "/vetproject_v2/review/insertReview.do" , 
		  method: "POST",
		  cache: false,
		  data: $('#reviewForm').serialize(),
		  success: function (data) {
			  console.log(data);
		  },
		  error: function(xhr, status, msg) {
		    console.log('xhr:\n ' + xhr);
		    console.log('status:\n ' + status);
		    console.log('msg:\n ' + msg);
		  }
		});
	}
	
	
	function fn_loop(){
		var i=0;
		for(i=0;i<1;i++){
			fn_insert_review();
		}
	}
	
	/* $.ajax({
		url : '/vetproject_v2/review/insertReview.do',
		method : 'POST',
		data : {
			"hpt_id" : $('#hpt_id').val(),
			"hpt_rate" : $('#hpt_rate').val(),
			"rv_title" : $('#rv_title').val(),
			"rv_content" : $('#rv_content').val(),
			"pet_type" : $('#pet_type').val(),
			"visit_date" : $('#visit_date').val(),
			"visit_is_new" : $('input[name="visit_is_new"]:checked').val()
		},
		success : function(data) {
			console.log('data'+data);
		},
		error : function(xhr, status, msg) {
			console.debug('xhr:\n ' + xhr);
			console.debug('status:\n ' + status);
			console.debug('msg:\n ' + msg);
		}
	}); */

	
	
</script>

<title>데이터 등록 화면</title>
</head>
<body>
	<div class="reviewWrite">
		<form id="reviewForm" method="POST">
			병원ID <input type="text" name="hpt_id" id="hpt_id" /><br />
			평점 <input type="text" name="hpt_rate" id="hpt_rate" /><br />
			후기 제목 <input type="text" name="rv_title" id="rv_title" /><br />
			후기 내용 <input type="text" name="rv_content" id="rv_content" /><br />
			이미지 <input type="text" name="rv_image" id="rv_image" /><br />
			반려동물 종류<select name="pet_type" id="pet_type">
				<option value="강아지">강아지</option>
				<option value="고양이">고양이</option>
				<option value="조류">조류</option>
				<option value="소">소</option>
				<option value="말">말</option>
				<option value="돼지">돼지</option>
				<option value="기타">기타</option>
			</select><br />
			방문일 <input type="text" name="visit_date" id="visit_date" /><br />
			<label for="visit_is_new">N번째 방문: </label><br />
			<input type="radio" name="visit_is_new" value="0" checked/>첫 방문
			<input type="radio" name="visit_is_new" value="1"/> 재방문
			
			<input type="button" value="작성하기" onclick="fn_insert2()"/>
		</form>
	</div>

</body>

<script>
	
/*

	/*

	 //min (포함) 과 max (포함) 사이의 임의 정수를 반환하는 함수.
	 

	 //setTimeout()은 지연시간을 발생시킨 후 특정 함수를 호출함.

	 var showAlert = setTimeout(function() {
	 clearInterval(getRandomNo);
	 alert('Wow!!!');
	 }, 10000);

	 

	//rvw 테이블에 AJAX로 id, cont 값을 입력 시키는 함수.
	function saveRvw(id, cont) {
		//  1 ~ 187사이의 난수를 발생 시켜서 rst_no값으로 준다.
		let rst_no = getRandomIntInclusive(1, 187);
		//  1 ~ 3 사이의 난수를 발생 시켜서 score값으로 준다.
		let score = getRandomIntInclusive(1, 2);
		param = JSON.stringify({
			"rst_no" : rst_no,
			"id" : id,
			"cont" : cont,
			"score" : score
		})

		setTimeout(function() {
			$.ajax("/yummy/rvw/save", {
				method : "POST",
				data : param,
				contentType : "application/json; charset=UTF-8",
				dataType : "json",
				success : function(data) {
					console.log(data);
				},
				error : function(xhr, status, msg) {
					console.debug('xhr:\n ' + xhr);
					console.debug('status:\n ' + status);
					console.debug('msg:\n ' + msg);
				}
			});
		}, 500);
	}

	function signUp(id, pwd, nick) {
		let url = '/yummy/memb/signUp';
		let param = {
			id : id,
			pwd : pwd,
			nick : nick
		}
		try {
			$.post(url, param).done(function(data) {
				console.log(data);
			});
		} catch (err) {
		}
	}

	var cnt = 0;
	function callbackInsert(cycle) {
		if (cnt < cycle) {
			setTimeout(function() {
				cnt++;
				let id = 'TestUser9-' + cnt;
				let nick = 'TestUser9-' + cnt;
				let pwd = 1111;

				signUp(id, pwd, nick);

				for (let j = 0; j <= 100; j++) {
					setTimeout(function() {
						let cont = cnt + '\n' + id + '의 Review Test - ' + j
								+ '임';
						saveRvw(id, cont);
					}, 500);
				}
				callbackInsert(cycle);
			}, 1000);
		}
	}
	*/
	
	
</script>
</html>