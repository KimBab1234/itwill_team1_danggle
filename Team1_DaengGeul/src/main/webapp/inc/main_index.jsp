<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>메인</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Free HTML Templates" name="keywords">
    <meta content="Free HTML Templates" name="description">

    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet"> 

    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/style.css" rel="stylesheet">
    
    <%--------------------------- 로그아웃 확인 알림창 ---------------------------%>
	<script src="js/jquery-3.6.3.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#logout").on("click", function() {
				if(confirm("로그아웃 하시겠습니까?")){
					var id = '${sessionScope.sId}';
					localStorage.removeItem(id);
					location.href = "MemberLogoutPro.me";
				}
			});
		});
	</script>
	<%----------------------------------------------------------------------------%>
</head>

<body>
    <!-- Navbar Start -->
    <div class="container-fluid mb-5" style="width: 1920px;">
        <div class="row border-top px-xl-5">
            <div class="col-lg-3 d-lg-block" style="width:300px;">
                <a class="btn shadow-none d-flex align-items-center justify-content-between bg-primary text-white w-100" data-toggle="collapse" href="#navbar-vertical" style="height: 65px; margin-top: -1px; padding: 0 30px;">
                    <span class="m-0">카테고리</span>
                    <i class="fa fa-angle-down text-dark"></i>
                </a>
                <nav class="collapse show navbar navbar-vertical navbar-light align-items-start p-0 border border-top-0 border-bottom-0" id="navbar-vertical">
                    <div class="navbar-nav w-100" style="width:300px; height: 460px">
                        <a href="ProductList.go?type=B_new_genre&keyword=humanities" class="nav-item nav-link">인문</a>
						<a href="ProductList.go?type=B_new_genre&keyword=novel" class="nav-item nav-link">소설</a>
						<a href="ProductList.go?type=B_new_genre&keyword=poem" class="nav-item nav-link">시</a>
						<a href="ProductList.go?type=B_new_genre&keyword=history" class="nav-item nav-link">역사</a>
						<a href="ProductList.go?type=B_new_genre&keyword=religion" class="nav-item nav-link">종교</a>
						<a href="ProductList.go?type=B_new_genre&keyword=society" class="nav-item nav-link">사회</a>
						<a href="ProductList.go?type=B_new_genre&keyword=science" class="nav-item nav-link">과학</a>
						<a href="ProductList.go?type=B_new_genre&keyword=self_improvement" class="nav-item nav-link">자기계발</a>
						<a href="ProductList.go?type=B_new_genre&keyword=kids" class="nav-item nav-link">어린이</a>
						<a href="ProductList.go?type=B_new_genre&keyword=health" class="nav-item nav-link">건강</a>
						<a href="ProductList.go?type=B_new_genre&keyword=reference" class="nav-item nav-link">참고서</a>
                    </div>
                </nav>
            </div>
            <div class="col-lg-9"  style="width: 1500px;">
                <nav class="navbar show navbar-expand-lg bg-light navbar-light py-3 py-lg-0 px-0">
                    <div class="collapse show navbar-collapse justify-content-between" id="navbarCollapse">
                        <div class="navbar-nav mr-auto py-0">
                            <a href="./" class="nav-item nav-link active">Home</a>
                            <a href="ProductList.go?type=B_recomm" class="nav-item nav-link">추천도서</a>
                            <a href="ProductList.go?type=B_best" class="nav-item nav-link">베스트셀러</a>
                            <a href="ProductList.go?type=B_disc" class="nav-item nav-link">할인 중인 도서</a>
                            <a href="ProductList.go?type=G" class="nav-item nav-link">굿즈샵</a>
                            <a href="" class="nav-item nav-link">커뮤니티</a>
                        </div>
                        
                        <div class="navbar-nav ml-auto py-0">
	            			<c:choose>
	            			<%-- 로그인 상태 --%>
								<c:when test="${!empty sessionScope.sId }">
									<c:choose>
										<%-- 관리자 --%>
										<c:when test="${sessionScope.sId eq 'admin'}">
											<div class="container" style="position: relative;">
											<button class="btn btn-primary dropdown-toggle" type="button" id="triggerId1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			                                	관리자
			                                </button>
			                                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="triggerId1" style="position: absolute;">
			                                    <a class="dropdown-item" href='ProductRegiForm.ad'>신규 상품 등록</a>
			                                    <a class="dropdown-item" href='ProductList.ad'>상품 정보 관리</a>
			                                    <a class="dropdown-item" href='RecommendBook.ad'>추천 도서 관리</a>
			                                </div>
			                                </div>
										</c:when>
										<%-- 일반사용자 --%>
										<c:otherwise>
											<div class="container" style="position: relative;">
			                                <button class="btn btn-primary dropdown-toggle" type="button" id="triggerId" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			                                	${sessionScope.sId } 님
			                                </button>
			                                <div class="dropdown-menu" aria-labelledby="triggerId" style="position: absolute;">
			                                    <a class="dropdown-item" href='MemberInfo.me?id=${sessionScope.sId}'>회원정보</a>
			                                    <a class="dropdown-item" href='OrderList.or'>주문내역</a>
			                                    <a class="dropdown-item" href="ReviewList.re">내가 쓴 리뷰</a>
			                                    <a class="dropdown-item" href="#">내 문의글</a>
			                                </div>
										</div>
										</c:otherwise>
									</c:choose>
									<div class="container">
										<a href="" id="logout" class="nav-item nav-link">로그아웃</a>
									</div>
								</c:when>
								
								
								<%-- 로그아웃 상태 --%>
								<c:otherwise>
									<a href="MemberLoginForm.me" class="nav-item nav-link">로그인</a>
									<a href="MemberJoinForm.me" class="nav-item nav-link">회원가입</a>
								</c:otherwise>
							</c:choose>
                		</div>
                	</div>
                </nav>
                
                <!-- 본문  -->
                <div id="header-carousel" class="carousel slide" data-ride="carousel">
                    <div class="carousel-inner">
                        <div class="carousel-item active" style="height: 410px;">
                            <img class="img-fluid" src="img/m1.PNG" alt="Image">
                        </div>
                        <div class="carousel-item" style="height: 410px;">
                            <img class="img-fluid" src="img/m2.PNG" alt="Image">
                        </div>
                    </div>
                    <div class="carousel-control-prev" >
                    <a class="btn btn-dark" style="width: 45px; height: 45px;" href="#header-carousel" data-slide="prev">
                       <span class="carousel-control-prev-icon mb-n2"></span>
                    </a>
                    </div>
                    <div class="carousel-control-next" >
                    <a class="btn btn-dark" style="width: 45px; height: 45px;" href="#header-carousel" data-slide="next">
                       <span class="carousel-control-next-icon mb-n2" ></span>
                    </a>
                   </div>
                </div>
                <!-- 본문  -->
                
            </div>
        </div>
    </div>
    <!-- Navbar End -->
      <!-- JavaScript Libraries -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>

    <!-- Contact Javascript File -->
    <script src="mail/jqBootstrapValidation.min.js"></script>
    <script src="mail/contact.js"></script>

    <!-- Template Javascript -->
    <script src="js/main.js"></script>
</body>
</html>
