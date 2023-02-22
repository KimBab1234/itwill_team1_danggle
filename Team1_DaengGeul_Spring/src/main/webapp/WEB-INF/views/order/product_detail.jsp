<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 	<title>상품 상세페이지</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Free HTML Templates" name="keywords">
    <meta content="Free HTML Templates" name="description">

    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath }/resources/css/default_order.css" rel="stylesheet" type="text/css">
<script src ="https://code.jquery.com/jquery-3.6.3.js"></script>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
<style>
* {
	font-family: 'Gowun Dodum', sans-serif;
	font-weight: bold;
	url: @import url('https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap');
	}
#alert-box {
    z-index: 1;
    position: absolute;
    background-color: #513e30;
    border-radius: 15px;
    width:300px;
    height: 50px;
    box-shadow: 0 2px 55px -25px rgb(0 0 0 / 100%);
    text-align: center;
    margin-top: 10px;
    color:#F0D264;
    font-weight: bolder;
    vertical-align: middle;
    display: none;
    line-height: 50px;
} 
button {
  color: #F0D264;
  background-color:#513e30;
}
button:hover {
  background-color: #F0D264;
  color:#513e30;
}
button i{
  color: #F0D264;
  background-color:none;
}
button:hover i{
  color:#513e30;
}
</style>
<script>


Map.prototype.toJSON = function toJSON() {
	  return [...Map.prototype.entries.call(this)];
	}

var id = '${sessionScope.sId}';
// localStorage.clear();
var cart = new Map(JSON.parse(localStorage.getItem(id)));

$(function() {
	$('.recent-three').fadeIn(300);
	
	if('${product.product_idx.substring(0,1)}'=="G") {
		 $("#detail").css("display", "none");
	} else {
		$("#goods_detail").css("display", "none");
	}
	

	// --------------------- 찜등록 전 확인알림 ---------------------
	$("#wish").on("click", function() {
		let product_name = '${product.name}'; // 추후 $("#product.product_name" ).val() 로 변경
		if(id=='') {
			alert("로그인 후 이용하세요.");
			location.href='MemberLoginForm';
		} else {
			if(confirm(product_name + " 상품을 찜하시겠습니까?")){
				$.ajax({
					type: "post",
					url: "WishPro.ws",
					dataType: "text", 
					data: {
						product_idx:$("#product_idx").val()
					},
					success: function(response) {
						$("#wishCount").text(response);
					},
					error: function(xhr, textStatus, errorThrown) { 
						alert("장바구니 추가 실패!");
					}
				});
				
			}
		}
		
	});
	
	
	// --------------------- 장바구니 추가 ---------------------
	$("#cartAddBtn").on("click", function() {
		if(id=='') {
			alert("로그인 후 이용하세요.");
			location.href='MemberLoginForm';
		} else {
			var product_idx = '${product.product_idx}'+ "_opt_" +$('#opt').val();
			var optionVal = $('#opt').val();
			if(optionVal=="") {
				alert("옵션을 선택하세요!");
				return;
			}
			$("#alert-box").css("display","block");
			setTimeout(() => {
				$('#alert-box').fadeOut(500);
			 }, 1000)
			var prod = {
					'opt' :  $('#opt').val(),
					'price' :  $('#price').val(),
					'img' :  $('#img').val(),
					'name' : '${product.name}',
					'count' : $('#count').val()
					};
			if(cart.size==0) {
				cart.set(product_idx, new Map(Object.entries(prod)));
			} else {
				if(cart.has(product_idx)) {
					const cartProd = new Map(cart.get(product_idx));
					cartProd.set('count', Number(cartProd.get('count'))+Number($("#count").val()));
					cart.set(product_idx, cartProd);
				} else {
					cart.set(product_idx, new Map(Object.entries(prod)));
				}
			}
			localStorage.setItem(id, JSON.stringify(cart));
			$("#cartBadge").text(cart.size);
		}
	});
	
	
	// --------------------- 리뷰 게시판 ---------------------
	$(".review_page").on("click", function() {
		var pageNum = 1;
		if($(this).val()!='') {
			pageNum = $(this).val();
		}
		$.ajax({
			type: "post",
			url: "ReviewList.re",
			dataType: "text", 
			data: {
				product_idx: '${product.product_idx}',
				pageNum: pageNum
			},
			success: function(response) {
				$("#product-review-area").html(response);
			},
			error: function(xhr, textStatus, errorThrown) { 
				alert("리뷰 목록 불러오기 실패!");
			}
		});

	});


	
});

function countModify(sign) {
	var num = document.getElementById('count');
	if(sign=='+') {
		num.value++;
	} else if(sign=='-') {
		if(num.value>1) {
			num.value--;
		}
	}
}	
</script>
</head>
<body>
	<header>
		<jsp:include page="../inc/top.jsp"></jsp:include>
		<jsp:include page="../inc/main.jsp"></jsp:include> <!-- 본문1 -->
	</header>
	
	<hr>
	<div style="width: 1900px; display: flex; margin-left: 30px; min-height: 500px;">
		<div style="width: 200px;">
			<jsp:include page="../inc/product_left.jsp"></jsp:include>
        </div>
	<!-- 왼쪽 메뉴바 세트 끝 -->
	
	<!-- 상단 이미지, 큰 정보 감싸는 곳 -->
		<div style="width: 1500px; margin-left: 50px;">
			<form action="CartAdd.or" method= "post" style="display: flex; align-items: center;">
				<img src="https://itwill220823team1.s3.ap-northeast-2.amazonaws.com/img/product/${img}" style="width: 400px; margin-left: 200px">
				<div class="mb-4 pt-2" style="text-align: left; width: 500px; align-content: center;  margin-left: 50px">
					<input type="hidden" id="product_idx" value="${product.product_idx}">
					<input type="hidden" id="price" value="${product.dis_price}">
					<input type="hidden" id="img" value="${img}">
					<input type="hidden" id="name" value="${product.name}">
					<span style="font-size: 55px;">${product.name}</span>
					<hr>
					<div id="detail">
						장르 : ${product.genre}
						<hr>
						작가 : ${product.writer} | 출판사 : ${product.publisher}
						<hr>
						출간일 : ${product.date}<br>
						<hr>
					</div>
					월간 베스트 순위 : ${product.rank}위
					<hr>
					정가: ${product.price }원 <br>
					할인가: <span style="font-size: 2em; font: bold; color: #f4511e;">${product.dis_price }</span>원
					(${product.discount }% 할인 적용)
					<br>
					
					<c:choose>
						<c:when test="${product.quantity<=0}">
							<div style="display: flex; width: 500px; margin-top: 20px;">
								<span style="width: 400px; font-size: 30px;">
									품절되었습니다.
								</span>
							</div>
						</c:when>
						<c:otherwise>
							<!--장바구니부분 -->
							<div class="input-group mr-3" style="width: 500px; display: flex;">
									<button class="btn btn-primary btn-minus" type="button" onclick="countModify('-')" style="width: 40px;">
										<i class="fa fa-minus cartIcon" style="margin-left: 0px;"></i>
									</button>
									<input type="text" id="count" name="count" style="background-color: #F6F6F6; border:none; width: 40px;" class="text-center" value="1">
									<button class="btn btn-primary btn-plus" type="button" style="width: 40px;" onclick="countModify('+')">
										<i class="fa fa-plus cartIcon" style="margin-left: 0px;"></i>
									</button>
									&nbsp;&nbsp;&nbsp;
									<c:if test="${product.option.size()>0 }">
										<span id="goods_detail">
										<select id="opt">
											<option value="">==옵션 선택==</option>
											<c:forEach var="i" begin="0" end="${product.option.size()-1}">
												<c:choose>
													<c:when test="${product.option.get(i).option_quantity==0}">
														<option value="${product.option.get(i).option_name}" disabled="disabled" style="background: lightgray">${product.option.get(i).option_name}(품절)</option>
													</c:when>
													<c:otherwise>
														<option value="${product.option.get(i).option_name}">${product.option.get(i).option_name}</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</select>
										</span>
									</c:if>
							</div>
							<div style="display: flex; width: 500px; margin-top: 20px;">
								<button class="btn btn-primary px-3" type="button" id="cartAddBtn" style="width: 150px;">
									<i class="fa fa-shopping-cart mr-1 cartIcon" style="margin-left: 0px;"></i> 장바구니
								</button>
								&nbsp;&nbsp;&nbsp;
								<button class="btn btn-primary px-3" type="button" id="wish" style="width: 130px;">
									<i class="fa fa-solid fa-heart cartIcon" style="margin-left: 0px;"></i> 찜하기
								</button>
							</div>
						</c:otherwise>
					</c:choose>
					<div id="alert-box">추가되었습니다!</div>
				</div>
			</form>
			<!-- 상단 이미지, 큰 정보 감싸는 곳 끝-->
			<hr>
			<div class="row px-xl-5" style="width: 1500px;">
	            <div class="col">
					<div class="nav nav-tabs justify-content-center border-secondary mb-4">
						<a class="nav-item nav-link active" data-toggle="tab" href="#tab-pane-1">상세 정보</a>
						<a class="nav-item nav-link review_page" data-toggle="tab" href="#tab-pane-3" id="tab3_review">리뷰</a>
					</div>
                    <div class="tab-content">
						<div class="tab-pane fade show active" id="tab-pane-1" align="center">
	                       <p style="width: 1000px; font-size: 20px;" align="left">
	                        	${product.detail}
	                       </p>
	                       <div align="center">
	                       		<c:if test="${not empty product.detail_img}">
			                       <img src="https://itwill220823team1.s3.ap-northeast-2.amazonaws.com/img/product_detail/${product.detail_img}" style="width: 800px;">
	                       		</c:if>
	                       </div>
	                    </div>
                    	<div class="tab-pane fade"  id="tab-pane-3" align="center">
                    		<div id="product-review-area" style="width: 1300px;" align="center">
								
							</div>
                    	</div>
                    	</div>
	                    </div>
					</div>
				</div>
			</div>

		<!-- 오른쪽 최근 목록 및 위로 가는거 -->   	
         <div>
			<div style="margin: 10px; width: 100px">
				<!-- Back to Top -->
	    		<a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>
			</div>
	    </div>
    	<!-- 오른쪽 최근 목록 및 위로 가는거 -->
    <!-- Shop Detail End -->

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