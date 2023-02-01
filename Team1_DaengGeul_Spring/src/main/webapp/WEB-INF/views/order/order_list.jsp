<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 내역</title>
<link href="${pageContext.request.contextPath }/resources/css/default.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/resources/css/style.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/resources/css/default_order.css" rel="stylesheet">
<script src="${pageContext.request.contextPath }/resources/js/main.js"></script>
<script src ="https://code.jquery.com/jquery-3.6.3.js"></script>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
<style>
* {
	font-family: 'Gowun Dodum', sans-serif;
	url: @import url('https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap');
	}
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
	<div style="display: flex;  margin-left: 50px; width: 1800px;" align="center">
		<div align="left" style="width: 300px; margin-top: 100px;">
			<jsp:include page="../inc/memberInfo_left.jsp"></jsp:include> <!-- 본문1 -->
		</div>
	<div align="right" class="orderTable" style="width: 1300px; min-height: 500px; margin-left:100px;">
	<h3 style="text-align: left; color:#736643; font-weight: bold;"><b style="border-left: 10px solid #795548">&nbsp;&nbsp;주문 내역</b></h3>
	<div align="right">
		<button type="button" onclick="location.href='OrderList?period=3 MONTH'" style="width: 200px;">최근 3개월 주문 내역</button>
		<button type="button" onclick="location.href='OrderList?period=6 MONTH'" style="width: 200px;">최근 6개월 주문 내역</button>
		<button type="button" onclick="location.href='OrderList?period=1 YEAR'" style="width: 200px;">최근 1년 주문 내역</button>
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
	<c:choose>
		<c:when test="${orderList==null || orderList.size()==0}">
			<h1 style="font-weight:bold; color:gray; margin-top: 50px;">최근 주문 내역이 없습니다.</h1>
		</c:when>
		<c:otherwise>
			<table border="1" style="width: 1300px; text-align: center; margin-top: 20px" class="regi_table">
				<tr>
					<th width="120px">주문 날짜</th>
<!-- 					<th width="100px" >이미지</th> -->
					<th width="300px" >상품</th>
					<th width="120px">결제한 금액</th>
					<th width="100px">주문 상태</th>
					<th width="70px">리뷰 쓰기</th>
				</tr>
				<c:forEach items="${orderList}" var="order" varStatus="status">
							<tr>
								<td rowspan="${order.order_prod_list.size() }"><a href="OrderDetailList?order_idx=${order.order_idx}">${order.order_date}</a></td>
								<c:choose>
									<c:when test="${order.order_prod_list.get(0).opt eq '-' }">
<%-- 										<td><img src="http://itwillbs3.cdn1.cafe24.com/img/product/${order.order_prod_list.get(0).img}" width="100px;"></td> --%>
										<td>${order.order_prod_list.get(0).name}</td>
									</c:when>
									<c:otherwise>
<%-- 										<td><img src="http://itwillbs3.cdn1.cafe24.com/img/product/${order.order_prod_list.get(0).img}" width="100px;"></td> --%>
										<td>${order.order_prod_list.get(0).name}(옵션:${order.order_prod_list.get(0).opt})</td>
									</c:otherwise>
								</c:choose>
								<td>${order.order_prod_list.get(0).price}</td>
								<td>${order.order_prod_list.get(0).status}</td>
								<c:choose>
									<c:when test="${order.order_prod_list.get(0).review_write eq 'Y'}">
										<td>리뷰 작성 완료</td>
									</c:when>
									<c:otherwise>
										<td><button type="button" onclick="location.href='ReviewWriteForm.re?product_idx=${order.order_prod_list.get(0).idx}&order_idx=${order.order_idx}'">리뷰쓰러가기</button></td>
									</c:otherwise>
								</c:choose>
							</tr>
						<c:forEach begin="1" end="${order.order_prod_list.size()-1}" var="i">
							<tr>
								<c:choose>
									<c:when test="${order.order_prod_list.get(i).opt eq '-' }">
<%-- 										<td><img src="http://itwillbs3.cdn1.cafe24.com/img/product/${order.order_prod_list.get(0).img}" width="100px;"></td> --%>
										<td>${order.order_prod_list.get(i).name}</td>
									</c:when>
									<c:otherwise>
<%-- 										<td><img src="http://itwillbs3.cdn1.cafe24.com/img/product/${order.order_prod_list.get(0).img}" width="100px;"></td> --%>
										<td>${order.order_prod_list.get(i).name}(옵션:${order.order_prod_list.get(i).opt})</td>
									</c:otherwise>
								</c:choose>
								<td>${order.order_prod_list.get(i).price}</td>
								<td>${order.order_prod_list.get(i).status}</td>
								<c:choose>
									<c:when test="${order.order_prod_list.get(i).review_write eq 'Y'}">
										<td>리뷰 작성 완료</td>
									</c:when>
									<c:otherwise>
										<td><button type="button" onclick="location.href='ReviewWriteForm.re?product_idx=${order.order_prod_list.get(i).idx}&order_idx=${order.order_idx}'">리뷰쓰러가기</button></td>
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