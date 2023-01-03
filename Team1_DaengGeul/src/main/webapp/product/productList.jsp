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
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">
	
	$(function() {
		var tdArr = new Array();
		
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
		
// 		$("input[type=checkbox][class=recoCheck]").on("click", function() {
// 			var checkbox = $("input[type=checkbox][class=recoCheck]:checked");
			
// 			checkbox.each(function(i) {
// 				var tr = checkbox.parent().parent().eq(i);
// 				var td = tr.children();
				
// 				var idx = td.eq(1).text();
				
// 				tdArr.push(idx);

// 				console.log(tdArr);
// 			});
			
				
			$("#recoBtn").on("click", function() {
				var checkbox = $("input[type=checkbox][class=recoCheck]:checked");
				
				checkbox.each(function(i) {
					var tr = checkbox.parent().parent().eq(i);
					var td = tr.children();
					
					var idx = td.eq(1).text();
					
					tdArr.push(idx);
				
				});
				//=======================================
				function unique(tdArr) {
				    
				    var result = [];
				    $.each(tdArr, function(index, element) {   // 배열의 원소수만큼 반복
				 
				        if ($.inArray(element, result) == -1) {  // result 에서 값을 찾는다.  //값이 없을경우(-1)
				            result.push(element);              // result 배열에 값을 넣는다.
				        }
				        
				    });
				    return result;
				}

				sessionStorage.setItem("recoProduct", JSON.stringify(unique(tdArr)));
				var book_idx = JSON.parse(sessionStorage.getItem("recoProduct"));
		
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
	<h4 align="center">상품 관리</h4><br>
	<div id="product_choice">
		<input type="checkbox" value="book" id="productType" class="book" checked="checked"> 책
		<input type="checkbox" value="goods" id="productType" class="goods"> 굿즈
	</div>
	<div id="bookList">
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
						<td><input type="checkbox" name="recoCheck" class="recoCheck"></td>
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
		<div id="recoBtnArea"><button id="recoBtn" onclick="location.href='RecommendBook.ad'">추천 도서 등록</button></div>
		<section id="pageList">
		<c:choose>
			<c:when test="${pageNum > 1}">
				<input type="button" id="pageBtn" value="<<" onclick="location.href='ProductList.ad?pageNum=${pageNum - 1}'">
			</c:when>
			<c:otherwise>
				<input type="button" id="pageBtn" value="<<">
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
				<input type="button" id="pageBtn" value=">>" onclick="location.href='ProductList.ad?pageNum=${pageNum + 1}'">
			</c:when>
			<c:otherwise>
				<input type="button" value=">>" id="pageBtn">
			</c:otherwise>
		</c:choose>
	</section> 
	</div>

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
						<td>${product.price }</td>
						<c:choose>
							<c:when test="${product.discount } eq 0">
								<td>${product.price }원(할인 없음)</td>
							</c:when>
							<c:otherwise>
								<td><fmt:formatNumber type="number" maxFractionDigits="0"  value="${product.price - (product.price * product.discount / 100)}" />
							원(${product.discount }% 할인)</td>
							</c:otherwise>
						</c:choose>
						<td><input type="button" value="수정" onclick="location.href='ProductEditForm.ad?product_idx=${product.product_idx}'"></td>
						<td><input type="button" value="삭제" onclick="location.href='ProductDelete.ad?product_idx=${product.product_idx}'"></td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</div>
	<div id="resultArea"></div>
	<footer>
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
	</footer>
</body>
</html>