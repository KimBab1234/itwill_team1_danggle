<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Review 게시판</title>
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>

<!-- 외부 css 가져오기 -->
<style type="text/css">
 	h1 { 
 		text-align: left; 
 	} 
	
 	table { 
 		align:left; 
 		width: 500px; 
 		height: 400px; 
 	} 
	
 	.d1 { 
 	text-align:left; 
 	background-color: #e6e6e6; 
 	width: 700px; 
 	} 
	
 	.a1 { 
 	background-color:  #eeec93; 
 	} 
 	.a2 { 
 	background-color:  #eeec93; 
 	} 

 	.td_left { 
 		width: 150px; 
 		background: #c0c0c0; 
 		text-align: center; 
 	} 
	
 	.td_right { 
 		width: 500px; 
 	} 
	
 	.r1 { 
 		width: 300px; 
 	} 
	
	.review_like {
	  	color: gray;
	}
	.review_like:hover {
		color: blue;
	}
	.review_like_done {
	  	color: blue;	
	}
	.review_like_done:hover {
	  	color: gray;
	}
	
</style>
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
</head>
<body>
	<header>
		<!-- Login, Join링크 표시 영역  -->
		<jsp:include page="../inc/top.jsp"></jsp:include>
	</header>
	<!-- 게시판 상세내용 보기 -->
	<section id="articleForm">
		<h2>리뷰 상세내용 보기</h2>
		<section id="basicInfoArea">
		
			<table border="1">
			<tr>
				<th width="70" height="50">제목</th><td colspan="1" >${review.review_subject}</td>
				<th width="70" height="50">조회수</th><td>${review.review_readcount}</td>
			</tr>
			<tr>
				<th width="70" height="30">작성자</th><td>${review.member_id }</td>
				<th width="70" height="30">날짜</th><td><fmt:formatDate value="${review.review_date }" pattern="yy-MM-dd"/></td>
			</tr>
			<tr>
				<th width="70" height="20">별점</th><td>${review.review_score}</td>
				<th width="70" height="20">좋아요</th><td>${review.review_like_done}</td>
				<td>
					<c:choose>
						<c:when test="${review.review_like_done eq 'Y'}">
							<button type="button" class="review_like_done" style="color:blue"
							onclick="location.href='ReviewLikeUpdate.re?review_idx=${param.review_idx}&member_id=${sessionScope.sId}&pageNum=${param.pageNum}&review_like_done=${review.review_like_done}'">
							<i class='far fa-thumbs-up' style='font-size:28px'></i></button><div class="like_count"></div>
						</c:when>
						<c:otherwise>
							<button type="button" class="review_like" style="color:gray"
							onclick="location.href='ReviewLikeUpdate.re?review_idx=${param.review_idx}&member_id=${sessionScope.sId}&pageNum=${param.pageNum}&review_like_done=${review.review_like_done}'">
							<i class='far fa-thumbs-up' style='font-size:28px' ></i></button><div class="like_count"></div>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<th width="70"height="120">내용</th><td colspan="3">${review.review_content}</td>
			</tr>
			</table>
		</section>
	</section>
	<section id="commandList">
		<input type="button" value="수정" onclick="location.href='ReviewModifyForm.re?review_idx=${param.review_idx}&pageNum=${param.pageNum}'">
		<input type="button" value="삭제" onclick="location.href='ReviewDeleteForm.re?review_idx=${param.review_idx}&pageNum=${param.pageNum}'">
		<input type="button" value="목록" onclick="location.href='ReviewList.re?pageNum=${param.pageNum}'">
	</section>
</body>
</html>








