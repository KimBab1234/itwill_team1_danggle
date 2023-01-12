<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 수정</title>
<link href="img/dot_daram.gif" rel="shortcut icon" type="image/x-icon">
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">

<%	String sId = (String)session.getAttribute("sId");
	if(sId == null || !sId.equals("admin")) { %>  
		alert("잘못된 접근입니다");
		history.back();
<%  }%>
	
	let productType = '<c:out value="${productType}"/>';




	$(function() {
		
		if(productType == "goods"){
			
			$("#writer").prop("required", false);
			$("#publisher").prop("required", false);
			$("#date").prop("required", false);
		}
	    $('.dropdown-toggle', this).trigger('click').blur();
		
	    var optionIndex = 0;
		var sum = 0;
		
		// 파일 이름 인풋 박스에 넣기
		$("#detail_img").on('change',function(){
			  var fileName = $("#detail_img").val().split('/').pop().split('\\').pop();;
			  $("#detail_img_name").val(fileName);
		});
		$("#img").on('change',function(){
			  var fileName = $("#img").val().split('/').pop().split('\\').pop();;
			  $("#img_name").val(fileName);
		});
		
		// 할인 설정 안함 선택 시 할인율 0으로 변경, 할인율 입력창 초기화, required 속성 해제, readonly 속성 추가
		$(".regi_check").on("click", function() {
			$("input[name=discount]").val(0);
			$("input[type=number][id=disPercent]").val("");
			$(".disNum").attr("required", false);
			$(".disNum").attr("readonly", true);
			
			
		});
		// 할인율 설정 시 입력한 할인율 반영
		$(".regi_check2").on("click", function() {
			var disNum = $("input[type=number][id=disPercent]").val();
			$("input[name=discount]").val(disNum);
			$(".disNum").attr("required", true);
			$(".disNum").attr("readonly", false);
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
		// 기존에 있던 옵션 수정 시 총 재고 계산
		$("input[type=number][name=option_qauntity]").on("change", function() {
			$.total();
		});
		// 기존에 있던 옵션 삭제 버튼 동작
		$(".removeOpt").on("click", function() {
			$(this).parent().remove();
			var NumLength = $("input[type=number]").length;
			$.total();
		});
		
	});
	
</script>
<link href="css/product.css" rel="stylesheet" type="text/css" />
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
	<div style="width : 1200px;">
	<h4 id="h4">상품 수정</h4><br>
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
						<div class="bookArea">작가명 : <input type="text" required="required" id="writer" name="book_writer" class="book_div" value="${product.book_writer}"></div>
						<div class="bookArea">출판사 : <input type="text" required="required" id="publisher" name="book_publisher" class="book_div" value="${product.book_publisher }"></div>
						<div class="bookArea">출판일 : <input type="date" required="required" id="date" name="book_date" value="${product.book_date }"></div>
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
								<input type="button" value="✖️" class="removeOpt" ></div>
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
					<input type="number" id="disPercent" required="required" name="not" min="0" max="100" class="disNum" value="${product.discount }">%할인 설정<br>
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
			<input type="button" id="okBtn" value="취소" onclick="location.href='ProductList.ad?pageNum=${param.pageNum }'">
		</div>
	</form>
	</div>
</div>
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