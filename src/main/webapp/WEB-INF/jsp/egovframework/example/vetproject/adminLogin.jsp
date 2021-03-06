<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
/*
*		관리자 로그인 페이지
*		NoticeController -> /notrespassing/signinview.do
*/		
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/reset.css" media="all" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/adminLogin.css" media="all" />
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500,700" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<title>FindVet :: 관리자 로그인</title>
<script>
	/* sessionId(로그인 시 만들어지는 세션 아이디)가 null이 아니면 */
	if(${sessionId ne null }){
		alert('이미 로그인되어 있습니다.');
		history.go(-1);	
	}
	
	/* 엔터키(키코드 13번)를 쳤을 경우 adminLogin() 함수 실행 */
	function enterkey() {
		if (window.event.keyCode == 13) {
			adminLogin();
		}
	}
	
	function adminLogin(){
		var id = $('#adminId');
		var pw = $('#adminPw');
		if(id.val()=='' || id.val()==null){
			alert('아이디를 입력해 주세요.');
			id.focus();
			return false;
		}
		if(pw.val()=='' || pw.val()==null){
			alert('비밀번호를 입력해 주세요.');
			pw.focus();
			return false;
		}
		
		/* 관리자 로그인 ajax, 성공 시 result 값으로 1 반환 */
		$.ajax({
			url : 'signin.do',
			type : 'POST',
			dataType : 'json',
			data : {
				'adminId' : id.val(),
				'adminPw' : pw.val()
			},
			success : function(data){
				if(data.result==1){
					location.href='adminconsole.do';
				} else{
					alert('로그인 실패. 다시 시도해 주세요.');
				}
			}
		});
	}
</script>
</head>
<body>
	<div id="wrap">
		<div class="signIn">
			<div class="signInForm">
				<ul>
				    <li class="top">
				    	<img src="<%=request.getContextPath() %>/images/vetproject/logo_findvet.png"" alt="로고" />
				        <h1>관리자 로그인</h1>    
				    </li>
					
					<li>
                        <span>아이디</span> 
                        <input type="text" name="adminId" id="adminId" onkeyup="enterkey()">
					</li>
					<li>
                        <span>비밀번호</span>  
				        <input type="password" name="adminPw" id="adminPw" onkeyup="enterkey()">
					</li>
                    <li>
                        <input type="button" value="로그인" onclick="adminLogin()" />
                    </li>
				</ul>
			</div>
			
		</div>
	</div>
</body>
</html>