<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src ="https://code.jquery.com/jquery-3.6.3.js"></script>
<link href="${pageContext.request.contextPath }/resources/css/hr.css" rel="stylesheet" type="text/css" />
<!-- 폰트 변경 끝  -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
<style>
*{
	color:#513e30;
	font-family: 'Gowun Dodum', sans-serif;
}

</style>
<!-- 폰트 변경 끝 -->
<script>
function movePassChange() {
	opener.location.href='HrEdit?empNo=${sessionScope.empNo}';
	this.close();
}
</script>
<div align="center" style="width: auto;">
	<h1>비밀번호 변경 필요!</h1>
	<h3>현재 임시 비밀번호를 사용하고 있습니다. 변경이 필요합니다.</h3>
	<button type="button" onclick="movePassChange()">변경하러가기!</button>
</div>