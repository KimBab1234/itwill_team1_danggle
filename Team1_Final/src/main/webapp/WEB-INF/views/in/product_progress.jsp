<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입고처리 진행상태</title>
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
	var loginEmp = '${sessionScope.empNo}';
	var priv = '${sessionScope.priv}';
	if(loginEmp=='') {
		alert("로그인 후 이용하세요.");
	    location.href="./Login";
	} else if(priv.charAt(3) !='1') {
		alert("권한이 없습니다.");
		history.back();
	}
	
	
	var pdList;
	var i = 0;
	var inList = opener.inList[opener.selectIdx].IN_SCHEDULE_CD;
	
	

	$(function() {		

		$.ajax({
			url: "ProductProgress",
			type: "GET",
			data: {
				keyword: inList
			},
			dataType: "json"
		})
		.done(function(response) {
			pdList =JSON.parse(response.ProgressList);
						
			$(".detail_table_progress").find("tr:gt(0)").remove();
				
				for(var i = 0; i < pdList.length; i++) {
					let result = "<tr>"
								+ "<td>" + pdList[i].PRODUCT_CD + "</td>"
								+ "<td>" + pdList[i].PRODUCT_NAME + "</td>"
								+ "<td>" + pdList[i].IN_SCHEDULE_QTY + "</td>"
								+ "<td>" + (pdList[i].IN_SCHEDULE_QTY - pdList[i].IN_QTY) + "</td>"
								+ "<td>" + pdList[i].IN_PD_COMPLETE + "</td>"
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
	<div style="width:500px;">
		<div class="title_regi">입고처리 진행상태</div>
		<table class="detail_table_progress">
			<tr>
				<th width="90">품목코드</th>
				<th width="150">품목명</th>
				<th width="90">입고예정수량</th>
				<th width="90">미입고수량</th>
				<th width="100">진행 상태</th>
			</tr>
		</table>
		<input type="button" onclick="window.close()" value="닫기" id="Listbtn7">
	</div>
</body>
</html>