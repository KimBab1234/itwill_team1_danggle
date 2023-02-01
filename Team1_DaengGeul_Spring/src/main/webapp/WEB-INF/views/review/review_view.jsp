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
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
<script type="text/javascript">

// 아이디 누르고 밑에 안 나오게
$(function() {
    $('.dropdown-toggle', this).trigger('click').blur();
});

$(function() {
	
	if('${review.review_like_done}' =='Y') {
		$("#review_like1").children("i").css("color", "blue");
		$("#review_like1").val("Y");
	} else {
		$("#review_like1").children("i").css("color", "gray");
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
						$("#review_like1").children("i").css("color", "gray");
						$("#review_like1").val("N");
						$("#review_like_done").text("N");
					} else {
						$("#review_like1").children("i").css("color", "blue");
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
	
	$(".review_like").on("mouseover", function() {
		var nowLikeYN = $("#review_like1").val();
		if(nowLikeYN=='Y') {
			$(this).children("i").css("color","gray");
		} else {
			$(this).children("i").css("color","blue");
		}
	});
	
	$(".review_like").on("mouseout", function() {
		var nowLikeYN = $("#review_like1").val();
		if(nowLikeYN=='Y') {
			$(this).children("i").css("color","blue");
		} else {
			$(this).children("i").css("color","gray");
		}
	});
	
	
	
});
</script>
<style type="text/css">
	* {
	font-family: 'Gowun Dodum', sans-serif;
	url: @import url('https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap');
	}
	
 	table { 
 		width: 600px; 
 		height: 300px;
 		border-color: #b09f76;
 		color:  #575754;
 		font-weight: bold;
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
		background-color: #fff5e6;
		width: 100px;
		height: 50px;
		color: #575754;
		border-radius: 20px;
		border-color: transparent;
		font-weight: bold; 
		font-size: 20px;
	}
	/* 삭제버튼 */
	#s2 {
		background-color: #fff5e6;
		width: 100px;
		height: 50px;
		color: #575754;
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
	<div style="display: flex; width: 1800px; margin-left: 50px;">
	<div align="left" style="width: 300px; margin-top: 100px;">
		<jsp:include page="../inc/memberInfo_left.jsp"></jsp:include> <!-- 본문1 -->
	</div>
	<br>
	<br>
	<!-- 게시판 상세내용 보기 -->
	<section id="articleForm">
		<div align="center" style="width: 700px">
		<h2>
		<b style="border-left: 10px solid #795548">&nbsp;&nbsp;리뷰 상세 조회</b></h2></div>
		<br>
		<div align="center" style="width: 1100px">
		<table>
			<tr>
				<td><img src="http://itwillbs3.cdn1.cafe24.com/img/product/${review1.product_img }" width="170"></td>
				<td><b style="font-size: 25px">${review1.product_name }</b></td>
			</tr>
		</table>
		</div>
		<section id="basicInfoArea">
			<form action="ReviewWritePro.re" name="reviewForm" id="myform">
			<table border="2" style="margin-left:auto;margin-right:auto;">
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
					<button type="button" type="button" class="review_like" id="review_like1" style="border-color: transparent; background-color: transparent;  outline: 0;">
					<i class='far fa-thumbs-up' style='font-size:28px;'></i><div id="like_count">${review.review_like_count}</div></button>
				</td>
			</tr>
			<tr>
				<td width="150"height="150" align="center" style="font-weight: bold;">내용</td><td colspan="3" align="center">${review.review_content}</td>
			</tr>
			</table>
			</form>
		</section>
	</section>
	</div>
	
	<br>
	<section id="commandList" style="width: 1800px; margin-left: 750px;">
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
    <script src="${pageContext.request.contextPath }/resources/lib/easing/easing.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/lib/owlcarousel/owl.carousel.min.js"></script>

    <!-- Contact Javascript File -->
    <script src="${pageContext.request.contextPath }/resources/mail/jqBootstrapValidation.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/mail/contact.js"></script>

    <!-- Template Javascript -->
    <script src="${pageContext.request.contextPath }/resources/js/main.js"></script>
	<!------------------------------ top, left, bottom 동작 관련 작업 빼지말것! ------------------------------>
	
	
</body>
</html>









