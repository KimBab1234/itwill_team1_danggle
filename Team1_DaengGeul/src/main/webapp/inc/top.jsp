<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>머릿글</title>
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
    <script src ="https://code.jquery.com/jquery-3.6.3.js"></script>
</head>

<body>
    <!-- Topbar Start -->
    <div class="container-fluid">
        <div class="row bg-secondary py-2 px-xl-5">
            <div class="col-lg-6 d-none d-lg-block">
                <div class="d-inline-flex align-items-center">
                    <a class="text-dark" href="">FAQs</a>
                    <span class="text-muted px-2">|</span>
                    <a class="text-dark" href="">도움말</a>
                    <span class="text-muted px-2">|</span>
                    <a class="text-dark" href="">고객센터</a>
                </div>
            </div>
            <div class="col-lg-6 text-center text-lg-right">
                <div class="d-inline-flex align-items-center">
                    <a class="text-dark px-2" href="">
                        <i class="fab fa-facebook-f"></i>
                    </a>
                    <a class="text-dark px-2" href="">
                        <i class="fab fa-twitter"></i>
                    </a>
                    <a class="text-dark px-2" href="">
                        <i class="fab fa-linkedin-in"></i>
                    </a>
                    <a class="text-dark px-2" href="">
                        <i class="fab fa-instagram"></i>
                    </a>
                    <a class="text-dark pl-2" href="">
                        <i class="fab fa-youtube"></i>
                    </a>
                </div>
            </div>
        </div>
        <div class="row align-items-center py-3 px-xl-5">
            <div class="col-lg-3 d-none d-lg-block">
                <a href="./" class="text-decoration-none">
                    <img alt="" src="img/logo2.jpg" width="200" height="50">
                </a>
            </div>
            <div class="col-lg-6 col-6 text-left">
                <form action="ProductList.go">
					<input type="hidden" name="type" value="B_best_search">
                    <div class="input-group">
                        <input type="text" class="form-control" name="keyword" placeholder="찾으시는 도서명을 입력하세요">
                        <div class="input-group-append">
                            <span class="input-group-text bg-transparent text-primary">
                                <i class="fa fa-search"></i>
                            </span>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-lg-3 col-6 text-right">
                <a href="Wishlist.ws" class="btn border" >
                    <i class="fas fa-heart text-primary"></i>
			        <span class="badge" id="wishCount">
			        	<c:choose>
	                   		<c:when test="${!empty sessionScope.sId }">
	                   			${sessionScope.wishlistCount }
	                    	</c:when>
	                    	<c:otherwise>
				                0                 	
	                    	</c:otherwise>
                    	</c:choose>
			        </span>                    	
                </a>
                <a href="CartListForm.or" class="btn border">
                    <i class="fas fa-shopping-cart text-primary"></i>
                    <span class="badge" id="cartBadge">
                    </span>
                </a>
            </div>
        </div>
    </div>
    <!-- Topbar End -->
   	<script>
   	
   	Map.prototype.toJSON = function toJSON() {
		  return [...Map.prototype.entries.call(this)];
		}

   		var id = '${sessionScope.sId}';
   		if(id != '') {
   			if(localStorage.getItem(id) == null) {
   				localStorage.setItem(id, JSON.stringify(new Map()));
   			}
	   		var cartCnt = new Map(JSON.parse(localStorage.getItem(id))).size;
   			$("#cartBadge").text(cartCnt);
   		} else {
   			$("#cartBadge").text(0);
   		}
   	</script>
    
</body>
</html>