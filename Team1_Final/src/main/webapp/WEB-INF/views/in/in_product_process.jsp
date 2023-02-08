<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입고 예정 등록</title>
<link rel="shortcut icon" href="#">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
<style>
*{
	color:#513e30;
	font-family: 'Gowun Dodum', sans-serif;
}
</style>

<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">
	var i = 0;

	
	$(function() {
		for(var i = 0; i < $(opener.document).find(".check").length; i++){
			
			if($(opener.document).find(".check").eq(i).prop("checked")){
				
				let result = "<tr>"
					+ "<td>" + opener.proList[i].IN_PD_SCHEDULE_CD + "</td>"
					+ "<td>" + opener.proList[i].PRODUCT_NAME + "</td>"
					+ "<td>" + opener.proList[i].IN_SCHEDULE_QTY + "</td>"
					+ "<td>" + (opener.proList[i].IN_SCHEDULE_QTY - opener.proList[i].IN_QTY) + "</td>"
					+ "<td><input type='text' class='sch_qty'></td>"
					+ "<td><input type='text' class='sch_num'></td>"
					+ "<td><input type='text' class='stock'><button>검색</button></td>"
					+ "</tr>";
					
				$("#in_process_table").append(result);

			}
			
		}

		
		// 오늘날짜 자동으로 기입
		var today = new Date().toISOString().substring(0,10).replace(/-/g,'');
		$("#today").val(today);

		
		
		//====================== 여기서부터 <tr>생성 & 합계 계산 =====================================
		let sum = 0;
		
		$.total = function() {
			var numberClass = $(".in_schedule_qty").length;
			let sum = 0;
			for(var i= 0; i < numberClass; i++){
				sum += Number($("input[type=number][name=IN_SCHEDULE_QTY]").eq(i).val());
			}
			$("input[type=number][name=TOTAL_QTY]").val(sum);
		};
		
		$("input[type=number][name=IN_SCHEDULE_QTY]").on("change", function() {
			$.total();
		});
		
		
	});
	
	

</script>
<link href="${pageContext.request.contextPath }/resources/css/in.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div style="width:1200px;">
		<div class="title_regi">입고예정등록</div>
		<form action="StockMovePro" method="post" style="width:900px;">
			<table id="in_process_table" style="table-layout:fixed">
				<tr>
					<th width="115">입고예정번호</th>
					<th width="200">품목명[규격]</th>
					<th width="100">입고예정수량</th>
					<th width="100">미입고수량</th>
					<th width="100">입고수량</th>
					<th width="155">재고번호</th>
					<th width="230">위치</th>
				</tr>
				<tbody id="optionArea"></tbody>
				<tr>
					<th colspan="2" style="font-size:13px;">합계</th>
					<th><input type="text" id="total" name="TOTAL_QTY" readonly="readonly"></th>
					<th><input type="text" id="total" name="TOTAL_QTY" readonly="readonly"></th>
					<th><input type="text" id="total" name="TOTAL_QTY" readonly="readonly"></th>
					<th></th>
					<th></th>
				</tr>	
			</table>
		</form>
	</div>

</body>
</html>