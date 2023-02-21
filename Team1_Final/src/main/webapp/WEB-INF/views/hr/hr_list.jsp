<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath }/resources/css/hr.css" rel="stylesheet" type="text/css" />
<script src="https://kit.fontawesome.com/5fad4a5f29.js" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<style>

.here, .fa-angles-right, .fa-angles-left {
	border: 1px solid;
	margin: 0px;
	width: 40px;
	height: 40px;
	display: table-cell;
	vertical-align: middle;
	text-align: center;
}

.here, .fa-angles-right, .fa-angles-left:hover {
	cursor: pointer;
	
}

</style>
<title>사원 목록</title>
<script>
	
	var pageList = {};
	
	///처음 들어왔을때 기본값 1
	var pageNum;
	var pageListLimit = 5;
	var startPage;
	var endPage;
	var maxPage;
	var	searchType = '';
	var keyword = '';
	var workType;

	////로그인 유무 및 권한 확인
	///기본등록(0), 사원조회(1), 사원관리(2), 재고조회(3), 재고관리(4)
	var loginEmp = '${sessionScope.empNo}';
	var priv = '${sessionScope.priv}';
	if(loginEmp=='') {
		alert("로그인 후 이용하세요.");
		location.href="./Login";
	}
	////로그인 유무 및 권한 확인 끝
	
	
	$(function() {
		
		workType=1;
		
		////재직 or 휴,퇴직 선택시 목록 변경
		$(".choice").on("click", function() {
		
			if(workType==$(this).attr("id")){
				return false;
			}

			workType = $(this).attr("id");
			$(".choiceSelect").attr("class","choice");
			$(this).attr("class","choiceSelect");
			
			searchType = '';
			keyword = '';
			$("#keyword").val("");
			$("#searchType").val('').prop("selected",true);
			
			///pageNum은 일부러 null로주기
			getPageList(null);
		});
		$("#1").attr("class","choiceSelect");
		
		getPageList(1);
	});
	
	function getEmpList(toPageNum) {

		///같은 페이지 들어오면 아무일 없음
		if(pageNum == toPageNum) {
			return false;
		}
		////새로운 페이지로 바꿔주기
		if(toPageNum==null) {
			toPageNum = 1;
		}
		pageNum = toPageNum;
		$.ajax({
			url: 'HrList',
			type: 'post',
			data: {
				pageNum : toPageNum,
				workType : workType,
				searchType : searchType,
				keyword : keyword
			},
			dataType : 'json',
			success : function(response) {
				emp = response.jsonEmp;
				/// 테이블 초기화
				$("table tbody").empty();
				/// 데이터 뿌리기
				if(emp.length==0) {
					$(".regi_table").append("<tr><td colspan='8'>검색 결과가 없습니다.</td></tr>");
				} else {
					for(var i=0; i<emp.length; i++) {
						var img;
						if(emp[i].PHOTO!=''){
							img = '<img src="https://itwill220823team1.s3.ap-northeast-2.amazonaws.com/profileImg/'+emp[i].PHOTO+'" style="width: 120px; height: 120px; object-fit: cover;">';
						} else {
							img = "사진 없음";
						}
						var deptName;
						if(emp[i].DEPT_NAME!=undefined){
							deptName = emp[i].DEPT_NAME;
						} else {
							deptName = "-";
						}
						$(".regi_table").append('<tr class="empListAdd" style="height:100px; width:150"><td width="150">'+img+'</td>'
								+'<td width="150">'+emp[i].EMP_NUM+'</td>'
								+'<td width="120">'+emp[i].EMP_NAME+'</td>'
								+'<td width="150">'+deptName+'</td>'
								+'<td width="70">'+emp[i].GRADE_NAME+'</td>'
								+'<td width="150">'+emp[i].EMP_TEL+'</td>'
								+'<td width="250">'+emp[i].EMP_EMAIL+'</td>'
								+'<td width="200"><button type="button" class="hrFormBtn" style="width: 150px; height:30px;  font-size:18px; " onclick="location.href=\'HrDetail?empNo='+emp[i].EMP_NUM+'\'">상세정보</button>'
								+'<br><button type="button"  class="hrFormBtn" style="width: 150px; height:30px;  font-size:18px; margin-top: 10px;" onclick="location.href=\'HrEdit?empNo='+emp[i].EMP_NUM+'\'">수정</button></td>'
								+'</tr>');
					}
				}
				
			}
		});
	}
	
	////페이지 목록 뿌리기 (가지고있는 데이터로 뿌리기만 함)
	function pageListChange(toPage) {
		
		if(toPage==null) {
			toPage = 1;
		}
		
		///pageNum 변경
		pageNum = toPage;
		
		/// pageList 영역 초기화
		$("#pageListSection").html("");
		
		/// 데이터 뿌리기
		startPage = (pageNum-1) / pageListLimit * pageListLimit + 1;
		endPage = startPage + pageListLimit - 1;
		maxPage = pageList.maxPage; 

		if(endPage > maxPage) {
			endPage = maxPage;
		}
		if(pageNum > pageListLimit) {
			$("#pageListSection").append('<i class="fas fa-solid fa-angles-left"onclick="arrowClick('+(startPage-pageListLimit)+')"></i>');
		}
		for(var i=startPage; i <= endPage; i++) {
			$("#pageListSection").append('<div class="here" onclick="hereClick('+i+')">'+ i + '</div>');
		}
		if(endPage < maxPage) {
			$("#pageListSection").append('<i class="fas fa-solid fa-angles-right" onclick="arrowClick('+(endPage+1)+')"></i>');
		}
	}
	
	
	////페이징처리
	function getPageList(pageNum) {
		$.ajax({
			url: 'HrListPage',
			type: 'post',
			data: {
				pageNum : pageNum,
				workType : workType,
				searchType : searchType,
				keyword : keyword
			},
			dataType : 'json',
			success : function(response) {
				pageList = JSON.parse(response.jsonPage);
				pageListChange(pageNum);
			}
		});
		getEmpList(pageNum);
		pageListChange(pageNum);
	}
	
	
	function hereClick(herePage){
		getEmpList(herePage);
	};
	
	function arrowClick(herePage){
		getPageList(herePage);
	};
	
	function searchHrList() {
		searchType = $("#searchType").val();
		if(searchType=='') {
			alert("검색 유형을 선택하세요.");
			return false;
		} else {
			keyword = $("#keyword").val();
			getPageList(null);
		}
	}
	
	 function searchEnter() {
			if(window.event.keyCode == 13) {
				searchHrList();
			}
		}

</script>
<style>
option {
	font-weight: bold;
}

</style>
</head>
<body>
	<jsp:include page="../inc/top.jsp"></jsp:include>
	<div style="width: 1800px; display: flex; min-height: 1300px;"  align="center">
		<div style="width: 300px; margin-top: 0px; margin-right: 0px;">
			<jsp:include page="../inc/hr_left.jsp"></jsp:include>
		</div>
		<!-- 여기서부터 본문-->
		<div style="width: 1500px;"  align="center" >
		<h1 align="left" style="width: 1500px; text-align: left; margin-left: 100px;">| 사원 조회</h1>
		<div style="display: flex; width: 1300px; text-align: right" align="right">
			<div class="choice" id="1">재직</div><div class="choice" id="2">휴직</div><div class="choice" id="3">퇴직</div>
			<form method="post" onsubmit="return false;" style="width: 1400px; margin-bottom:10px; text-align: right;">
				<select id="searchType" name="searchType" style="text-align: center; font-weight: bold; width: 100px; height: 35px;">
					<option value="">검색 유형</option>
					<option value="EMP_NUM">사번</option>
					<option value="EMP_NAME">사원명</option>
					<option value="DEPT_NAME">부서명</option>
				</select>
				<input type="text" name="keyword" id="keyword" style="height: 35px; font-weight: bold;" onkeyup="searchEnter()">
				<button type="button" class="hrFormBtn" onclick="searchHrList()" style="width: 100px; height:30px; font-size:18px;" >
					<i class="fa-solid fa-magnifying-glass" style="color: #fff; margin: 0;"></i>&nbsp;검색
				</button>
			</form>
		</div>
		<table border="1" class="regi_table" style="text-align: center; width: 1300px; font-size: 20px;">
			<thead>
				<tr>
					<th width="150">사진</th>
					<th width="150">사번</th>
					<th width="120">이름</th>
					<th width="150">부서</th>
					<th width="70">직급</th>
					<th width="150">연락처</th>
					<th width="250">E-MAIL</th>
					<th width="200">버튼</th>
				</tr>
			</thead>
			<tbody>
			
			</tbody>
		</table>
		<div align="right" style="width: 1300px;">
		<button type="button" class="hrFormSpeBtn" style="width: 200px; height:40px;  font-size:20px; margin-top: 10px;" onclick="location.href='HrRegist'">신규 등록</button>
		</div>
		
		<!-- 페이지 목록 부분 -->
        <div id="pageListSection" align="center" style="width:1500px; font-weight: bold; display:flex table; font-size: 20px;">
			
		</div> 
        <!-- 페이지 목록 끝-->
		</div>
		<!-- 여기까지 본문-->
	</div>
</body>
</html>
