<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
<link href="${pageContext.request.contextPath }/resources/css/hr.css" rel="stylesheet" type="text/css" />
<style>
*{
	color:#513e30;
}

/* input 은 숨겨주기 */
 input.chk_top{
   display:none;
  } 
/* input 바로 다음의 label */
input.chk_top + label{
  cursor:pointer;
  vertical-align: middle;
  line-height:20px; 
  font-size:16px;
  font-weight: bold;  
  color: #736643
 }

/* input 바로 다음의 label:before 에 체크하기 전 CSS 설정 */
 input.chk_top + label:before{ 
   content:""; 
   display: inline-block; 
   width:17px; 
   height:17px; 
   line-height:17px; 
   border:1px solid #cbcbcb; 
   vertical-align: middle;
   margin: 0px;
   }
  
/*  checked된 input 바로 다음의 label:before 에 체크 후 CSS 설정    */
 input.chk_top:checked + label:before{ 
   content:"\f00c"; 
   font-family:"Font Awesome 5 free"; 
    font-weight:900; 
   color:#fff; 
   background-color:#736643; 
   border-color:#c9b584; 
   font-size:13px; 
   text-align:center; 
   vertical-align: middle;
   } 
  

</style>
<script>
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
                  extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildinzgName);
               }

               // 우편번호와 주소 정보를 해당 필드에 넣는다.
               document.getElementById('EMP_POST_NO').value = data.zonecode;
               document.getElementById("EMP_ADDR").value = roadAddr;
               
           }
       }).open();
	<%-- 주소검색 팝업창 --%>
   }
<%----------------------------------------------------------------------------%>

	function emailSelect(domain){
		if(domain=='') {
			$("#EMP_EMAIL2").val(domain);
			$("#EMP_EMAIL2").prop("readOnly",false);
			$("#EMP_EMAIL2").css("background","white");
			
		} else {
			$("#EMP_EMAIL2").val(domain);
			$("#EMP_EMAIL2").prop("readOnly",true);
			$("#EMP_EMAIL2").css("background","#c9b584");
		}
	}

	/////처음 폼 들어왔을때 동작
	var empNo = '${param.empNo}';
	var loginEmp = '${sessionScope.empNo}';
	var isThisEmp = false;
	var chkArr = [0,0,0,0,0];
	if(empNo==loginEmp) {
		isThisEmp = true;
	}
	
	var priv = '${sessionScope.priv}';
	if(!isThisEmp && priv.charAt(2) !='1') {
		alert("잘못된 접근입니다.");
		history.back();
	}
	
	var nowPageURL = location.href.split("/")[location.href.split("/").length-1];
	var nowPage = nowPageURL.split("?")[0];
	$(function() {
		if(empNo=='') {
			$("#WORK_CD1").prop("selected", true);
			$("#WORK_CD").prop("disabled", true);
			
			$("#hrRegiTitle").text("| 사원 신규 등록");
			$(".editMode").css("display","inline");
			$("#hrFormSubmit").val("등록");
			$("img").css("display","none");
		} else {
			
			/// 수정이니까 DB에서 가져온값 그대로 넣어주기
			$("select").eq(0).val('${emp.GRADE_CD}').prop("selected", true);
			$("select").eq(2).val('${emp.WORK_CD}').prop("selected", true);

			///email select 
			var email2 = '${emp.EMP_EMAIL2}';
			for(var i=1; i<$("#EMP_EMAIL2_SEL").children().length; i++){
				var opt = $("#EMP_EMAIL2_SEL option").eq(i).val();
				if(email2 == opt) {
					$("#EMP_EMAIL2_SEL option").eq(i).prop("selected", true);
					$("#EMP_EMAIL2").prop("readOnly",true);
					$("#EMP_EMAIL2").css("background","#c9b584");
					break;
				} else {
					$("#EMP_EMAIL2_SEL option").eq(0).prop("selected", true);
				}
				
			}
			
			
			
			var empPrivStr = '${emp.PRIV_CD}';
			for(var i=0; i<5; i++){
				if(empPrivStr.charAt(i)=='1') {
					$(".chk_top").eq(i).prop("checked",true);
					chkArr[i]=1;
				}
			}
			
			////수정이면 editMode 활성화시키기
			if(nowPage=='HrEdit') {
				$("#hrRegiTitle").text("| 사원 정보 수정");
				$(".editMode").css("display","inline");
				$(".detailMode").css("display","none");
				$("#hrFormSubmit").val("수정하기");
				
				/////사원 관리 권한은 2번
				if(priv.charAt(2) != "1") {
					$("form input").prop("readOnly", true);
					$("form input").css("background", "#c9b584");
					////select는 readOnly가 없음!
					$("select").prop("disabled", true);
					$("form button").prop("disabled", true);
					$(".thisEmp").prop("readOnly", false);
					$(".thisEmp").css("background", "white");
				}
				
				
				
				
			} else if(nowPage=='HrDetail') {
				$("#hrRegiTitle").text("| 사원 상세 정보");
				$(".editMode").css("display","none");
				$(".detailMode").css("display","inline");
				$("#hrFormSubmit").val("수정");
				
			}
			
		}
		/////처음 폼 들어왔을때 동작 끝
	
		///권한 체크박스 동작
		var chkClick = false;
		$(".chk_top").on("click", function() {
			if(priv.charAt(2) != "1") {
				return false;
			}
			chkClick = true;
			if(this.checked) {
				chkArr[$(this).index()/2] = 1;
			} else {
				chkArr[$(this).index()/2] = 0;
			}
		});
		
		
		
		/////비밀번호 확인
		var checkPasswdResult = false;
		var checkPasswdSame = false;
		$("#EMP_PASS_NEW1").on("keyup", function() {
			let passwd = $(this).val();
			let regex = /^[\w!@#$%]{8,16}$/;
			if(!regex.exec(passwd)){
				$("#checkPasswdResult").html("8~16자 영문,숫자, 특수문자(_!@#$%) 필수").css("color","red");
				checkPasswdResult = false;
			} else {
				let upperCaseRegex = /[A-Z]/;
				let lowerCaseRegex = /[a-z]/;
				let numberCaseRegex = /[0-9]/;
				let specialCaseRegex = /[_!@#$%]/;
				let count =0;
				if(upperCaseRegex.exec(passwd)) {
					count++;
				}
				if(lowerCaseRegex.exec(passwd)) {
					count++;
				}
				if(numberCaseRegex.exec(passwd)) {
					count++;
				}
				if(specialCaseRegex.exec(passwd)) {
					count++;
				}
				
				switch (count) {
				case 1:
					$("#checkPasswdResult").html("사용불가능한 비밀번호").css("color","red");
					checkPasswdResult = false;
					break;
				case 2:
					$("#checkPasswdResult").html("위험한 비밀번호").css("color","orange");
					checkPasswdResult = true;
					break;
				case 3:
					$("#checkPasswdResult").html("보통 비밀번호").css("color","green");
					checkPasswdResult = true;
					break;
				case 4:
					$("#checkPasswdResult").html("안전한 비밀번호").css("color","blue");
					checkPasswdResult = true;
					break;
				}
			}
			
			if($("#EMP_PASS_NEW2").val()==$(this).val()) {
				$("#checkPasswdSame").html("비밀번호가 일치합니다.").css("color","blue");
				checkPasswdSame=true;
			} else {
				$("#checkPasswdSame").html("비밀번호가 일치하지 않습니다.").css("color","red");
				checkPasswdSame=false;
			}
		});
		
		$("#EMP_PASS_NEW2").on("keyup", function() {
			if($("#EMP_PASS_NEW1").val()==$(this).val()) {
				$("#checkPasswdSame").html("비밀번호가 일치합니다.").css("color","blue");
				checkPasswdSame=true;
			} else {
				$("#checkPasswdSame").html("비밀번호가 일치하지 않습니다.").css("color","red");
				checkPasswdSame=false;
			}
		});
	/////비밀번호 확인 끝
		
		
		
		////등록, 수정 버튼 눌렀을때 동작
		$("#hrFormSubmit").on("click", function() {
			
			if(nowPage == 'HrDetail') {
				location.href="HrEdit?empNo=${param.empNo}";
				return false;
			}
			
			if($("#EMP_NAME").val()=='') {
				alert("이름을 작성해주세요.");
				return false;
			}
			if($("#GRADE_CD").val()=='') {
				alert("직급을 선택해주세요.");
				return false;
			}
			
			if($("#EMP_TEL1").val().length>0 || $("#EMP_TEL2").val().length>0 || $("#EMP_TEL3").val().length>0) {
				if($("#EMP_TEL1").val().length==0 || $("#EMP_TEL2").val().length==0 || $("#EMP_TEL3").val().length==0) {
					alert("연락처(개인)에 미 입력된 칸이 있습니다.");
					return false;
				}
			}
			
			if($("#EMP_DTEL1").val().length>0 || $("#EMP_DTEL2").val().length>0 || $("#EMP_DTEL3").val().length>0) {
				if($("#EMP_DTEL1").val().length==0 || $("#EMP_DTEL2").val().length==0 || $("#EMP_DTEL3").val().length==0) {
					alert("연락처(사무실)에 미 입력된 칸이 있습니다.");
					return false;
				}
			}
			
			if($("#EMP_EMAIL1").val()=='' || $("#EMP_EMAIL2").val()=='') {
				alert("이메일에 미 입력된 칸이 있습니다.");
				return false;
			}
			
			if($("#HIRE_DATE").val()=='') {
				alert("입사 날짜를 선택해주세요.");
				return false;
			}
			
			
			///신규 등록 및 관리자가 수정할때엔 비밀번호 필요없음
			if(isThisEmp) {
				if(!checkPasswdResult) {
					alert("변경할 비밀번호가 안전하지 않습니다.");
					return false;
				}
				
				if(!checkPasswdSame) {
					alert("변경할 비밀번호가 일치하지 않습니다.");
					return false;
				}
			}

			
			///권한 배열 string으로 합친 후 hidden 넘겨주기
			if(chkClick) {
				var privStr = "";
				for(var i of chkArr) {
					privStr += i;
				}
				$("#PRIV_CD").val(privStr);
				alert(privStr);
			}
			
			////직전에 disable 된거 풀어주기
			$("select").prop("disabled", false);
			if(empNo=='') {
				hrForm.action = "HrRegistPro";
			} else {
				hrForm.action = "HrEditPro";
			}
			hrForm.submit();
		});
		
		
		
	});
	

</script>
</head>
<body style="border-left: solid 10px; border-color: #BDBDBD;">
	<jsp:include page="../inc/top.jsp"></jsp:include>
	<div style="display: flex; margin-bottom:100px; ">
		<div style="width: 300px; margin-top: 0px; margin-right: 0px;">
			<jsp:include page="../inc/hr_left.jsp"></jsp:include>
		</div>
		<!-- 여기서부터 본문-->
		<form name="hrForm" id="hrForm" action="HrRegistPro" method="post" enctype="multipart/form-data">
		<h1 id="hrRegiTitle"  style="width: 1300px;"></h1>
		<div align="center" style="width: 1300px;">
			<table class="regi_table" style="text-align: center; border: solid 1px; width: 600px;">
				<tr>
					<th align="right" width="150">이름</th>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<span class="detailMode">${emp.EMP_NAME}</span>
						<input type="text" class="editMode" id="EMP_NAME" name="EMP_NAME" required="required" value="${emp.EMP_NAME}" >
					</td>
				</tr>
				<c:if test="${param.empNo!=null}">
				<tr>
					<th align="right" width="150">사번</th>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<span class="detailMode">${param.empNo}</span>
						<input type="text" class="editMode" id="EMP_NUM" name="EMP_NUM" required="required" value="${param.empNo}" >
					</td>
				</tr>
				<!-- 당사자가 들어왔으면 비밀번호 변경 보이게 -->
				<c:if test="${emp.EMP_NUM eq sessionScope.empNo}">
					<tr>
						<th align="right" width="150">기존 비밀번호</th>
						<td align="left" >&nbsp;&nbsp;&nbsp;
							<input type="password" class="thisEmp" id="EMP_PASS" name="EMP_PASS" >
						</td>
					</tr>
					<tr>
						<th align="right" width="150">신규 비밀번호</th>
						<td align="left" >&nbsp;&nbsp;&nbsp;
							<input type="password" class="thisEmp" id="EMP_PASS_NEW1" name="EMP_PASS_NEW" >
							<span id="checkPasswdResult"></span>
						</td>
					</tr>
					<tr>
						<th align="right" width="150">신규 비밀번호 확인</th>
						<td align="left" >&nbsp;&nbsp;&nbsp;
							<input type="password" class="thisEmp" id="EMP_PASS_NEW2" >
							<span id="checkPasswdSame"></span>
						</td>
					</tr>
				</c:if>
				</c:if>
				<tr>
					<th align="right" >부서</th>
					<td align="left" >&nbsp;&nbsp;&nbsp;
					<span class="detailMode">${emp.DEPT_NAME}</span>
					<div class="editMode">
						<input type="text" name="DEPT_CD" id="DEPT_CD" readonly="readonly" style="width: 50px;" value="${emp.DEPT_CD}">
						<input type="text" name="DEPT_NAME" id="DEPT_NAME" readonly="readonly" style="width: 80px;" value="${emp.DEPT_NAME}">
						<button type="button" onclick="window.open('DepartSearchForm', 'searchPopup', 'width=500, height=500, left=600, top=400')">부서 선택</button>
					</div>
					</td>
				</tr>
				<tr>
					<th align="right" >직급</th>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<span class="detailMode">${emp.GRADE_NAME}</span>
						<select id="GRADE_CD" name="GRADE_CD" class="editMode" style="text-align: center;">
							<option value="">==직급==</option>
							<c:forEach items="${gradeList}" var="grade">
								<option value="${grade.GRADE_CD}">${grade.GRADE_NAME}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th align="right" >연락처(개인)</th>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<span class="detailMode">${emp.EMP_TEL}</span>
						<div class="editMode">
							<input type="text" class="thisEmp" id="EMP_TEL1" name="EMP_TEL1" style="width: 50px;" oninput="this.value=this.value.replace(/[^0-9]/g, '');" value="${emp.EMP_TEL1}"> -
							<input type="text" class="thisEmp" id="EMP_TEL2" name="EMP_TEL2" style="width: 50px;"oninput="this.value=this.value.replace(/[^0-9]/g, '');" value="${emp.EMP_TEL2}"> -
							<input type="text" class="thisEmp" id="EMP_TEL3" name="EMP_TEL3" style="width: 50px;"oninput="this.value=this.value.replace(/[^0-9]/g, '');" value="${emp.EMP_TEL3}">
						</div>
					</td>
				</tr>
				<tr>
					<th align="right" >연락처(사무실)</th>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<span class="detailMode">${emp.EMP_DTEL}</span>
						<div class="editMode">
							<input type="text" class="thisEmp" id="EMP_DTEL1" name="EMP_DTEL1" style="width: 50px;" oninput="this.value=this.value.replace(/[^0-9]/g, '');" value="${emp.EMP_DTEL1}"> -
							<input type="text" class="thisEmp" id="EMP_DTEL2" name="EMP_DTEL2" style="width: 50px;" oninput="this.value=this.value.replace(/[^0-9]/g, '');" value="${emp.EMP_DTEL2}"> -
							<input type="text" class="thisEmp" id="EMP_DTEL3" name="EMP_DTEL3" style="width: 50px;" oninput="this.value=this.value.replace(/[^0-9]/g, '');" value="${emp.EMP_DTEL3}">
						</div>
					</td>
				</tr>
				<tr>
					<th align="right" >E-mail</th>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<span class="detailMode">${emp.EMP_EMAIL}</span>
						<div class="editMode">
							<input type="text" id="EMP_EMAIL1" name="EMP_EMAIL1" style="width: 100px;" value="${emp.EMP_EMAIL1}"> @
							<input type="text" id="EMP_EMAIL2" name="EMP_EMAIL2" style="width: 100px;" value="${emp.EMP_EMAIL2}">
							<select onchange="emailSelect(this.value)" id="EMP_EMAIL2_SEL">
								<option value="">직접 입력</option>
								<option value="naver.com">naver.com</option>
								<option value="gmail.com">gmail.com</option>
								<option value="nate.com">nate.com</option>
							</select>
						</div>
					</td>
				</tr>
				<tr>
					<th align="right">우편번호</th>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<span class="detailMode">${emp.EMP_POST_NO}</span>
						<div class="editMode">
							<input type="text" id="EMP_POST_NO" name="EMP_POST_NO" style="width: 100px;" readonly="readonly" value="${emp.EMP_POST_NO}">
							<button id="postbutton" onclick="execDaumPostcode()">우편번호 찾기</button>
						</div>
					</td>
				</tr>
				<tr>
					<th align="right" >주소</th>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<span class="detailMode">${emp.EMP_ADDR}</span>
						<input type="text" class="editMode" id="EMP_ADDR" name="EMP_ADDR" style="width: 300px;" readonly="readonly" value="${emp.EMP_ADDR}">
					</td>
				</tr>
				<tr>
					<th align="right" >상세주소</th>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<span class="detailMode">${emp.EMP_ADDR_DETAIL}</span>
						<input type="text" class="editMode" id="EMP_ADDR_DETAIL" name="EMP_ADDR_DETAIL" style="width: 300px;" value="${emp.EMP_ADDR_DETAIL}">
					</td>
				</tr>
				<tr>
					<th align="right" >입사일</th>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<span class="detailMode">${emp.HIRE_DATE}</span>
						<input type="date" class="editMode" id="HIRE_DATE" name="HIRE_DATE" required="required" value="${emp.HIRE_DATE}">
					</td>
				</tr>
				<tr>
					<th align="right" >재직여부</th>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<span class="detailMode">${emp.WORK_TYPE}</span>
						<div class="editMode">
							<select id="WORK_CD" name="WORK_CD" style="text-align: center;">	
								<option id="WORK_CD0" value="">==선택==</option>
								<option id="WORK_CD1" value="1">재직</option>
								<option id="WORK_CD2"  value="2">휴직</option>
								<option id="WORK_CD3"  value="3">퇴사</option>
							</select>
						</div>
					</td>
				</tr>
				<tr>
					<th align="right" >권한</th>
					<td align="left" >
						<input type="checkbox" class="chk_top" id="chk_top1" />
  						<label for="chk_top1">&nbsp;기본등록</label><br>
						<input type="checkbox" class="chk_top" id="chk_top2" />
  						<label for="chk_top2">&nbsp;사원조회</label>
						<input type="checkbox" class="chk_top" id="chk_top3" />
  						<label for="chk_top3">&nbsp;사원관리</label><br>
						<input type="checkbox" class="chk_top" id="chk_top4" />
  						<label for="chk_top4">&nbsp;재고조회</label>
						<input type="checkbox" class="chk_top" id="chk_top5" />
  						<label for="chk_top5">&nbsp;재고관리</label>
  						<input type="hidden" name="PRIV_CD" id="PRIV_CD" value="${emp.PRIV_CD}">
					</td>
				</tr>
				<tr>
					<th align="right" >사진이미지</th>
					<td align="left" style="vertical-align: middle;">&nbsp;&nbsp;&nbsp;
						<input type="file" class = "thisEmp" id="registPHOTO" name="registPHOTO" style="">
						<input type="hidden" name="PHOTO" value="${emp.PHOTO}" >
						<span><img src="${pageContext.request.contextPath}/resources/img/${emp.PHOTO}" width="150"></span>
					</td>
				</tr>
			</table>
		<input type="button" class="hrFormBtn" id="hrFormSubmit" style="width: 200px; margin-top: 20px; font-size: 16px; font-weight: bold;">
		<input type="button" class="hrFormBtn" style="width: 200px; margin-top: 20px; font-size: 16px; font-weight: bold;" onclick="history.back()" value="돌아가기">
		</div>
		</form>
		<!-- 여기까지 본문-->
	</div>
</body>
</html>
