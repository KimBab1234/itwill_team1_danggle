<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 검색</title>
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">
	var empList;
	var i = 0;

		
		
		
		function func(){
		
			$.ajax({
				url: "SearchEmp",
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
									+ "<td>" + empList[i].EMP_NUM + "</td>"
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
		
		
	}
	
		function departSelect(i) {
			alert(empList);
			$(opener.document).find('#emp_code').val(empList[i].EMP_NUM);
			$(opener.document).find('#emp_name').val(empList[i].EMP_NAME);
			this.close();
		}

	
	
	
</script>
<link href="${pageContext.request.contextPath }/resources/css/in.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div style="width:1200px;">
		<div class="title_regi">사원 검색</div>
		<div class="box">
			<div class="in_box">
				<input type="text" class="note" placeholder="사원명을 입력하세요" name="keyword" id="keyword">
				<input type="button" id="Listbtn2" value="검색" onclick="func()">
			</div>
			<br>
			<table class="detail_table">
				<tr>
					<th width="150">사원 번호</th>
					<th width="200">사원명</th>
					<th width="100">선택</th>
				</tr>
			</table>
		</div>
	</div>

</body>
</html>