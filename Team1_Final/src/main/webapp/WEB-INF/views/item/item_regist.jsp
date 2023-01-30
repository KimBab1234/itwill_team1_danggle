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
			<jsp:include page="../inc/left.jsp"></jsp:include>
		</div>
		<!-- 여기서부터 본문-->
		<div align="left" style="margin-left: 500px; margin-top: 30px;">
		<h1><b style="border-bottom: 10px solid">품목 등록</b></h1>
		<form action="">
			<table>
				<tr>
					<td id="td_left">품목코드</td>
					<td><input type="text" style="height: 30px; width: 450px;" name="ITEM_CD"></td>
				</tr>
				<tr>
					<td id="td_left">품목명</td>
					<td><input type="text" style="height: 30px; width: 450px;" name="ITEM_NAME"></td>
				</tr>
				<tr>
					<td id="td_left">품목그룹<br>
						<button style="height: 30px; width: 80px;">신규등록</button>
					</td>
					<td>
						<input type="text" name="keyword" style="height: 30px; width: 370px;">
						<input type="submit" value="검색" style="height: 30px; width: 70px;">
					</td>
				</tr>
				<tr>
					<td id="td_left">규격</td>
					<td><input type="text" style="height: 30px; width: 450px;" name="ITEM_STANDARD"></td>
				</tr>
				<tr>
					<td id="td_left">바코드</td>
					<td><input type="text" style="height: 30px; width: 450px;" name="ITEM_BARCODE"></td>
				</tr>
				<tr>
					<td id="td_left">입고단가</td>
					<td><input type="text" style="height: 30px; width: 450px;" name="ITEM_IN_COST"></td>
				</tr>
				<tr>
					<td id="td_left">출고단가</td>
					<td><input type="text" style="height: 30px; width: 450px;" name="ITEM_OUT_COST"></td>
				</tr>
				<tr>
					<td id="td_left">품목구분</td>
					<td>
						<select style="height: 30px; width: 460px;">
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
					<td id="td_left">대표이미지</td>
					<td>
						<input type="file" name="files" />
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