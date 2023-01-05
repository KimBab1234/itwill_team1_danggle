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
		<h1>
			<img src="img/green_Acon.jpg" width="20" height="25">
			<img src="img/normal_Acon.jpg" width="20" height="25">
			<img src="img/gold_Acon.jpg" width="20" height="25">
			<img src="img/daram.jpg" width="30" height="45">
			 마이페이지  
			<img src="img/daram2.jpg" width="30" height="45">
			<img src="img/green_Acon.jpg" width="20" height="25">
			<img src="img/normal_Acon.jpg" width="20" height="25">
			<img src="img/gold_Acon.jpg" width="20" height="25">
		</h1>
		
		<form action="MemberUpdatePro.me" method="post" id="join" name="fr">
	  		<fieldset>
	  			<legend>회원정보</legend>
	  			<label>아이디</label>
	  			<input type="text" name="id" class="id" id="id" value="${member.member_id }" readonly="readonly"><br>
	  			
	  			<label>기존 비밀번호</label>
	  			<input type="password" name="oldPasswd" id="oldPasswd" required="required" placeholder="패스워드 입력"><br> 			
	
	  			<label>변경할 비밀번호</label>
	  			<input type="password" name="newPasswd" id="newPasswd" placeholder="변경시에만 입력"><br> 			
	  			
	  			<label>변경할 비밀번호 재입력</label>
	  			<input type="password" name="newPasswd2" placeholder="변경시에만 입력"><br>
	  			
	  			<label>이름</label>
	  			<input type="text" name="name" id="name" required="required" value="${member.member_name }"><br>
	  			
	  			<label>E-Mail</label>
				<input type="text" class="in-e" name="email1" id="email1" value="${requestScope.email1 }" required="required" size="10"> <strong>&nbsp;@&nbsp;</strong> 
				<input type="text" class="in-e2" name="email2" id="email2" value="${requestScope.email2 }" required="required" size="10" placeholder="직접입력">
					<select name="email" id="selectDomain" >
					 	<option value="">직접입력</option>
					 	<option value="naver.com">naver.com</option>
					 	<option value="nate">nate.com </option>
					 	<option value="daum.net">daum.net</option>
					 	<option value="gmail.com">gmail.com</option>
					 </select>
	  		</fieldset>
		  		
	  		<fieldset>
	  			<legend>배송정보</legend>
	  			<label>주소</label>
					<input type="text" id="postcode" name="postcode" placeholder="우편번호" value="${member.member_postcode }">
					<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
					<input type="text" id="roadAddress" name="roadAddress" placeholder="도로주소" value="${member.member_roadAddress }">
					<input type="text" id="jibunAddress" name="jibunAddress" placeholder="지번주소" value="${member.member_jibunAddress }"><br>
					<textarea rows="5" cols="52" name="addressDetail" placeholder="상세주소">${member.member_addressDetail }</textarea><br>
	  			<label>전화번호</label>
	  			<input type="text" name="phone" value="${member.member_phone }"><br>
	  		</fieldset>
	  		<div class="clear"></div>
	  		<div id="buttons">
	  			<input type="submit" value="회원정보변경" class="submit">
	  			<input type="button" id="btnDelete" value="회원탈퇴" class="cancel">
	  		</div>
	  	</form>
		<section>
			<h4>최근 주문내역</h4>
			<table>
				<tr>
					<th width="100">주문일자</th>
					<th width="100">주문번호</th>
					<th width="100">주문내역</th>
					<th width="100">주문상태</th>
					<th>배송</th>
				</tr>
				<tr>
					<td>2022-12-15</td>
					<td>00124584</td>
					<td>화폐전쟁</td>
					<td>결제완료</td>
					<td>배송준비중</td>
				</tr>
			</table>
		</section>
		
		<%-- --------------------------------------------------------------------- --%>
		
		<section>
			<h4>리뷰 / 한줄평 리워드</h4>
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
		</section>
		
		<%-- --------------------------------------------------------------------- --%>
		
		<section>
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
		</section>
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