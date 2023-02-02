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
	height: 600px;
	width: 650px;
	font-size: 20px;
	font-weight: bold;
}

#td_left {
	text-align: left;
	width: 250px;
	
}
</style>
</head>
<body>
	<jsp:include page="../inc/top.jsp"></jsp:include>
	<div style="display: flex;">
		<div style="width: 300px; margin-top: 0px; margin-right: 0px;">
			<jsp:include page="../inc/pd_left.jsp"></jsp:include>
		</div>
		<!-- 여기서부터 본문-->
		<div align="left" style="margin-left: 100px; margin-top: 30px;">
		<h1><b style="border-bottom: 10px solid">품목 등록</b></h1>
		<form action="PdRegistPro" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td id="td_left"><label for="PRODUCT_NAME">품목명</label></td>
					<td><input type="text" style="height: 30px; width: 450px;" name="PRODUCT_NAME" required="required"></td>
				</tr>
				<tr>
					<td id="td_left"><label for="PRODUCT_GROUP_BOTTOM_CD">품목그룹(소)</label><br>
						<button style="height: 30px; width: 80px;">신규등록</button>
					</td>
					<td>
						<input type="text" name="PRODUCT_GROUP_BOTTOM_CD" id="PRODUCT_GROUP_BOTTOM_CD" readonly="readonly" required="required" style="height: 30px; width: 340px;">
						<button type="button" onclick="window.open('ItemSearchhForm', 'searchPopup', 'width=500, height=500, left=600, top=400')">품목그룹 선택</button>
					</td>
				</tr>
				<tr>
					<td id="td_left"><label for="SIZE_DES">규격</label></td>
					<td><input type="text" style="height: 30px; width: 450px;" name="SIZE_DES"></td>
				</tr>
				<tr>
					<td id="td_left"><label for="BARCODE">바코드</label></td>
					<td><input type="text" style="height: 30px; width: 450px;" name="BARCODE" required="required"></td>
				</tr>
				<tr>
					<td id="td_left"><label for="IN_UNIT_PRICE">입고단가</label></td>
					<td><input type="text" style="height: 30px; width: 450px;" name="IN_UNIT_PRICE" required="required"></td>
				</tr>
				<tr>
					<td id="td_left"><label for="OUT_UNIT_PRICE">출고단가</label></td>
					<td><input type="text" style="height: 30px; width: 450px;" name="OUT_UNIT_PRICE" required="required"></td>
				</tr>
				<tr>
					<td id="td_left"><label for="PRODUCT_TYPE_CD">품목타입코드</label></td>
					<td>
						<select style="height: 30px; width: 460px;" name="PRODUCT_TYPE_CD">
							<option value="">=======================  품목구분  =======================</option>
							<option value="원재료">원재료</option>
							<option value="부재료">부재료</option>
							<option value="제품">제품</option>
							<option value="반제품">반제품</option>
							<option value="상품">상품</option>
						</select>
					</td>
				</tr>
				<tr>
					<td id="td_left"><label for="BUSINESS_NO">구매거래처코드</label></td>
					<td><input type="text" style="height: 30px; width: 450px;" name="BUSINESS_NO" required="required"></td>
				</tr>
				<tr>
					<td id="td_left"><label for="PRODUCT_IMAGE">대표이미지</label></td>
					<td>
						<input type="file" name="file" />
					</td>
				</tr>
				<tr>
					<td id="td_left"><label for="REMARKS">적요</label></td>
					<td><input type="text" style="height: 30px; width: 450px;" name="REMARKS" required="required"></td>
				</tr>
			</table>
			<br>
			<hr>
			<br>
			<div id="commandCell" align="center">
				<input type="submit" value="등록" style="height: 30px; width: 100px;">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기" style="height: 30px; width: 100px;">&nbsp;&nbsp;
				<input type="button" value="취소" style="height: 30px; width: 100px;" onclick="history.back()">
			</div>
		</form>
		</div>
	</div>
</body>
</html>