<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/default_order.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<script>
Map.prototype.toJSON = function toJSON() {
	  return [...Map.prototype.entries.call(this)];
	}

	var id = '${sessionScope.sId}';
	var totalPay = Number('${totalPay}');
	const today = new Date();
	var month = (today.getMonth()+1).toString().padStart(2, "0");
	var day = today.getDate().toString().padStart(2, "0");
	var hour = today.getHours().toString().padStart(2, "0");
	var min = today.getMinutes().toString().padStart(2, "0");
	var sec = today.getSeconds().toString().padStart(2, "0");
	var orderNo = '${member.member_id}'+ today.getFullYear() + month + day + hour + min + sec;
	
	if(totalPay==null || totalPay==0) {
		alert("결제하실 상품을 선택해주세요.");
		history.back();	
	}
	
	var cartList = new Map(JSON.parse(localStorage.getItem(id)));
	var orderProName="";
	for(var key of cartList.keys()) {
		orderProName += new Map(cartList.get(key)).get('name');
		break;
	}
	orderProName += (" 외 " + (cartList.size-1) + "종");

	function listSetUp() {
		var i = 0;
		for(var key of cartList.keys()) {
			if(i>0) {
				$("#payTb").append("<tr>"+$("#cartAddRow").html()+"</tr>");
			}
			var prod = new Map(cartList.get(key));
			$("tr td").eq(i*4+4).text(prod.get('name'));
			$("tr td").eq(i*4+5).text(prod.get('price'));
			$("tr td").eq(i*4+6).text(prod.get('count'));
			$("tr td").eq(i*4+7).text(Number(prod.get('price')*Number(prod.get('count')))+"원");
			
			i++;
		}
	}

	$(function() {
		listSetUp();
	});
	
	function totalPay(){
		document.getElementById('totalPay').value = document.getElementById('total').innerHTML;
	}
	
	function pointApply() {
		var member_point = Number('${member.member_point}');
		var usingPoint = Number($("#point").val());

		//만약 적립금보다 큰 금액이 들어오면 max값만 입력되게하기
		if(usingPoint>member_point) {
			alert("현재 사용 가능한 포인트는 "+member_point+"원입니다.");
			usingPoint = member_point;
		}
		$("#usePoint").val(usingPoint);
		$("#totalPay").val(totalPay - usingPoint);
		$("#point").val(usingPoint);
		$("#total").text(totalPay - usingPoint);
		
	}
	
	////아임포트 결제 API 적용
	var IMP = window.IMP;   // 생략 가능
	IMP.init("imp70426001"); // 예: imp00000000 
	
    function requestPay() {
		
        IMP.request_pay({
            pg : 'html5_inicis',
            pay_method : 'card',
            merchant_uid: orderNo, 
            name : orderProName,
            amount : 100,
            buyer_email : '${member.member_email}',
            buyer_name : '${member.member_name}',
            buyer_tel : '010-1234-5678',
            buyer_addr : '${member.member_roadAddress}',
            buyer_postcode : '123-456'

            //////////////////실험용//////////////////
//             merchant_uid: "ORD20180131-0000013",   // 주문번호
//             name: "노르웨이 회전 의자",
//             amount: 100,                         // 숫자 타입
//             buyer_email: "gildong@gmail.com",
//             buyer_name: "홍길동",
//             buyer_tel: "010-4242-4242",
//             buyer_addr: "서울특별시 강남구 신사동",
//             buyer_postcode: "01181"


        }, function (rsp) { // callback
        		$("#imp_uid").val(rsp.imp_uid);
        		$("#merchant_uid").val(rsp.merchant_uid);
        		$("#cartJson").val(JSON.stringify(new Map(JSON.parse(localStorage.getItem(id)))));
            	document.dangglePayForm.submit();
            if (rsp.success) {
            } else {
				alert("결제에 실패했습니다! 에러 내용: " +  rsp.error_msg);
            }
        });
    }
	
</script>
</head>
<body>
	<header>
		<jsp:include page="../inc/top.jsp"></jsp:include>
		<jsp:include page="../inc/main.jsp"></jsp:include> <!-- 본문1 -->
	</header>
	<div align="center">
	<div style="width: 1000px; margin-top: 50px; min-height: 500px;">
	<h3 style="text-align: left;">| 결제</h3>
		<form action="OrderPayPro.or" method="post" name="dangglePayForm">
			<table border="1" style="width: 1000px; text-align: center; margin-top: 20px" id="payTb">
				<tr>
					<td width="140px">상품</td>
					<td width="70px">판매가</td>
					<td width="70px">수량</td>
					<td width="70px">합계</td>
				</tr>
				<tr id="cartAddRow" >
					<td></td>
					<td></td>
					<td></td>
					<td style="color: red"></td>
				</tr>
			</table>
			<br>
			<section>
				<h3 style="text-align: left;">| 적립금, 쿠폰</h3>
				적립금 : ${member.member_point}원
				사용할 적립금 : <input type="text" name="point" id="point" value="0" oninput="this.value=this.value.replace(/[^0-9]/g, '');">원
				<button type="button" onclick="pointApply()">사용</button><br>
			</section>
			<h3 style="text-align: left;">| 배송 주소</h3>
			<table border="1" style="width: 1000px; text-align: center; margin-top: 20px">
				<tr align="center">
					<th width="200px">주문자</th>
					<td width="800px"><input type="text" name="name" value="${member.member_name}" size="100"></td>
				</tr>
				<tr align="center">
					<th>주소</th>
					<td><input type="text" name="address" value="${member.member_roadAddress.concat(member.member_jibunAddress)}"></td>
				</tr>
				<tr align="center">
					<th>상세 주소</th>
					<td><input type="text" name="addressDetail" value="${member.member_addressDetail}"></td>
				</tr>
				<tr align="center">
					<th>연락처</th>
					<td><input type="text" name="phone" value="${member.member_phone}"></td>
				</tr>
			</table>
			<h2>최종 금액 : <span id="total" style="color: red">${totalPay}</span>원</h2>
			<input type="hidden" id="totalPay" name="totalPay" value="${totalPay}">
			<input type="hidden" id="usePoint" name="usePoint" value="0">
			<input type="hidden" id="payment" name="payment" value="card">
			<input type="hidden" id="imp_uid" name="imp_uid" >
			<input type="hidden" id="merchant_uid" name="merchant_uid">
			<input type="hidden" id="cartJson" name="cartJson" >
			<button class="button"  type="button" onclick="requestPay()" style="vertical-align:middle"><span>결제 </span></button>
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