<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/default.css" rel="stylesheet" type="text/css">
<style type="text/css">
table {
	margin-left: auto;
	margin-right: auto;
}
table, td, th {
	border-collapse: collapse;
	border: 1px solid black;
}
</style>
<link href="css/product.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<header>
		<jsp:include page="../inc/top.jsp" />
		<jsp:include page="../inc/main.jsp"/>
	</header>
	<h1 align="center">상품 수정</h1>
	<form action="ProductEditPro.ad" method="post" enctype="multipart/form-data" name="proRegi">
 		<input type="hidden" name="product_idx" value="${product.product_idx }">
 		<input type="hidden" name="old_img" value="${product.img }">
 		<input type="hidden" name="old_detail_img" value="${product.detail_img }">
 		<input type="hidden" name="pageNum" value="${param.pageNum}">
		<table class="regi_table">
				<tr>
					<th>상품정보</th>
					<td>상품명 : <input type="text" required="required" name="name" value="${product.name }">
						<div id="book_info" style="display:inline">
								<select name="book_genre" class="book_genre">
									<option value="humanities" class="book_genre">인문</option>
									<option value="novel" class="book_genre">소설</option>
									<option value="poem" class="book_genre">시</option>
									<option value="history" class="book_genre">역사</option>
									<option value="religion" class="book_genre">종교</option>
									<option value="society" class="book_genre">사회</option>
									<option value="science" class="book_genre">과학</option>
									<option value="self_improvement" class="book_genre">자기계발</option>
									<option value="kids" class="book_genre">어린이</option>
									<option value="health" class="book_genre">건강</option>
									<option value="reference" class="book_genre">참고서</option>
								</select>
								<hr>
								작가명 : <input type="text" id="book_div" name="book_writer" value="${product.book_writer}"><br>
								출판사 : <input type="text" id="book_div" name="book_publisher" value="${product.book_publisher }"><br>
								출판일 : <input type="date" name="book_date" value="${product.book_date }">
						</div>
					</td>
				</tr>
					
				<tr>
					<th>판매가</th>
					<td>
						<input type="number" required="required" name="price" value="${product.price }" class="disNum" class="number">원<br>
					</td>
				</tr>
				<tr>
					<th>할인</th>
					<td>
						<input type="radio" checked="checked" class="regi_check2" name="disc" onclick="discountSet()">
						<input type="number" id="nd" min="1" max="100" onkeyup="disKeyup(this.value)" value="${product.discount }" class="disNum">%할인 설정<br>
						<input type="radio" name="disc" class="regi_check" onclick="discountSet()">할인 설정 안함<br>
						<input type="hidden" id="discount" name="discount" value="${product.discount }">
					</td>
				</tr>
				<tr>
					<th>재고</th>
					<td><input type="number" required="required" name="quantity" value="${product.quantity }" class="number">개</td>
				</tr>
				<tr>
					<th>상품 대표 이미지</th>
					<td>
						기존 파일 : ${product.img }<br>
						<input type="file" name="img">
					</td>
				</tr>
				<tr>
					<th>상세 설명</th>
					<td>
						기존 파일 : ${product.detail_img }<br>
						<input type="file" name="detail_img"><br>
						<textarea rows="30" cols="50" name="detail">${product.detail}</textarea>
					</td>
				</tr>
		</table> 
		<br>
		<div align="center">
			<input type="submit" value="수정">
			<input type="button" value="취소" onclick="history.back()">
		</div>
	</form>
	<%-- 자바스크립트 영역 --%>
	<script type="text/javascript"> 
		// 책으로 넘어 왔을 때만 책 정보 페이지 보여줌
		
		
		var productType = '<c:out value="${productType}"/>';
	
		if(productType == "book"){
			document.getElementById("book_info").style.display = 'inline';
		} else { 	
			document.getElementById("book_info").style.display = 'none';
	
		}
		
		let genre = document.getElementsByClassName("book_genre");
		let ori_genre = '<c:out value="${product.book_genre}"/>';
		for(var i = 0; i < genre.length; i++){
			if(ori_genre == genre[i].value){
				genre[i].selected = 'selected';
			}
		}
// 		document.getElementById("discount").value = ${product.discount}
		function discountSet() {
		// 할인 설정 안함 버튼 선택 시 value값 0으로 변경
			if(document.proRegi.disc[1].checked){
				document.getElementById("discount").value = 0;
				
				
			}
		}
		function disKeyup(discount) {
		// 할인 설정 버튼 선택 시 할인율 입력 값 discount(hidden) value에 적용
			document.getElementById("discount").value = discount;
		}
		
	</script>
	<footer>
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
	</footer>
</body>
</html>