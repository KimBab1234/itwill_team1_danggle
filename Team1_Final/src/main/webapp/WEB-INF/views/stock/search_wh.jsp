<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>창고 검색</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
<script src="https://kit.fontawesome.com/5fad4a5f29.js" crossorigin="anonymous"></script>
<style>
*{
	color:#513e30;
	font-family: 'Gowun Dodum', sans-serif;
	font-weight: bold;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">

	var whList;
	var pageNum=1;
	
	function searchEnter() {
		if (window.event.keyCode == 13) {
			searchWhFunc();
		}
	}

	function searchWhFunc() {
		searchType = $("#searchType").val();
		if (searchType == '') {
			alert("검색 유형을 선택하세요.");
			return false;
		}
		keyword = $("#keyword").val();
		$.ajax({
			url: 'WhSearch',
			type: 'POST',
			data: {
				pageNum : pageNum,
				searchType : searchType,
				keyword : keyword
			},
			dataType : 'json',
			success : function(response) {
				wh = response.jsonWh;
				/// 테이블 초기화
				$("table tbody").empty();
				/// 데이터 뿌리기
				if(wh.length==0) {
					$(".detail_table").append('<tr><td colspan="4" style="font-size: 20px;">검색 결과가 없습니다.</td></tr>');
				} else {
					for(var i=0; i<wh.length; i++) {
						$(".detail_table").append('<tr class="empListAdd">'
								+'<td>'+wh[i].WH_NAME+'</td>'
								+'<td>'+wh[i].WH_AREA+'</td>'
								+'<td>'+wh[i].WH_LOC_IN_AREA+'</td>'
								+'<td><button onclick="whSelect(' + i + ')">선택</button></td>'
								+'</tr>');
					}
				}
				
			}
		});
	}

	function whSelect(i) {
		alert(opener.selectIdx);
		$(opener.document).find('.searchLoc').eq(opener.selectIdx).text(wh[i].WH_NAME + "-" + wh[i].WH_AREA + "-" + wh[i].WH_LOC_IN_AREA);
		$(opener.document).find('.TARGET_STOCK_CD_Arr').eq(opener.selectIdx).val(wh[i].WH_LOC_IN_AREA_CD);
		this.close();
	}
	
</script>
<link href="${pageContext.request.contextPath }/resources/css/in.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div style="width:750px;">
		<div class="title_regi">창고 검색</div>
		<div class="box" style="width:750px; height: 100px;">
			<div class="in_box" >
				<select id="searchType" name="searchType" style="text-align: center; font-size: 20px; width: 150px; height: 35px;">
					<option value="">검색 유형</option>
					<option value="WH_NAME">창고명</option>
					<option value="WH_AREA">구역명</option>
					<option value="WH_LOC_IN_AREA">위치명</option>
				</select>
				<input type="text" class="note" name="keyword" style="font-size: 20px;" id="keyword" onkeyup="searchEnter()" >
				<button style="font-size: 20px; width: 100px; height: 35px;" id="Listbtn2"  onclick="searchWhFunc()">
					<i class="fa-solid fa-magnifying-glass" style="color: #fff; margin: 0;"></i>&nbsp;검색
				</button>
			</div>
			<br>
			<table class="detail_table" style="width:750px;">
				<thead>
					<tr>
						<th width="150" style="font-size: 20px;">창고명</th>
						<th width="200" style="font-size: 20px;">구역명</th>
						<th width="200" style="font-size: 20px;">위치명</th>
						<th width="100" style="font-size: 20px;">선택</th>
					</tr>
				</thead>
				<tbody>
				
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>