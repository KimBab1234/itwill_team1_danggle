<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 상세 내역</title>
<link href="${pageContext.request.contextPath }/resources/css/default_order.css" rel="stylesheet" type="text/css">
<script src ="https://code.jquery.com/jquery-3.6.3.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
<style>
* {
	font-family: 'Gowun Dodum', sans-serif;
	url: @import url('https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap');
	}
</style>
<style>
.cartB:focus, .cartB:active { outline:none; }

.proDe:hover {
	background: #F0D264;
}
</style>
</head>
</head>
<body>
<script>

</script>

	<header>
		<jsp:include page="../inc/top.jsp"></jsp:include>
		<jsp:include page="../inc/main.jsp"></jsp:include> <!-- 본문1 -->
	</header>
	
	<div style="display: flex; margin-left: 50px; width: 1800px;">
	<div align="left" style="width: 300px; margin-top: 100px;">
		<jsp:include page="../inc/memberInfo_left.jsp"></jsp:include> <!-- 본문1 -->
	</div>
	<div style="width: 1300px; min-height: 500px; margin-left:100px;">
	<div>
	<h3 style="text-align: left; color:#736643; font-weight: bold;"><b style="border-left: 10px solid #795548">&nbsp;&nbsp;상세 주문 내역 (주문 날짜 - ${order.order_date})</b></h3>
	<br>
	<h5 style="text-align: left; color:#736643; font-weight: bold;">- 상품명을 클릭하시면 해당 상품 페이지로 이동합니다.</h5>
	<table border="1" class="regi_table" style="width: 1300px; text-align: center; margin-top: 20px">
		<tr>
			<th width="300px">상품명</th>
			<th width="70px">가격</th>
			<th width="50px">옵션</th>
			<th width="30px">수량</th>
			<th width="70px">금액</th>
		</tr>
		<c:forEach begin="0" end="${order.order_prod_list.size()-1}" var="i" varStatus="status">
			<tr class="proDe">
				<c:choose>
					<c:when test="${order.order_prod_list.get(i).name eq '-'}">
						<td>현재 판매하지 않는 상품입니다. 상품 코드 : ${order.order_prod_list.get(i).idx}</td>
					</c:when>
					<c:otherwise>
						<td><a href="ProductDetail?product_idx=${order.order_prod_list.get(i).idx}">${order.order_prod_list.get(i).name}</a></td>
					</c:otherwise>
				</c:choose>
				<td>${order.order_prod_list.get(i).price}</td>
				<td>${order.order_prod_list.get(i).opt}</td>
				<td>${order.order_prod_list.get(i).cnt}</td>
				<td>${order.order_prod_list.get(i).cnt*order.order_prod_list.get(i).price}</td>
			</tr>
		</c:forEach>
	</table>
	</div>
	<div style="width: 900px; margin-top: 50px;" align="left">
	<h3 style="text-align: left; color:#736643; font-weight: bold;"><b style="border-left: 10px solid #795548">&nbsp;&nbsp;배송 정보</b></h3>
	<table border="1" class="regi_table" style="width: 800px; text-align: left; margin-top: 20px; margin-left: 0;">
		<tr>
			<th width="100px">받는 사람</th>
			<td>${order.order_name}</td>
		</tr>
		<tr>
			<th width="70px">주소</th>
			<td>${order.order_address}</td>
		</tr>
		<tr>
			<th width="70px">연락처</th>
			<td>${order.order_phone}</td>
		</tr>
	</table>
	<br>
	</div>
	<div align="left" style="width: 900px; margin-top: 50px;">
		<h3 style="text-align: left; color:#736643; font-weight: bold;"><b style="border-left: 10px solid #795548">&nbsp;&nbsp;결제 정보</b></h3>
	<div align="left">
	<table border="1"  class="regi_table" style="width: 500px; text-align: left; margin-top: 20px;  margin-left: 0;'">
		<tr>
			<th width="200px">사용한 포인트</th>
			<td>${order.order_point}원</td>
		</tr>
		<tr>
			<th width="70px">결제 수단</th>
			<td>${order.order_payment}</td>
		</tr>
		<tr>
			<th width="70px">총 결제 금액</th>
			<td>${order.order_total_pay}원</td>
		</tr>
	</table>
	
	</div>
	<div class="orderTable" style="width: 1300px; margin-top: 50px;" align="center">
		<button type="button" style="width: 300px; height: 50px; font-size: 20px;" onclick="history.back()">돌아가기</button>
	</div>
	</div>
</div>
</div>
<!------------------------------------ 바닥글 --------------------------------------->
	<footer>
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
	</footer>
	<!----------------------------------------------------------------------------------->
	
	<!------------------------------ top, left, bottom 동작 관련 작업 빼지말것! ------------------------------>
	<!-- Back to Top -->
    <a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>

    <!-- JavaScript Libraries -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/lib/easing/easing.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/lib/owlcarousel/owl.carousel.min.js"></script>

    <!-- Contact Javascript File -->
    <script src="${pageContext.request.contextPath }/resources/mail/jqBootstrapValidation.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/mail/contact.js"></script>

    <!-- Template Javascript -->
    <script src="${pageContext.request.contextPath }/resources/js/main.js"></script>
	<!------------------------------ top, left, bottom 동작 관련 작업 빼지말것! ------------------------------>
</body>
</html>