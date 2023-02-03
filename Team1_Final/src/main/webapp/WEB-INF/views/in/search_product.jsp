<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>품목 검색</title>
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

	var proList;
	var i = 0;
		function func(){
			
			$.ajax({
				url: "SearchPro",
				type: "GET",
				data: {
					keyword : $("#keyword").val()
				},
				dataType: "json"
			})
			.done(function(response) {
				proList =JSON.parse(response.productList);
				
				if(proList.length == 0){
					alert("검색 결과가 없습니다");
				}else {					
					
					
					$("#detail").find("tr:gt(0)").remove();
					
					for(var i = 0; i < proList.length; i++) {
						let result = "<tr>"
									+ "<td>" + proList[i].PRODUCT_CD + "</td>"
									+ "<td>" + proList[i].PRODUCT_NAME + "</td>"
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
		var j = 0;
		function departSelect(i) {
			
			for(var j = 0; j < $(opener.document).find(".indexCh").length; j++){
				if($(opener.document).find('.product_cd').eq(j).val() == ''){
					$(opener.document).find('.product_cd').eq(j).val(proList[i].PRODUCT_CD);
					$(opener.document).find('.product_name').eq(j).val(proList[i].PRODUCT_NAME);
					$(opener.document).find('.size_des').eq(j).val(proList[i].SIZE_DES);
					break;
				}
			}
			
			this.close();
		}

	
	
	
</script>
<link href="${pageContext.request.contextPath }/resources/css/in.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div style="width:1200px;">
		<div class="title_regi">품목 검색</div>
		<div class="box">
			<div class="in_box">
				<input type="hidden" id="index">
				<input type="text" class="note" placeholder="품목명을 입력하세요" name="keyword" id="keyword">
				<input type="button" id="Listbtn2" value="검색" onclick="func()">
			</div>
			<br>
			<table class="detail_table">
				<tr>
					<th width="150">품목코드</th>
					<th width="200">품목명</th>
					<th width="100">선택</th>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>