<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src ="https://code.jquery.com/jquery-3.6.3.js"></script>
<link href="${pageContext.request.contextPath }/resources/css/hr.css" rel="stylesheet" type="text/css" />
<script src="https://kit.fontawesome.com/5fad4a5f29.js" crossorigin="anonymous"></script>
<!-- 폰트 변경 끝  -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
<style>
*{
	color:#513e30;
	font-family: 'Gowun Dodum', sans-serif;
	background: #c9b584;
}

</style>
<!-- 폰트 변경 끝 -->
<script>
function movePassChange() {
	opener.location.href='HrEdit?empNo=${sessionScope.empNo}';
	this.close();
}
</script>
<div align="center" style="width: auto; margin-top: 100px;">
	<h1><i class="fa fa-solid fa-triangle-exclamation" style="color: #A50000;"></i>비밀번호 변경 필요!</h1>
	<h2>현재 임시 비밀번호를 사용하고 있습니다.</h2>
	<h2>신규 비밀번호로 변경이 필요합니다.</h2>
	<button type="button" class="hrFormBtn" style="width: 300px; font-size: 24px;"  onclick="movePassChange()">비밀번호 변경하러가기!</button>
</div>