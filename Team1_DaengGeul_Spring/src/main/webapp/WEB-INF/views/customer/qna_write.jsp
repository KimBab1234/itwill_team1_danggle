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
<link href="${pageContext.request.contextPath }/resources/css/default.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
<style type="text/css">
	#writeForm {
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
	<!-- 게시판 등록 -->
	<section id="writeForm">
		<h4>1:1 문의</h4>
		<form action="QnaWritePro.cu" name="boardForm" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td class="td_left"><label for="${sessionScope.sId }">글쓴이</label></td>
					<td class="td_right"><input type="text" name="${sessionScope.sId }" placeholder=" ${sessionScope.sId }" /></td>
				</tr>
				
				<tr>
					<td class="td_left"><label for="qna_subject">제목</label></td>
					<td class="td_right"><input type="text" name="qna_subject" required="required" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="qna_content">내용</label></td>
					<td class="td_right">
						<textarea id="board_content" name="qna_content" cols="40" rows="15" required="required"></textarea>
					</td>
				</tr>
				<tr>
					<td class="td_left"><label for="qna_file">파일 첨부</label></td>
					<!-- 파일 첨부 형식은 input 태그의 type="file" 속성 사용 -->
					<td class="td_right">
					<input type="file"  name="qna_file"  /><br>
<!-- 					<input type="file" name="board_file2"  /><br> -->
<!-- 					<input type="file" name="board_file3"  /> -->
					
					</td>
				</tr>
			</table>
			&nbsp;&nbsp;
			<section id="commandCell">
				<input type="submit" id="okBtn"value="등록">&nbsp;&nbsp;
				<input type="reset" id="okBtn"value="다시쓰기">&nbsp;&nbsp;
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








