<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>      
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
      <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자주 묻는 질문</title>
<link href="css/default.css" rel="stylesheet" type="text/css">
<style type="text/css">
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
		text-align: center;
	}
	
	#subject {
		text-align: left;
		padding-left: 20px;
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
	<!-- 게시판 리스트 -->
	<section id="listForm">
	
	<h2>자주 묻는 질문</h2>
	&nbsp;&nbsp;
	<table>
		<tr id="tr_top">
			
			<td>제목</td>
			
			<td width="150px">작성자</td>
			</tr>
			<c:forEach var="common" items="${commonList }">
			<tr>
				
				<td id="subject">
				<img src ="img/re.gif">
				<a href="CommonDetail.cu?common_idx=${common.common_idx }&sId=${sId }">
				${common.common_subject}</a>
				</td>
				
				<td>관리자</td>
			</tr>
		
			</c:forEach>	
	</table>
	</section>
	<section id="buttonArea">
		<form action="CommonList.cu">
		
		<c:if test="${sessionScope.sId eq 'admin'}">
		<input type="button" value="글쓰기" onclick="location.href='CommonWriteForm.co'" />
		</c:if>
		</form>
	</section>
	</div>
	</div>
	<footer>
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
	</footer>
</body>
</html>













