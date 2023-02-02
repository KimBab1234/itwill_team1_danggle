<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<style>
#verticalNav{
	margin: 0;
	padding: 0;
	position: absolute;
	height: 100%;
	border-right:solid 1px; border-color: #BDBDBD;
	z-index: -1;
}

.menuH3:hover{
	background: #FAED7D;
	cursor: pointer;
}
.menuH3 > a:link{
	text-decoration: none;
	color: #000;
}
.menuH3 > a:visited{
	text-decoration: none;
	color: #000;
}
</style>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="verticalNav" align="center" style="width: 200px; margin-top: 0px;">
		<div style="margin-left: 20px; margin-right: 0px; ">
			<h2>입출고 관리</h2>
			<h3 class="menuH3">
				<a href="HrRegist">창고 관리</a>
			</h3>
			<h3 class="menuH3">
				<a href="IN_Schedule">입고 예정</a>
			</h3>
			<h3 class="menuH3">
				<a href="IN_Process">입고 처리</a>
			</h3>
			<h3 class="menuH3">
				<a href="OutSchList">출고 예정</a>
			</h3>
			<h3 class="menuH3">
				<a href="OutProList">출고 처리</a>
			</h3>
			<h3 class="menuH3">
				<a href="#">재고 조정</a>
			</h3>
		</div>
	</div>
</body>
</html>