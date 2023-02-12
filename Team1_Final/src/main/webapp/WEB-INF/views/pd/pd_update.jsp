<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<%-- <link href="${pageContext.request.contextPath }/resources/css/pd.css" rel="stylesheet" type="text/css" /> --%>
<style type="text/css">
#titleH1 {
	margin-top: 20px;
	text-align: left;
}
table { 
 	margin: 0 auto;
	border-collapse: collapse;
	border-color: #b09f76;
	font-weight: bold;
	border-radius: 10px;
  	box-shadow: 0 0 0 2px #c9b584;
  	width: 670px;
 	} 

#td_left {
	height: 50px;
	font-weight: bold;
	font-size: 20px;
	background: #c9b584; 
 	color: #736643;
 	width: 250px;
	text-align: center;
}
#b1 {
	background-color: #fff5e6;
	width: 120px;
	height: 50px;
	color: #575754;
	border-radius: 20px;
	border-color: transparent;
	font-weight: bold;
	font-size: 20px;
}
#b2 {
	background-color: #fff5e6;
	width: 120px;
	height: 50px;
	color: #575754;
	border-radius: 20px;
	border-color: transparent;
	font-weight: bold;
	font-size: 20px;
}
#b3 {
	background-color: #fff5e6;
	width: 120px;
	height: 50px;
	color: #575754;
	border-radius: 20px;
	border-color: transparent;
	font-weight: bold;
	font-size: 20px;
}
#b4 {
	background-color: #fff5e6;
	width: 200px;
	height: 50px;
	color: #575754;
	border-radius: 10px;
	border-color: transparent;
	font-weight: bold;
	font-size: 15px;
}
 /* 기존 파일 인풋 박스 안보이게 처리 */
 .filebox input[type="file"] { 
     position: absolute; 
     width: 0; 
     height: 0; 
     padding: 0; 
     overflow: hidden; 
     border: 0; 
 }
</style>
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
	
	function selectFile(fileName) {
		$("#img_name").val(fileName.substr(fileName.lastIndexOf("\\")+1));		
	}
</script>
</head>
<body>
	<jsp:include page="../inc/top.jsp"></jsp:include>
	<div style="display: flex; width: 1900px;">
		<div style="width: 500px; margin-top: 0px; margin-right: 0px;">
			<jsp:include page="../inc/pd_left.jsp"></jsp:include>
		</div>
		<!-- 여기서부터 본문-->
		<div style="margin-left: 100px;">
		<h1 id="titleH1"><b style="border-left: 10px solid">&nbsp;품목 수정 (상세 정보 조회)</b></h1>
		<form action="PdUpdatePro" method="post" enctype="multipart/form-data">
			<input type="hidden" name="PRODUCT_CD" value="${product.PRODUCT_CD }" >
			<table>
				<tr>
					<td id="td_left"><label for="PRODUCT_NAME">품목명</label></td>
					<td><input type="text" style="height: 30px; width: 230px; font-weight : bold ;margin-left:35px;" name="PRODUCT_NAME" readonly="readonly" value="${product.PRODUCT_NAME }">&nbsp;품목명 수정 불가</td>
				</tr>
				<tr>
					<td id="td_left"><label for="PRODUCT_GROUP_BOTTOM_CD">품목그룹</label><br>
					</td>
					<td>
						<input type="text" name="PRODUCT_GROUP_TOP_CD" id="PRODUCT_GROUP_TOP_CD" readonly="readonly" required="required" style="height: 30px; width: 70px; font-weight : bold ;margin-left:35px;" value="${product.PRODUCT_GROUP_TOP_CD }">
						<input type="text" name="PRODUCT_GROUP_BOTTOM_CD" id="PRODUCT_GROUP_BOTTOM_CD" readonly="readonly" required="required" style="height: 30px; width: 70px; font-weight : bold ;" value="${product.PRODUCT_GROUP_BOTTOM_CD}">
						<input type="text" name="PRODUCT_GROUP_BOTTOM_NAME" id="PRODUCT_GROUP_BOTTOM_NAME" readonly="readonly" required="required" style="height: 30px; width: 70px; font-weight : bold ;" value="${product.PRODUCT_GROUP_BOTTOM_NAME }">
						<button type="button" onclick="window.open('Pd_group_bottom_SearchForm', 'searchPopup', 'width=500, height=500, left=600, top=400')"><b>품목그룹(소) 선택</b></button>
					</td>
				</tr>
				<tr>
					<td id="td_left"><label for="SIZE_DES">규격</label></td>
					<td id="td_right"><input type="text" style="height: 30px; width: 350px; font-weight : bold ;margin-left: 35px;" name="SIZE_DES" value="${product.SIZE_DES }"></td>
				</tr>
				<tr>
					<td id="td_left"><label for="UNIT">단위</label></td>
					<td>
						<select style="height: 30px; width: 350px; font-weight : bold ;margin-left: 35px;" name="UNIT" required="required" >
							<option value="">=======================  단위  =======================</option>
							<option value="SET" ${product.UNIT == 'SET' ? 'selected="selected"' : '' }>SET</option>
							<option value="BOX" ${product.UNIT == 'BOX' ? 'selected="selected"' : '' }>BOX</option>
							<option value="EA" ${product.UNIT == 'EA' ? 'selected="selected"' : '' }>EA</option>
						</select>
					</td>
				</tr>
				<tr>
					<td id="td_left"><label for="BARCODE">바코드</label></td>
					<td>
						<input type="text" style="font-weight : bold ;margin-left: 35px; width: 230px;" name="BARCODE" id="BARCODE" readonly="readonly" value="${product.BARCODE }">&nbsp;바코드 수정 불가
					</td>
				</tr>
				<tr>
					<td id="td_left"><label for="IN_UNIT_PRICE">입고단가</label></td>
					<td id="td_right"><input type="text" style="height: 30px; width: 350px; font-weight : bold ;margin-left: 35px;" name="IN_UNIT_PRICE" required="required" value="${product.IN_UNIT_PRICE }"></td>
				</tr>
				<tr>
					<td id="td_left"><label for="OUT_UNIT_PRICE">출고단가</label></td>
					<td id="td_right"><input type="text" style="height: 30px; width: 350px; font-weight : bold ;margin-left: 35px;" name="OUT_UNIT_PRICE" required="required" value="${product.OUT_UNIT_PRICE }"></td>
				</tr>
				<tr>
					<td id="td_left"><label for="PRODUCT_TYPE_CD">품목구분</label></td>
					<td id="td_right">
						<input type="text" name="PRODUCT_TYPE_CD" id="PRODUCT_TYPE_CD" readonly="readonly" required="required" style="height: 30px; width: 120px; font-weight : bold ;margin-left: 35px;"value="${product.PRODUCT_TYPE_CD }">
						<input type="text" name="PRODUCT_TYPE_NAME" id="PRODUCT_TYPE_NAME" readonly="readonly" required="required" style="height: 30px; width: 120px; font-weight : bold ;" value="${product.PRODUCT_TYPE_NAME }">
						<button type="button" onclick="window.open('Pd_type_SearchForm', 'searchPopup', 'width=500, height=500, left=600, top=400')"><b>&nbsp;&nbsp;품목구분 선택</b></button>
					</td>
				</tr>
				<tr>
					<td id="td_left"><label for="BUSINESS_NO">구매거래처</label></td>
					<td id="td_right">
						<input type="text" name="BUSINESS_NO" id="BUSINESS_NO" readonly="readonly" required="required" style="height: 30px; width: 120px; font-weight : bold ;margin-left: 35px;" value="${product.BUSINESS_NO }">
						<input type="text" name="CUST_NAME" id="CUST_NAME" readonly="readonly" required="required" style="height: 30px; width: 120px; font-weight : bold ;" value="${product.CUST_NAME }">
						<button type="button" onclick="window.open('Business_No_SearchForm', 'searchPopup', 'width=500, height=500, left=600, top=400')"><b>거래처목록 선택</b></button>
					</td>
				</tr>
				<tr>
					<td id="td_left"><label for="PRODUCT_IMAGE">대표이미지</label></td>
					<td id="td_right">
<!-- 						<input type="file" name="file" style="font-weight : bold ;"/> -->
						<div class="filebox">
		   					<input type="text" class="upload-name" placeholder="선택된 파일 없음" id="img_name" style="margin-left: 35px;">
		    				<label for="img">파일찾기</label> 
		   					<input type="file" name="file" id="img" onchange="selectFile(this.value)">
		   					<img width='100px' src='http://itwillbs3.cdn1.cafe24.com/profileImg/${product.PRODUCT_IMAGE }'>
						</div>
					</td>
				</tr>
				<tr>
					<td id="td_left"><label for="REMARKS">적요</label></td>
					<td id="td_right">
						<textarea rows="3" cols="55" name="REMARKS"  style="font-weight : bold ;margin-left: 35px;">${product.REMARKS }</textarea>
					</td>
				</tr>
			</table>
			<br>
			<div id="commandCell" align="center">
				<input type="submit" value="수정" id="b1" style="height: 30px; width: 100px; font-weight: bold;">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기" id="b2" style="height: 30px; width: 100px; font-weight: bold;">&nbsp;&nbsp;
				<input type="button" value="취소" id="b3" style="height: 30px; width: 100px; font-weight: bold;" onclick="history.back()">
			</div>
		</form>
		</div>
	</div>
</body>
</html>