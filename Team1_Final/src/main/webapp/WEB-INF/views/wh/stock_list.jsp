<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>재고 목록</title>
<link href="css/default.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/resources/css/default.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">



</script>
</head>
<body>
	<jsp:include page="../inc/top.jsp"></jsp:include>
	<div style="display: flex;">
		<div style="width: 300px; margin-top: 0px; margin-right: 0px; border-right:solid 1px; border-color: #BDBDBD;">
			<jsp:include page="../inc/wms_left.jsp"></jsp:include>
		</div>
		<!-- 여기서부터 본문-->
		<div align="center" style="width: 1300px;">
			<h2 align="center">재고 목록</h2>
			<table style="text-align: center; border: solid 1px; width: 600px; height: 400px;">
		
			<c:forEach items="${stockList }" var="list">
			<tr>
				<th align="center" style="width: 200px;">재고번호</th><td>${stockList.STOCK_CD }</td>
			</tr>
			<tr>
				<th align="center" style="width: 200px;">품목명</th><td>${stockList.PRODUCT_NAME }</td> 
			</tr>
			<tr>
				<th align="center" style="width: 200px;">규격</th><td>${stockList.SIZE_DES }</td> 
			</tr>
			<tr>
				<th align="center" style="width: 200px;">수량</th><td>${stockList.STOCK_QTY }</td> 
			</tr>
<!-- 			<tr> -->
<%-- 				<th align="center" style="width: 200px;">창고명</th><td>${stockList.WH_NAME }</td>  --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<%-- 				<th align="center" style="width: 200px;">구역명</th><td>${stockList.WH_AREA}</td>  --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<%-- 				<th align="center" style="width: 200px;">위치명</th><td>${stockList.WH_LOC_IN_AREA }</td>  --%>
<!-- 			</tr> -->
			</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>