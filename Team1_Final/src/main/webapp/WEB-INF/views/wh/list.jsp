<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>창고 목록</title>
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
		background: orange;
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
	</header>
	<!-- 게시판 리스트 -->
	<section id="listForm">
	<h2>창고 목록</h2>
	<section id="buttonArea">
		<form action="WhList">
			<select name="searchType">
				<option value="code" <c:if test="${searchType eq 'code' }">selected</c:if>>창고 코드</option>
				<option value="name" <c:if test="${searchType eq 'name' }">selected</c:if>>창고명</option>
				<option value="code_name" <c:if test="${searchType eq 'code_name' }">selected</c:if>>창고 코드&창고명</option>
			</select>
		<input type="text" name="keyword" value="${param.keyword }">
		<input type="submit" value="검색">
		&nbsp;&nbsp;
		<input type="button" value="글쓰기" onclick="location.href='WhWriteForm'" />
		</form>
	</section>
	<table>
		<tr id="tr_top">
			<td>창고 코드</td>
			<td width="150px">창고명</td>
		</tr>
		<c:forEach var="wh" items="${whList }">
			<tr>
				<td>${wh.WH_CD}</td>
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
				<td id="name">
				
				<a href="WhDetail?WH_CD=${wh.WH_CD}&pageNum=${pageNum}">
				${wh.WH_NAME}
				</a>
				</td>
				
			</tr>
		</c:forEach>
	</table>
	</section>
	
	<section id="pageList">
		<!-- 
		현재 페이지 번호(pageNum)가 1보다 클 경우에만 [이전] 링크 동작
		=> 클릭 시 BoardList.bo 서블릿 주소 요청하면서 
		   현재 페이지 번호(pageNum) - 1 값을 page 파라미터로 전달
		-->
		<c:choose>
			<c:when test="${pageNum > 1}">
				<input type="button" value="이전" onclick="location.href='WhList?pageNum=${pageNum - 1}'">
			</c:when>
			<c:otherwise>
				<input type="button" value="이전">
			</c:otherwise>
		</c:choose>
			
		<!-- 페이지 번호 목록은 시작 페이지(startPage)부터 끝 페이지(endPage) 까지 표시 -->
		<c:forEach var="i" begin="${pageInfo.startPage }" end="${pageInfo.endPage }">
			<!-- 단, 현재 페이지 번호는 링크 없이 표시 -->
			<c:choose>
				<c:when test="${pageNum eq i}">
					${i }
				</c:when>
				<c:otherwise>
					<a href="WhList?pageNum=${i }">${i }</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<!-- 현재 페이지 번호(pageNum)가 총 페이지 수보다 작을 때만 [다음] 링크 동작 -->
		<c:choose>
			<c:when test="${pageNum <pageInfo.maxPage}">
				<input type="button" value="다음" onclick="location.href='WhList?pageNum=${pageNum + 1}'">
			</c:when>
			<c:otherwise>
				<input type="button" value="다음">
			</c:otherwise>
		</c:choose>
	</section>
</body>
</html>