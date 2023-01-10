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
	<script type="text/javascript">
		function delete2() {
			var result = confirm("글을 지우시겠습니까?");
			if (result) {
				location.href = "Community_DeletePro.co?board_idx=${board.board_idx }&board_real_file=${board.board_real_file}&board_type=${board.board_type }";
			} else {
				history.back();
			}
		}
	</script>
	<header>
		<jsp:include page="/inc/top.jsp"></jsp:include>
		<jsp:include page="../inc/main.jsp"></jsp:include>
	</header>
	<h1>글</h1>
	<table border="1">
		<tr align="center">
			<td>글제목</td>
			<td colspan="2">${board.board_subject }</td>
		</tr>
		<tr align="center">
			<td>작성자</td>
			<td colspan="2">${board.member_id }</td>
		</tr>
		<tr align="center">
			<td>내용</td>
			<td width="500" height="500" colspan="2">${board.board_content }</td>
		</tr>
		<tr align="center">
			<td>파일</td>
			<td colspan="2">${board.board_file }</td>
		</tr>
		<tr align="center">
			<td>조회수 : ${board.board_readcount}</td>
			<td colspan="1">${board.board_date }</td>
		</tr>
		<tr>
			<c:if test="${not empty sessionScope.sId }">
				<c:choose>
					<c:when test="${duplicateLike == true}">
						<th><input type="button" value="추천취소"
							onclick="location.href='CommunityLikeDelete.co?board_idx=${board.board_idx}&member_id=${sessionScope.sId }'"></th>
					</c:when>
					<c:otherwise>
						<th><input type="button" value="추천"
							onclick="location.href='CommuniteLikeBoard.co?board_idx=${board.board_idx }&member_id=${sessionScope.sId}'">
						</th>
					</c:otherwise>
				</c:choose>
			</c:if>
			<th>추천갯수 : ${likeCount }</th>
		</tr>
		<tr>
			<td colspan="3"><input type="button" value="뒤로가기"
				onclick="location.href='Community${board.board_type}.co?board_type=${board.board_type }'">
				&nbsp;&nbsp; <input type="button" value="홈페이지"
				onclick="location.href='./'"> &nbsp;&nbsp; <c:if
					test="${sessionScope.sId eq board.member_id}">
					<input type="button" value="글삭제" onclick="delete2()">&nbsp;&nbsp;
				<input type="button" value="글수정"
						onclick="location.href='CommunityModify.co?board_idx=${board.board_idx}&board_type=${board.board_type }'">
				</c:if></td>
		</tr>

	</table>
	<form action="Community_ReplyPro.co">
		<c:choose>
			<c:when test="${not empty sessionScope.sId }">
		${sessionScope.sId } 	| <input type="text" name="reply_content"
					placeholder="댓글을 작성하세요">
				<input type="submit" value="등록">
				<input type="hidden" name="board_type" value="${board.board_type }">
			</c:when>
			<c:otherwise>
			로그인하세요
			</c:otherwise>
		</c:choose>
		| <input type="hidden" name="board_idx" value="${board.board_idx }">
		<input type="hidden" name="member_id" value="${sessionScope.sId }">
	</form>

	<table>
		<c:forEach var="reply" items="${replyList }">
			<tr align="center">
				<td width="80">${reply.member_id }&nbsp;&nbsp;&nbsp;</td>
				<td width="400">${reply.reply_content }</td>
				<td>${reply.date }</td>
				<c:if test="${reply.member_id eq sessionScope.sId }">
					<td><input type="button" value="댓글삭제"
						onclick="location.href='CommunityReplyDeletePro.co?board_idx=${board.board_idx}&reply_idx=${reply.reply_idx }'"></td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
	<header>
		<jsp:include page="/inc/bottom.jsp"></jsp:include>
	</header>
</body>
</html>