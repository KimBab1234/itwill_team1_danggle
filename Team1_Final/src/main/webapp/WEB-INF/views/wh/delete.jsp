<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>창고 삭제</title>
</head>
<body>
	<jsp:include page="../inc/top.jsp"></jsp:include>
	<div style="display: flex;">
		<div style="width: 300px; margin-top: 0px; margin-right: 0px; border-right:solid 1px; border-color: #BDBDBD;">
			<jsp:include page="../inc/hr_left.jsp"></jsp:include>
		</div>
			<div align="center" style="width: 1300px;">
				<h2 align="center">창고 삭제</h2>
				<form action="WhDeletePro" name="deleteForm" method="post">
				<input type="hidden" name="WH_CD" value="${param.WH_CD }">
				<input type="hidden" name="pageNum" value="${param.pageNum }">
				<table>
					<tr>
						<td colspan="2">
							<input type="submit" value="삭제">&nbsp;&nbsp;
							<input type="button" value="돌아가기" onclick="javascript:history.back()">
						</td>
					</tr>
				</table>
				</form>
			</div>
		</div>
</body>
</html>