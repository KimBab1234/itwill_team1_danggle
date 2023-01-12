<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>      
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
      <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지 목록</title>
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
		text-align: left;
	}
	
	.subject1 a {
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
	width: 75px;
	border-radius: 4px;
	margin-top: 50px;
}
</style>
</head>
<body>
	<header>
		<jsp:include page="../inc/top.jsp"></jsp:include>
		<jsp:include page="../inc/main.jsp"></jsp:include> <!-- 본문1 -->
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
	<h2><img src ="img/re.gif">&nbsp;&nbsp;공지사항&nbsp;&nbsp;<img src ="img/re.gif"></h2>
	&nbsp;&nbsp;
	<table>
		<tr id="tr_top">
			
			<td>제목</td>
			<td width="150px">작성자</td>
			<td width="150px">날짜</td>
			<td width="100px">조회수</td>
			
		</tr>
		<c:forEach var="notice" items="${noticeList }">
			<tr>
				
				<c:choose>
					<c:when test="${empty param.pageNum }">
						<c:set var="pageNum" value="1"/>
					</c:when>
					<c:otherwise>
						<c:set var="pageNum" value="${param.pageNum}"/>
					</c:otherwise>
				</c:choose>
<%-- 				<c:if test="${empty param.pageNum }"> --%>
<%-- 					<c:set var="pageNum" value="1" /> --%>
<%-- 				</c:if> --%>
				<td class="subject1">
				<img src ="img/re.gif">
					
					
				
				<a href="NoticeDetail.ad?notice_idx=${notice.notice_idx }&pageNum=${pageNum}">
				${notice.notice_subject}
				</a>
				</td>
				<td>관리자</td>
				<td>
				<fmt:formatDate value="${notice.notice_date}" pattern="yy-MM-dd"/>
				
				</td>
				<td>${notice.notice_readcount}</td>
			</tr>
		</c:forEach>
	</table>
	</section>
	<section id="buttonArea">
		<form action="NoticeList.ad">
		<input type="text" name="keyword">
		<input type="submit" id="okBtn" value="검색">
		&nbsp;&nbsp;
		<c:if test="${sessionScope.sId eq 'admin' }">
		<input type="button" id="okBtn" value="글쓰기" onclick="location.href='NoticeWriteForm.ad'" />
		</c:if>
		</form>
	</section>
	<section id="pageList">
		<!-- 
		현재 페이지 번호(pageNum)가 1보다 클 경우에만 [이전] 링크 동작
		=> 클릭 시 BoardList.bo 서블릿 주소 요청하면서 
		   현재 페이지 번호(pageNum) - 1 값을 page 파라미터로 전달
		-->
		<c:choose>
			<c:when test="${pageNum > 1}">
				<input type="button" id="okBtn" value="이전" onclick="location.href='NoticeList.ad?pageNum=${pageNum - 1}'">
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
					<a href="NoticeList.ad?pageNum=${i }">${i }</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		&nbsp;&nbsp;
		<!-- 현재 페이지 번호(pageNum)가 총 페이지 수보다 작을 때만 [다음] 링크 동작 -->
		<c:choose>
			<c:when test="${pageNum <pageInfo.maxPage}">
				<input type="button" id="okBtn" value="다음" onclick="location.href='NoticeList.ad?pageNum=${pageNum + 1}'">
			</c:when>
			<c:otherwise>
				<input type="button" id="okBtn" value="다음">
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













