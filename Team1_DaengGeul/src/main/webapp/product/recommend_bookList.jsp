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
<script type="text/javascript">
<%	String sId = (String)session.getAttribute("sId");
	if(sId == null || !sId.equals("admin")) { %> 
		alert("잘못된 접근입니다");
		history.back();
<%  }%>

	function deleteBook(product){
		
		var deleteBook = confirm("추천 도서 목록에서 삭제하시겠습니까?");
		if(deleteBook){
			location.href = "RecommendBookDelete.ad?product_idx=" + product;
 		}
	}
</script>
<link rel="shortcut icon" href="#">
</head>
<body>
	<header>
		<jsp:include page="../inc/top.jsp" />
		<jsp:include page="../inc/main.jsp"/>
	</header>
	<h4 align="center">추천 도서 목록</h4><br>
	<div id="bookList">
		<table class="recoTable">
			<tr>
				<th width="85">상품 번호</th>
				<th width="400">상품명</th>
				<th width="60">재고</th>
				<th width="55">삭제</th>
			<tr>
			<c:forEach var="book" items="${recoList }">
				<tr>
					<td>${book.product_idx}</td>
					<td>${book.name}</td>
					<td>${book.quantity}</td>
					<td><input type="button" id="Listbtn" class="recobtn" value="삭제" onclick="deleteBook('${book.product_idx}')"></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<footer>
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
	</footer>
</body>
</html>