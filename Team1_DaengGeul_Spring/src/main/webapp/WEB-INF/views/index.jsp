<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
<style>
* {
	font-family: 'Gowun Dodum', sans-serif;
	font-weight:bold;
	url: @import url('https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap');
	}
</style>
<head>
<meta charset="UTF-8">
<title>댕글댕글</title>
</head>
<body>
	<!--------------------------------------- 머릿글 --------------------------------------->
	<header>
		<jsp:include page="inc/top.jsp"></jsp:include>
		<jsp:include page="inc/main_index.jsp"></jsp:include> <!-- 본문1 -->
	</header>
	<!--------------------------------------- 머릿글 --------------------------------------->

	
	<!------------------------------------------ 본문 2 ----------------------------------------->
	<article>
	<!-- Featured Start -->
    <div class="container-fluid pt-5">
        <div class="row px-xl-5 pb-3">
            <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                <div class="d-flex align-items-center border mb-4" style="padding: 30px;">
                    <h1 class="fa fa-check text-primary m-0 mr-3"></h1>
                    <h5 class="font-weight-semi-bold m-0">완벽한 포장과 구김없는 도서</h5>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                <div class="d-flex align-items-center border mb-4" style="padding: 30px;">
                    <h1 class="fa fa-shipping-fast text-primary m-0 mr-2"></h1>
                    <h5 class="font-weight-semi-bold m-0">빠르고 정확한 배송</h5>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                <div class="d-flex align-items-center border mb-4" style="padding: 30px;">
                    <h1 class="fas fa-exchange-alt text-primary m-0 mr-3"></h1>
                    <h5 class="font-weight-semi-bold m-0">7일 무료 교환/반품</h5>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                <div class="d-flex align-items-center border mb-4" style="padding: 30px;">
                    <h1 class="fa fa-phone-volume text-primary m-0 mr-3"></h1>
                    <h5 class="font-weight-semi-bold m-0">빠른 응답과 친절한 답변</h5>
                </div>
            </div>
        </div>
    </div>
    <!-- Featured End -->


    <!-- Categories Start -->
    <div class="container-fluid pt-5">
        <div class="row px-xl-5 pb-3">
            <div class="col-lg-4 col-md-6 pb-1">
                <div class="cat-item d-flex flex-column border mb-4" style="padding: 30px;">
                    <p class="text-right">댕글추천도서</p>
                    <a href="ProductList?type=B_recomm" class="cat-img position-relative overflow-hidden mb-3">
                        <img class="img-fluid" src="http://itwillbs3.cdn1.cafe24.com/img/b1.PNG" alt="">
                    </a>
                    <h5 class="font-weight-semi-bold m-0">도서명</h5>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 pb-1">
                <div class="cat-item d-flex flex-column border mb-4" style="padding: 30px;">
                    <p class="text-right">베스트셀러</p>
                    <a href="ProductList?type=B_best" class="cat-img position-relative overflow-hidden mb-3">
                        <img class="img-fluid" src="http://itwillbs3.cdn1.cafe24.com/img/b2.PNG" alt="">
                    </a>
                    <h5 class="font-weight-semi-bold m-0">도서명</h5>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 pb-1">
                <div class="cat-item d-flex flex-column border mb-4" style="padding: 30px;">
                    <p class="text-right">최신도서</p>
                    <a href="ProductList?type=B_new" class="cat-img position-relative overflow-hidden mb-3">
                        <img class="img-fluid" src="http://itwillbs3.cdn1.cafe24.com/img/b3.PNG" alt="">
                    </a>
                    <h5 class="font-weight-semi-bold m-0">도서명</h5>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 pb-1">
                <div class="cat-item d-flex flex-column border mb-4" style="padding: 30px;">
                    <p class="text-right">굿즈</p>
                    <a href="ProductList?type=G" class="cat-img position-relative overflow-hidden mb-3">
                        <img class="img-fluid" src="http://itwillbs3.cdn1.cafe24.com/img/g1.PNG" alt="">
                    </a>
                    <h5 class="font-weight-semi-bold m-0">문구세트</h5>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 pb-1">
                <div class="cat-item d-flex flex-column border mb-4" style="padding: 30px;">
                    <p class="text-right">굿즈</p>
                    <a href="" class="cat-img position-relative overflow-hidden mb-3">
                        <img class="img-fluid" src="http://itwillbs3.cdn1.cafe24.com/img/g2.PNG" alt="">
                    </a>
                    <h5 class="font-weight-semi-bold m-0">노트</h5>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 pb-1">
                <div class="cat-item d-flex flex-column border mb-4" style="padding: 30px;">
                    <p class="text-right">굿즈</p>
                    <a href="" class="cat-img position-relative overflow-hidden mb-3">
                        <img class="img-fluid" src="http://itwillbs3.cdn1.cafe24.com/img/g3.PNG" alt="">
                    </a>
                    <h5 class="font-weight-semi-bold m-0">책홀더</h5>
                </div>
            </div>
        </div>
    </div>
    <!-- Categories End -->


    <!-- Offer Start -->
    <div class="container-fluid offer pt-5">
        <div class="row px-xl-5">
            <div class="col-md-6 pb-4">
                <div class="position-relative bg-secondary2 text-center text-md-right text-white mb-2 py-5 px-5">
                    <img src="http://itwillbs3.cdn1.cafe24.com/img/ha.jpg" alt="">
                    <div class="position-relative" style="z-index: 1;">
                        <h5 class="text-uppercase text-primary mb-3">할인도서</h5>
                        <h1 class="mb-4 font-weight-semi-bold">책과 겨울나기</h1>
                        <a href="" class="btn btn-outline-primary py-md-2 px-md-3">지금 검색</a>
                    </div>
                </div>
            </div>
            <div class="col-md-6 pb-4">
                <div class="position-relative bg-secondary2 text-center text-md-left text-white mb-2 py-5 px-5">
                    <img src="http://itwillbs3.cdn1.cafe24.com/img/doc.jpg" alt="">
                    <div class="position-relative" style="z-index: 1;">
                        <h5 class="text-uppercase text-primary mb-3">할인굿즈</h5>
                        <h1 class="mb-4 font-weight-semi-bold">다양한 제품</h1>
                        <a href="" class="btn btn-outline-primary py-md-2 px-md-3">지금 검색</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Offer End -->
    
    
    <!-- Vendor Start -->
    <div class="container-fluid py-5">
    	<div id="venderTitle">
	    	제휴업체
    	</div>
        <div class="row px-xl-5">
            <div class="col">
                <div class="owl-carousel vendor-carousel">
                    <div class="vendor-item border p-4">
                        <img src="http://itwillbs3.cdn1.cafe24.com/img/kyobo.png" width="120px" height="120px" alt="">
                    </div>
                    <div class="vendor-item border p-4">
                        <img src="http://itwillbs3.cdn1.cafe24.com/img/yp.png" width="120px" height="120px" alt="">
                    </div>
                    <div class="vendor-item border p-4">
                        <img src="http://itwillbs3.cdn1.cafe24.com/img/yes24.png" width="120px" height="120px" alt="">
                    </div>
                    <div class="vendor-item border p-4">
                        <img src="http://itwillbs3.cdn1.cafe24.com/img/aladin.png" width="120px" height="120px" alt="">
                    </div>
                    <div class="vendor-item border p-4">
                        <img src="http://itwillbs3.cdn1.cafe24.com/img/interpark.png" width="120px" height="120px" alt="">
                    </div>
                    <div class="vendor-item border p-4">
                        <img src="http://itwillbs3.cdn1.cafe24.com/img/amazon.png" width="120px" height="60px" alt="">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Vendor End -->
	</article>
	<!------------------------------------------ 본문 2 ----------------------------------------->

	
	<!------------------------------------ 바닥글 --------------------------------------->
	<footer>
		<jsp:include page="inc/bottom.jsp"></jsp:include>
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