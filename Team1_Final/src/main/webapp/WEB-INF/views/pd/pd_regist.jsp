<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<%-- <link href="${pageContext.request.contextPath }/resources/css/pd.css" rel="stylesheet" type="text/css" /> --%>
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
<script type="text/javascript">
	function Barcode() {

		$.ajax({
			type: "GET",
			url: "Barcode",
			dataType: "json",
				
			 success : function(data){
			 	$("#BARCODE").val(data);

			 },
			 error : function(){
			    alert("에러")		
			 }
		});
	
	}
</script>
</head>
<body>
	<jsp:include page="../inc/top.jsp"></jsp:include>
	<div style="display: flex;">
		<div style="width: 500px; margin-top: 0px; margin-right: 0px;">
			<jsp:include page="../inc/pd_left.jsp"></jsp:include>
		</div>
		<!-- 여기서부터 본문-->
		<div align="left" style="margin-left: 100px;">
		<h1><b style="border-bottom: 10px solid">품목 등록</b></h1>
		<form action="PdRegistPro" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td id="td_left"><label for="PRODUCT_NAME">품목명</label></td>
					<td><input type="text" style="height: 30px; width: 450px;" name="PRODUCT_NAME" required="required"></td>
				</tr>
				<tr>
					<td id="td_left"><label for="PRODUCT_GROUP_BOTTOM_CD">품목그룹</label><br>
						<button style="height: 30px; width: 80px;"><b>신규등록</b></button>
					</td>
					<td>
						<input type="text" name="PRODUCT_GROUP_TOP_CD" id="PRODUCT_GROUP_TOP_CD" readonly="readonly" required="required" style="height: 30px; width: 105px;">
						<input type="text" name="PRODUCT_GROUP_BOTTOM_CD" id="PRODUCT_GROUP_BOTTOM_CD" readonly="readonly" required="required" style="height: 30px; width: 105px;">
						<input type="text" name="PRODUCT_GROUP_BOTTOM_NAME" id="PRODUCT_GROUP_BOTTOM_NAME" readonly="readonly" required="required" style="height: 30px; width: 105px;">
						<button type="button" onclick="window.open('Pd_group_bottom_SearchForm', 'searchPopup', 'width=500, height=500, left=600, top=400')"><b>품목그룹(소) 선택</b></button>
					</td>
				</tr>
				<tr>
					<td id="td_left"><label for="SIZE_DES">규격</label></td>
					<td><input type="text" style="height: 30px; width: 450px;" name="SIZE_DES"></td>
				</tr>
				<tr>
					<td id="td_left"><label for="UNIT">단위</label></td>
					<td>
						<select style="height: 30px; width: 460px;" name="UNIT" required="required">
							<option value="">=======================  단위  =======================</option>
							<option value="SET">SET</option>
							<option value="BOX">BOX</option>
							<option value="EA">EA</option>
						</select>
					</td>
				</tr>
				<tr>
					<td id="td_left"><label for="BARCODE">바코드</label></td>
					<td>
						<button type="button" onclick="Barcode()"><b>바코드 생성</b></button>
						<input type="text" name="BARCODE" id="BARCODE" required="required"> 
					</td>
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
					<td id="td_left"><label for="PRODUCT_TYPE_CD">품목구분</label></td>
					<td>
						<input type="text" name="PRODUCT_TYPE_CD" id="PRODUCT_TYPE_CD" readonly="readonly" required="required" style="height: 30px; width: 105px;">
						<input type="text" name="PRODUCT_TYPE_NAME" id="PRODUCT_TYPE_NAME" readonly="readonly" required="required" style="height: 30px; width: 105px;">
						<button type="button" onclick="window.open('Pd_type_SearchForm', 'searchPopup', 'width=500, height=500, left=600, top=400')"><b>품목구분 선택</b></button>
					</td>
				</tr>
				<tr>
					<td id="td_left"><label for="BUSINESS_NO">구매거래처</label></td>
					<td>
						<input type="text" name="BUSINESS_NO" id="BUSINESS_NO" readonly="readonly" required="required" style="height: 30px; width: 105px;">
						<input type="text" name="CUST_NAME" id="CUST_NAME" readonly="readonly" required="required" style="height: 30px; width: 105px;">
						<button type="button" onclick="window.open('Business_No_SearchForm', 'searchPopup', 'width=500, height=500, left=600, top=400')"><b>거래처목록 선택</b></button>
					</td>
				</tr>
				<tr>
					<td id="td_left"><label for="PRODUCT_IMAGE">대표이미지</label></td>
					<td>
						<input type="file" name="file" />
					</td>
				</tr>
				<tr>
					<td id="td_left"><label for="REMARKS">적요</label></td>
					<td>
						<textarea rows="3" cols="70" name="REMARKS"></textarea>
					</td>
				</tr>
			</table>
			<br>
			<hr>
			<br>
			<div id="commandCell" align="center">
				<input type="submit" value="등록" style="height: 30px; width: 100px; font-weight: bold;">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기" style="height: 30px; width: 100px; font-weight: bold;">&nbsp;&nbsp;
				<input type="button" value="취소" style="height: 30px; width: 100px; font-weight: bold;" onclick="history.back()">
			</div>
		</form>
		</div>
	</div>
</body>
</html>