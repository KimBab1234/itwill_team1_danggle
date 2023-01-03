<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 목록</title>
<link href="css/default_order.css" rel="stylesheet" type="text/css">
</head>
<body>
	<header>
		<jsp:include page="../inc/top.jsp"></jsp:include>
		<jsp:include page="../inc/main.jsp"></jsp:include> <!-- 본문1 -->
	</header>
	
	<article>
	<hr>
	<div style="width: auto; display: flex; margin-left: 10px;">
		<div style="width: 300px; margin-left: 10px; "> <!-- border: 5px solid red; -->
			<jsp:include page="../inc/left.jsp"></jsp:include>
        </div>
	    <!-- Shop Start -->
	    <div class="container-fluid pt-5" style="margin-left: 20px; width: 1500px;">
	        <div class="row px-xl-5">
	            <!-- Shop Product Start -->
	                <div class="row pb-3">
	                    <div class="col-12 pb-1">
	                        <div class="d-flex justify-content-between mb-4">
	                            <div class="dropdown ml-4" style="left: 65%">
	                            	<c:if test="${param.type ne 'B_recomm' && param.type ne 'B_disc' }">
				                       <span> ■ 베스트 순위는 최근 7일간 판매량 기준입니다.</span>
		                                <button class="btn border dropdown-toggle" type="button" id="triggerId" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		                                            정렬
		                                </button>
		                                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="triggerId">
		                                    <a class="dropdown-item" href='ProductList.go?type=${param.type.substring(0,1)}_new${param.keyword==null? "": "_".concat(param.type.substring(param.type.lastIndexOf("_")+1).concat("&keyword=".concat(param.keyword)))}'>최신순</a>
		                                    <a class="dropdown-item" href='ProductList.go?type=${param.type.substring(0,1)}_best${param.keyword==null? "": "_".concat(param.type.substring(param.type.lastIndexOf("_")+1).concat("&keyword=".concat(param.keyword)))}'>많이 팔린 순</a>
		                                    <a class="dropdown-item" href='ProductList.go?type=${param.type.substring(0,1)}_pricedown${param.keyword==null? "": "_".concat(param.type.substring(param.type.lastIndexOf("_")+1).concat("&keyword=".concat(param.keyword)))}'>가격 낮은 순</a>
		                                    <a class="dropdown-item" href='ProductList.go?type=${param.type.substring(0,1)}_priceup${param.keyword==null? "": "_".concat(param.type.substring(param.type.lastIndexOf("_")+1).concat("&keyword=".concat(param.keyword)))}'>가격 높은 순</a>
		                                    <a class="dropdown-item" href="#">리뷰 좋은 순</a>
		                                </div>
	                            	</c:if>
	                            </div>
	                        </div>
	                    </div>
	                    
	                    <c:if test="${productList==null || productList.size()==0}">
		                    <div class="col-lg-12 col-md-6 col-sm-12 pb-1" style="width: 1005px;">
		                            <div class="card-header product-img bg-transparent p-0" style="display:flex;align-items: center; justify-content: center;">
		                            </div>
		                            <div class="card-body text-center p-0 pt-4 pb-3" style="">
		                              <span style="width: 300px; font-size: xx-large;">해당 상품이 없습니다.</span>
		                            </div>
		                            <div class="card-footer2 d-flex justify-content-between">
		                            </div>
		                    </div>
	                    </c:if>
	                    
	                    <c:forEach items="${productList}" var="item">
	                    <div class="col-lg-3 col-md-6 col-sm-12 pb-1" style="width: 1005px; "> <!-- border: solid black 1px;  -->
	                            <div class="card-header product-img bg-transparent border p-0" style=" display:flex;align-items: center; justify-content: center;">
	                              <a href="ProductDetail.go?product_idx=${item.product_idx}"><img class="img-fluid" src="img/product/${item.img}" style="width: 300px; height: 320px; object-fit: cover;"></a>
	                            </div>
	                            <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
	                                <h5 class="text-truncate mb-3">${item.name}</h5>
	                                <div class="d-flex justify-content-center">
	                                    <h6 class="text-muted ml-2"><del>${item.price}</del></h6>&nbsp;<h5 style="color: orange">${item.dis_price}</h5>
	                                </div>
	                                <div class="d-flex justify-content-center">
	                                    <h6 style="color: orange">${item.discount}% 할인</h6>
	                                </div>
	                                <c:if test="${item.product_idx.substring(0,1) eq 'B'}">
		                                 <div class="d-flex justify-content-center">
		                                    ${item.book_writer} | ${item.book_publisher} 
		                                 </div>
	                                </c:if>
	                                 <div class="d-flex justify-content-center">
	                                    ${item.sel_count} | ${item.book_date}
	                                 </div>
	                            </div>
	                            <div class="card-footer d-flex justify-content-between bg-light border">
	                                <a href="" class="btn btn-sm p-0"><i class="fas fa-eye text-primary mr-1"></i>View Detail</a>
	                                <a href="" class="btn btn-sm p-0"><i class="fas fa-shopping-cart text-primary mr-1"></i>Add To Cart</a>
	                            </div>
	                    </div>
	                   </c:forEach> 
	                   
	                   <!-- 페이지 목록 부분 -->
	                   <c:if test="${productList==null || productList.size()>0}">
		                    <div class="col-12 pb-1" style="margin-top: 30px;">
		                        <nav aria-label="Page navigation">
		                          <ul class="pagination justify-content-center mb-3">
		                            <li class="page-item">
		                            	<c:choose>
		                            		<c:when test="${param.pageNum > 3}">
		                            			 <a class="page-link" href="ProductList.go?type=${param.type}&pageNum=${param.pageNum-3}" aria-label="Previous">
				                                 <span aria-hidden="true">&laquo;</span>
				                                 <span class="sr-only">Previous</span>
				                                </a>
		                            		</c:when>
		                            		<c:otherwise>
		                            		</c:otherwise>
				                        </c:choose>
		                            </li>
		                            <c:forEach begin="${pageInfo.startPage}" end="${pageInfo.endPage}" varStatus="i">
		                            	<c:if test="${i.index <= pageInfo.maxPage}">
				                            <li class="page-item">
				                            	<c:choose>
				                            		<c:when test="${i.index == param.pageNum}">
				                            			<span class="thisPage" style="">${i.index}</span>
				                            		</c:when>
				                            		<c:otherwise>
						                            	<a class="page-link" href="ProductList.go?type=${param.type}&pageNum=${i.index}">${i.index}</a>
				                            		</c:otherwise>
				                            	</c:choose>
				                            </li>
		                            	</c:if>
		                            </c:forEach>
		                            <li class="page-item">
		                            	<c:choose>
		                            		<c:when test="${pageInfo.endPage < pageInfo.maxPage}">
				                              <a class="page-link" href="ProductList.go?type=${param.type}&pageNum=${param.pageNum+3>pageInfo.maxPage? pageInfo.maxPage : param.pageNum+3}" aria-label="Next">
				                                <span aria-hidden="true">&raquo;</span>
				                                <span class="sr-only">Next</span>
				                              </a>
		                            		</c:when>
		                            		<c:otherwise>
		                            		</c:otherwise>
				                        </c:choose>
		                            </li>
		                          </ul>
		                        </nav>
		                    </div>
	                    </c:if>
	                   <!-- 페이지 목록 끝-->
	                </div>
	            </div>
	            <!-- Shop Product End -->
	    </div>
	    <!-- Shop End -->
		<div> <!-- border: 5px solid red; -->
			<div style="margin: 10px; width: 100px">
				<!-- Back to Top -->
	    		<a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>
				
			</div>
	    </div>
	</div>
	</article>
	<!----------------------------------------------------------------------------------->
	
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