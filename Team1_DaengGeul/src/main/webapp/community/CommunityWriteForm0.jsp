<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<header>
		<jsp:include page="/inc/top.jsp"></jsp:include>
	</header>
	<h1>회원들의 추천목록 작성</h1>
	<form action="CommunityWritePro.co" method="post" enctype="multipart/form-data">
	<input type="hidden" name="board_type" value="0">
	<input type="hidden" name="member_id" value=${sessionScope.sId }>
	<table border="1">
		<tr>
			<td>글제목</td>
			<td><input type="text" name="board_subject" width="50"></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${sessionScope.sId }</td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea rows="30" cols="50" name="board_content"></textarea></td>
		</tr>
		<tr>
			<td>파일</td>
			<td><input type="file" name="board_file"></td>
		</tr>
		<tr>
			<td colspan="2">
			<input type="submit" value="등록"> &nbsp;&nbsp;
<!-- 			<input type="button" value="취소" onclick="location.href='./'"> &nbsp;&nbsp; -->
				<input type="button" value="뒤로가기"
				onclick="location.href='Community${board.board_type}.co?board_type=${board.board_type }'">
			<input type="button" value="목록" onclick="location.href='./'">
			</td>
		</tr>
	</table>
	</form>
		<header>
		<jsp:include page="/inc/bottom.jsp"></jsp:include>
	</header>
</body>
</html>