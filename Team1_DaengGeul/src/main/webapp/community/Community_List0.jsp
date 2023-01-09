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
	</header>
	<h1>회원들의 추천 목록</h1>
	<table>
		<tr>
			<td width="100">이름</td>
			<td width="200">제목</td>
			<td>날짜</td>

		</tr>
		<c:forEach var="board" items="${Board }">
			<tr>
				<td>${board.member_id }</td>
				<td><a href="CommunityDetail.co?board_idx=${board.board_idx }">${board.board_subject }</a></td>
				<td>${board.board_date }</td>
			</tr>
		</c:forEach>
	</table>
	<input type="button" value="메인" onclick="location.href='./'">
	<input type="button" value="글쓰기"
		onclick="location.href='CommunityWrite0.co'">
	<input type="button" value="독후감목록"
		onclick="location.href='Community1.co?board_type=1'">

	<header>
		<jsp:include page="/inc/bottom.jsp"></jsp:include>
	</header>
</body>
</html>