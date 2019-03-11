<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script>
	function fn_insert_review(hpt_id, hpt_rate, rv_title, rv_content, pet_type, visit_date, visit_is_new){
		var param = JSON.stringify({
			"hpt_id" : hpt_id,
			"hpt_rate" : hpt_rate,
			"rv_title" : rv_title,
			"rv_content" : rv_content,
			"pet_type" : pet_type,
			"visit_date" : visit_date,
			"visit_is_new" : visit_is_new
		});
		
		$.ajax({
			url : 'insertReview.do',
			method : 'POST'
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
	}
</script>

<title>Insert title here</title>
</head>
<body>
	<div class="reviewWrite">
		<form action="insertReview.do" name="reviewForm">
			병원ID <input type="text" name="hpt_id" id="hpt_id" /><br />
			평점 <input type="text" name="hpt_rate" id="hpt_rate" /><br />
			후기 제목 <input type="text" name="rv_title" id="rv_title" /><br />
			후기 내용 <input type="text" name="rv_content" id="rv_content" /><br />
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
			<input type="radio" name="visit_is_new" id="visit_is_new" value="첫 방문" />첫 방문
			<input type="radio" name="visit_is_new" id="visit_is_new" value="재방문" />재방문
			<input type="button" value="작성하기" onclick="fn_insert_review()" />
		</form>
	</div>

</body>

<script>
/*

	/*

	 //min (포함) 과 max (포함) 사이의 임의 정수를 반환하는 함수.
	 function getRandomIntInclusive(min, max) {
	 return Math.round(Math.random() * (max - min + 1)) + min;
	 //  Math.round() 고르지 않은 분포의 난수를 생성한다.
	 }

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