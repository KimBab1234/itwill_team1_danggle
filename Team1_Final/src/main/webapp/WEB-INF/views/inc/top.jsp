<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>

<link rel="stylesheet"
	href="http://netdna.bootstrapcdn.com/font-awesome/4.6.2/css/font-awesome.min.css">
	
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style>
@import url(https://fonts.googleapis.com/css?family=Roboto:400,700,500);

/* main Styles */
html {
	box-sizing: border-box;
}

*, *:before, *:after {
	box-sizing: inherit;
}

body {
	background: #fafafa;
	font-family: "Roboto", sans-serif;
	font-size: 14px;
	margin: 0;
}

a {
	text-decoration: none;
	vertical-align: top;
}

a:link {
	text-decoration: none;
}

a:visited {
	text-decoration: none;
}

#titleName>a, #login:link {
	text-decoration: none;
	color: black;
}

#titleName>a, #login:visited {
	text-decoration: none;
	color: black;
}

.container {
	width: 1000px;
	margin: auto;
}

h1 {
	text-align: center;
	margin-top: 150px;
}

/* Navigation Styles */
nav {
	background: #513e30; <%-- #2ba0db --%>
}

nav ul {
	font-size: 0;
	margin: 0;
	padding: 0;
}

nav ul li {
	display: inline-block;
	position: relative;
}

nav ul li a {
	color: #fae37d; <%-- #fff --%>
	display: block;
	font-size: 14px;
	padding: 15px 14px;
	transition: 0.3s linear;
}

nav ul li:hover {
	background: #b09f76;  <%-- #126d9b --%>
}

nav ul li ul {
	border-bottom: 5px solid #fae37d; <%-- #2ba0db --%>
	display: none;
	position: absolute;
	width: 250px;
}

nav ul li ul li {
	border-top: 1px solid #444;
	display: block;
}

nav ul li ul li:first-child {
	border-top: none;
}

nav ul li ul li a {
	background: #373737;
	display: block;
	padding: 10px 14px;
}

nav ul li ul li a:hover {
	background: #b09f76;
}

nav .fa.fa-angle-down {
	margin-left: 6px;
}
</style>
</head>

<body>
	<h1 align="center" id="titleName">
		<a href="/">댕글댕글 창고 재고관리</a>
	</h1>
	<h3 align="right" style="vertical-align: text-bottom;">
<%-- 		<c:choose> --%>
<%-- 			<c:when test="${empty sessionScope.sId}"> --%>
<!-- 				<a href="#" style="color: black;">로그인</a> -->
<!-- 				<a href="#" style="font-size: 20px; text-align: center;"><i class="material-icons" style="color:black;">&#xe899;</i></a> -->
<%-- 			</c:when> --%>
<%-- 			<c:otherwise> --%>
				<a href="#" style="color: black;">${sessionScope.sId}님</a>
				<a href="#"><i class="material-icons" style="color:black;">&#xe898;</i></a>&nbsp;&nbsp;|&nbsp;&nbsp;
				<a href="#"><i style="font-size:24px; color: black;" class="fa">&#xf2c0;</i></a>&nbsp;&nbsp;|&nbsp;&nbsp;
				<a href="#"><i style="font-size:24px; color: black;" class="fa">&#xf2bc;</i></a>&nbsp;&nbsp;|&nbsp;&nbsp;
				<a href="#"><i class="material-icons" style="color: black;">&#xe0e1;</i></a>
<%-- 			</c:otherwise> --%>
<%-- 		</c:choose> --%>
	</h3>
	<hr style="margin: 0px;">
	<nav>
		<div class="container">
			<ul>
				<li><a href="/">Home</a></li>
				<li><a href="#">회사 연혁</a></li>
				<li><a href="#">인사 관리<i class='fa fa-angle-down'></i></a>
					<ul>
						<li><a href="HrRegist">사원 등록</a></li>
						<li><a href="HrInquiry">사원 조회</a></li>
					</ul>
				</li>
				<li><a href="#">거래처 관리<i class='fa fa-angle-down'></i></a>
					<ul>
						<li><a href="#">거래처 등록</a></li>
						<li><a href="#">거래처 조회</a></li>
					</ul>
				</li>
				<li><a href="#">품목 관리<i class='fa fa-angle-down'></i></a>
					<ul>
						<li><a href="PdRegist">품목 등록</a></li>
						<li><a href="PdInquiry">품목 조회</a></li>
					</ul>
				</li>
				<li><a href="#">창고 관리<i class='fa fa-angle-down'></i></a>
					<ul>
						<li><a href="WhRegistForm">창고 등록</a></li>
						<li><a href="#">창고 조회</a></li>
					</ul>
				</li>
				<li><a href="#">발주 요청<i class='fa fa-angle-down'></i></a>
					<ul>
						<li><a href="#">발주 요청 관리</a></li>
						<li><a href="#">발주 요청 조회</a></li>
						<li><a href="#">발주 요청 현황</a></li>
					</ul>
				</li>
				<li><a href="#">구매<i class='fa fa-angle-down'></i></a>
					<ul>
						<li><a href="#">구매 입력</a></li>
						<li><a href="#">구매 조회</a></li>
						<li><a href="#">구매 현황</a></li>
					</ul>
				</li>
				
				<li><a href="#">입출고 관리<i class='fa fa-angle-down'></i></a>
					<ul>
						<li><a href="WhList">창고 관리</a></li>
						<li><a href="IN_Schedule">입고 예정</a></li>
						<li><a href="IN_Process">입고 처리</a></li>
						<li><a href="#">출고 예정</a></li>
						<li><a href="#">출고 처리</a></li>
						<li><a href="#">재고 조정</a></li>
					</ul>
				</li>
				<li><a href="#">건의사항</a></li>
			</ul>
		</div>
	</nav>

	<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script>
	$('nav li').hover(
	  function() {
	      $('ul', this).stop().slideDown(200);
	  },
	    function() {
	    $('ul', this).stop().slideUp(200);
	  }
	);
	</script>

</body>
</html>