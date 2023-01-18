<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	
	
	h2 {
		text-align: center;
	}
	
	table { 
 		margin-right: auto;
 		margin-left: auto;
 		margin-top: 100px;
 		color:  #575754;
 		background-color: #f0f0f0;
 		width: 500px;
 		height: 150px;
 		border-radius: 40px;
 	} 
	
	/* 수정, 돌아가기 버튼 */
	#s1 {
		width: 100px;
		height: 40px;
		color: #575754;
		border-color: #b3b3b3;
		font-weight: bold; 
		font-size: 20px;
		border-radius: 2px;
		
	}
</style>
</head>
<body>
	<header>
		<jsp:include page="../inc/top.jsp"></jsp:include>
		<jsp:include page="../inc/main.jsp"></jsp:include> <!-- 본문1 -->
	</header>
	<div style="display: flex; width: 1800px; margin-left: 50px;">
	<div align="left" style="width: 300px; margin-top: 70px;">
		<jsp:include page="../inc/memberInfo_left.jsp"></jsp:include> <!-- 본문1 -->
	</div>
	<!-- 게시판 글 삭제 -->
	<h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b style="border-left: 10px solid #795548">&nbsp;&nbsp;리뷰 삭제</b></h2>
	<section id="passForm">
		<form action="ReviewDeletePro.re" name="deleteForm" method="post" align="center">
			<!-- 입력받지 않은 글번호는 hidden으로 넘겨야함 -->
			
			<input type="hidden" name="review_idx" value="${param.review_idx }">
			<input type="hidden" name="pageNum" value="${param.pageNum }">
			<table>
				<tr>
					<td><label><b style="font-size: 20px;">리뷰 비밀번호를 작성하세요</b></label></td>
				</tr>
				<tr>	
					<td><input type="password" name="review_passwd" required="required"></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="삭제" id="s1">&nbsp;&nbsp;
						<input type="button" value="돌아가기" id="s1" onclick="javascript:history.back()">
					</td>
				</tr>
			</table>
		</form>
	</section>
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





