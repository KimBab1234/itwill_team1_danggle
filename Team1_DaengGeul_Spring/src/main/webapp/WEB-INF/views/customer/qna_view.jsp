<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의사항</title>
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">
$(function() {
    $('.dropdown-toggle', this).trigger('click').blur();
});
</script>
<link href="${pageContext.request.contextPath }/resources/css/default.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">

<style type="text/css">
	#articleForm {
		width: 900px;
		height: 750px;
		border: 1px red;
	}
	
	h4 {
		 font-family: 'Gowun Dodum', sans-serif;
   url: @import url('https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap');
		text-align: center;
	}
	
	table {
		margin: 0 auto;
		border-collapse: collapse;
		border-style: solid;
		border-color: #b09f76;
		width: 600px;
	}
	
	th {
		text-align: center;
	}
	
	td {
		width: 150px;
		text-align: center;
	}
	
	#basicInfoArea {
		height: 70px;
		text-align: center;
	}
	
	#articleContentArea {
		background: ;
		margin-top: 20px;
		height: 350px;
		text-align: center;
		overflow: auto;
		white-space: pre-line;
	}
	
	#commandList {
		margin: auto;
		width: 500px;
		text-align: center;
	}
	#okBtn {
	background-color: #736643;
	border: none;
	cursor: pointer;
	color: #fff;
	height: 30px;
	width: 75px;
	border-radius: 4px;
	margin-top: 50px;
}
</style>
<script type="text/javascript">//답글을 달려고 하는 원본 글, 답글 작성 후 돌아갈 페이지넘버
	function changeView(value) {
		if(value == 0) location.href="QnaListAction.cu?pageNum=${param.pageNum}";
		else if(value == 1){
			location.href="QnaReplyForm.cu?qna_idx=${qna.qna_idx}+'&'+pageNum=${param.pageNum}";
		}
	}
</script>
</head>
<body>
<header>
		<!-- Login, Join 링크 표시 영역 -->
		<jsp:include page="../inc/top.jsp"></jsp:include>
		<jsp:include page="../inc/main.jsp"></jsp:include>
	</header>
	<hr>
	<div style="width: 1920px; display: flex; margin-left: 10px; min-height: 500px;">
		<div style="width: 500px;">
			<jsp:include page="../inc/customer_left.jsp"></jsp:include>
        </div>
	<!-- 왼쪽 메뉴바 세트 끝 -->
	
	<!-- 상단 이미지, 큰 정보 감싸는 곳 -->
		<div style="width: 1000px; margin-left: 20px; ">
	<!-- 게시판 상세내용 보기 -->
	<section id="articleForm">
		<h4>문의 내용 보기</h4>
		&nbsp;&nbsp;
		<section id="basicInfoArea">
			<table border="1">
			
			<tr><th width="70">제 목</th><td colspan="3" >${qna.qna_subject }</td></tr>
			<tr>
				<th width="70">작성자</th><td>${qna.member_id}</td>
				<th width="70">작성일</th>
				<td><fmt:formatDate value="${qna.qna_date }" pattern="yy-MM-dd"/></td>
			</tr>
			<tr>
				<th width="70">첨부파일</th>
				<td><a href="upload/${qna.qna_original_file}" download="${qna.qna_file }">${qna.qna_file}</a></td>
				
			</tr>
			
			</table>
		</section>
		<section id="articleContentArea">
			${qna.qna_content }
		</section>
	</section>
	<section id="commandList">
	<c:if test="${sessionScope.sId == 'admin'}">
		<input type="button" id="okBtn" value="답변" onclick="location.href='QnaReplyForm.cu?qna_idx=${qna.qna_idx}&pageNum=${param.pageNum }'">&nbsp;&nbsp;
	</c:if>
	<c:if test="${sessionScope.sId == qna.member_id || sessionScope.sId == 'admin'}">
		<input type="button" id="okBtn" value="수정" onclick="location.href='QnaModifyForm.cu?qna_idx=${qna.qna_idx}&pageNum=${param.pageNum }'">&nbsp;&nbsp;
		<input type="button" id="okBtn" value="삭제" onclick="location.href='QnaDeleteForm.cu?qna_idx=${qna.qna_idx}&pageNum=${param.pageNum }'">&nbsp;&nbsp;	
		<input type="button" id="okBtn" value="목록" onclick="location.href='QnaList.cu?qna_idx=${qna.qna_idx}&pageNum=${param.pageNum}'">&nbsp;&nbsp;
	</c:if>	
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</section>
	</div>
	</div>
	<footer>
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
	</footer>
</body>
</html>
















