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

</head>
<body>
	<div style="width:900px;">
		<div class="title_regi">출고예정 입력</div>
		<form action="#" method="post" name="proRegi" style="width:600px;">
			<table class="regi_table">
				<tr>
					<th>일자</th>
					<td><input type="date" name="out_today"></td>
					<th>유형</th>
					<td>
						<div>
							<input type="radio" name="typeA" value="1" class="recoCheck"> 발주서
							<input type="radio" name="typeA" value="2" class="recoCheck"> 구매
						</div>
					</td>
				</tr>
				<tr>
					<th>담당자</th>
					<td>
						<input type="text" class="emp_code" placeholder="사원번호">
						<input type="text" class="emp_name" placeholder="사원명">
						<button type="button" id="Listbtn">검색</button>
					</td>
					<th>거래처</th>
					<td>
						<input type="text" class="emp_code" id="acc_code" name="acc_code" placeholder="거래처 코드" ondblclick="window.open('AccSearch', 'searchPopup', 'width=500, height=500, left=600, top=400')">
						<input type="text" class="emp_name" id="acc_name" name="acc_name" placeholder="거래처명" ondblclick="window.open('AccSearch', 'searchPopup', 'width=500, height=500, left=600, top=400')">
						<button id="Listbtn" type="button" onclick="window.open('AccSearch', 'searchPopup', 'width=500, height=500, left=600, top=400')">검색</button>
					</td>
				</tr>
				<tr>
					<th>비고</th>
					<td><input type="text" class="note" id=""></td>
					<th>납기일자</th>
					<td><input type="date" name="out_date"></td>
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
				<tr>
					<td><input type="text" id="product_cd" name="product_cd"></td>
					<td><input type="text" id="product_name" name="product_name"></td>
					<td><input type="text" id="size_des" name="size_des"></td>
					<td><input type="text" id="out_schedule_qty" name="out_schedule_qty"></td>
					<td><input type="date" id="out_date" name="out_date"></td>
					<td><input type="text" id="remarks" name="remarks"></td>
				</tr>
				<tr>
					<th></th>
					<th></th>
					<th></th>
					<th><input type="number" id="total" ></th>
					<th></th>
					<th></th>
				</tr>
			</table>
			<input type="button" value="품목 추가" id="recoBtn">
			<input type="submit" value="출고예정 입력" id="recoBtn">
		</form>
	</div>

</body>
</html>