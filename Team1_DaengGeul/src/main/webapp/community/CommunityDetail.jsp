<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<link href="css/default.css" rel="stylesheet" type="text/css">
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

#articleContentArea {
	background:;
	margin-top: 20px;
	height: 550px;
	text-align: center;
	overflow: auto;
	white-space: pre-line;
}

table {
	margin: 0 auto;
	border-collapse: collapse;
	/* 	border-style: solid; */
	border-color: #b09f76;
	width: 600px;
}

#reply_table {
	margin: auto;
	width: 1000px;
}

#reply {
	margin: auto;
	width: 1000px;
}

#id_sId {
	margin-left: -190px;
}

#submit {
	margin-right: -50px;
}

h2 {
	margin: auto;
	text-align: center;
}

tr {
	align-content: center;
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
</style>
</head>
<body>
	<script type="text/javascript">
		function delete2() {
			var result = confirm("글을 지우시겠습니까?");
			if (result) {
				location.href = "Community_DeletePro.co?board_idx=${board.board_idx }&board_real_file=${board.board_real_file}&board_type=${board.board_type }";
			} else {
				location.href = "CommunityDetail.co?board_idx=${board.board_idx }";
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
	<h2>
		<img src="img/re.gif">&nbsp;&nbsp;회원들의 글추천,독후감&nbsp;&nbsp;<img
			src="img/re.gif">
	</h2>
	<br>
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
			<td>파일</td>
			<td colspan="2"><a href="${board.board_file }" download>${board.board_file }</a></td>
		</tr>
		<tr align="center">
			<td>조회수 : ${board.board_readcount}</td>
			<td colspan="2">${board.board_date }</td>
		</tr>
		<tr align="center">
			<th colspan="2">추천수 : ${likeCount } &nbsp;&nbsp;<c:if
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
	<section id="articleContentArea">${board.board_content }</section>
	<table id="but_table">
		<tr align="right">

			<td><c:choose>
					<c:when test="${not empty param.pageNum }">
						<input type="button" value="목록"
							onclick="location.href='Community${board.board_type}.co?board_type=${board.board_type }&pageNum=${param.pageNum }'">
					</c:when>
					<c:otherwise>
						<input type="button" value="목록"
							onclick="location.href='Community${board.board_type}.co?board_type=${board.board_type }&pageNum=1'">
					</c:otherwise>
				</c:choose> &nbsp;&nbsp; <c:if test="${sessionScope.sId eq board.member_id}">

					<input type="button" value="글수정"
						onclick="location.href='CommunityModify.co?board_idx=${board.board_idx}&board_type=${board.board_type }'">
	&nbsp;
				<input type="button" value="글삭제" onclick="delete2()">
				</c:if></td>
		</tr>
	</table>
	<hr>
	<div id="rep_wrtie"></div>
	<form action="Community_ReplyPro.co">
		<table id="reply_table">
			<c:choose>
				<c:when test="${not empty sessionScope.sId }">
					<td>${sessionScope.sId }</td>
					<td><input type="text" name="reply_content"
						placeholder="댓글을 작성하세요" required="required"
						style="border: 0 solid black;" size="75"></td>

					<td id="submit"><input type="submit" value="등록"></td>
					<input type="hidden" name="board_type" value="${board.board_type }">
					<input type="hidden" name="board_idx" value="${board.board_idx }">
					<input type="hidden" name="member_id" value="${sessionScope.sId }">
				</c:when>
				<c:otherwise>
					<td><a href="MemberLoginForm.me">로그인이 필요합니다</a></td>
				</c:otherwise>
			</c:choose>
			<tr>
				<th>
			<br>
				</th>
			</tr>
			<c:forEach var="reply" items="${replyList }">
				<tr align="center">
					<c:choose>
						<c:when test="${reply.member_id eq 'admin' }">
							<td width="100"><img src="img/re.gif">${reply.member_id }&nbsp;&nbsp;&nbsp;</td>
						</c:when>
						<c:otherwise>
							<td><img src="img/dot_Acon.gif">${reply.member_id }&nbsp;&nbsp;&nbsp;</td>
						</c:otherwise>
					</c:choose>
					<td>${reply.reply_content }</td>
					<td>${reply.date }</td>
					<td>${reply.reply_likeduplicate }</td>
					<td>${reply.reply_likecount }&nbsp;&nbsp;<c:choose>
							<c:when test="${reply.reply_likeduplicate lt 0}">
								<input type="button" value="추천취소"
									onclick="location.href='ReplyLikeDelete.co?board_idx=${board.board_idx}&member_id=${sessionScope.sId }&reply_idx=${reply.reply_idx }'">
							</c:when>
							<c:otherwise>
								<input type="button" value="추천"
									onclick="location.href='ReplyLike.co?board_idx=${board.board_idx}&member_id=${sessionScope.sId }&reply_idx=${reply.reply_idx }'">
							</c:otherwise>
						</c:choose>
					</td>
					<c:if test="${reply.member_id eq sessionScope.sId || sessionScope.sId eq 'admin'}">
				&nbsp;&nbsp;	<td><input type="button" value="댓글삭제"
							onclick="location.href='CommunityReplyDeletePro.co?board_idx=${board.board_idx}&reply_idx=${reply.reply_idx }'"></td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
	</form>
	<footer>
		<jsp:include page="/inc/bottom.jsp"></jsp:include>
	</footer>
</body>
</html>