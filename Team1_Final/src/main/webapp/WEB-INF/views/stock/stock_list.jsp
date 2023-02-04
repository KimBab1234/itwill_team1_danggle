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
<style>

.here, .fa-angles-right, .fa-angles-left {
	border: 1px solid;
	margin: 0px;
	width: 40px;
	height: 40px;
	display: table-cell;
	vertical-align: middle;
	text-align: center;
}

.here, .fa-angles-right, .fa-angles-left:hover {
	cursor: pointer;
	
}

</style>
<title>Insert title here</title>
<script>
	
	var pageList = {};
	
	///처음 들어왔을때 기본값 1
	var pageNum;
	var pageListLimit = 1;
	var startPage;
	var endPage;
	var maxPage;
	var	searchType = '';
	var keyword = '';

	$(function() {
		getPageList(1);
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
		
		$.ajax({
			url: 'StockList',
			type: 'POST',
			data: {
				pageNum : toPageNum,
				searchType : searchType,
				keyword : keyword
			},
			dataType : 'json',
			success : function(response) {
				stock = response.jsonStock;
				/// 테이블 초기화
				$("tbody").empty();
				/// 데이터 뿌리기
				if(stock.length==0) {
					$(".regi_table").append('<tr><td colspan="6" style="font-size: 20px;">검색 결과가 없습니다.</td></tr>');
				} else {
					for(var i=0; i<stock.length; i++) {
						$(".regi_table").append('<tr class="empListAdd" style="height:100px; width:150">'
								+'<td width="100"><a href="javascript:showStockDetail(' + stock[i].STOCK_CD + ')">' + stock[i].STOCK_CD + '</a></td>'
								+'<td width="100">'+stock[i].PRODUCT_CD+'</td>'
								+'<td width="250">'+stock[i].PRODUCT_NAME+'</td>'
								+'<td width="150">'+stock[i].WH_AREA+'</td>'
								+'<td width="150">'+stock[i].WH_LOC_IN_AREA+'</td>'
								+'<td width="70">'+stock[i].STOCK_QTY+'</td>'
								+'</tr>');
					}
				}
				
			}
		});
		
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
    
	////페이지 목록 뿌리기 (가지고있는 데이터로 뿌리기만 함)
	function pageListChange(toPageNum) {
		
		if(toPageNum==null) {
			toPageNum = 1;
		}
		
		///pageNum 변경
		pageNum = toPageNum;
		/// pageList 영역 초기화
		$("#pageListSection").html("");
		
		/// 데이터 뿌리기
		startPage = (pageNum-1) / pageListLimit * pageListLimit + 1;
		endPage = startPage + pageListLimit - 1;
		maxPage = pageList.maxPage; 

		if(endPage > maxPage) {
			endPage = maxPage;
		}
		if(pageNum > pageListLimit) {
			$("#pageListSection").append('<i class="fas fa-solid fa-angles-left"onclick="arrowClick('+(startPage-pageListLimit)+')"></i>');
		}
		for(var i=startPage; i <= endPage; i++) {
			$("#pageListSection").append('<div class="here" onclick="hereClick('+i+')">'+ i + '</div>');
		}
		if(endPage < maxPage) {
			$("#pageListSection").append('<i class="fas fa-solid fa-angles-right" onclick="arrowClick('+(endPage+1)+')"></i>');
		}
	}
	
	
	////페이징처리
	function getPageList(toPageNum) {
		$.ajax({
			url: 'StockListPage',
			type: 'post',
			data: {
				pageNum : toPageNum,
				searchType : searchType,
				keyword : keyword
			},
			dataType : 'json',
			success : function(response) {
				pageList = JSON.parse(response.jsonPage);
				getStockList(toPageNum);
				pageListChange(toPageNum);
			}
		});
	}
	
	function hereClick(herePage){
		getStockList(herePage);
	};
	
	function arrowClick(herePage){
		getPageList(herePage);
	};
	
	 function showStockDetail(stockNo) {
		 window.open('StockDetail?stockNo='+stockNo, 'searchPopup', 'width=1000, height=700, left=600, top=400');
	}
	

</script>
<style>
option {
	font-weight: bold;
}

</style>
</head>
<body>
	<jsp:include page="../inc/top.jsp"></jsp:include>
	<div style="width: 1800px; display: flex; min-height: 1300px;"  align="center">
		<div style="width: 300px; margin-top: 0px; margin-right: 0px;">
			<jsp:include page="../inc/hr_left.jsp"></jsp:include>
		</div>
		<!-- 여기서부터 본문-->
		<div style="width: 1500px;"  align="center" >
		<h1 align="left" style="width: 1500px; text-align: left; margin-left: 100px;">| 재고 조회</h1>
		<div style="display: flex; width: 1300px; text-align: right" align="right">
			<div style="width: 1400px; margin-bottom:10px; text-align: right;">
				<select name="searchType" id="searchType" style="text-align: center; font-weight: bold; width: 100px; height: 35px;">
					<option value="">검색 유형</option>
					<option value="PRODUCT_CD">품목코드</option>
					<option value="PRODUCT_NAME">품목명</option>
					<option value="WH_AREA">구역명</option>
					<option value="WH_LOC_IN_AREA">위치명</option>
				</select>
				<input type="text" name="keyword" id="keyword" onkeyup="searchEnter()" style="height: 35px;">
				<button  class="hrFormBtn"  style="width: 100px; height:30px; font-size:18px;" onclick="searchList()">
					<i class="fa-solid fa-magnifying-glass" style="color: #fff; margin: 0;"></i>&nbsp;검색
				</button>
			</div>
		</div>
		<table border="1" class="regi_table" style="text-align: center; width: 1300px; font-size: 20px;">
			<tr>
				<th width="150">재고번호</th>
				<th width="120">품목코드</th>
				<th width="200">품목명</th>
				<th width="150">구역명</th>
				<th width="150">위치명</th>
				<th width="70">재고수량</th>
			</tr>
			<tbody>
			
			</tbody>
		</table>
	
		<!-- 페이지 목록 부분 -->
        <div id="pageListSection" align="center" style="width:1500px; margin-top:20px; font-weight: bold; display:flex table; font-size: 20px;">
			
		</div> 
        <!-- 페이지 목록 끝-->
		</div>
		<!-- 여기까지 본문-->
	</div>
</body>
</html>
