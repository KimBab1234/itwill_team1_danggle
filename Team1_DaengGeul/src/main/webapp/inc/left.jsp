<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?Poppins:wght@400"	rel="stylesheet">
<script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
<script src ="https://code.jquery.com/jquery-3.6.3.js"></script>
<script>
$(function() {
	$('.recent-three').fadeIn(300);
});
</script>
<div class="recent-three" style=" display: none; left: 30px; bottom: 30px; position: fixed; background: white; z-index: 1000;" align="center">
	<h6 style="font-family: 'Poppins', sans-serif;">최근본 상품목록</h6>
	<button style="width: 120px; background: #513e30; border: none;"><i class="fas fa-solid fa-chevron-up" style="color: #c9b584;"></i></button><br>
	<c:forEach begin="1" end="${sessionScope.recentImgList.size()>3? 3:sessionScope.recentImgList.size()}" var="i">
		<a href="ProductDetail.go?product_idx=${sessionScope.recentIdxList.toArray()[sessionScope.recentIdxList.size()-i]}">
			<img src="${sessionScope.recentImgList.toArray()[sessionScope.recentImgList.size()-i]}" style="width: 120px; height: 120px; object-fit: cover;" >
		</a>
		<br>
	</c:forEach>
	<button style="width: 120px; background: #513e30; border: none;"><i class="fas fa-solid fa-chevron-down" style="color: #c9b584;"></i></button><br>
</div>
