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
	width: 600px;
	font-size: 20px;
	font-weight: bold;
}

#td_left {
	text-align: left;
	width: 200px;
	
}
</style>
</head>
<body>
	<jsp:include page="../inc/top.jsp"></jsp:include>
	<div style="display: flex;">
		<div style="width: 300px; margin-top: 0px; margin-right: 0px; border-right:solid 1px; border-color: #BDBDBD;">
			<jsp:include page="../inc/hr_left.jsp"></jsp:include>
		</div>
		<!-- 여기서부터 본문-->
		<div align="left" style="margin-left: 100px; margin-top: 30px;">
		<h1><b style="border-bottom: 10px solid">품목 등록</b></h1>
		<form action="ItemRegistPro" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td id="td_left"><label for="ITEM_CD">품목코드</label></td>
					<td>
						<input type="text" style="height: 30px; width: 370px;" name="ITEM_CD" required="required">
						<input type="submit" value="중복확인" style="height: 30px; width: 70px;">
					</td>
					
				</tr>
				<tr>
					<td id="td_left"><label for="ITEM_NAME">품목명</label></td>
					<td><input type="text" style="height: 30px; width: 450px;" name="ITEM_NAME" required="required"></td>
				</tr>
				<tr>
					<td id="td_left"><label for="ITEM_GROUP">품목그룹</label><br>
						<button style="height: 30px; width: 80px;">신규등록</button>
					</td>
					<td>
						<input type="text" name="ITEM_GROUP" style="height: 30px; width: 370px;" required="required">
						<input type="submit" value="검색" style="height: 30px; width: 70px;">
					</td>
				</tr>
				<tr>
					<td id="td_left"><label for="ITEM_STANDARD">규격</label></td>
					<td><input type="text" style="height: 30px; width: 450px;" name="ITEM_STANDARD"></td>
				</tr>
				<tr>
					<td id="td_left"><label for="ITEM_BARCODE">바코드</label></td>
					<td><input type="text" style="height: 30px; width: 450px;" name="ITEM_BARCODE" required="required"></td>
				</tr>
				<tr>
					<td id="td_left"><label for="ITEM_IN_COST">입고단가</label></td>
					<td><input type="text" style="height: 30px; width: 450px;" name="ITEM_IN_COST" required="required"></td>
				</tr>
				<tr>
					<td id="td_left"><label for="ITEM_OUT_COST">출고단가</label></td>
					<td><input type="text" style="height: 30px; width: 450px;" name="ITEM_OUT_COST" required="required"></td>
				</tr>
				<tr>
					<td id="td_left"><label for="ITEM_GUBUN">품목구분</label></td>
					<td>
						<select style="height: 30px; width: 460px;" name="ITEM_GUBUN">
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
					<td id="td_left"><label for="ITEM_IMAGE">대표이미지</label></td>
					<td>
						<input type="file" name="file" />
					</td>
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