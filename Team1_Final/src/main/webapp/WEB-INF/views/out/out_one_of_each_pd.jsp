<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>출고처리 진행상태</title>
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
	var eachPdList;
	var outSchCdList = opener.outSchList[opener.pIndex].OUT_SCHEDULE_CD;
	
	$(function() {

		$.ajax({
			url: "OutEachPdPro",
			type: "POST",
			data: {
				outSchCdList: outSchCdList
			},
			dataType: "json"
		})
		.done(function(response) {
			eachPdList =JSON.parse(response.pdList);
						
			$(".detail_table_progress").find("tr:gt(0)").remove();
				
				for(var i = 0; i < eachPdList.length; i++) {
					let result = "<tr>"
								+ "<td>" + eachPdList[i].PRODUCT_CD + "</td>"
								+ "<td>" + eachPdList[i].PRODUCT_NAME + "</td>"
								+ "<td>" + eachPdList[i].OUT_SCHEDULE_QTY + "</td>"
								+ "<td>" + (eachPdList[i].OUT_SCHEDULE_QTY - eachPdList[i].OUT_QTY) + "</td>"
								+ "</tr>";
								
					$(".detail_table_progress").append(result);
				}
			
		})
		.fail(function() {
			$(".detail_table_progress").append("<h3>요청 실패!</h3>");
		});
		
	});
	
</script>
<link href="${pageContext.request.contextPath }/resources/css/in.css" rel="stylesheet" type="text/css" />
</head>
<body>

	<div class="title_regi">출고처리 진행상태</div>
	<table class="detail_table_progress">
		<tr>
			<th width="100">품목코드</th>
			<th width="150">품목명</th>
			<th width="100">출고예정수량</th>
			<th width="100">미출고수량</th>
		</tr>
	</table>
	<input type="button" onclick="window.close()" value="닫기" id="Listbtn">

</body>
</html>