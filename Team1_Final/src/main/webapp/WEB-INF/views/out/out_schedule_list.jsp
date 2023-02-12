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

.nav-tabs {
    list-style: none;
    margin-top: -5px;
    margin-left: 210px;
}

.navli {
    float: left;
}

.nav-tabs>li>#outAll {
    background-color: #c9b584;
    color: #736643;
    font-size: 15px;
    font-weight: bold;
    text-decoration: none;
    padding: 4px 10px 5px 11px;
    border-style: solid;
 	border-color: #b09f76;
    border-radius: 10px 10px 0 0;
}
.nav-tabs>li>#outPro, .nav-tabs>li>#outCom {
    background-color: #FFF;
    color: #736643;
    font-size: 15px;
    font-weight: bold;
    text-decoration: none;
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
	
	
	// -------------------- 출고 예정 목록 - 기능 목록 --------------------
	// 출고 예정 목록 조회 기능
	function load_list(keyword){
		$.ajax({
			url: "OutSchListJson",
			type: "POST",
			data : {
				"keyword" : keyword	
			},
			dataType: "json"
		})
		.done(function(response) {
			
			outSchList =JSON.parse(response.searchOutSchList);			
			$("#outListT").find("tr:gt(0)").remove();	
			
			for(var i = 0; i < outSchList.length; i++) {
				let result =
					  "<tr>"
					+ "		<td>" + outSchList[i].OUT_SCHEDULE_CD + "</td>"
					+ "		<td>" + outSchList[i].OUT_TYPE_NAME + "</td>"
					+ "		<td>" + outSchList[i].CUST_NAME + "</td>"
					+ "		<td>" + outSchList[i].EMP_NAME + "</td>"
					+ "		<td>" + outSchList[i].PRODUCT_NAME + "</td>"
					+ "		<td>" + outSchList[i].OUT_DATE + "</td>"
					+ "		<td>" + outSchList[i].TOTAL_QTY + "</td>"
					+ "		<td><a id='com" + i + "' href='javascript:OutCom("+ i +")' class='comColor'>" + outSchList[i].OUT_COMPLETE + "</a></td>"
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
				
				// 종결 / 취소 색상변경
				if(outSchList[i].OUT_COMPLETE == "종결") {
					$("#com" + i).css("color", "#005B9E");
				} else if(outSchList[i].OUT_COMPLETE == "취소") {
					$("#com" + i).css("color", "#FF8F32");
				}
				
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
		window.open('OutEachPd', 'OutEachPd', 'width=510, height=500, left=600, top=400');
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
					+ "		<td>" + outSchList[i].OUT_SCHEDULE_CD + "</td>"
					+ "		<td>" + outSchList[i].OUT_TYPE_NAME + "</td>"
					+ "		<td>" + outSchList[i].CUST_NAME + "</td>"
					+ "		<td>" + outSchList[i].EMP_NAME + "</td>"
					+ "		<td>" + outSchList[i].PRODUCT_NAME + "</td>"
					+ "		<td>" + outSchList[i].OUT_DATE + "</td>"
					+ "		<td>" + outSchList[i].TOTAL_QTY + "</td>"
					+ "		<td><a id='com" + i + "' href='javascript:OutCom("+ i +")' class='comColor'>" + outSchList[i].OUT_COMPLETE + "</a></td>"
					+ "		<td><a href='javascript:OutEachPd("+ i +")'>조회</a></td>"
					+ "</tr>";
					
				$("#outListT").append(result);
				
				// 종결 / 취소 색상변경
				if(outSchList[i].OUT_COMPLETE == "종결") {
					$("#com" + i).css("color", "#005B9E");
				} else if(outSchList[i].OUT_COMPLETE == "취소") {
					$("#com" + i).css("color", "#FF8F32");
				}
				
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
	<jsp:include page="../inc/wms_left.jsp"></jsp:include>
	
	<div align="center" id="outListDiv">
	<h3 id="outTitle">출고 예정</h3>
		<ul class="nav-tabs">
			<li class="navli">
				<a id="outAll" href="#">전체</a>
			</li>
			<li class="navli">
				<a id="outPro" href="#">미완료</a>
			</li>
			<li class="navli">
				<a id="outCom" href="#">완료</a>
			</li>
		</ul>
		<br>
		<table id="outListT">
			<tr>
				<th width="150px">출고예정번호</th>
				<th width="100px">유형</th>
				<th width="200px">받는곳명</th>
				<th width="100px">담당자명</th>
				<th width="500px">품목명[규격]</th>
				<th width="100px">납기일자</th>
				<th width="100px">출고예정수량합계</th>
				<th width="50px">종결여부</th>
				<th width="50px">진행상태</th>
			</tr>
		</table><br>
		<button type="button" onclick="window.open('OutRegist', 'OutRegist', 'width=1220, height=600, left=200, top=300')">등록</button>
	</div>
	
</body>
</html>