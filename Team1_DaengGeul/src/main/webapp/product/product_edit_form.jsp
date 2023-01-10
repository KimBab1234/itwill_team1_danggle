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
<%	String sId = (String)session.getAttribute("sId");
	if(sId == null || !sId.equals("admin")) { %> 
		alert("잘못된 접근입니다");
		history.back();
<%  }%>
	$(function() {
		
		var optionIndex = 0;
		var sum = 0;
		
		
		// 파일 이름 인풋 박스에 넣기
		$("#detail_img").on('change',function(){
			  var fileName = $("#detail_img").val();
			  $("#detail_img_name").val(fileName);
		});
		$("#img").on('change',function(){
			  var fileName = $("#img").val();
			  $("#img_name").val(fileName);
		});
		
		// 할인 설정 안함 선택 시 할인율 0으로 변경
		$(".regi_check").on("click", function() {
			$("input[name=discount]").val(0);
			$("input[type=number][id=disPercent]").val(0);
		});
		// 할인율 설정 시 입력한 할인율 반영
		$(".regi_check2").on("click", function() {
			var disNum = $("input[type=number][id=disPercent]").val();
			$("input[name=discount]").val(disNum);
		});
		// 할인율 입력 시 hidden 박스에 반영
		$("input[type=number][id=disPercent]").on("keyup", function() {
			var disPercent = $("input[type=number][id=disPercent]").val();
			$("input[name=discount]").val(disPercent);
		});
		
		$.total = function() {
			var NumLength = $("input[type=number]").length;
			sum = 0;
			for(var i= 0; i < NumLength-3; i++){
				sum += Number($("input[type=number][name=option_qauntity]").eq(i).val());
			}
			$("input[type=number][name=quantity]").val(sum);
		};
		$("#optionBtn").on("click", function() {
			$("#optionArea").append(
				'<div id="optionDiv' + optionIndex +'"><input type="text" name="option_name" placeholder="옵션명"> <input type="number" name="option_qauntity" id="optionNum'+ optionIndex +'" class="optionNum" placeholder="수량"><input type="button" value="✖️" class="removeOpt" onclick="removeOption('+ optionIndex + ')"></div>'
			);
			optionIndex++;
			var NumLength = $("input[type=number]").length;
			$("input[type=number][name=option_qauntity]").on("change", function() {
				$.total();
			});
			
			$(".removeOpt").on("click", function() {
				$(this).parent().remove();
				var NumLength = $("input[type=number]").length;
				$.total();
			});

		});
		
	});
	
</script>
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
	<h4 align="center">상품 수정</h4>
	<form action="ProductEditPro.ad" method="post" enctype="multipart/form-data" name="proRegi">
		<input type="hidden" name="pageNum" value="${param.pageNum }">
 		<input type="hidden" name="product_idx" value="${product.product_idx }">
 		<input type="hidden" name="old_img" value="${product.img }">
 		<input type="hidden" name="old_detail_img" value="${product.detail_img }">
 		<input type="hidden" name="pageNum" value="${param.pageNum}">
		<table class="regi_table">
			<tr>
				<th>상품명</th>
				<td>
					<input type="text" required="required" name="name" id="productName" value="${product.name }">
				</td>
			</tr>
			<c:if test="${productType eq 'book'}">
				<tr>
					<th>카테고리</th>
					<td>
						<div id="selectBookcate">
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
						</div>			
					</td>	
				</tr>
				<tr>
					<th>책 정보</th>
					<td>
						<div class="bookArea">작가명 : <input type="text" name="book_writer" id='book_div' value="${product.book_writer}"></div>
						<div class="bookArea">출판사 : <input type="text" name="book_publisher"id="book_div" value="${product.book_publisher }"></div>
						<div class="bookArea">출판일 : <input type="date" name="book_date" value="${product.book_date }"></div>
					</td>
				</tr>
			</c:if>
			<c:if test="${productType eq 'goods'}">
				<tr>
					<th>옵션</th>
					<td>
					<div id="optionArea">
						<c:if test="${product.option_name.size() ne 0}">
							<c:forEach var="i" begin="0" end="${product.option_name.size() -1 }">
								<div id="optionDiv' + optionIndex +'"><input type="text" name="option_name" value="${product.option_name.get(i)}">
								<input type="number" name="option_qauntity" id="optionNum'+ optionIndex +'" class="optionNum" value="${product.option_qauntity.get(i)}">
								<input type="button" value="✖️" class="removeOpt" onclick="removeOption('+ optionIndex + ')"></div>
							</c:forEach>
						</c:if>
					</div>
						<input type="button" value="옵션 추가" id="optionBtn" class="optionBtn">
					</td>
				</tr>
			</c:if>
			<tr>
				<th>판매가</th>
				<td>
					<input type="number" required="required" name="price" value="${product.price }" class="number">원<br>
				</td>
			</tr>
			<tr>
				<th>할인</th>
				<td>
					<input type="radio" id="regi_check2" class="regi_check2" name="disc">
					<input type="number" id="disPercent" name="not" min="0" max="100" class="disNum" value="${product.discount }">%할인 설정<br>
					<input type="radio" name="disc" id="regi_check" class="regi_check">할인 설정 안함<br>
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
				<div class="filebox">
   					<input class="upload-name" value="현재 이미지 : ${product.img }" id="img_name">
    				<label for="img">파일찾기</label> 
   					<input type="file"name="img" id="img">
				</div>
				</td>
			</tr>
			<tr>
				<th>상세 설명</th>
				<td>
					<div class="filebox">
   						<input class="upload-name" value="현재 이미지 : ${product.detail_img }" id="detail_img_name">
    					<label for="detail_img">파일찾기</label> 
   						<input type="file" id="detail_img" name="detail_img">
					</div>

					<hr>
					<div id="textDiv">
						<textarea rows="30" cols="50" name="detail">${product.detail}</textarea>
					</div>
				</td>
			</tr>
		</table> 
		<br>
		<div align="center">
			<input type="submit" id="okBtn" value="수정">
			<input type="button" id="okBtn" value="취소" onclick="history.back()">
		</div>
	</form>
	<%-- 자바스크립트 영역 --%>
	<script type="text/javascript"> 
		
		let genre = document.getElementsByClassName("book_genre");
		let ori_genre = '<c:out value="${product.book_genre}"/>';
		for(var i = 0; i < genre.length; i++){
			if(ori_genre == genre[i].value){
				genre[i].selected = 'selected';
			}
		}
		
		let disZero = '<c:out value="${product.discount}"/>';
		if(disZero == 0){
			document.getElementById("regi_check").checked = true;
			document.getElementById("regi_check2").checked = false;
			
		}else if(disZero != 0){
			document.getElementById("regi_check").checked = false;
			document.getElementById("regi_check2").checked = true;
		}
		
	</script>
	<footer>
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
	</footer>
</body>
</html>