<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>내부 구역 추가</title>
<link href="css/default.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/resources/css/default.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
////로그인 유무 및 권한 확인
///기본등록(0), 사원조회(1), 사원관리(2), 재고조회(3), 재고관리(4)
var loginEmp = '${sessionScope.empNo}';
var priv = '${sessionScope.priv}';
if(loginEmp=='') {
 alert("로그인 후 이용하세요.");
 location.href="./Login";
} else if(priv.charAt(0) !='1') {
 alert("권한이 없습니다.");
 history.back();
}
////로그인 유무 및 권한 확인 끝

</script>

<link href="${pageContext.request.contextPath }/resources/css/hr.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.hrFormBtn {
	background-color: #736643;
	border: none;
	cursor: pointer;
	color: #fff;
	height: 27px;
	width: 55px;
	border-radius: 4px;
	font-size: 15px;
}

.Btn {
	background-color: #736643;
	border: none;
	cursor: pointer;
	color: #fff;
	height: 27px;
	width: 100px;
	border-radius: 4px;
	font-size: 15px;
}
</style>
</head>
<body>
	<jsp:include page="../inc/top.jsp"></jsp:include>
	<div style="display: flex;">
		<div style="width: 300px; margin-top: 0px; margin-right: 0px; border-right:solid 1px; border-color: #BDBDBD;">
			<jsp:include page="../inc/regist_left.jsp"></jsp:include>
		</div>
		<!-- 여기서부터 본문-->
		<div align="center" style="width: 1300px;">
			<h2 align="center">내부 구역 등록</h2>
			<form action="WhAreaRegistPro" method="post">
			<input type="hidden" name="WH_CD" value="${param.WH_CD}">
			<input type="hidden" name="pageNum" value="${param.pageNum}">
			<table style="text-align: center; border: solid 1px; width: 500px; height: 150px;">
				<tr>
					<td align="right" style="width: 200px;">구역명</td> 
					<td align="left" style="width: 400px;" >&nbsp;&nbsp;&nbsp;
						<input type="text" id="WH_AREA" name="WH_AREA" required="required" placeholder="구역명">
					</td>
				</tr> 
				<tr>
					
				<td colspan="2" align="center">
					<input type="submit" class="Btn" value="구역명 등록">
					<input type="button" class="hrFormBtn" value="취소" onclick="history.back()">
				</td>
			</tr>
			</table>
			</form>
		</div>
		
		
</div>
</body>
</html>