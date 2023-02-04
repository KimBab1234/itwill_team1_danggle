<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>거래처 검색</title>
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
	
	var accList;
	var i = 0;
		
		function func(){
		
			$.ajax({
				url: "AccSearchPro",
				type: "GET",
				data: {
					keyword : $("#keyword").val()
				},
				dataType: "json"
			})
			.done(function(response) {
				acclist =JSON.parse(response.accList);
				if(acclist.length == 0){
					alert("검색 결과가 없습니다");
				}else {					
					$(".detail_table").find("tr:gt(0)").remove();
					
					for(var i = 0; i < acclist.length; i++) {
						let result = "<tr>"
									+ "<td>" + acclist[i].CUST_NAME + "</td>"
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

			$(opener.document).find('#business_no').val(acclist[i].BUSINESS_NO);
			$(opener.document).find('#business_name').val(acclist[i].CUST_NAME);
			this.close();
		}

	
	
	
</script>
<link href="${pageContext.request.contextPath }/resources/css/in.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div style="width:1200px;">
		<div class="title_regi">거래처 검색</div>
		<div class="box">
			<div class="in_box">
				<input type="text" class="note" placeholder="거래처명을 입력하세요" name="keyword" id="keyword">
				<input type="button" id="Listbtn2" value="검색" onclick="func()">
			</div>
			<br>
			<table class="detail_table">
				<tr>
					<th width="350">거래처명</th>
					<th width="100">선택</th>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>