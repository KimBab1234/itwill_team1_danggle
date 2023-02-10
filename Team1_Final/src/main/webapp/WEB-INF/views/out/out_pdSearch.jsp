<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!---------------------------- 거래처 검색 --------------------------------->
<script src ="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">
	var pdList;
	
	function searchFunc() {
		$.ajax({
			url: 'PdSearchPro',
			type: 'POST',
			data: {
				keyword: $("#keyword").val(),
				searchType: $("#searchType").val()
			},
			dataType : 'json',
			success : function(response) {
				pd =JSON.parse(response.pdList);
				$("#pdCode").html("");
				$("#pdName").html("");
				$("#pdSelect").html("");
				$("#searchNone").html("");
				if(pd.length == 0) {
					$("#searchNone").html("<h3>검색 결과가 없습니다.</h3>");
				} else {
					for(var i=0; i< pd.length; i++) {
						$("#pdCode").append("<h3>"+pd[i].PRODUCT_CD+"</h3>");
						$("#pdName").append("<h3><a href='javascript:pdSelect("+i+")'>"+pd[i].PRODUCT_NAME+"</a></h3>");
					}
				}
				
			}
			
			
		});
	}
	
	var j = 0;
	function pdSelect(i) {
		for(var j = 0; j < $(opener.document).find(".idx").length; j++){
			if($(opener.document).find('.product_cd').eq(j).val() == ''){
				$(opener.document).find('.product_cd').eq(j).val(pd[i].PRODUCT_CD);
				$(opener.document).find('.product_name').eq(j).val(pd[i].PRODUCT_NAME);
				$(opener.document).find('.size_des').eq(j).val(pd[i].SIZE_DES);
				$(opener.document).find('.pd_remarks').eq(j).val(pd[i].REMARKS);
				break;
			}
		}
		this.close();
	}

</script>
<!-------------------------------------------------------------------------->
<div align="center" style="width: auto;">
	<h1>품목 검색</h1>
	<form action="javascript:searchFunc()">
		<select name="searchType" id="searchType">
			<option value="pd_name" <c:if test="${param.searchType eq 'pd_name'}">selected</c:if>>품목명</option>
			<option value="pd_code" <c:if test="${param.searchType eq 'pd_code'}">selected</c:if>>품목코드</option>
		</select>
		<input type="text" name="keyword" id ="keyword" >
		<input type="submit" value="검색">
	</form>
	<hr>
	<div align="center" id="searchNone">
	</div>
	<div align="center" style="display: flex;" >
		<div id="pdCode" align="right" style="width: 200px;">
		</div>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<div id="pdName" align="left" style="width: 300px;">
		</div>
	</div>
</div>