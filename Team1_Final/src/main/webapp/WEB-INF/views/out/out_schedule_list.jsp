<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#outTitle {
	margin-left: 300px;
}

table{
	margin: 0 auto;
	margin-left: 300px;
	border-collapse: collapse;
	border-style: solid;
	border-color: #b09f76;;
	width: 1000px;
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
}

td {
	border-style: solid;
	border-width: 0.5px;
 	border-color: #b09f76;
 	padding: 8px 0px 8px 4px;
 	font-size: 15px;
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
    display: block;
    margin-top: 10px;
    margin-left: 300px;
}

.nav-tabs {
    margin: -5px 0 0 -130px;
    padding: 0 0 0 14px;
    list-style: none;
    width: 1000px;
    clear: both;
}

.navli {
    float: left;
}

.nav-tabs>li>#outAll {
    padding: 0;
    background-color: #c9b584;
    color: #736643;
    font-size: 15px;
    font-weight: bold;
    text-decoration: none;
    display: block;
    padding: 4px 10px 5px 11px;
    border-style: solid;
 	border-color: #b09f76;
    border-radius: 10px 10px 0 0;
}
.nav-tabs>li>#outPro, .nav-tabs>li>#outCom {
    padding: 0;
    background-color: #FFF;
    color: #736643;
    font-size: 15px;
    font-weight: bold;
    text-decoration: none;
    display: block;
    padding: 4px 10px 5px 11px;
    border-style: solid;
 	border-color: #b09f76;
    border-radius: 10px 10px 0 0;
}


</style>

<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">
	var outSchList;
	var i = 0;
	var selectIdx;
	
	function load_list(keyword){
		$.ajax({
			url: "OutSchListJson",
			type: "POST",
			data : keyword,
			dataType: "json"
		})
		.done(function(response) {
			
			outSchList =JSON.parse(response.searchOutSchList);			
			$("#outListT").find("tr:gt(0)").remove();	
			
			for(var i = 0; i < outSchList.length; i++) {
				let result = "<tr>"
							+ "<td><input type='checkbox'></td>"
							+ "<td>" + outSchList[i].OUT_SCHEDULE_CD + "</td>"
							+ "<td>" + outSchList[i].OUT_TYPE_NAME + "</td>"
							+ "<td>" + "</td>"
							+ "<td>" + outSchList[i].EMP_NAME + "</td>"
							+ "<td>" + outSchList[i].PRODUCT_NAME + "</td>"
							+ "<td>" + outSchList[i].OUT_DATE + "</td>"
							+ "<td>" + outSchList[i].TOTAL_QTY + "</td>"
							+ "<td>" + outSchList[i].OUT_COMPLETE + "</td>"
							+ "<td>조회</td>"
							+ "</tr>";
				$("#outListT").append(result);
			}
			
		})
		.fail(function() {
			$("#outListT").append("<h3>요청 실패!</h3>");
		});
		
	}
	
	$(function() {
		let keyword;

		// 전체 / 진행중 / 완료
		$("#outAll").click(function() {
			keyword = "";
			load_list(keyword);
		});
		$("#outPro").click(function() {
			keyword = "0";
			load_list(keyword);
		});
		$("#outCom").click(function() {
			keyword = "1";
			load_list(keyword);
		});
		
		// 첫 목록 페이지
		load_list(keyword);
			
		// 무한스크롤 기능 구현
		$(window).scroll(function() {
			let scrollTop = $(window).scrollTop();
			let windowHeight = $(window).height();
			let documentHeight = $(document).height();
			
			if(scrollTop + windowHeight + 1 >= documentHeight){
				pageNum++;
				load_list(keyword);
			}
			
		});
		
	});
		
	function openWin(product_cd){
		selectIdx = product_cd;
		window.open('ScheduleProgress', 'schedule_progress', 'width=500, height=500, left=750, top=400');
	}
	
	function openCom(product_cd){
		selectIdx = product_cd;
		window.open('ScheduleComplete', 'schedule_complete', 'width=500, height=500, left=750, top=400');
	}
	
</script>
</head>
<body>
	<header>
		<jsp:include page="../inc/top.jsp"></jsp:include>
	</header>
	<jsp:include page="../inc/in_left.jsp"></jsp:include>
	
	<h3 id="outTitle">출고 예정</h3>
	<div align="center">
		<ul class="nav nav-tabs" style="width: 755px;">
			<li class="navli">
				<a id="outAll" href="#">전체</a>
			</li>
			<li class="navli">
				<a id="outPro" href="#">미완료</a>
			</li>
			<li class="navli">
				<a id="outCom" href="#">완료</a>
			</li>
		</ul><br>
		<table id="outListT">
			<tr>
				<th width="50px"><input type="checkbox"></th>
				<th width="300px">출고예정번호</th>
				<th width="300px">유형</th>
				<th width="300px">받는곳명</th>
				<th width="300px">담당자명</th>
				<th width="300px">품목명[규격]</th>
				<th width="300px">납기일자</th>
				<th width="400px">출고예정수량합계</th>
				<th width="200px">종결여부</th>
				<th width="200px">진행상태</th>
			</tr>
		</table>
	</div>
	<button type="button" onclick="window.open('OutRegist', 'OutRegist', 'width=1250, height=600, left=200, top=300')">등록</button>
	
</body>
</html>