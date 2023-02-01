<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	
	h2 {
		margin : auto;
		text-align: center;
	}
	
	table {
		margin: auto;
		border-collapse: collapse;
		border-style: solid;
		border-color: #b09f76;
		width: 600px;
	}
	
	.td_left {
		width: 150px;
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
	height: 30px;
	width: 75px;
	border-radius: 4px;
	margin-top: 50px;
}
	
   
 </style>
</head>
<body>
	<header>
		<jsp:include page="../inc/top.jsp"></jsp:include>
		<jsp:include page="../inc/main.jsp"></jsp:include>
	</header>
	<hr>
	<h2><img src="http://itwillbs3.cdn1.cafe24.com/img/re.gif">&nbsp;&nbsp;회원들의 추천목록 작성&nbsp;&nbsp;<img
					src="http://itwillbs3.cdn1.cafe24.com/img/re.gif"></h2>
<div style="width: 1920px; display: flex; margin-left: 10px; min-height: 500px;">
	<div style="width: 630px;" id="c_div">
		<jsp:include page="../inc/community_left.jsp"></jsp:include>
	</div>
	<form action="CommunityWritePro.co" method="post"
		enctype="multipart/form-data" name="boardForm">
		<input type="hidden" name="board_type" value="0"> <input
			type="hidden" name="member_id" value=${sessionScope.sId }>
		<table border="1">
			<tr>
				<td class="td_left">글제목</td>
				<td class="td_right"><input type="text" name="board_subject" size="70" style="border:0 solid black;"></td>
			</tr>
			<tr>
				<td class="td_left">작성자</td>
				<td class="td_right">${sessionScope.sId }</td>
			</tr>
			<tr>
				<td class="td_left">내용</td>
				<td class="td_right"><textarea rows="22" cols="71" name="board_content" style="border:0 solid black;"></textarea></td>
			</tr>
			<tr>
				<td class="td_left">파일</td>
				<td class="td_right"><input type="file" name="board_file"></td>
			</tr>
			<tr>
				<td colspan="2" class="td_left"><input type="submit" value="등록">
					&nbsp;&nbsp; 
					<input type="button" value="취소"
					onclick="location.href='Community0.co?board_type=0'">
				</td>
			</tr>
		</table>
	</form>
	</div>
	<footer>
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
	</footer>
</body>
</html>