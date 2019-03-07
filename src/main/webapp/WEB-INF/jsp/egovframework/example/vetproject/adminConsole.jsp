<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/vetproject/reset.css" media="all" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/vetproject/adminConsole.css" media="all" />
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500,700" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<title>관리자 콘솔</title>
<script>
	if(${sessionId eq null }){
		alert('관리자만 접근 가능합니다.');
		history.go(-1);	
	}
</script>
</head>
<body>
	<div id="wrap">
		<div class="console">
			관리자 콘솔
		</div>
	</div>
</body>
</html>