<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link href="img/daram.png" rel="shortcut icon" type="image/x-icon">
<title>댕글댕글 : 로그인</title>

<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta http-equiv="X-UA_Compatible" content="IE=edge">

<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Hi+Melody&family=Nanum+Gothic&family=Noto+Serif+KR&display=swap"
	rel="stylesheet">

<%------------------- 임시 홈페이지 CSS -------------------%>
<link href="${pageContext.request.contextPath }/resources/css/default.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/resources/css/loginForm.css" rel="stylesheet" type="text/css">
<%---------------------------------------------------------%>
</head>
<body>
	<header>
		<!-- Login, Join 링크 표시 영역(inc/top.jsp 페이지 삽입) -->
		<jsp:include page="../inc/top.jsp"></jsp:include>
		<jsp:include page="../inc/main.jsp"></jsp:include>
	</header>

	<div class="clear"></div>

	<form action="MemberLoginPro.me" method="post">
		<!-- 화면 커버 -->
		<div class="login-cover">

			<!-- 로그인 영역 -->
			<div class="login-wrapper">

				<div class="row header">
					로그인
					<img src="${pageContext.request.contextPath }/resources/img/daram.png" width="40" height="50">
				</div>

				<div class="row">
					<input type="text" name="id" required="required" autocomplete="off">
					<label>ID : </label>
					<div class="highlight"></div>
				</div>

				<div class="row">
					<input type="password" name="passwd" required="required"> <label>PW : </label>
					<div class="highlight"></div>
				</div>

				<div class="row-button">
					<input type="submit" id="login" value="로그인" >
					<input type="button" value="아이디/비밀번호 찾기" onclick="location.href='MemberInfoSearchForm.me'">
					<input type="button" value="회원가입" onclick="location.href='MemberJoinForm.me'">
					<input type="button" value="뒤로가기" onclick="history.back()">
				</div>

			</div>

		</div>
	</form>

	<div class="clear"></div>

	<!------------------------------------ 바닥글 --------------------------------------->
	<footer>
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
	</footer>
	<!----------------------------------------------------------------------------------->

	<!------------------------------ top, left, bottom 동작 관련 작업 빼지말것! ------------------------------>
	<!-- Back to Top -->
	<a href="#" class="btn btn-primary back-to-top"><i
		class="fa fa-angle-double-up"></i></a>

	<!-- JavaScript Libraries -->
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
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