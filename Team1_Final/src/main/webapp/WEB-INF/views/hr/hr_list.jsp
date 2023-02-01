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
	<div style="display: flex;" align="center">
		<div style="width: 300px; margin-top: 0px; margin-right: 0px;">
			<jsp:include page="../inc/hr_left.jsp"></jsp:include>
		</div>
		<!-- 여기서부터 본문-->
		<div align="center" style="width: 1500px;">
		<h1 align="left" style="margin-left: 100px;">| 사원 조회</h1>
		<table border="1" style="text-align: center; width: 1300px; font-size: 20px;">
			<tr>
				<th width="150">사진</th>
				<th width="150">사번</th>
				<th width="120">이름</th>
				<th width="150">부서</th>
				<th width="70">직급</th>
				<th width="150">연락처</th>
				<th width="250">E-MAIL</th>
				<th width="200">버튼</th>
			</tr>
		
			<c:forEach items="${empList}" var="emp">
				<tr>
					<td><img src="${pageContext.request.contextPath}/resources/upload/${emp.PHOTO}" width="100"></td>
					<td>${emp.EMP_NUM }</td>
					<td>${emp.EMP_NAME }</td>
					<td>${emp.DEPT_NAME }</td>
					<td>${emp.GRADE_NAME }</td>
					<td>${emp.EMP_TEL}</td>
					<td>${emp.EMP_EMAIL }</td>
					<td><button type="button" style="width: 180px; font-size:18px; font-weight: bold;" onclick="location.href='HrDetail?empNo=${emp.EMP_NUM}'">상세정보</button>
					<br><button type="button"  style="width: 180px; font-size:18px; font-weight: bold; margin-top: 10px;" onclick="location.href='HrEdit?empNo=${emp.EMP_NUM}'">수정</button></td>
				</tr>
			</c:forEach>
		</table>
		</div>
		<!-- 여기까지 본문-->
	</div>
</body>
</html>
