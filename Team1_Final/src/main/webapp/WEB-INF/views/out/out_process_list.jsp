<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath }/resources/css/out.css" rel="stylesheet" type="text/css" />
<style type="text/css">
#outTitle {
	margin-left: 210px;
}

table{
	margin-left: 250px;
	margin-right: 50px;
	border-collapse: collapse;
	border-style: solid;
	border-color: #b09f76;;
}

th{
	border: 1px solid; 
 	background: #c9b584; 
 	text-align: center; 
 	border-color: #b09f76;
 	color: #736643;
 	font-size: 15px;
 	padding-top: 10px;
 	padding-bottom: 10px;
 	overflow:hidden;
 	white-space:nowrap;
 	text-overflow:ellipsis;
}

td {
	border-style: solid;
	border-width: 0.5px;
 	border-color: #b09f76;
 	padding: 8px 0px 8px 4px;
 	font-size: 15px;
 	overflow:hidden;
 	white-space:nowrap;
 	text-overflow:ellipsis;
}

input{
	text-align: center;
}

td > a {
	color: "blue";
}

button {
	color: #fff;
    background-color: #736643;
    cursor: pointer;
    height: 35px;
    width: 120px;
    font-size: 0.75rem;
    border-radius: 4px;
    border: none;
	margin-left: 500px;
}
</style>

<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">
	var proList;
	var index;

	// ---------------------------- 권한 판단 -----------------------------
	var loginEmp = '${sessionScope.empNo}';
	var priv = '${sessionScope.priv}';
	if(loginEmp=='') {
		alert("로그인 후 이용하세요.");
	    location.href="./Login";
	} else if(priv.charAt(3) !='1') {
		alert("권한이 없습니다.");
		history.back();
	}
	// --------------------------------------------------------------------
	
	
	$(function() {
		
		$.ajax({
			url: "OutProList",
			type: "POST",
			dataType: "json"
		})
		.done(function(response) {
			proList =JSON.parse(response.searchOutProList);			
			$(".outListT").find("tr:gt(0)").remove();
	
			for(var i = 0; i < proList.length; i++) {

				let result = "<tr>"
							+ "<td><input type='checkbox' value='"+proList[i].PD_OUT_SCHEDULE_CD+"' class='check'></td>"
							+ "<td><a href='javascript:outPdUpdate("+ i +")' class='comColor'>" + proList[i].PD_OUT_SCHEDULE_CD + "</a></td>"
							+ "<td>" + proList[i].CUST_NAME + "</td>"
							+ "<td>" + proList[i].SIZE_DES + "</td>"
							+ "<td>" + proList[i].PD_OUT_DATE + "</td>"
							+ "<td>" + proList[i].OUT_SCHEDULE_QTY + "</td>"
							+ "<td>" + proList[i].OUT_QTY + "</td>"
							+ "<td>" + (proList[i].OUT_SCHEDULE_QTY - proList[i].OUT_QTY) + "</td>"
							+ "<td>" + proList[i].PD_REMARKS + "</td>"
							+ "</tr>";
							
				$(".outListT").append(result);
			}
			
		})
		.fail(function() {
			$(".bookTable").append("<h3>요청 실패!</h3>");
		});
		
	});
	
	function outPdUpdate(i){
		index = i;
		window.open('OutSchPdUpdate', 'OutSchPdUpdate', 'width=1250, height=600, left=200, top=300');
	}
	
	function release(){
		if ($(".check").is(":checked") == false) {
			alert("출고 처리할 항목을 선택해주세요!");
			return false;
		}
		window.open('OutConfirmList', 'OutConfirmList', 'width=1250, height=600, left=200, top=300');
	}
	
</script>
</head>
<body>
	<header>
		<jsp:include page="../inc/top.jsp"></jsp:include>
	</header>
	<jsp:include page="../inc/in_left.jsp"></jsp:include>
	
	<div align="center">
	<h3 id="outTitle">출고 처리</h3>
		<table class="outListT">
			<tr>
				<th width="30px"><input type="checkbox"></th>
				<th width="150px">출고예정번호</th>
				<th width="200px">보낸곳명</th>
				<th width="500px">품목명[규격]</th>
				<th width="100px">품목별납기일자</th>
				<th width="80px">출고예정수량</th>
				<th width="80px">미출고수량</th>
				<th width="80px">출고대기수량</th>
				<th width="300px">적요</th>
			</tr>
		</table>
		<button type="button" onclick="release()">출고</button>
	</div>

</body>
</html>