<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath }/resources/css/in.css" rel="stylesheet" type="text/css" />
<script src="https://kit.fontawesome.com/5fad4a5f29.js" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<title>출고 예정 재고 조회</title>
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script>	
	var	searchType = '';
	var keyword = '';
	
	$(function() {
// 		alert(opener.pdcd);
		
		$.ajax({
			url: 'StockList',
			type: 'POST',
			data: {
				searchType : "PRODUCT_CD",
				keyword : opener.pdcd
			},
			dataType : 'json',
			success : function(response) {
				stock = response.jsonStock;
				/// 테이블 초기화
				$("table tbody").empty();
				/// 데이터 뿌리기
				if(stock.length==0) {
					$(".regi_table").append('<tr><td colspan="8" style="font-size: 20px;">검색 결과가 없습니다.</td></tr>');
				} else {
					for(var i = 0; i < stock.length; i++) {
						$(".regi_table").append('<tr id="sendStock" onclick="selectstock('+ i +')">'
								+'<td width="150"><a href="javascript:showStockDetail(' + stock[i].STOCK_CD + ')">' + stock[i].STOCK_CD + '</a></td>'
								+'<td width="120">'+stock[i].PRODUCT_CD+'</td>'
								+'<td width="400">'+stock[i].PRODUCT_NAME+'</td>'
								+'<td width="250">'+stock[i].WH_NAME +'</td>'
								+'<td width="250">'+stock[i].WH_AREA +'</td>'
								+'<td width="250">'+stock[i].WH_LOC_IN_AREA+'</td>'
								+'<td width="150">'+stock[i].STOCK_QTY+'</td>'
								+'</tr>');
					}
				}
				
			}
		});
		
	});
	
	// 재고 기록 보기
	function showStockDetail(stockNo) {
		 window.open('StockDetail?stockNo='+stockNo, 'searchPopup', 'width=1300, height=700, left=300, top=200');
	}
	
	// 재고 선택
	function selectstock(i) {
// 		alert(i);
// 		alert(stock[i].STOCK_CD);
		for(var j = 0; j < $(opener.document).find(".idx").length; j++){
			if($(opener.document).find('.stock_cd').eq(j).val() == "재고번호"){
				$(opener.document).find('.stock_cd').eq(j).val(stock[i].STOCK_CD);
				break;
			}
		}
		this.close();
	}
	

</script>
<style>
option {
	font-weight: bold;
}

</style>
</head>
<body>
	<h1 align="left" style="text-align: left; margin-left: 100px;">재고 조회</h1>
	<table border="1" class="regi_table">
		<thead>
		<tr>
			<th width="150">재고번호</th>
			<th width="120">품목코드</th>
			<th width="400">품목명</th>
			<th width="250">창고명</th>
			<th width="250">구역명</th>
			<th width="250">위치명</th>
			<th width="150">재고수량</th>
		</tr>
		</thead>
		<tbody>
		
		</tbody>
	</table>
</body>
</html>