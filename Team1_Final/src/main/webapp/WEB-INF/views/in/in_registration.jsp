<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입고 예정 입력</title>
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">

	$(function() {
		
		let sum = 0;
		
		$.total = function() {
			
			var numberClass = $(".in_schedule_qty").length;
			let sum = 0;
			for(var i= 0; i < numberClass; i++){
				sum += Number($("input[type=number][name=in_schedule_qty]").eq(i).val());
			}
			$("input[type=number][name=total]").val(sum);
		};
		
		$("input[type=number][name=in_schedule_qty]").on("change", function() {
			
			$.total();
		});
		
		
		$("#recoBtn").on("click", function() {
			
			$("#optionArea").append(
					'<tr>' 
					+'<td><input type="text" class="product_cd" name="product_cd"></td>'
					+ '<td><input type="text" class="product_name" name="product_name"></td>'
					+ '<td><input type="text" class="size_des" name="size_des"></td>'
					+ '<td><input type="number" class="in_schedule_qty" name="in_schedule_qty"></td>'
					+ '<td><input type="date" class="in_date" name="in_date"></td>'
					+ '<td><input type="text" class="remarks" name="remarks"></td>'
				    + '</tr>'
				
			);
			var inClass = $(".in_schedule_qty").length;

			$("input[type=number][name=in_schedule_qty]").on("change", function() {
				
				$.total();
				
			});
			
			
		});
		
	});


</script>
<link href="${pageContext.request.contextPath }/resources/css/in.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div style="width:1200px;">
		<div class="title_regi">입고예정입력</div>
		<form action="IncomingRegiPro" method="post" name="proRegi" style="width:900px;">
			<table class="regi_table">
				<tr>
					<th>일자</th>
					<td><input type="date" name="in_date"></td>
					<th>유형</th>
					<td>
						<div>
							<input type="checkbox" value="1" class="recoCheck"> 발주서
							<input type="checkbox" value="2" class="recoCheck"> 구매
						</div>
					</td>
				</tr>
				<tr>
					<th>담당자</th>
					<td>
						<input type="text" class="emp_code" placeholder="사원번호">
						<input type="text" class="emp_name" placeholder="사원명">
						<button id="Listbtn">검색</button>
					</td>
					<th>거래처</th>
					<td>
						<input type="text" class="emp_code" placeholder="거래처 코드">
						<input type="text" class="emp_name" placeholder="거래처명">
						<button id="Listbtn">검색</button>
					</td>
				</tr>
				<tr>
					<th>비고</th>
					<td><input type="text" class="note"></td>
					<th>납기일자</th>
					<td><input type="date" name="in_date"></td>
				</tr>
			</table>
			<br>
			<table class="in_table">
				<tr>
					<th width="100">품목코드</th>
					<th width="250">품목명</th>
					<th width="100">규격</th>
					<th width="100">수량</th>
					<th width="150">납기일자</th>
					<th width="200">적요</th>
				</tr>
				<tr>
					<td><input type="text" class="product_cd" name="product_cd"></td>
					<td><input type="text" class="product_name" name="product_name"></td>
					<td><input type="text" class="size_des" name="size_des"></td>
					<td><input type="number" class="in_schedule_qty" name="in_schedule_qty"></td>
					<td><input type="date" class="in_date" name="in_date"></td>
					<td><input type="text" class="remarks" name="remarks"></td>
				</tr>
				<tr>
					<td><input type="text" class="product_cd" name="product_cd"></td>
					<td><input type="text" class="product_name" name="product_name"></td>
					<td><input type="text" class="size_des" name="size_des"></td>
					<td><input type="number" class="in_schedule_qty" name="in_schedule_qty"></td>
					<td><input type="date" class="in_date" name="in_date"></td>
					<td><input type="text" class="remarks" name="remarks"></td>
				</tr>
				<tr>
					<td><input type="text" class="product_cd" name="product_cd"></td>
					<td><input type="text" class="product_name" name="product_name"></td>
					<td><input type="text" class="size_des" name="size_des"></td>
					<td><input type="number" class="in_schedule_qty" name="in_schedule_qty"></td>
					<td><input type="date" class="in_date" name="in_date"></td>
					<td><input type="text" class="remarks" name="remarks"></td>
				</tr>
				<tbody id="optionArea"></tbody>
				<tr>
					<th></th>
					<th></th>
					<th></th>
					<th><input type="number" id="total" name="total"></th>
					<th></th>
					<th></th>
				</tr>
			</table>
			<input type="button" value="품목 추가" id="recoBtn">
		</form>
	</div>

</body>
</html>