<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Review 게시판</title>
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
<script type="text/javascript">
$(function() {
    $('.dropdown-toggle', this).trigger('click').blur();
});
</script>
<style type="text/css">
	* {
	font-family: 'Gowun Dodum', sans-serif;
	url: @import url('https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap');
	}
 	h2 { 
 		text-align: left; 
 	} 
	
 	.b1 { 
 		align: center; 
 		width: 600px; 
 		height: 300px;
 		border-color: #b09f76;
 		color:  #575754;
 	} 
	/* 테이블 왼쪽 */
 	.td_left { 
 		width: 200px; 
 		background: #c9b584; 
 		text-align: center; 
 		font-weight: bold;
 	} 
	/* 테이블 오른쪽 */
 	.td_right { 
 		width: 400px; 
 	} 
	/* 테이블 입력란 */
 	.r1 { 
 		width: 400px; 
 	} 
	/* 등록버튼 */
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
	/* 초기화버튼 */
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
	/* 취소버튼 */
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
	/* 별점 */
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
	    text-shadow: 0 0 0 #f0f0f0; /* 새 이모지 색상 부여 */
	}
	#myform fieldset label:hover{
		color: transparent; /* 기존 이모지 컬러 제거 */
	    text-shadow: 0 0 0 #ffcc00; /* 마우스 호버 */
	}
	#myform fieldset label:hover ~ label{
		color: transparent; /* 기존 이모지 컬러 제거 */
	    text-shadow: 0 0 0 #ffcc00; /* 마우스 호버 뒤에오는 이모지들 */
	}
	#myform fieldset input[type=radio]:checked ~ label{
		color: transparent; /* 기존 이모지 컬러 제거 */
	    text-shadow: 0 0 0 #ffcc00; /* 마우스 클릭 체크 */
	}
	 	
</style>
</head>
<body>
	<header>
		<!-- Login, Join링크 표시 영역  -->
		<jsp:include page="../inc/top.jsp"></jsp:include>
		<jsp:include page="../inc/main.jsp"></jsp:include> <!-- 본문1 -->
	</header>
	<br>
	<div style="display: flex; width: 1300px;">
	<div align="left" style="width: 300px; margin-top: 100px; margin-left: 100px">
		<jsp:include page="../inc/memberInfo_left.jsp"></jsp:include> <!-- 본문1 -->
	</div>
	<!-- 게시판 글 수정 -->
	<div id="modifyForm" align="left" style="width: 1000px; margin-left: 350px;">
		<h2><b style="border-left: 10px solid #795548">리뷰 수정</b></h2>
		<br>
		<br>
		<table>
			<tr>
				<td><img src="img/product/${review1.product_img }" width="170" style="margin-right: 60px"></td>
				<td><b style="font-size: 25px">${review1.product_name }</b></td>
			</tr>
		</table>
		<br>
		<form action="ReviewModifyPro.re" name="reviewForm"id="myform" method="post" action="./save" >
		<!-- 글번호, 페이지번호 / 글 수정 작업 동작 흐름-->
			<!-- 입력받지 않은 글번호는 hidden으로 넘겨야함 -->
			<input type="hidden" name="review_idx" value="${param.review_idx }">
			<input type="hidden" name="pageNum" value="${param.pageNum }">
			<input type="hidden" name="product_idx" value="${param.product_idx }">
			<table border="1" class="b1" style="">
				<tr>
					<td class="td_left"><label for="review_subject"><b>제목</b></label></td>
					<td class="td_right"><input type="text" name="review_subject" value="${review.review_subject }" required="required" class="r1"/></td>
				</tr>
				<tr>
					<td class="td_left"><label for="review_passwd"><b>비밀번호</b></label></td>
					<td class="td_right"><input type="password" name="review_passwd" required="required" class="r1"/></td>
				</tr>
				<tr>
					<td class="td_left"><label for="review_score"><b>별점</b></label></td>
					<td class="td_right">
				    <fieldset name="review_score" class="score">
				        <input type="radio" name="review_score" value="5" id="rate1"><label for="rate1">⭐</label>
				        <input type="radio" name="review_score" value="4" id="rate2"><label for="rate2">⭐</label>
				        <input type="radio" name="review_score" value="3" id="rate3"><label for="rate3">⭐</label>
				        <input type="radio" name="review_score" value="2" id="rate4"><label for="rate4">⭐</label>
				        <input type="radio" name="review_score" value="1" id="rate5"><label for="rate5">⭐</label>
				    </fieldset>
					</td> 
				</tr>
				<tr>
					<td class="td_left"><label for="review_content"><b>내용</b></label></td>
					<td class="td_right">
						<textarea id="review_content"  class="r1" name="review_content" cols="40" rows="10" required="required">${review.review_content }</textarea>
					</td>
				</tr>
			</table>
			<br>
			<section id="commandCell" align= "center">
				<input type="submit" value="수정" id="s1">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기" id="s2">&nbsp;&nbsp;
				<input type="button" value="취소" id="s3" onclick="history.back()">
			</section>
		</form>
	</div>
	</div>
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

















