<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구역 상세정보</title>
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
			<h2 align="center">${wh.WH_AREA} 상세내용</h2>
			<input type="hidden" name="WH_AREA_CD" value="${wh.WH_AREA_CD }" >
			<input type="hidden" name="WH_LOC_IN_AREA_CD" value="${wh.WH_LOC_IN_AREA_CD }" >
			<table style="text-align: center; border: solid 1px; width: 600px; height: 400px;">
			
			<tr>
				<th align="right" style="width: 200px;">구역명</th><td>${wh.WH_AREA}</td>
			</tr>
			<tr>
				<th align="right" style="width: 200px;">위치명</th><td>${wh.WH_LOC_IN_AREA}</td>
			</tr>
<%-- 			<c:forEach var="area" items="${areaList }"> --%>
<!-- 			<tr> -->
<%-- 				<th align="right" style="width: 200px;">위치명</th><td>${wh.WH_LOC_IN_AREA }</td>  --%>
<!-- 			</tr> -->
<%-- 			</c:forEach> --%>
			</table>
			<input type="button" value="수정" onclick="location.href='WhAreaModifyForm?WH_AREA_CD=${param.WH_AREA_CD}&pageNum=${param.pageNum }'">
			<input type="button" value="삭제" onclick="location.href='WhAreaDeleteForm?WH_AREA_CD=${param.WH_AREA_CD}&pageNum=${param.pageNum }'">
			<input type="button" value="목록" onclick="location.href='WhList?pageNum=${param.pageNum}'">
		</div>
	</div>
</body>
</html>