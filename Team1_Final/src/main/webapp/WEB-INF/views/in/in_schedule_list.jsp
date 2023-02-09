<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입고예정</title>
<link rel="shortcut icon" href="#">
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">
	var inList;
	var i = 0;	
	var selectIdx;

	
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
			url: "in_schedule_list_status",
			type: "GET",
			data : {
				keyword : $("#keyword").val()
			},
			dataType: "json"
		})
		.done(function(response) {
			inList =JSON.parse(response.in_scList);			
			$(".bookTable").find("tr:gt(0)").remove();	
			for(var i = 0; i < inList.length; i++) {
				let result = "<tr>"
							+ "<td>" + inList[i].IN_SCHEDULE_CD + "</td>"
							+ "<td>" + inList[i].IN_TYPE_NAME + "</td>"
							+ "<td>" + inList[i].CUST_NAME + "</td>"
							+ "<td>" + inList[i].EMP_NAME + "</td>"
							+ "<td>" + inList[i].PRODUCT_NAME + "</td>"
							+ "<td>" + inList[i].IN_DATE + "</td>"
							+ "<td>" + inList[i].TOTAL_QTY + "</td>"
							+ "<td id='tdColor"+i+"' onclick='openCom("+i+")'>" + inList[i].IN_COMPLETE + "</td>"
							+ "<td onclick='openWin("+i+")' class='td_color'>조회</td>"
							+ "</tr>";
							
				$(".bookTable").append(result);
				
				if(inList[i].IN_COMPLETE == '종결'){
					$("#tdColor"+i).css("color", "#176105");
				}else if(inList[i].IN_COMPLETE == '취소'){
					$("#tdColor"+i).css("color", "#c45c00");
				}
			}
			
		})
		.fail(function() {
			$(".bookTable").append("<h3>요청 실패!</h3>");
		});
		
	}
	
	$(function() {
		
		
		
		$("#all_sc").css("background", "#c9b584").css("color", "#736643");
		
		$.ajax({
			url: "in_schedule_list",
			type: "GET",
			dataType: "json"
		})
		.done(function(response) {
			inList =JSON.parse(response.in_scList);			
			$(".bookTable").find("tr:gt(0)").remove();

			for(var i = 0; i < inList.length; i++) {

				let result = "<tr>"
							+ "<td>" +inList[i].IN_SCHEDULE_CD + "</td>"
							+ "<td>" + inList[i].IN_TYPE_NAME + "</td>"
							+ "<td>" + inList[i].CUST_NAME + "</td>"
							+ "<td>" + inList[i].EMP_NAME + "</td>"
							+ "<td>" + inList[i].PRODUCT_NAME + "</td>"
							+ "<td>" + inList[i].IN_DATE + "</td>"
							+ "<td>" + inList[i].TOTAL_QTY + "</td>"
							+ "<td id='tdColor"+i+"' onclick='openCom("+i+")'>" + inList[i].IN_COMPLETE + "</td>"
							+ "<td onclick='openWin("+i+")' class='td_color'>조회</td>"
							+ "</tr>";
				
							
				$(".bookTable").append(result);
				
				if(inList[i].IN_COMPLETE == '종결'){
					$("#tdColor"+i).css("color", "#176105");
				}else if(inList[i].IN_COMPLETE == '취소'){
					$("#tdColor"+i).css("color", "#c45c00");
				}
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
		
	function openWin(product_cd){
		selectIdx = product_cd;
		window.open('ScheduleProgress', 'schedule_progress', 'width=540, height=500, left=750, top=400');
	}
	
	function openCom(product_cd){
		selectIdx = product_cd;
		window.open('ScheduleComplete', 'schedule_complete', 'width=500, height=500, left=750, top=400');
	}

	
	
	
</script>
<link href="${pageContext.request.contextPath }/resources/css/in.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div>
		<header>
			<jsp:include page="../inc/top.jsp"></jsp:include>
		</header>
		<jsp:include page="../inc/in_left.jsp"></jsp:include>
	</div>
	<div style="width:1900px;">
		<h3 id="h4">입고 예정</h3>
		<div class="choice_in" id="all_sc">전체</div><div class="choice_in" id="sc_ing">진행중</div><div class="choice_in" id="sc_com">완료</div>
		<table class="bookTable">
			<tr>
				<th width="120"><input type="hidden" id="keyword" name="keyword">입고예정번호</th>
				<th width="85">유형</th>
				<th width="105">보낸곳명</th>
				<th width="90">담당자명</th>
				<th width="230">품목명[규격]</th>
				<th width="110">납기일자</th>
				<th width="130">입고예정수량합계</th>
				<th width="65">종결여부</th>
				<th width="65">진행상태</th>
			</tr>
		</table><br>
		<button onclick="window.open('IncomingRegistration', 'incomeRegi', 'width=1000, height=700, left=600, top=200')" id="list_recoBtn">신규 등록</button>
	</div>
</body>
</html>