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
		<h3>입고 처리</h3>
		<table>
			<tr>
				<th><input type="checkbox"></th>
				<th>입고예정번호</th>
				<th>보낸곳명</th>
				<th>품목명[규격]</th>
				<th>납기일자</th>
				<th>입고예정수량</th>
				<th>입고수량</th>
				<th>미입고수량</th>
				<th>적요</th>
			</tr>
		</table>
	</div>

</body>
</html>