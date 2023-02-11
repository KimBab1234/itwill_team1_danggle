<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!---------------------------- 거래처 검색 --------------------------------->
<script src ="https://code.jquery.com/jquery-3.6.3.js"></script>
<script>
	var acc;
	var i = 0;
	
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
			url: 'AccSearchPro',
			type: 'POST',
			data: {
				keyword: $("#keyword").val(),
				searchType: $("#searchType").val()
			},
			dataType : 'json',
			success : function(response) {
				acc = JSON.parse(response.accList);
				$("#accCode").html("");
				$("#accName").html("");
				$("#AccSelect").html("");
				$("#searchNone").html("");
				if(acc.length == 0) {
					$("#searchNone").html("<h3>검색 결과가 없습니다.</h3>");
				} else {
					for(var i=0; i< acc.length; i++) {
						$("#accCode").append("<h3>"+acc[i].BUSINESS_NO+"</h3>");
						$("#accName").append("<h3><a href='javascript:AccSelect("+i+")'>"+acc[i].CUST_NAME+"</a></h3>");
					}
				}
				
			}
			
			
		});
	}
	
	function AccSelect(i) {
		$(opener.document).find('#acc_code').val(acc[i].BUSINESS_NO);
		$(opener.document).find('#acc_name').val(acc[i].CUST_NAME);
		this.close();
	}

</script>
<!-------------------------------------------------------------------------->
<div align="center" style="width: auto;">
	<h1>거래처 검색</h1>
	<form action="javascript:searchFunc()">
		<select name="searchType" id="searchType">
			<option value="acc_name" <c:if test="${param.searchType eq 'acc_name'}">selected</c:if>>거래처명</option>
			<option value="acc_code" <c:if test="${param.searchType eq 'acc_code'}">selected</c:if>>거래처코드</option>
		</select>
		<input type="text" name="keyword" id ="keyword" onsubmit="searchFunc()">
		<button type="button" onclick="searchFunc()">검색</button>
	</form>
	<hr>
	<div align="center" id="searchNone">
	</div>
	<div align="center" style="display: flex;" >
		<div id="accCode" align="right" style="width: 200px;">
		</div>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<div id="accName" align="left" style="width: 300px;">
		</div>
	</div>
</div>