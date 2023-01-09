<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="img/daram.png" rel="shortcut icon" type="image/x-icon">
<title>댕글댕글 : 임시 비밀번호 발급</title>
<%------------------- 임시 홈페이지 CSS -------------------%>
<link href="css/default.css" rel="stylesheet" type="text/css">
<%---------------------------------------------------------%>
</head>
<body>
	<header>
		<!-- Login, Join 링크 표시 영역(inc/top.jsp 페이지 삽입) -->
		<jsp:include page="/inc/top.jsp"></jsp:include>
		<jsp:include page="/inc/main.jsp"></jsp:include>
	</header>
	
	<div class="clear"></div>
	
	<div align="center">
		<h1>
			<img src="img/green_Acon.png" width="20" height="25">
			<img src="img/brown_Acon.png" width="20" height="25">
			<img src="img/gold_Acon.png" width="20" height="25">
			<img src="img/daram.png" width="30" height="45">
			임시 비밀번호가 메일로 발송되었습니다!
			<img src="img/daram.png" width="30" height="45">
			<img src="img/green_Acon.png" width="20" height="25">
			<img src="img/brown_Acon.png" width="20" height="25">
			<img src="img/gold_Acon.png" width="20" height="25">
		</h1>
			<br>
		<h3>
			임시 비밀번호로 로그인 후, 반드시 비밀번호를 변경해주세요!
		</h3>
	</div>
	
	<div align="center">
		<input type="button" value="홈으로" onclick="location.href='./'">
		<input type="button" value="로그인" onclick="location.href='MemberLoginForm.me'">
	</div>
	
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