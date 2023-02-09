<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>알림</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
<style>
*{
	color:#513e30;
	font-family: 'Gowun Dodum', sans-serif;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">
	var proList;
	var i = 0;
	var inList = opener.inList[opener.selectIdx].IN_SCHEDULE_CD;
	var complete = opener.inList[opener.selectIdx].IN_COMPLETE;
	
	var loginEmp = '${sessionScope.empNo}';
	var priv = '${sessionScope.priv}';
	if(loginEmp=='') {
		alert("로그인 후 이용하세요.");
		window.close();
		opener.location.href  = './Login';
	} else if(priv.charAt(4) !='1') {
		alert("권한이 없습니다.");
		window.close();
	}
	
	$(function() {		
		if(complete == "종결"){
			$(".box_complete").html("<br>선택한 전표를 종결 처리 하시겠습니까?<br><br>종결된 전표는 [완료] 탭에서 확인 가능합니다.<br>");
		}else {
			$(".box_complete").html("<br>선택한 전표를 종결 취소 하시겠습니까?<br><br>종결 취소된 전표는 [진행중] 탭에서 확인 가능합니다.<br>");
		}
		
	});
		
	function updateCom(){
		
		$.ajax({
			
	        type: "GET",
	        url: "UpdateComplete",
	        data: {
	        	keyword : inList,
	        	com_status : complete
	        },
	        dataType: 'json',
	        success: function(result) {
	            if (result != "0") {
	                window.close();
	                opener.location.reload();
	            } else {
	            	alert("종결 처리 변경 실패!");
	            	window.close();	                
	            }
	        },
	        error: function(a, b, c) {
	            console.log(a, b, c);
	        }
					
	    });
		
	}
	
	
</script>
<link href="${pageContext.request.contextPath }/resources/css/in.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div style="width:1200px;">
		<div class="title_regi">알림</div>
		<div class="box_complete">
			
		</div>
		<div id="comBtnArea">
			<input type="button" value="확인" id="comListbtn" onclick="updateCom()">
			<input type="button" value="취소" id="comListbtn" onclick="window.close()">
		</div>
	</div>
</body>
</html>