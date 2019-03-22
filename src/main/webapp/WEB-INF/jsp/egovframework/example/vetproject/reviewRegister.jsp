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
    
	<script>
	    if(${sessionId != 'administrator' }){
			alert('관리자만 접근 가능합니다.');
			history.go(-1);	
		}
    </script>
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
        
        
        /*
    	 * 
    	 * 랜덤 후기 데이터 ajax로 넣기
    	 * (int)(Math.random() * 최대값 - 최소값 + 1) + 최소값;
    	 */

    	function getRandomIntInclusive(min, max) {
    		return Math.floor((Math.random() * (max - min + 1)) + min);
    	}
    	 
    	function getRandomDate() {
            var maxDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
            
            var minMonth = 1;
            var maxMonth = 12;
            
            var randomMonth = getRandomIntInclusive(minMonth, maxMonth);
            var randomDay = Math.floor((Math.random() * (maxDays[randomMonth-1] -2) + 1));
            var randomYear = getRandomIntInclusive(2010,2019);
            
            var randomDate = randomYear+"-"+randomMonth+"-"+randomDay;
            return randomDate;
    	}
    	 
        
        /* 후기 등록 AJAX function */
        function fn_egov_save_ajax() {
       		
       		var hpt_id = getRandomIntInclusive(1, 4565);
    		var rateArr = [0.0, 0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0];
    		var hpt_rate = rateArr[getRandomIntInclusive(0,10)];
    		var rv_titleArr = [
    			'좋은 병원이에요', '친절하네요',
    			'종합검진 받고 왔어요', '동물 병원에 다녀왔습니다.','다녀온 후기입니다.','방문 후기입니다.','병원 후기','동물 병원 후기','병원 방문 후기',
    			'실망했어요','별로네요'];
    		var rv_title = rv_titleArr[getRandomIntInclusive(2,8)];
    		var rv_contentArr = [
    			'친절하셔서 좋았습니다.','이 병원에 다녀오고 다 나았어요.','넓고 깨끗해서 좋았어요.',
    			'후기가 좋아서 방문해 봤어요.',	'동물 병원에 다녀온 후기입니다.','종합 검진 받았어요',
    			'별로였어요','조금 실망했네요.'];
    		var rv_content = rv_contentArr[getRandomIntInclusive(3,5)];
    		var typeArr = ['.jpg', '.png'];
    		var image_type = typeArr[getRandomIntInclusive(0,1)];
    		var rv_image = 'image'+ getRandomIntInclusive(0,50) + image_type;
    		var petArr = ['강아지','고양이','조류','기타','소','말','돼지'];
    		var pet_type = petArr[getRandomIntInclusive(0,2)];
    		var visit_date = getRandomDate();
    		//var visit_is_new = getRandomIntInclusive(0,1);
    		var visit_is_new = 0;
    		
    		$.ajax({
    			url : '/vetproject_v2/review/addReviewAjax.do',
    			method : 'POST',
    			dataType : 'json',
    			data : {
    				"hpt_id" : hpt_id,
    				"hpt_rate" : hpt_rate,
    				"rv_title" : rv_title,
    				"rv_content" : rv_content,
    				"rv_image" : rv_image,
    				"pet_type" : pet_type,
    				"visit_date" : visit_date,
    				"visit_is_new" : visit_is_new
    			},
    			success : function(data) {
    				console.log('성공');
    			},
    			error : function(xhr, status, msg) {
    				console.debug('xhr:\n ' + xhr);
    				console.debug('status:\n ' + status);
    				console.debug('msg:\n ' + msg);
    			}
    		});
       		
        }
        
        
        
        
        
        
       
    </script>
    <script>
    function hitUp(){
    	$.ajax({
    		url : '/vetproject_v2/vetHitUp.do',
    		method : 'POST',
    		data : {
    			'hpt_id' : getRandomIntInclusive(0, 4565)
    		},
    		success : function(data) {
				console.log('data: '+data);
			},
			error : function(xhr, status, msg) {
				
			}
    	});
    }
    
    function loop(){
    	for(var i=0;i<10000;i++){
    		fn_egov_save_ajax();
    	}
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
                    	<!-- 등록 버튼 -->
                        <!-- <a href="javascript:fn_egov_save_ajax();"> -->
                        <a href="javascript:loop();">
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