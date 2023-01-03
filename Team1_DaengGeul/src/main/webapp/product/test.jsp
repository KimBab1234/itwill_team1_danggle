<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/product.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<header>
		<jsp:include page="../inc/top.jsp" />
		<jsp:include page="../inc/main.jsp"/>
<%-- 		<jsp:include page="../inc/main_index.jsp"/> --%>
	</header>
	<h4 class="font-weight-semi-bold mb-4">상품 등록</h4>
	<form action="ProductRegiPro.ad" method="post" enctype="multipart/form-data" name="proRegi">
		<table class="regi_table">
			<tr>
				<th>분류</th>
				<td>
					<input type="radio" id="group" class="regi_check" value="book" name="group">책
					&nbsp;
					<select name="book_genre" class="book_genre" class="regiSelect">
						<option value="" class="book_genre" >==카테고리 선택==</option>
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
					</select><br>
					<input type="radio" id="group" class="regi_check" value="goods" name="group">굿즈
				</td>
			</tr>
			<tr>
				<th>상품명</th>
				<td>
					<input type="text" required="required" name="name" width="400" id="productName">
					<div id="book_info" style="display:none">
					<!--카테고리에서 책 선택 했을 때만 보이는 화면 -->
						<br>
						<hr>
						<b>책 정보</b><br>
						<div class="bookArea">작가명 : <input type="text" name="book_writer" id='book_div'></div>
						<div class="bookArea">출판사 : <input type="text" name="book_publisher"id="book_div"></div>
						<div class="bookArea">출판일 : <input type="date" name="book_date"></div>
					</div>
				</td>
			</tr>
			<tr>
				<th>판매가</th>
				<td>
					<input type="number" required="required" name="price" size="1000">원<br>
				</td>
			</tr>
			<tr>
				<th>할인</th>
				<td>
					<input type="radio" checked="checked" name="disc" class="regi_check">할인 설정
					<div id="disArea">
						<input type="number" id="nd" min="1" max="100" >% 할인 설정
					</div>
					<input type="hidden" id="discount" name="discount"><br>
					<input type="radio" name="disc" class="regi_check">할인 설정 안함<br>
				</td>
			</tr>
			<tr>
				<th>재고</th>
				<td><input type="number" required="required" name="quantity">개</td>
			</tr>
			<tr>
				<th>상품 대표 이미지</th>
				
				<td><input type="file" required="required" name="img"></td>
			</tr>
			<tr>
				<th>상세 설명</th>
				<td>
					<input type="file" id="file" name="detail_img">
					<hr>
					<div>
						<textarea rows="30" cols="50" name="detail"></textarea>
					</div>
				</td>
			</tr>
		</table>
		<br>
		<div align="center">
			<input type="submit" value="등록">
			<input type="button" value="취소" onclick="history.back()">
		</div>
	</form>
	<button id="testbtn">테스트</button>
	<footer>
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
	</footer>
</body>
</html>