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
	<jsp:include page="../inc/top.jsp"></jsp:include>
	<div style="display: flex;">
		<div style="width: 300px; margin-top: 0px; margin-right: 0px; border-right:solid 1px; border-color: #BDBDBD;">
			<jsp:include page="../inc/left.jsp"></jsp:include>
		</div>
		<!-- 여기서부터 본문-->
			<h2>사원 조회</h2>
		<!-- 여기까지 본문-->
		<c:forEach items="${hrList }" var="hrItem">
			${hrItem.DEPT_CD}<br>
			${hrItem.GRADE_CD}<br>
			${hrItem.WORK_CD}<br>
			${hrItem.PRIV_CD}<br>
		</c:forEach>
	</div>
</body>
</html>