<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/vetproject/reset.css" media="all" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/vetproject/header.css" media="all" />
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500,700" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<div class="header">
	<div class="gnb">
		<ul>
			<li class="home"><img src="images/vetproject/ic_home.png" alt="홈" onclick="location.href='main.do'"/></li>
			<li class="logo" onclick="location.href='main.do'"><span>VET_PROJECT_LOGO</span></li>
			<li class="notice"><span onclick="location.href='egovSampleList.do'">공지사항</span></li>
		</ul>
	</div>
</div>