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
		<!-- Login, Join 링크 표시 영역(inc/top.jsp 페이지 삽입) -->
		<jsp:include page="/inc/top.jsp"></jsp:include>
	</header>
	<h1>독후감 목록</h1>
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
	<input type="button" value="메인" onclick="location.href='./'" >
	<input type="button" value="글쓰기" onclick="location.href='CommunityWrite1.co'">
	<input type="button" value="회원들의추천목록" onclick="location.href='Community0.co?board_type=0'">
		<header>
		<!-- Login, Join 링크 표시 영역(inc/top.jsp 페이지 삽입) -->
		<jsp:include page="/inc/bottom.jsp"></jsp:include>
	</header>
</body>
</html>