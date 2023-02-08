<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<%-- <link href="${pageContext.request.contextPath }/resources/css/pd.css" rel="stylesheet" type="text/css" /> --%>
<script type="text/javascript">
	$('#pd_group_bottom_regist').click(function() {
		
	    $.ajax({
	        type: 'POST',
	        url: 'Pd_group_bottom_registPro',
	        data: $('#pd_group_bottom_RegisPro').serialize(),
	        dataType: 'json'
	        	
	    });
	    
	});
</script>
</head>
<body>
		<!-- 여기서부터 본문-->
		<div align="center">
		<h1><b style="border-left: 10px solid">품목 삭제</b></h1>
		<section id="passForm">
		<form action="Pd_deletePro" name="deleteForm" method="post">
			<!-- 입력받지 않은 글번호는 hidden으로 넘겨야함 -->
			<input type="hidden" name="product_cd" value="${param.product_cd }">
			<hr>
			<table>
				<tr>
					<td colspan="2">
						<input type="submit" value="삭제">&nbsp;&nbsp;
						<input type="button" value="돌아가기" onclick="javascript:history.back()">
					</td>
				</tr>
			</table>
		</form>
	</section>
		</div>
</body>
</html>