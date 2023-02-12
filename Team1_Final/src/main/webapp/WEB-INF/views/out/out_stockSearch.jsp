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
<style type="text/css">
	#sendStock {
		color: #fff;
	    background-color: #736643;
	    border-color: #fff;
	    border-radius: 4px;
	    margin-left: 3px;
	    padding: 3px 5px;;
	}
</style>
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script>	
	var	searchType = '';
	var keyword = '';
	var updateStr = opener.updateStr;
	
	//---------------------------- 권한 판단 -----------------------------
	var loginEmp = '${sessionScope.empNo}';
	var priv = '${sessionScope.priv}';
	if(loginEmp=='') {
		alert("로그인 후 이용하세요.");
		window.close();
		history.back();
		opener.location.href  = './Login';
	} else if(priv.charAt(1) !='1') {
		alert("권한이 없습니다.");
		window.close();
	}
	// --------------------------------------------------------------------

	
	$(function() {
// 		alert(opener.pdcd);
		
		$.ajax({
			url: 'StockList',
			type: 'POST',
			data: {
				searchType : "OUT_PRODUCT_CD",
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
						$(".regi_table").append('<tr>'
								+'<td width="150"><a href="javascript:showStockDetail(' + stock[i].STOCK_CD + ')">' + stock[i].STOCK_CD + '</a></td>'
								+'<td width="120">'+stock[i].PRODUCT_CD+'</td>'
								+'<td width="400">'+stock[i].PRODUCT_NAME+'</td>'
								+'<td width="250">'+stock[i].WH_NAME +'</td>'
								+'<td width="250">'+stock[i].WH_AREA +'</td>'
								+'<td width="250">'+stock[i].WH_LOC_IN_AREA+'</td>'
								+'<td width="150">'+stock[i].STOCK_QTY+'</td>'
								+'<td><input type="button" id="sendStock" value="선택" onclick="selectstock('+ i +', '+ stock[i].STOCK_CD +')"></td>'
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
	function selectstock(i, stock_cd) {
		for(var j = 0; j < $(opener.document).find(".idx").length; j++){
			var val = $(opener.document).find('.stock_cd').eq(j).val();
		
			if(updateStr == "up"){
				if(val != stock_cd){
					$(opener.document).find('.stock_cd').eq(j).val(stock[i].STOCK_CD);
					$(opener.document).find('.stock_qty').eq(j).val(stock[i].STOCK_QTY);
					break;
				}
			} else {
				if(val == "재고검색"){
					$(opener.document).find('.stock_cd').eq(j).val(stock[i].STOCK_CD);
					$(opener.document).find('.stock_qty').eq(j).val(stock[i].STOCK_QTY);
					break;
				}				
							
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
			<th></th>
		</tr>
		</thead>
		<tbody>
		
		</tbody>
	</table>
</body>
</html>