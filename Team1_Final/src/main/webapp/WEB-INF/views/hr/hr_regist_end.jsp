<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
<script>

	$(function() {
		  $.ajax({
				url: "HrTempPass",
				type : "post",
				dataType: "text", 
				data: {
					empNo:'${param.empNo}'
				},
				success: function(response) {
				},
				error: function(xhr, textStatus, errorThrown) { 
					alert("메일 실패!");
				}
			});
	});
</script>
</head>
<body>
	<jsp:include page="../inc/top.jsp"></jsp:include>
	<div style="display: flex;" align="center">
		<div style="width: 300px; margin-top: 0px; margin-right: 0px;">
			<jsp:include page="../inc/hr_left.jsp"></jsp:include>
		</div>
		<!-- 여기서부터 본문-->
		<div style="width: 1500px;" align="center">
			<h1>등록 완료</h1>
			<h3>등록한 이메일로 임시 비밀번호가 발송되었습니다.</h3>
			<h3>임시 비밀번호로 로그인 후 비밀번호 변경을 해주세요.</h3>
		</div>
		<!-- 여기까지 본문-->
	</div>
</body>
</html>