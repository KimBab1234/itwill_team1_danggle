<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1 align="center"><a href="/">Team1 댕글댕글</a></h1>
<h3 align="right">
	<c:choose>
		<c:when test="${empty sessionScope.sId}">
			<a href="">로그인</a>
		</c:when>
		<c:otherwise>
			<a href="">${sessionScope.sId}님</a>
			<a href="">로그아웃</a>
		</c:otherwise>
	</c:choose>
</h3>
<hr style="margin: 0px;">
</body>
</html>
