<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"      uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/*
 *	공지사항 목록 페이지 
 */
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>FindVet :: 공지사항</title>
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/notice.css'/>"/>

    <script type="text/javaScript" language="javascript" defer="defer">
        
        /* 글 수정 화면 function */
        function fn_notice_update(id) {
           	document.listForm.action = "<c:url value='/updateNoticeView.do?id="+id+"'/>";
           	document.listForm.submit();
        }

        /* 글 보기 화면 function */
        function fn_notice_select(id) {
           	document.listForm.action = "<c:url value='/selectNoticeView.do?id="+id+"'/>";
           	document.listForm.submit();
        }
        
        /* 글 등록 화면 function */
        function fn_notice_addView() {
        	document.listForm.selectedId.value='';
        	document.listForm.action = "<c:url value='/addNoticeView.do'/>";
           	document.listForm.submit();
        }
        
        /* 글 목록 검색 화면 function */
        function fn_notice_selectList() {
        	document.listForm.action = "<c:url value='/noticeList.do'/>";
           	document.listForm.submit();
        }
        
        /* pagination 페이지 링크 function */
        function fn_notice_link_page(pageNo){
        	document.listForm.pageIndex.value = pageNo;
        	document.listForm.action = "<c:url value='/noticeList.do'/>";
           	document.listForm.submit();
        }
        
        
    </script>
</head>

<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
	<jsp:include page="../vetproject/header.jsp" />
	
    <form:form commandName="searchVO" id="listForm" name="listForm" method="post">
        <input type="hidden" name="selectedId" />
        <div id="content_pop">
        	<!-- 타이틀 -->
        	<div id="title_div">
        		<ul>
        			<li onclick="location.href='noticeList.do'"><spring:message code="list.sample" /></li>
        		</ul>
        	</div>
        	<!-- // 타이틀 -->
        	<div id="search">
        		<ul>
        			<li>
        			    <label for="searchCondition" style="visibility:hidden;"><spring:message code="search.choose" /></label>
        				<form:select path="searchCondition" cssClass="use">
        					<form:option value="1" label="제목" />
        					<form:option value="0" label="내용" />
        				</form:select>
        			</li>
        			<li><label for="searchKeyword" style="visibility:hidden;display:none;"><spring:message code="search.keyword" /></label>
                        <form:input path="searchKeyword" cssClass="txt"/>
                    </li>
        			<li>
        	            <span class="btn_blue_l">
        	                <a href="javascript:fn_notice_selectList();"><spring:message code="button.search" /></a>
        	            </span>
        	        </li>
                </ul>
        	</div>
        	<!-- List -->
        	<div id="table">
        		<table width="100%" border="0" cellpadding="0" cellspacing="0" summary="공지글 ID, 제목, 등록자('관리자'), 등록일 표시하는 테이블">
        			<caption style="visibility:hidden">공지글 ID, 제목, 등록자("관리자"), 등록일 표시하는 테이블</caption>
        			<colgroup>
        				<col width="200"/>
        				<col width="?"/>
        				<col width="230"/>
        				<col width="250"/>
        			</colgroup>
        			<tr>
        				<th align="center">ID</th>
        				<th align="center">제목</th>
        				<th align="center">작성자</th>
        				<th align="center">등록일</th>
        			</tr>
        			<c:forEach var="result" items="${resultList}" varStatus="status">
            			<tr>
            				<td align="center" class="listtd id"><c:out value="${result.id}"/></td>
            				<!-- 제목 클릭 시 조회 페이지로 -->
            				<td align="center" class="listtd title" onclick="fn_notice_select(${result.id})" style=""><c:out value="${result.title}"/></td>
            				<td align="center" class="listtd writer"><c:out value="관리자"/></td>
            				<td align="center" class="listtd regdate"><fmt:formatDate value="${result.regDate}" pattern="yyyy-MM-dd" /> </td>
            			</tr>
        			</c:forEach>
        			<tr>
        				<td class="nopost" colspan="4"><c:if test="${totCnt == 0 }"><p>게시물이 없습니다.</p></c:if></td>
        			</tr>
        		</table>
        	</div>
        	<!-- /List -->
        	<div id="paging">
        		<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="fn_notice_link_page" />
        		<form:hidden path="pageIndex" />
        	</div>
        	<!-- 관리자만 볼 수 있음 -->
        	<c:if test="${sessionId == 'administrator' }">
	        	<div id="sysbtn">
	        	  <ul>
	        	      <li>
	        	          <span class="btn_blue_l">
	        	              <a href="javascript:fn_notice_addView();">새 공지 <spring:message code="button.create" /></a>
	                      </span>
	                  </li>
	              </ul>
	        	</div>
            </c:if>
        </div>
    </form:form>
	
	<jsp:include page="../vetproject/footer.jsp" />
</body>
</html>
