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
<title>Insert title here</title>
<style>
	.here, .fa-angles-right, .fa-angles-left {
		border: 1px solid;
		margin: 0px;
		width: 20px;
		height: 15px;
		display: table-cell;
		vertical-align: middle;
		text-align: center;
	}
	
	.here, .fa-angles-right, .fa-angles-left:hover {
		cursor: pointer;
	}
</style>

<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script>	
// 	var pageList = {};
	
	///처음 들어왔을때 기본값 1
// 	var pageNum;
// 	var pageListLimit = 10;
// 	var startPage;
// 	var endPage;
// 	var maxPage;
	var	searchType = '';
	var keyword = '';

	$(function() {
// 		getPageList(1);
		$.ajax({
			url: 'StockList',
			type: 'POST',
			data: {
				pageNum : toPageNum,
				searchType : "PRODUCT_CD",
				keyword : keyword
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
					for(var i=0; i<stock.length; i++) {
						$(".regi_table").append('<tr>'
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
	
	function getStockList(toPageNum) {

		///같은 페이지 들어오면 아무일 없음
		if(pageNum == toPageNum) {
			return false;
		}
		////새로운 페이지로 바꿔주기
		if(toPageNum==null) {
			toPageNum = 1;
		}
		pageNum = toPageNum;
		
// 		$.ajax({
// 			url: 'StockList',
// 			type: 'POST',
// 			data: {
// 				pageNum : toPageNum,
// 				searchType : searchType,
// 				keyword : keyword
// 			},
// 			dataType : 'json',
// 			success : function(response) {
// 				stock = response.jsonStock;
// 				/// 테이블 초기화
// 				$("table tbody").empty();
// 				/// 데이터 뿌리기
// 				if(stock.length==0) {
// 					$(".regi_table").append('<tr><td colspan="8" style="font-size: 20px;">검색 결과가 없습니다.</td></tr>');
// 				} else {
// 					for(var i=0; i<stock.length; i++) {
// 						$(".regi_table").append('<tr>'
// 								+'<td width="150"><a href="javascript:showStockDetail(' + stock[i].STOCK_CD + ')">' + stock[i].STOCK_CD + '</a></td>'
// 								+'<td width="120">'+stock[i].PRODUCT_CD+'</td>'
// 								+'<td width="400">'+stock[i].PRODUCT_NAME+'</td>'
// 								+'<td width="250">'+stock[i].WH_NAME +'</td>'
// 								+'<td width="250">'+stock[i].WH_AREA +'</td>'
// 								+'<td width="250">'+stock[i].WH_LOC_IN_AREA+'</td>'
// 								+'<td width="150">'+stock[i].STOCK_QTY+'</td>'
// 								+'</tr>');
// 					}
// 				}
				
// 			}
// 		});
		
	}

    function searchEnter() {
		if(window.event.keyCode == 13) {
			searchList();
		}
	}
	
    function searchList() {
    	searchType = $("#searchType").val();
		if(searchType=='') {
			alert("검색 유형을 선택하세요.");
			return false;
		}
		keyword = $("#keyword").val();
		getPageList(null);
    }
    
// 	////페이지 목록 뿌리기 (가지고있는 데이터로 뿌리기만 함)
// 	function pageListChange(toPageNum) {
		
// 		if(toPageNum==null) {
// 			toPageNum = 1;
// 		}
		
// 		///pageNum 변경
// 		pageNum = toPageNum;
// 		/// pageList 영역 초기화
// 		$("#pageListSection").html("");
		
// 		/// 데이터 뿌리기
// 		startPage = (pageNum-1) / pageListLimit * pageListLimit + 1;
// 		endPage = startPage + pageListLimit - 1;
// 		maxPage = pageList.maxPage; 

// 		if(endPage > maxPage) {
// 			endPage = maxPage;
// 		}
// 		if(pageNum > pageListLimit) {
// 			$("#pageListSection").append('<i class="fas fa-solid fa-angles-left"onclick="arrowClick('+(startPage-pageListLimit)+')"></i>');
// 		}
// 		for(var i=startPage; i <= endPage; i++) {
// 			$("#pageListSection").append('<div class="here" onclick="hereClick('+i+')">'+ i + '</div>');
// 		}
// 		if(endPage < maxPage) {
// 			$("#pageListSection").append('<i class="fas fa-solid fa-angles-right" onclick="arrowClick('+(endPage+1)+')"></i>');
// 		}
// 	}
	
	
// 	////페이징처리
// 	function getPageList(toPageNum) {
// 		$.ajax({
// 			url: 'StockListPage',
// 			type: 'post',
// 			data: {
// 				pageNum : toPageNum,
// 				searchType : searchType,
// 				keyword : keyword
// 			},
// 			dataType : 'json',
// 			success : function(response) {
// 				pageList = JSON.parse(response.jsonPage);
// 				getStockList(toPageNum);
// 				pageListChange(toPageNum);
// 			}
// 		});
// 	}
	
// 	function hereClick(herePage){
// 		getStockList(herePage);
// 	};
	
// 	function arrowClick(herePage){
// 		getPageList(herePage);
// 	};
	
	function showStockDetail(stockNo) {
		 window.open('StockDetail?stockNo='+stockNo, 'searchPopup', 'width=1300, height=700, left=300, top=200');
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
	<div style="display: flex; text-align: right" align="right">
		<div style="margin-bottom:10px; text-align: right;">
			<select name="searchType" id="code" style="text-align: center; width: 100px;">
				<option value="">검색 유형</option>
				<option value="PRODUCT_CD">품목코드</option>
				<option value="PRODUCT_NAME">품목명</option>
				<option value="WH_AREA">구역명</option>
				<option value="WH_LOC_IN_AREA">위치명</option>
			</select>
			<input type="text" name="keyword" id="keyword" onkeyup="searchEnter()">
			<button  class="Listbtn" onclick="searchList()">
				<i class="fa-solid fa-magnifying-glass" ></i>&nbsp;검색
			</button>
		</div>
	</div>
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
	<!-- 페이지 목록 부분 -->
       <div id="pageListSection" align="center" style="margin-top:20px; font-weight: bold; display:flex table; font-size: 20px;">
	</div> 
</body>
</html>