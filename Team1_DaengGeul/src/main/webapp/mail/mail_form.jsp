<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    String sId = (String)session.getAttribute("sId");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>문의사항 메일 전송 폼</h1>
	<form action="mail_Pro.jsp" method="post">
				<input type="hidden" name="sender" value="${sessionScope.sId }">
		<table border="1">
			
			
			
			
			
			<tr>
			<th>제목</th>
			<td><input type="text" name="title"></td>
			</tr>
			<tr>
			<th>내용</th>
			<td><textarea name = "content" rows="20" cols="40"></textarea></td>
			</tr>
			<tr>
			<td colspan="2"><input type="submit" value="메일발송"></td>
			</tr>
		</table>
	</form>
	
	
</body>
</html>