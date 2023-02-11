<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src ="https://code.jquery.com/jquery-3.6.3.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
<style type="text/css">
*{
	color:#513e30;
	font-family: 'Gowun Dodum', sans-serif;
<!-- 폰트 변경 끝  -->
}
</style>
<script>

	var business_no;
	function searchFunc() {
		
		$.ajax({
			url: 'Business_no_Search',
			type: 'POST',
			data: {
				keyword: $("#keyword").val()
			},
			dataType : 'json',
			success : function(response) {
				business_no = response.jsonBusiness_no;
				$("#BUSINESS_NO").html("");
				$("#CUST_NAME").html("");
				$("#business_noSelect").html("");
				$("#searchNone").html("");
				if(business_no.length==0) {
					$("#searchNone").html("<h3>검색 결과가 없습니다.</h3>");
				} else {
					for(var i=0; i<business_no.length; i++) {
						$("#BUSINESS_NO").append("<h3>"+business_no[i].BUSINESS_NO+"</h3>");
						$("#CUST_NAME").append("<h3><a href='javascript:business_noSelect("+i+")'>"+business_no[i].CUST_NAME+"</a></h3>");
					}
				}
				
			}
		});
	}
	
	function business_noSelect(i) {
		$(opener.document).find('#BUSINESS_NO').val(business_no[i].BUSINESS_NO);
		$(opener.document).find('#CUST_NAME').val(business_no[i].CUST_NAME);
		this.close();
	}

</script>
<div align="center" style="width: auto;">
	<h1 id="titleH1"><b style="border-left: 10px solid">&nbsp;거래처 검색</b></h1>
	<form action="javascript:searchFunc()">
	
	<input type="text" name="keyword" id ="keyword" onsubmit="searchFunc()">
	<button type="button" onclick="searchFunc()">검색</button>
	</form>
	<hr>
	<div align="center" id="searchNone">
	</div>
	<div align="center" style="display: flex;" >
		<div id="BUSINESS_NO" style="width: 300px; margin-left: 60px;">
		</div>
		&nbsp;&nbsp;
		<div id="CUST_NAME" style="width: 200px;">
		</div>
	</div>
</div>