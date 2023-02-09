<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.min.js"></script>

<style type="text/css">
table {
	font-size: 20px;
}
input[type=text]{
	width: 150px;
	height: 30px;
	font-size: 18px;
	 border: 0;
  border-radius: 15px;
  outline: none;
  padding-left: 10px;
  background-color: rgb(233, 233, 233);
  text-align: center;
}
input[type=password]{
	width: 150px;
	height: 30px;
	font-size: 18px;
	 border: 0;
  border-radius: 15px;
  outline: none;
  padding-left: 10px;
  background-color: rgb(233, 233, 233);
  text-align: center;
}
input[type=button]{
	width: 70px;
	height: 30px;
	font-size: 13px;
	font-weight : bold;
	 border: 0;
  border-radius: 15px;
  outline: none;
  padding-left: 10px;
  text-align: center;
}
input[type=submit]{
	width: 70px;
	height: 30px;
	font-size: 13px;
	font-weight : bold;
	 border: 0;
  border-radius: 15px;
  outline: none;
  padding-left: 10px;
  text-align: center;
}
textarea{
	width: 550px;
	height: 200px;
	font-size: 18px;
	 border: 0;
  border-radius: 15px;
  outline: none;
  padding-left: 10px;
  background-color: rgb(233, 233, 233);
}
h2 {
	font-size: 30px;
}
</style>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
	function execDaumPostcode() {
		new daum.Postcode({
			oncomplete : function(data) {
				// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

				// 각 주소의 노출 규칙에 따라 주소를 조합한다.
				// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
				var addr = ''; // 주소 변수
				var extraAddr = ''; // 참고항목 변수

				//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
				if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
					addr = data.roadAddress;
				} else { // 사용자가 지번 주소를 선택했을 경우(J)
					addr = data.jibunAddress;
				}

				// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
				if (data.userSelectedType === 'R') {
					// 법정동명이 있을 경우 추가한다. (법정리는 제외)
					// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
					if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
						extraAddr += data.bname;
					}
					// 건물명이 있고, 공동주택일 경우 추가한다.
					if (data.buildingName !== '' && data.apartment === 'Y') {
						extraAddr += (extraAddr !== '' ? ', '
								+ data.buildingName : data.buildingName);
					}

				}
				// 우편번호와 주소 정보를 해당 필드에 넣는다.
				document.getElementById('POST_NO').value = data.zonecode;
				document.getElementById("ADDR1").value = addr;
			}
		}).open();
	}

	$(function() {
		// 이메일 도메인 선택 시 두번째 항목에 도메인 자동 입력
		$("#domain").on("change", function() {
			$("#EMAIL2").val($(this).val());

			// 도메인이 널스트링("")일 경우 입력창 잠금 해제, 아니면 입력창 잠금
			if ($(this).val() == "") {
				$("#EMAIL2").prop("readonly", false); // 잠금 해제
				$("#EMAIL2").css("background-color", "white"); // 흰색 변경
				$("#EMAIL2").focus(); // 포커스 요청
			} else {
				$("#EMAIL2").prop("readonly", true); // 잠금
				$("#EMAIL2").css("background-color", "lightgray"); // 회색 변경
			}
		});
	});

	$(function() {
		// 담당자 이메일 도메인 선택 시 두번째 항목에 도메인 자동 입력
		$("#domain2").on("change", function() {
			$("#MAN_EMAIL2").val($(this).val());

			// 도메인이 널스트링("")일 경우 입력창 잠금 해제, 아니면 입력창 잠금
			if ($(this).val() == "") {
				$("#MAN_EMAIL2").prop("readonly", false); // 잠금 해제
				$("#MAN_EMAIL2").css("background-color", "white"); // 흰색 변경
				$("#MAN_EMAIL2").focus(); // 포커스 요청
			} else {
				$("#MAN_EMAIL2").prop("readonly", true); // 잠금
				$("#MAN_EMAIL2").css("background-color", "lightgray"); // 회색 변경
			}
		});
	});
	
	
	$(function() {
		// 거래처코드 중복 체크
		$("#BUSINESS_NO3").on("change",function(){
// 		var business_no = $("#BUSINESS_NO1").val();+"-"+$("#BUSINESS_NO2").val()+"-"+$("#BUSINESS_NO3").val();
			$.ajax({
				type:"GET",
				url: "accCheckBusinessNo",
				data: {
					"BUSINESS_NO1": $("#BUSINESS_NO1").val(),
					"BUSINESS_NO2": $("#BUSINESS_NO2").val(),
					"BUSINESS_NO3": $("#BUSINESS_NO3").val()
				},
				success: function(data) {
					if(data == "true"){
						$("#accResult").html("이미 존재하는 거래처").css("color", "red");
					}else {
						$("#accResult").html("등록 가능한 거래처").css("color", "blue");	
					}	
				}
			});
		});
		
		///주민번호, 사업자번호 등등 선택했을때 바꾸는 부분
		$("input[type=radio][name=G_GUBUN]").on("click", function() {
			if($(this).val()=="03") {
				$("#busiArea").css("display","none");
				$("#BUSINESS_NO1").prop("maxlength","6");
				$("#BUSINESS_NO2").prop("maxlength","7");
				$("#BUSINESS_NO2").prop("type","password");
				$("#BUSINESS_NO1").prop("size","10");
				$("#BUSINESS_NO2").prop("size","10");
				$("#BUSINESS_NO3").prop("required",false)
				$("#BUSINESS_NO1").val('');
				$("#BUSINESS_NO2").val('');
				$("#BUSINESS_NO3").val('');
				$("#BUSINESS_NO2").on("change",function(){
//			 		var business_no = $("#BUSINESS_NO1").val();+"-"+$("#BUSINESS_NO2").val()+"-"+$("#BUSINESS_NO3").val();
						$.ajax({
							type:"GET",
							url: "accCheckBusinessNo",
							data: {
								"BUSINESS_NO1": $("#BUSINESS_NO1").val(),
								"BUSINESS_NO2": $("#BUSINESS_NO2").val(),
								"BUSINESS_NO3": $("#BUSINESS_NO3").val()
							},
							success: function(data) {
								if(data == "true"){
									$("#accResult").html("이미 존재하는 거래처").css("color", "red");
								}else {
									$("#accResult").html("등록 가능한 거래처").css("color", "blue");	
								}	
							}
						});
					});
			} else {
				$("#busiArea").css("display","inline-block");
				$("#BUSINESS_NO1").prop("maxlength","3");
				$("#BUSINESS_NO2").prop("maxlength","2");
				$("#BUSINESS_NO2").prop("type","text");
				$("#BUSINESS_NO3").prop("required",true)
				$("#BUSINESS_NO1").val('');
				$("#BUSINESS_NO2").val('');
				$("#BUSINESS_NO3").val('');
			}
		});
		
	}); // ready 끝
</script>
</head>
<body>

	<jsp:include page="../inc/top.jsp"></jsp:include>
	<div style="display: flex;">
		<div
			style="width: 300px; margin-top: 0px; margin-right: 0px; border-right: solid 1px; border-color: #BDBDBD;">
			<jsp:include page="../inc/acc_left.jsp"></jsp:include>
		</div>

		<div align="center">
			<h2>거래처 등록</h2>
			<form action="accregistPro" method="post" name="accRegistForm">
				<table>
					<tr>
						<td>회사명 &nbsp;</td>
						<td colspan="4"><input type="text" name="CUST_NAME"
							id="CUST_NAME" required="required"></td>
					</tr>
					<tr>
						<td>대표자명</td>
						<td colspan="4"><input type="text" name="BOSS_NAME"
							id="BOSS_NAME" required="required"></td>
					</tr>
					<tr>
						<td>거래처코드구분 &nbsp;</td>
						<td colspan="4"><input type="radio" name="G_GUBUN" value="01"
							checked="checked">사업자등록번호 <input type="radio"
							name="G_GUBUN" value="02">해외사업자등록번호 <input type="radio"
							name="G_GUBUN" value="03">주민등록번호
							 <input type="radio"
							name="G_GUBUN" value="04">외국인&nbsp;</td>
					</tr>
					<tr>
						<td>거래처코드</td>
						<td>
						<input type="text" name="BUSINESS_NO1" id="BUSINESS_NO1"
							required="required" size="7" maxlength="3">-<input
							type="text" name="BUSINESS_NO2" id="BUSINESS_NO2"
							required="required" size="7" maxlength="2"><span id="busiArea">-<input
							type="text" name="BUSINESS_NO3" id="BUSINESS_NO3"
							required="required" maxlength="5"></span>
							<span id="accResult"><!-- 거래처코드 중복 확인 메세지 표시 --></span>
							</td>
					</tr>
					<tr>
						<td>업태 &nbsp;</td>
						<td colspan="4"><input type="text" name="UPTAE" id="UPTAE"
							required="required"></td>
					</tr>
					<tr>
						<td>종목</td>
						<td colspan="4"><input type="text" name="JONGMOK"
							id="JONGMOK" required="required"></td>
					</tr>
					<tr>
						<td>대표전화번호 &nbsp;</td>
						<td colspan="4"><input type="text" name="TEL1" id="TEL1"
							size="7" maxlength="3">-<input type="text" name="TEL2"
							id="TEL2" size="7" maxlength="4">-<input type="text"
							name="TEL3" id="TEL3" size="7" maxlength="4"></td>
					</tr>
					<tr>
						<td>모바일</td>
						<td colspan="4"><input type="text" name="MOBILE_NO1"
							id="MOBILE_NO1" size="7" maxlength="3">-<input
							type="text" name="MOBILE_NO2" id="MOBILE_NO2" size="7"
							maxlength="4">-<input type="text" name="MOBILE_NO3"
							id="MOBILE_NO3" size="7" maxlength="4"></td>
					</tr>
					<tr>
						<td>E-Mail</td>
						<td colspan="4"><input type="text" name="EMAIL1" id="EMAIL1"
							size="10" maxlength="20">@<input type="text"
							name="EMAIL2" id="EMAIL2" size="10"> <select
							name="selectDomain" id="domain">
								<option value="">직접입력</option>
								<option value="naver.com">naver.com</option>
								<option value="daum.net">daum.net</option>
								<option value="google.com">google.com</option>
						</select></td>
					</tr>
					<tr>
						<td>우편번호 &nbsp;</td>
						<td colspan="3"><input type="text" name="POST_NO"
							id="POST_NO" placeholder="우편번호" required="required"
							readonly="readonly" size="15"> <input type="button"
							id="postbutton" onclick="execDaumPostcode()" value="주소찾기"></td>
					</tr>
					<tr>
						<td>주소 &nbsp;</td>
						<td colspan="4"><input type="text" name="ADDR1" id="ADDR1"
							readonly="readonly" size="50px" required="required"></td>
					</tr>
					<tr>
						<td>상세주소</td>
						<td colspan="4"><input type="text" name="ADDR2" id="ADDR2"
							required="required" maxlength="20"></td>
					</tr>
					<tr>
						<td>홈페이지</td>
						<td colspan="4"><input type="text" name="URL_PATH"
							id="URL_PATH"></td>
					</tr>
					<tr>
						<td>팩스</td>
						<td colspan="4"><input type="text" name="FAX" id="FAX"></td>
					</tr>
					<tr>
						<td>적요</td>
						<td colspan="4"><textarea cols="50" rows="10" name="REMARKS"
								id="REMARKS"></textarea></td>
					</tr>
					<tr>
						<td>담당자명</td>
						<td colspan="4"><input type="text" name="MAN_NAME"
							id="MAN_NAME"></td>
					</tr>
					<tr>
						<td>담당자 전화번호</td>
						<td colspan="4"><input type="text" name="MAN_TEL1" id="MAN_TEL1"
							size="7" maxlength="3">-<input type="text" name="MAN_TEL2"
							id="MAN_TEL2" size="7" maxlength="4">-<input type="text"
							name="MAN_TEL3" id="MAN_TEL3" size="7" maxlength="4"></td>
					</tr>
					<tr>
						<td>담당자 이메일</td>
						<td colspan="4"><input type="text" name="MAN_EMAIL1"
							id="MAN_EMAIL1" size="10">@<input type="text"
							name="MAN_EMAIL2" id="MAN_EMAIL2" size="10"> <select
							name="selectDomain" id="domain2">
								<option value="">직접입력</option>
								<option value="naver.com">naver.com</option>
								<option value="daum.net">daum.net</option>
								<option value="google.com">google.com</option>
						</select></td>
					</tr>
					<tr align="center">
						<td colspan="5"><input type="submit" value="등록">
						<input type="button" value="취소" onclick="location.href='AccList'">
						</td>
					</tr>
				</table>
			</form>
		</div>

		<!-- 여기까지 본문-->
	</div>
</body>
</html>