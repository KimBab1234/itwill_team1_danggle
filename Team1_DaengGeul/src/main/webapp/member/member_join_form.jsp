<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="img/daram.png" rel="shortcut icon" type="image/x-icon">
<title>댕글댕글 : 회원가입</title>

<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta http-equiv="X-UA_Compatible" content="IE=edge">

<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Hi+Melody&family=Nanum+Gothic&family=Noto+Serif+KR&display=swap" rel="stylesheet">
<%------------------- 임시 홈페이지 CSS -------------------%>
<link href="css/default.css" rel="stylesheet" type="text/css">
<link href="css/joinForm.css" rel="stylesheet" type="text/css">
<%---------------------------------------------------------%>

<%----------------------------- 회원가입 CheckList----------------------------%>
<script src="js/jquery-3.6.3.js"></script>
<script type="text/javascript">
	// 아이디, 패스워드 유효성 검사 적용을 위한 변수
	var idStatus = false;
	var passwdStatus = false;
	var nameStatus = false;
	var emailStatus = false;
	
	$(function() {
		
		// --------------- 아이디 유효성 검사 및 중복체크 ----------------
		$("#id").on("change", function() {
			let id = $("#id").val();
			let lengthRegex = /^[A-Za-z0-9-_]{5,12}$/;

			if(!lengthRegex.exec(id)){
				$("#checkIdResult").html("사용 불가능한 아이디").css("color", "red");
				
			} else {
				
				$.ajax({
					url: "MemberCheckId.me",
					data: {
						id: $("#id").val()
					},
					success: function(result) {
						
						if(result == "true"){
							$("#checkIdResult").html("이미 존재하는 아이디").css({
								color : "#c9b584",
								marginLeft : "137px"
							});
							
						} else {
							$("#checkIdResult").html("사용 가능한 아이디").css({
								color : "#fae37d",
								marginLeft : "137px"
							});
							idStatus = true;
						}
						
					}
					
				});
				
			}
		});
		// ---------------------------------------------------------------
		
		
		// --------------------- 비밀번호 유효성 검사 --------------------
		$("#passwd").on("change", function() {
			let passwd = $("#passwd").val();

			let regex = /^[A-Za-z0-9~!@#$%^&*-_]{8,16}$/;
			let engUpperRegex = /[A-Z]/;
			let engLowerRegex = /[a-z]/;
			let numRegex = /[0-9]/;
			let specRegex = /[!@#$%]/;
			
			if(!regex.exec(passwd)){
				$("#rightPasswdResult").html("사용 불가능한 패스워드").css("color", "red");
			} else {
				passwdStatus = true;
				let count = 0;
				if(engUpperRegex.exec(passwd)){ count++ };
				if(engLowerRegex.exec(passwd)){ count++ };
				if(numRegex.exec(passwd)){ count++ };
				if(specRegex.exec(passwd)){ count++ };
				
				switch(count){
					case 4 : $("#rightPasswdResult").html("안전").css("color", "green"); break;
					case 3 : $("#rightPasswdResult").html("보통").css("color", "orange"); break;
					case 2 : $("#rightPasswdResult").html("위험").css("color", "yellow"); break;
					case 1 : $("#rightPasswdResult").html("사용불가").css("color", "red");
					passwdStatus = false;
				}
				
			}
		});
		// ---------------------------------------------------------------
		
		
		// ----------------------- 비밀번호 확인 --------------------------
		$("#checkpasswd").on("change", function() {
			let passwd = $("#passwd").val();
			let checkpasswd = $("#checkpasswd").val();
			
			if(checkpasswd == passwd){
				$("#checkPasswdResult").html("비밀번호가 일치합니다").css({
					color : "#fae37d",
					marginLeft : "137px"
				});
			} else {
				$("#checkPasswdResult").html("비밀번호가 일치하지 않습니다").css({
					color : "#c9b584",
					marginLeft : "137px"
				});
			}
			
		});
		// ---------------------------------------------------------------
		
		
		// ---------------------- 이름 유효성 검사 -----------------------
		$("#name").on("keyup", function() {
			let name = $("#name").val();
			let regex = /^[가-힣]{2,10}$/;

			if(!regex.exec(name)){
				$("#checkNameResult").html("한글 2~10글자로 입력해주세요").css("color", "#c9b584");
			}else {
				nameStatus = true;
				$("#checkNameResult").html("");
			}
		});
		// ---------------------------------------------------------------
		
		
		// --------------- 이메일 유효성 검사 및 입력 제한 ---------------
		$("#email1").on("keyup", function() {
			var inputVal = $(this).val();
			$(this).val($(this).val().replaceAll(/[^\w_.\d]/g,""));
		});
		
		$("#email2").on("keyup", function() {
			var inputVal = $(this).val();
			$(this).val($(this).val().replaceAll(/[^\w@.]/g,""));
		});
		// ---------------------------------------------------------------
		
		
		// ----------------------- 도메인 선택목록 -----------------------
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
		// ---------------------------------------------------------------

		
		// --------------------- 이메일 중복체크 -------------------------
		$("#email2, #selectDomain").on("change", function() {
			let email1 = $("#email1").val();
			let email2 = $("#email2").val();
			
			if(email1 != ""){

				$.ajax({
					url: "MemberCheckEmail.me",
					data: {
						email1: email1,
						email2: email2
					},
					success: function(result) {
						
						if(result == "true"){
							$("#checkEmailResult").html("이미 등록된 이메일").css({
								color : "#c9b584",
								marginLeft : "137px"
							});
						} else {
							$("#checkEmailResult").html("사용 가능한 이메일").css({
								color : "#fae37d",
								marginLeft : "137px"
							});
						}
						
					}
					
				});
				
			} else {
				$("#checkEmailResult").html("이메일을 전부 입력하세요").css({
					color : "red",
					marginLeft : "137px"
				});
			}
			
		});
		// ---------------------------------------------------------------
		
		
		// ---------------------- 인증코드 보내기 ------------------------
		$("#sendCert").on("click", function() {
			let id = $("#id").val();
			let email1 = $("#email1").val();
			let email2 = $("#email2").val();
			let type = "join";
			
			if(id == ""){
				$("#certEmailMsg").html("아이디부터 입력해주세요!").css("color", "red");
			} else {
				if(email1 == "" || email2 ==""){
					$("#certEmailMsg").html("이메일을 입력해주세요!").css("color", "red");
				} else {
					$("#certEmailMsg").html("인증번호가 전송되었습니다").css({
						color : "#fae37d",
						marginLeft : "137px"
					});
					
					$.ajax({
						url: "MemberSendCertPro.me",
						type : 'POST',
						data: {
							id: id,
							email1: email1,
							email2: email2,
							type: type
						}
						
					});
					
				}
				
			}
			
		});
		// ---------------------------------------------------------------
		
		
		// ----------------------- 인증코드 인증 -------------------------
		$("#checkCert").on("click", function() {
			let id = $("#id").val();
			let email1 = $("#email1").val();
			let email2 = $("#email2").val();
			let certNum = $("#certNum").val();
			
			if(id == ""){
				$("#certEmailMsg").html("아이디부터 입력해주세요!").css("color", "red");
			} else {
				if(email1 == "" || email2 ==""){
					$("#certEmailMsg").html("이메일을 입력해주세요!").css("color", "red");
				} else {
					if(certNum == ""){
						$("#certEmailMsg").html("인증번호를 입력해주세요!").css("color", "red");
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
									$("#certEmailMsg").html("이메일이 인증되었습니다").css({
										color : "#fae37d",
										marginLeft : "137px"
									});
								} else {
									$("#certEmailMsg").html("인증코드가 틀렸습니다!").css({
										color : "red",
										marginLeft : "137px"
									});
								}
								
							}
							
						});
									
					}
					
				}
				
			}
			
		});
		// ---------------------------------------------------------------
		
		
		// ------------------ 조건 충족 시, 회원가입 ---------------------
		$("#joinForm").submit(function() {
			if(!idStatus){
				alert("아이디를 재입력 해주세요!")
				return false;
			} else if(!passwdStatus) {
				alert("패스워드를 재입력 해주세요!")
				return false;
			} else if(!emailStatus){
				alert("이메일 인증을 해주세요!")
				return false;
			} else if(!nameStatus){
				alert("이름을 확인 해주세요!")
				return false;
			}
		});
		// ---------------------------------------------------------------
		
	});
</script>
<%----------------------------------------------------------------------------%>

<%------------------------------- 주소찾기 API -------------------------------%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
function execDaumPostcode() {
	<%-- 주소검색 팝업창 --%>
       new daum.Postcode({
           oncomplete: function(data) {
               // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

               // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
               // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
               var roadAddr = data.roadAddress; // 도로명 주소 변수
               var extraRoadAddr = ''; // 참고 항목 변수

               // 법정동명이 있을 경우 추가한다. (법정리는 제외)
               // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
               if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                   extraRoadAddr += data.bname;
               }
               // 건물명이 있고, 공동주택일 경우 추가한다.
               if(data.buildingName !== '' && data.apartment === 'Y'){
                  extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
               }

               // 우편번호와 주소 정보를 해당 필드에 넣는다.
               document.getElementById('postcode').value = data.zonecode;
               document.getElementById("roadAddress").value = roadAddr;
               document.getElementById("jibunAddress").value = data.jibunAddress;
               
           }
       }).open();
	<%-- 주소검색 팝업창 --%>
   }
   </script>
<%----------------------------------------------------------------------------%>

</head>
<body>
	<header>
		<!-- join, Join 링크 표시 영역(inc/top.jsp 페이지 삽입) -->
		<jsp:include page="/inc/top.jsp"></jsp:include>
		<jsp:include page="/inc/main.jsp"></jsp:include>
	</header>
	
	<div class="clear"></div>
	
	<form action="MemberJoinPro.me" method="post" id="joinForm" name="joinForm">
		
		<!-- 화면 커버 -->
		<div class="join-cover">
	
			<!-- 로그인 영역 -->
			<div class="join-wrapper">
	
				<div class="row header">
					회원가입
					<img src="img/daram.png" width="40" height="50">
				</div>

				<div class="row">
					<b>아이디</b>
					<input type="text" class="in-id" name="id" id="id" required="required" size="30" 
						placeholder="5-12자리 영문자,숫자 조합" maxlength="12">
				</div>
				<div id="checkIdResult"></div>
				
				<div class="row">
					<b>비밀번호</b>
					<input type="password" class="in-pw" name="passwd" id="passwd" required="required" size="40" 
						placeholder="8-16자리 영어 대/소문자,숫자,특수문자 조합" maxlength="16" >
				</div>
				<div id="rightPasswdResult"></div>
				<div class="row">
					<b>비밀번호 확인</b>
					<input type="password" name="checkpasswd" id="checkpasswd" required="required" size="35" 
						placeholder="8-16자리 영어 대/소문자,숫자,특수문자 조합" maxlength="16" >
				</div>
				<div id="checkPasswdResult"></div>
				
				<div class="row">
					<b>이름</b>
					<input type="text" class="in-na" name="name" id="name" required="required" size="20" placeholder="이름을 입력하세요.">
				</div>
				<div id="checkNameResult"></div>
				
				<div class="row">
					<b>성별</b>
					<input type="radio" id="in-gen1" name="gender" value="남"><strong>남</strong>&nbsp;&nbsp;
					<input type="radio" id="in-gen2" name="gender" value="여" checked="checked"><strong>여</strong>
				</div>
				
				<div class="row">
					<b>E-Mail</b>
					<input type="text" class="in-e" name="email1" id="email1" required="required" size="10"> <strong>&nbsp;@&nbsp;</strong> 
					<input type="text" class="in-e2" name="email2" id="email2" required="required" size="10" placeholder="직접입력">
					<select name="email" id="selectDomain" >
					 	<option value="">직접입력</option>
					 	<option value="naver.com">naver.com</option>
					 	<option value="nate">nate.com </option>
					 	<option value="daum.net">daum.net</option>
					 	<option value="gmail.com">gmail.com</option>
					 </select>
				</div>
				<div id="checkEmailResult"></div>
				<div class="row">
					<input type="text" class="in-e2" name="certNum" id="certNum" required="required" size="10" placeholder="인증번호">
					<input type="button" class="in-button" id="sendCert" value="인증번호 받기" >
					<input type="button" class="in-button" id="checkCert" value="Email 인증하기" >
				</div>
				<div id="certEmailMsg"></div>
				
				<div class="row">
					<b>휴대전화</b>
					<select name="phone1">
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="016">016</option>
						<option value="017">017</option>
					</select>
					<strong>&nbsp;-&nbsp;</strong><input type="text" class="in-ph" name="phone2" required="required" 
													size="9" maxlength="4" oninput="this.value=this.value.replace(/[^0-9]/g, '');"><strong>&nbsp;-&nbsp;</strong>
					<input type="text" class="in-ph" name="phone3" required="required" size="10" maxlength="4" oninput="this.value=this.value.replace(/[^0-9]/g, '');">
				</div>
				
				<div class="row">
					<b>주소</b>
					<input type="text" id="postcode" name="postcode" placeholder="우편번호" required="required" oninput="this.value=this.value.replace(/[^0-9]/g, '');">
					<input type="button" id="postbutton" onclick="execDaumPostcode()" value="우편번호 찾기">
				</div>
				<div class="row">
					<b>&nbsp;</b>
					<input type="text" id="jibunAddress" name="jibunAddress" placeholder="지번주소"><br>
					<input type="text" id="roadAddress" name="roadAddress" placeholder="도로명주소">
				</div>
				
				<div class="row">
					<b>상세주소</b>
					<textarea rows="3" cols="37" name="addressDetail" required="required"></textarea>	
				</div>
				
				<!-- 버튼 -->
				<div class="row-button">
					<input type="submit" id="btnJoin" class="in-button" value="가입하기">
					<input type="reset" class="in-button" value="다시입력">					
					<input type="button" class="in-button" value="뒤로가기" onclick="history.back()">
				</div>
				<!-- 버튼 -->
			</div>
		</div>
	</form>
	
	<div class="clear-join"></div>
	
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