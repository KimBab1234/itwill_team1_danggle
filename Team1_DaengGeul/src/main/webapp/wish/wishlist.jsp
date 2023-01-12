<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="img/daram.png" rel="shortcut icon" type="image/x-icon">
<title>댕글댕글 : 찜목록</title>
<%------------------- 임시 홈페이지 CSS -------------------%>
<link href="css/memberList.css" rel="stylesheet" type="text/css">
<%---------------------------------------------------------%>


<%------------------------------ 찜목록 삭제 --------------------------------%>
<script src="js/jquery-3.6.3.js"></script>
<script type="text/javascript">

var id = '${sessionScope.sId}';

if(id=='') {
	alert("로그인 후 이용하세요.");
	location.href='MemberLoginForm.me';
}	


	$(function() {
		// ------------------------ 찜전체 선택 ----------------------------
		$("table").css("text-align", "center");
		
		// 전체선택 체크박스 체크 시, 모든 체크박스 체크
		$("#selectedWishAll").on("change", function() {
			if($("#selectedWishAll").is(":checked")) {
				$(":checkbox").each(function(index, item) {
					$(item).prop("checked", true)
				});
			} else {
				$(":checkbox").prop("checked", false);
			}
		});
		// -----------------------------------------------------------------

		
		// ---------------------- 찜목록 전체삭제 --------------------------
		$("#deleteWishAll").on("click", function() {
		  	if(confirm("모든 찜을 취소하시겠습니까?")) {
		  		$.ajax({
					url : "WishDeletePro.ws",
					type : 'POST',
					success: function(wishlistCount){
						if(wishlistCount){
							$("#wishCount").html(wishlistCount);
							alert("찜 취소되었습니다");
							location.replace("Wishlist.ws");
							
						} else {
							alert("찜 취소가 실패했습니다");
						}
					},
					error : function(request, status, error){
						alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					}
				});
		  		
		  	}
		}); // 찜목록 전체삭제 끝
		// -----------------------------------------------------------------
		
		
		// ----------------------- 찜목록 선택삭제 -------------------------
		$("#deleteSelectedWish").on("click", function() {
			
		  	if(confirm("해당 상품(들)의 찜을 취소하시겠습니까?")) {
	  			let listArr = new Array();
		  		let list = $("input[name='selectedwishlist']:checked");
		  		
		  		// ----------- 선택항목들을 배열에 저장 ----------
		  		for(var i = 0; i < list.length; i++){
					if(list[i].checked){
						listArr.push(list[i].value);
// 						alert("배열값 = "+ listArr);
					}
				}
		  		// -----------------------------------------------
		  		
		  		// -----------------------------------------------
		  		// 체크박스 선택없이 삭제 버튼을 누를 시
		  		if(list.length == 0){
			  		alert("취소할 항목을 체크해주세요");
			  		
			  	// 체크박스 선택 시
		  		} else {
		  			
		  			$.ajax({
						url : "WishDeletePro.ws",
						type : 'POST',
						traditional : true,
						data : {
							listArr : listArr
						},
						success: function(wishlistCount){
							if(wishlistCount){
								$("#wishCount").html(wishlistCount);
								alert("찜 취소되었습니다");
								location.replace("Wishlist.ws");
							}else{
								alert("찜 취소가 실패했습니다");
							}
						},
						error : function(request, status, error){
							alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
						}
					});
		  			
		  		}
		  		// -----------------------------------------------
		  		
		  	}
		  	
		}); // 찜목록 선택삭제 끝
		// -----------------------------------------------------------------
		
	});
</script>
<%---------------------------------------------------------------------------%>
</head>
<body>
	<header>
		<!-- Login, Join 링크 표시 영역(inc/top.jsp 페이지 삽입) -->
		<jsp:include page="/inc/top.jsp"></jsp:include>
		<jsp:include page="/inc/main.jsp"></jsp:include>
	</header>
	
	<div style="display: flex; margin-left: 50px; width: 1800px;;">
	<div align="left" style="width: 300px; margin-top: 100px;">
		<jsp:include page="../inc/memberInfo_left.jsp"></jsp:include> <!-- 본문1 -->
	</div>
	<div align="center" class="orderTable" style="width: 1300px; min-height: 500px; margin-left:100px;">
		
	<div id="listName">
		<img src="img/daram.png" width="50" height="75">
		찜목록
	</div>
		<!-------------------------- 찜전체 취소버튼 --------------------------->
		<div style="width: 1300px;" align="left">
			<input type="button" id="deleteSelectedWish" value="선택취소">			
			<input type="button" id="deleteWishAll" value="전체취소">			
		</div>
		<table border="1" style="width: 1300px; text-align: center; margin-top: 20px">
			<tr>
				<th width="80"><input type="checkbox" id="selectedWishAll">&nbsp;전체</th>
				<th width="150">상품이미지</th>
				<th width="300">상품명</th>				
				<th width="100">상품가</th>
				<th width="100">할인가</th>
				<th width="100">찜등록일</th>
			</tr>
			<c:forEach var="wishlist" items="${wishlist }">
				<tr>				
					<td><input type="checkbox" name="selectedwishlist" value="${wishlist.product_idx }"></td>
					<td><a href="ProductDetail.go?product_idx=${wishlist.product_idx }"><img src="img/product/${wishlist.product_real_img}" width="130px" height="130px" style="object-fit: cover;"></a></td>
					<td><a href="ProductDetail.go?product_idx=${wishlist.product_idx }">${wishlist.product_name }</a></td>
					<td>${wishlist.product_price }원</td>
					<td>${wishlist.product_discount}원</td>
					<td>${wishlist.wish_date }</td>
				</tr>
			</c:forEach>
		</table>
		
		
		<!-------------------------- 상품명 검색버튼 --------------------------->
		<section id="buttonArea">
			<form action="Wishlist.ws">
				<input type="text" name="keyword" placeholder="상품명을 입력하세요">
				<input type="submit" id="btnSearch" value="검색">
			</form>
		</section>
		
		<!-------------------------- 페이지 이동버튼 --------------------------->
		<section id="pageList">
			<!-- 이전 페이지 -->
			<c:choose>
				<c:when test="${pageNum > 1}">
					<input type="button" value="이전" id="btnPre" onclick="location.href='Wishlist.ws?pageNum=${pageNum - 1}'">
				</c:when>
				<c:otherwise>
					<input type="button" value="이전" id="btnPre">
				</c:otherwise>
			</c:choose>
				
			<!-- 페이지 번호 목록 -->
			<c:forEach var="i" begin="${memberPageInfo.startPage }" end="${memberPageInfo.endPage }">
				<c:choose>
					<c:when test="${pageNum eq i}">
						${i }
					</c:when>
					<c:otherwise>
						<a href="Wishlist.ws?pageNum=${i }">${i }</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
	
			<!-- 다음 페이지 -->
			<c:choose>
				<c:when test="${pageNum < memberPageInfo.maxPage}">
					<input type="button" value="다음" id="btnNext" onclick="location.href='Wishlist.ws?pageNum=${pageNum + 1}'">
				</c:when>
				<c:otherwise>
					<input type="button" value="다음" id="btnNext">
				</c:otherwise>
			</c:choose>
		</section>
		
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
	    <script src="lib/easing/easing.min.js"></script>
	    <script src="lib/owlcarousel/owl.carousel.min.js"></script>
	
	    <!-- Contact Javascript File -->
	    <script src="mail/jqBootstrapValidation.min.js"></script>
	    <script src="mail/contact.js"></script>
	
	    <!-- Template Javascript -->
	    <script src="js/main.js"></script>
		<!------------------------------ top, left, bottom 동작 관련 작업 빼지말것! ------------------------------>
</body>
</html>