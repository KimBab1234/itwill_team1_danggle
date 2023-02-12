<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src ="https://code.jquery.com/jquery-3.6.3.js"></script>
<link href="${pageContext.request.contextPath }/resources/css/stock.css" rel="stylesheet" type="text/css" />
<!-- 폰트 변경 시작  -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
<style>
*{
	color:#513e30;
	font-family: 'Gowun Dodum', sans-serif;
}
</style>
<!-- 폰트 변경 끝  -->
<script>

	var depart;
	function searchFunc() {
		$.ajax({
			url: 'DepartSearch',
			type: 'POST',
			data: {
				keyword: $("#keyword").val()
			},
			dataType : 'json',
			success : function(response) {
				depart = response.jsonDepart;
				$("#departCode").html("");
				$("#departName").html("");
				$("#departSelect").html("");
				$("#searchNone").html("");
				if(depart.length==0) {
					$("#searchNone").html("<h3>검색 결과가 없습니다.</h3>");
				} else {
					for(var i=0; i<depart.length; i++) {
						$("#departCode").append("<h3>"+depart[i].DEPT_CD+"</h3>");
						$("#departName").append("<h3><a href='javascript:departSelect("+i+")'>"+depart[i].DEPT_NAME+"</a></h3>");
					}
				}
				
			}
		});
	}
	
	function departSelect(i) {
		$(opener.document).find('#DEPT_CD').val(depart[i].DEPT_CD);
		$(opener.document).find('#DEPT_NAME').val(depart[i].DEPT_NAME);
		if(opener.nowPage=='HrEdit'){
			var originEmpNo = $(opener.document).find('#EMP_NUM').val();
			$(opener.document).find('#newEMP_NUM').val(depart[i].DEPT_CD+originEmpNo.substr(2));
		}
		this.close();
	}

</script>
<div align="center" style="width: auto;">
	<h1>부서 검색</h1>
	<form action="javascript:searchFunc()">
	
	<input type="text" name="keyword" style="width: 150px; height: 30px;" id ="keyword" onsubmit="searchFunc()">
	<button type="button" class="hrFormBtn" onclick="searchFunc()">검색</button>
	</form>
	<hr>
	<div align="center" id="searchNone">
	</div>
	<div align="center" style="display: flex;" >
		<div id="departCode" align="right" style="width: 200px; font-size: 18px;">
		</div>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<div id="departName" align="left" style="width: 300px; font-size: 18px;">
		</div>
	</div>
</div>