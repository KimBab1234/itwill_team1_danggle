<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap"
	rel="stylesheet">
<style type="text/css">
* {
	font-family: 'Gowun Dodum', sans-serif;
	url:
	@import
	url('https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap')
	;
}

#writeForm {
	width: 900px;
	height: 750px;
	border: 1px red;
}

h2 {
	margin : auto;
	text-align: center;
	width: 
}

table {
	padding : 
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
		<jsp:include page="/inc/top.jsp"></jsp:include>
		<jsp:include page="../inc/main.jsp"></jsp:include>
	</header>
	<hr>
		<div style="display: flex;">
	<div style="width: 500px;" id="c_div">
		<jsp:include page="../inc/community_left.jsp"></jsp:include>
	</div>
	<h2 style="height: 30px;">
		<img src="img/re.gif">&nbsp;&nbsp;글 수정&nbsp;&nbsp;<img
			src="img/re.gif">
	</h2>
	<form action="CommunityModifyPro.co" method="post"
		enctype="multipart/form-data" id="form">
		<input type="hidden" name="board_idx" value="${param.board_idx }">
		<input type="hidden" name="board_type" value="${param.board_type }">
		<input type="hidden" name="member_id" value=${sessionScope.sId }>
		<table border="1">
			<tr>
				<td>글제목</td>
				<td><input type="text" name="board_subject" width="50"
					required="required"></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${sessionScope.sId }</td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea rows="10" cols="74" name="board_content"
						required="required"></textarea></td>
			</tr>
			<tr>
				<td>파일</td>
				<td><input type="file" name="board_file"></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" value="등록">
					&nbsp;&nbsp; <input type="button" value="취소"
					onclick="location.href='./'"> &nbsp;&nbsp;</td>
			</tr>
		</table>
	</form>
	</div>
	<footer>
		<jsp:include page="/inc/bottom.jsp"></jsp:include>
	</footer>
	
</body>
</html>