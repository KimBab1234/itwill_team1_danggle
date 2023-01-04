<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Review 게시판</title>
<!-- 외부 css 가져오기 -->
<style>
	#passForm {
		width: 300px;
		margin: auto;
		border: 1px solid;
		text-align: center;
	}
	
	h2 {
		text-align: center;
	}
	
	table {
		width: 300px;
		margin: auto;
		text-align: center;
	}
	
</style>
</head>
<body>
	<header>
		<!-- Login, Join링크 표시 영역  -->
		<jsp:include page="../inc/top.jsp"></jsp:include>
	</header>
	<!-- 게시판 글 삭제 -->
	<h2>리뷰게시판 글 삭제</h2>
	<section id="passForm">
		<form action="ReviewDeletePro.re" name="deleteForm" method="post">
			<!-- 입력받지 않은 글번호는 hidden으로 넘겨야함 -->
			
			<input type="hidden" name="review_idx" value="${param.review_idx }">
			<input type="hidden" name="pageNum" value="${param.pageNum }">
			<table>
				<tr>
					<td><label>리뷰 비밀번호를 작성하세요</label></td>
				</tr>
				<tr>	
					<td><input type="password" name="review_passwd" required="required"></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="삭제">&nbsp;&nbsp;
						<input type="button" value="돌아가기" onclick="javascript:history.back()">
					</td>
				</tr>
			</table>
		</form>
	</section>
</body>
</html>





