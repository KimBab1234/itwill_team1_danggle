<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Review 게시판</title>
<!-- 외부 css 가져오기 -->
<link href="review/css/review_modify.css" rel="stylesheet" type="text/css">
<style type="text/css">
	h1 { 
 		text-align: left; 
 	} 
	
 	table { 
 		align:left; 
 		width: 700px; 
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
</style>
</head>
<body>
	<header>
		<!-- Login, Join링크 표시 영역  -->
		<jsp:include page="../inc/top.jsp"></jsp:include>
	</header>
	<!-- 게시판 글 수정 -->
	<section id="modifyForm">
		<h1>리뷰게시판 글 수정</h1>
		<form action="ReviewModifyPro.re" name="reviewForm"id="myform" method="post" action="./save">
		<!-- 글번호, 페이지번호 / 글 수정 작업 동작 흐름-->
			<!-- 입력받지 않은 글번호는 hidden으로 넘겨야함 -->
			<input type="hidden" name="review_idx" value="${param.review_idx }">
			<input type="hidden" name="pageNum" value="${param.pageNum }">
			<table border="1" class="b1">
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
						<textarea id="review_content" name="review_content" cols="40" rows="15" required="required">${review.review_content }</textarea>
					</td>
				</tr>
			</table>
			<section id="commandCell">
				<input type="submit" value="수정">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기">&nbsp;&nbsp;
				<input type="button" value="취소" onclick="history.back()">
			</section>
		</form>
	</section>
</body>
</html>








