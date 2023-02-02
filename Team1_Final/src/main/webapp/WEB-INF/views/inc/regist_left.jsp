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
			<h2>기본 등록</h2>
			<h3 class="menuH3"><a href="#">거래처 등록</a></h3>
			<h3 class="menuH3"><a href="WhRegistForm">창고 등록</a></h3>
			<h3 class="menuH3"><a href="#">품목 등록</a></h3>
		</div>
	</div>
</body>
</html>