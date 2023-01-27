<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<style type="text/css">

</style>
</head>
<body>
	<jsp:include page="../inc/top.jsp"></jsp:include>
	<div style="display: flex;">
		<div style="width: 300px; margin-top: 0px; margin-right: 0px; border-right:solid 1px; border-color: #BDBDBD;">
			<jsp:include page="../inc/left.jsp"></jsp:include>
		</div>
		<!-- 여기서부터 본문-->
		<div align="center" style="width: 1000px">
		<h2>품목 등록</h2>
		
		<table border="1">
			<tr>
				<td >품목코드</td>
				<td></td>
			</tr>
			<tr>
				<td>품목명</td>
				<td></td>
			</tr>
			<tr>
				<td>품목그룹</td>
				<td></td>
			</tr>
			<tr>
				<td>규격</td>
				<td></td>
			</tr>
			<tr>
				<td>바코드</td>
				<td></td>
			</tr>
			<tr>
				<td>입고단가</td>
				<td></td>
			</tr>
			<tr>
				<td>출고단가</td>
				<td></td>
			</tr>
			<tr>
				<td>품목구분</td>
				<td></td>
			</tr>
			<tr>
				<td>대표이미지</td>
				<td></td>
			</tr>
		</table>
		</div>
	</div>
</body>
</html>