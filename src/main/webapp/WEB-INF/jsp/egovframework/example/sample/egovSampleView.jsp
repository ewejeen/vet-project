<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"         uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"         uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form"      uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring"    uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : egovSampleRegister.jsp
  * @Description : Sample Register 화면
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
    
    <title>FindVet :: 공지사항 조회
    </title>
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/sample.css'/>"/>
    
    <%-- <!--For Commons Validator Client Side-->
    <script type="text/javascript" src="<c:url value='/cmmn/validator.do'/>"></script>
    <validator:javascript formName="detailForm" staticJavascript="false" xhtml="true" cdata="false"/> --%>
    
    <script type="text/javaScript" language="javascript" defer="defer">
        
        /* 글 목록 화면 function */
        function fn_egov_selectList() {
           	document.detailForm.action = "<c:url value='/noticeList.do'/>";
           	document.detailForm.submit();
        }
        
        /* 글 삭제 function */
        function fn_egov_delete(id) {
        	if(confirm('공지를 삭제하시겠습니까?')){
	        	document.detailForm.id.value = id;
	           	document.detailForm.action = "<c:url value='/deleteNotice.do'/>";
	           	document.detailForm.submit();        		
        	}
        }
        
        /* 글 수정 화면 function */
        function fn_egov_modify_view(id) {
        	document.detailForm.selectedId.value = id;
        	document.detailForm.id.value = id;
        	document.detailForm.action = "<c:url value='/updateNoticeView.do'/>";
           	document.detailForm.submit();
        }
        
       
    </script>
</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
<jsp:include page="../vetproject/header.jsp" />
<form:form commandName="sampleVO" id="detailForm" name="detailForm">
	<input type="hidden" name="selectedId" />
	<input type="hidden" name="id" value="${sampleVO.id }" />
    <div id="content_pop">
    	<!-- 타이틀 -->
    	<div id="title_div">
    		<ul>
    			<li>
                                   공지사항 조회
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
       				<p>${sampleVO.title }</p>
    			</td>
    		</tr>
    		<tr>
    			<td class="tbtd_caption"><label for="reg_date">등록 시간</label></td>
    			<td class="tbtd_content">
    				<p><fmt:formatDate value="${sampleVO.reg_date }" pattern="yyyy-MM-dd HH:mm"/> </p>
    			</td>
    		</tr>
    		<tr>
    			<td class="tbtd_caption"><label for="content">내용</label></td>
    			<td class="tbtd_content">
    				<img class="content" src="${pageContext.request.contextPath}/images/upload/${sampleVO.image }" alt="${sampleVO.image }" />	
    				<p class="content">${sampleVO.content }</p>
                </td>
    		</tr>
    		<%-- <tr>
    			<td class="tbtd_caption"><label for="image">이미지</label></td>
    			<td class="tbtd_content">
    				<form:input path="image" maxlength="30" cssClass="txt"  readonly="true"/>
    				&nbsp;<form:errors path="image" />
    			</td>
    		</tr> --%>
    	</table>
      </div>
    	<div id="sysbtn">
    		<ul>
    			<li>
                    <span class="btn_blue_l">
                        <a href="javascript:fn_egov_selectList();"><spring:message code="button.list" /></a>
                    </span>
                </li>
    			<c:if test="${sessionId == 'administrator'}">
	    			<li>
	                    <span class="btn_blue_l">
	                        <a href="javascript:fn_egov_modify_view(${sampleVO.id }, 'modify');">	<!-- modify 정체? -->
	                            <spring:message code="button.modify" />
	                        </a>
	                    </span>
	                </li>
                    <li>
                        <span class="btn_blue_l">
                            <a href="javascript:fn_egov_delete(${sampleVO.id });"><spring:message code="button.delete" /></a>
                        </span>
                    </li>
    			</c:if>
            </ul>
    	</div>
    </div>
    <!-- 검색조건 유지 -->
    <input type="hidden" name="searchCondition" value="<c:out value='${searchVO.searchCondition}'/>"/>
    <input type="hidden" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>"/>
    <input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
</form:form>
</body>
</html>