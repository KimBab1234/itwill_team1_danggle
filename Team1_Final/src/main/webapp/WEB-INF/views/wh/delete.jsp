<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>창고 삭제</title>
<link href="${pageContext.request.contextPath }/resources/css/hr.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.hrFormBtn {
	background-color: #736643;
	border: none;
	cursor: pointer;
	color: #fff;
	height: 27px;
	width: 55px;
	border-radius: 4px;
	font-size: 15px;
}

.Btn {
	background-color: #736643;
	border: none;
	cursor: pointer;
	color: #fff;
	height: 27px;
	width: 100px;
	border-radius: 4px;
	font-size: 15px;
}
</style>
</head>
<body>
	<jsp:include page="../inc/top.jsp"></jsp:include>
	<div style="display: flex;">
		<div style="width: 300px; margin-top: 0px; margin-right: 0px; border-right:solid 1px; border-color: #BDBDBD;">
			<jsp:include page="../inc/wms_left.jsp"></jsp:include>
		</div>
			<div align="center" style="width: 1300px;">
				<h2 align="center">창고 삭제</h2>
				<form action="WhDeletePro" name="deleteForm" method="post">
				<input type="hidden" name="WH_CD" value="${param.WH_CD }">
				<input type="hidden" name="pageNum" value="${param.pageNum }">
				<table>
					<tr>
						<td colspan="2">
							<input type="submit" class="hrFormBtn" value="삭제">&nbsp;&nbsp;
							<input type="button" class="Btn" value="돌아가기" onclick="javascript:history.back()">
						</td>
					</tr>
				</table>
				</form>
			</div>
		</div>
</body>
</html>