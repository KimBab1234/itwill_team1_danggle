<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 내역</title>
<link href="css/default.css" rel="stylesheet" type="text/css">
<link href="css/style.css" rel="stylesheet">
<script src="js/main.js"></script>
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
	<div style="width: 1200px; margin-top: 50px; min-height: 500px;">
	<h3 style="text-align: left;">| 주문 내역</h3>
	<form action="" method="post">
	<c:choose>
		<c:when test="${orderList==null || orderList.size()==0}">
			<h2>주문 내역이 없습니다.</h2>
		</c:when>
		<c:otherwise>
			<div align="right">
				<button type="button" onclick="location.href='OrderList.or?period=3 MONTH'">최근 3개월 주문 내역</button>
				<button type="button" onclick="location.href='OrderList.or?period=6 MONTH'">최근 6개월 주문 내역</button>
				<button type="button" onclick="location.href='OrderList.or?period=1 YEAR'">최근 1년 주문 내역</button>
			</div>
			<div align="left">
			■ 최근 7일간 주문 내역입니다.
			</div>
			<table border="1" style="width: 1200px; text-align: center; margin-top: 20px">
				<tr>
					<td width="70px">주문 번호</td>
					<td width="200px">상품</td>
					<td width="70px">결제한 금액</td>
					<td width="70px">주문 상태</td>
					<td width="70px">리뷰 쓰기</td>
				</tr>
				<c:forEach items="${orderList}" var="order" varStatus="status">
							<tr>
								<td rowspan="${order.order_prod_name.size() }"><a href="OrderDetailList.or?order_idx=${order.order_merchant_uid}">${order.order_merchant_uid}</a></td>
								<td>${order.order_prod_name.get(0)}</td>
								<td>${order.order_prod_price.get(0)}</td>
								<td>${order.order_status.get(0)}</td>
								<c:choose>
									<c:when test="${order.review_write.get(0) eq 'Y'}">
										<td>리뷰 작성 완료</td>
									</c:when>
									<c:otherwise>
										<td><button type="button" onclick="location.href='ReviewWriteForm.re?product_idx=${order.order_prod_idx.get(0)}&order_idx=${order.order_merchant_uid}'">리뷰쓰러가기</button></td>
									</c:otherwise>
								</c:choose>
							</tr>
						<c:forEach begin="1" end="${order.order_prod_name.size()-1}" var="i">
							<tr>
								<td>${order.order_prod_name.get(i)}</td>
								<td>${order.order_prod_price.get(i)}</td>
								<td>${order.order_status.get(i)}</td>
								<c:choose>
									<c:when test="${order.review_write.get(i) eq 'Y'}">
										<td>리뷰 작성 완료</td>
									</c:when>
									<c:otherwise>
										<td><button type="button" onclick="location.href='ReviewWriteForm.re?product_idx=${order.order_prod_idx.get(i)}&order_idx=${order.order_merchant_uid}'">리뷰쓰러가기</button></td>
									</c:otherwise>
								</c:choose>
							</tr>
						</c:forEach>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	</form>
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