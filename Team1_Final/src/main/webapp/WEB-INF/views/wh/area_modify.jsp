<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>내부 구역 수정</title>
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
			<h2 align="center">내부 구역 이름 변경</h2>
			<form action="WhAreaModifyPro" method="post">
			<input type="hidden" name="WH_AREA_CD" value="${param.WH_AREA_CD}">
			<table style="text-align: center; border: solid 1px; width: 500px; height: 150px;">
				<tr>
					<td align="right" style="width: 200px;">구역명</td> 
					<td align="left" style="width: 400px;" >&nbsp;&nbsp;&nbsp;
						<input type="text" id="WH_AREA" name="WH_AREA" required="required" placeholder="${wh.WH_AREA }">
					</td>
				</tr> 
				<tr>
					
				<td colspan="2" align="center">
					<input type="submit" value="구역명 등록">
					<input type="button" value="취소" onclick="history.back()">
				</td>
			</tr>
			</table>
			</form>
		</div>
		
		
</div>
</body>
</html>