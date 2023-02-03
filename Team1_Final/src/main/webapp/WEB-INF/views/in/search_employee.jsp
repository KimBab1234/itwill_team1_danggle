<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 검색</title>
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">
	
	$(function() {
		
		$("#Listbtn2").on("click", function() {
			
			$.ajax({
				url: "SearchEmp",
				type: "GET",
				data: {
					keyword : $("#keyword").val()
				},
				dataType: "json"
			})
			.done(function(EmpList) {
				
				
// 				for(let book of bookList) {
					
// 					let result = "<tr>"
// 								+ "<td>" + book.product_idx + "</td>"
// 								+ "<td>" + book.name + "</td>"
// 								+ "<td>" + book.quantity + "</td>"
// 							+ "<td>" + "<input type='button' id='Listbtn' class='recobtn' value='삭제'>"
// 							 + "<input type='hidden' class='recoHidden' value='"+book.product_idx +"'>" + "</td>"
// 								+ "</tr>";
					
// 					// 지정된 위치(table 태그 내부)에 JSON 객체 출력문 추가
// 					$(".recoTable").append(result);
// 				}
				
				
			})
			.fail(function() {
				$(".recoTable").append("<h3>요청 실패!</h3>");
			});
		});
	});
	
</script>
<link href="${pageContext.request.contextPath }/resources/css/in.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div style="width:1200px;">
		<div class="title_regi">사원 검색</div>
		<div class="box">
			<div class="in_box">
				<input type="text" class="note" placeholder="사원명을 입력하세요" name="keyword" id="keyword">
				<input type="button" id="Listbtn2" value="검색">
			</div>
			<table>
			</table>
		</div>
	</div>

</body>
</html>