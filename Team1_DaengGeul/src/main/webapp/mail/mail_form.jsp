<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    String sId = (String)session.getAttribute("sId");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">
$(function() {
    $('.dropdown-toggle', this).trigger('click').blur();
});
</script>
<link href="css/default.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
<style type="text/css">
* {
   font-family: 'Gowun Dodum', sans-serif;
   url: @import url('https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap');
   }
	#writeForm {
		width: 900px;
		height: 750px;
		border: 1px red;
	}
	h3 {
		text-align: center;
	}
	
	table {
		margin: 0 auto;
		border-collapse: collapse;
		border-style: solid;
		border-color: #b09f76;
		width: 600px;
	}
	
	.td_left {
		width: 110px;
		background: #c9b584;
 		text-align: center; 
 		border-collapse: collapse;
		border: 1px solid black;
	}
	
	.td_right {
		width: 300px;
		background: #c9b584;
		border-collapse: collapse;
		border: 1px solid black;
	}	

	
	#commandCell {
		text-align: center;
	}
	
	#okBtn {
	background-color: #736643;
	border: none;
	cursor: pointer;
	color: #fff;
	height: 45px;
	width: 100px;
	border-radius: 4px;
	margin-top: 50px;
}
</style>
</head>
<body>
<header>
	<jsp:include page="/inc/top.jsp"></jsp:include>
	<jsp:include page="../inc/main.jsp"></jsp:include>
	</header>
	<hr>
	<div style="width: 1920px; display: flex; margin-left: 10px; min-height: 500px;">
		<div style="width: 500px;">
			<jsp:include page="../inc/customer_left.jsp"></jsp:include>
        </div>
	<!-- 왼쪽 메뉴바 세트 끝 -->
	
	<!-- 상단 이미지, 큰 정보 감싸는 곳 -->
		<div style="width: 1000px; margin-left: 20px; ">
	<section id="writeForm">
	<h3><img src ="img/re.gif">&nbsp;&nbsp;문의사항 메일 전송&nbsp;&nbsp;<img src ="img/re.gif"></h3>
<!-- 	<img src ="img/re.gif"> -->
	<form action="MailPro.cu" method="post">
				<input type="hidden" name="sender" value="${sessionScope.sId }">
		<table>
		<tr>
			<td class="td_left"><label for="title">제목</label></td>
			<td class="td_right"><input type="text" name="title" required="required" /></td>
		</tr>
		<tr>
			<td class="td_left"><label for="content">내용</label></td>
			<td class="td_right">
			<textarea id="board_content" name="content" cols="40" rows="15" required="required"></textarea>
			</td>
		</tr>
		
		</table>
			
			
			<section id="commandCell">
			
			<input type="submit"  id="okBtn" value="메일 발송">
			</section>
	</form>
	</section>
	</div>
	</div>
	<footer>
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
	</footer>
</body>
</html>