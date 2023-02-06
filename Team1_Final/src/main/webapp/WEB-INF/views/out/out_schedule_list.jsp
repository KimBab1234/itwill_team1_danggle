<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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

button {
	color: #fff;
    background-color: #736643;
    cursor: pointer;
    height: 35px;
    width: 120px;
    font-size: 0.75rem;
    border-radius: 4px;
    border: none;
    display: block;
    margin-top: 10px;
    margin-left: 300px;
}
</style>
</head>
<body>
	<header>
		<jsp:include page="../inc/top.jsp"></jsp:include>
	</header>
	<jsp:include page="../inc/in_left.jsp"></jsp:include>
	
	<h3 id="outTitle">출고 예정</h3>
	<div align="center">
		<table>
			<tr>
				<th width="50px"><input type="checkbox"></th>
				<th width="300px">출고예정번호</th>
				<th width="300px">유형</th>
				<th width="300px">보낸곳명</th>
				<th width="300px">담당자명</th>
				<th width="300px">품목명[규격]</th>
				<th width="300px">납기일자</th>
				<th width="400px">출고예정수량합계</th>
				<th width="200px">종결여부</th>
				<th width="200px">진행상태</th>
				<th width="200px">인쇄</th>
			</tr>
		</table><br>
	</div>
	<button type="button" onclick="window.open('OutRegist', 'OutRegist', 'width=1250, height=600, left=200, top=300')">등록</button>
	
</body>
</html>