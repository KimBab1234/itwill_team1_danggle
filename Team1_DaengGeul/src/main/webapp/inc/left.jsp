<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<div class="recent-three" style=" display: none; left: 30px; bottom: 30px; position: fixed;">
	<h5 style="font-family: 나눔고딕;">최근본 상품목록</h5>
	<button style="width: 30px">1</button><br>
	<c:forEach begin="1" end="${sessionScope.recentImgList.size()>3? 3:sessionScope.recentImgList.size()}" var="i">
		<a href="ProductDetail.go?product_idx=${sessionScope.recentIdxList.toArray()[sessionScope.recentIdxList.size()-i]}">
			<img src="${sessionScope.recentImgList.toArray()[sessionScope.recentImgList.size()-i]}" style="width: 120px; height: 120px; object-fit: cover;" >
		</a>
		<br>
	</c:forEach>
	<button style="width: 30px">1</button>
</div>
