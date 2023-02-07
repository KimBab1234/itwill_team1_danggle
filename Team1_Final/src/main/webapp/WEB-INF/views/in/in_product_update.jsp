<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입고 예정 수정</title>
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
	var proList;
	var i = 0;
	var productCd = opener.proList[opener.selectIdx].IN_PD_SCHEDULE_CD;
	var productName = opener.proList[opener.selectIdx].PRODUCT_NAME;
	var date;
	var pd_date;
	
	// json으로 받아온 date 다시 date 형식으로 만들어주기
	function changeDate(on_date){
		var d = new Date(on_date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

	    if (month.length < 2) 
	        month = '0' + month;
	    if (day.length < 2) 
	        day = '0' + day;
		
	    date = year +"-" + month + "-" + day;
    	return date;
	}
	
	function changePdDate(on_date){
		var d = new Date(on_date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

	    if (month.length < 2) 
	        month = '0' + month;
	    if (day.length < 2) 
	        day = '0' + day;
		
	    pd_date = year +"-" + month + "-" + day;
    	return pd_date;
	}
	
	// 품목 검색
	function funct(){
		window.open('SearchProduct', 'searchPro', 'width=500, height=500, left=1000, top=400');
	}
	
	$(function() {
		// 에이젝스로 데이터 가져오기
		$.ajax({
			
	        type: "GET",
	        url: "UpdateProductInfo",
	        data: {
	        	product_cd : productCd,
				product_name : productName
	        },
	        dataType: 'json',
	        success: function(result) {

				console.log(result);       
				var type_cd = result.in_TYPE_CD;
				$("#today").val(result.in_PD_SCHEDULE_CD);
				
				if(type_cd == "1"){
					$("#type_1").prop('checked',true);
				}else {
					$("#type_2").prop('checked',false);
				}

				changeDate(result.in_DATE)
				changePdDate(result.in_PD_DATE)
				
				
				$("#emp_code").val(result.emp_NUM);
				$("#emp_name").val(result.emp_NAME);
				$("#business_no").val(result.business_NO);
				$("#business_name").val(result.cust_NAME);
				$("#in_date").val(date);
				$("#remarks").val(result.remarks);
				$("#pro_cd").val(result.product_CD);
				$("#pro_name").val(result.product_NAME);
				$("#pro_size").val(result.size_DES);
				$("#pro_qty").val(result.in_SCHEDULE_QTY);
				$("#pro_date").val(pd_date);
				$("#pro_remarks").val(result.in_PD_REMARKS);
				
	        },
	        error: function(a, b, c) {
	            console.log(a, b, c);
	        }
					
	    });

		// 발주서, 구매 중 체크박스 하나만 선택하게 하기
		$("input[type=checkbox][name=in_type_cd]").click(function(){
			if($(this).prop('checked')){
				$("input[type=checkbox][name=in_type_cd]").prop('checked',false);
				$(this).prop('checked',true);
			}
		});
		
		
		
		$('#recoBtn2').click(function() {
					
			for(var i = 0; i < $(".in_date").length; i++){
				if($(".in_date").eq(i).val() == ""){
					$(".in_date").eq(i).val("1900-01-01");
				}
			}
			
		});
		
		
		
		
	});
	
	

</script>
<link href="${pageContext.request.contextPath }/resources/css/in.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div style="width:1200px;">
		<div class="title_regi">입고예정수정</div>
		<form onsubmit="return false" id="inSc_regi" name="proRegi" style="width:900px;">
			<table class="regi_table">
				<tr>
					<th>일자</th>
					<td><input type="text" name="TODAY" id="today" readonly="readonly"></td>
					<th>유형</th>
					<td>
						<div>
							<input type="checkbox" value="1" class="recoCheck" name="IN_TYPE_CD" id="type_1"> 발주서
							<input type="checkbox" value="2" class="recoCheck" name="IN_TYPE_CD" id="type_2"> 구매
						</div>
					</td>
				</tr>
				<tr>
					<th>담당자</th>
					<td>
						<input type="text" class="emp_code" id="emp_code" name="EMP_NUM" placeholder="사원번호">
						<input type="text" class="emp_name" id="emp_name" placeholder="사원명">
						<button id="Listbtn" type="button" onclick="window.open('SearchEMP', 'searchEmployee', 'width=500, height=500, left=750, top=400')">검색</button>
					</td>
					<th>거래처</th>
					<td>
						<input type="text" class="emp_code" id="business_no" name="BUSINESS_NO" placeholder="거래처 코드">
						<input type="text" class="emp_name" id="business_name" placeholder="거래처명">
						<button id="Listbtn" type="button" onclick="window.open('searchBusiness_no', 'SearchBUS', 'width=500, height=500, left=1000, top=400')">검색</button>
					</td>
				</tr>
				<tr>
					<th>납기일자</th>
					<td><input type="date" id="in_date" name="IN_DATE"></td>
					<th>비고</th>
					<td><input type="text" class="note" id="remarks" name="REMARKS"></td>
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
				<tr class="indexCh" ondblclick="funct()">
					<td><input type="hidden" id="index">
					<input type="text" class="product_cd" name="PRODUCT_CD" id="pro_cd"></td>
					<td><input type="text" class="product_name" name="PRODUCT_NAME" id="pro_name"></td>
					<td><input type="text" class="size_des" name="SIZE_DES" id="pro_size"></td>
					<td><input type="number" class="in_schedule_qty" name="IN_SCHEDULE_QTY" id="pro_qty"></td>
					<td><input type="date" class="in_date" name="IN_PD_DATE" id="pro_date"></td>
					<td><input type="text" class="remarks" name="IN_PD_REMARKS" id="pro_remarks"></td>
				</tr>
				<tr class="indexCh" ondblclick="funct()"> 
					<td><input type="text" class="product_cd" name="PRODUCT_CD"></td>
					<td><input type="text" class="product_name" name="PRODUCT_NAME"></td>
					<td><input type="text" class="size_des" name="SIZE_DES"></td>
					<td><input type="number" class="in_schedule_qty" name="IN_SCHEDULE_QTY"></td>
					<td><input type="date" class="in_date" name="IN_PD_DATE"></td>
					<td><input type="text" class="remarks" name="IN_PD_REMARKS"></td>
				</tr>
				<tr class="indexCh" ondblclick="funct()">
					<td><input type="text" class="product_cd" name="PRODUCT_CD"></td>
					<td><input type="text" class="product_name" name="PRODUCT_NAME"></td>
					<td><input type="text" class="size_des" name="SIZE_DES"></td>
					<td><input type="number" class="in_schedule_qty" name="IN_SCHEDULE_QTY"></td>
					<td><input type="date" class="in_date" name="IN_PD_DATE"></td>
					<td><input type="text" class="remarks" name="IN_PD_REMARKS"></td>
				</tr>
				<tbody id="optionArea"></tbody>
				<tr>
					<th></th>
					<th></th>
					<th></th>
					<th><input type="number" id="total" name="TOTAL_QTY" readonly="readonly"></th>
					<th></th>
					<th></th>
				</tr>
			</table>
			<div>
				<input type="button" value="수정" id="recoBtn4">
			</div>
		</form>
	</div>

</body>
</html>