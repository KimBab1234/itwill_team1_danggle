<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<style>
.menuH3:hover{
	background: #FAED7D;
	cursor: pointer;
}
a:link{
	text-decoration: none;
	color: black;
}
a:visited{
	text-decoration: none;
	color: black;
}
</style>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center" style="width: 300px; margin-top: 0px;">
		<div style="margin-left: 20px; margin-right: 20px; ">
			<h2>인사 관리</h2>
			<h3 class="menuH3">
				<a href="HrRegist">사원 등록</a>
			</h3>
			<h3 class="menuH3">
				<a href="HrList">사원 조회</a>
			</h3>
		</div>
		<hr>
		<div style="margin-left: 20px; margin-right: 20px; ">
			<h2>기본 등록</h2>
			<h3 class="menuH3">
				<a href="AccRegist">거래처 등록</a>
				</h3>
			<h3 class="menuH3" onclick="location.href='WhList'">창고 등록</h3>
			<h3 class="menuH3">
				<a href="ItemRegist">품목 등록</a>
			</h3>
		</div>
		<hr>
		<div style="margin-left: 20px; margin-right: 20px; ">
			<h2>기본 조회</h2>
			<h3 class="menuH3">
				<a href="AccRegist">거래처 조회</a>
				</h3>
			<h3 class="menuH3" onclick="location.href='WhList'">창고 조회</h3>
			<h3 class="menuH3">
				<a href="ItemInquiry">품목 조회</a>
			</h3>
		</div>
		<hr>
		<div style="margin-left: 20px; margin-right: 20px; ">
			<h2>발주 요청</h2>
			<h3 class="menuH3">발주 요청 입력</h3>
			<h3 class="menuH3">발주 요청 조회</h3>
			<h3 class="menuH3">발주 요청 현황</h3>
		</div>
		<hr>
		<div style="margin-left: 20px; margin-right: 20px; ">
			<h2>구매</h2>
			<h3 class="menuH3">구매 입력</h3>
			<h3 class="menuH3">구매 조회</h3>
			<h3 class="menuH3">구매 현황</h3>
		</div>
		<hr>
		<div style="margin-left: 20px; margin-right: 20px; ">
			<h2>WMS</h2>
			<h3 class="menuH3" >창고 관리</h3>
			<h3 class="menuH3">입고 예정</h3>
			<h3 class="menuH3">입고 처리</h3>
			<h3 class="menuH3">출고 예정</h3>
			<h3 class="menuH3">출고 처리</h3>
			<h3 class="menuH3">재고 조정</h3>
		</div>
	</div>
</body>
</html>