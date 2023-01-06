<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<link href="css/default_order.css" rel="stylesheet" type="text/css">
<script src ="https://code.jquery.com/jquery-3.6.3.js"></script>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=family=Jua&Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
<style>
.cartB:focus, .cartB:active { outline:none; }
</style>
</head>
</head>
<body>
<script>
Map.prototype.toJSON = function toJSON() {
		  return [...Map.prototype.entries.call(this)];
		}

	var id = '${sessionScope.sId}';
	var cartList = new Map(JSON.parse(localStorage.getItem(id)));
	var chkList = document.getElementsByClassName('cartCheck');
	var deleteArr = [];
	var productList=[];
	var totalChk=0;
	
	function listSetUp() {
		cartList = new Map(JSON.parse(localStorage.getItem(id)));
 		if(cartList.size==0) {
			$("#cartNone").css("visibility", "visible");
			$("#cartUse").css("visibility", "hidden");
			$("#cartUse1").css("visibility", "hidden");
		} else {
			$("#cartUse").css("visibility", "visible");
			$("#cartUse1").css("visibility", "visible");
			var i = 0;
			for(var key of cartList.keys()) {
				if(i>0) {
					$("#cartUse table").append("<tr id='cartAddRow"+i+"'>"+$("#cartAddRow0").html()+"</tr>");
				}
				var prod = new Map(cartList.get(key));
				productList[i]= key;
				$("#cartAddRow"+i+" input[type=checkbox]").val(key);
				$("#cartAddRow"+i+" input[type=text]").val(prod.get('count'));
				$("#cartAddRow"+i+" img").prop("src", prod.get('img'))
				if(prod.get('opt')!=null) {
					$("#cartAddRow"+i+" td").eq(2).text(prod.get('name')+" (선택 옵션 :"+prod.get('opt')+")");
				} else {
					$("#cartAddRow"+i+" td").eq(2).text(prod.get('name'));
				}
				$("#cartAddRow"+i+" td").eq(3).text(prod.get('price')+"원");
				$("#cartAddRow"+i+" .price").text(Number(prod.get('price')*Number(prod.get('count'))));
				$("#cartAddRow"+i+" button").eq(0).val(i);
				$("#cartAddRow"+i+" button").eq(1).val(i);
				
				i++;
			}
		}
		
		allCheck('checked');
	}

	function sum() {
		var total = 0;
		totalChk=0;
		for(var i=0; i<chkList.length; i++) {
			if(chkList[i].checked && $("#cartAddRow"+i).is(":visible")){
				var prod = new Map(cartList.get(productList[i]));
				var temp = Number(prod.get('price'))*Number(prod.get('count'));
				$('.price').get(i).innerText=temp;
				total = total+Number(temp);
				totalChk++;
			}
		}
		$('#totalChk').text(totalChk);
		$('#total').text(total);
		$('#totalPay').val(total);
	}
	
	function countModify(index, sign) {
		var prod = new Map(cartList.get(productList[index]));
		var num = Number(prod.get('count'));
		if(sign=='+') {
			prod.set('count', num+1);
			$('.count').eq(index).val(num+1);
		} else if(sign=='-') {
			if(num-1>0) {
				prod.set('count', num-1);
				$('.count').eq(index).val(num-1);
			}
		}
		cartList.set(productList[index], prod);
		localStorage.setItem(id, JSON.stringify(cartList));
		sum();
	}
	

	function allCheck(chk) {
		for(var i=0; i<chkList.length; i++) {
			chkList[i].checked = chk;
			if(chk) {
				$("#allChkB").val('');
				$("#allChk").text('전체선택해제');
			} else {
				$("#allChkB").val('checked');
				$("#allChk").text('전체선택');
			}
		}
		sum();
	}
	
	function deleteCart() {
		var deleteConfirm = confirm("선택하신 상품 "+totalChk+"개를 삭제하시겠습니까?");
		if(deleteConfirm) {
			var deleteItem="";
			for(var i=0; i<chkList.length; i++) {
				if(chkList[i].checked) {
					$("#cartAddRow"+i).css("display","none");
					cartList.delete(productList[i]);
				}
			}
			if(cartList.size==0) {
				localStorage.setItem(id, JSON.stringify(new Map()));
				$("#cartNone").css("visibility", "visible");
				$("#cartUse").css("visibility", "hidden");
				$("#cartUse1").css("visibility", "hidden");
			} else {
				localStorage.setItem(id, JSON.stringify(cartList));
			}
			allCheck('checked');
			$("#cartBadge").text(cartList.size);
		}
	}
	

	
	$(function() {
		listSetUp();
	});
	
</script>

	<header>
		<jsp:include page="../inc/top.jsp"></jsp:include>
		<jsp:include page="../inc/main.jsp"></jsp:include> <!-- 본문1 -->
	</header>
	<div align="center">
	<div style="width: 1000px; margin-top: 50px; min-height: 500px;">
	<h2 id="cartUse1" style="visibility: hidden; text-align: left; font-weight:bold; font-family: 'Poppins', sans-serif; color:#513e30">| 상품 확인</h2>
	<form action="OrderPayForm.or" method="post">
		<h1 id="cartNone" style="visibility: hidden; font-family: 'Jua', sans-serif; color: #513e30;">장바구니가 비었습니다.</h1>
		<div id="cartUse" style="visibility: hidden;">
			<div align="right">
				<button type="button" class="cartB" id="allChkB" onclick="allCheck(this.value)" value="true"><span id="allChk">전체 선택</span></button>
				<button type="button" class="cartB" onclick="deleteCart()">선택 상품 장바구니 삭제하기</button>
			</div>
			<table border="1" class="regi_table" style="width: 1000px; text-align: center; margin-top: 20px">
				<tr id="tableFirst" >
					<th width="30px">체크</th>
					<th width="50px">이미지</th>
					<th width="300px">상품</th>
					<th width="70px">개당 가격</th>
					<th width="70px">수량</th>
					<th width="70px">금액</th>
				</tr>
				<tr id="cartAddRow0" >
					<td><input type="checkbox" name="cartItem" onclick="sum()" class="cartCheck" ></td>
					<td><img src="" width="100px"></td>
					<td></td>
					<td></td>
					<td>
						<button type="button" class="cartB small" onclick="countModify(this.value, '-')" style="width: 20px">-</button>
						<input type="text" readonly="readonly" class="count" name="count" value="" style="width:30px; text-align: center;">
						<button type="button" class="cartB small" onclick="countModify(this.value, '+')" style="width: 20px">+</button>
					</td>
					<td><span class="price"></span>원</td>
				</tr>
			</table>
				<h3 style="margin-top: 50px; font-weight:bold; font-family: 'Poppins', sans-serif; color:#513e30">선택한 상품 : <span id="totalChk">0</span>개 | 
				최종 금액 : <span id="total">0</span>원</h3>
				<div class="wrap" style="margin-top: 30px; margin-bottom: 50px;">
				  <button class="orderB">주문하기!</button>
				</div>
			<input type="hidden" name="totalPay" id="totalPay" value="0">
		</div>
	</form>
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