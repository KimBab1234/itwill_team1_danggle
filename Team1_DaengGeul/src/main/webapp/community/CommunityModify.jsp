<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
		<jsp:include page="/inc/top.jsp"></jsp:include>
		<jsp:include page="../inc/main.jsp"></jsp:include>
	</header>
	<h1>글 수정</h1>
	<form action="CommunityModifyPro.co" method="post"
		enctype="multipart/form-data">
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
				<td><textarea rows="10" cols="30" name="board_content"
						required="required"></textarea></td>
			</tr>
			<tr>
				<td>파일</td>
				<td><input type="file" name="board_file"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="등록">
					&nbsp;&nbsp; <input type="button" value="취소"
					onclick="location.href='./'"> &nbsp;&nbsp; <input
					type="button" value="목록" onclick="location.href='./'"></td>
			</tr>
		</table>
	</form>
	<header>
		<jsp:include page="/inc/bottom.jsp"></jsp:include>
	</header>
</body>
</html>