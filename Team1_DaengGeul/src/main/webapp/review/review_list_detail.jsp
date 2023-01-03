<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- EL에서 표기 방식 (날짜 등)을 변경하려면 fmt(format) 라이브러리 필요 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Review 게시판</title>
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
</head>
<body>
	<!-- 게시판 리스트 -->
	<section id="listForm">
	<h2>Review 게시판 글 목록</h2>
	<table border="1">
		<tr id="tr_top">
			<td width="100">번호</td>
			<td width="200">제목</td>
			<td width="150">작성자</td>
			<td width="150">별점</td>
			<td width="150">날짜</td>
			<td width="150"><i class='far fa-thumbs-up'></i>좋아요</td>
			<td width="150">조회수</td>
		</tr>
			<!-- JSTL 과 EL 활용하여 글목록 표시 작업 반복  -->
		<c:forEach var="review" items="${reviewList }">
			<tr>
				<td>${review.review_idx }</td>
				<!-- 제목 하이퍼링크(BoardDetail.bo) 연결 -->
				<c:choose>
					<c:when test="${empty param.pageNum }">
						<c:set var="pageNum" value="1"></c:set>
					</c:when>
					<c:otherwise>
						<c:set var="pageNum" value="${param.pageNum }"></c:set>
					</c:otherwise>
				</c:choose>
				<td>
					<a href="ReviewDetail.re?review_idx=${review.review_idx }&pageNum=${pageNum}">
					${review.review_subject }
					</a>
				</td>
				<td>${review.member_id }</td>
				<td>${review.review_score }</td>
				<td>
					<!-- JSTL의 fmt 라이브러리 활용해서 날짜 표현 형식 변경 -->
					<fmt:formatDate value="${review.review_date }" pattern="yy-MM-dd"/>
				</td>
				<td>
					<i class='far fa-thumbs-up'></i>${param.review_like}
				</td>
				<td>${review.review_readcount }</td>
			</tr>
		</c:forEach>
			
			
	</table>
	</section>
</body>
</html>