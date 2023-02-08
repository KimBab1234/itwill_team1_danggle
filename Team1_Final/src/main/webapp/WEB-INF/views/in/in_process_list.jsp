<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css">
<style type="text/css">
table{
	border: 1px solid;
}

th{
	border-left: 1px solid;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">
	var proList;
	var i = 0;	
	var selectIdx;

	$(function() {
		
		
		$.ajax({
			url: "in_process_list",
			type: "GET",
			dataType: "json"
		})
		.done(function(response) {
			proList =JSON.parse(response.progressList);			
			$(".bookTable").find("tr:gt(0)").remove();
	
			for(var i = 0; i < proList.length; i++) {

				let result = "<tr>"
							+ "<td><input type='checkbox' value='"+proList[i].IN_PD_SCHEDULE_CD+"' class='check'></td>"
							+ "<td onclick='openUpdate("+i+")' class='td_color'>" +proList[i].IN_PD_SCHEDULE_CD + "</td>"
							+ "<td>" + proList[i].CUST_NAME + "</td>"
							+ "<td>" + proList[i].PRODUCT_NAME + "</td>"
							+ "<td>" + proList[i].IN_PD_DATE + "</td>"
							+ "<td>" + proList[i].IN_SCHEDULE_QTY + "</td>"
							+ "<td>" + proList[i].IN_QTY + "</td>"
							+ "<td>" + (proList[i].IN_SCHEDULE_QTY - proList[i].IN_QTY) + "</td>"
							+ "<td>" + proList[i].IN_PD_REMARKS + "</td>"
							+ "</tr>";
							
				$(".bookTable").append(result);
			}
			
		})
		.fail(function() {
			$(".bookTable").append("<h3>요청 실패!</h3>");
		});
		
		
	});
	
	function openUpdate(product_cd){
		selectIdx = product_cd;
		window.open('ScheduleUpdate', 'schedule_update', 'width=1000, height=700, left=500, top=100');
	}
	
	function incoming(){
		
		window.open('incomingProcess', 'incomingprocess', 'width=1200, height=400, left=500, top=100');
		
	}
</script>
<link href="${pageContext.request.contextPath }/resources/css/in.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<header>
		<jsp:include page="../inc/top.jsp"></jsp:include>
	</header>
	<jsp:include page="../inc/in_left.jsp"></jsp:include>
	<div style="width:1900px;">
		<h3 id="h4">입고 처리</h3>
		<table class="bookTable">
			<tr>
				<th width="40">
					<input type="checkbox">
					<input type="hidden" id="keyword" name="keyword">
				</th>
				<th width="120">입고예정번호</th>
				<th width="105">보낸곳명</th>
				<th width="250">품목명[규격]</th>
				<th width="110">납기일자</th>
				<th width="90">입고예정수량</th>
				<th width="80">입고수량</th>
				<th width="80">미입고수량</th>
				<th width="125">적요</th>
			</tr>
		</table><br>
		<button id="list_recoBtn" onclick="incoming()">입고</button>
	</div>
</body>
</html>