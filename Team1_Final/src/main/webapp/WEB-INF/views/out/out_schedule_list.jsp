<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    margin-left: 127px;
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
	var keyword;
	var outSchList;
	var cIndex;
	var pIndex;
	
	// -------------------- 출고 예정 목록 - 기능 목록 --------------------
	// 출고 예정 목록 조회 기능
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
				let result =
					  "<tr>"
					+ "		<td><input type='checkbox'></td>"
					+ "		<td>" + outSchList[i].OUT_SCHEDULE_CD + "</td>"
					+ "		<td>" + outSchList[i].OUT_TYPE_NAME + "</td>"
					+ "		<td>" + outSchList[i].CUST_NAME + "</td>"
					+ "		<td>" + outSchList[i].EMP_NAME + "</td>"
					+ "		<td>" + outSchList[i].PRODUCT_NAME + "</td>"
					+ "		<td>" + outSchList[i].OUT_DATE + "</td>"
					+ "		<td>" + outSchList[i].TOTAL_QTY + "</td>"
					+ "		<td><a href='javascript:OutCom("+ i +")'>" + outSchList[i].OUT_COMPLETE + "</a></td>"
					+ "		<td><a href='javascript:OutEachPd("+ i +")'>조회</a></td>"
					+ "</tr>";
					
					// 가능
// 					if("${outSchList[i].OUT_COMPLETE }" == '0' ) {
// 						result += "<td>종결</td>";
// 					} else {
// 						result += "<td>취소</td>";
// 					}

// 					result += "<td>조회</td>"
// 							+ "</tr>";
				$("#outListT").append(result);
			}
		})
		.fail(function() {
			$("#outListT").append("<h3>요청 실패!</h3>");
		});
		
	}

	
	// 종결 여부 변경
	function OutCom(i){
		cIndex = i;
		window.open('OutCom', 'OutCom', 'width=500, height=300, left=600, top=400');
	}
	
	// 출고 예정 품목 - 조회(개별품목)
	function OutEachPd(i){
		pIndex = i;
		window.open('OutEachPd', 'OutEachPd', 'width=500, height=500, left=600, top=400');
	}
	// --------------------------------------------------------------------
	
	
	// -------------------------- 페이지 로드 전 --------------------------
	$(function() {
		
		$.ajax({
			url: "OutSchListJson",
			type: "POST",
			dataType: "json"
		})
		.done(function(response) {
			
			outSchList =JSON.parse(response.searchOutSchList);			
			$("#outListT").find("tr:gt(0)").remove();	
			
			for(var i = 0; i < outSchList.length; i++) {
				let result =
					  "<tr>"
					+ "		<td><input type='checkbox'></td>"
					+ "		<td>" + outSchList[i].OUT_SCHEDULE_CD + "</td>"
					+ "		<td>" + outSchList[i].OUT_TYPE_NAME + "</td>"
					+ "		<td>" + outSchList[i].CUST_NAME + "</td>"
					+ "		<td>" + outSchList[i].EMP_NAME + "</td>"
					+ "		<td>" + outSchList[i].PRODUCT_NAME + "</td>"
					+ "		<td>" + outSchList[i].OUT_DATE + "</td>"
					+ "		<td>" + outSchList[i].TOTAL_QTY + "</td>"
					+ "		<td><a href='javascript:OutCom("+ i +")'>" + outSchList[i].OUT_COMPLETE + "</a></td>"
					+ "		<td><a href='javascript:OutEachPd("+ i +")'>조회</a></td>"
					+ "</tr>";

				$("#outListT").append(result);
			}
		})
		.fail(function() {
			$("#outListT").append("<h3>요청 실패!</h3>");
		});

		// 페이지 리스트 구분 [ 전체 / 미완료 / 완료 ]
		// 전체
		$("#outAll").click(function() {
			keyword = "";
			$("#outAll").css("background", "#c9b584").css("color", "#736643");
			$("#outPro").css("background", "#fff").css("color", "#736643");
			$("#outCom").css("background", "#fff").css("color", "#736643");
			
			load_list(keyword);
		});
		
		// 미완료
		$("#outPro").click(function() {
			keyword = "0";
			$("#outAll").css("background", "#fff").css("color", "#736643");
			$("#outPro").css("background", "#c9b584").css("color", "#736643");
			$("#outCom").css("background", "#fff").css("color", "#736643");
			
			load_list(keyword);
		});
		
		// 완료
		$("#outCom").click(function() {
			keyword = "1";
			$("#outAll").css("background", "#fff").css("color", "#736643");
			$("#outPro").css("background", "#fff").css("color", "#736643");
			$("#outCom").css("background", "#c9b584").css("color", "#736643");
			
			load_list(keyword);
		});
		
		
			
		// 무한스크롤 기능 구현
// 		$(window).scroll(function() {
// 			let scrollTop = $(window).scrollTop();
// 			let windowHeight = $(window).height();
// 			let documentHeight = $(document).height();
			
// 			if(scrollTop + windowHeight + 1 >= documentHeight){
// 				pageNum++;
// 				load_list(keyword);
// 			}
			
// 		});
		
	});
	// --------------------------------------------------------------------
</script>
</head>
<body>
	<header>
		<jsp:include page="../inc/top.jsp"></jsp:include>
	</header>
	<jsp:include page="../inc/in_left.jsp"></jsp:include>
	
	<div align="center" style="width: 1200px;">
	<h3 id="outTitle">출고 예정</h3>
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
	<button type="button" onclick="window.open('OutRegist', 'OutRegist', 'width=1250, height=600, left=200, top=300')">등록</button>
	</div>
	
</body>
</html>