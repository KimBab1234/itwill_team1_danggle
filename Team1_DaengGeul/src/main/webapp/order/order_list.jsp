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
<link href="css/default_order.css" rel="stylesheet">
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
	<div style="display: flex; width: 1800px;" align="center">
		<div align="left" style="width: 300px; margin-top: 100px;">
			<jsp:include page="../inc/memberInfo_left.jsp"></jsp:include> <!-- 본문1 -->
		</div>
	<div align="right" class="orderTable" style="width: 1300px; margin-top: 50px; min-height: 500px;">
	<h3 style="text-align: left; color:#736643; font-weight: bold;">| 주문 내역</h3>
	<c:choose>
		<c:when test="${orderList==null || orderList.size()==0}">
			<h1 style="font-family: 'Jua', sans-serif; color:gray;">주문 내역이 없습니다.</h1>
		</c:when>
		<c:otherwise>
			<div align="right">
				<button type="button" onclick="location.href='OrderList.or?period=3 MONTH'" style="width: 200px;">최근 3개월 주문 내역</button>
				<button type="button" onclick="location.href='OrderList.or?period=6 MONTH'" style="width: 200px;">최근 6개월 주문 내역</button>
				<button type="button" onclick="location.href='OrderList.or?period=1 YEAR'" style="width: 200px;">최근 1년 주문 내역</button>
			</div>
			<div align="left">
			<c:choose>
				<c:when test="${empty param.period}">
					■ 최근 7일간 주문 내역입니다.
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${param.period.substring(2) eq 'MONTH'}">
							■ 최근 ${param.period.substring(0,1)}개월 주문 내역입니다.
						</c:when>
						<c:otherwise>
							■ 최근 ${param.period.substring(0,1)}년 주문 내역입니다.
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
			</div>
			<table border="1" style="width: 1300px; text-align: center; margin-top: 20px" class="regi_table">
				<tr>
					<th width="70px">주문 날짜</th>
					<th width="300px">상품</th>
					<th width="120px">결제한 금액</th>
					<th width="100px">주문 상태</th>
					<th width="70px">리뷰 쓰기</th>
				</tr>
				<c:forEach items="${orderList}" var="order" varStatus="status">
							<tr>
								<td rowspan="${order.order_prod_name.size() }"><a href="OrderDetailList.or?order_idx=${order.order_merchant_uid}">${order.order_merchant_uid.substring(sessionScope.sId.length())}</a></td>
								<c:choose>
									<c:when test="${order.order_prod_opt.get(0) eq '-' }">
										<td>${order.order_prod_name.get(0)}</td>
									</c:when>
									<c:otherwise>
										<td>${order.order_prod_name.get(0)}(옵션:${order.order_prod_opt.get(0)})</td>
									</c:otherwise>
								</c:choose>
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
								<c:choose>
									<c:when test="${order.order_prod_opt.get(i) eq '-' }">
										<td>${order.order_prod_name.get(i)}</td>
									</c:when>
									<c:otherwise>
										<td>${order.order_prod_name.get(i)}(옵션:${order.order_prod_opt.get(i)})</td>
									</c:otherwise>
								</c:choose>
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