<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
/*
*		관리자 콘솔
*		NoticeController -> /notrespassing/adminconsole.do
*/		
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/reset.css" media="all" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/adminConsole.css" media="all" />
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500,700" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<title>FindVet :: 관리자 콘솔</title>
<script>
	if(${sessionId != 'administrator' }){
		alert('관리자만 접근 가능합니다.');
		history.go(-1);	
	}

	/* 로그아웃 */
	function fn_admin_logout(){
		if(confirm('로그아웃 하시겠습니까?')){
			$.ajax({
				url : '/vetproject_v2/notrespassing/signout.do',
				type : 'POST',
				success : function(data) {
					if (data.result == 1) {
						alert('로그아웃 되었습니다.');
						location.href = '/vetproject_v2/notrespassing/signinview.do';
					} else {
						alert('로그아웃에 실패했습니다. 다시 시도해 주세요.');
					}
				}
			});
		}
	}
</script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div id="wrap">
		<div class="console">
			<div class="top">
				${sessionId }님 로그인
				<button onclick="fn_admin_logout()">로그아웃</button>
			</div>
			<div class="main">
				<p class="title">관리자 페이지</p>
				<div class="boards">
					<div class="notice">
						<a href="<%=request.getContextPath() %>/addNoticeView.do">공지사항 등록</a>
					</div>
					<div class="review">
						<a href="<%=request.getContextPath() %>/review/reviewList.web">후기 관리</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../vetproject/footer.jsp" />
</body>
</html>