<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="img/daram.png" rel="shortcut icon" type="image/x-icon">
<title>댕글댕글 : 찜목록</title>
<%------------------- 임시 홈페이지 CSS -------------------%>
<link href="css/memberList.css" rel="stylesheet" type="text/css">
<%---------------------------------------------------------%>


<%------------------------------ 찜목록 삭제 --------------------------------%>
<script src="js/jquery-3.6.3.js"></script>
<script type="text/javascript">
	$(function() {
		
		// -------------------- 찜전체 삭제 확인 알림창 --------------------
		$("#deleteWishAll").on("click", function() {
		  	if(confirm("모든 찜 상품을 취소하시겠습니까?")) {
		  		location.href = "WishDeletePro.ws";
		  	}
		});
		// -----------------------------------------------------------------
		
		
		// -------------------- 찜개별 삭제 확인 알림창 --------------------
		$(".deleteWish").on("click", function() {
		  	if(confirm("[product_name]을 찜 취소하시겠습니까?" + $('#id').val() + $('#product_idx').val())) {
		  		location.href = "WishDeletePro.ws?product_idx=" + $('#product_idx').val();
		  	}
		});
		// -----------------------------------------------------------------
		
	});
</script>
<%---------------------------------------------------------------------------%>
</head>
<body>
	<header>
		<!-- Login, Join 링크 표시 영역(inc/top.jsp 페이지 삽입) -->
		<jsp:include page="/inc/top.jsp"></jsp:include>
		<jsp:include page="/inc/main.jsp"></jsp:include>
	</header>
	
	<div class="clear"></div>
	
	<h1>위시리스트</h1>
		<table border="1">
			<tr>
				<th width="150">상품이미지</th>
				<th width="120">상품코드</th>
				<th width="150">상품명</th>				
				<th width="100">상품가</th>
				<th width="100">할인액</th>
				<th width="100">찜등록일</th>
				<th width="80"></th>
			</tr>
			<c:forEach var="wishlist" items="${wishlist }">
				<tr>
					<td>${wishlist.product_real_img }</td>
					<td>${wishlist.product_idx }</td>
					<td>${wishlist.product_name }</td>
					<td>${wishlist.product_price }</td>
					<td>${wishlist.product_discount }</td>
					<td>${wishlist.wish_date }</td>
					<td><input type="button" class="deleteWish" value="찜 취소"></td>
				</tr>
			</c:forEach>
		</table>
		
		<!-------------------------- 찜전체 취소버튼 --------------------------->
		<section id="buttonArea">
			<input type="button" id="deleteWishAll" value="찜 전체취소">			
		</section>
		
		<!-------------------------- 상품명 검색버튼 --------------------------->
		<section id="buttonArea">
			<form action="Wishlist.ws">
				<%-- 로그인 시에만 검색 --%>
				<c:if test="${!empty sessionScope.sId }">
					<input type="text" name="keyword" placeholder="상품명을 입력하세요">
					<input type="submit" value="검색">
				</c:if>
			</form>
		</section>
		
		<!-------------------------- 페이지 이동버튼 --------------------------->
		<section id="pageList">
			<!-- 이전 페이지 -->
			<c:choose>
				<c:when test="${pageNum > 1}">
					<input type="button" value="이전" onclick="location.href='Wishlist.ws?pageNum=${pageNum - 1}'">
				</c:when>
				<c:otherwise>
					<input type="button" value="이전">
				</c:otherwise>
			</c:choose>
				
			<!-- 페이지 번호 목록 -->
			<c:forEach var="i" begin="${memberPageInfo.startPage }" end="${memberPageInfo.endPage }">
				<c:choose>
					<c:when test="${pageNum eq i}">
						${i }
					</c:when>
					<c:otherwise>
						<a href="Wishlist.ws?pageNum=${i }">${i }</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
	
			<!-- 다음 페이지 -->
			<c:choose>
				<c:when test="${pageNum < memberPageInfo.maxPage}">
					<input type="button" value="다음" onclick="location.href='Wishlist.ws?pageNum=${pageNum + 1}'">
				</c:when>
				<c:otherwise>
					<input type="button" value="다음">
				</c:otherwise>
			</c:choose>
		</section>
		
		<div class="clear"></div>
		
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