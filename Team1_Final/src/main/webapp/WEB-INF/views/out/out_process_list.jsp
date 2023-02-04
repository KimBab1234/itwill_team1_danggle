<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath }/resources/css/out.css" rel="stylesheet" type="text/css" />
<style type="text/css">
#outTitle {
	margin-left: 300px;
}

table{
	margin: 0 auto;
	margin-left: 300px;
	border-collapse: collapse;
	border-style: solid;
	border-color: #b09f76;;
	width: 1000px;
}

th{
	border: 1px solid; 
 	background: #c9b584; 
 	text-align: center; 
 	border-color: #b09f76;
 	color: #736643;
 	font-size: 15px;
 	padding-top: 10px;
 	padding-bottom: 10px;
}

td {
	border-style: solid;
	border-width: 0.5px;
 	border-color: #b09f76;
 	padding: 8px 0px 8px 4px;
 	font-size: 15px;
}
</style>
</head>
<body>
	<header>
		<jsp:include page="../inc/top.jsp"></jsp:include>
	</header>
	<jsp:include page="../inc/in_left.jsp"></jsp:include>
	
	<h3 id="outTitle">출고 처리</h3>
	<div align="center">
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