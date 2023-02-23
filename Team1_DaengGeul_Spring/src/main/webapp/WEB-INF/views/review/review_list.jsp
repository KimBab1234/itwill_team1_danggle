<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- EL에서 표기 방식 (날짜 등)을 변경하려면 fmt(format) 라이브러리 필요 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Review 게시판</title>
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
<style type="text/css">

	* {
	font-family: 'Gowun Dodum', sans-serif;
	url: @import url('https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap');
	}
	/* 검색버튼  */
	#s1 {
		background-color: #fff5e6;
		width: 70px;
		height: 30px;
		color: #575754;
		border-radius: 20px;
		border-color: transparent;
		font-weight: bold; 
		font-size: 20px;
	}
	/* 이전 다음 버튼 */
	#s2 {
		background-color: #fff5e6;
		width: 70px;
		height: 30px;
		color: #575754;
		border-radius: 20px;
		border-color: transparent;
		font-weight: bold; 
		font-size: 20px;
	}
	table { 
 		border-color: #b09f76;
 		color:  #575754;
 	} 
	/* 테이블 위쪽 */
 	.td_top { 
 		height: 50px;
 		background: #513e30; 
 		text-align: center; 
 		font-size: 20px;
 		color: #fae37d;
 	} 
 	/* 테이블 아래쪽 전체 */
 	#tr_down {
 		text-align: center;
 		height: 40px;
 	}
 	/* 별점 */
	.rate {
		align: center;
	}
	#myform fieldset{
    	display: inline-block; /* 하위 별점 이미지들이 있는 영역만 자리를 차지함.*/
    	direction: rtl; /* 이모지 순서 반전 */
    	border: 0; /* 필드셋 테두리 제거 */
	}
	#myform input[type=radio]{
	    display: none; /* 라디오박스 감춤 */
	}
	#myform fieldset label{
	    font-size: 1.5em; /* 이모지 크기 */
	    color: transparent; /* 기존 이모지 컬러 제거 */
	    text-shadow: 0 0 0 #ffcc00;
	}
</style>
</head>
<body>
	<header>
		<jsp:include page="../inc/top.jsp"></jsp:include>
		<jsp:include page="../inc/main.jsp"></jsp:include> <!-- 본문1 -->
	</header>
	<div style="display: flex; width: 1800px; margin-left: 50px;">
	<div align="left" style="width: 300px; margin-top: 100px;">
		<jsp:include page="../inc/memberInfo_left.jsp"></jsp:include> <!-- 본문1 -->
	</div>
	<!-- 게시판 리스트 -->
	<div align="center">
	<div style="width: 1300px; margin-top: 50px; min-height: 500px;">
	<section id="listForm">
	<div align="left" style="margin-left:100px;">
		<h2>
		<b style="border-left: 10px solid #795548">&nbsp;&nbsp;내가 쓴 리뷰 목록</b></h2>
	<br>
	<br>
	<form id="myform">
	<table border="1">
		<tr id="tr_top">
			<td width="400" class="td_top">제목</td>
			<td width="150" class="td_top">작성자</td>
			<td width="250" class="td_top">별점</td>
			<td width="150" class="td_top">날짜</td>
			<td width="150" class="td_top"><i class='far fa-thumbs-up'></i>&nbsp;&nbsp;좋아요</td>
			<td width="120" class="td_top">조회수</td>
		</tr>
			<!-- JSTL 과 EL 활용하여 글목록 표시 작업 반복  -->
		<c:forEach var="review" items="${reviewList }">
			<tr id="tr_down">
				<!-- 제목 하이퍼링크(BoardDetail.bo) 연결 -->
				<c:choose>
					<c:when test="${empty param.pageNum }">
						<c:set var="pageNum" value="1"></c:set>
					</c:when>
					<c:otherwise>
						<c:set var="pageNum" value="${param.pageNum }"></c:set>
					</c:otherwise>
				</c:choose>
				<td width="400">
					<a href="ReviewDetail?review_idx=${review.review_idx }&pageNum=${pageNum}&product_idx=${review.product_idx}&mine=Y">
					${review.review_subject }
					</a>
				</td>
				<td>${review.member_id }</td>
				<td>
					<fieldset name="review_score" class="score" id="score">
							<c:forEach var="i" begin="1" end="${review.review_score}">
						        <input type="radio" name="review_score" id="rate" checked="checked"><label for="rate">⭐</label>
							</c:forEach>
					</fieldset>
				<td>
					<!-- JSTL의 fmt 라이브러리 활용해서 날짜 표현 형식 변경 -->
					<fmt:formatDate value="${review.review_date }" pattern="yy-MM-dd"/>
				</td>
				<td>
					<i class='far fa-thumbs-up'></i>&nbsp;&nbsp;${review.review_like_count}개
				</td>
				<td>${review.review_readcount }</td>
			</tr>
		</c:forEach>
	</table>
	</form>
	</div>
	</section>
	<br>
	<div align="right">
	<section id="buttonArea">
		<form action="ReviewList">
		<input type="text" name="keyword">
		<input type="submit" value="검색" id="s1">
		</form>
	</section>
	</div>
	<br>
	<section id="pageList">
		<!-- 
		현재 페이지 번호(pageNum)가 1보다 클 경우에만 [이전] 링크 동작
		=> 클릭 시 BoardList.bo 서블릿 주소 요청하면서 
		   현재 페이지 번호(pageNum) - 1 값을 page 파라미터로 전달
		-->
		<c:choose>
			<c:when test="${pageNum > 1}">
				<input type="button" value="이전" onclick="location.href='ReviewList?pageNum=${pageNum - 1}'" id="s2">
			</c:when>
			<c:otherwise>
				<input type="button" value="이전" id="s2">
			</c:otherwise>
		</c:choose>
			
		<!-- 페이지 번호 목록은 시작 페이지(startPage)부터 끝 페이지(endPage) 까지 표시 -->
		<c:forEach var="i" begin="${pageInfo.startPage }" end="${pageInfo.endPage }">
			<!-- 단, 현재 페이지 번호는 링크 없이 표시 -->
			<c:choose>
				<c:when test="${pageNum eq i}">
					<b style="font-size: 25px">${i }</b>
				</c:when>
				<c:otherwise>
					<a href="ReviewList?pageNum=${i }">${i }</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<!-- 현재 페이지 번호(pageNum)가 총 페이지 수보다 작을 때만 [다음] 링크 동작 -->
		<c:choose>
			<c:when test="${pageNum < pageInfo.maxPage}">
				<input type="button" value="다음" onclick="location.href='ReviewList?pageNum=${pageNum + 1}'" id="s2">
			</c:when>
			<c:otherwise>
				<input type="button" value="다음" id="s2">
			</c:otherwise>
		</c:choose>
	</section>
	</div>
	</div>
	</div>
	<!------------------------------------ 바닥글 --------------------------------------->
	<footer>
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
	</footer>
	<!----------------------------------------------------------------------------------->
	
	<!------------------------------ top, left, bottom 동작 관련 작업 빼지말것! ------------------------------>
	<!-- Back to Top -->
    <a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>

    <!-- JavaScript Libraries -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/lib/easing/easing.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/lib/owlcarousel/owl.carousel.min.js"></script>

    <!-- Contact Javascript File -->
    <script src="${pageContext.request.contextPath }/resources/mail/jqBootstrapValidation.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/mail/contact.js"></script>

    <!-- Template Javascript -->
    <script src="${pageContext.request.contextPath }/resources/js/main.js"></script>
	<!------------------------------ top, left, bottom 동작 관련 작업 빼지말것! ------------------------------>
</body>
</html>