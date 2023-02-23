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
<script>
$(function() {
	
	$(".thB").on("click", function(e) {
		e.stopImmediatePropagation();
		var likeRow = $(this).prev().val();
		var nowLike = $(this);
		var id = '${sessionScope.sId}';
		if(id=='') {
			alert("로그인 후 이용하세요.");
		} else {
			$.ajax({
				type: "post",
				url: "ReviewLikeUpdate",
				data: {
					review_idx: likeRow,
					review_like_done: nowLike.val()
					},
					success: function(response) {
						$("#like_count"+likeRow).text(response);
						if(nowLike.val() =='Y') {
							$("#review_like_th"+likeRow).css("color", "gray");
							nowLike.val("N");
						} else {
							$("#review_like_th"+likeRow).css("color", "blue");
							nowLike.val("Y");
						}
						
					},
					error: function(xhr, textStatus, errorThrown) { 
						// 요청에 대한 처리 실패 시(= 에러 발생 시) 실행되는 이벤트
						$("#resultArea").html("xhr = " + xhr + "<br>textStatus = " + textStatus + "<br>errorThrown = " + errorThrown);
					}
			});
		}
	});
	
	
	$(".thB").on("mouseover", function(e) {
		var nowLikeYN = $(this).val();
		if(nowLikeYN=='Y') {
			$(this).children("i").css("color","gray");
		} else {
			$(this).children("i").css("color","blue");
		}
	});
	
	$(".thB").on("mouseout", function(e) {
		var nowLikeYN = $(this).val();
		if(nowLikeYN=='Y') {
			$(this).children("i").css("color","blue");
		} else {
			$(this).children("i").css("color","gray");
		}
	});
	
	
	
	
	
});


</script>
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
	<div id="reviewAll" style="display: flex;  display:table;">
		<div align="center" style="width: 500px; display:table-cell; vertical-align:middle;">
			${review.review_content}
		</div>
		<div align="center" style="width: 300px; display:table-cell; vertical-align:middle;">
			<fieldset name="review_score" class="score" id="score">
				<c:forEach var="i" begin="1" end="${review.review_score}">
			       <i class="fas fa-solid fa-star" style="color: #ffcc00;"></i>
				</c:forEach>
			</fieldset>
		</div>
		<div align="center" style="width: 200px; display:table-cell; vertical-align:middle;">
			<input type="hidden" value="${review.review_idx }">
			<c:choose>
				<c:when test="${review.review_like_done.equals('Y')}">
					<button type="button" class="thB" id="${review.review_idx }" style="border: none; background: none;" value="${review.review_like_done}">
					<i class='far fa-thumbs-up' id="review_like_th${review.review_idx }" style='font-size:28px; color: blue;'></i><div id="like_count${review.review_idx }">${review.review_like_count}</div></button>
				</c:when>
				<c:otherwise>
					<button type="button" class="thB" id="${review.review_idx }" style="border: none; background: none;" value="${review.review_like_done}">
					<i class='far fa-thumbs-up' id="review_like_th${review.review_idx }" style='font-size:28px; color: gray;'></i><div id="like_count${review.review_idx }">${review.review_like_count}</div></button>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>









