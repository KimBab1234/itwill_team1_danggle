<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입고처리 진행상태</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
<style>
*{
	color:#513e30;
	font-family: 'Gowun Dodum', sans-serif;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">
	var empList;
	var i = 0;
	
	$(function() {
		$.ajax({
			url: "ProductProgress",
			type: "GET",
			data: {
				keyword : $("#keyword").val()
			},
			dataType: "json"
		})
		.done(function(response) {
			empList =JSON.parse(response.EmpList);
			if(empList.length == 0){
				alert("검색 결과가 없습니다");
			}else {					
				$(".detail_table").find("tr:gt(0)").remove();
				
				for(var i = 0; i < empList.length; i++) {
					let result = "<tr>"
								+ "<td>" + empList[i].DEPT_NAME + "</td>"
								+ "<td>" + empList[i].EMP_NAME + "</td>"
								+ "<td>" + "<input type='button' id='Listbtn' class='recobtn' value='선택' onclick='departSelect("+i+")'>"
								+ "</tr>";
								
					$(".detail_table").append(result);
				}
			}
		})
		.fail(function() {
			$(".recoTable").append("<h3>요청 실패!</h3>");
		});
	});
	
	
	function departSelect(i) {

		$(opener.document).find('#emp_code').val(empList[i].EMP_NUM);
		$(opener.document).find('#emp_name').val(empList[i].EMP_NAME);
		this.close();
	}

	
	
	
</script>
<link href="${pageContext.request.contextPath }/resources/css/in.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div style="width:1200px;">
		<div class="title_regi">입고처리 진행상태</div>
		<div class="box">
			<table class="detail_table">
				<tr>
					<th width="150">품목코드</th>
					<th width="200">품목명</th>
					<th width="100">입고예정수량</th>
					<th width="100">미입고수량</th>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>