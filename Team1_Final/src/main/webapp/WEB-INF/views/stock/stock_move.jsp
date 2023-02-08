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
	
	var priv ='${sessionScope.priv}';
	if(priv.charAt(4)!='1') {
		alert("권한이 없습니다.");
		
	}
	
	var stock = opener.stock;
	var selectIdx;
	var isGreaterSum = false;
	
	$(function() {
		setList();
		
	});
	
	
	var j=0;
	function setList() {
		for(var i=0; i < $(opener.document).find(".chk_top").length; i++) {
			if($(opener.document).find(".chk_top").eq(i).prop("checked")) {
				$("tbody").append (
						'<tr class="empListAdd">'
						+'<td><input type="hidden" class="STOCK_CD_Arr" name="STOCK_CD_Arr" value="'+stock[i].STOCK_CD+'"/>'+stock[i].STOCK_CD+'</td>'
						+'<td><input type="hidden" name="PRODUCT_CD_Arr" value="'+stock[i].PRODUCT_CD+'">'+stock[i].PRODUCT_NAME+'</td>'
						+'<td>'+stock[i].WH_NAME+"-"+stock[i].WH_AREA+"-"+stock[i].WH_LOC_IN_AREA+'</td>'
						+'<td><span class="stock_qty">'+stock[i].STOCK_QTY+'</span></td>'
						+'<td><input type="text" name="QTY_Arr" class="QTY_Arr" value="0" oninput="this.value=this.value.replace(/[^0-9]/g, \'\');" onchange="qtyChange(this)" /></td>'
						+'<td><input type="hidden" name="WH_LOC_IN_AREA_CD_Arr" class="TARGET_STOCK_CD_Arr" readOnly="readOnly" />'
						+'<span class="searchLoc"></span><input type="button" class="hrFormBtn" style="height:35px" value="위치 검색" onclick="searchFormOpen('+j+')"/></td>'
						+'<td><input type="text" name="MOVE_QTY_Arr" class="MOVE_QTY_Arr" value="0" oninput="this.value=this.value.replace(/[^0-9]/g, \'\');" onchange="qtyChange(this)" /></td>'
						+'<td><input type="text" class="sum" readOnly="readOnly" /></td>'
						+'<td><input type="text" style="width:200" name="REMARKS_Arr" value="-" onchange="qtyChange(this)" /></td>'
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
	
	function resetAll() {
		stockForm.reset();
		$(".searchLoc").text("");
	}
	
	function stockSubmit() {
		for(var i=0; i < j; i++) {
			var move_qty = $(".MOVE_QTY_Arr").eq(i).val();
			var target_loc = $(".TARGET_STOCK_CD_Arr").eq(i).val();
			var source_loc = $(".SOURCE_STOCK_CD_Arr").eq(i).val();
			var move_stockNo = $(".STOCK_CD_Arr").eq(i).val();
			if( move_qty != 0 && target_loc=="") {
				alert("재고번호 " + move_stockNo +"번 : 이동할 위치를 선택해주세요.");
				return false;
			}
			if( move_qty == 0 && target_loc!="") {
				alert("재고번호 " + move_stockNo +"번 : 이동시킬 수량을 입력하세요.");
				return false;
			}
			if(source_loc==target_loc) {
				alert("재고번호 " + move_stockNo +"번 : 현재 위치와 이동시킬 위치가 같습니다.");
				return false;
			}
			if(isGreaterSum) {
				alert("조정하려는 수량이 기존 수량을 초과했습니다.");
				return false;
			}
			if($(".REMARKS_Arr").eq(i).val()=='') {
				alert("--");
				$(".REMARKS_Arr").eq(i).val("-");
			}
		}
		stockForm.submit();
	}
	
	function qtyChange(selQty) {
		var idx = $(selQty).parents("tr").index(); 
		if(selQty.value=="") {
			if($(selQty).prop("name")=="REMARKS_Arr") {
				selQty.value="-";
				return false;
			} else {
				selQty.value=0;
			}
		}
		var sumQty = Number($(".QTY_Arr").eq(idx).val())+Number($(".MOVE_QTY_Arr").eq(idx).val());
		$(".sum").eq(idx).val(sumQty);
		if(sumQty > Number($(".stock_qty").eq(idx).text())) {
			alert("조정하려는 수량이 기존 수량을 초과했습니다.");
			isGreaterSum = true;
		} else {
			isGreaterSum = false;
		}
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
	<form action="StockMovePro" method="post" name="stockForm">
	<input type="hidden" name="STOCK_CONTROL_TYPE_CD" value="2" />
	<input type="hidden" name="EMP_NUM" value="${sessionScope.empNo }" />
	<table border="1" class="regi_table" style="text-align: center; margin-left: 20px; width: 1450px; font-size: 20px;">
		<thead>
		<tr>
			<th width="100">재고번호</th>
			<th width="250">품목명[규격]</th>
			<th width="250">위치</th>
			<th width="100">재고 수량</th>
			<th width="100">조정 수량</th>
			<th width="300">이동 위치</th>
			<th width="100">이동 수량</th>
			<th width="100">합계 수량</th>
			<th width="200">적요</th>
		</tr>
		</thead>
		<tbody>
		
		
		</tbody>
	</table>
	<div align="right" style="margin-top: 20px; width: 1450px;">
		<button type="button" onclick="stockSubmit()" class="hrFormBtn">조정</button>
		<button type="button" onclick="resetAll()" class="hrFormBtn">초기화</button>
	</div>
	<!-- 여기까지 본문-->
	</form>
</body>
</html>
