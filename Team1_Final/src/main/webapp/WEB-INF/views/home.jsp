<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댕글댕글 - ERP</title>
<script>

	var needPassChange = '${sessionScope.needPassChange}'
	
	if(needPassChange=='Y') {
		window.open('MovePassChange', 'searchPopup', 'width=500, height=500, left=600, top=400');
	}

</script>
</head>
<body>
	<jsp:include page="inc/top.jsp"></jsp:include>
	<div style="width: 1800px; min-height: 1300px;">
		<h1 align="center" style="width: 1800px; text-align: center;">메인페이지</h1>
	</div>
</body>
</html>