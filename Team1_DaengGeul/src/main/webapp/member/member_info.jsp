<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="img/daram.png" rel="shortcut icon" type="image/x-icon">
<title>댕글댕글 : 회원정보</title>
<%-------------------- 임시 홈페이지 CSS -------------------%>
<link href="css/default.css" rel="stylesheet" type="text/css">
<link href="css/MemberInfoForm.css" rel="stylesheet" type="text/css">
<%----------------------------------------------------------%>


<%------------------------------ 회원상세정보 --------------------------------%>
<script src="js/jquery-3.6.3.js"></script>
<script type="text/javascript">
	$(function() {
		
		// -------------------- 회원탈퇴 확인 알림창 --------------------
		$("#btnDelete").on("click", function() {
		  	if(confirm("정말 회원탈퇴를 하시겠습니까?")) {
		  		location.href = "MemberDeleteForm.me?id=" + $('#id').val();
		  	}
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
		
	});
	
</script>
<%----------------------------------------------------------------------------%>


<%-- 주소찾기 API --%>
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
<%-- 주소찾기 API --%>
</head>
<body>
	<header>
		<!-- Login, Join 링크 표시 영역(inc/top.jsp 페이지 삽입) -->
		<jsp:include page="/inc/top.jsp"></jsp:include>
		<jsp:include page="/inc/main.jsp"></jsp:include>
	</header>
	
	<div class="clear"></div>
	
	<article>
	
		<!---------------------------------- 마이페이지 영역 ----------------------------------->
	  	<form action="MemberUpdatePro.me" method="post" id="joinForm" name="joinForm">
		<!-- 화면 커버 -->
		<div class="join-cover">
	
			<div class="join-wrapper">
	
				<div class="row header">
					마이페이지
					<img src="img/daram.png" width="40" height="50">
				</div>

				<div class="row">
					<b>아이디</b>
					<input type="text" name="id" class="in-id" id="id" value="${member.member_id }" readonly="readonly">
				</div>
				
				<div class="row">
					<b>기존 비밀번호</b>
					<input type="password" name="oldPasswd" id="oldPasswd" class="in-pw1" size="28" required="required" placeholder="패스워드 입력">
				</div>
				<div class="row">
					<b>변경할 비밀번호</b>
					<input type="password" name="newPasswd" id="newPasswd" class="in-pw2" size="28" placeholder="변경시에만 입력">
				</div>
				<div id="rightPasswdResult"></div>
				<div class="row">
					<b>변경할 비밀번호 확인</b>
					<input type="password" name="newPasswd2" id="newPasswd2" class="in-pw3" size="23" placeholder="변경시에만 입력">
				</div>
				<div id="checkPasswdResult"></div>
				
				<div class="row">
					<b>이름</b>
					<input type="text" name="name" id="name" class="in-na" required="required" value="${member.member_name }">
				</div>
				<div id="checkNameResult"></div>
				
				<div class="row">
					<b>E-Mail</b>
					<input type="text" class="in-e" name="email1" id="email1" value="${requestScope.email1 }" required="required" size="10"> <strong>&nbsp;@&nbsp;</strong> 
					<input type="text" class="in-e2" name="email2" id="email2" value="${requestScope.email2 }" required="required" size="10" placeholder="직접입력">
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
					<input type="text" class="in-e2" name="certNum" id="certNum" size="11" placeholder="인증번호">
					<input type="button" class="in-button" id="sendCert" value="인증번호 받기" >
					<input type="button" class="in-button" id="checkCert" value="Email 인증하기" >
				</div>
				<div id="certEmailMsg"></div>
				
				<div class="clear"></div>
				
				<!-- 배송정보 영역 -->
				<div class="row header">
					배송정보
					<img src="img/daram.png" width="40" height="50">
				</div>
				
				<div class="row">
					<b>휴대전화</b>
					<select id="selectPhone1" name="phone1" >
						<% String phone1 = (String)request.getAttribute("phone1"); %>
						<option value="010" <%if(phone1.equals("010")) {%>  selected="selected" <%} %>>010</option>
						<option value="011" <%if(phone1.equals("011")) {%>  selected="selected" <%} %>>011</option>
						<option value="016" <%if(phone1.equals("016")) {%>  selected="selected" <%} %>>016</option>
						<option value="017" <%if(phone1.equals("017")) {%>  selected="selected" <%} %>>017</option>
					</select>
					<strong>&nbsp;-&nbsp;</strong>
					<input type="text" class="in-ph" name="phone2" required="required" value="${requestScope.phone2 }"
						size="9" maxlength="4" oninput="this.value=this.value.replace(/[^0-9]/g, '');">
					<strong>&nbsp;-&nbsp;</strong>
					<input type="text" class="in-ph" name="phone3" required="required" value="${requestScope.phone3 }"
						size="10" maxlength="4" oninput="this.value=this.value.replace(/[^0-9]/g, '');">
				</div>
				
				<div class="row">
					<b>주소</b>
					<input type="text" id="postcode" name="postcode" placeholder="우편번호" value="${member.member_postcode }" oninput="this.value=this.value.replace(/[^0-9]/g, '');">
					<input type="button" id="postbutton" onclick="execDaumPostcode()" value="우편번호 찾기">
				</div>
				<div class="row">
					<b>&nbsp;</b>
					<input type="text" id="roadAddress" name="roadAddress" placeholder="도로주소" value="${member.member_roadAddress }">
					<input type="text" id="jibunAddress" name="jibunAddress" size="23px" placeholder="지번주소" value="${member.member_jibunAddress }">
				</div>
				
				<div class="row">
					<b>상세주소</b>
					<textarea rows="5" cols="49" name="addressDetail" placeholder="상세주소">${member.member_addressDetail }</textarea>	
				</div>
				
				<!-- 버튼 -->
				<div class="row-button">
					<input type="submit" class="in-button"  value="회원정보변경" class="submit">
	  				<input type="button" class="in-button" id="btnDelete" value="회원탈퇴" class="cancel">			
					<input type="button" class="in-button" value="뒤로가기" onclick="history.back()">
				</div>
				<!-- 버튼 -->
			</div>
		</div>
	</form>
	<!-------------------------------------------------------------------------------------->
	  	
	<div class="clear"></div>
	
	<!-- 내 최근 정보 테이블 모음 -->
	<div class="container-fluid pt-5">
		<div class="row px-xl-5 pb-3">
		
			<!---------------------------------- 최근 상품 리뷰------------------------------------->
			<div class="col-lg-4 col-md-6 pb-1">
				<div class="cat-item d-flex flex-column border mb-4"
					style="padding: 30px;">
					<h4>내 상품리뷰</h4>
					<table>
						<tr>
							<th width="80">글번호</th>
							<th width="150">게시판</th>
							<th width="100">작성자</th>
							<th width="100">작성일</th>
						</tr>
						<tr>
							<td>12</td>
							<td>도서추천 게시판</td>
							<td>style</td>
							<td>22-12/15</td>
						</tr>
					</table>
				</div>
			</div>
			<!-------------------------------------------------------------------------------------->
			
			<!--------------------------------- 최근 게시판 댓글 ----------------------------------->
			<div class="col-lg-4 col-md-6 pb-1">
				<div class="cat-item d-flex flex-column border mb-4"
					style="padding: 30px;">
					<table>
						<h4>내 댓글</h4>
						<tr>
							<th width="80">글번호</th>
							<th width="150">게시판</th>
							<th width="100">작성자</th>
							<th width="100">작성일</th>
						</tr>
						<tr>
							<td>12</td>
							<td>도서추천 게시판</td>
							<td>style</td>
							<td>22-12/15</td>
						</tr>
					</table>
				</div>
			</div>
			<!-------------------------------------------------------------------------------------->

			<!-------------------------------- 최근 1:1 문의내역 ----------------------------------->
			<div class="col-lg-4 col-md-6 pb-1">
				<div class="cat-item d-flex flex-column border mb-4"
					style="padding: 30px;">
					<h4>1:1 문의내역</h4>
					<table>
						<tr>
							<th width="80">글번호</th>
							<th width="200">제목</th>
							<th width="100">작성일</th>
							<th width="100">상태</th>
						</tr>
						<tr>
							<td>4</td>
							<td>언제 배송되나요?</td>
							<td>22-12/15</td>
							<td>답변완료</td>
						</tr>
					</table>
				</div>
			</div>
			<!-------------------------------------------------------------------------------------->
			
			<!----------------------------------- 최근 주문내역 ------------------------------------>
			<div class="col-lg-4 col-md-6 pb-1">
				<div class="cat-item d-flex flex-column border mb-4"
					style="padding: 30px;">
					<h4>주문내역</h4>
					<table>
						<tr>
							<th width="80">주문번호</th>
							<th width="200">상품명</th>
							<th width="100">구매일</th>
							<th width="100">결제정보</th>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</table>
				</div>
			</div>
			<!-------------------------------------------------------------------------------------->
			
			<!-- 최근 정보 추가 할거면 위에 div 양식 그대로 복붙해서 만들고 정보 뿌리시면 됩니다! -->
			
			<!-------------------------------------------------------------------------------------->

		</div>
	</div>

	</article>
	
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