<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css">
<style type="text/css">
table{
	border: 1px solid;
}

th{
	border-left: 1px solid;
}
</style>
</head>
<body>
	<header>
		<jsp:include page="../inc/top.jsp"></jsp:include>
	</header>
	<jsp:include page="../inc/in_left.jsp"></jsp:include>
	<div align="center">
		<h3>출고 예정</h3>
		<table>
			<tr>
				<th><input type="checkbox"></th>
				<th>출고예정번호</th>
				<th>유형</th>
				<th>보낸곳명</th>
				<th>담당자명</th>
				<th>품목명[규격]</th>
				<th>납기일자</th>
				<th>출고예정수량합계</th>
				<th>종결여부</th>
				<th>진행상태</th>
				<th>인쇄</th>
			</tr>
		</table><br>
		<button onclick="window.open('OutRegist', 'OutRegist', 'width=800, height=500, left=600, top=400')">등록</button>
	</div>
</body>
</html>