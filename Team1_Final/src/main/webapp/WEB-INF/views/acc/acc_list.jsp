<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#listForm {
		width: 1024px;
		max-height: 610px;
		margin: auto;
	}
</style>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript">

// 전체선택 버튼 누르면 전체 선택, 취소
	$(function() {
	$("#accAllCheck").on("change", function() {
         if($("#accAllCheck").is(":checked")) {
            $(":checkbox").each(function(index, item) {
               $(item).prop("checked", true)
            });
         } else {
            $(":checkbox").prop("checked", false);
         }
      });
	});

	</script>
</head>
<body>

	<jsp:include page="../inc/top.jsp"></jsp:include>
	<div style="display: flex;">
	<div
		style="width: 300px; margin-top: 0px; margin-right: 0px; border-right: solid 1px; border-color: #BDBDBD;">
		<jsp:include page="../inc/acc_left.jsp"></jsp:include>
	</div>
	
	<form action="AccList">
		<h1>거래처 목록 리스트</h1>
		<select name="accName" id="accName">
			<option id="BUSINESS_NO" value="BUSINESS_NO">거래처코드</option>
			<option id="CUST_NAME" value="CUST_NAME">거래처명</option>
			<option id="UPTAE" value="UPTAE">업태</option>
			<option id="JONGMOK" value="JONGMOK">종목</option>
			<option id="G_GUBUN" value="G_GUBUN">거래처구분</option>
			<option id="BOSS_NAME" value="BOSS_NAME">대표명</option>
			<option id="MAN_NAME" value="MAN_NAME">담당자명</option>
		</select>
		<input type="text" name="keyword" id="keyword" value="${param.keyword }">
			<input type="submit" value="검색">
			<br>
			<table border="1" id="listform" >
				<tr align="center" height="50">
					<td width="80">전체선택<br><input type="checkbox" id="accAllCheck"></td>
					<td width="200" id="">회사명</td>
					<td width="200">사업자번호</td>
					<td width="80" id="g_gubun">분류코드</td>
					<td width="80" id="boss_name">대표명</td>
					<td width="200" id="uptae">업태</td>
					<td width="200" id="jongmok">종목</td>
					<td width="450">주소</td>
					<td width="80">담당자</td>
					<td width="150">담당자이메일</td>
				</tr>
				<c:forEach var="acc" items="${acc }">
				<tr>
					<td><input type="checkbox" id="accCheck"></td>
					<td>${acc.CUST_NAME }</td>
					<c:choose>
					<c:when test="${empty param.pageNum }">
						<c:set var="pageNum" value="1" />
					</c:when>
					<c:otherwise>
						<c:set var="pageNum" value="${param.pageNum }" />
					</c:otherwise>
					</c:choose>
					<td><a href="AccView?BUSINESS_NO=${acc.BUSINESS_NO }">${acc.BUSINESS_NO }</a></td>
					<td>${acc.g_GUBUN }</td>
					<td>${acc.CUST_NAME }</td>
					<td>${acc.UPTAE }</td>
					<td>${acc.JONGMOK }</td>
					<td>${acc.ADDR }</td>
					<td>${acc.MAN_NAME }</td>
					<td>${acc.MAN_EMAIL }</td>
				</tr>
				</c:forEach>
			</table>
			<br>
			<div align="right">
			<input type="button" name="modifyAcc" value="수정" onclick="location.href='AccModify'">&nbsp;
			<input type="button" name="deleteAcc" value="삭제" onclick="location.href='AccDelete'">
			</div>
			<div align="center">
					<section id="pageList">
		<c:choose>
			<c:when test="${pageNum > 1}">
				<input type="button" value="이전" onclick="location.href='BoardList.bo?pageNum=${pageNum - 1}'">
			</c:when>
			<c:otherwise>
				<input type="button" value="이전">
			</c:otherwise>
		</c:choose>
			
		<c:forEach var="i" begin="${pageInfo.startPage }" end="${pageInfo.endPage }">
			<c:choose>
				<c:when test="${pageNum eq i}">
					${i }
				</c:when>
				<c:otherwise>
					<a href="BoardList.bo?pageNum=${i }">${i }</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<c:choose>
			<c:when test="${pageNum < pageInfo.maxPage}">
				<input type="button" value="다음" onclick="location.href='BoardList.bo?pageNum=${pageNum + 1}'">
			</c:when>
			<c:otherwise>
				<input type="button" value="다음">
			</c:otherwise>
		</c:choose>
	</section>
	</div>
		</form>
		

		</div>
</body>
</html>