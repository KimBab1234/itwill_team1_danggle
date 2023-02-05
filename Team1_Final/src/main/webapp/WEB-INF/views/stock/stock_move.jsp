<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath }/resources/css/hr.css" rel="stylesheet" type="text/css" />
<script src="https://kit.fontawesome.com/5fad4a5f29.js" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<!-- 폰트 변경 시작  -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
<style>
*{
	color:#513e30;
	font-family: 'Gowun Dodum', sans-serif;
}

input[type="button"]{
	vertical-align: middle;
	margin: 0;
}
</style>
<!-- 폰트 변경 끝  -->
<style>

</style>
<title>Insert title here</title>
<script>
	
	var stock = opener.stock;
	var selectIdx;
	
	$(function() {
		setList();
	});
	
	
	function setList() {
		var j=0;
		for(var i=0; i < $(opener.document).find(".chk_top").length; i++) {
			if($(opener.document).find(".chk_top").eq(i).prop("checked")) {
				$("tbody").append (
						'<tr class="empListAdd">'
						+'<td><input type="hidden" name="STOCK_CD_Arr" value="'+stock[i].STOCK_CD+'"/>'+stock[i].STOCK_CD+'</td>'
						+'<td><input type="hidden" name="PRODUCT_CD_Arr" value="'+stock[i].PRODUCT_CD+'">'+stock[i].PRODUCT_NAME+'</td>'
						+'<td><input type="hidden" name="SOURCE_STOCK_CD_Arr" value="'+stock[i].WH_LOC_IN_AREA_CD+'">'+stock[i].WH_NAME+"-"+stock[i].WH_AREA+"-"+stock[i].WH_LOC_IN_AREA+'</td>'
						+'<td>'+stock[i].STOCK_QTY+'</td>'
						+'<td><input type="text" name="QTY_Arr" value="0" oninput="this.value=this.value.replace(/[^0-9]/g, \'\');" /></td>'
						+'<td><input type="hidden" name="TARGET_STOCK_CD_Arr" class="TARGET_STOCK_CD_Arr" readOnly="readOnly" />'
						+'<span class="searchLoc"></span><input type="button" class="hrFormBtn" style="height:35px" value="위치 검색" onclick="searchFormOpen('+j+')"/></td>'
						+'<td><input type="text" name="MOVE_QTY_Arr" value="0" oninput="this.value=this.value.replace(/[^0-9]/g, \'\');" /></td>'
						+'<td><input type="text" id="sum" readOnly="readOnly" /></td>'
						+'<td><input type="text" style="width:200" name="REMARKS_Arr" /><input type="hidden" name="STOCK_CONTROL_TYPE_CD_Arr" value="2" /></td>'
						+'</tr>'
				);
				j++;
			}
		}
	}
	
	function searchFormOpen(i) {
		selectIdx=i;
		window.open('WhSearchForm', 'searchPopup', 'width=800, height=500, left=600, top=400');
	}
</script>
<style>
option {
	font-weight: bold;
}

input {
	width: 50px;
}
</style>
</head>
<body>
	
	<h1 align="left" style="width: 1450px; text-align: left; margin-left: 20px;">| 재고 조정 </h1>
	<form action="StockMovePro" method="post">
	<table border="1" class="regi_table" style="text-align: center; margin-left: 20px; width: 1450px; font-size: 20px;">
		<thead>
		<tr>
			<th width="100">재고번호</th>
			<th width="300">품목명[규격]</th>
			<th width="250">위치</th>
			<th width="100">재고 수량</th>
			<th width="100">조정 수량</th>
			<th width="250">이동 위치</th>
			<th width="100">이동 수량</th>
			<th width="100">합계 수량</th>
			<th width="200">적요</th>
		</tr>
		</thead>
		<tbody>
		
		
		</tbody>
	</table>
	<div align="right" style="margin-top: 20px; width: 1450px;">
		<button type="button" class="hrFormBtn">조정</button>
		<button type="reset" class="hrFormBtn">초기화</button>
	</div>
	<!-- 여기까지 본문-->
	</form>
</body>
</html>
