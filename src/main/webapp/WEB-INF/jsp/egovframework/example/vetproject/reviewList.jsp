<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"      uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : egovSampleList.jsp
  * @Description : Sample List 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2009.02.01            최초 생성
  *
  * author 실행환경 개발팀
  * since 2009.02.01
  *
  * Copyright (C) 2009 by MOPAS  All right reserved.
  */
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>FindVet :: 후기 목록</title>
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/sample.css'/>"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- <script>
	    if(${sessionId != 'administrator' }){
			alert('관리자만 접근 가능합니다.');
			history.go(-1);	
		}
    </script> -->
    
    <script type="text/javaScript" language="javascript" defer="defer">
  
        /* 글 보기 화면 function */
        function fn_review_select(id) {
           	document.listForm.action = "<c:url value='/review/reviewDetail.web?rvId="+id+"'/>";
           	document.listForm.submit();
        }
      
        /* 글 목록 검색 화면 function */
        function fn_review_selectList() {
        	document.listForm.action = "<c:url value='/review/reviewList.web'/>";
           	document.listForm.submit();
        }
        
        /* 글 삭제 function */
        function fn_review_delete(id) {
        	if(confirm('후기를 삭제하시겠습니까?')){ 
	           	$.ajax({
	           		url : '/vetproject_v2/review/deleteReview.web',
	           		type : 'POST',
	           		data : {
	           			'checkArr' : id+','	/* 다중삭제 컨트롤러에 맞추기 위함 */
	           		},
	    			success : function(data) {
	    				location.reload();
	    			},
	    			error : function(xhr, status, msg) {
	    				console.debug('xhr:\n ' + xhr);
	    				console.debug('status:\n ' + status);
	    				console.debug('msg:\n ' + msg);
	    			}
	    		});
        	}
        }
        
        /* 글 다중 삭제 function */
        $(document).ready(function(){
        	$("#checkAll").click(function(){
        		var chk = $("#checkAll").prop("checked");
        		if(chk) {
        			$(".deleteChkBox").prop("checked", true);
        		} else {
        			$(".deleteChkBox").prop("checked", false);
        		}
        	});
        	
        	$(".deleteChkBox").click(function(){
        		$("#checkAll").prop("checked", false);
        	});
        	
        	$('#deleteSelected').click(function(){
        		if(confirm('선택하신 후기를 삭제하시겠습니까?')){
        			var checkArr = '';
        			$('input[name="deleteChkBox"]:checked').each(function(){
        				checkArr+=$(this).attr('value')+",";
        			});
    				console.log(checkArr);
        			$.ajax({
    	    			url : '/vetproject_v2/review/deleteReview.web',
    	    			type : 'POST',
    	    			data : {
    	    				'checkArr' : checkArr
    	    			},
    	    			success : function(data){
    	    				alert(data+'개의 후기가 삭제되었습니다.');
    	    				location.reload();
    	    			},
    	    			error : function(xhr, status, msg) {
    	    				console.debug('xhr:\n ' + xhr);
    	    				console.debug('status:\n ' + status);
    	    				console.debug('msg:\n ' + msg);
    	    			}
    	    		});
        		}
        		return false;
        	}); 
        });
        
        
        /* pagination 페이지 링크 function */
        function fn_review_link_page(pageNo){
        	document.listForm.pageIndex.value = pageNo;
        	document.listForm.action = "<c:url value='/review/reviewList.web'/>";
           	document.listForm.submit();
        }
        
        
    </script>
</head>

<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
	<jsp:include page="header.jsp" />
	
    <form:form commandName="searchVO" id="listForm" name="listForm" method="post" acceptCharset="utf-8">
        <input type="hidden" name="selectedId" />
        <div id="content_pop">
        	<!-- 타이틀 -->
        	<div id="title_div">
        		<ul>
        			<li onclick="location.href='reviewList.web'">후기 목록</li>
        		</ul>
        	</div>
        	<!-- // 타이틀 -->
        	<div id="search">
        		<ul>
        			<li>
        			    <label for="searchCondition" style="visibility:hidden;"><spring:message code="search.choose" /></label>
        				<form:select path="searchCondition" cssClass="use">
        					<form:option value="0" label="제목" />
        					<form:option value="1" label="병원명" />
        					<form:option value="2" label="주소" />
        				</form:select>
        			</li>
        			<li><label for="searchKeyword" style="visibility:hidden;display:none;"><spring:message code="search.keyword" /></label>
                        <form:input path="searchKeyword" cssClass="txt"/>
                    </li>
        			<li>
        	            <span class="btn_blue_l">
        	                <a href="javascript:fn_review_selectList();"><spring:message code="button.search" /></a>
        	            </span>
        	        </li>
                </ul>
        	</div>
        	<!-- List -->
        	<div id="table">
        		<table width="100%" border="0" cellpadding="0" cellspacing="0" summary="공지글 ID, 제목, 등록자('관리자'), 등록일 표시하는 테이블">
        			<caption style="visibility:hidden">공지글 ID, 제목, 등록자("관리자"), 등록일 표시하는 테이블</caption>
        			<colgroup>
        				<col width="70"/>
        				<col width="120"/>
        				<col width="?"/>
        				<col width="220"/>
        				<col width="170"/>
        			</colgroup>
        			<tr>
        				<th align="center"><input type="checkbox" name="checkAll" id="checkAll" /></th>
        				<th align="center">ID</th>
        				<th align="center">제목 (병원명)</th>
        				<th align="center">등록일</th>        			
        				<th align="center">삭제</th>
        			</tr>
        			<c:forEach var="result" items="${resultList}" varStatus="status">
            			<tr>
            				<td align="center" class="listtd deleteChk"><input type="checkbox" name="deleteChkBox" class="deleteChkBox"  value="${result.rvId}"/></td>
            				<td align="center" class="listtd id"><c:out value="${result.rvId}"/></td>
            				<!-- 제목 클릭 시 조회 페이지로 -->
            				<%-- <td align="center" class="listtd title" onclick="fn_review_select(${result.id})" style=""><c:out value="${result.title}"/></td> --%>
            				<td align="center" class="listtd title" onclick="fn_review_select(${result.rvId})"><c:out value="${result.rvTitle}"/> (<c:out value="${result.hptName}" />)</td>
            				<td align="center" class="listtd regdate"><fmt:formatDate value="${result.rvRegDate}" pattern="yyyy-MM-dd" /> </td>
            				<td align="center" class="listtd delete"><a onclick="fn_review_delete(${result.rvId})">삭제</a></td>
            			</tr>
        			</c:forEach>
        			<tr>
        				<td class="nopost" colspan="4"><c:if test="${totCnt ==0 }"><p>게시물이 없습니다.</p></c:if></td>
        			</tr>
        		</table>
        		<button id="deleteSelected">선택 삭제</button>
        	</div>
        	<!-- /List -->
        	<div id="paging">
        		<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="fn_review_link_page" />
        		<form:hidden path="pageIndex" />
        	</div>
        
        </div>
    </form:form>
</body>
</html>
