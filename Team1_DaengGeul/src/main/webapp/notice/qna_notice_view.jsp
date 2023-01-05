<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<link href="css/default.css" rel="stylesheet" type="text/css">
<style type="text/css">
	#articleForm {
		width: 900px;
		height: 750px;
		border: 1px red;
	}
	
	h4 {
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
		height: 550px;
		text-align: center;
		overflow: auto;
		white-space: pre-line;
	}
	
	#commandList {
		margin: auto;
		width: 500px;
		text-align: center;
	}
</style>
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
		<h4>공지사항</h4>
		&nbsp;&nbsp;
		<section id="basicInfoArea">
			<table border="1">
			
			<tr><th width="70">제 목</th><td colspan="3" >${notice.notice_subject }</td></tr>
			<tr>
				<th width="70">작성자</th><td>관리자</td>
				<th width="70">작성일</th>
				<td><fmt:formatDate value="${notice.notice_date }" pattern="yy-MM-dd"/></td>
			</tr>
			<tr>
				<th width="70">첨부파일</th>
				<td><a href="upload/${notice.notice_real_file}" download="${notice.notice_file }">${notice.notice_file}</a></td>
				<th width="70">조회수</th>
				<td>${notice.notice_readcount }</td>
			</tr>
			
			</table>
		</section>
		<section id="articleContentArea">
			${notice.notice_content }
		</section>
	</section>
	<section id="commandList">
	<c:if test="${sessionScope.sId eq 'admin' }">
		<input type="button" value="수정" onclick="location.href='NoticeModifyForm.ad?notice_idx=${param.notice_idx}&pageNum=${param.pageNum }'">&nbsp;&nbsp;
		<input type="button" value="삭제" onclick="location.href='NoticeDelete.ad?notice_idx=${param.notice_idx}&pageNum=${param.pageNum }'">&nbsp;&nbsp;
	</c:if>
		<input type="button" value="목록" onclick="location.href='NoticeList.ad?notice_idx=${ param.notice_idx}&pageNum=${param.pageNum}'">&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</section>
	</div>
	</div>
	<footer>
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
	</footer>
</body>
</html>
















