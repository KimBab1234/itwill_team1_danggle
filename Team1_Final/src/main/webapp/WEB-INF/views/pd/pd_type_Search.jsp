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

	var pd_type;
	function searchFunc() {
		
		$.ajax({
			url: 'Pd_type_Search',
			type: 'POST',
			data: {
				keyword: $("#keyword").val()
			},
			dataType : 'json',
			success : function(response) {
				pd_type = response.jsonPd_type;
				$("#PRODUCT_TYPE_CD").html("");
				$("#PRODUCT_TYPE_NAME").html("");
				$("#pd_typeSelect").html("");
				$("#searchNone").html("");
				if(pd_type.length==0) {
					$("#searchNone").html("<h3>검색 결과가 없습니다.</h3>");
				} else {
					for(var i=0; i<pd_type.length; i++) {
						$("#PRODUCT_TYPE_CD").append("<h3>"+pd_type[i].PRODUCT_TYPE_CD+"</h3>");
						$("#PRODUCT_TYPE_NAME").append("<h3><a href='javascript:pd_typeSelect("+i+")'>"+pd_type[i].PRODUCT_TYPE_NAME+"</a></h3>");
					}
				}
				
			}
		});
	}
	
	function pd_typeSelect(i) {
		$(opener.document).find('#PRODUCT_TYPE_CD').val(pd_type[i].PRODUCT_TYPE_CD);
		$(opener.document).find('#PRODUCT_TYPE_NAME').val(pd_type[i].PRODUCT_TYPE_NAME);
		this.close();
	}

</script>
<div align="center" style="width: auto;">
	<h1 id="titleH1"><b style="border-left: 10px solid">&nbsp;품목 구분 검색</b></h1>
	<form action="javascript:searchFunc()">
	
	<input type="text" name="keyword" id ="keyword" onsubmit="searchFunc()">
	<button type="button" onclick="searchFunc()">검색</button>
	</form>
	<hr>
	<div align="center" id="searchNone">
	</div>
	<div align="center" style="display: flex;" >
		<div id="PRODUCT_TYPE_CD" style="width: 100px; margin-left: 150px;">
		</div>
		&nbsp;&nbsp;
		<div id="PRODUCT_TYPE_NAME" style="width: 200px;">
		</div>
	</div>
</div>