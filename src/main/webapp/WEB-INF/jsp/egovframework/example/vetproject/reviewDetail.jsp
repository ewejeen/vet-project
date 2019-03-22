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
    
    <title>FindVet :: 후기 조회
    </title>
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/sample.css'/>"/>
    
	<script>
	    if(${sessionId != 'administrator' }){
			alert('관리자만 접근 가능합니다.');
			history.go(-1);	
		}
    </script>
    <%-- <!--For Commons Validator Client Side-->
    <script type="text/javascript" src="<c:url value='/cmmn/validator.do'/>"></script>
    <validator:javascript formName="detailForm" staticJavascript="false" xhtml="true" cdata="false"/> --%>
    
    <script type="text/javaScript" language="javascript" defer="defer">
        
        /* 글 목록 화면 function */
        function fn_egov_selectList() {
           	document.detailForm.action = "<c:url value='/review/reviewList.web'/>";
           	document.detailForm.submit();
        }
        
        /* 글 삭제 function */
        function fn_egov_delete(id) {
        	if(confirm('후기를 삭제하시겠습니까?')){
	        	document.detailForm.rv_id.value = id;
	           	document.detailForm.action = "<c:url value='/review/deleteReview.web'/>";
	           	document.detailForm.submit();        		
        	}
        }
       
    </script>
</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
<jsp:include page="header.jsp" />
<form:form commandName="reviewVO" id="detailForm" name="detailForm">
	<input type="hidden" name="selectedId" />
	<input type="hidden" name="rv_id" value="${reviewVO.rv_id }" />
    <div id="content_pop">
    	<!-- 타이틀 -->
    	<div id="title_div">
    		<ul>
    			<li>
                                   후기 조회
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
       				<p>${reviewVO.rv_title }</p>
    			</td>
    		</tr>
    		<tr>
    			<td class="tbtd_caption"><label for="reg_date">등록 시간</label></td>
    			<td class="tbtd_content">
    				<p><fmt:formatDate value="${reviewVO.rv_reg_date }" pattern="yyyy-MM-dd HH:mm"/> </p>
    			</td>
    		</tr>
    		<tr>
    			<td class="tbtd_caption"><label for="reg_date">방문 병원</label></td>
    			<td class="tbtd_content">
    				<p>${reviewVO.hpt_name} </p>
    			</td>
    		</tr>
    		<tr>
    			<td class="tbtd_caption"><label for="reg_date">병원 주소 (도로명)</label></td>
    			<td class="tbtd_content">
    				<p>
    					<c:if test="${reviewVO.adrs_new != null}">${reviewVO.adrs_new} </c:if>
    					<c:if test="${reviewVO.adrs_new == null}">도로명 주소 정보가 없습니다. </c:if>
    				</p>
    			</td>
    		</tr>
    		<tr>
    			<td class="tbtd_caption"><label for="reg_date">병원 주소 (지번)</label></td>
    			<td class="tbtd_content">
    				<p>
    					<c:if test="${reviewVO.adrs_old != null}">${reviewVO.adrs_old} </c:if>
    					<c:if test="${reviewVO.adrs_old == null}">지번 주소 정보가 없습니다.</c:if> 
    				</p>
    			</td>
    		</tr>
    		<tr>
    			<td class="tbtd_caption"><label for="hpt_rate">평점</label></td>
    			<td class="tbtd_content">
    				<p>${reviewVO.hpt_rate} </p>
    			</td>
    		</tr>
    		<tr>
    			<td class="tbtd_caption"><label for="visit_date">방문일</label></td>
    			<td class="tbtd_content">
	    			<fmt:parseDate value="${reviewVO.visit_date}" var="visitDate" pattern="yyyy-M-d"/>
    				<p><fmt:formatDate value="${visitDate}" pattern="yyyy-MM-dd"/> </p>
    			</td>
    		</tr>
    		<tr>
    			<td class="tbtd_caption"><label for="reg_date">반려동물</label></td>
    			<td class="tbtd_content">
    				<p>${reviewVO.pet_type} </p>
    			</td>
    		</tr>
    		<tr>
    			<td class="tbtd_caption"><label for="reg_date">재방문 여부</label></td>
    			<td class="tbtd_content">
    				<p>
    					<c:if test="${reviewVO.visit_is_new == 0}">첫 방문</c:if> 
    					<c:if test="${reviewVO.visit_is_new == 1}">재방문</c:if> 
    				</p>
    			</td>
    		</tr>
    		<tr>
    			<td class="tbtd_caption"><label for="content">내용</label></td>
    			<td class="tbtd_content">
    				<img class="content" src="#" alt="${reviewVO.rv_image }" />	<!-- 이미지 받아야 함 -->
    				<p class="content">${reviewVO.rv_content }</p>
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
	            <li>
	                <span class="btn_blue_l">
	                    <a href="javascript:fn_egov_delete(${reviewVO.rv_id });"><spring:message code="button.delete" /></a>
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
</body>
</html>