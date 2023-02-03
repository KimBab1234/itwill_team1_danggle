<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#listForm {
		width: 1024px;
		max-height: 610px;
		margin: auto;
	}
</style>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	// 게시물 목록 조회를 AJAX + JSON 으로 처리할 load_list() 함수 정의
	// => 검색타입과 검색어를 파라미터로 지정
		$.ajax({
			type: "GET",
// 			url: "BoardListJson.bo?pageNum=" + pageNum,
			url: "SearchAccList",
			dataType: "json"
		})
		.done(function(AccList) { // 요청 성공 시
// 			$("#listForm > table").append(boardList);
			
			// JSONArray 객체를 통해 배열 형태로 전달받은 JSON 데이터를
			// 반복문을 통해 하나씩 접근하여 객체 꺼내기
			let result="";
			for(let acc of AccList) {
				// 테이블에 표시할 JSON 데이터 출력문 생성
				// => 출력할 데이터는 board.xxx 형식으로 접근
				result += "<tr align='center' height='50'>"
							+ "<td><input type='checkbox' id='accCheck'></td>"
							+ "<td>" + acc.CUST_NAME + "</td>"
// 							+ "<td id='subject'>" 
// 								+ "<a href='BoardDetail.bo?board_num=" + board.board_num + "'>"
							+ "<td><a href='AccView?BUSINESS_NO=" + acc.BUSINESS_NO + "'>"+acc.BUSINESS_NO+"</a></td>"
							+ "<td>"+ acc.g_GUBUN + "</td>"
							+ "<td>" + acc.BOSS_NAME + "</td>"
							+ "<td>" + acc.UPTAE+ "</td>"
							+ "<td>" + acc.JONGMOK + "</td>"
							+ "<td>" + acc.ADDR + "</td>"
							+ "</a>"
							+ "</tr>";
				
				// 지정된 위치(table 태그 내부)에 JSON 객체 출력문 추가
			}
// 			alert(result);
			$("#listform").append(result);
		})
		.fail(function() {
			$("#listform > table").append("<h3>요청 실패!</h3>");
		});
	
// 	$("#accAllCheck").click(function() {
// 		if($("#accAllCheck").is(":checked")){
// 			$("checkbox").each(function(index, item) {
// 				$(item).prop("checked", true);
// 			});
// 		} else {
// 				$(item).prop("checked", false);
// 		}
// 	});
	$(function() {
	$("#accAllCheck").on("change", function() {
         if($("#accAllCheck").is(":checked")) {
            $(":checkbox").each(function(index, item) {
               $(item).prop("checked", true)
            });
         } else {
            $(":checkbox").prop("checked", false);
         }
      });
	});

	</script>
</head>
<body>

	<jsp:include page="../inc/top.jsp"></jsp:include>
	<div style="display: flex;">
	<div
		style="width: 300px; margin-top: 0px; margin-right: 0px; border-right: solid 1px; border-color: #BDBDBD;">
		<jsp:include page="../inc/acc_left.jsp"></jsp:include>
	</div>
	
	<form action="listForm">
		<h1>거래처 목록 리스트</h1>
			<table border="1" id="listform" >
				<tr align="center" height="50">
					<td>전체선택<br><input type="checkbox" id="accAllCheck"></td>
					<td width="200">회사명</td>
					<td width="50">사업자번호</td>
					<td width="80">분류코드</td>
					<td width="80">대표명</td>
					<td width="200">업태</td>
					<td width="200">종목</td>
					<td width="450">주소</td>
				</tr>

			</table>
			<br>
			<div align="right">
			<input type="button" name="modifyAcc" value="수정" onclick="location.href='AccModify'">&nbsp;
			<input type="button" name="deleteAcc" value="삭제" onclick="location.href='AccDelete'">
			</div>
		</form>
		</div>
</body>
</html>