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
<style type="text/css">
 	h2 { 
 		text-align: left; 
 	} 
	
 	table { 
 		align:left; 
 		width: 600px; 
 		height: 300px;
 		border-color: #b09f76;
 		color:  #575754;
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
	<header>
		<!-- Login, Join링크 표시 영역  -->
		<jsp:include page="../inc/top.jsp"></jsp:include>
		<jsp:include page="../inc/main.jsp"></jsp:include> <!-- 본문1 -->
	</header>
	<br>
	<br>
	<!-- 게시판 상세내용 보기 -->
	<section id="articleForm">
		<h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<b style="border-left: 10px solid #795548">&nbsp;&nbsp;리뷰 상세 조회</b></h2>
		<br>
		<table align = "center">
			<tr>
				<td><img src="img/product/${review1.product_img }" width="200"></td>
				<td><h4>${review1.product_name }</h4></td>
			</tr>
		</table>
		<section id="basicInfoArea">
			<form action="ReviewWritePro.re" name="reviewForm" id="myform">
			<table border="2" align="center">
			<tr>
				<td width="150" height="50" align="center" style="font-weight: bold;">제목</td><td width="800" colspan="1" align="center">${review.review_subject}</td>
				<td width="150" height="50" align="center" style="font-weight: bold;">조회수</td><td align="center">${review.review_readcount}</td>
			</tr>
			<tr>
				<td width="150" height="50" align="center" style="font-weight: bold;">작성자</td><td align="center">${review.member_id }</td>
				<td width="150" height="50" align="center" style="font-weight: bold;">날짜</td><td width="200" align="center"><fmt:formatDate value="${review.review_date }" pattern="yy-MM-dd"/></td>
			</tr>
			<tr>
				<td width="150" height="50" align="center" style="font-weight: bold;">별점</td>
				<td width="800" align="center">
					<fieldset name="review_score" class="score" id="score">
						<c:forEach var="i" begin="1" end="${review.review_score}">
					        <input type="radio" name="review_score" id="rate" checked="checked"><label for="rate">⭐</label>
						</c:forEach>
					</fieldset>
				</td>
				<td width="150" height="50" align="center" style="font-weight: bold;">좋아요</td>
				<td align="center">
					<button type="button" type="button" class="review_like" id="review_like1">
					<i class='far fa-thumbs-up' style='font-size:28px'></i><div id="like_count">${review.review_like_count}</div></button>
				</td>
			</tr>
			<tr>
				<td width="150"height="150" align="center" style="font-weight: bold;">내용</td><td colspan="3" align="center">${review.review_content}</td>
			</tr>
			</table>
			</form>
		</section>
	</section>
	<br>
	<section id="commandList" align="center">
		<input type="button" value="수정" id="s1" onclick="location.href='ReviewModifyForm.re?review_idx=${param.review_idx}&pageNum=${param.pageNum}&product_idx=${review.product_idx }'">
		<input type="button" value="삭제" id="s2" onclick="location.href='ReviewDeleteForm.re?review_idx=${param.review_idx}&pageNum=${param.pageNum}'">
		<input type="button" value="목록" id="s3" onclick="location.href='ReviewList.re?pageNum=${param.pageNum}'">
	</section>
	
<!------------------------------------ 바닥글 --------------------------------------->
	<footer>
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
	</footer>
	<!----------------------------------------------------------------------------------->
	
	<!------------------------------ top, left, bottom 동작 관련 작업 빼지말것! ------------------------------>
	<!-- Back to Top -->
    <a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>

    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>

    <!-- Contact Javascript File -->
    <script src="mail/jqBootstrapValidation.min.js"></script>
    <script src="mail/contact.js"></script>

    <!-- Template Javascript -->
    <script src="js/main.js"></script>
	<!------------------------------ top, left, bottom 동작 관련 작업 빼지말것! ------------------------------>
	
	
</body>
</html>









