<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"         uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"      uri="http://www.springframework.org/tags/form" %>
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
    
    <title>후기 <spring:message code="button.create" />
    </title>
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/sample.css'/>"/>
    
    <script type="text/javaScript" language="javascript" defer="defer">
        
        /* 후기 목록 화면 function */
        function fn_egov_selectList() {
           	document.detailForm.action = "<c:url value='/egovSampleList.do'/>";
           	document.detailForm.submit();
        }
        
        /* 후기 삭제 function */
        function fn_egov_delete() {
           	document.detailForm.action = "<c:url value='/deleteSample.do'/>";
           	document.detailForm.submit();
        }
        
        /* 후기 등록 function */
        function fn_egov_save() {
        	var frm = document.reviewForm;
        	frm.action = "<c:url value='addReview.do'/>";
            frm.submit();
        }
        
       
    </script>
</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
<jsp:include page="../vetproject/header.jsp" />
<form:form commandName="reviewVO" id="reviewForm" name="reviewForm">
    <div id="content_pop">
    	<!-- 타이틀 -->
    	<div id="title">
    		<ul>
    			<li>
                    <spring:message code="button.create" />
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
    			<td class="tbtd_caption"><label for="hpt_id">Hpt_Id</label></td>
    			<td class="tbtd_content">
     				<form:input path="hpt_id" maxlength="10" cssClass="txt"  />
     				&nbsp;<form:errors path="hpt_id" /></td>
    			</td>
    		</tr>
    		<tr>
    			<td class="tbtd_caption"><label for="visit_date">Visit_Date</label></td>
    			<td class="tbtd_content">
     				<form:input path="visit_date" maxlength="10" cssClass="txt"  />
     				&nbsp;<form:errors path="visit_date" /></td>
    			</td>
    		</tr>
    		<tr>
    			<td class="tbtd_caption"><label for="hpt_rate">Hpt_Rate</label></td>
    			<td class="tbtd_content">
     				<form:input path="hpt_rate" maxlength="10" cssClass="txt"  />
     				&nbsp;<form:errors path="hpt_rate" /></td>
    			</td>
    		</tr>
    		<tr>
    			<td class="tbtd_caption"><label for="pet_type">Pet_Type</label></td>
    			<td class="tbtd_content">
     				<form:input path="pet_type" maxlength="10" cssClass="txt"  />
     				&nbsp;<form:errors path="pet_type" /></td>
    			</td>
    		</tr>
    		<tr>
    			<td class="tbtd_caption"><label for="visit_is_new">Visit_Is_New</label></td>
    			<td class="tbtd_content">
     				<form:input path="visit_is_new" maxlength="10" cssClass="txt"  />
     				&nbsp;<form:errors path="visit_is_new" /></td>
    			</td>
    		</tr>
    		<tr>
    			<td class="tbtd_caption"><label for="rv_title">Rv_Title</label></td>
    			<td class="tbtd_content">
    				<form:input path="rv_title" maxlength="10" cssClass="txt"  />
    				&nbsp;<form:errors path="rv_title" />
                </td>
    		</tr>
    		<tr>
    			<td class="tbtd_caption"><label for="rv_content">Rv_Content</label></td>
    			<td class="tbtd_content">
    				<form:textarea path="rv_content" rows="5" cols="58" />
    				&nbsp;<form:errors path="rv_content" />
                </td>
    		</tr>
    		<tr>
    			<td class="tbtd_caption"><label for="rv_image">Rv_Image</label></td>
    			<td class="tbtd_content">
    				<form:input path="rv_image" maxlength="30" cssClass="txt"/>
    				&nbsp;<form:errors path="rv_image" />
    			</td>
    		</tr>
    	</table>
      </div>
    	<div id="sysbtn">
    		<ul>
    			<li>
                    <span class="btn_blue_l">
                        <a href="javascript:fn_egov_selectList();"><spring:message code="button.list" /></a>
                        <img src="<c:url value='/images/egovframework/example/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
                    </span>
                </li>
    			<li>
                    <span class="btn_blue_l">
                        <a href="javascript:fn_egov_save();">
                            <spring:message code="button.create" />
                        </a>
                        <img src="<c:url value='/images/egovframework/example/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
                    </span>
                </li>
                <li>
                    <span class="btn_blue_l">
                        <a href="javascript:fn_egov_delete();"><spring:message code="button.delete" /></a>
                        <img src="<c:url value='/images/egovframework/example/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
                    </span>
                </li>
    			<li>
                    <span class="btn_blue_l">
                        <a href="javascript:document.detailForm.reset();"><spring:message code="button.reset" /></a>
                        <img src="<c:url value='/images/egovframework/example/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
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