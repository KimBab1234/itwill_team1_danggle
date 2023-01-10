<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
<style type="text/css">
* {
   font-family: 'Gowun Dodum', sans-serif;
   url: @import url('https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap');
   }
   
table {
	margin: auto;
	width: 968px;
}
h2 {
	margin: auto;
}

tr {
	align-content: center;
}

h2{
	text-align: center;
}
</style>
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
		$(function() {
			$('.dropdown-toggle', this).trigger('click').blur();
		});
	</script>

	<header>
		<jsp:include page="/inc/top.jsp"></jsp:include>
		<jsp:include page="../inc/main.jsp"></jsp:include>
	</header>
	<hr>
	<div style="width: 500px;" id="c_div">
		<jsp:include page="../inc/community_left.jsp"></jsp:include>
	</div>
	<h2>회원들의 글추천,독후감</h2>
	<br>
	<table border="1" id="com_table">
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
			<td colspan="2">${board.board_date }</td>
		</tr>
		<tr align="center">
			<th colspan="2">추천수 : ${likeCount } <c:if
					test="${not empty sessionScope.sId }">
					<c:choose>
						<c:when test="${duplicateLike == true}">
							<input type="button" value="추천취소"
								onclick="location.href='CommunityLikeDelete.co?board_idx=${board.board_idx}&member_id=${sessionScope.sId }'">
						</c:when>
						<c:otherwise>
							<input type="button" value="추천"
								onclick="location.href='CommuniteLikeBoard.co?board_idx=${board.board_idx }&member_id=${sessionScope.sId}'">
						</c:otherwise>
					</c:choose>
				</c:if>
			</th>
		</tr>
	</table>
	<table id="but_table">
	<tr align="right"><td><input type="button" value="뒤로가기"
		onclick="location.href='Community${board.board_type}.co?board_type=${board.board_type }'">
	&nbsp;&nbsp;
	<c:if test="${sessionScope.sId eq board.member_id}">
		<input type="button" value="글수정"
			onclick="location.href='CommunityModify.co?board_idx=${board.board_idx}&board_type=${board.board_type }'">
	&nbsp;&nbsp;
				<input type="button" value="글삭제" onclick="delete2()">
	</c:if>
	</td>
	</tr>
	</table>
	<div id="rep_wrtie">
		<form action="Community_ReplyPro.co">
			<table>
				<tr>
					<c:choose>
						<c:when test="${not empty sessionScope.sId }">
							<td>${sessionScope.sId }</td>
							<td><input type="text" name="reply_content"
								placeholder="댓글을 작성하세요"></td>
							<td><input type="submit" value="등록"></td>
							<input type="hidden" name="board_type"
								value="${board.board_type }">
						</c:when>
						<c:otherwise>
							<td>로그인하세요</td>
						</c:otherwise>
					</c:choose>
				</tr>
			</table>
			<input type="hidden" name="board_idx" value="${board.board_idx }">
			<input type="hidden" name="member_id" value="${sessionScope.sId }">
		</form>
	</div>
	<table id="rep_table">
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