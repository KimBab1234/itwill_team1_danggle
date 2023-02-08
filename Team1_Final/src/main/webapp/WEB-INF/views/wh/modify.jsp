<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>창고 수정</title>
<link href="css/default.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/resources/css/default.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">

$(function() {
	$("#WH_CD").on("change", function() {
		let WH_CD = $("#WH_CD").val();
		$.ajax({
			type : 'POST', 
			 data: {
				 WH_CD : WH_CD
			},
			 url: "codeCheck",
			 dataType: 'text',
			 success: function(count) { 
				 if(count > 0){
					 $("#checkCdResult").html("이미 존재하는 창고코드입니다!").css("color", "red");
				 }else{
					 $("#checkCdResult").html("사용 가능한 창고코드입니다!").css("color", "blue");
				 }
	      	},
	      	error: function(count) {
	      	    alert("창고 코드를 입력해주세요.");
	      	}
		});

	});
		$(".WH_LOCATION").on("click", function() {
			
			if($(this).val() == 1) {
				$(".address").css("display","none");
			} else{
				$(".address").css("display","table-row");
			}
			
		});
		$(".address").css("display","none");
});
	
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
               document.getElementById('WH_POST_NO').value = data.zonecode;
               document.getElementById("WH_ADDR").value = roadAddr;
               
           }
       }).open();
	<%-- 주소검색 팝업창 --%>
   }
</script>
<link href="${pageContext.request.contextPath }/resources/css/hr.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.hrFormBtn {
	background-color: #736643;
	border: none;
	cursor: pointer;
	color: #fff;
	height: 27px;
	width: 55px;
	border-radius: 4px;
	font-size: 15px;
}

.Btn {
	background-color: #736643;
	border: none;
	cursor: pointer;
	color: #fff;
	height: 27px;
	width: 100px;
	border-radius: 4px;
	font-size: 15px;
}
</style>
</head>
<body>
	<jsp:include page="../inc/top.jsp"></jsp:include>
	<div style="display: flex;">
		<div style="width: 300px; margin-top: 0px; margin-right: 0px; border-right:solid 1px; border-color: #BDBDBD;">
			<jsp:include page="../inc/regist_left.jsp"></jsp:include>
		</div>
		<!-- 여기서부터 본문-->
		<div align="center" style="width: 1300px;">
			<h2 align="center">창고 수정</h2>
			<form action="WhModifyPro" method="post">
			<table style="text-align: center; border: solid 1px; width: 600px; height: 400px;">
				<tr>
					<td align="right" style="width: 200px;">창고 코드</td> <!-- 입력 후 빠져나가면 중복확인 후 결과 표시하여 중복 방지 기능 적용 -->
					<td align="left" style="width: 400px;" >&nbsp;&nbsp;&nbsp;
						<input type="text" id="WH_CD" name="WH_CD" required="required" value="${param.WH_CD}">
					</td>
				</tr> 
				<tr>
					<td align="left">
						<span id="checkCdResult"><!-- 자바스크립트에 의해 메세지가 표시될 공간 --></span>
					</td>
				</tr>
				<tr>
					<td align="right" >창고명</td>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<input type="text" id="WH_NAME" name="WH_NAME" required="required" value="${wh.WH_NAME}">
					</td>
				</tr>
				<tr>
					<td align="right" >구분(창고,공장 선택)</td><!-- 1: 창고 2: 공장 -->
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<input type="radio" name="WH_GUBUN" value="1" checked/>창고
						<input type="radio" name="WH_GUBUN" value="2"/>공장
					</td>
				</tr>
				<tr>
					<td align="right" >위치(내부,외부 선택)</td><!-- 외부 선택시 주소 입력 항목 표시 -->
					<!-- 1: 내부 2: 외부 -->
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<input type="radio"  name="WH_LOCATION" class="WH_LOCATION" value="1" checked/>내부
						<input type="radio" name="WH_LOCATION" class="WH_LOCATION" value="2"/>외부
						
					</td>
				</tr>
				<tr class="address">
					<td align="right">우편번호</td>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<input type="text" id="WH_POST_NO" name="WH_POST_NO" style="width: 100px;" readonly="readonly" value="${wh.WH_POST_NO}">
						<input type="button" id="postbutton" onclick="execDaumPostcode()" value="우편번호 찾기" >
					</td>
				</tr>
				<tr class="address">
					<td align="right" >주소</td>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<input type="text" id="WH_ADDR" name="WH_ADDR" style="width: 300px;" readonly="readonly" value="${wh.WH_ADDR}">
					</td>
				</tr>
				<tr class="address">
					<td align="right" >상세주소</td>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<input type="text" id="WH_ADDR_DETAIL" name="WH_ADDR_DETAIL" style="width: 300px;" value="${wh.WH_ADDR_DETAIL}">
					</td>
				</tr>
				
				
				<tr>
					<td align="right" >전화번호</td>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<input type="text" id="WH_TEL" name="WH_TEL" required="required" value="${wh.WH_TEL}">
					</td>
				</tr>
				<tr>
					<td align="right">관리자명</td>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<input type="text" id="WH_MAN_NAME" name="WH_MAN_NAME" required="required" value="${wh.WH_MAN_NAME}">
					</td>
				</tr>
				<tr>
					<td align="right" >사용여부</td><!--  1:사용 2:미사용 -->
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<input type="radio" name="WH_USE" value="1" checked/>사용
						<input type="radio" name="WH_USE" value="2"/>미사용
					</td>
				</tr>
				<tr>
					<td align="right">적요</td>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<input type="text" id="REMARKS" name="REMARKS" value="${wh.REMARKS}">
					</td>
				</tr>
				<tr>
				<td colspan="2" align="center">
					<input type="submit" class="Btn" value="창고등록">
					<input type="button" class="hrFormBtn" value="취소" onclick="history.back()">
				</td>
			</tr>
			</table>
			</form>
		</div>
		
		
</div>
</body>
</html>