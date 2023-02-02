<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 관리</title>
<link href="${pageContext.request.contextPath }/resources/css/product.css" rel="stylesheet" type="text/css" />
<script src="https://kit.fontawesome.com/8f75f06127.js" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">
	
	function Gdele(product_idx) {
		var ment = confirm(product_idx + " 상품을 삭제하시겠습니까?");
		if(ment){
			location.href="ProductDelete.ad?product_idx="+product_idx;
		}
		
	}
	
	function Bdele(product_idx, pageNum) {
		var ment = confirm(product_idx + " 상품을 삭제하시겠습니까?");
		if(ment){
			location.href="ProductDelete.ad?product_idx="+product_idx+"&pageNum="+pageNum;
		}
		
	}
	
	
	$(function() {
		$('.dropdown-toggle', this).trigger('click').blur();
		
		$("#choice_book").css("background", "#c9b584").css("color", "#736643");
		$("#choice_goods").css("background", "#736643").css("color", "#c9b584");
		
		// 굿즈 등록 후에만 굿즈 목록이 보이도록 함
		// 기본 목록 페이지는 책 목록임
		const URLSearch = new URLSearchParams(location.search);
		var product = URLSearch.get('product');
		if(product == "G"){
			$("input[type=checkbox][class=book]").prop("checked", false);
			$("input[type=checkbox][class=goods]").prop("checked", true);
			$("#goodsList").show();
			$("#bookList").hide();
			$("#choice_goods").css("background", "#c9b584").css("color", "#736643");
			$("#choice_book").css("background", "#736643").css("color", "#c9b584");
		}
		
		$("#choice_book").on("click", function() {
			$("#bookList").show();
			$("#goodsList").hide();
			$("#choice_book").css("background", "#c9b584").css("color", "#736643");
			$("#choice_goods").css("background", "#736643").css("color", "#c9b584");
		});
		$("#choice_goods").on("click", function() {
			$("#bookList").hide();
			$("#goodsList").show();
			$("#choice_goods").css("background", "#c9b584").css("color", "#736643");
			$("#choice_book").css("background", "#736643").css("color", "#c9b584");
			
			
		});
		
		$("#recoBtn").on("click", function() {
			if($(".recoCheck").is(":checked") == false){
				alert("추천 도서는 한 권 이상 선택되어야 합니다.");
				return false;
			}
		});
	
	});
	
</script>
<link href="img/dot_daram.gif" rel="shortcut icon" type="image/x-icon">
</head>
<body>
	<header>
		<jsp:include page="../inc/top.jsp" />
		<jsp:include page="../inc/main.jsp"/>
	</header>

	<div class="recoArea" style="width: 1800px; margin-left: 50px;">
	<div align="left" style="width: 300px; margin-top: 100px;">
		<jsp:include page="../inc/memberInfo_left.jsp"></jsp:include> <!-- 본문1 -->
	</div>
	<div style="width : 1200px; ">
		<h4 id="listH4">상품 관리</h4><br>
		<div class="choice" id="choice_book">책</div><div class="choice" id="choice_goods">굿즈</div>
		<div id="buttonArea">
			<form action="ProductList.ad">
				<!-- 검색 타입 추가 -->
				<select name="searchType" id="searchType">
					<option value="name" <c:if test="${param.searchType eq 'name'}">selected</c:if>>상품명</option>
					<option value="product_idx" <c:if test="${param.searchType eq 'product_idx'}">selected</c:if>>상품번호</option>
				</select>
				<input type="text" name="keyword" id="keyword" value="${param.keyword }">
				<input type="submit" id="keywordBtn" value="검색">
			</form>
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
							<c:choose>
								<c:when test="${product.quantity > 0}">
									<td>${product.quantity}</td>
								</c:when>
								<c:when test="${product.quantity < 1}">
									<td>품절</td>
								</c:when>
							</c:choose>
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
							<td><input type="button" id="Listbtn" value="삭제" class="BdeleBtn" onclick="Bdele('${product.product_idx}', '${pageNum }')"></td>
						</tr>
					</c:if>
				</c:forEach>
			</table>
			<div>
				<input type="submit" value="추천 도서 등록" id="recoBtn">
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
	
	<%-- ===================== 굿즈 목록 ====================== --%>
		<div id="goodsList" style="display:none">
			<table class="bookTable" style="margin-bottom: 100px;">
				<tr>
					<th width="90">상품 번호</th>
					<th width="430">상품명</th>
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
							<c:choose>
								<c:when test="${product.quantity > 0}">
									<td>${product.quantity}</td>
								</c:when>
								<c:when test="${product.quantity < 1}">
									<td>품절</td>
								</c:when>
							</c:choose>
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
							<td><input type="button" id="Listbtn" value="삭제" class="GdeleBtn" onclick="Gdele('${product.product_idx}')"></td>
						</tr>
					</c:if>
				</c:forEach>
			</table>
			<section id="pageList">
					<a><i class="fas fa-solid fa-angles-left"></i></a>
					<div class="here">1</div>
					<a><i class="fas fa-solid fa-angles-right"></i></a>
			</section> 
		</div>
		</div>
	</div>
	<footer>
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
	</footer>
</body>
</html>