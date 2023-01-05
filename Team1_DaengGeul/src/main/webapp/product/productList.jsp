<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/product.css" rel="stylesheet" type="text/css" />
<script src="https://kit.fontawesome.com/8f75f06127.js" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">
	
	$(function() {
		// 굿즈 등록 후에만 굿즈 목록이 보이도록 함
		// 기본 목록 페이지는 책 목록임
		const URLSearch = new URLSearchParams(location.search);
		var product = URLSearch.get('product');
		if(product == "G"){
			$("input[type=checkbox][class=book]").prop("checked", false);
			$("input[type=checkbox][class=goods]").prop("checked", true);
			$("#goodsList").show();
			$("#bookList").hide();
			$("#recoBook").hide();
		}

		// 체크박스 책, 굿즈 중 하나만 누를 수 있게 하기
		$("input[type=checkbox][id=productType]").on("click", function() {
			if(this.checked){
				const checkboxes = $("input[type=checkbox]");
				for(let ind = 0; ind < checkboxes.length; ind++){
					checkboxes[ind].checked = false;
				}
				this.checked = true;
			} else {
				this.checked = false;
			}
		});
		
		// 체크버튼 상태에 따라 다른 목록 보여주기
		$("input[type=checkbox][id=productType]").on("click", function() {
			if($("input[type=checkbox][class=book]").is(":checked") == true) {
				$("#bookList").show();
				$("#goodsList").hide();
			} else if($("input[type=checkbox][class=goods]").is(":checked") == true){
				$("#goodsList").show();
				$("#bookList").hide();
				$("#recoBook").hide();
			}
		});
		
		$("#recoBtn").on("click", function() {
			if($(".recoCheck").is(":checked") == false){
				alert("추천 도서는 한 권 이상 선택되어야 합니다.");
				return false;
			}
		});
		
	});
	
</script>
<link rel="shortcut icon" href="#">
</head>
<body>
	<header>
		<jsp:include page="../inc/top.jsp" />
		<jsp:include page="../inc/main.jsp"/>
	</header>
	<div class="recoArea">
		<h4 align="center">상품 관리</h4><br>
		<div id="product_choice">
			<input type="checkbox" value="book" id="productType" class="book" checked="checked"> 책
			<input type="checkbox" value="goods" id="productType" class="goods"> 굿즈
		</div>
		<div id="bookList">
		<form action="RecommendBook.ad" method="post" >
			<table class="bookTable">
				<tr>
					<th width="90">추천 도서</th>
					<th width="90">상품 번호</th>
					<th width="310">상품명</th>
					<th width="60">재고</th>
					<th width="110">가격</th>
					<th width="190">판매가</th>
					<th width="55">수정</th>
					<th width="55">삭제</th>
				<tr>
				<c:forEach var="product" items="${productList }">
					<c:choose>
						<c:when test="${empty param.pageNum }">
							<c:set var="pageNum" value="1" />
						</c:when>
						<c:otherwise>
							<c:set var="pageNum" value="${param.pageNum }" />
						</c:otherwise>
					</c:choose>
					<c:if test='${product.product_idx.substring(0,1) == "B"}'>
						<tr>
							<td><input type="checkbox" name="recoCheck" class="recoCheck" value="${product.product_idx }"></td>
							<td>${product.product_idx}</td>
							<td>${product.name}</td>
							<td>${product.quantity}</td>
							<td><fmt:formatNumber type="number" value="${product.price}"/>원</td>
							<c:choose>
								<c:when test="${product.discount } eq 0">
									<td>${product.price }원(할인 없음)</td>
								</c:when>
								<c:otherwise>
									<td><fmt:formatNumber type="number" maxFractionDigits="0"  value="${product.price - (product.price * product.discount / 100)}" />원
									(${product.discount }% 할인)</td>
								</c:otherwise>
							</c:choose>
							<td><input type="button" id="Listbtn" value="수정" onclick="location.href='ProductEditForm.ad?product_idx=${product.product_idx}&pageNum=${pageNum }'"></td>
							<td><input type="button" id="Listbtn" value="삭제" onclick="location.href='ProductDelete.ad?product_idx=${product.product_idx}&pageNum=${pageNum }'"></td>
						</tr>
					</c:if>
				</c:forEach>
			</table>
			<div>
				<input type="submit" value="추천 도서 등록" id="recoBtn" >
			</div>
		</form>
		<section id="pageList">
			<c:choose>
				<c:when test="${pageNum > 1}">
					<a href="ProductList.ad?pageNum=${pageNum - 1}"><i class="fas fa-solid fa-angles-left"></i></a>
				</c:when>
				<c:otherwise>
					<a><i class="fas fa-solid fa-angles-left"></i></a>
				</c:otherwise>
			</c:choose>
			<c:forEach var="i" begin="${pageInfo.startPage }" end="${pageInfo.endPage }">
				<c:choose>
					<c:when test="${pageNum eq i}">
						<div class="here">${i }</div>
					</c:when>
					<c:otherwise>
						<a href="ProductList.ad?pageNum=${i }"><div class="notHere">${i }</div></a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${pageNum < pageInfo.maxPage}">
					<a href="ProductList.ad?pageNum=${pageNum + 1}"><i class="fas fa-solid fa-angles-right"></i></a>
				</c:when>
				<c:otherwise>
					<a><i class="fas fa-solid fa-angles-right"></i></a>
				</c:otherwise>
			</c:choose>
		</section> 
		</div>
	</div>
	<%-- ===================== 굿즈 목록 ====================== --%>
	<div id="goodsList" style="display:none">
		<table class="goodsTable">
			<tr>
				<th width="80">상품 번호</th>
				<th width="280">상품명</th>
				<th width="50">재고</th>
				<th width="100">가격</th>
				<th width="180">판매가</th>
				<th width="55">수정</th>
				<th width="55">삭제</th>
			<tr>
			<c:forEach var="product" items="${productList }">
				<c:if test="${product.product_idx.substring(0,1) == 'G'}">
					<tr>
						<td>${product.product_idx}</td>
						<td>${product.name}</td>
						<td>${product.quantity}</td>
						<td>${product.price }원</td>
						<c:choose>
							<c:when test="${product.discount } eq 0">
								<td>${product.price }원(할인 없음)</td>
							</c:when>
							<c:otherwise>
								<td><fmt:formatNumber type="number" maxFractionDigits="0"  value="${product.price - (product.price * product.discount / 100)}" />원
								 (${product.discount }% 할인)</td>
							</c:otherwise>
						</c:choose>
						<td><input type="button" id="Listbtn" value="수정" onclick="location.href='ProductEditForm.ad?product_idx=${product.product_idx}'"></td>
						<td><input type="button" id="Listbtn" value="삭제" onclick="location.href='ProductDelete.ad?product_idx=${product.product_idx}'"></td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</div>
	<footer>
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
	</footer>
</body>
</html>