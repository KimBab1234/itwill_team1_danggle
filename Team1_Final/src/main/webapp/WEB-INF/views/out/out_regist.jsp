<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>출고 예정 입력</title>
<!------------------------------- 아이콘 ----------------------------------->
<meta name='viewport' content='width=device-width, initial-scale=1'>
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!-------------------------------------------------------------------------->

<!----------------------------- 페이지 CSS --------------------------------->
<link href="${pageContext.request.contextPath }/resources/css/out.css" rel="stylesheet" type="text/css" />
<!-------------------------------------------------------------------------->

<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">

	// ----------------------------------- 검색창 -------------------------------------
	function searchEmp(){
		window.open('EmpSearch', 'searchEmp', 'width=500, height=500, left=600, top=400')
	}
	
	function searchAcc(){
		window.open('AccSearch', 'searchPopup', 'width=500, height=500, left=600, top=400')
	}
	
	function searchPd(){
		window.open('PdSearch', 'searchPro', 'width=500, height=500, left=1000, top=400');
	}
	// --------------------------------------------------------------------------------
	
	
	// ------------------------------- 출고 예정 기능 ---------------------------------
	$(function() {
		// 오늘날짜 자동으로 기입
		var today = new Date().toISOString().substring(0,10).replace(/-/g,'');
		$("#out_today").val(today);
		
		// 품목 수량 합계 계산
		let sum = 0;
		$.total = function() {
			var numberClass = $(".out_schedule_qty").length;
			let sum = 0;
			for(var i= 0; i < numberClass; i++){
				sum += Number($("input[type=number][name=OUT_SCHEDULE_QTY]").eq(i).val());
			}
			$("input[type=number][name=TOTAL_QTY]").val(sum);
		};
		
		$("input[type=number][name=OUT_SCHEDULE_QTY]").on("change", function() {
			$.total();
		});
		
		// 품목 추가
		$("#recoBtn").on("click", function() {
			$("#optionArea").append(
					'<tr class="idx">' 
					+ '<td>'
					+ 	'<input type="text" class="product_cd" name="PRODUCT_CD" ondblclick="searchPd()">'
					+ 	'<a id="searchBtn" onclick="searchPd()"><i style="font-size:10px" class="fa">&#xf002;</i></a>'
				    + '</td>'
					+ '<td>'
					+	'<input type="text" class="product_name" name="PRODUCT_NAME" ondblclick="searchPd()">'
					+	'<a id="searchBtn" onclick="searchPd()"><i style="font-size:10px" class="fa">&#xf002;</i></a>'
					+ '</td>'
					+ '<td><input type="text" class="size_des" name="SIZE_DES" readonly="readonly"></td>'
					+ '<td><input type="number" class="out_schedule_qty" name="OUT_SCHEDULE_QTY"></td>'
					+ '<td><input type="date" class="pd_out_date" name="PD_OUT_DATE"></td>'
					+ '<td><input type="text" class="pd_remarks" name="PD_REMARKS" readonly="readonly"></td>'
				    + '</tr>'
			);

			// 추가된 품목 수량 합계 계산
			var inClass = $(".out_schedule_qty").length;

			$("input[type=number][name=OUT_SCHEDULE_QTY]").on("change", function() {
				$.total();
			});
			
		});
		
	});
	// --------------------------------------------------------------------------------
	
	
	// -------------------------------- 출고예정 등록 ---------------------------------
	function registFunc(){
		for(var i = 0; i < $(".pd_out_date").length; i++){
			if($(".pd_out_date").eq(i).val() == ""){
				$(".pd_out_date").eq(i).val("1900-01-01");
			}
		}
		$.ajax({
			url: 'OutSchRegistPro',
			type: 'POST',
			data: $("#proRegi").serialize(),
			dataType : 'json',
			success : function(response) {
				if (result != "0") {
	                window.close();
	                opener.location.reload();
	            } else {
	            	alert("출고 예정 등록 실패!");
	            	window.close();	                
	            }

			}
			
		});
		
	}
	// --------------------------------------------------------------------------------
	
</script>

</head>
<body>
	<div style="width:900px;">
		<div class="title_regi">출고예정 입력</div>
		<form action="javascript:registFunc()" method="post" id="proRegi" name="proRegi" style="width:600px;">
			<table class="regi_table">
				<tr>
					<th>일자</th>
					<td><input type="text" id="out_today" name="OUT_TODAY" readonly="readonly"></td>
					<th>유형</th>
					<td>
						<div>
							<input type="radio" value="1" class="recoCheck" name="OUT_TYPE_NAME" checked="checked"> 발주서
							<input type="radio" value="2" class="recoCheck" name="OUT_TYPE_NAME"> 구매
						</div>
					</td>
				</tr>
				<tr>
					<th>담당자</th>
					<td>
						<input type="text" class="code" id="emp_code" name="EMP_NUM" placeholder="사원번호" ondblclick="searchEmp()">
						<input type="text" class="name" id="emp_name" placeholder="사원명" ondblclick="searchEmp()">
						<button id="Listbtn" type="button" onclick="searchEmp()">검색</button>
					</td>
					<th>거래처</th>
					<td>
						<input type="text" class="code" id="acc_code" name="BUSINESS_NO" placeholder="거래처 코드" ondblclick="searchAcc()">
						<input type="text" class="name" id="acc_name" name="BUSINESS_NAME" placeholder="거래처명" ondblclick="searchAcc()">
						<button id="Listbtn" type="button" onclick="searchAcc()">검색</button>
					</td>
				</tr>
				<tr>
					<th>비고</th>
					<td><input type="text" class="note" name="REMARKS" id="REMARKS"></td>
					<th>납기일자</th>
					<td><input type="date" name="OUT_DATE" id="OUT_DATE"></td>
				</tr>
			</table>
			<br>
			<table class="out_table">
				<tr>
					<th width="100">품목코드</th>
					<th width="250">품목명</th>
					<th width="100">규격</th>
					<th width="100">수량</th>
					<th width="150">납기일자</th>
					<th width="200">적요</th>
				</tr>
				<tr class="idx">
					<td>
						<input type="text" class="product_cd" name="PRODUCT_CD" ondblclick="searchPd()">
						<a id="searchBtn" onclick="searchPd()"><i style="font-size:10px" class="fa">&#xf002;</i></a>
					</td>
					<td>
						<input type="text" class="product_name" name="PRODUCT_NAME" ondblclick="searchPd()">
						<a id="searchBtn" onclick="searchPd()"><i style="font-size:10px" class="fa">&#xf002;</i></a>
					</td>
					<td><input type="text" class="size_des" name="SIZE_DES" readonly="readonly"></td>
					<td><input type="number" class="out_schedule_qty" name="OUT_SCHEDULE_QTY"></td>
					<td><input type="date" class="pd_out_date" name="PD_OUT_DATE"></td>
					<td><input type="text" class="pd_remarks" name="PD_REMARKS" readonly="readonly"></td>
				</tr>
				<tbody id="optionArea"></tbody>
				<tr>
					<th></th>
					<th></th>
					<th></th>
					<th><input type="number" id="total" name="TOTAL_QTY"></th>
					<th></th>
					<th></th>
				</tr>
			</table>
			<input type="button" value="품목 추가" id="recoBtn">
			<input type="submit" value="출고예정 입력" id="submitBtn">
		</form>
	</div>

</body>
</html>