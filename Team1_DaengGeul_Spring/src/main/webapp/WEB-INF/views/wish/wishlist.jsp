<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/resources/img/daram.png" rel="shortcut icon" type="image/x-icon">
<title>댕글댕글 : 찜목록</title>
<%------------------- 임시 홈페이지 CSS -------------------%>
<link href="${pageContext.request.contextPath}/resources/css/memberList.css" rel="stylesheet" type="text/css">
<%---------------------------------------------------------%>


<%------------------------------ 찜목록 삭제 --------------------------------%>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.6.3.js"></script>
<script type="text/javascript">
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
		<jsp:include page="../inc/top.jsp"></jsp:include>
		<jsp:include page="../inc/main.jsp"></jsp:include>
	</header>
	
	<div class="clear"></div>
	
	<div id="wishBody">
	<article>
		<div id="listName">
			<img src="${pageContext.request.contextPath}/resources/img/daram.png" width="50" height="75">
			찜목록
		</div>
			<table border="1">
				<tr>
					<th width="80"><input type="checkbox" id="selectedWishAll">&nbsp;전체</th>
					<th width="150">상품이미지</th>
					<th width="150">상품코드</th>
					<th width="200">상품명</th>				
					<th width="120">상품가</th>
					<th width="120">할인액</th>
					<th width="100">찜등록일</th>
				</tr>
				<c:forEach var="wishlist" items="${wishlist }">
					<tr>				
						<td><input type="checkbox" name="selectedwishlist" value="${wishlist.product_idx }"></td>
						<td>${wishlist.product_real_img }</td>
						<td>${wishlist.product_idx }</td>
						<td>${wishlist.product_name }</td>
						<td>${wishlist.product_price }</td>
						<td>${wishlist.product_discount }</td>
						<td>${wishlist.wish_date }</td>
					</tr>
				</c:forEach>
			</table>
			
			<!-------------------------- 찜전체 취소버튼 --------------------------->
			<section id="buttonArea">
				<input type="button" id="deleteSelectedWish" value="선택취소">			
				<input type="button" id="deleteWishAll" value="전체취소">			
			</section>
			
			<!-------------------------- 상품명 검색버튼 --------------------------->
			<section id="buttonArea">
				<form action="Wishlist.ws">
					<%-- 로그인 시에만 검색 --%>
					<c:if test="${!empty sessionScope.sId }">
						<select name="searchType">
							<option value="name" <c:if test="${param.searchType eq 'name'}">selected</c:if>>상품명</option>
							<option value="code" <c:if test="${param.searchType eq 'code'}">selected</c:if>>상품코드</option>
							<option value="name_code" <c:if test="${param.searchType eq 'name_code'}">selected</c:if>>상품명&상품코드</option>
						</select>
						<input type="text" name="keyword" value="${param.keyword }" placeholder="상품명을 입력하세요">
						<input type="submit" id="btnSearch" value="검색">
					</c:if>
				</form>
			</section>
			
			<!-------------------------- 페이지 이동버튼 --------------------------->
			<section id="pageList">
				<!-- 이전 페이지 -->
				<c:choose>
					<c:when test="${pageNum > 1}">
						<input type="button" value="이전" id="btnPre" onclick="location.href='Wishlist.ws?pageNum=${pageNum - 1}&searchType=${searchType }&keyword=${keyword }'">
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
							<a href="Wishlist.ws?pageNum=${i }&searchType=${searchType }&keyword=${keyword }">${i }</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
		
				<!-- 다음 페이지 -->
				<c:choose>
					<c:when test="${pageNum < memberPageInfo.maxPage}">
						<input type="button" id="btnNext" value="다음" onclick="location.href='Wishlist.ws?pageNum=${pageNum + 1}&searchType=${searchType }&keyword=${keyword }'">
					</c:when>
					<c:otherwise>
						<input type="button" id="btnNext" value="다음">
					</c:otherwise>
				</c:choose>
			</section>
		</article>
		</div>
		
		<div class="clear"></div>
		
		<!------------------------------------ 바닥글 --------------------------------------->
		<footer>
			<jsp:include page="../inc/bottom.jsp"></jsp:include>
		</footer>
		<!----------------------------------------------------------------------------------->
		
		
		<!------------------------------ top, left, bottom 동작 관련 작업 빼지말것! ------------------------------>
		<!-- Back to Top -->
	    <a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>
	
	    <!-- JavaScript Libraries -->
	    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
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