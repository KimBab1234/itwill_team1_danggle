<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<style>
a:link {
  color : #736643;
}
a:visited {
  color : #736643;
}
a:hover {
  color : #736643;
}
a:active {
  color : #736643;
}
.menuLink:hover {
	background-color: #f9de4a;
}
</style>
<script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
<div style="margin-left:20px; font-weight:bolder; font-size:24px; background: white; color:#736643; width: 200px;" align="center">
	${sessionScope.sId}님<br>
</div>
<div style="margin-left:20px; background: #F0D264; color:#736643; width: 200px; height: 200px; font-size: 20px;" align="center">
	<c:choose>
		<%-- 관리자 --%>
		<c:when test="${sessionScope.sId eq 'admin'}">
            <div class="menuLink"><a href='ProductRegiForm.ad' style="color: #736643;">신규 상품 등록</a></div>
            <div class="menuLink"><a href='ProductList.ad'>상품 정보 관리</a></div>
            <div class="menuLink"><a href='RecommendBookList.ad'>추천 도서 관리</a></div>
		</c:when>
		<%-- 일반사용자 --%>
		<c:otherwise>
			<br>
            <div class="menuLink"><a  href='MemberInfo.me?id=${sessionScope.sId}'>회원정보수정</a></div>
            <div class="menuLink"><a  href='OrderList.or'>주문내역</a></div>
            <div class="menuLink"><a  href="ReviewList.re">내가 쓴 리뷰</a></div>
            <div class="menuLink"><a  href="#">내 문의글</a></div>
		</c:otherwise>
	</c:choose>
</div>
