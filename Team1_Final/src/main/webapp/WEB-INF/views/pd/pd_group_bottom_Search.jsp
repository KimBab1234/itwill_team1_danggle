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

	var pd_group_bottom;
	function searchFunc() {
		
		$.ajax({
			url: 'Pd_group_bottom_Search',
			type: 'POST',
			data: {
				keyword: $("#keyword").val()
			},
			dataType : 'json',
			success : function(response) {
				pd_group_bottom = response.jsonPd_group_bottom;
				$("#PRODUCT_GROUP_TOP_CD").html("");
				$("#PRODUCT_GROUP_BOTTOM_CD").html("");
				$("#PRODUCT_GROUP_BOTTOM_NAME").html("");
				$("#pd_group_bottomSelect").html("");
				$("#searchNone").html("");
				if(pd_group_bottom.length==0) {
					$("#searchNone").html("<h3>검색 결과가 없습니다.</h3>");
				} else {
					for(var i=0; i<pd_group_bottom.length; i++) {
						$("#PRODUCT_GROUP_TOP_CD").append("<h3>"+pd_group_bottom[i].PRODUCT_GROUP_TOP_CD+"</h3>");
						$("#PRODUCT_GROUP_BOTTOM_CD").append("<h3>"+pd_group_bottom[i].PRODUCT_GROUP_BOTTOM_CD+"</h3>");
						$("#PRODUCT_GROUP_BOTTOM_NAME").append("<h3><a href='javascript:pd_group_bottomSelect("+i+")'>"+pd_group_bottom[i].PRODUCT_GROUP_BOTTOM_NAME+"</a></h3>");
					}
				}
				
			}
		});
	}
	
	function pd_group_bottomSelect(i) {
		$(opener.document).find('#PRODUCT_GROUP_TOP_CD').val(pd_group_bottom[i].PRODUCT_GROUP_TOP_CD);
		$(opener.document).find('#PRODUCT_GROUP_BOTTOM_CD').val(pd_group_bottom[i].PRODUCT_GROUP_BOTTOM_CD);
		$(opener.document).find('#PRODUCT_GROUP_BOTTOM_NAME').val(pd_group_bottom[i].PRODUCT_GROUP_BOTTOM_NAME);
		this.close();
	}

</script>
<div align="center" style="width: auto;">
	<h1 id="titleH1"><b style="border-left: 10px solid">&nbsp;품목 그룹 검색</b></h1>
	<form action="javascript:searchFunc()">
	
	<input type="text" name="keyword" id ="keyword" onsubmit="searchFunc()">
	<button type="button" onclick="searchFunc()">검색</button>
	</form>
	<hr>
	<div align="center" id="searchNone">
	</div>
	<div align="center" style="display: flex;">
		<div id="PRODUCT_GROUP_TOP_CD" style="width: 100px; margin-left: 130px;">
		</div>
		&nbsp;
		<div id="PRODUCT_GROUP_BOTTOM_CD" style="width: 100px;">
		</div>
		&nbsp;
		<div id="PRODUCT_GROUP_BOTTOM_NAME" style="width: 150px;">
		</div>
	</div>
</div>