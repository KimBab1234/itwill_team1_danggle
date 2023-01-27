<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
<%------------------------------- 주소찾기 API -------------------------------%>
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
               document.getElementById('EMP_POST_NO').value = data.zonecode;
               document.getElementById("EMP_ADDR").value = roadAddr;
               
           }
       }).open();
	<%-- 주소검색 팝업창 --%>
   }
<%----------------------------------------------------------------------------%>

	function emailSelect(domain){
		if(domain=='') {
			$("#EMP_EMAIL2").val(domain);
			$("#EMP_EMAIL2").prop("readOnly",false);
			$("#EMP_EMAIL2").css("background","white");
			
		} else {
			$("#EMP_EMAIL2").val(domain);
			$("#EMP_EMAIL2").prop("readOnly",true);
			$("#EMP_EMAIL2").css("background","lightgray");
		}
	}

</script>
</head>
<body>
	<jsp:include page="../inc/top.jsp"></jsp:include>
	<div style="display: flex;">
		<div style="width: 300px; margin-top: 0px; margin-right: 0px; border-right:solid 1px; border-color: #BDBDBD;">
			<jsp:include page="../inc/left.jsp"></jsp:include>
		</div>
		<!-- 여기서부터 본문-->
		<div align="center" style="width: 1300px;">
			<h2 align="center">사원 등록</h2>
			<table style="text-align: center; border: solid 1px;">
				<tr>
					<td align="right" >이름</td>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<input type="text" id="EMP_NAME" name="EMP_NAME" >
					</td>
				</tr>
				<tr>
					<td align="right" >부서코드</td>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<input type="text" id="DEPT_CD" name="DEPT_CD" >
					</td>
				</tr>
				<tr>
					<td align="right" >직급코드</td>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<input type="text" id="GRADE_CD" name="GRADE_CD" >
					</td>
				</tr>
				<tr>
					<td align="right" >연락처(개인)</td>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<input type="text" id="EMP_TEL" name="EMP_TEL" >
					</td>
				</tr>
				<tr>
					<td align="right" >연락처(사무실)</td>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<input type="text" id="EMP_DTEL" name="EMP_DTEL" >
					</td>
				</tr>
				<tr>
					<td align="right" >E-mail</td>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<input type="text" id="EMP_EMAIL1" name="EMP_EMAIL1" style="width: 100px;"> @
						<input type="text" id="EMP_EMAIL2" name="EMP_EMAIL2" style="width: 100px;">
						<select onchange="emailSelect(this.value)">
							<option value="">직접 입력</option>
							<option value="naver.com">naver.com</option>
							<option value="gmail.com">gmail.com</option>
							<option value="nate.com">nate.com</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">우편번호</td>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<input type="text" id="EMP_POST_NO" name="EMP_POST_NO" style="width: 100px;" readonly="readonly">
						<input type="button" id="postbutton" onclick="execDaumPostcode()" value="우편번호 찾기" >
					</td>
				</tr>
				<tr>
					<td align="right" >주소</td>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<input type="text" id="EMP_ADDR" name="EMP_ADDR" style="width: 300px;" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td align="right" >상세주소</td>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<input type="text" id="EMP_ADDR_detail" name="EMP_ADDR_detail" style="width: 300px;">
					</td>
				</tr>
				<tr>
					<td align="right" >입사일</td>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<input type="date" id="HIRE_DATE" name="HIRE_DATE" >
					</td>
				</tr>
				<tr>
					<td align="right" >재직여부</td>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<select id="WORK_CD" name="WORK_CD" >	
							<option value="1">재직</option>
							<option value="2">휴직</option>
							<option value="3">퇴사</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right" >권한</td>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<select id="PRIV_CD" name="PRIV_CD" >	
							<option value="0">기본등록</option>
							<option value="1">사원조회</option>
							<option value="2">사원관리</option>
							<option value="3">재고조회</option>
							<option value="4">재고관리</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right" >사진이미지</td>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<input type="file" id="PHOTO" name="PHOTO" >
					</td>
				</tr>
			</table>
		</div>
		<!-- 여기까지 본문-->
	</div>
</body>
</html>