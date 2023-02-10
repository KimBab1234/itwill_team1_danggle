<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 검색</title>
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
	var emp;
	var i = 0;
		
	function func(){
		$.ajax({
			url: "EmpSearchPro",
			type: "POST",
			data: {
				keyword : $("#keyword").val()
			},
			dataType: "json"
		})
		.done(function(response) {
			emp = JSON.parse(response.empList);
			if(emp.length == 0){
				alert("검색 결과가 없습니다");
			} else {					
				$(".detail_table").find("tr:gt(0)").remove();
				
				for(var i = 0; i < emp.length; i++) {
					let result = "<tr>"
								+ "<td>" + emp[i].DEPT_NAME + "</td>"
								+ "<td>" + emp[i].EMP_NAME + "</td>"
								+ "<td>" + "<input type='button' id='Listbtn' class='recobtn' value='선택' onclick='empSelect("+i+")'>"
								+ "</tr>";
								
					$(".detail_table").append(result);
				}
			}
		})
		.fail(function() {
			$(".recoTable").append("<h3>요청 실패!</h3>");
		});
	
	}
	
	function empSelect(i) {
		$(opener.document).find('#emp_code').val(emp[i].EMP_NUM);
		$(opener.document).find('#emp_name').val(emp[i].EMP_NAME);
		this.close();
	}
</script>
<link href="${pageContext.request.contextPath }/resources/css/out.css" rel="stylesheet" type="text/css" />
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
					<th width="150">부서명</th>
					<th width="200">사원명</th>
					<th width="100">선택</th>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>