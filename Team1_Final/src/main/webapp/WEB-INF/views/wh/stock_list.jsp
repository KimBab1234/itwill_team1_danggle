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
////로그인 유무 및 권한 확인
///기본등록(0), 사원조회(1), 사원관리(2), 재고조회(3), 재고관리(4)
var loginEmp = '${sessionScope.empNo}';
var priv = '${sessionScope.priv}';
if(loginEmp=='') {
 alert("로그인 후 이용하세요.");
 location.href="./Login";
} else if(priv.charAt(3) !='1') {
 alert("권한이 없습니다.");
 history.back();
}
////로그인 유무 및 권한 확인 끝

</script>
<link href="css/default.css" rel="stylesheet" type="text/css">
<style type="text/css">
	#listForm {
		width: 1024px;
		max-height: 610px;
	}
	
	h2 {
		text-align: center;
	}
	
	table {
		width: 1024px;
	}
	
	#tr_top {
		background: #b09f76;
		text-align: center;
	}
	
	.wh_td {
		text-align: center;
	
	}
	
	#subject {
		text-align: left;
		padding-left: 20px;
	}
	
	#pageList {
		width: 1024px;
		text-align: center;
		padding-right: 500px;
	}
	
	#emptyArea {
		width: 1024px;
		text-align: center;
	}
	
	#buttonArea {
		width: 1024px;
		text-align: right;
		margin-top: 10px;
	}
	
	a {
		text-decoration: none;
	}
</style>
</head>
<body>
	<jsp:include page="../inc/top.jsp"></jsp:include>
	<div style="display: flex;">
		<div style="width: 300px; margin-top: 0px; margin-right: 0px; border-right:solid 1px; border-color: #BDBDBD;">
			<jsp:include page="../inc/wms_left.jsp"></jsp:include>
		</div>
		<!-- 여기서부터 본문-->
	<section id="listForm">
	<div align="center" style="width: 1000px;">
		
			<h2 align="center">재고 목록</h2>
			<table style="text-align: center; border: solid 1px; width: 800px; height: 170px;">
			<tr id="tr_top">
			<td>재고번호</td>
			<td width="150px">품목명</td>
			<td>규격</td>
			<td>수량</td>
			<td>구역명</td>
			<td>위치명</td>
			
		</tr>
			<c:forEach items="${stockList }" var="list">
			<tr>
				<td>${list.STOCK_CD }</td>
				<td>${list.PRODUCT_NAME }</td> 
				<td>${list.SIZE_DES }</td> 
				<td>${list.STOCK_QTY }</td> 
				<td>${list.WH_AREA}(${list.WH_NAME})</td>
				<td>${list.WH_LOC_IN_AREA }</td>
			</tr>
			</c:forEach>
			</table>
			</div>
			</section>
		</div>
		
	
</body>
</html>