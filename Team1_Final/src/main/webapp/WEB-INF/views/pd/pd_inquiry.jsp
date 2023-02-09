<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<style type="text/css">
#titleH1 {
	margin-top: 20px;
	text-align: left;
}
table { 
 	margin: 0 auto;
	border-collapse: collapse;
	border-color: #b09f76;
	text-align: center;
	font-weight: bold;
	border-radius: 10px;
  	box-shadow: 0 0 0 2px #c9b584;
 	} 
#td_top {
	height: 50px;
	font-weight: bold;
	font-size: 20px;
	background: #c9b584; 
 	color: #736643;
}
#b1 {
	background-color: #fff5e6;
	width: 120px;
	height: 50px;
	color: #575754;
	border-radius: 20px;
	border-color: transparent;
	font-weight: bold;
	font-size: 20px;
}
#b2 {
	background-color: #fff5e6;
	width: 120px;
	height: 50px;
	color: #575754;
	border-radius: 20px;
	border-color: transparent;
	font-weight: bold; 
	font-size: 20px;
}
#b3 {
	background-color: #fff5e6;
	width: 150px;
	height: 50px;
	color: #575754;
	border-radius: 20px;
	border-color: transparent;
	font-weight: bold; 
	font-size: 20px;
}

</style>
<script type="text/javascript">

	////로그인 유무 및 권한 확인
	///기본등록(0), 사원조회(1), 사원관리(2), 재고조회(3), 재고관리(4)
	var loginEmp = '${sessionScope.empNo}';
	var priv = '${sessionScope.priv}';
	if(loginEmp=='') {
	   alert("로그인 후 이용하세요.");
	   location.href="./Login";
	} else if(priv.charAt(0) !='1') {
	   alert("권한이 없습니다.");
	   history.back();
	}
	////로그인 유무 및 권한 확인 끝

	let pageNum = 1;
	
	$(function() {
		let searchType = $("#searchType").val();
		let keyword = $("#keyword").val();
		
		load_list(searchType, keyword);
		
		$(window).scroll(function() {
			
			let scrollTop = $(window).scrollTop();
			let windowHeigtht = $(window).height();
			let documentHeight = $(document).height();
			
			if(scrollTop + windowHeigtht + 1 >= documentHeight) {
				pageNum++;
				load_list(searchType, keyword);
			}
			
		});
	});
	
	function load_list(searchType, keyword) {

		$.ajax({
			type: "GET",
// 			url: "BoardListJson.bo?pageNum=" + pageNum,
			url: "PdInquiryJson?pageNum=" + pageNum + "&searchType=" + searchType + "&keyword=" + keyword, 
			dataType: "json"
		})
		.done(function(response) { // 요청 성공 시
			
// 			$("#listForm > table").append(boardList);
			var pdList2 = JSON.parse(response.pdList);
			// JSONArray 객체를 통해 배열 형태로 전달받은 JSON 데이터를
			// 반복문을 통해 하나씩 접근하여 객체 꺼내기
			for(let product of pdList2) {
				// 테이블에 표시할 JSON 데이터 출력문 생성
				// => 출력할 데이터는 board.xxx 형식으로 접근
				let result = "<tr height='100'>"
							+ "<td>" + "<input type='checkbox' class='chkList' value=" + product.PRODUCT_CD +"></td>"
							+ "<td>" + product.PRODUCT_CD + "</td>"
							+ "<td id='subject'>" 
								+ "<a href='PdUpdate?PRODUCT_CD=" + product.PRODUCT_CD + "'>"
								+ product.PRODUCT_NAME + "</a>" + "</td>"
							+ "<td>" + product.PRODUCT_GROUP_BOTTOM_NAME + "</td>"
							+ "<td>" + product.SIZE_DES + "</td>"
							+ "<td>" + product.UNIT + "</td>"
							+ "<td>" + product.BARCODE + "</td>"
							+ "<td>" + product.IN_UNIT_PRICE + "</td>"
							+ "<td>" + product.OUT_UNIT_PRICE + "</td>"
							+ "<td>" + product.PRODUCT_TYPE_NAME + "</td>"
							+ "<td>" + product.BUSINESS_NO + "</td>"
// 							+ "<td>" + product.PRODUCT_IMAGE + "</td>"
							+ "<td>" + "<img width='100px' src='http://itwillbs3.cdn1.cafe24.com/profileImg/" + product.PRODUCT_IMAGE + "'></td>"
							+ "<td>" + product.REMARKS + "</td>"
							+ "</tr>";
				
				// 지정된 위치(table 태그 내부)에 JSON 객체 출력문 추가
				$("#listForm > table").append(result);
			}
		})
		.fail(function() {
			$("#listForm > table").append("<h3>요청 실패!</h3>");
		});
	}
	
	function deleteConfirm() {
		var deleteProdArr ="";
		if(confirm("삭제하시겠습니까?")) {
			var len = $("input[type=checkbox]").length;
			for(var i=1;  i<len; i++) {
				var chk = $("input[type=checkbox]").eq(i).prop("checked");
				var product_cd = $("input[type=checkbox]").eq(i).val();
				if(chk) {
					deleteProdArr += product_cd+",";
				}
			}
			location.href='Pd_deletePro?deleteProdArr='+deleteProdArr;
		}
	}
	
	function allChkFunc(allChk) {
		var allChk = $("#allChkBox").prop("checked");
		for(var i=0; i < $(".chkList").length; i++) {
			$(".chkList").eq(i).prop("checked",allChk);
		}
		
	}
	
</script>
</head>
<body>
	<jsp:include page="../inc/top.jsp"></jsp:include>
	<div style="display: flex; width: 1900px; margin-bottom: 30px;">
		<div style="width: 300px; margin-top: 0px; margin-right: 0px;">
			<jsp:include page="../inc/pd_left.jsp"></jsp:include>
		</div>
		<!-- 게시판 리스트 -->
		<div align="left" style="margin-right: 50px;">
		<h1 id="titleH1"><b style="border-left: 10px solid">&nbsp;품목 조회</b></h1>
		<section id="listForm">
		<section id="buttonArea">
			<form>
				<!-- 검색 타입 추가 -->
				<select name="searchType" id="searchType" style="height: 30px;">
					<option value="PRODUCT_CD" selected="selected" >품목코드</option>
<%-- 					<option value="PRODUCT_NAME" <c:if test="${param.searchType eq 'PRODUCT_NAME'}">selected</c:if>>품목명</option> --%>
				</select>
				<input type="text" name="keyword" id="keyword" value="${param.keyword }" style="height: 30px;">
				<input type="submit" value="검색" style="height: 30px;">
				<input type="button" value="품목등록" id="b1"  style="margin-left: 860px;" onclick="location.href='PdRegist'" />
				&nbsp; | &nbsp;
				<input type="button" value="품목삭제" id="b2" onclick="deleteConfirm()">
				&nbsp; | &nbsp;
				<input type="button" value="품목 전체 삭제" id="b3">
			</form>
		</section>
		<br>
		<table border="1" style="width: 1600px">
			<tr>
				<td id="td_top"><input type="checkbox" id="allChkBox" onclick="allChkFunc()"></td>
				<td id="td_top" style="width: 80px">품목코드</td>
				<td id="td_top" style="width: 150px">품목명</td>
				<td id="td_top" style="width: 120px">품목그룹(소)</td>
				<td id="td_top">규격</td>
				<td id="td_top">단위</td>
				<td id="td_top">바코드</td>
				<td id="td_top">입고단가</td>
				<td id="td_top">출고단가</td>
				<td id="td_top" style="width: 120px">품목타입코드</td>
				<td id="td_top" style="width: 130px">구매거래처코드</td>
				<td id="td_top" style="width: 150px">대표이미지</td>
				<td id="td_top">적요</td>
			</tr>
		</table>
	</section>
		</div>
	</div>
</body>
</html>