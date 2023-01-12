<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>      
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
      <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 목록</title>
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">
$(function() {
    $('.dropdown-toggle', this).trigger('click').blur();
});
</script>
<script>
var id = '${sessionScope.sId}';

if(id=='') {
   alert("로그인 후 이용하세요.");
   location.href='MemberLoginForm.me';
}   

</script>
<link href="css/default.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
<style type="text/css">
* {
   font-family: 'Gowun Dodum', sans-serif;
   url: @import url('https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap');
   }
	#listForm {
		width: 1024px;
		max-height: 610px;
		margin: auto;
	}
	
	h2 {
		text-align: center;
	}
	
	table {
		margin: auto;
		width: 1024px;
	}
	
	#tr_top {
		background: #b09f76;
		text-align: center;
	}
	
	table td {
	}
	
	.subject1 a {
		text-align: left;
		color: brown;
	}
	
	#pageList {
		margin: auto;
		width: 1024px;
		text-align: center;
	}
	
	#emptyArea {
		margin: auto;
		width: 1024px;
		text-align: center;
	}
	
	#buttonArea {
		margin: auto;
		width: 1024px;
		text-align: right;
		margin-top: 10px;
	}
	
	a {
		text-decoration: none;
	}
	
	#okBtn {
	background-color: #736643;
	border: none;
	cursor: pointer;
	color: #fff;
	height: 30px;
	width: 70px;
	border-radius: 4px;
	margin-top: 50px;
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
	<!-- 게시판 리스트 -->
	<section id="listForm">
	
	<h2><img src ="img/re.gif">&nbsp;&nbsp;문의 목록&nbsp;&nbsp;<img src ="img/re.gif"></h2>
	&nbsp;&nbsp;
	<table>
		<tr id="tr_top">
			
			<td>제목</td>
			<td width="150px">작성자</td>
			<th width="150px">날짜</th>
			
			
		</tr>
		
		<c:forEach var="qna" items="${qnaList }">
			<tr>
				
				<c:choose>
					<c:when test="${empty param.pageNum }">
						<c:set var="pageNum" value="1"/>
					</c:when>
					<c:otherwise>
						<c:set var="pageNum" value="${param.pageNum}"/>
					</c:otherwise>
				</c:choose>
				
				
				<td class="subject1">
				<c:if test="${qna.qna_re_lev==0 && qna.qna_re_seq==0 }">
					<c:forEach var="i" begin="1" end="${qna.qna_re_lev }">
						&nbsp;&nbsp;
					
					</c:forEach>
				<img src ="img/dotori.gif">
					
					
				</c:if>
				
				<c:if test="${qna.qna_re_lev>0 }">
					<c:forEach var="i" begin="1" end="${qna.qna_re_lev }">
						&nbsp;&nbsp;
					
					</c:forEach>
				<img src ="img/re.gif">
					
					
				</c:if>
				<a href="QnaDetail.cu?qna_idx=${qna.qna_idx }&pageNum=${pageNum}">
				${qna.qna_subject}
				</a>
				</td>
				
				<c:if test="${qna.member_id == 'admin'}">
					<td align="center">관리자</td>
				</c:if>
				<c:if test="${qna.member_id != 'admin' }">
				<td align="center">${qna.member_id }</td>
				</c:if>
				
				<td align="center">
				<fmt:formatDate value="${qna.qna_date}" pattern="yy-MM-dd"/>
				</td>
				
			</tr>
		</c:forEach>
		
	</table>
	</section>
	<section id="buttonArea">
		<form action="QnaList.cu">
		<input type="text" name="keyword">
		<input type="submit" id="okBtn" value="검색">
		&nbsp;&nbsp;
		<c:if test="${not empty sessionScope.sId }">
		<input type="button"  id="okBtn" value="글쓰기" onclick="location.href='QnaWriteForm.cu'" />
		</c:if>
		</form>
	</section>
	<section id="pageList">
		<c:choose>
			<c:when test="${pageNum > 1}">
				<input type="button" id="okBtn" value="이전" onclick="location.href='QnaList.cu?pageNum=${pageNum - 1}'">
			</c:when>
			<c:otherwise>
				<input type="button" id="okBtn" value="이전">
			</c:otherwise>
		</c:choose>
			&nbsp;&nbsp;
		<!-- 페이지 번호 목록은 시작 페이지(startPage)부터 끝 페이지(endPage) 까지 표시 -->
		<c:forEach var="i" begin="${pageInfo.startPage }" end="${pageInfo.endPage }">
			<!-- 단, 현재 페이지 번호는 링크 없이 표시 -->
			<c:choose>
				<c:when test="${pageNum eq i}">
					${i }
				</c:when>
				<c:otherwise>
					<a href="QnaList.cu?pageNum=${i }">${i }</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		&nbsp;&nbsp;
		<!-- 현재 페이지 번호(pageNum)가 총 페이지 수보다 작을 때만 [다음] 링크 동작 -->
		<c:choose>
			<c:when test="${pageNum <pageInfo.maxPage}">
				<input type="button"  id="okBtn" value="다음" onclick="location.href='QnaList.cu?pageNum=${pageNum + 1}'">
			</c:when>
			<c:otherwise>
				<input type="button"  id="okBtn" value="다음">
			</c:otherwise>
		</c:choose>
	</section>
	</div>
	</div>
	<footer>
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
	</footer>
</body>
</html>













