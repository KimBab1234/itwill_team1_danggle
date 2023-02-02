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
		<h3>출고 처리</h3>
		<table>
			<tr>
				<th><input type="checkbox"></th>
				<th>출고예정번호</th>
				<th>보낸곳명</th>
				<th>품목명[규격]</th>
				<th>품목별납기일자</th>
				<th>출고예정수량</th>
				<th>미출고수량</th>
				<th>출고대기수량</th>
				<th>출고지시수량</th>
				<th>적요</th>
			</tr>
		</table>
	</div>

</body>
</html>