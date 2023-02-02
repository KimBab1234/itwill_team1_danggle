<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
							+ "<td>" + acc.CUST_NAME + "</td>"
// 							+ "<td id='subject'>" 
// 								+ "<a href='BoardDetail.bo?board_num=" + board.board_num + "'>"
// 								+  + "</td>"
							+ "<td>" + acc.BUSINESS_NO + "</td>"
							+ "<td>" + acc.G_GUBUN + "</td>"
							+ "<td>" + acc.BOSS_NAME + "</td>"
							+ "<td>" + acc.UPTAE+ "</td>"
							+ "<td>" + acc.JONGMOK + "</td>"
							+ "<td>" + acc.ADDR + "</td>"
							+ "</tr>";
				
				// 지정된 위치(table 태그 내부)에 JSON 객체 출력문 추가
			}
// 			alert(result);
			$("#listForm").append(result);
		})
		.fail(function() {
			$("#listForm > table").append("<h3>요청 실패!</h3>");
		});
	
	</script>
</head>
<body>


	<jsp:include page="../inc/top.jsp"></jsp:include>
	<div
		style="width: 300px; margin-top: 0px; margin-right: 0px; border-right: solid 1px; border-color: #BDBDBD;">
		<jsp:include page="../inc/acc_left.jsp"></jsp:include>
	</div>
	<section >
		<h1>거래처 목록 리스트</h1>
		<div align="center">
			<table border="1" id="listForm" >
				<tr align="center" height="50">
					<td width="200">회사명</td>
					<td width="50">사업자번호</td>
					<td width="10">분류코드</td>
					<td width="80">대표명</td>
					<td width="200">업태</td>
					<td width="200">종목</td>
					<td width="450">주소</td>
				</tr>
			</table>
		</div>
	</section>
</body>
</html>