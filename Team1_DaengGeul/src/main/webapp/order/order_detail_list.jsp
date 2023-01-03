<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/default_order.css" rel="stylesheet" type="text/css">
<script src ="https://code.jquery.com/jquery-3.6.3.js"></script>
<style>
.cartB:focus, .cartB:active { outline:none; }
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

	<div align="center">
	<div style="width: 1000px; margin-top: 50px;">
	<h3 style="text-align: left;">| 상세 주문 내역</h3>
	<table border="1" style="width: 1000px; text-align: center; margin-top: 20px">
		<tr>
			<td width="300px">상품명</td>
			<td width="70px">가격</td>
			<td width="50px">옵션</td>
			<td width="30px">수량</td>
			<td width="70px">금액</td>
		</tr>
		<c:forEach begin="0" end="${order.order_prod_name.size()-1}" var="i" varStatus="status">
			<tr>
				<td>${order.order_prod_name.get(i)}</td>
				<td>${order.order_prod_price.get(i)}</td>
				<td>${order.order_prod_opt.get(i)}</td>
				<td>${order.order_prod_cnt.get(i)}</td>
				<td>${order.order_prod_cnt.get(i)*order.order_prod_price.get(i)}</td>
			</tr>
		</c:forEach>
	</table>
	<h3 style="text-align: left; margin-top: 50px;">| 배송 정보</h3>
	<table border="1" style="width: 1000px; text-align: center; margin-top: 20px">
		<tr>
			<td width="100px">받는 사람</td>
			<td>${order.order_name}</td>
		</tr>
		<tr>
			<td width="70px">주소</td>
			<td>${order.order_address}</td>
		</tr>
		<tr>
			<td width="70px">연락처</td>
			<td>${order.order_phone}</td>
		</tr>
	</table>
	<br>
	</div>
	<div align="left" style="width: 1000px; margin-top: 50px;">
	<h3 style="text-align: left;">| 상세 주문 내역</h3>
	<table border="1" style="width: 500px; text-align: center; margin-top: 20px">
		<tr>
			<td width="200px">사용한 포인트</td>
			<td>${order.order_point}원</td>
		</tr>
		<tr>
			<td width="70px">결제 수단</td>
			<td>${order.order_payment}</td>
		</tr>
		<tr>
			<td width="70px">총 결제 금액</td>
			<td>${order.order_total_pay}원</td>
		</tr>
	</table>
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
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>

    <!-- Contact Javascript File -->
    <script src="mail/jqBootstrapValidation.min.js"></script>
    <script src="mail/contact.js"></script>

    <!-- Template Javascript -->
    <script src="js/main.js"></script>
	<!------------------------------ top, left, bottom 동작 관련 작업 빼지말것! ------------------------------>
</body>
</html>