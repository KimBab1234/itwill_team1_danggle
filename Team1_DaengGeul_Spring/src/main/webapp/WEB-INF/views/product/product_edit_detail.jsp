<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 수정 확인</title>
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<link href="img/dot_daram.gif" rel="shortcut icon" type="image/x-icon">
<script type="text/javascript">
<%	String sId = (String)session.getAttribute("sId");
 if(sId == null || !sId.equals("admin")) { %>  
	alert("잘못된 접근입니다");
	history.back();
<%  }%>

	$(function() {
	    $('.dropdown-toggle', this).trigger('click').blur();
	});
</script>
<link href="${pageContext.request.contextPath }/resources/css/product.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<header>
		<jsp:include page="../inc/top.jsp" />
		<jsp:include page="../inc/main.jsp"/>
	</header>
	<div class="recoArea" style="width: 1800px; margin-left: 50px;">
	<div align="left" style="width: 300px; margin-top: 100px;">
		<jsp:include page="../inc/memberInfo_left.jsp"></jsp:include> <!-- 본문1 -->
	</div>
	<div style="width : 1200px; ">
		<h4 id="h4">상품 수정 확인</h4><br>
			<table class="detail_table">
				<tr>
					<th>상품명</th>
					<td>
						<input type="text" value="${product.name }" readonly="readonly">
					</td>
				</tr>
				<c:if test="${productType eq 'book'}">
					<tr>
						<th>카테고리</th>
						<td><input type="text" id="cateSelect" readonly="readonly"></td>	
					</tr>
					<tr>
						<th>책 정보</th>
						<td>
							<div class="bookArea">&nbsp;작가명 : <input type="text" value="${product.book_writer}" readonly="readonly"></div>
							<div class="bookArea">&nbsp;출판사 : <input type="text" value="${product.book_publisher }" readonly="readonly"></div>
							<div class="bookArea">&nbsp;출판일 : <input type="text"  value="${product.book_date }" readonly="readonly"></div>
						</td>
					</tr>
				</c:if>
				<c:if test="${productType eq 'goods'}">
					<tr>
						<th>옵션</th>
						<td>
							<c:if test="${product.option_name.size() ne 0}">
								<c:forEach var="i" begin="0" end="${product.option_name.size() -1 }">
									&nbsp;옵션명 : <input type="text" value="${product.option_name.get(i)}" id="optionDetail" readonly="readonly">
									수량 : <input type="text" value="${product.option_qauntity.get(i)}" id="optionDetail" readonly="readonly"><br>
								</c:forEach>
							</c:if>
						</td>
					</tr>
				</c:if>
				<tr>
					<th>판매가</th>
					<td>
						<input type="text" value="${product.price }원" readonly="readonly">
					</td>
				</tr>
				<tr>
					<th>할인</th>
					<td>
						<c:choose>
							<c:when test="${product.discount eq 0}">
								<input type="text" value="할인 설정 안함" readonly="readonly">
							</c:when>
							<c:when test="${product.discount ne 0}">
								<input type="text" value="${product.discount }% 할인 설정" readonly="readonly">
							</c:when>
						</c:choose>
					</td>
				</tr>
				<tr>
					<th>재고</th>
					<td><input type="text" required="required" name="quantity" value="${product.quantity }" readonly="readonly"></td>
				</tr>
				<tr>
					<th>상품 대표 이미지</th>
					<td>
	   					<input type="text"value="${product.img }" readonly="readonly">
					</td>
				</tr>
				<tr>
					<th>상세 설명</th>
					<td>
						&nbsp;상세 이미지 : <input type="text"value="${product.detail_img }" readonly="readonly">
						<hr>
						&nbsp;상세 설명 : <br><br> <textarea readonly="readonly">${product.detail}</textarea>
					</td>
				</tr>
			</table> 
			<br>
			<div align="center">
				<c:choose>
					<c:when test="${productType eq 'goods'}">
						<input type="submit" id="okBtn" value="다시 수정" onclick="location.href='ProductEditForm?product_idx=${product.product_idx}'">
						<input type="button" id="okBtn" value="목록" onclick="location.href='ProductRegistrationList?product=G'">
					</c:when>
					<c:when test="${productType eq 'book'}">
						<input type="submit" id="okBtn" value="다시 수정" onclick="location.href='ProductEditForm?product_idx=${product.product_idx}&pageNum=${param.pageNum }'">
						<input type="button" id="okBtn" value="목록" onclick="location.href='ProductRegistrationList?pageNum=${param.pageNum }'">
					</c:when>
				</c:choose>
			</div>
		</div>
	</div>

	<%-- 자바스크립트 영역 --%>
	<script type="text/javascript"> 
		
		// 책 카테고리 input 박스에 넣어서 출력
		var selectName = ["인문", "소설", "시", "역사", "종교", "사회", "과학", "자기계발", "어린이", "건강", "참고서"];
		var selectVal = ["humanities", "novel", "poem", "history", "religion", "society", "science", "self_improvement", "kids", "health", "reference"];
		let ori_genre = '<c:out value="${product.book_genre}"/>';
		
		for(var i = 0; i < selectName.length; i++){
			if(ori_genre == selectVal[i]){
				document.getElementById("cateSelect").value= selectName[i];
			}
		}
		
	</script>
	<footer>
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
	</footer>
</body>
</html>