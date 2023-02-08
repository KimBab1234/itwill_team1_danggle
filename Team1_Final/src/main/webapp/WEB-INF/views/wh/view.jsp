<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>창고 상세정보</title>
<link href="css/default.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/resources/css/default.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">

$(function() {
	var location = "${wh.WH_LOCATION }";
	if(location == 1) {
		$(".address").css("display","none");
	} else{
		$(".address").css("display","table-row");
	}
	
});

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
			<h2 align="center">창고 상세내용</h2>
			<table style="text-align: center; border: solid 1px; width: 600px; height: 400px;">
			<tr>
				<th align="right" style="width: 200px;">창고 코드</th><td>${wh.WH_CD }</td>
			</tr>
			<tr>
				<th align="right" style="width: 200px;">창고명</th><td>${wh.WH_NAME }</td> 
			</tr>
			<tr>
				<th align="right" style="width: 200px;">구분</th><td>
				<c:choose>
					<c:when test="${wh.WH_GUBUN eq 1 }">창고</c:when>
					<c:when test="${wh.WH_GUBUN eq 2 }">공장</c:when>
				
				</c:choose></td><!-- 1:창고 2:공장 표시하기 -->
				
			</tr>
			<tr class="WH_LOCATION">
				<td align="right" style="width: 200px;">위치</td><td>
				<c:choose>
					<c:when test="${wh.WH_GUBUN eq 1 }">내부</c:when>
					<c:when test="${wh.WH_GUBUN eq 2 }">외부</c:when>
				
				</c:choose></td><!-- 1: 내부 2: 외부 표시하기 -->
			</tr>
			<tr class="address">
				<th align="right" style="width: 200px;">우편번호</th><td>${wh.WH_POST_NO }</td>
			</tr>
			<tr class="address">
				<th align="right" style="width: 200px;">주소</th><td>${wh.WH_ADDR }</td>
			</tr>
			<tr class="address">
				<th align="right" style="width: 200px;">상세주소</th><td>${wh.WH_ADDR_DETAIL }</td>
			</tr>
			<tr>
				<th align="right" style="width: 200px;">전화번호</th><td>${wh.WH_TEL }</td>
			</tr>
			<tr>
				<th align="right" style="width: 200px;">관리자명</th><td>${wh.WH_MAN_NAME }</td>
			</tr>
			<tr>
				<th align="right" style="width: 200px;">사용여부</th><td>
				<c:choose>
					<c:when test="${wh.WH_GUBUN eq 1 }">사용</c:when>
					<c:when test="${wh.WH_GUBUN eq 2 }">미사용</c:when>
				
				</c:choose></td><!--  1: 사용 2: 미사용 표시하기 -->
			</tr>
			<tr>
				<th align="right" style="width: 200px;">적요</th><td>${wh.REMARKS }</td>
			</tr>
			
			</table>
			<input type="button" value="수정" onclick="location.href='WhModifyForm?WH_CD=${param.WH_CD}&pageNum=${param.pageNum }'">
			<input type="button" value="삭제" onclick="location.href='WhDeleteForm?WH_CD=${param.WH_CD}&pageNum=${param.pageNum }'">
			<input type="button" value="목록" onclick="location.href='WhList?pageNum=${param.pageNum}'">
		</div>
			<div align="center" style="width: 1300px;">
			<h2 align="center">재고 목록</h2>
			<table style="text-align: center; border: solid 1px; width: 600px; height: 400px;">
		
			<c:forEach items="${stockList }" var="list">
			<tr>
				<th align="center" style="width: 200px;">재고번호</th><td>${list.STOCK_CD }</td>
			</tr>
			<tr>
				<th align="center" style="width: 200px;">품목명</th><td>${list.PRODUCT_NAME }</td> 
			</tr>
			<tr>
				<th align="center" style="width: 200px;">규격</th><td>${list.SIZE_DES }</td> 
			</tr>
			<tr>
				<th align="center" style="width: 200px;">수량</th><td>${list.STOCK_QTY }</td> 
			</tr>
			
			</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>