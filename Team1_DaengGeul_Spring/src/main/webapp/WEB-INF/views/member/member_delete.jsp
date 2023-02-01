<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/resources/img/daram.png" rel="shortcut icon" type="image/x-icon">
<title>댕글댕글 : 회원탈퇴</title>
<%------------------- 임시 홈페이지 CSS -------------------%>
<link href="${pageContext.request.contextPath}/resources/css/default.css" rel="stylesheet" type="text/css">
<%---------------------------------------------------------%>
<style>
	#passForm {
		width: 500px;
		border: 1px solid orange;
		text-align: center;
	}
	
	h2 {
		text-align: center;
	}
	
	table {
		width: 300px;
		margin: auto;
		text-align: center;
	}
	
	label{
		width: 120px;
	}
	
</style>
</head>
<body>
	<header>
		<!-- Login, Join 링크 표시 영역(inc/top.jsp 페이지 삽입) -->
		<jsp:include page="../inc/top.jsp"></jsp:include>
		<jsp:include page="../inc/main.jsp"></jsp:include>
	</header>
	
	<div class="clear"></div>
	
	<!-- 회원탈퇴 -->
	<div align="center">
		<h1>
			<img src="${pageContext.request.contextPath}/resources/img/green_Acon.png" width="20" height="25">
			<img src="${pageContext.request.contextPath}/resources/img/brown_Acon.png" width="20" height="25">
			<img src="${pageContext.request.contextPath}/resources/img/gold_Acon.png" width="20" height="25">
			<img src="${pageContext.request.contextPath}/resources/img/daram.png" width="30" height="45">
			 회원탈퇴  
			<img src="${pageContext.request.contextPath}/resources/img/daram.png" width="30" height="45">
			<img src="${pageContext.request.contextPath}/resources/img/gold_Acon.png" width="20" height="25">
			<img src="${pageContext.request.contextPath}/resources/img/brown_Acon.png" width="20" height="25">
			<img src="${pageContext.request.contextPath}/resources/img/green_Acon.png" width="20" height="25">
		</h1>
	</div>
	
	<section>
		<form action="MemberDeletePro.me" name="deleteForm" method="post">
			<input type="hidden" name="id" value="${param.id }">
			<table>
				<tr>
					<td><label>비밀번호 입력</label></td>
					<td><input type="password" name="passwd" required="required"></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="회원탈퇴">&nbsp;&nbsp;
						<input type="button" value="돌아가기" onclick="javascript:history.back()">
					</td>
				</tr>
			</table>
		</form>
	</section>
	
	<div class="clear"></div>
	<div class="clear"></div>
	
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