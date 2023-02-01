<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<jsp:include page="../inc/hr_left.jsp"></jsp:include>
		</div>
			
			<div align="center">
				<h2>거래처 등록</h2>
				<table>
					<tr>
						<td>회사명 &nbsp;</td>
						<th><input type="text" required="required"></th>
					</tr>				
					<tr>
						<td>거래처코드 &nbsp;</td>
						<th><input type="radio" name="G_GUBUN" value="01" checked="checked">사업자등록번호</th>
						<th><input type="radio" name="G_GUBUN" value="02">해외사업자등록번호</th>
						<th><input type="radio" name="G_GUBUN" value="03">주민등록번호</th>
						<th><input type="radio" name="G_GUBUN" value="04">외국인</th>
					</tr>				
					<tr>
						<td>회사명 &nbsp;</td>
						<th><input type="text" required="required"></th>
					</tr>				
					<tr>
						<td>회사명 &nbsp;</td>
						<th><input type="text" required="required"></th>
					</tr>				
					<tr>
						<td>회사명 &nbsp;</td>
						<th><input type="text" required="required"></th>
					</tr>				
					<tr>
						<td>회사명 &nbsp;</td>
						<th><input type="text" required="required"></th>
					</tr>				
					<tr>
						<td>회사명 &nbsp;</td>
						<th><input type="text" required="required"></th>
					</tr>				
				</table>
			</div>	
				
		<!-- 여기까지 본문-->
	</div>
</body>
</html>