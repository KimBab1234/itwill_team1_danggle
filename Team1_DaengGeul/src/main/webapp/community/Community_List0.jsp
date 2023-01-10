<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src ="https://code.jquery.com/jquery-3.6.3.js"></script>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
<script type="text/javascript">
		$(function() {
			$('.dropdown-toggle', this).trigger('click').blur();
		});
	</script>
<style type="text/css">
* {
   font-family: 'Gowun Dodum', sans-serif;
   url: @import url('https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap');
   }
#listForm {
	width: 1024px;
	max-height: 610px;
	margin: auto;
}

h2 {
	text-align: center;
}

table {
	margin: auto;
	width: 1024px;
}

#tr_top {
	background: #b09f76;
	text-align: center;
}

table td {
	text-align: center;
}

#subject {
	text-align: left;
	padding-left: 20px;
}

#pageList {
	margin: auto;
	width: 1024px;
	text-align: center;
}

#emptyArea {
	margin: auto;
	width: 1024px;
	text-align: center;
}

#buttonArea {
	margin: auto;
	width: 1024px;
	text-align: right;
	margin-top: 10px;
}

a {
	text-decoration: none;
}
</style>
</head>
<body>
	<div>
	<header>
		<jsp:include page="/inc/top.jsp"></jsp:include>
		<jsp:include page="../inc/main.jsp"></jsp:include>
	</header>
	<hr>
	<div style="width: 500px;" id="c_div">
		<jsp:include page="../inc/community_left.jsp"></jsp:include>
	</div>
	<h2>회원들의 추천 목록</h2>
	<table>
		<tr id="tr_top">
			<td width="100">이름</td>
			<td width="200">제목</td>
			<td width="120">날짜</td>
			<td width="50" align="center">조회수</td>
		</tr>

		<c:choose>
			<c:when test="${empty param.pageNum }">
				<c:set var="pageNum" value="1"></c:set>
			</c:when>
			<c:otherwise>
				<c:set var="pageNum" value="${param.pageNum }"></c:set>
			</c:otherwise>
		</c:choose>

		<c:forEach var="board" items="${Board }">
			<tr>
				<td>${board.member_id }</td>
				<td><a href="CommunityDetail.co?board_idx=${board.board_idx }">${board.board_subject }</a></td>
				<td>${board.board_date }</td>
				<td>${board.board_readcount }</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<section id="buttonArea">
		<form action="Community0.co?board_type=0">
			<input type="text" name="keyword"><input type="hidden"
				name="board_type" value="0"> <input type="submit" value="검색"> <input type="button" value="글쓰기"
			onclick="location.href='CommunityWrite0.co'">
		</form>
	</section>

	<section id="pageList">
		<c:choose>
			<c:when test="${pageNum > 1}">
				<input type="button" value="이전"
					onclick="location.href='Community0.co?board_type=0&pageNum=${pageNum - 1}'">
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
					<a href="Community0.co?board_type=0&pageNum=${i }">${i }</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>


		<c:choose>
			<c:when test="${pageNum < pageInfo.maxPage}">
				<input type="button" value="다음"
					onclick="location.href='Community0.co?board_type=0&pageNum=${pageNum + 1}'">
			</c:when>
			<c:otherwise>
				<input type="button" value="다음">
			</c:otherwise>
		</c:choose>
	</section>


	<header>
		<jsp:include page="/inc/bottom.jsp"></jsp:include>
	</header>
</div>
</body>
</html>