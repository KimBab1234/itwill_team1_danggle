<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<%-- <link href="${pageContext.request.contextPath }/resources/css/pd.css" rel="stylesheet" type="text/css" /> --%>
<!-- 폰트 변경 시작  -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
<style type="text/css">
*{
	color:#513e30;
	font-family: 'Gowun Dodum', sans-serif;
<!-- 폰트 변경 끝  -->
}

#titleH1 {
	margin-top: 20px;
	text-align: left;
	margin-left: 190px;
}
table {
	height: 300px;
	width: 650px;
	font-size: 20px;
	
	margin: 0 auto;
	border-collapse: collapse;
	border-color: #b09f76;
	font-weight: bold;
	border-radius: 10px;
  	box-shadow: 0 0 0 2px #c9b584;
  	width: 670px;
}

#td_left {
	height: 50px;
	font-weight: bold;
	font-size: 20px;
	background: #c9b584; 
 	color: #736643;
 	width: 250px;
 	text-align: center;
}
#pd_group_bottom_regist {
	background-color: #fff5e6;
	width: 120px;
	height: 50px;
	color: #575754;
	border-radius: 20px;
	border-color: transparent;
	font-weight: bold;
	font-size: 20px;
}
#b2 {
	background-color: #fff5e6;
	width: 120px;
	height: 50px;
	color: #575754;
	border-radius: 20px;
	border-color: transparent;
	font-weight: bold;
	font-size: 20px;
}
</style>
<script type="text/javascript">
	$(function() {
		$("#pd_group_bottom_regist").click(function() {
// 			alert("클릭됨");
			  $.ajax({
			        type: "POST",
			        url: 'Pd_group_bottom_registPro',
			        data: $('#pd_group_bottom_RegisPro').serialize(),
//	 		        dataType: 'text',
			        success: function(result) {
// 			        	alert(result);
			            if (result != "0") {
			                window.close();
// 			                opener.location.reload();
			            } else {
			            	alert("품목 그룹 신규 등록 실패!");
			            	window.close();	                
			            }
			        },
			        error: function(a, b, c) {
			            console.log(a, b, c);
			        }
							
			    });
		});
	});
</script>
</head>
<body>
		<!-- 여기서부터 본문-->
		<div align="center">
		<h1 id="titleH1"><b style="border-left: 10px solid">&nbsp;품목 그룹(소) 신규 등록</b></h1>
		<form onsubmit="return false" id="pd_group_bottom_RegisPro">
			<table>
				<tr>
					<td id="td_left"><label for="PRODUCT_GROUP_TOP_CD">품목그룹코드(대)</label></td>
					<td>
						<input type="text" name="PRODUCT_GROUP_TOP_CD" id="PRODUCT_GROUP_TOP_CD" readonly="readonly" required="required" style="height: 30px; width: 100px; font-weight : bold ; margin-left: 35px;">
						<input type="text" name="PRODUCT_GROUP_TOP_NAME" id="PRODUCT_GROUP_TOP_NAME" readonly="readonly" required="required" style="height: 30px; width: 100px; font-weight : bold ;">
						<button type="button" onclick="window.open('Pd_group_top_SearchForm', 'searchPopup1', 'width=500, height=500, left=600, top=400')"><b>품목그룹코드(대) 선택</b></button>
					</td>
				</tr>
				<tr>
					<td id="td_left"><label for="PRODUCT_GROUP_BOTTOM_NAME">품목그룹명(소)</label></td>
					<td><input type="text" style="height: 30px; width: 360px; font-weight : bold ; margin-left: 35px;" name="PRODUCT_GROUP_BOTTOM_NAME" required="required"></td>
				</tr>
			</table>
			<br>
			<div id="commandCell" align="center">
				<button type="button"  id="pd_group_bottom_regist" style="height: 30px; width: 100px; font-weight: bold;">등록</button>
				<input type="button" id="b2" value="취소" style="height: 30px; width: 100px; font-weight: bold;" onclick="window.close()">
			</div>
		</form>
		</div>
</body>
</html>