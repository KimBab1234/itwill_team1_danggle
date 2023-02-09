<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<link href="${pageContext.request.contextPath }/resources/css/hr.css" rel="stylesheet" type="text/css" />
<script src="https://kit.fontawesome.com/5fad4a5f29.js" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>댕글댕글 - ERP</title>
<script>

var needPassChange = '${sessionScope.needPassChange}'
$(function() {
		if(needPassChange=='Y') {
			popOpen();
		} else {
			popClose();
		}
});

	 function popOpen() {
	    $('.modal-bg').css("display","inline-block");
	    $('.modal-wrap').css("display","inline-block");
	 }
	 function popClose() {
	   var modalPop = $('.modal-wrap');
	   var modalBg = $('.modal-bg');
	   $(modalPop).hide();
	   $(modalBg).hide();
	}
	 

		
</script>
<style>

/* 팝업 스타일 */
.modal-bg {display:none; width:100%;height:100%;position:fixed;top:0;left:0;right:0;background: rgba(0, 0, 0, 0.6);z-index:1002;}
.modal-wrap {
	display:none;position:absolute;top:50%;left:50%;
	transform:translate(-50%,-50%);
	width:500px;height:500px;
	background:#c9b584; z-index:1003;
	border-radius: 20px;
}
</style>
</head>
<body>

  <!-- 전체 레이아웃 -->
<jsp:include page="inc/top.jsp"></jsp:include>
		<h1 align="center" style="width: auto; margin-top: 100px; margin-right: 0px; text-align: center;">메인페이지</h1>
<!-- //전체 레이아웃 -->
        <!-- modal 영역 -->
  <div class="modal-bg" onClick="javascript:popClose();"></div>
  <div class="modal-wrap" align="center" style="display: table;">
  <div style="display: table-cell; vertical-align: middle; height:500px;">
  <div style="font-size: 40px; font-weight: bold; ">
  	<i class="fa fa-solid fa-triangle-exclamation" style="color: #A50000;"></i><span>비밀번호 변경 필요!</span>
  </div>
  <div>
	<h2>현재 임시 비밀번호를 사용하고 있습니다.</h2>
	<h2>신규 비밀번호로 변경이 필요합니다.</h2>
	<button type="button" class="hrFormBtn" style="width: 300px; font-size: 24px;"  onclick="javascript:location.href='HrEdit?empNo=${sessionScope.empNo}'">비밀번호 변경하러가기!</button>
  </div>
  </div>
  </div>
<!-- //modal 영역 -->
</body>
</html>