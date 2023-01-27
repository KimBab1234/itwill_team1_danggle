<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 등록</title>
<link href="img/dot_daram.gif" rel="shortcut icon" type="image/x-icon">
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">


	var optionIndex = 0;
	var sum = 0;
	
	function discountSet() {
		// 할인 설정 안함 체크 시 할인율 입력창 초기화, discount(hidden) value값 0으로 변경, 할인율 입력창 required 속성 해제, readonly 속성 추가
		if(document.proRegi.disc[1].checked){
			document.getElementById("discount").value = 0;
			document.getElementById("disNum").value = "";
			$("#disNum").attr("required", false);
			$("#disNum").attr("readonly", true);
		}
	}
	
	function disKeyup(discount) {
	// 할인 설정 버튼 선택 시 할인율 입력 값 discount(hidden) value에 적용
		document.getElementById("discount").value = discount;
	}

	$(function() {
		$('.dropdown-toggle', this).trigger('click').blur();
		
		// 할인 설정 체크 시 readonly 속성 해제, required 속성 추가 
		$(".regi_check2").on("click", function() {
			$("#disNum").attr("required", true);
			$("#disNum").attr("readonly", false);
		});
		
		$("#goods_option").hide();
		// 책 선택 시 책 정보 입력 창 보여주기
		// 굿즈 선택 시 옵션 창 보여주기
		$("input[type=radio][class=regi_check]").on("click", function() {

			if($("input[type=radio][class=regi_check]:checked").val() == "book"){
				$("#book_info").show();
				$("#goods_option").hide();
				
			}else if($("input[type=radio][class=regi_check]:checked").val() == "goods"){
				$("#book_info").hide();
				$("#goods_option").show();
				// 굿즈 선택 시 책 관련 required 속성 해제
				$("#writer").attr("required", false);
				$("#publisher").attr("required", false);
				$("#date").attr("required", false);
				$(".book_genre").attr("required", false);
			}
 			
		});
		// 이미지 파일 선택 시 input 박스에 파일 이름 출력
		$("#detail_img").on('change',function(){
			  var fileName = $("#detail_img").val().split('/').pop().split('\\').pop();;
			  $("#detail_img_name").val(fileName);
		});
		
		$("#img").on('change',function(){
			  var fileName = $("#img").val().split('/').pop().split('\\').pop();;
			  $("#img_name").val(fileName);
		});
		
		// 굿즈 총 재고 계산하는 함수
		$.total = function() {
			var NumLength = $("input[type=number]").length;
			sum = 0;
			for(var i= 0; i < NumLength-3; i++){
				sum += Number($("input[type=number][name=option_qauntity]").eq(i).val());
			}
			$("input[type=number][name=quantity]").val(sum);
		};
		
		// 옵션 추가 버튼 클릭 시 div 영역에 인풋 박스 추가
		$("#optionBtn").on("click", function() {
			$("#optionArea").append(
				'<div id="optionDiv' + optionIndex +'"><input type="text" name="option_name" placeholder="옵션명"><input type="number" width="100" name="option_qauntity" id="optionNum'+ optionIndex +'" class="optionNum" placeholder="수량"><input type="button" value="✖️" class="removeOpt" onclick="removeOption('+ optionIndex + ')"></div>'
			);
			optionIndex++;
			var NumLength = $("input[type=number]").length;
			
			// 옵션별 재고량 입력 시 총 재고에 더해짐
			$("input[type=number][name=option_qauntity]").on("change", function() {
				$.total();
			});
			
			// 옵션 삭제 시 재고량 빼기
			$(".removeOpt").on("click", function() {
				$(this).parent().remove();
				var NumLength = $("input[type=number]").length;
				$.total();
			});

		});
		
	});
</script>
<link href="${pageContext.request.contextPath }/resources/css/product.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<header>
		<jsp:include page="../inc/top.jsp" />
		<jsp:include page="../inc/main.jsp"/>
<%-- 		<jsp:include page="../inc/main_index.jsp"/> --%>
	</header>
	<div class="recoArea" style="width: 1800px; margin-left: 50px;">
	<div align="left" style="width: 300px; margin-top: 100px;">
		<jsp:include page="../inc/memberInfo_left.jsp"></jsp:include> <!-- 본문1 -->
	</div>
	<div style="width : 1200px;" >
	<h4 id="h4">상품 등록</h4><br>
	<form action="ProductRegiPro.ad" method="post" enctype="multipart/form-data" name="proRegi">
		<table class="regi_table">
			<tr>
				<th>분류</th>
				<td>
					<input type="radio" id="group_book" class="regi_check" value="book" name="group" checked="checked">책
					&nbsp;
					<select name="genre" class="book_genre" required="required">
						<option value="" class="book_genre">==카테고리 선택==</option>
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
						<div class="bookArea">작가명 : <input type="text" id="writer" name="writer" class='book_div' required="required"></div>
						<div class="bookArea">출판사 : <input type="text" id="publisher" name="publisher" class="book_div" required="required"></div>
						<div class="bookArea">출판일 : <input type="date" id="date" name="date" required="required"></div>
					</td>
				</tr>
			</tbody>
			<tbody id="goods_option">
				<tr>
					<th>옵션</th>
					<td>
					<div id="optionArea"></div>
						<input type="button" value="옵션 추가" id="optionBtn" class="optionBtn">
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
					<input type="number" class="disNum" min="1" id="disNum" max="100" onkeyup="disKeyup(this.value)" required="required">% 할인 설정
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
   					<input class="upload-name" placeholder="선택된 파일 없음" id="img_name">
    				<label for="img">파일찾기</label> 
   					<input type="file" required="required" name="files" id="img">
				</div>
				</td>
			</tr>
			<tr>
				<th>상세 설명</th>
				<td>
					<div class="filebox">
   						<input class="upload-name" placeholder="선택된 파일 없음" id="detail_img_name">
    					<label for="detail_img">파일찾기</label> 
   						<input type="file" id="detail_img" name="files">
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
			<input type="submit" id="okBtn" value="등록">
			<input type="button" id="okBtn" value="취소" onclick="history.back()">
		</div>
	</form>
		</div>
	</div>

	<footer>
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
	</footer>
</body>
</html>