<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">
	var proList;
	var i = 0;	
	var selectIdx;
	var in_scList;

	var loginEmp = '${sessionScope.empNo}';
	var priv = '${sessionScope.priv}';
	if(loginEmp=='') {
		alert("로그인 후 이용하세요.");
	    location.href="./Login";
	} else if(priv.charAt(3) !='1') {
		alert("권한이 없습니다.");
		history.back();
	}
	
	function sc_status(){
		$.ajax({
			url: "in_process_list_status",
			type: "GET",
			data : {
				keyword : $("#keyword").val()
			},
			dataType: "json"
		})
		.done(function(response) {
			pro_ing_List =JSON.parse(response.in_scList);			
			$(".bookTable").find("tr:gt(0)").remove();
	
			for(var i = 0; i < proList.length; i++) {

				let result = "<tr>"
							+ "<td><input type='checkbox' value='"+pro_ing_List[i].IN_PD_SCHEDULE_CD+"' class='check process_ck'></td>"
							+ "<td onclick='openUpdate("+i+")' class='td_color'>" +pro_ing_List[i].IN_PD_SCHEDULE_CD + "</td>"
							+ "<td>" + pro_ing_List[i].CUST_NAME + "</td>"
							+ "<td>" + pro_ing_List[i].PRODUCT_NAME + "</td>"
							+ "<td>" + pro_ing_List[i].IN_PD_DATE + "</td>"
							+ "<td>" + pro_ing_List[i].IN_SCHEDULE_QTY + "</td>"
							+ "<td>" + pro_ing_List[i].IN_QTY + "</td>"
							+ "<td>" + (pro_ing_List[i].IN_SCHEDULE_QTY - pro_ing_List[i].IN_QTY) + "</td>"
							+ "<td>" + pro_ing_List[i].IN_PD_REMARKS + "</td>"
							+ "</tr>";
							
				$(".bookTable").append(result);
			}
			
		})
		.fail(function() {
			$(".bookTable").append("<h3>요청 실패!</h3>");
		});
		
	}
	
	$(function() {
		
		$("#all_sc").css("background", "#c9b584").css("color", "#736643");
		
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
							+ "<td><input type='checkbox' value='"+proList[i].IN_PD_SCHEDULE_CD+"' class='check process_ck'></td>"
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
		
		$("#sc_ing").on("click", function() {
			$("#sc_ing").css("background", "#c9b584").css("color", "#736643");
			$("#all_sc").css("background", "#736643").css("color", "#c9b584");
			$("#sc_com").css("background", "#736643").css("color", "#c9b584");
			$("#keyword").val("0");
			sc_status()
		});
		
		$("#all_sc").on("click", function() {
			$("#all_sc").css("background", "#c9b584").css("color", "#736643");
			$("#sc_ing").css("background", "#736643").css("color", "#c9b584");
			$("#sc_com").css("background", "#736643").css("color", "#c9b584");
			$("#keyword").val("");
			sc_status()
		});
		
		$("#sc_com").on("click", function() {
			$("#sc_com").css("background", "#c9b584").css("color", "#736643");
			$("#sc_ing").css("background", "#736643").css("color", "#c9b584");
			$("#all_sc").css("background", "#736643").css("color", "#c9b584");
			$("#keyword").val("1");
			sc_status()
		});
		
	});
	
	function openUpdate(product_cd){
		selectIdx = product_cd;
		window.open('ScheduleUpdate', 'schedule_update', 'width=1000, height=700, left=500, top=100');
	}
	
	function incoming(){
		
		if ($(".process_ck").is(":checked")==false) {
			alert("품목은 하나 이상 선택되어야 합니다");
			return false;
		}
		
		window.open('incomingProcess', 'incomingprocess', 'width=1200, height=400, left=500, top=100');
		
		
	}
</script>
<link href="${pageContext.request.contextPath }/resources/css/in.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<header>
		<jsp:include page="../inc/top.jsp"></jsp:include>
	</header>
	<jsp:include page="../inc/wms_left.jsp"></jsp:include>
	<div style="width:1900px;">
		<h3 id="h4">입고 처리</h3>
		<div class="choice_in" id="all_sc">전체</div><div class="choice_in" id="sc_ing">입고예정</div><div class="choice_in" id="sc_com">입고완료</div>
		<table class="bookTable">
			<tr>
				<th width="40">
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