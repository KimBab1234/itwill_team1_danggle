<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src ="https://code.jquery.com/jquery-3.6.3.js"></script>
<script>

	var pd_group_top;
	function searchFunc() {
		
		$.ajax({
			url: 'Pd_group_top_Search',
			type: 'POST',
			data: {
				keyword: $("#keyword").val()
			},
			dataType : 'json',
			success : function(response) {
				pd_group_top = response.jsonPd_group_top;
				$("#PRODUCT_GROUP_TOP_CD").html("");
				$("#PRODUCT_GROUP_TOP_NAME").html("");
				$("#pd_group_topSelect").html("");
				$("#searchNone").html("");
				if(pd_group_top.length==0) {
					$("#searchNone").html("<h3>검색 결과가 없습니다.</h3>");
				} else {
					for(var i=0; i<pd_group_top.length; i++) {
						$("#PRODUCT_GROUP_TOP_CD").append("<h3>"+pd_group_top[i].PRODUCT_GROUP_TOP_CD+"</h3>");
						$("#PRODUCT_GROUP_TOP_NAME").append("<h3><a href='javascript:pd_group_topSelect("+i+")'>"+pd_group_top[i].PRODUCT_GROUP_TOP_NAME+"</a></h3>");
					}
				}
				
			}
		});
	}
	
	function pd_group_topSelect(i) {
		$(opener.document).find('#PRODUCT_GROUP_TOP_CD').val(pd_group_top[i].PRODUCT_GROUP_TOP_CD);
		$(opener.document).find('#PRODUCT_GROUP_TOP_NAME').val(pd_group_top[i].PRODUCT_GROUP_NAME_NAME);
		this.close();
	}

</script>
<div align="center" style="width: auto;">
	<h1>품목 그룹(대) 검색</h1>
	<form action="javascript:searchFunc()">
	
	<input type="text" name="keyword" id ="keyword" onsubmit="searchFunc()">
	<button type="button" onclick="searchFunc()">검색</button>
	</form>
	<hr>
	<div align="center" id="searchNone">
	</div>
	<div align="center" style="display: flex;" >
		<div id="PRODUCT_GROUP_TOP_CD" style="width: 200px;">
		</div>
		&nbsp;&nbsp;
		<div id="PRODUCT_GROUP_TOP_NAME" style="width: 300px;">
		</div>
	</div>
</div>