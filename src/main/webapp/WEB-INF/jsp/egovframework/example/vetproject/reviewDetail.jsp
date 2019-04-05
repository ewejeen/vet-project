<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator"
	uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/*
*		후기 상세 보기 페이지 (관리자 전용)
*		ReviewController -> /review/reviewDetail.web
*/		
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>FindVet :: 후기 조회</title>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/notice.css'/>" />

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script>
	    if(${sessionId != 'administrator' }){
			alert('관리자만 접근 가능합니다.');
			history.go(-1);	
		}
        
        /* 후기 목록 function */
        function fn_review_list() {
           	document.detailForm.action = "<c:url value='/review/reviewList.web'/>";
           	document.detailForm.submit();
        }
        
        /* 후기 삭제 function */
        function fn_review_delete(id) {
        	if(confirm('후기를 삭제하시겠습니까?')){
        		$.ajax({
	           		url : '/vetproject_v2/review/deleteReview.web',
	           		type : 'POST',
	           		data : {
	           			'checkArr' : id+','	/* 다중삭제 컨트롤러에 맞추기 위함 */
	           		},
	    			success : function(data) {
	    				location.href='reviewList.web';
	    			},
	    			error : function(xhr, status, msg) {
	    				console.debug('xhr:\n ' + xhr);
	    				console.debug('status:\n ' + status);
	    				console.debug('msg:\n ' + msg);
	    			}
	    		});      		
        	}
        }
       
    </script>
</head>
<body style="text-align: center; margin: 0 auto; display: inline; padding-top: 100px;">
	<jsp:include page="header.jsp" />
	<form:form commandName="reviewVO" id="detailForm" name="detailForm">
		<input type="hidden" name="selectedId" />
		<input type="hidden" name="rv_id" id="rv_id" value="${reviewVO.rv_id }" />
		<div id="content_pop">
			<!-- 타이틀 -->
			<div id="title_div">
				<ul>
					<li>후기 조회</li>
				</ul>
			</div>
			<!-- // 타이틀 -->
			<div id="table">
				<table width="100%" border="1" cellpadding="0" cellspacing="0"
					style="bordercolor: #D3E2EC; bordercolordark: #FFFFFF; BORDER-TOP: #C2D0DB 2px solid; BORDER-LEFT: #ffffff 1px solid; BORDER-RIGHT: #ffffff 1px solid; BORDER-BOTTOM: #C2D0DB 1px solid; border-collapse: collapse;">
					<colgroup>
						<col width="150" />
						<col width="?" />
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
							<p>
								<fmt:formatDate value="${reviewVO.rv_reg_date }" pattern="yyyy-MM-dd HH:mm" />
							</p>
						</td>
					</tr>
					<tr>
						<td class="tbtd_caption"><label for="reg_date">방문 병원</label></td>
						<td class="tbtd_content">
							<p>${reviewVO.hpt_name}</p>
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
							<p>${reviewVO.hpt_rate}</p>
						</td>
					</tr>
					<tr>
						<td class="tbtd_caption"><label for="visit_date">방문일</label></td>
						<td class="tbtd_content"><fmt:parseDate value="${reviewVO.visit_date}" var="visitDate" pattern="yyyy-M-d" />
							<p>
								<fmt:formatDate value="${visitDate}" pattern="yyyy-MM-dd" />
							</p>
						</td>
					</tr>
					<tr>
						<td class="tbtd_caption"><label for="reg_date">반려동물</label></td>
						<td class="tbtd_content">
							<p>${reviewVO.pet_type}</p>
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
							<img class="content" src="${pageContext.request.contextPath}/images/upload/${reviewVO.rv_image }" alt="${reviewVO.rv_image }" />
							<p class="content">${reviewVO.rv_content }</p>
						</td>
					</tr>
				</table>
			</div>
			
			<!-- 댓글 -->
			<h2 class="cmt">댓글</h2>
			<div id="commentTable">
				<ul>
					<li class="cmtCon">내용</li>
					<li class="cmtReg">날짜</li>
				</ul>
			</div>
			<div id="sysbtn">
				<ul>
					<li>
						<span class="btn_blue_l">
							<a href="javascript:fn_review_list();"><spring:message code="button.list" /></a>
						</span>
					</li>
					<li>
						<span class="btn_blue_l">
							<a href="javascript:fn_review_delete(${reviewVO.rv_id });"><spring:message code="button.delete" /></a>
						</span>
					</li>
				</ul>
			</div>
		</div>
		
		<!-- 검색조건 유지 -->
		<input type="hidden" name="searchCondition" value="<c:out value='${searchVO.searchCondition}'/>" />
		<input type="hidden" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>" />
		<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>" />
	</form:form>
	<jsp:include page="../vetproject/footer.jsp" />
	
	<script>
		/* AJAX로 댓글 목록을 불러 와서 html로 뿌려 준다 */
    	$.ajax({
    		url : 'commentList.do',
    		type : 'get',
    		dataType : 'json',
    		data : {
       			'rv_id' : '${reviewVO.rv_id}'
       		},
			success : function(data) {
				console.log(data);
				
	            var html = "";
	            if(data.length > 0){
	                
	                for(i=0; i<data.length; i++){
	                    html += "<ul>";
	                    html += "<li class='cmtChk'><input type='checkbox' name='cmtChkBox' class='cmtChkBox'  value='" + data[i].cmtId + "'/></li>";
	                    html += "<li class='cmtCon'>" + data[i].cmtContent + "</li>";
	                    html += "<li class='cmtReg'>" + data[i].cmtRegDate + "</li>";
	                    html += "<li class='cmtDel'><button onclick='deleteComment(" + data[i].cmtId + ")'>삭제</button></li>";
	                    html += "</ul>";
	                }
                    html += "<div id='cmtDiv'>";
                    html += "<input type='checkbox' name='selectAllChk' id='selectAllChk'/>";                    
                    html += "<button onclick='selectAllCmt()' id='selectAll' type='button'>전체 댓글 선택</button>";
                    html += "<button onclick='deleteSelectedCmt()' id='deleteSelected' type='button'>선택 댓글 삭제</button>";
                    html += "</div>";
	            } else {
	                html += "<ul>";
	                html += "<li style='text-align: center; width:100%;'>등록된 댓글이 없습니다.</li>";
	                html += "</ul>";
	            }
	            
	            $("#commentTable").html(html); 
				
			},
			error : function(xhr, status, msg) {
				console.debug('xhr:\n ' + xhr);
				console.debug('status:\n ' + status);
				console.debug('msg:\n ' + msg);
			}
    	});
    	
    	/* 댓글을 삭제한다 (1개) */
    	function deleteComment(cmtId){
    		if(confirm('댓글을 삭제하시겠습니까?')){
    			$.ajax({
    				url : 'deleteComment.web',
    				type : 'POST',
    				data : {
    					'cmt_id' : cmtId+','	/* 다중삭제 컨트롤러에 맞추기 위함 */
    				},
    				success : function(data){
    					location.reload();
    					console.log(data);
    				},
    				error : function(xhr, status, msg){
    					console.log('xhr:\n' + xhr);
    					console.log('status:\n' + status);
    					console.log('msg:\n' + msg);
    				}
    			});
    		}
    		return false;
    	}
        
        /* 댓글을 삭제한다 (여러 개) */
        $(document).ready(function(){
        	$(".cmtChkBox").click(function(){
        		$("#selectAllChk").prop("checked", false);
        	});
        	
        });

        /* 전체 선택 버튼 */
       	function selectAllCmt(){
       		var chk = $("#selectAllChk").prop('checked');
       		if(chk) {
       			$(".cmtChkBox").prop("checked", true);
       		} else {
       			$(".cmtChkBox").prop("checked", false);
       		}
       		console.log('클릭');
       		/* $('.cmtChkBox').prop('checked',function(){
       	        return !$(this).prop('checked');
       	    }); */
       	}

        /* 선택 삭제 버튼 */
       	function deleteSelectedCmt(){
       		if(confirm('선택하신 댓글을 삭제하시겠습니까?')){
       			/* checkArr에 체크된 value를 넣는다 */
       			var checkArr = '';
       			$('input[name="cmtChkBox"]:checked').each(function(){
       				checkArr+=$(this).attr('value')+",";
       			});
       			
       			$.ajax({
   	    			url : 'deleteComment.web',
   	    			type : 'POST',
   	    			data : {
   	    				'checkArr' : checkArr
   	    			},
   	    			/* 삭제 성공 시 alert를 띄운다 */
   	    			success : function(data){
   	    				alert(data+'개의 댓글이 삭제되었습니다.');
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
       	}
        
    </script>
</body>
</html>