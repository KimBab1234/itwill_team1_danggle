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
  width:17px;
  height:17px;
  line-height:17px;
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
                  extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
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
			$("#EMP_EMAIL2").css("background","lightgray");
		}
	}

	/////처음 폼 들어왔을때 동작
	var empNo = '${param.empNo}';
	var nowPageURL = location.href.split("/")[location.href.split("/").length-1];
	var nowPage = nowPageURL.split("?")[0];
	$(function() {
		if(empNo=='') {
			$("#WORK_CD1").prop("selected", true);
			$("#WORK_CD").prop("disabled", true);
			
			$("#hrRegiTitle").text("| 사원 신규 등록");
			$(".editMode").css("display","inline");
			$("#hrFormSubmit").text("등록");
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
					$("#EMP_EMAIL2").css("background","lightgray");
					break;
				} else {
					$("#EMP_EMAIL2_SEL option").eq(0).prop("selected", true);
				}
				
			}
			
			
			
			var empPrivStr = '${emp.PRIV_CD}';
			for(var i=0; i<5; i++){
				if(empPrivStr.charAt(i)=='1') {
					$(".chk_top").eq(i).prop("checked",true);
				}
			}
			
			////수정이면 editMode 활성화시키기
			if(nowPage=='HrEdit') {
				$("#hrRegiTitle").text("| 사원 정보 수정");
				$(".editMode").css("display","inline");
				$(".detailMode").css("display","none");
				$("#hrFormSubmit").text("수정 완료");
			} else if(nowPage=='HrDetail') {
				$("#hrRegiTitle").text("| 사원 상세 정보");
				$(".editMode").css("display","none");
				$(".detailMode").css("display","inline");
				$("#hrFormSubmit").text("수정하기");
				
			}
			
		}
		/////처음 폼 들어왔을때 동작 끝
	
		///권한 체크박스 동작
		var chkArr = [0,0,0,0,0];
		var chkClick = false;
		$(".chk_top").on("click", function() {
			chkClick = true;
			if(this.checked) {
				chkArr[$(this).index()/2] = 1;
			} else {
				chkArr[$(this).index()/2] = 0;
			}
		});
		
		
		
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
			
		
			///권한 배열 string으로 합친 후 hidden 넘겨주기
			
			if(chkClick) {
				var privStr = "";
				for(var i of chkArr) {
					privStr += i;
				}
				$("#PRIV_CD").val(privStr);
			} else {
				
			}
			
			
			
			if(empNo=='') {
				hrForm.action = "HrRegistPro";
				hrForm.submit();
			} else {
				hrForm.action = "HrEditPro";
				hrForm.submit();
			}
		});
		
	});
	

</script>
</head>
<body>
	<jsp:include page="../inc/top.jsp"></jsp:include>
	<div style="display: flex;" align="center">
		<div style="width: 300px; margin-top: 0px; margin-right: 0px; border-right:solid 1px; border-color: #BDBDBD;">
			<jsp:include page="../inc/hr_left.jsp"></jsp:include>
		</div>
		<!-- 여기서부터 본문-->
		<form name="hrForm" id="hrForm" action="HrRegistPro" method="post" enctype="multipart/form-data">
		<div align="center" style="width: 1300px;">
			<h1 align="left" style="margin-left: 300px;" id="hrRegiTitle"></h1>
			<div align="left" style="margin-left: 300px;"><img src="${pageContext.request.contextPath}/resources/upload/${emp.PHOTO}" width="200"></div>
			<table style="text-align: center; border: solid 1px; width: 700px;">
				<tr>
					<td align="right" width="150">이름</td>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<span class="detailMode">${emp.EMP_NAME}</span>
						<input type="text" class="editMode" id="EMP_NAME" name="EMP_NAME" required="required" value="${emp.EMP_NAME}" >
						<input type="hidden" name="EMP_NUM" value = "${param.empNo}">
					</td>
				</tr>
				<tr>
					<td align="right" >부서</td>
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
					<td align="right" >직급</td>
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
					<td align="right" >연락처(개인)</td>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<span class="detailMode">${emp.EMP_TEL}</span>
						<div class="editMode">
							<input type="text" id="EMP_TEL1" name="EMP_TEL1" style="width: 50px;" oninput="this.value=this.value.replace(/[^0-9]/g, '');" value="${emp.EMP_TEL1}"> -
							<input type="text" id="EMP_TEL2" name="EMP_TEL2" style="width: 50px;"oninput="this.value=this.value.replace(/[^0-9]/g, '');" value="${emp.EMP_TEL2}"> -
							<input type="text" id="EMP_TEL3" name="EMP_TEL3" style="width: 50px;"oninput="this.value=this.value.replace(/[^0-9]/g, '');" value="${emp.EMP_TEL3}">
						</div>
					</td>
				</tr>
				<tr>
					<td align="right" >연락처(사무실)</td>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<span class="detailMode">${emp.EMP_DTEL}</span>
						<div class="editMode">
							<input type="text" id="EMP_DTEL1" name="EMP_DTEL1" style="width: 50px;" oninput="this.value=this.value.replace(/[^0-9]/g, '');" value="${emp.EMP_DTEL1}"> -
							<input type="text" id="EMP_DTEL2" name="EMP_DTEL2" style="width: 50px;" oninput="this.value=this.value.replace(/[^0-9]/g, '');" value="${emp.EMP_DTEL2}"> -
							<input type="text" id="EMP_DTEL3" name="EMP_DTEL3" style="width: 50px;" oninput="this.value=this.value.replace(/[^0-9]/g, '');" value="${emp.EMP_DTEL3}">
						</div>
					</td>
				</tr>
				<tr>
					<td align="right" >E-mail</td>
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
					<td align="right">우편번호</td>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<span class="detailMode">${emp.EMP_POST_NO}</span>
						<div class="editMode">
							<input type="text" id="EMP_POST_NO" name="EMP_POST_NO" style="width: 100px;" readonly="readonly" value="${emp.EMP_POST_NO}">
							<input type="button" id="postbutton" onclick="execDaumPostcode()" value="우편번호 찾기" >
						</div>
					</td>
				</tr>
				<tr>
					<td align="right" >주소</td>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<span class="detailMode">${emp.EMP_ADDR}</span>
						<input type="text" class="editMode" id="EMP_ADDR" name="EMP_ADDR" style="width: 300px;" readonly="readonly" value="${emp.EMP_ADDR}">
					</td>
				</tr>
				<tr>
					<td align="right" >상세주소</td>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<span class="detailMode">${emp.EMP_ADDR_DETAIL}</span>
						<input type="text" class="editMode" id="EMP_ADDR_DETAIL" name="EMP_ADDR_DETAIL" style="width: 300px;" value="${emp.EMP_ADDR_DETAIL}">
					</td>
				</tr>
				<tr>
					<td align="right" >입사일</td>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<span class="detailMode">${emp.HIRE_DATE}</span>
						<input type="date" class="editMode" id="HIRE_DATE" name="HIRE_DATE" required="required" value="${emp.HIRE_DATE}">
					</td>
				</tr>
				<tr>
					<td align="right" >재직여부</td>
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
					<td align="right" >권한</td>
					<td align="left" >&nbsp;&nbsp;&nbsp;
						<input type="checkbox" class="chk_top" id="chk_top1" />
  						<label for="chk_top1">&nbsp;기본등록</label>
						<input type="checkbox" class="chk_top" id="chk_top2" />
  						<label for="chk_top2">&nbsp;사원조회</label>
						<input type="checkbox" class="chk_top" id="chk_top3" />
  						<label for="chk_top3">&nbsp;사원관리</label>
						<input type="checkbox" class="chk_top" id="chk_top4" />
  						<label for="chk_top4">&nbsp;재고조회</label>
						<input type="checkbox" class="chk_top" id="chk_top5" />
  						<label for="chk_top5">&nbsp;재고관리</label>
  						<input type="hidden" name="PRIV_CD" id="PRIV_CD" value="${emp.PRIV_CD}">
					</td>
				</tr>
				<tr height="10px;"></tr>
				<tr>
					<td align="right" >사진이미지</td>
					<td align="left" style="vertical-align: middle;">&nbsp;&nbsp;&nbsp;
						<input type="file"  id="registPHOTO" name="registPHOTO" style="padding:0px;margin:0px;">
						<input type="hidden" name="PHOTO" value="${emp.PHOTO}" >
					</td>
				</tr>
				<tr height="10px;"></tr>
			</table>
		</div>
		<button type="button" id="hrFormSubmit" style="width: 200px; margin-top: 20px; font-size: 16px; font-weight: bold;"></button>
		<button type="button" style="width: 200px; margin-top: 20px; font-size: 16px; font-weight: bold;" onclick="history.back()">돌아가기</button>
		</form>
		<!-- 여기까지 본문-->
	</div>
</body>
</html>
