<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>출고 예정 수정</title>
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
	var proList;
	var i = 0;
	var productCd = opener.proList[opener.index].PD_OUT_SCHEDULE_CD;
	var productName = opener.proList[opener.index].PRODUCT_NAME;
	var date;
	var pd_date;

// 	alert(opener.index);
// 	alert("PD_OUT_SCHEDULE_CD : " + opener.proList[opener.index].PD_OUT_SCHEDULE_CD + "\n"
// 			+ "PRODUCT_NAME : " + opener.proList[opener.index].PRODUCT_NAME);
	
	// ----------------------------------- 검색창 -------------------------------------
	function searchEmp(){
		window.open('EmpSearch', 'searchEmp', 'width=500, height=500, left=600, top=400')
	}
	
	function searchAcc(){
		window.open('AccSearch', 'searchPopup', 'width=500, height=500, left=600, top=400')
	}
	
	function searchPd(){
		window.open('UpdatePdSearch', 'searchPro', 'width=500, height=500, left=1000, top=400');
	}
	// --------------------------------------------------------------------------------
	
	
	// ---------------------------- 출고 예정 수정 기능 -------------------------------
	// 데이터 형식 변경 (String -> Date) 
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
	// --------------------------------------------------------------------------------
	
	
	// -------------------------------- 페이지 로드 전 --------------------------------
	$(function() {
		// 입력 Check
		let empCheck = false;
		let accCheck = false;
		let outDateCheck = false;
		let pdCheck = false;
		let qtyCheck = false;
		let outPdDateCheck = false;
		
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
			if(!$(".pd_out_date").val() == ""){
				outPdDateCheck = true;
			}
			
			
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
			} else if(!qtyCheck){
				alert("품목 수량을 입력해주세요!");
				return false;
			} else if(!outPdDateCheck){
				alert("개별 품목 납기일자를 입력해주세요!");
				return false;
			}
			
		});
		
		
		// 수정할 출고 예정 품목 데이터 로드
		$.ajax({
			
	        type: "POST",
	        url: "OutSchPdUpdate",
	        data: {
	        	pd_outSch_cd : productCd,
				product_name : productName
	        },
	        dataType: 'json',
	        success: function(result) {
	        	
				console.log(result);
// 				alert(result.out_TYPE_NAME);
				
				$("#out_today").val(result.pd_OUT_SCHEDULE_CD);
				
				if(result.out_TYPE_NAME == "1"){
					$("#type_1").prop('checked', true);
				} else {
					$("#type_2").prop('checked', false);
				}

				changeDate(result.out_DATE);
				
				$("#emp_code").val(result.emp_NUM);
				$("#emp_name").val(result.emp_NAME);
				$("#acc_code").val(result.business_NO);
				$("#acc_name").val(result.cust_NAME);
				$("#OUT_DATE").val(date);
				$("#REMARKS").val(result.remarks);
				$(".product_cd").val(result.product_CD);
				$(".product_name").val(result.product_NAME);
				$(".size_des").val(result.size_DES);
				$(".out_schedule_qty").val(result.out_SCHEDULE_QTY);
				$(".pd_out_date").val(result.pd_OUT_DATE);
				$(".pd_remarks").val(result.pd_REMARKS);
				
	        }
					
	    });
		
	});
	// --------------------------------------------------------------------------------
	
	
	// -------------------------------- 출고예정 수정 ---------------------------------
	function updateFunc(){
		$("#submitBtn").click(function() {
			
			for(var i = 0; i < $(".out_date").length; i++){
				if($(".out_date").eq(i).val() == ""){
					$(".out_date").eq(i).val("1900-01-01");
				}
			}
			// 입고 수정 에이젝스
		    $.ajax({
						
		        type: "POST",
		        url: "InProductUpdate",
		        data: $('#inSc_regi').serialize(),
		        success: function(result) {
		            if (result != "0") {
		                window.close();
		                opener.location.reload();
		            } else {
		            	alert("입고 예정 수정 실패!");
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
	<div style="width:900px;">
		<div class="title_regi">출고예정 수정</div>
		<form action="javascript:updateFunc()" method="post" id="proRegi" name="proRegi" style="width:600px;">
			<table class="regi_table">
				<tr>
					<th>일자</th>
					<td><input type="text" id="out_today" name="OUT_TODAY" readonly="readonly"></td>
					<th>유형</th>
					<td>
						<div>
							<input type="radio" value="1" class="recoCheck" id="type_1" name="OUT_TYPE_NAME" checked="checked"> 발주서
							<input type="radio" value="2" class="recoCheck" id="type_2" name="OUT_TYPE_NAME"> 구매
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
				</tr>
			</table>
			<input type="submit" value="품목 수정" id="submitBtn">
		</form>
	</div>

</body>
</html>