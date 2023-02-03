<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입고예정</title>
<link rel="shortcut icon" href="#">
<link href="${pageContext.request.contextPath }/resources/css/in.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div>
		<header>
			<jsp:include page="../inc/top.jsp"></jsp:include>
		</header>
		<jsp:include page="../inc/in_left.jsp"></jsp:include>
	</div>
	<div style="width:1900px;">
		<h3>입고 예정</h3>
		<div class="choice_in">전체</div><div class="choice_in">진행중</div><div class="choice_in">완료</div>
		<table class="bookTable">
			<tr>
				<th><input type="checkbox"></th>
				<th>입고예정번호</th>
				<th>유형</th>
				<th>보낸곳명</th>
				<th>담당자명</th>
				<th>품목명[규격]</th>
				<th>납기일자</th>
				<th>입고예정수량합계</th>
				<th>종결여부</th>
				<th>진행상태</th>
				<th>인쇄</th>
			</tr>
		</table><br>
		<button onclick="window.open('IncomingRegistration', 'incomeRegi', 'width=1000, height=700, left=600, top=400')">등록</button>
	</div>
</body>
</html>