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
	height: 300px;
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
	$('#pd_group_bottom_regist').click(function() {
		
	    $.ajax({
	        type: 'POST',
	        url: 'Pd_group_bottom_registPro',
	        data: $('#pd_group_bottom_RegisPro').serialize(),
	        dataType: 'json'
	        	
	    });
	    
	});
</script>
</head>
<body>
		<!-- 여기서부터 본문-->
		<div align="center" style="margin-left: 100px;">
		<h1><b style="border-bottom: 10px solid">품목 그룹(소) 신규 등록</b></h1>
		<form enctype="multipart/form-data" id="pd_group_bottom_RegisPro">
			<table>
				<tr>
					<td id="td_left"><label for="PRODUCT_GROUP_TOP_CD">품목그룹코드(대)</label></td>
					<td>
						<input type="text" name="PRODUCT_GROUP_TOP_CD" id="PRODUCT_GROUP_TOP_CD" readonly="readonly" required="required" style="height: 30px; width: 105px;">
						<button type="button" onclick="window.open('Pd_group_top_SearchForm', 'searchPopup1', 'width=500, height=500, left=600, top=400')"><b>품목그룹코드(대) 선택</b></button>
					</td>
				</tr>
				<tr>
					<td id="td_left"><label for="PRODUCT_GROUP_BOTTOM_NAME">품목그룹명(소)</label></td>
					<td><input type="text" style="height: 30px; width: 450px;" name="PRODUCT_GROUP_BOTTOM_NAME" required="required"></td>
				</tr>
			</table>
			<br>
			<hr>
			<br>
			<div id="commandCell" align="center">
				<input type="submit" id="pd_group_bottom_regist" value="등록" style="height: 30px; width: 100px; font-weight: bold;">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기" style="height: 30px; width: 100px; font-weight: bold;">&nbsp;&nbsp;
				<input type="button" value="취소" style="height: 30px; width: 100px; font-weight: bold;" onclick="history.back()">
			</div>
		</form>
		</div>
</body>
</html>