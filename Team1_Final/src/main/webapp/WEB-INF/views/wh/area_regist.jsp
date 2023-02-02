<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>내부 구역 추가</title>
<link href="css/default.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/resources/css/default.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

</head>
<body>
	<jsp:include page="../inc/top.jsp"></jsp:include>
	<div style="display: flex;">
		<div style="width: 300px; margin-top: 0px; margin-right: 0px; border-right:solid 1px; border-color: #BDBDBD;">
			<jsp:include page="../inc/regist_left.jsp"></jsp:include>
		</div>
		<!-- 여기서부터 본문-->
		<div align="center" style="width: 1300px;">
			<h2 align="center">내부 구역 등록</h2>
			<form action="WhAreaRegistPro" method="post">
			<table style="text-align: center; border: solid 1px; width: 600px; height: 400px;">
				<tr>
					<td align="right" style="width: 200px;">창고 코드</td> <!-- 입력 후 빠져나가면 중복확인 후 결과 표시하여 중복 방지 기능 적용 -->
					<td align="left" style="width: 400px;" >&nbsp;&nbsp;&nbsp;
						<input type="text" id="WH_CD" name="WH_CD" required="required" placeholder="창고코드">
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
						<input type="text" id="WH_NAME" name="WH_NAME" required="required">
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
						<input type="text" id="WH_POST_NO" name="WH_POST_NO" style="width: 100px;" readonly="readonly">
						<input type="button" id="postbutton" onclick="execDaumPostcode()" value="우편번호 찾기" >
					</td>
				</tr>
				<tr class="address">
					<td align="right" >주소</td>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<input type="text" id="WH_ADDR" name="WH_ADDR" style="width: 300px;" readonly="readonly">
					</td>
				</tr>
				<tr class="address">
					<td align="right" >상세주소</td>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<input type="text" id="WH_ADDR_DETAIL" name="WH_ADDR_DETAIL" style="width: 300px;">
					</td>
				</tr>
				
				
				<tr>
					<td align="right" >전화번호</td>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<input type="text" id="WH_TEL" name="WH_TEL" required="required">
					</td>
				</tr>
				<tr>
					<td align="right">관리자명</td>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<input type="text" id="WH_MAN_NAME" name="WH_MAN_NAME" required="required">
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
						<input type="text" id="REMARKS" name="REMARKS">
					</td>
				</tr>
				<tr>
				<td colspan="2" align="center">
					<input type="submit" value="창고등록">
					<input type="button" value="취소" onclick="history.back()">
				</td>
			</tr>
			</table>
			</form>
		</div>
		
		
</div>
</body>
</html>