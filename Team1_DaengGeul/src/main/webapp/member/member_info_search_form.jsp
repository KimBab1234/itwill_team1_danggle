<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="img/daram.png" rel="shortcut icon" type="image/x-icon">
<title>댕글댕글 : 아이디/비밀번호 찾기</title>
<%------------------- 임시 홈페이지 CSS -------------------%>
<link href="css/default.css" rel="stylesheet" type="text/css">
<%---------------------------------------------------------%>

<%------------------- 회원정보 찾기 -------------------%>
<script src="js/jquery-3.6.3.js"></script>
<script type="text/javascript">
	$(function() {
		// ------------------ 도메인 목록 -------------------
		$("#selectDomain").on("change", function() {
			let domain = $("#selectDomain").val();
			$("#email2").val(domain);
			
			if(domain == ""){
				$("#email2").prop("readonly", false);
				$("#email2").focus();
			} else {
				$("#email2").prop("readonly", true);
			}
			
		});
		// --------------------------------------------------
		
		
		// ------------------ 아이디 찾기 -------------------
		$("#btnSearchId").on("click", function() {
	  	
	  		$.ajax({
				url : "MemberSearchId.me",
				type : "post",
				data: {
					email1: $("#email1").val(),
					email2: $("#email2").val()
				},
				dataType: "text", 
				success: function(findId){
// 					alert(findId);
						$("#searchIdResult").text(findId);
					if(findId != null){
						
					} else {
						alert("아이디 찾기가 실패했습니다");
					}
				},
				error : function(request, status, error){
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});
		  	
		});
		// --------------------------------------------------
		
	});
</script>
<%-----------------------------------------------------%>


</head>
<body>
	<header>
		<!-- Login, Join 링크 표시 영역(inc/top.jsp 페이지 삽입) -->
		<jsp:include page="/inc/top.jsp"></jsp:include>
		<jsp:include page="/inc/main.jsp"></jsp:include>
	</header>
	
	<div class="clear"></div>
	
	<h1>
		<img src="img/green_Acon.png" width="20" height="25">
		<img src="img/brown_Acon.png" width="20" height="25">
		<img src="img/gold_Acon.png" width="20" height="25">
		<img src="img/daram.png" width="30" height="45">
		회원 아이디/비밀번호 찾기
		<img src="img/daram.png" width="30" height="45">
		<img src="img/green_Acon.png" width="20" height="25">
		<img src="img/brown_Acon.png" width="20" height="25">
		<img src="img/gold_Acon.png" width="20" height="25">
	</h1>
	
	<br>
	
	<div>
		<form id="MemberSearchId" action="MemberSearchId.me">
			<h5>아이디 찾기</h5>
			<input type="text" name="email1" id="email1" required="required" size="10"> @
			<input type="text" name="email2" id="email2" required="required" size="10" placeholder="직접입력">
			<select name="email" id="selectDomain" >
			 	<option value="">직접입력</option>
			 	<option value="naver.com">naver.com</option>
			 	<option value="nate">nate.com </option>
			 	<option value="daum.net">daum.net</option>
			 	<option value="gmail.com">gmail.com</option>
			 </select>
			<input type="button" id="btnSearchId" value="아이디 찾기" >
		</form>
	</div>
	<div id="searchIdResult"></div>
	
	<div class="clear"></div>
	
	<div>
		<h5>비밀번호 찾기(Email로 인증코드 발송)</h5>
		<input type="text" placeholder="아이디 입력" >
		<input type="text" placeholder="인증번호" >
		<input type="button" value="인증번호 전송" onclick="location.href='.me'">
		<input type="button" value="비밀번호 찾기" onclick="location.href='.me'">
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