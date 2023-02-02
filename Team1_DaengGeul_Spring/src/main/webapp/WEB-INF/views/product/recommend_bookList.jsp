<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>추천 도서 관리</title>
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<link href="${pageContext.request.contextPath }/resources/css/product.css" rel="stylesheet" type="text/css" />
<script src="https://kit.fontawesome.com/8f75f06127.js" crossorigin="anonymous"></script>
<script type="text/javascript">

	
	

	$(function() {
		

		
		$.ajax({
			type: "GET",
			url: "RecommendJson",
			dataType: "json"
		})
		.done(function(bookList) {
			
			
			for(let book of bookList) {
				
				let result = "<tr>"
							+ "<td>" + book.product_idx + "</td>"
							+ "<td>" + book.name + "</td>"
							+ "<td>" + book.quantity + "</td>"
						+ "<td>" + "<input type='button' id='Listbtn' class='recobtn' value='삭제'>"
						 + "<input type='hidden' class='recoHidden' value='"+book.product_idx +"'>" + "</td>"
							+ "</tr>";
				
				// 지정된 위치(table 태그 내부)에 JSON 객체 출력문 추가
				$(".recoTable").append(result);
			}
			
			$(".recobtn").on("click", function() {
				let product = $(this).next().val()
		 		var deleteBook = confirm("추천 도서 목록에서 삭제하시겠습니까?");
		 		if(deleteBook){
		 			location.href = "RecommendBookDelete.ad?product_idx=" + product;
		 		}
			});
			
		})
		.fail(function() {
			$(".recoTable").append("<h3>요청 실패!</h3>");
		});
		
		
		
	});
	
	

// 	function deleteReco(product){
		
// 	}

	
	$(function() {
	       $('.dropdown-toggle', this).trigger('click').blur();
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
		<h4 id="recoH4">추천 도서 목록</h4><br>
		<div id="bookList">
			<table class="recoTable" style="margin-bottom: 100px;">
				<tr>
					<th width="85">상품 번호</th>
					<th width="400">상품명</th>
					<th width="60">재고</th>
					<th width="55">삭제</th>
				<tr>
				
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