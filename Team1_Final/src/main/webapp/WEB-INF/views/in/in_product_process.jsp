<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입고</title>
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
<script src="https://kit.fontawesome.com/4eef210fa3.js" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">
	var i = 0;
	var j = 0;
	var selectIndex;
	var selectIdx;
	var proList = opener.proList;
	var sum = 0;
	var total = 0;
	var total_num = 0;
	var isChecked = false;

	
	var loginEmp = '${sessionScope.empNo}';
	var priv = '${sessionScope.priv}';
	if(loginEmp=='') {
		alert("로그인 후 이용하세요.");
		window.close();
		opener.location.href  = './Login';
	} else if(priv.charAt(4) !='1') {
		alert("권한이 없습니다.");
		window.close();
	}
	
	// 재고번호 검색
	function search_num(num){ 
		selectIndex = num;
		window.open('SearchStockCd', 'searchStock', 'width=650, height=500, left=1000, top=400');
	}
	
	// 창고 검색
	function search_wh(num){
		selectIdx = num;
		window.open('SearchWh', 'search_wh', 'width=500, height=500, left=1000, top=400');
	}
	
	
	//======= 재고번호 생성 ==========================
	function create_num(num){
		
		var arr = new Array();
		
		for(var i = 0; i < j; i++){
			arr.push($("#sch_num"+i).val());
		}

		$.ajax({
	        type: "GET",
	        url: "CreateStockCd",
	        data: {
				"arrList" : arr
			},
	        dataType: 'json',
	        success: function(result) {
	        	$("#sch_num"+num).val(result);
	        },
	        error: function(a, b, c) {
	            console.log(a, b, c);
	        }
	    });
	}
	
	//===================================================
		
	$(function() {
		
		//================ 합계, 뺄셈 구하는 함수 ====================
		$.total = function() {
			var numberClass = $(".qty_sum").length;
			total = 0;
			for(var i= 0; i < numberClass; i++){
				total += Number($("input[type=text][name=MOVE_QTY_Arr]").eq(i).val());
			}
			$("#no_qty").val(total);
		};
		
		$.total_qty = function() {
			var numberClass = $(".mi_qty").length;
			total_num = 0;
			for(var i= 0; i < numberClass; i++){
				total_num += Number($(".mi_qty").eq(i).val());
			}
			$("#total_qty").val(total_num);
		};
		
		$.subtract = function(num) {
			var a = $("#mi_qty"+num).val();
			var b = $("#qty_sum"+num).val();
			
			$("#mi_qty"+num).val(a-b);
			$.total_qty();
		};
		
		//================= 여기까지 ====================
		
			
		
		
		
		//========== 부모창에서 값 가져오기 ==============================================================
		for(var i = 0; i < $(opener.document).find(".check").length; i++){
			
			if($(opener.document).find(".check").eq(i).prop("checked")){
				
				let result = "<tr>"
					+ "<td><input type='text' name='IN_PD_SCHEDULE_CD_Arr' class='sch_cd' value='" + proList[i].IN_PD_SCHEDULE_CD + "' readonly='readonly'></td>"
					+ "<td><input type='hidden' value='" + proList[i].PRODUCT_CD + "' name='PRODUCT_CD_Arr' id='pro_cd"+j+"'><input type='text' name='PRODUCT_NAME_Arr' class='sch_name' value='" + proList[i].PRODUCT_NAME + "' readonly='readonly'></td>"
					+ "<td><input type='text' class='sch_qty' id='qty"+j+"' name='IN_SCHEDULE_QTY_Arr' value='" + proList[i].IN_SCHEDULE_QTY + "' readonly='readonly'></td>"
					+ "<td><input type='hidden'value='" + proList[i].WH_LOC_IN_AREA_CD + "' name='WH_LOC_IN_AREA_CD_Arr' id='wh_loc"+j+"'><input type='text' id='mi_qty"+j+"' class='sch_qty mi_qty' value='" + (proList[i].IN_SCHEDULE_QTY - proList[i].IN_QTY) + "'></td>"
					+ "<td><input type='text' name='MOVE_QTY_Arr' id='qty_sum"+j+"' class='sch_qty qty_sum' onchange='sum_qty("+j+")'></td>"
					+ "<td><input type='text' name='STOCK_CD_Arr' id='sch_num"+j+"' class='sch_num' onclick='create_num("+j+")' readonly='readonly'><i class='fa-solid fa-magnifying-glass' onclick='search_num("+j+")'></i></td>"
					+ "<td><input type='text' id='stock"+j+"' readonly='readonly' class='stock'><i class='fa-solid fa-magnifying-glass' onclick='search_wh("+j+")'></i></td>"
					+ "</tr>";
					
				$("#in_process_table").append(result);
				j++;
				
				sum += Number(proList[i].IN_SCHEDULE_QTY);
			}
			
			
		}
		
		$("#total_sch").val(sum);
		
		//======================================================================================================================
			
		$("#recoBtn3").on("click", function() {
			for(var i = 0; i < $(".sch_cd").length; i++){
				if($(".sch_num").eq(i).val() == ""){
					alert("재고 번호를 선택해주세요");
					isChecked = false;
					
				}
				
				if($(".stock").eq(i).val() == ""){
					alert("창고 위치를 선택해주세요");
					isChecked = false;
					
				}
				
				if($(".sch_num").eq(i).val() != "" && $(".stock").eq(i).val() != ""){
					isChecked = true;
				}
				break;
				
				
			}
			
				
			if(isChecked == true){
				in_process.action = "StockMovePro";
				in_process.submit();
			}
			
			
		});
		

	});	
		
	function sum_qty(num){
		if($("#qty_sum"+num).val() > $("#mi_qty"+num).val()){
			alert("입고지시수량은 입고예정수량보다 클 수 없습니다");
			$("#qty_sum"+num).val(0);
		}
		$.total();
		$.subtract(num);
	}
	
	
	
	

</script>
<link href="${pageContext.request.contextPath }/resources/css/in.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div style="width:1200px;">
		<div class="title_regi">입고</div>
		<form onsubmit="return false;" method="post" style="width:900px;" id="in_process">
			<table id="in_process_table" style="table-layout:fixed">
				<tr>
					<th width="115">입고예정번호</th>
					<th width="200">품목명[규격]</th>
					<th width="100">입고예정수량</th>
					<th width="100">미입고수량</th>
					<th width="100">입고지시수량</th>
					<th width="155">재고번호</th>
					<th width="230">창고 위치</th>
				</tr>
				<tbody id="optionArea"></tbody>
				<tr>
					<th colspan="2" style="font-size:13px;">합계</th>
					<th><input type="text" class="total" id="total_sch" readonly="readonly"></th>
					<th><input type="text" class="total" id="total_qty" readonly="readonly"></th>
					<th><input type="text" class="total" id="no_qty" readonly="readonly"></th>
					<th><input type="hidden" value="0" name="STOCK_CONTROL_TYPE_CD"></th>
					<th></th>
				</tr>	
			</table>
			<input type="button" value="저장" id="recoBtn3">
		</form>
	</div>

</body>
</html>