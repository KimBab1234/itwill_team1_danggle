<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
<style>
* {
	font-family: 'Gowun Dodum', sans-serif;
	url: @import url('https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap');
}
</style>
<script>
$(function() {
	$('.recent-three').fadeIn(300);
	
	var nowTop = 0;
	$('#recentUp').click(function () {
		nowTop -=120;
	    $('.recentList').animate({scrollTop: nowTop}, 300, 'easeInOutExpo');
	});
	$('#recentDown').click(function () {
		nowTop +=120;
	    $('.recentList').animate({scrollTop: nowTop}, 300, 'easeInOutExpo');
	});
});


</script>
<style>
.recentList::-webkit-scrollbar { 
   width: 0 !important;
   display: none;  
}
</style>
<div class="recent-three" style="display: none; left: 30px; bottom: 30px; position: fixed; background: white; z-index: 1000;" align="center" id="recentDiv">
	<h6 style="font-family: 'Poppins', sans-serif;">최근본 상품목록</h6>
	<button style="width: 120px; background: #513e30; border: none;" id="recentUp"><i class="fas fa-solid fa-chevron-up" style="color: #c9b584;"></i></button><br>
	<div style="height:auto; max-height:360px;  overflow: scroll;" class="recentList">
	<c:forEach begin="1" end="${sessionScope.recentImgList.size()}" var="i">
		<a href="ProductDetail?product_idx=${sessionScope.recentIdxList.toArray()[sessionScope.recentIdxList.size()-i]}">
			<img src="${pageContext.request.contextPath }/resources/${sessionScope.recentImgList.toArray()[sessionScope.recentImgList.size()-i]}" style="width: 120px; height: 120px; object-fit: cover;" >
		</a>
		<br>
	</c:forEach>
	</div>
	<button style="width: 120px; background: #513e30; border: none;" id="recentDown"><i class="fas fa-solid fa-chevron-down" style="color: #c9b584;"></i></button><br>
</div>
