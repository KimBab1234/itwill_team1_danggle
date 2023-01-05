<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 게시판</title>
<link href="css/default.css" rel="stylesheet" type="text/css">
<style type="text/css">
	#replyForm {
		width: 900px;
		height: 750px;
		border: 1px red;
	}
	
	h4 {
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
</style>
</head>
<body>
<header>
		<!-- Login, Join 링크 표시 영역 -->
		<jsp:include page="../inc/top.jsp"></jsp:include>
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
	<!-- 게시판 답글 작성 -->
	<section id="replyForm">
		<h1>게시판 답글 작성</h1>
		<form action="QnaReplyPro.cu" name="boardForm" method="post" enctype="multipart/form-data">
			<!-- 입력받지 않은 글번호, 페이지번호 hidden 속성으로 전달 -->
			
			<input type="hidden" name="qna_idx" value="${qna.qna_idx }" > <!-- 원본글 번호 -->
			<input type="hidden" name="pageNum" value="${param.pageNum }" >
			<!-- 답글 작성에 필요한 정보도 hidden 속성으로 전달 -->
			<input type="hidden" name="qna_re_ref" value="${qna.qna_re_ref }" >
			<input type="hidden" name="qna_re_lev" value="${qna.qna_re_lev }" >
			<input type="hidden" name="qna_re_seq" value="${qna.qna_re_seq }" >
			<table>
				<tr>
					<td class="td_left"><label for="${sessionScope.sId }">글쓴이</label></td>
					<td class="td_right">
						<input type="text" name="sId" required="required" />
					</td>
				</tr>
				
				<tr>
					<td class="td_left"><label for="qna_subject">제목</label></td>
					<td class="td_right">
						<input type="text" name="qna_subject" value="re:${qna.qna_subject}"required="required" />
					</td>
				</tr>
				<tr>
					<td class="td_left"><label for="qna_content">내용</label></td>
					<td class="td_right">
						<textarea id="qna_content" name="qna_content" cols="40" rows="15" required="required">${qna.qna_content}</textarea>
					</td>
				</tr>
			</table>
			&nbsp;&nbsp;
			<section id="commandCell">
				<input type="submit" value="답글등록">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기">&nbsp;&nbsp;
				<input type="button" value="취소" onclick="history.back()">
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








