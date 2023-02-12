<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!---------------------------- 거래처 검색 --------------------------------->
<style>
	*{
		color:#513e30;
		font-family: 'Gowun Dodum', sans-serif;
	}
	th, td {
		text-align: center;
	}
</style>
<script src ="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">
	var pdList;
	
	//---------------------------- 권한 판단 -----------------------------
	var loginEmp = '${sessionScope.empNo}';
	var priv = '${sessionScope.priv}';
	if(loginEmp=='') {
		alert("로그인 후 이용하세요.");
		window.close();
		history.back();
		opener.location.href  = './Login';
	} else if(priv.charAt(1) !='1') {
		alert("권한이 없습니다.");
		window.close();
	}
	// --------------------------------------------------------------------

	
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
					alert("검색 결과가 없습니다");
				} else {
// 					for(var i=0; i< pd.length; i++) {
// 						$("#pdCode").append("<h3>"+pd[i].PRODUCT_CD+"</h3>");
// 						$("#pdName").append("<h3><a href='javascript:pdSelect("+i+")'>"+pd[i].PRODUCT_NAME+"</a></h3>");
// 					}
					$(".detail_table").find("tr:gt(0)").remove();
				
					for(var i = 0; i < pd.length; i++) {
						let result = "<tr>"
									+ "<td>" + pd[i].PRODUCT_CD + "</td>"
									+ "<td><a href='javascript:pdSelect("+i+")'>"+pd[i].PRODUCT_NAME+"</a></td>"
									+ "</tr>";
									
						$(".detail_table").append(result);
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
<link href="${pageContext.request.contextPath }/resources/css/out.css" rel="stylesheet" type="text/css" />
<div align="center">
	<div id="sTitle">품목 검색</div>
	<div class="box">
		<div class="in_box">
			<form action="javascript:searchFunc()">
				<select name="searchType" id="searchType">
					<option value="pd_name" <c:if test="${param.searchType eq 'pd_name'}">selected</c:if>>품목명</option>
					<option value="pd_code" <c:if test="${param.searchType eq 'pd_code'}">selected</c:if>>품목코드</option>
				</select>
				<input type="text" class="note" name="keyword" id ="keyword" >
				<input type="submit" id="Listbtn2" value="검색">
			</form>
		</div>
		<table class="detail_table">
			<tr>
				<th width="150">품목코드</th>
				<th width="200">품목명</th>
			</tr>
		</table>
	</div>
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