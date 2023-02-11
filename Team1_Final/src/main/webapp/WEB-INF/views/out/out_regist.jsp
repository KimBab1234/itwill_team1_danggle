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
	
	//---------------------------- 권한 판단 -----------------------------
	var loginEmp = '${sessionScope.empNo}';
	var priv = '${sessionScope.priv}';
	if(loginEmp=='') {
		alert("로그인 후 이용하세요.");
		window.close();
		history.back();
		opener.location.href  = './Login';
	} else if(priv.charAt(4) !='1') {
		alert("권한이 없습니다.");
		window.close();
	}
	// --------------------------------------------------------------------


	// ----------------------------------- 검색창 -------------------------------------
	function searchEmp(){
		window.open('EmpSearch', 'searchEmp', 'width=500, height=500, left=600, top=400')
	}
	
	function searchAcc(){
		window.open('AccSearch', 'searchPopup', 'width=500, height=500, left=600, top=400')
	}
	
	function searchPd(){
		window.open('PdSearch', 'searchPro', 'width=500, height=500, left=600, top=400');
	}
	
	function searchStock(product_cd){
		pdcd = product_cd;
		window.open('StockSearch', 'StockSearch', 'width=1000, height=600, left=600, top=400');
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
				sum += Number($("input[type=number][name=OUT_SCHEDULE_QTY_Arr]").eq(i).val());
			}
			$("input[type=number][name=TOTAL_QTY]").val(sum);
		};
		
		$("input[type=number][name=OUT_SCHEDULE_QTY_Arr]").on("change", function() {
			$.total();
		});
		
		// 품목 추가 - 추가 행 개수 제한
		$("#recoBtn").on("click", function() {
			var trCount = $(".out_table tr").length;
			alert(trCount);
			if(trCount < 7){
				$("#optionArea").append(
						'<tr class="idx">' 
						+ '<td>'
						+ 	'<input type="text" class="product_cd" name="PRODUCT_CD_Arr" ondblclick="searchPd()">'
						+ 	'<a id="searchBtn" onclick="searchPd()"><i style="font-size:10px" class="fa">&#xf002;</i></a>'
					    + '</td>'
						+ '<td>'
						+	'<input type="text" class="product_name" name="PRODUCT_NAME_Arr" ondblclick="searchPd()">'
						+	'<a id="searchBtn" onclick="searchPd()"><i style="font-size:10px" class="fa">&#xf002;</i></a>'
						+ '</td>'
						+ '<td><input type="text" class="size_des" name="SIZE_DES_Arr" readonly="readonly"></td>'
						+ '<td><input type="number" class="out_schedule_qty" name="OUT_SCHEDULE_QTY_Arr"></td>'
						+ '<td><input type="date" class="pd_out_date" name="PD_OUT_DATE_Arr"></td>'
						+ '<td><input type="text" class="pd_remarks" name="PD_REMARKS_Arr" readonly="readonly"></td>'
						+ '<td><input type="text" class="stock_cd" name="STOCK_CD_Arr" readonly="readonly" value="재고번호"></td>'
						+ '<td><input type="text" id="Listbtn" value="취소" name="dtl_del" readonly="readonly"></td>'
					    + '</tr>'
				);
			} else {
				alert("최대 5개까지만 등록 가능합니다!")
				return false;
			}

			// 추가된 품목 수량 합계 계산
			var inClass = $(".out_schedule_qty").length;

			$("input[type=number][name=OUT_SCHEDULE_QTY_Arr]").on("change", function() {
				$.total();
			});
			
		});
		
		
		// 품목입력 행 삭제
		$(document).on('click', "[name='dtl_del']", function(){
			var tr = $(this).parent().parent();
			tr.remove();
		});
		
		
		// 재고 선택
		$(document).on("click", "input[name=STOCK_CD_Arr]", function() {
			
			if(!$(".product_cd").val() == "" && !$(".product_name").val() == "") {
				let index = $(this).closest("tr").index();
				let product_cd = $("input[name=PRODUCT_CD_Arr]").eq((index-1)).val();
				searchStock(product_cd);
			} else {
				alert("품목정보를 먼저 선택해주세요!");
			}
			
		});
		
		// 입력 Check
		let empCheck = false;
		let accCheck = false;
		let outDateCheck = false;
		let pdCheck = false;
		let qtyCheck = false;
		let outPdDateCheck = false;
// 		let stockCheck = false;
		
	
		$("#proRegi").submit(function() {
			
			if(!$("#emp_code").val() == "" && !$("#emp_name").val() == "") {
				empCheck = true;
			}
			if(!$("#acc_code").val() == "" && !$("#acc_name").val() == "") {
				accCheck = true;
			}
			if(!$("#OUT_DATE").val() == "") {
				outDateCheck = true;
			}
			if(!$(".product_cd").val() == "" && !$(".product_name").val() == "") {
				pdCheck = true;
			}
			if(!$(".out_schedule_qty").val() == "0" || !$(".out_schedule_qty").val() == ""){
				qtyCheck = true;
			}
			if(!$(".pd_out_date").val() == "" || !$(".pd_out_date").val() == "1900-01-01"){
				outPdDateCheck = true;
			}
// 			if(!$(".stock_cd").val() == "재고번호"){
// 				stockCheck = true;
// 			}
			
			
			if(!empCheck){
				alert("담당자 정보를 검색해주세요!");
				return false;
			} else if(!accCheck) {
				alert("거래처 정보를 검색해주세요!");
				return false;
			} else if(!outDateCheck) {
				alert("날기일자를 선택해주세요!");
				return false;
			} else if(!pdCheck) {
				alert("품목 정보를 검색해주세요!");
				return false;
			} else if(!outPdDateCheck){
				alert("개별 품목 납기일자를 입력해주세요!");
				return false;
// 			} else if(!stockCheck){
// 				alert("출고할 품목의 재고를 선택해주세요!");
// 				return false;
			} else if(!qtyCheck){
				alert("품목 수량을 입력해주세요!");
				return false;
			}
			
		});
		
	});
	// --------------------------------------------------------------------------------
	
	
	// -------------------------------- 출고예정 등록 ---------------------------------
	function registFunc(){
		$("#submitBtn").click(function() {
			for(var i = 0; i < $(".pd_out_date").length; i++){
				if($(".pd_out_date").eq(i).val() == ""){
					$(".pd_out_date").eq(i).val("1900-01-01");
				}
			}
			
			for(var i = 0; i < $(".stock_cd").length; i++){
				if($(".stock_cd").eq(i).val() == "재고번호"){
					Number($(".stock_cd").eq(i).val());
				}
			}
			
			$.ajax({
				url: 'OutSchRegistPro',
				type: 'POST',
				data: $("#proRegi").serialize(),
				dataType : 'json',
				success : function(response) {
					if (response != "0") {
		                window.close();
		                opener.location.reload();
		            } else {
		            	alert("출고 예정 등록 실패!");
		            	window.close();
		            }
				}
			});
			
		});
		
	}
	// --------------------------------------------------------------------------------

	</script>

</head>
<body>
	<div>
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
					<th>납기일자</th>
					<td><input type="date" name="OUT_DATE" id="OUT_DATE"></td>
					<th>비고</th>
					<td><input type="text" class="note" name="REMARKS" id="REMARKS"></td>
				</tr>
			</table>
			<br>
			<table class="out_table">
				<tbody id="optionArea">
					<tr>
						<th width="100">품목코드</th>
						<th width="250">품목명</th>
						<th width="100">규격</th>
						<th width="100">수량</th>
						<th width="150">납기일자</th>
						<th width="200">적요</th>
						<th width="200">출고처리</th>
						<th width="50">취소</th>
					</tr>
					<tr class="idx">
	<!-- 				<tr> -->
						<td>
							<input type="text" class="product_cd" name="PRODUCT_CD_Arr" ondblclick="searchPd()">
							<a id="searchBtn" onclick="searchPd()"><i style="font-size:10px" class="fa">&#xf002;</i></a>
						</td>
						<td>
							<input type="text" class="product_name" name="PRODUCT_NAME_Arr" ondblclick="searchPd()">
							<a id="searchBtn" onclick="searchPd()"><i style="font-size:10px" class="fa">&#xf002;</i></a>
						</td>
						<td><input type="text" class="size_des" name="SIZE_DES_Arr" readonly="readonly"></td>
						<td><input type="number" class="out_schedule_qty" name="OUT_SCHEDULE_QTY_Arr" oninput="this.value=this.value.replace(/[^0-9]/g, '');"></td>
						<td><input type="date" class="pd_out_date" name="PD_OUT_DATE_Arr"></td>
						<td><input type="text" class="pd_remarks" name="PD_REMARKS_Arr" readonly="readonly"></td>
						<td><input type="text" class="stock_cd" name="STOCK_CD_Arr" readonly="readonly" value="재고번호"></td>
						<td><input type="text" id="removeTr" readonly="readonly"></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<th></th>
						<th></th>
						<th></th>
						<th><input type="number" id="total" name="TOTAL_QTY"></th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
					</tr>
				</tfoot>
			</table>
			<input type="button" value="품목 추가" id="recoBtn">
<!-- 			<input type="button" value="품목 취소" id="recoBtn2"><br> -->
			<input type="submit" value="출고예정 입력" id="submitBtn">
		</form>
	</div>

</body>
</html>