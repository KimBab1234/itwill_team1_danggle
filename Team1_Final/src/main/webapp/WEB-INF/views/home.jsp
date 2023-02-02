<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>

	var needPassChange = '${sessionScope.needPassChange}'
	
	if(needPassChange=='Y') {
		window.open('MovePassChange', 'searchPopup', 'width=500, height=500, left=600, top=400');
	}

</script>
</head>
<body>
	<jsp:include page="inc/top.jsp"></jsp:include>
		<h1 align="center" style="text-align: center;">메인페이지</h1>
</body>
</html>