<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<title>Insert title here</title>
<script>
	$(function() {
		
		$.aax
		
	});


</script>
</head>
<body>
	<jsp:include page="../inc/top.jsp"></jsp:include>
	<div style="display: flex; min-height: 1300px;"  align="center">
		<div style="width: 300px; margin-top: 0px; margin-right: 0px;">
			<jsp:include page="../inc/hr_left.jsp"></jsp:include>
		</div>
		<!-- 여기서부터 본문-->
		<div style="width: 1500px;">
		<h1 align="left" style="text-align: left; margin-left: 100px;">| 사원 조회</h1>
		<div style="display: flex; width: 1300px; text-align: right" align="right">
			<div class="choice" id="choice_book">재직</div><div class="choice" id="choice_goods">휴,퇴직</div>
			<form action="HrList" method="post" style="width: 1400px; margin-bottom:10px; text-align: right;">
				<select name="searchType" style="text-align: center;">
					<option value="">검색 유형</option>
					<option value="EMP_NUM">사번</option>
					<option value="EMP_NAME">사원명</option>
					<option value="DEPT_NAME">부서명</option>
				</select>
				<input type="text" name="keyword">
				<button  class="hrFormBtn"  style="width: 150px; height:30px; font-size:18px;" >사원 검색</button>
			</form>
		</div>
		<table border="1" class="regi_table" style="text-align: center; width: 1300px; font-size: 20px;">
			<tr>
				<th width="150">사진</th>
				<th width="150">사번</th>
				<th width="120">이름</th>
				<th width="150">부서</th>
				<th width="70">직급</th>
				<th width="150">연락처</th>
				<th width="250">E-MAIL</th>
				<th width="200">버튼</th>
			</tr>
		
			<c:forEach items="${empList}" var="emp">
				<tr>
					<td><img src="${pageContext.request.contextPath}/resources/img/${emp.PHOTO}" width="100"></td>
					<td>${emp.EMP_NUM }</td>
					<td>${emp.EMP_NAME }</td>
					<td>${emp.DEPT_NAME }</td>
					<td>${emp.GRADE_NAME }</td>
					<td>${emp.EMP_TEL}</td>
					<td>${emp.EMP_EMAIL }</td>
					<td><button type="button" class="hrFormBtn" style="width: 150px; height:30px;  font-size:18px; " onclick="location.href='HrDetail?empNo=${emp.EMP_NUM}'">상세정보</button>
					<br><button type="button"  class="hrFormBtn" style="width: 150px; height:30px;  font-size:18px; margin-top: 10px;" onclick="location.href='HrEdit?empNo=${emp.EMP_NUM}'">수정</button></td>
				</tr>
			</c:forEach>
		</table>
		<div align="right" style="width: 1300px;">
		<button type="button" class="hrFormBtn" style="width: 150px; height:30px;  font-size:18px; margin-top: 10px;" onclick="location.href='HrRegist'">신규 등록</button>
		</div>
		
		<!-- 페이지 목록 부분 -->
      <section id="pageList">
			<c:choose>
				<c:when test="${pageNum > 1}">
					<a href="ProductList.ad?pageNum=${pageNum - 1}"><i class="fas fa-solid fa-angles-left"></i></a>
				</c:when>
				<c:otherwise>
					<a><i class="fas fa-solid fa-angles-left"></i></a>
				</c:otherwise>
			</c:choose>
			<c:forEach var="i" begin="${pageInfo.startPage }" end="${pageInfo.endPage }">
				<c:choose>
					<c:when test="${pageNum eq i}">
						<div class="here">${i }</div>
					</c:when>
					<c:otherwise>
						<div class="notHere">${i }</div>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${pageNum < pageInfo.maxPage}">
				<a href="ProductList.ad?pageNum=${pageNum + 1}"><i class="fas fa-solid fa-angles-right"></i></a>
				</c:when>
				<c:otherwise>
					<a><i class="fas fa-solid fa-angles-right"></i></a>
				</c:otherwise>
			</c:choose>
		</section> 
        <!-- 페이지 목록 끝-->
		</div>
		<!-- 여기까지 본문-->
	</div>
</body>
</html>
