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
	});
});
</script>

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
		<h2 align="center">리뷰 상세내용 보기</h2>
		<section id="basicInfoArea">
			<form action="ReviewWritePro.re" name="reviewForm" id="myform">
			<table border="1" align="center">
			<tr>
				<th width="150" height="50">제목</th><td width="800" colspan="1" align="center">${review.review_subject}</td>
				<th width="150" height="50">조회수</th><td align="center">${review.review_readcount}</td>
			</tr>
			<tr>
				<th width="150" height="50">작성자</th><td align="center">${review.member_id }</td>
				<th width="150" height="50">날짜</th><td width="200" align="center"><fmt:formatDate value="${review.review_date }" pattern="yy-MM-dd"/></td>
			</tr>
			<tr>
				<th width="150" height="50">별점</th>
				<td width="800">
					<fieldset name="review_score" class="score" id="score">
						<c:forEach var="i" begin="1" end="${review.review_score}">
					        <input type="radio" name="review_score" id="rate" checked="checked"><label for="rate">?</label>
						</c:forEach>
					</fieldset>
				</td>
				<th width="150" height="50">좋아요</th>
				<td align="center">
					<button type="button" type="button" class="review_like" id="review_like1">
					<i class='far fa-thumbs-up' style='font-size:28px'></i><div id="like_count">${review.review_like_count}</div></button>
				</td>
			</tr>
			<tr>
				<th width="150"height="150">내용</th><td colspan="3" align="center">${review.review_content}</td>
			</tr>
			</table>
			</form>
		</section>
	</section>
	<section id="commandList" align="center">
		<input type="button" value="수정" onclick="location.href='ReviewModifyForm.re?review_idx=${param.review_idx}&pageNum=${param.pageNum}'">
		<input type="button" value="삭제" onclick="location.href='ReviewDeleteForm.re?review_idx=${param.review_idx}&pageNum=${param.pageNum}'">
		<input type="button" value="목록" onclick="location.href='ReviewList.re?pageNum=${param.pageNum}'">
	</section>
</body>
</html>










