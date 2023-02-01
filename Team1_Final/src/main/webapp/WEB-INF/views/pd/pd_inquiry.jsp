<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<style type="text/css">
table {
	font-size: 20px;
	font-weight: bold;
}

#td_left {
	text-align: left;
	width: 200px;
	
}
</style>
<script type="text/javascript">

	let pageNum = 1;
	
	$(function() {
		let searchType = $("#searchType").val();
		let keyword = $("#keyword").val();
		
		load_list(searchType, keyword);
		
		$(window).scroll(function() {
			
			let scrollTop = $(window).scrollTop();
			let windowHeigtht = $(window).height();
			let documentHeight = $(document).height();
			
			if(scrollTop + windowHeigtht + 1 >= documentHeight) {
				pageNum++;
				load_list(searchType, keyword);
			}
			
		});
	});
	
	function load_list(searchType, keyword) {

		$.ajax({
			type: "GET",
// 			url: "BoardListJson.bo?pageNum=" + pageNum,
			url: "PdInquiryJson?pageNum=" + pageNum + "&searchType=" + searchType + "&keyword=" + keyword, 
			dataType: "json"
		})
		.done(function(pdList) { // 요청 성공 시
// 			$("#listForm > table").append(boardList);
			
			// JSONArray 객체를 통해 배열 형태로 전달받은 JSON 데이터를
			// 반복문을 통해 하나씩 접근하여 객체 꺼내기
			for(let product of pdList) {
				// 테이블에 표시할 JSON 데이터 출력문 생성
				// => 출력할 데이터는 board.xxx 형식으로 접근
				let result = "<tr height='100'>"
							+ "<td>" + product.PRODUCT_CD + "</td>"
							+ "<td id='subject'>" 
								+ "<a href='PdDetail?PRODUCT_CD=" + product.PRODUCT_CD + "'>"
								+ product.PRODUCT_NAME + "</a>" + "</td>"
							+ "<td>" + product.PRODUCT_GROUP_BOTTOM_CD + "</td>"
							+ "<td>" + product.SIZE_DES + "</td>"
							+ "<td>" + product.UNIT + "</td>"
							+ "<td>" + product.BARCODE + "</td>"
							+ "<td>" + product.IN_UNIT_PRICE + "</td>"
							+ "<td>" + product.OUT_UNIT_PRICE + "</td>"
							+ "<td>" + product.PRODUCT_TYPE_CD + "</td>"
							+ "<td>" + product.BUSINESS_NO + "</td>"
							+ "<td>" + product.PRODUCT_IMAGE + "</td>"
							+ "<td>" + product.REMARKS + "</td>"
							+ "</tr>";
				
				// 지정된 위치(table 태그 내부)에 JSON 객체 출력문 추가
				$("#listForm > table").append(result);
			}
		})
		.fail(function() {
			$("#listForm > table").append("<h3>요청 실패!</h3>");
		});
	}
</script>
</head>
<body>
	<jsp:include page="../inc/top.jsp"></jsp:include>
	<div style="display: flex;">
		<div style="width: 300px; margin-top: 0px; margin-right: 0px; border-right:solid 1px; border-color: #BDBDBD;">
			<jsp:include page="../inc/left.jsp"></jsp:include>
		</div>
		<!-- 게시판 리스트 -->
		<div align="left" style="margin-left: 100px; margin-top: 30px;">
		<section id="listForm">
		<h1><b style="border-bottom: 10px solid">품목 조회</b></h1>
		<section id="buttonArea">
			<form action="PdList">
				<!-- 검색 타입 추가 -->
				<select name="searchType" id="searchType">
					<option value="PRODUCT_NAME" <c:if test="${param.searchType eq 'PRODUCT_NAME'}">selected</c:if>>품목명</option>
					<option value="PRODUCT_GROUP_BOTTOM_CD" <c:if test="${param.searchType eq 'PRODUCT_GROUP_BOTTOM_CD'}">selected</c:if>>품목그룹</option>
				</select>
				<input type="text" name="keyword" id="keyword" value="${param.keyword }">
				<input type="submit" value="검색">
				&nbsp;&nbsp;
				<input type="button" value="품목등록" onclick="location.href='PdRegist'" />
			</form>
		</section>
		<table border="1">
			<tr>
				<td>품목코드</td>
				<td>품목명</td>
				<td>품목그룹(소)</td>
				<td>규격</td>
				<td>단위</td>
				<td>바코드</td>
				<td>입고단가</td>
				<td>출고단가</td>
				<td>품목타입코드</td>
				<td>구매거래처코드</td>
				<td>대표이미지</td>
				<td>적요</td>
			</tr>
		</table>
	</section>
		</div>
	</div>
</body>
</html>