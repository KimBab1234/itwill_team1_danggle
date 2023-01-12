<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자주 묻는 질문</title>
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">
$(function() {
    $('.dropdown-toggle', this).trigger('click').blur();
});
</script>
<link href="css/default.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
<style>
* {
   font-family: 'Gowun Dodum', sans-serif;
   url: @import url('https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap');
   }
	#passForm {
		width: 300px;
		margin: auto;
		border: 1px ;
		text-align: center;
	}
	
	h2 {
		text-align: center;
	}
	
	table {
		width: 300px;
		margin: auto;
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
	<!-- 게시판 글 삭제 -->
	<h2>자주 묻는 질문 삭제</h2>
	<section id="passForm">
		<form action="CommonDeletePro.cu" name="deleteForm" method="post">
		<input type="hidden" name="common_idx" value="${param.common_idx}">
<%-- 		<input type="text" name="sId" value="${sessionScope.sId}"> --%>
		
			<table>
				<tr>
				
					<td colspan="2">
						<input type="submit" id="okBtn" value="삭제" >&nbsp;&nbsp;
						<input type="button" id="okBtn" value="돌아가기" onclick="javascript:history.back()">
					</td>
				
				</tr>
			</table>
		</form>
	</section>
	</div>
	</div>
	<footer>
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
	</footer>
</body>
</html>





