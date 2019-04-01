<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"         uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"      uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring"    uri="http://www.springframework.org/tags"%>
<%
/*
 *	공지사항 등록 페이지 (관리자 전용) 
 */
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!-- id 있거나 0이면 registerFlag가 modify, 없으면 create -->
    <c:set var="registerFlag" value="${selectedId eq null && (noticeVO.id eq null || noticeVO.id eq 0) ? 'create' : 'modify'}"/>
    <title>FindVet :: 공지사항 
    	<c:if test="${registerFlag == 'create'}"><spring:message code="button.create" /></c:if>
        <c:if test="${registerFlag == 'modify'}"><spring:message code="button.modify" /></c:if>
    </title>
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/notice.css'/>"/>
    
    <script>
    	if(${sessionId != 'administrator'}){
    		alert('관리자만 접근 가능합니다.');
    		history.go(-1);
    	}
    	
    	/* 글 등록 function */
        function fn_notice_save() {
        	frm = document.detailForm;
        	var title = document.getElementById('title');
        	var content = document.getElementById('content');
        	
        	if(title.value == null || title.value == ''){
        		alert('제목을 입력해 주세요.');
        		title.focus();
        		return false;
        	}        		
        	
        	if(content.value == null || content.value == ''){
        		alert('내용을 입력해 주세요.');
        		content.focus();
        		return false;
        	}
        	
        	//saveImage();
        	frm.action = "<c:url value="${registerFlag == 'create' ? '/addNotice.do' : '/updateNotice.do'}"/>";	/* 플래그가 create면 등록, 아니면 수정 */
            frm.submit();
        }
    	
    	/* 글 목록 화면 function */
        function fn_notice_selectList() {
           	document.detailForm.action = "<c:url value='/noticeList.do'/>";
           	document.detailForm.submit();
        }
    </script>
    
</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
<jsp:include page="../vetproject/header.jsp" />
<form:form commandName="noticeVO" id="detailForm" name="detailForm" enctype="multipart/form-data">
    <div id="content_pop">
    	<!-- 타이틀 -->
    	<div id="title_div">
    		<ul>
    			<li>
                                   공지사항 
                    <c:if test="${registerFlag == 'create'}"><spring:message code="button.create" /></c:if>	<!-- 등록 -->
                    <c:if test="${registerFlag == 'modify'}"><spring:message code="button.modify" /></c:if> <!-- 수정 -->
                </li>
    		</ul>
    	</div>
    	<!-- // 타이틀 -->
    	<div id="table">
    	<table width="100%" border="1" cellpadding="0" cellspacing="0" style="bordercolor:#D3E2EC; bordercolordark:#FFFFFF; BORDER-TOP:#C2D0DB 2px solid; BORDER-LEFT:#ffffff 1px solid; BORDER-RIGHT:#ffffff 1px solid; BORDER-BOTTOM:#C2D0DB 1px solid; border-collapse: collapse;">
    		<colgroup>
    			<col width="150"/>
    			<col width="?"/>
    		</colgroup>

    		<tr>
    			<td class="tbtd_caption"><label for="title">제목</label></td>
    			<td class="tbtd_content">
    				<c:if test="${registerFlag == 'modify'}">
        				<form:input path="title" maxlength="10" cssClass="txt"  />
        				<form:hidden path="id" cssClass="essentiality" maxlength="10"  />
        				&nbsp;<form:errors path="title" /></td>
                    </c:if>
                    <c:if test="${registerFlag != 'modify'}">
        				<form:input path="title" maxlength="10" cssClass="txt"  />
        				&nbsp;<form:errors path="title" /></td>
                    </c:if>
    			</td>
    		</tr>
    		<tr>
    			<td class="tbtd_caption"><label for="content">내용</label></td>
    			<td class="tbtd_content">
    				<form:textarea path="content" rows="5" cols="58" />
    				&nbsp;<form:errors path="content" />
                </td>
    		</tr>
    		<tr>
    			<td class="tbtd_caption"><label for="image">이미지</label></td>
    			<td class="tbtd_content">
    				<input type="file" name="imageFile" id="imageFile" accept="image/*"/>
    				<img src="#" alt="preview" id="preview" width="300"/>
    				&nbsp;<form:errors path="image" />
    			</td>
    		</tr>
    	</table>
    	
    	
      </div>
    	<div id="sysbtn">
    		<ul>
    			<li>
                    <span class="btn_blue_l">
                        <a href="javascript:fn_notice_selectList();"><spring:message code="button.list" /></a>
                    </span>
                </li>
    			<li>
                    <span class="btn_blue_l">
                        <a href="javascript:fn_notice_save();">
                            <c:if test="${registerFlag == 'create'}"><spring:message code="button.create" /></c:if>
                            <c:if test="${registerFlag == 'modify'}"><spring:message code="button.modify" /></c:if>
                        </a>
                    </span>
                </li>
    			<li>
                    <span class="btn_blue_l">
                        <a href="javascript:document.detailForm.reset();"><spring:message code="button.reset" /></a>
                    </span>
                </li>
            </ul>
    	</div>
    </div>
    <!-- 검색조건 유지 -->
    <input type="hidden" name="searchCondition" value="<c:out value='${searchVO.searchCondition}'/>"/>
    <input type="hidden" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>"/>
    <input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
</form:form>
<jsp:include page="../vetproject/footer.jsp" />

	<%-- <script src="<c:url value='/js/jquery.form.min.js' />"></script> --%>
	<!-- HTML5 File Upload API로 이미지 썸네일 보기 (추후 업로드와 연동) -->
	<script>
		var file = document.querySelector('#imageFile');
		var dataurl;
		file.onchange = onchangeFn;
		function onchangeFn() { 
		    var canvas, ctx;
		    var fileList = file.files ;
		    var img = document.querySelector('#preview');
		    // 읽기
		    var reader = new FileReader();
		    reader.readAsDataURL(fileList [0]);

		    //로드 한 후
		    reader.onload = onloadFn; 
		    
		    function onloadFn() {	
		        img.src = reader.result ;
			    canvas = document.createElement('canvas');
			    ctx = canvas.getContext('2d');
			    ctx.drawImage(img, 0, 0);
			    dataurl = canvas.toDataURL("image/png");
			    
			    return dataurl;
		    }
		    return dataurl;
		}
		 
		/* 
		// HTML5 File API
		function saveImage(){
			console.log('호출');
			var url = onchangeFn();
			console.log(url);
        	var fd = new FormData($('#detailForm')[0]);
        	fd.append('imageFile',url);
        	fd.append('title', $('#title').val());
        	fd.append('content', $('#content').val());
        	console.log(fd.get('imageFile'));
        	console.log(fd.get('title'));
        	console.log(fd.get('content'));
        	
        	$('#detailForm').ajaxForm({
        		url : '/vetproject_v2/addNotice.do',
        		enctype : 'multipart/form-data',
        		data : fd,
        		success: function(url){
        			console.log('성공');
        		}
        	});
        	$('#detailForm').submit();
        	
        	$.ajax({
        		data : {
        			fd
        		},
        		type : 'POST',
        		url : '/vetproject_v2/addNotice.do',
        		contentType: false,
        		enctype : "multipart/form-data",
        		processData : false,
        		success: function(url){
        			console.log('성공');
        		}
        	});
		} */
		
 	</script>
</body>
</html>