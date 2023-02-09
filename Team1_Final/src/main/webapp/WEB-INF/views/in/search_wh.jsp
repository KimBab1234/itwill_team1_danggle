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
<style>
*{
	color:#513e30;
	font-family: 'Gowun Dodum', sans-serif;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">
	var stocklist;
	var i = 0;
		
		function func(){
		
			$.ajax({
				url: "SearchWhLoc",
				type: "GET",
				data: {
					keyword : $("#keyword").val()
				},
				dataType: "json"
			})
			.done(function(response) {
				stocklist =JSON.parse(response.stockList);
				if(stocklist.length == 0){
					alert("검색 결과가 없습니다");
				}else {					
					$(".detail_table").find("tr:gt(0)").remove();
					
					for(var i = 0; i < stocklist.length; i++) {
						let result = "<tr>"
									+ "<td>" + stocklist[i].wh_loc_in_area + "</td>"
									+ "<td>" + stocklist[i].wh_name + "</td>"
									+ "<td>" + "<input type='button' id='Listbtn' class='recobtn' value='선택' onclick='departSelect("+i+")'>"
									+ "</tr>";
									
						$(".detail_table").append(result);
					}
				}
			})
			.fail(function() {
				$(".recoTable").append("<h3>요청 실패!</h3>");
			});
		
		}
	
		function departSelect(i) {
			$(opener.document).find('#wh_loc'+opener.selectIdx).val(stocklist[i].WH_LOC_IN_AREA_CD);
			$(opener.document).find('#stock'+opener.selectIdx).val(stocklist[i].wh_name + "_" + stocklist[i].wh_loc_in_area);
			this.close();
		}

	
	
	
</script>
<link href="${pageContext.request.contextPath }/resources/css/in.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div style="width:1200px;">
		<div class="title_regi">창고 검색</div>
		<div class="box">
			<div class="in_box">
				<input type="text" class="note" placeholder="창고명을 입력하세요" name="keyword" id="keyword">
				<input type="button" id="Listbtn2" value="검색" onclick="func()">
			</div>
			<br>
			<table class="detail_table">
				<tr>
					<th width="150">위치명</th>
					<th width="220">구역명</th>
					<th width="80">선택</th>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>