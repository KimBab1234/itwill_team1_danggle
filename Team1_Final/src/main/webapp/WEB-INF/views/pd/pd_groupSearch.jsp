<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src ="https://code.jquery.com/jquery-3.6.3.js"></script>
<script>

	var pd_group;
	function searchFunc() {
		$.ajax({
			url: 'Pd_groupSearch',
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
		this.close();
	}

</script>
<div align="center" style="width: auto;">
	<h1>부서 검색</h1>
	<form action="javascript:searchFunc()">
	
	<input type="text" name="keyword" id ="keyword" onsubmit="searchFunc()">
	<button type="button" onclick="searchFunc()">검색</button>
	</form>
	<hr>
	<div align="center" id="searchNone">
	</div>
	<div align="center" style="display: flex;" >
		<div id="departCode" align="right" style="width: 200px;">
		</div>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<div id="departName" align="left" style="width: 300px;">
		</div>
	</div>
</div>