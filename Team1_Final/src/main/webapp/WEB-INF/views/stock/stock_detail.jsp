<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath }/resources/css/hr.css" rel="stylesheet" type="text/css" />
<script src="https://kit.fontawesome.com/5fad4a5f29.js" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<!-- 폰트 변경 시작  -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
<style>
*{
	color:#513e30;
	font-family: 'Gowun Dodum', sans-serif;
}
</style>
<!-- 폰트 변경 끝  -->
<style>

</style>
<title>Insert title here</title>
<script>
	
	

</script>
<style>
option {
	font-weight: bold;
}

</style>
</head>
<body>
	
	<h1 align="left" style="width: 1250px; text-align: left; margin-left: 20px;">| 재고 이력 </h1>
	<table border="1" class="regi_table" style="text-align: center; margin-left: 20px; width: 1250px; font-size: 20px;">
		<tr>
			<th width="200">작업일자</th>
			<th width="150">작업구분</th>
			<th width="400">품목명[규격]</th>
			<th width="250">보내는 재고번호</th>
			<th width="250">받는 재고번호</th>
			<th width="150">수량</th>
			<th width="150">작업자명</th>
			<th width="500">적요</th>
		</tr>
		<c:forEach items="${stockList}" var="stock">
			<tr>
				<td>${stock.STOCK_DATE}</td>
				<td>${stock.STOCK_CONTROL_TYPE_NAME}</td>
				<td>${stock.PRODUCT_NAME}</td>
				<td>
					<c:choose>
						<c:when test="${stock.SOURCE_STOCK_CD==null }">
							-
						</c:when>
						<c:otherwise>
							<a href="StockDetail?stockNo=${stock.SOURCE_STOCK_CD}">${stock.SOURCE_STOCK_CD}</a>
						</c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${stock.TARGET_STOCK_CD==null }">
							-
						</c:when>
						<c:otherwise>
							<a href="StockDetail?stockNo=${stock.TARGET_STOCK_CD}">${stock.TARGET_STOCK_CD}</a>
						</c:otherwise>
					</c:choose>
				</td>
				<td>${stock.QTY}</td>
				<td>${stock.EMP_NAME}</td>
				<td>${stock.REMARKS}</td>
			</tr>
		</c:forEach>
		
	
	</table>

	<!-- 여기까지 본문-->
</body>
</html>
