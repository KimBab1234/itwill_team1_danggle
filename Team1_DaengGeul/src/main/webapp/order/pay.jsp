<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 진행</title>
<link href="css/default_order.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
<style>
* {
	font-family: 'Gowun Dodum', sans-serif;
	url: @import url('https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap');
	}
</style>
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
			if(prod.get('opt')!=null) {
				$("tr td").eq(i*4+4).text(prod.get('name')+" (선택 옵션 :"+prod.get('opt')+")");
			} else {
				$("tr td").eq(i*4+4).text(prod.get('name'));
			}
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
            amount : $("#totalPay").val(),
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
            if (rsp.success) {
        		$("#imp_uid").val(rsp.imp_uid);
        		$("#merchant_uid").val(rsp.merchant_uid);
        		$("#cartJson").val(JSON.stringify(new Map(JSON.parse(localStorage.getItem(id)))));
            	document.dangglePayForm.submit();
            } else {
				alert("결제에 실패했습니다! " +  rsp.error_msg);
            }
        });
    }
	
	
    <%------------------------------- 주소찾기 API -------------------------------%>
    function execDaumPostcode() {
    	<%-- 주소검색 팝업창 --%>
           new daum.Postcode({
               oncomplete: function(data) {
                   // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                   // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                   // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                   var roadAddr = data.roadAddress; // 도로명 주소 변수
                   var extraRoadAddr = ''; // 참고 항목 변수

                   // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                   // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                   if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                       extraRoadAddr += data.bname;
                   }
                   // 건물명이 있고, 공동주택일 경우 추가한다.
                   if(data.buildingName !== '' && data.apartment === 'Y'){
                      extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                   }

                   // 우편번호와 주소 정보를 해당 필드에 넣는다.
                   document.getElementById('postcode').value = data.zonecode;
                   document.getElementById("roadAddress").value = roadAddr;
//                    document.getElementById("jibunAddress").value = data.jibunAddress;
                   
               }
           }).open();
    	<%-- 주소검색 팝업창 --%>
       }
    <%----------------------------------------------------------------------------%>
	
	
</script>
</head>
<body>
	<header>
		<jsp:include page="../inc/top.jsp"></jsp:include>
		<jsp:include page="../inc/main.jsp"></jsp:include> <!-- 본문1 -->
	</header>
	<div align="center">
	<div style="width: 1000px; margin-top: 50px; min-height: 500px;">
	<h3 style="text-align: left; font-family: 'Jua', sans-serif; color: #513e30;">| 결제</h3>
		<form action="OrderPayPro.or" method="post" name="dangglePayForm">
			<table border="1" class="regi_table"  style="width: 1000px; text-align: center; margin-top: 20px" id="payTb">
				<tr style="background: #513e30; color:#F0D264">
					<td width="300px">상품</td>
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
			<hr>			
			<section style="font-size: 20px;">
				<h3 style="text-align: left; font-family: 'Jua', sans-serif; color: #513e30;">| 적립금, 쿠폰</h3>
				적립금 : ${member.member_point}원
				사용할 적립금 : <input type="text" name="point" style="background: #513e30; color:#F0D264" id="point" value="0" oninput="this.value=this.value.replace(/[^0-9]/g, '');">원
				<button class="cartB" type="button" onclick="pointApply()">사용</button><br>
			</section>
			<hr>
			<div align="left">
			<h3 style="text-align: left; font-family: 'Jua', sans-serif; color: #513e30;">| 배송 주소</h3>
			<table border="1" style="width: 800px; text-align: left; margin-top: 20px">
				<tr>
					<th width="100px" style="text-align:center; background: #513e30; color:#F0D264">주문자</th>
					<td width="700px"><input type="text" name="name" value="${member.member_name}" style="width: 200px;"></td>
				</tr>
				<tr>
					<th style="text-align:center; background: #513e30; color:#F0D264">주소</th>
					<td>
						<input type="text" name="postcode" id="postcode" style="width: 200px;" value="${member.member_postcode}">
						<input type="button" id="postbutton" onclick="execDaumPostcode()" value="우편번호 찾기">
						<input type="text" name="roadAddress" id="roadAddress" style="width: 600px;" value="${member.member_roadAddress}">
					</td>
				</tr>
				<tr>
					<th style="text-align:center; background: #513e30; color:#F0D264">상세 주소</th>
					<td><input type="text" name="addressDetail" id="addressDetail" style="width: 600px;" value="${member.member_addressDetail}"></td>
				</tr>
				<tr>
					<th style="text-align:center; background: #513e30; color:#F0D264">연락처</th>
					<td>
						<input type="text" name="phone1" value="${member.member_phone.substring(0,3)}" maxlength="3" oninput="this.value=this.value.replace(/[^0-9]/g, '');"  style="width: 64px;">-
						<input type="text" name="phone2" value="${member.member_phone.substring(4,member.member_phone.lastIndexOf('-'))}" maxlength="4"  oninput="this.value=this.value.replace(/[^0-9]/g, '');" style="width: 60px;">-
						<input type="text" name="phone3" value="${member.member_phone.substring(member.member_phone.lastIndexOf('-')+1)}"  maxlength="4"  oninput="this.value=this.value.replace(/[^0-9]/g, '');"  style="width: 60px;">
					</td>
				</tr>
			</table>
			</div>
			<hr>
			<h2 style="font-family: 'Jua', sans-serif; color: #513e30;">최종 금액 : <span id="total" style="color: red">${totalPay}</span>원</h2>
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