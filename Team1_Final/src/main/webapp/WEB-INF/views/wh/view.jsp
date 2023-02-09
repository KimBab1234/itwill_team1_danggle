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

////로그인 유무 및 권한 확인
///기본등록(0), 사원조회(1), 사원관리(2), 재고조회(3), 재고관리(4)
var loginEmp = '${sessionScope.empNo}';
var priv = '${sessionScope.priv}';
if(loginEmp=='') {
   alert("로그인 후 이용하세요.");
   location.href="./Login";
} else if(priv.charAt(0) !='1') {
   alert("권한이 없습니다.");
   history.back();
}
////로그인 유무 및 권한 확인 끝

$(function() {
	var location = "${wh.WH_LOCATION }";
	if(location == 1) {
		$(".address").css("display","none");
	} else{
		$(".address").css("display","table-row");
	}
	
});

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
			<br>
			<input type="button" class="hrFormBtn" value="수정" onclick="location.href='WhModifyForm?WH_CD=${param.WH_CD}&pageNum=${param.pageNum }'">&nbsp;
			<input type="button" class="hrFormBtn" value="삭제" onclick="location.href='WhDeleteForm?WH_CD=${param.WH_CD}&pageNum=${param.pageNum }'">&nbsp;
			<input type="button" class="hrFormBtn" value="목록" onclick="location.href='WhList?pageNum=${param.pageNum}'">
		</div>
			<section id="listForm">
	<div align="center" style="width: 1000px;">
		
			<h2 align="center">재고 목록</h2>
			<table style="text-align: center; border: solid 1px; width: 600px; height: 170px;">
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