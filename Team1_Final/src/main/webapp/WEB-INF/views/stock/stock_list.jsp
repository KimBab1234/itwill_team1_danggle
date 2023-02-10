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

/* input 은 숨겨주기 */
 input.chk_top{
   display:none;
  } 
/* input 바로 다음의 label */
input.chk_top + label{
  cursor:pointer;
  vertical-align: middle;
  line-height:0px; 
  font-size:16px;
  font-weight: bold;  
  color: #736643
 }

/* input 바로 다음의 label:before 에 체크하기 전 CSS 설정 */
 input.chk_top + label:before{ 
   content:""; 
   display: inline-block; 
   width:30px; 
   height:30px; 
   line-height:30px; 
   border:1px solid #cbcbcb; 
   vertical-align: middle;
   margin: 0px;
   }
  
/*  checked된 input 바로 다음의 label:before 에 체크 후 CSS 설정    */
 input.chk_top:checked + label:before{ 
   content:"\f00c"; 
   font-family:"Font Awesome 5 free"; 
    font-weight:900; 
   color:#fff; 
   background-color:#736643; 
   border-color:#c9b584; 
   font-size:25px; 
   text-align:center; 
   vertical-align: middle;
   } 

</style>
<title>재고 목록</title>
<script>
	
	////재고 조회 권한은 "3"
	var priv ='${sessionScope.priv}';
	if(priv=='') {
		alert("로그인 후 이용하세요.")
		location.href="./Login";
	} else if(priv.charAt(3)!='1') {
		alert("권한이 없습니다.");
		history.back();
	}
	
	
	///처음 들어왔을때 기본값 1
	var pageList = {};
	var pageNum;
	var pageListLimit = 10;
	var startPage;
	var endPage;
	var maxPage;
	var	searchType = '';
	var keyword = '';
	var stock;

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
				$("table tbody").empty();
				/// 데이터 뿌리기
				if(stock.length==0) {
					$(".regi_table").append('<tr><td colspan="8" style="font-size: 20px;">검색 결과가 없습니다.</td></tr>');
				} else {
					for(var i=0; i<stock.length; i++) {
						$(".regi_table").append('<tr>'
								+'<td width="50"><input type="checkbox" class="chk_top" id="chk_top'+i+'" /><label for="chk_top'+i+'" /></td>'
								+'<td width="150"><a href="javascript:showStockDetail(' + i + ')">' + stock[i].STOCK_CD + '</a></td>'
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
	
	function showStockDetail(idx) {
		 window.open('StockDetail?stockNo='+stock[idx].STOCK_CD, 'searchPopup', 'width=1300, height=700, left=300, top=200');
	}
	
	function showStockMoveList() {
		var oneChk = false;
		for(var i=0; i < $(".chk_top").length; i++) {
			if($(".chk_top").eq(i).prop("checked")) {
				oneChk=true;
				break;
			}			
		}
		if(oneChk) {
			window.open('StockMove', 'movePopup', 'width=1500, height=700, left=150, top=50');
		} else {
			alert("조정할 재고를 선택하세요.");
		}
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
			<jsp:include page="../inc/wms_left.jsp"></jsp:include>
		</div>
		<!-- 여기서부터 본문-->
		<div style="width: 1500px;"  align="center" >
		<h1 align="left" style="width: 1500px; text-align: left; margin-left: 100px;">| 재고 조회</h1>
		<div style="display: flex; width: 1300px; text-align: right" align="right">
			<div style="width: 1400px; margin-bottom:10px; text-align: right;">
				<select name="searchType" id="searchType" style="text-align: center; font-weight: bold; width: 100px; height: 35px;">
					<option value="">검색 유형</option>
					<option value="PRODUCT_CD">품목코드</option>
					<option value="STOCK_CD">재고번호</option>
					<option value="PRODUCT_NAME">품목명</option>
					<option value="WH_AREA">구역명</option>
					<option value="WH_LOC_IN_AREA">위치명</option>
				</select>
				<input type="text" name="keyword" id="keyword" onkeyup="searchEnter()" style="height: 35px; font-weight: bold;">
				<button  class="hrFormBtn"  style="width: 100px; height:30px; font-size:18px;" onclick="searchList()">
					<i class="fa-solid fa-magnifying-glass" style="color: #fff; margin: 0;"></i>&nbsp;검색
				</button>
			</div>
		</div>
		<table border="1" class="regi_table" style="text-align: center; width: 1300px; font-size: 20px;">
			<thead>
			<tr>
				<th width="50">조정체크</th>
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
		<div align="left" style="width: 1300px; margin-top: 20px;">
			<button type="button" class="hrFormBtn" onclick="showStockMoveList()" style="width:200px; font-size: 20px;">재고 조정</button>
		</div>
		<!-- 페이지 목록 부분 -->
        <div id="pageListSection" align="center" style="width:1500px; margin-top:20px; font-weight: bold; display:flex table; font-size: 20px;">
			
		</div> 
        <!-- 페이지 목록 끝-->
		</div>
		<!-- 여기까지 본문-->
	</div>
</body>
</html>
