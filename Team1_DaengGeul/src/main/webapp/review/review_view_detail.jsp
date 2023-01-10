<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Review 게시판</title>
<!-- <link href="review/css/review_view.css" rel="stylesheet" type="text/css"> -->
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script src ="js/product_detail_review.js"></script>
<style type="text/css">
	/* 좋아요버튼 */
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
	/* 별점 */
	.rate {
		align: center;
	}
	fieldset{
    	display: inline-block; /* 하위 별점 이미지들이 있는 영역만 자리를 차지함.*/
    	direction: rtl; /* 이모지 순서 반전 */
    	border: 0; /* 필드셋 테두리 제거 */
	}
	input[type=radio]{
	    display: none; /* 라디오박스 감춤 */
	}
	fieldset label{
	    font-size: 2em; /* 이모지 크기 */
	    color: transparent; /* 기존 이모지 컬러 제거 */
	    text-shadow: 0 0 0 #ffcc00;
	    background: none;
	}
	
</style>
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
</head>
<body>
		${review.review_content}
		<fieldset name="review_score" class="score" id="score">
			<c:forEach var="i" begin="1" end="${review.review_score}">
		       <i class="fas fa-solid fa-star" style="color: #ffcc00;"></i>
			</c:forEach>
		</fieldset>
		<input type="hidden" value="${review.review_idx }">
		<button type="button" type="button" id="${review.review_idx }" style="border: none; background: none;">
		<i class='far fa-thumbs-up' id="review_like_th${review.review_idx }" style='font-size:28px'></i><div class="like_count">${review.review_like_count}</div></button>
</body>
</html>









