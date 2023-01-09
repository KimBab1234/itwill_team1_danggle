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
			<td width="50" align="center">조회수</td>
		</tr>
		<c:forEach var="board" items="${Board }">
			<tr>
				<td>${board.member_id }</td>
				<td><a href="CommunityDetail.co?board_idx=${board.board_idx }">${board.board_subject }</a></td>
				<td>${board.board_date }</td>
				<td>${board.board_readcount }</td>
			</tr>
		</c:forEach>
	</table>
	<section id="buttonArea">
		<input type="button" value="메인" onclick="location.href='./'">
		<input type="button" value="회원들의추천목록"
			onclick="location.href='Community0.co?board_type=0'">

		<form action="Community1.co?board_type=1">
			<input type="text" name="keyword"> <input type="submit"
				value="검색"> &nbsp;&nbsp; <input type="button" value="글쓰기"
				onclick="location.href='CommunityWrite1.co'">
		</form>
	</section>
	<section id="pageList">
		<c:choose>
			<c:when test="${pageNum > 1}">
				<input type="button" value="이전"
					onclick="location.href='Community1.co?board_type=1&pageNum=${pageNumm - 1}'">
			</c:when>
			<c:otherwise>
				<input type="button" value="이전">
			</c:otherwise>
		</c:choose>
		<c:forEach var="i" begin="${pageInfo.startPage }"
			end="${pageInfo.endPage }">
			<c:choose>
				<c:when test="${pageNum eq i}">
					${i }
				</c:when>
				<c:otherwise>
					<a href="Community1.co?board_type=1&pageNum=${i }">${i }</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:choose>
			<c:when test="${pageNum < pageInfo.maxPage}">
				<input type="button" value="다음"
					onclick="location.href='Community1.co?board_type=1&pageNum=${pageNum + 1}'">
			</c:when>
			<c:otherwise>
				<input type="button" value="다음">
			</c:otherwise>
		</c:choose>
	</section>
	<header>
		<!-- Login, Join 링크 표시 영역(inc/top.jsp 페이지 삽입) -->
		<jsp:include page="/inc/bottom.jsp"></jsp:include>
	</header>
</body>
</html>















