<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="#">
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">
	
	function discountSet() {
	// 할인 설정 버튼 선택 시 할인 금액 입력 창 활성화 / 비활성화
		if(document.proRegi.disc[1].checked){
			document.getElementById("discount").value = 0;
		}
	}
	
	function disKeyup(discount) {
	// 할인 설정 버튼 선택 시 할인율 입력 값 discount(hidden) value에 적용
		document.getElementById("discount").value = discount;
	}

	$(function() {
		
		// 책 선택 시 책 정보 입력 창 보여주기	
		$("input[type=radio][class=regi_check]").on("click", function() {

			if($("input[type=radio][class=regi_check]:checked").val() == "book"){
				$("#book_info").show();
			}else if($("input[type=radio][class=regi_check]:checked").val() == "goods"){
				$("#book_info").hide();
			}
 			
		});
		
		$("#detail_img").on('change',function(){
			  var fileName = $("#detail_img").val();
			  $("#detail_img_name").val(fileName);
		});
		
		$("#img").on('change',function(){
			  var fileName = $("#img").val();
			  $("#img_name").val(fileName);
		});
		
	});
</script>
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
					<input type="radio" id="group_book" class="regi_check" value="book" name="group" checked="checked">책
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
					<input type="radio" id="group_goods" class="regi_check" value="goods" name="group">굿즈
				</td>
			</tr>
			<tr>
				<th>상품명</th>
				<td>
					<input type="text" required="required" name="name" width="400" id="productName">
				</td>
			</tr>
			<tbody id="book_info">
				<tr>
					<th>책 정보</th>
					<td>
						<div class="bookArea">작가명 : <input type="text" name="book_writer" id='book_div'></div>
						<div class="bookArea">출판사 : <input type="text" name="book_publisher"id="book_div"></div>
						<div class="bookArea">출판일 : <input type="date" name="book_date"></div>
					</td>
				</tr>
			</tbody>
			<tr>
				<th>판매가</th>
				<td>
					<input type="number" class="number" required="required" name="price" size="1000">원<br>
				</td>
			</tr>
			<tr>
				<th>할인</th>
				<td>
					<input type="radio" checked="checked" class="regi_check2" name="disc">
					<input type="number" class="disNum" min="1" max="100" onkeyup="disKeyup(this.value)">% 할인 설정
					<input type="hidden" id="discount" name="discount"><br>
					<input type="radio" name="disc" class="regi_check" onclick="discountSet()">할인 설정 안함<br>
				</td>
			</tr>
			<tr>
				<th>재고</th>
				<td><input type="number" class="number" required="required" name="quantity">개</td>
			</tr>
			<tr>
				<th>상품 대표 이미지</th>
				<td>
				<div class="filebox">
   					<input class="upload-name" value="첨부파일" placeholder="첨부파일" id="img_name">
    				<label for="img">파일찾기</label> 
   					<input type="file" required="required" name="img" id="img">
				</div>
				</td>
			</tr>
			<tr>
				<th>상세 설명</th>
				<td>
					<div class="filebox">
   						<input class="upload-name" value="첨부파일" placeholder="첨부파일" id="detail_img_name">
    					<label for="detail_img">파일찾기</label> 
   						<input type="file" id="detail_img" name="detail_img">
					</div>

					<hr>
					<div id="textDiv">
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
	<footer>
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
	</footer>
</body>
</html>