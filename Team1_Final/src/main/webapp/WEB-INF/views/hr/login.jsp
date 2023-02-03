<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<style>
/*input 은 숨겨주기*/
input.chk_top{
  display:none;
  }
/*input 바로 다음의 label*/
input.chk_top + label{
  cursor:pointer;
 }

/*input 바로 다음의 label:before 에 체크하기 전 CSS 설정*/
input.chk_top + label:before{
  content:"";
  display:inline-block;
  width:20px;
  height:20px;
  line-height:20px;
  border:1px solid #cbcbcb;
  vertical-align:middle;/*체크 전과 체크 후 높이 차이 때문에 설정*/
  }
  
/*checked된 input 바로 다음의 label:before 에 체크 후 CSS 설정*/  
input.chk_top:checked + label:before{
  content:"\f00c";/*폰트어썸 유니코드*/
  font-family:"Font Awesome 5 free"; /*폰트어썸 아이콘 사용*/
  font-weight:900;/*폰트어썸 설정*/
  color:#fff;
  background-color:#000;
  border-color:#000;
  font-size:13px;
  text-align:center;
  }
  

</style>
<title>Insert title here</title>
<script>

///localStorage에 저장된 email 가져오기
var email1 = localStorage.getItem("email1");
var email2 = localStorage.getItem("email2");

$(function() {
	
	if(email1 != null) {
		$("#EMP_EMAIL1").val(email1);
		$("#EMP_EMAIL2").val(email2);
		$("#rememberEmailChk").prop("checked",true);
		///email select 
		for(var i=1; i<$("#EMP_EMAIL2_SEL").children().length; i++){
			var opt = $("#EMP_EMAIL2_SEL option").eq(i).val();
			if(email2 == opt) {
				$("#EMP_EMAIL2_SEL option").eq(i).prop("selected", true);
				$("#EMP_EMAIL2").prop("readOnly",true);
				$("#EMP_EMAIL2").css("background","lightgray");
				break;
			} else {
				$("#EMP_EMAIL2_SEL option").eq(0).prop("selected", true);
			}
			
		}
	}
	
	
	
});
	function emailSelect(domain){
		if(domain=='') {
			$("#EMP_EMAIL2").val(domain);
			$("#EMP_EMAIL2").prop("readOnly",false);
			$("#EMP_EMAIL2").css("background","white");
			
		} else {
			$("#EMP_EMAIL2").val(domain);
			$("#EMP_EMAIL2").prop("readOnly",true);
			$("#EMP_EMAIL2").css("background","lightgray");
		}
	}
	/////로그인 버튼 눌렀을때 이메일 기억 및 폼 전송
	function loginSubmit() {
		if($("#rememberEmailChk").prop("checked")) {
			localStorage.setItem("email1", $("#EMP_EMAIL1").val());
			localStorage.setItem("email2", $("#EMP_EMAIL2").val());
		} else {
			localStorage.removeItem("email1");
			localStorage.removeItem("email2");
		}
		loginForm.submit();
	}




</script>
</head>
<body>
<jsp:include page="../inc/top.jsp"></jsp:include>
	<div style="display: flex;" align="center">
		<div style="width: 300px; margin-top: 0px; margin-right: 0px;">
			<jsp:include page="../inc/hr_left.jsp"></jsp:include>
		</div>
		<!-- 여기서부터 본문-->
		<form name="loginForm"  action="LoginPro" method="post" >
		<div align="center" style="width: 1300px;">
			<h1 align="left">로그인</h1>
			<table style="text-align: center; border: solid 1px; width: 500px;">
			<tr>
				<td align="right" >E-mail</td>
				<td align="left" >&nbsp;&nbsp;&nbsp;
					<input type="text" id="EMP_EMAIL1" name="EMP_EMAIL1" style="width: 100px;"> @
					<input type="text" id="EMP_EMAIL2" name="EMP_EMAIL2" style="width: 100px;">
					<select onchange="emailSelect(this.value)" id="EMP_EMAIL2_SEL">
						<option value="">직접 입력</option>
						<option value="naver.com">naver.com</option>
						<option value="gmail.com">gmail.com</option>
						<option value="nate.com">nate.com</option>
					</select>
				</td>
			</tr>
			<tr>
				<td align="right" >비밀번호</td>
				<td align="left" >&nbsp;&nbsp;&nbsp;
					<input type="password" id="EMP_PASS" name="EMP_PASS" style="width: 200px;">
				</td>
			</tr>
			</table>
		</div>
		<table style="margin: 20px; font-size: 20px;">
		<tr>
			<td align="center" style="vertical-align: middle;">
				<input type="checkbox" class="chk_top" id="rememberEmailChk" style="width: 250px;"/>
				<label for="rememberEmailChk" style="vertical-align: bottom;">&nbsp;&nbsp;&nbsp;이메일 기억</label>
			</td>
		</tr>
		</table>
		<button type="button" onclick="loginSubmit()" style="margin-top: 10px;">로그인</button>
		</form>
	</div>
				
</body>
</html>