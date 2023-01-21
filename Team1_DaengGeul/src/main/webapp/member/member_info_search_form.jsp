<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="img/daram.png" rel="shortcut icon" type="image/x-icon">
<title>댕글댕글 : 회원정보 찾기</title>
<%------------------- 임시 홈페이지 CSS -------------------%>
<link href="css/default.css" rel="stylesheet" type="text/css">
<link href="css/memberInfoSearchForm.css" rel="stylesheet" type="text/css">
<%---------------------------------------------------------%>

<%------------------- 회원정보 찾기 -------------------%>
<script src="js/jquery-3.6.3.js"></script>
<script type="text/javascript">
	$(function() {
		var emailStatus = false;
		
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
			let email1 = $("#email1").val();
			let email2 = $("#email2").val();
			
			if(email1 == "" || email2 ==""){
				$("#searchIdResult").html("이메일을 입력해주세요!").css("color", "red");
			} else {
				
		  		$.ajax({
					url : "MemberSearchId.me",
					type : 'POST',
					data: {
						email1: email1,
						email2: email2
					},
					success: function(findId){
						$("#searchIdResult").html("회원님의 아이디는 : " + findId + "입니다").css("color", "#fae37d");;
					},
					error : function(request, status, error){
						$("#searchIdResult").html("이메일을 확인해주세요").css("color", "red");
					}
				});
			  		
			}
		  	
		});
		// --------------------------------------------------
		
		
		// ------------- 인증번호 이메일 전송 ---------------
		$("#sendCert").on("click", function() {
			let id = $("#id").val();
			let type = "cert";
			
			if(id == ""){
				$("#searchPasswdResult").html("아이디부터 입력해주세요!").css("color", "red");
			} else {
				$("#searchPasswdResult").html("인증번호가 전송되었습니다").css("color", "#c9b584");
				$.ajax({
					url : "MemberSendCertPro.me",
					type : 'POST',
					data: {
						id: id,
						type: type
					},
					error : function(request, status, error){
						$("#searchPasswdResult").html("존재하지 않는 회원입니다").css("color", "#c9b584");
					}
				});
				
			}
			
		});
		// --------------------------------------------------
		
		
		// ----------------------- 인증코드 인증 -------------------------
		$("#checkCert").on("click", function() {
			let id = $("#id").val();
			let certNum = $("#certNum").val();
			
			if(id == ""){
				$("#searchPasswdResult").html("아이디부터 입력해주세요!").css("color", "red");
			} else {
					
				if(certNum == ""){
					$("#searchPasswdResult").html("인증번호를 입력해주세요!").css("color", "red");
				} else {
					
					$.ajax({
						url: "MemberCheckCertPro.me",
						data: {
							id: id,
							certNum: certNum
						},
						success: function(result) {
							if(result == "true"){
								emailStatus = true;
								$("#searchPasswdResult").html("이메일이 인증되었습니다").css("color", "#fae37d");
							} else {
								$("#searchPasswdResult").html("인증코드가 틀렸습니다!").css("color", "red");
							}
						},
						error : function(request, status, error){
							$("#searchPasswdResult").html("존재하지 않는 회원입니다").css("color", "#c9b584");
						}
						
					});
								
				}
				
			}
			
		});
		// ---------------------------------------------------------------
		
		
		// --------------------- 이메일 미인증 시 ------------------------
		$("#MemberSearchPasswd").submit(function() {
			if(!emailStatus){
				alert("이메일 인증을 해주세요!")
				return false;
			}
		});
		// ---------------------------------------------------------------
		
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
	
<!-- 	<div> -->
<!-- 		<form id="MemberSearchPasswd" action="MemberSearchPasswd.me"> -->
<!-- 			<h5>비밀번호 찾기(Email로 인증코드 발송)</h5> -->
<!-- 			<input type="text" name="id" id="id" placeholder="아이디 입력" ><br> -->
<!-- 			<input type="text" id="certNum" placeholder="인증번호" > -->
<!-- 			<input type="button" id="sendCert" value="인증번호 전송" ><br> -->
<!-- 			<div id="searchPasswdResult"></div> -->
<!-- 			<input type="button" id="checkCert" value="인증확인" > -->
<!-- 			<input type="submit" id="searchPasswd" value="비밀번호 찾기" > -->
<!-- 		</form> -->
<!-- 	</div> -->
	
	<form action="MemberSearchId.me" method="post" id="MemberSearchId" name="joinForm">
		
		<!-- 화면 커버 -->
		<div class="join-cover">
	
			<!-- 아이디 찾기 영역 -->
			<div class="join-wrapper">
	
				<div class="row header">
					아이디 찾기
					<img src="img/daram.png" width="40" height="50">
				</div>
				
				<div class="row">
					<input type="text" name="email1" id="email1" class="in-e" required="required" size="10"> <strong>&nbsp;@&nbsp;</strong>
					<input type="text" name="email2" id="email2" class="in-e2" required="required" size="10" placeholder="직접입력">
					<select name="email" id="selectDomain" >
					 	<option value="">직접입력</option>
					 	<option value="naver.com">naver.com</option>
					 	<option value="nate">nate.com </option>
					 	<option value="daum.net">daum.net</option>
					 	<option value="gmail.com">gmail.com</option>
					 </select>
					 
				<div id="searchIdResult"></div>
				</div>
				
				<!-- 버튼 -->
				<div class="row-button">
					<input type="button" id="btnSearchId" class="in-button" value="아이디 찾기" >
				</div>
				<!-- 버튼 -->
				
			</div>
		</div>
	</form>
	
	<div class="clear"></div>
	
	<form action="MemberSearchPasswd.me" method="post" id="MemberSearchPasswd" name="joinForm">
		
		<!-- 화면 커버 -->
		<div class="join-cover">
	
			<!-- 로그인 영역 -->
			<div class="join-wrapper">
	
				<div class="row header">
					비밀번호 찾기
					<img src="img/daram.png" width="40" height="50">
				</div>
				<div class="row">
					<b>(Email로 인증코드 발송)</b>
				</div>

				<div class="row">
					<input type="text" name="id" id="id" placeholder="아이디 입력" >
				</div>
				<div class="row">
					<input type="text" id="certNum" placeholder="인증번호" >
					<input type="button" id="sendCert" value="인증번호 전송" >
					<input type="button" id="checkCert" value="인증확인" >
					<div id="searchPasswdResult"></div>
				</div>
				<!-- 버튼 -->
				<div class="row-button">
					<input type="submit" id="searchPasswd" class="in-button" value="비밀번호 찾기" >
				</div>
				<!-- 버튼 -->
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