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
<script type="text/javascript">
$(function() {
	
	if('${review.review_like_done}' =='Y') {
		$("#review_like1").css("color", "blue");
		$("#review_like1").val("Y");
	} else {
		$("#review_like1").css("color", "gray");
		$("#review_like1").val("N");
	}
	
	$("#review_like1").on("click", function() {
		var review_like_done = $(this).val();
		var id = '${sessionScope.sId}';
		if(id=='') {
			alert("로그인 후 이용하세요.");
			return;
		} else {
			$.ajax({
				type: "post",
				url: "ReviewLikeUpdate.re",
				data: {
					review_idx: '${param.review_idx}',
					review_like_done: review_like_done
					},
					success: function(response) {
						$("#like_count").text(response);
						
						if(review_like_done =='Y') {
							$("#review_like1").css("color", "gray");
							$("#review_like1").val("N");
							$("#review_like_done").text("N");
						} else {
							$("#review_like1").css("color", "blue");
							$("#review_like1").val("Y");
							$("#review_like_done").text("Y");
						}
						
					},
					error: function(xhr, textStatus, errorThrown) { 
						// 요청에 대한 처리 실패 시(= 에러 발생 시) 실행되는 이벤트
						$("#resultArea").html("xhr = " + xhr + "<br>textStatus = " + textStatus + "<br>errorThrown = " + errorThrown);
					}
			});
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
	#myform fieldset{
    	display: inline-block; /* 하위 별점 이미지들이 있는 영역만 자리를 차지함.*/
    	direction: rtl; /* 이모지 순서 반전 */
    	border: 0; /* 필드셋 테두리 제거 */
	}
	#myform input[type=radio]{
	    display: none; /* 라디오박스 감춤 */
	}
	#myform fieldset label{
	    font-size: 2em; /* 이모지 크기 */
	    color: transparent; /* 기존 이모지 컬러 제거 */
	    text-shadow: 0 0 0 #ffcc00;
	}
	/* 수정버튼 */
	#s1 {
		background-color: #513e30;
		width: 100px;
		height: 50px;
		color: #fae37d;
		border-radius: 20px;
		border-color: transparent;
		font-weight: bold; 
		font-size: 20px;
	}
	/* 삭제버튼 */
	#s2 {
		background-color: #b38600;
		width: 100px;
		height: 50px;
		color: #fae37d;
		border-radius: 20px;
		border-color: transparent;
		font-weight: bold; 
		font-size: 20px;
	}
	/* 목록버튼 */
	#s3 {
		background-color: #fff5e6;
		width: 100px;
		height: 50px;
		color: #575754;
		border-radius: 20px;
		border-color: transparent;
		font-weight: bold; 
		font-size: 20px;
		
	}
</style>
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
</head>
<body>
	<table>
	<tr>
		<td colspan="3">
			${review.review_content}
		</td>
		<td colspan="2">
			<c:forEach var="i" begin="1" end="${review.review_score}">
			    <label for="rate">⭐</label>
			</c:forEach>
		</td>
		<td colspan="2">
			<button type="button" type="button" class="review_like" id="review_like1">
			<i class='far fa-thumbs-up' style='font-size:28px'></i><div id="like_count">${review.review_like_count}</div></button>
		</td>
	</tr>
	</table>
</body>
</html>









