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
	</div>
</div>
	<footer>
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
	</footer>
</body>
</html>