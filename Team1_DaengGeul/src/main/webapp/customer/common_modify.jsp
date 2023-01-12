<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 게시판</title>
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">
$(function() {
    $('.dropdown-toggle', this).trigger('click').blur();
});
</script>
</script>
<link href="css/default.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">

<style type="text/css">
	#modifyForm {
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
	
	.td_left {
		width: 150px;
		background: #c9b584;
 		text-align: center; 
 		border-collapse: collapse;
		border: 1px solid black;
	}
	
	.td_right {
		width: 300px;
		background: #c9b584;
		border-collapse: collapse;
		border: 1px solid black;
	}
	
	#commandCell {
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
	<!-- 게시판 글 수정 -->
	<section id="modifyForm">
		<h4>문의 글 수정</h4>
		<form action="CommonModifyPro.cu" name="boardForm" method="post">
		<input type="hidden" name="common_idx" value="${common.common_idx}">
					<table>
				<tr>
					<td class="td_left"><label for="sId">관리자</label></td>
					<td class="td_right"><input type="text" name="sId" value="${sessionScope.sId}"></td>
				</tr>
				
				<tr>
					<td class="td_left"><label for="common_subject">제목</label></td>
					<td class="td_right"><input type="text" name="common_subject" value="${common.common_subject}"></td>
				</tr>
				<tr>
					<td class="td_left"><label for="common_content">내용</label></td>
					<td class="td_right"><textarea name="common_content" rows="15" cols="40" required="required">${common.common_content}</textarea></td>
				</tr>
				
			</table>
			&nbsp;&nbsp;
			<section id="commandCell">
				<input type="submit" id="okBtn" value="수정">&nbsp;&nbsp;
				<input type="reset" id="okBtn" value="다시쓰기">&nbsp;&nbsp;
				<input type="button" id="okBtn" value="취소" onclick="history.back()">
			</section>
		</form>
	</section>
	</div>
	</div>
	<footer>
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
	</footer>
</body>
</html>








