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
</style>
<!-- 폰트 변경 끝  -->
<style>

</style>
<title>재고 이력</title>
<script>

var type;
var pageNum;
var stockNo = '${param.stockNo}';
var stock;
var input=0;
var output=0;
var delta=0;
var len;

$(function() {
	
	////전체 입고 출고 선택시 목록 변경
	$(".choice").on("click", function() {
	
		if(type==$(this).attr("id")){
			return false;
		}
		type = $(this).attr("id");
		$(".choiceSelect").attr("class","choice");
		$(this).attr("class","choiceSelect");
		///pageNum은 일부러 null로주기
		getList(null);
	});
	
	$("#0").attr("class","choiceSelect");
	getList(1);
	
	
	
});
	
function getList(toPageNum) {

	///같은 페이지 들어오면 아무일 없음
	if(pageNum == toPageNum) {
		return false;
	}
	////새로운 페이지로 바꿔주기
	if(toPageNum==null) {
		toPageNum = 1;
	}
	pageNum = toPageNum;
	$.ajax({
		url: 'StockDetailPro',
		type: 'post',
		data: {
			pageNum : toPageNum,
			type : type,
			stockNo: stockNo
		},
		dataType : 'json',
		success : function(response) {
			stock = response.jsonStock;
			thisLoc = response.jsonThisLoc;
			$("#thisLocName").text(thisLoc);
			
			len = stock.length;
			///페이지가 1보다 크면 초기화 안함
			if(pageNum==1) {
				/// 테이블 초기화
				$("table tbody").empty();
				/// 데이터 뿌리기
				if(stock.length==0) {
					$(".regi_table").append('<tr><td colspan="8">해당 기록이 없습니다.</td></tr>');
					return false;
				}
				
			}
			
			for(var i=0; i<stock.length; i++) {
				var str = "";
				str += '<tr height="100px;"><td>'+stock[i].STOCK_DATE+'</td>'
				+'<td>'+stock[i].STOCK_CONTROL_TYPE_NAME+'</td>'
				+'<td>'+stock[i].PRODUCT_NAME+'</td>';
				
				if(stock[i].SOURCE_STOCK_CD!=undefined) {
					str += '<td><a href="StockDetail?stockNo='+stock[i].SOURCE_STOCK_CD+'">'+stock[i].SOURCE_STOCK_CD+'</a></td>';
				} else {
					str += '<td>-</td>';
				}
				if(stock[i].TARGET_STOCK_CD!=undefined) {
					str += '<td><a href="StockDetail?stockNo='+stock[i].TARGET_STOCK_CD+'">'+stock[i].TARGET_STOCK_CD+'</a></td>';
				}else {
					str += '<td>-</td>';
				}
				str += '<td>'+stock[i].QTY+'</td>'
				+'<td>'+stock[i].EMP_NAME+'</td>'
				+'<td>'+stock[i].REMARKS+'</td></tr>';
				
				$(".regi_table").append(str);
				
			}
		}
	});
}


$(window).scroll(function() {
	let scrollTop = $(window).scrollTop();
	let windowHeigtht = $(window).height();
	let documentHeight = $(document).height();
	if(scrollTop + windowHeigtht + 1 >= documentHeight) {
		getList(pageNum+1);
	}
	
});

</script>
<style>
option {
	font-weight: bold;
}

</style>
</head>
<body>
	
	<div style="display: flex; width: 1250px; margin-left: 20px;">
		<h1 align="left" style="text-align: left; width:600px;">| 재고 이력 </h1>
	</div>
	<div style="width: 1250px; display: flex; margin-left: 20px;">
		<div class="choice" id="0">전체</div><div class="choice" id="1">입고</div><div class="choice" id="2">출고</div>
		<h1 style="text-align:right; margin: 0">&nbsp;&nbsp;&nbsp;재고 위치 : <span id="thisLocName"></span></h1>
	</div>
	<table border="1" class="regi_table" style="text-align: center; margin-left: 20px; width: 1250px; font-size: 20px;">
		<thead>
			<tr>
				<th width="150">작업일자</th>
				<th width="150">작업구분</th>
				<th width="300">품목명[규격]</th>
				<th width="100">보낸 번호</th>
				<th width="100">받는 번호</th>
				<th width="70">수량</th>
				<th width="150">작업자명</th>
				<th width="200">적요</th>
			</tr>
		</thead>
		<tbody>
		
		</tbody>
	
	</table>

	<!-- 여기까지 본문-->
</body>
</html>
