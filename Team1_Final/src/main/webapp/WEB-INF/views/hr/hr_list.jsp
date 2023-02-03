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

.here {
	margin : 20px;
}

.here:hover {
	cursor: pointer;
	
}

</style>
<title>Insert title here</title>
<script>
	
	

	$(function() {
		var pageList = {};
		
		///처음 들어왔을때 기본값 1
		var pageNum = 1;
		var pageListLimit = 5;
		var startPage;
		var endPage;
		var maxPage;
		var	searchType;
		var keyword;
		
		function getEmpList(toPageNum, searchType, keyword) {
			////새로운 페이지로 바꿔주기
			pageNum = toPageNum;
			$.ajax({
				url: 'HrList',
				type: 'get',
				data: {
					pageNum : toPageNum,
					searchType : searchType,
					keyword : keyword
				},
				dataType : 'json',
				success : function(response) {
					emp = response.jsonEmp;
					/// 테이블 초기화
					$("tbody").empty();
					/// 데이터 뿌리기
					if(emp.length==0) {
						$("#searchNone").html("<h3>검색 결과가 없습니다.</h3>");
					} else {
						for(var i=0; i<emp.length; i++) {
							$(".regi_table").append('<tr class="empListAdd" style="height:100px; width:150"><td width="150"><img src="${pageContext.request.contextPath}/resources/img/'+emp[i].PHOTO+'" style="width: 120px; height: 120px; object-fit: cover;"></td>'
									+'<td width="150">'+emp[i].EMP_NUM+'</td>'
									+'<td width="120">'+emp[i].EMP_NAME+'</td>'
									+'<td width="150">'+emp[i].DEPT_NAME+'</td>'
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
				$("#pageListSection").append('<i class="fas fa-solid fa-angles-left"></i>');
			}
			for(var i=startPage; i <= endPage; i++) {
				$("#pageListSection").append('<span class="here">'+ i + '</span>');
			}
			if(pageNum > pageListLimit && endPage < pageListLimit) {
				$("#pageListSection").append('<i class="fas fa-solid fa-angles-right"></i>');
			}
		}
		
		
		////페이징처리
		function getPageList(pageNum, searchType, keyword) {
			$.ajax({
				url: 'HrListPage',
				type: 'post',
				data: {
					pageNum : pageNum,
					searchType : searchType,
					keyword : keyword
				},
				dataType : 'json',
				success : function(response) {
					pageList = JSON.parse(response.jsonPage);
					pageListChange(pageNum);
				}
			});
			getEmpList(pageNum, searchType, keyword);
			pageListChange(pageNum);
		}
		
		
		////재직 or 휴,퇴직 선택시 목록 변경
		$(".choice").on("click", function() {
			workTypeSel = $(this).attr("id");
			///pageNum은 강제로 1페이지
			getEmpList(1, 'WORK_CD', workTypeSel);
		});
		
		$(".here").on("click", function() {
			getPageList(pageNum, searchType, keyword);
		});
		
		getEmpList(1,'WORK_CD','1');
		getPageList(1,'WORK_CD','1');
	});

</script>
<style>
option {
	font-weight: bold;
}

</style>
</head>
<body>
	<jsp:include page="../inc/top.jsp"></jsp:include>
	<div style="display: flex; min-height: 1300px;"  align="center">
		<div style="width: 300px; margin-top: 0px; margin-right: 0px;">
			<jsp:include page="../inc/hr_left.jsp"></jsp:include>
		</div>
		<!-- 여기서부터 본문-->
		<div style="width: 1500px;">
		<h1 align="left" style="width: 1500px; text-align: left; margin-left: 100px;">| 사원 조회</h1>
		<div style="display: flex; width: 1300px; text-align: right" align="right">
			<div class="choice" id="1">재직</div><div class="choice" id="2">휴직</div><div class="choice" id="3">퇴직</div>
			<form action="HrList" method="post" style="width: 1400px; margin-bottom:10px; text-align: right;">
				<select name="searchType" style="text-align: center; font-weight: bold; width: 100px;">
					<option value="">검색 유형</option>
					<option value="EMP_NUM">사번</option>
					<option value="EMP_NAME">사원명</option>
					<option value="DEPT_NAME">부서명</option>
				</select>
				<input type="text" name="keyword">
				<button  class="hrFormBtn"  style="width: 100px; height:30px; font-size:18px;" >
					<i class="fa-solid fa-magnifying-glass" style="color: #fff; margin: 0;"></i>&nbsp;검색
				</button>
			</form>
		</div>
		<table border="1" class="regi_table" style="text-align: center; width: 1300px; font-size: 20px;">
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
			<tbody>
			
			</tbody>
		</table>
		<div align="right" style="width: 1300px;">
		<button type="button" class="hrFormBtn" style="width: 150px; height:30px;  font-size:18px; margin-top: 10px;" onclick="location.href='HrRegist'">신규 등록</button>
		</div>
		
		<!-- 페이지 목록 부분 -->
      <section id="pageListSection" style="font-weight: bold; font-size: 20px;">
			
		</section> 
        <!-- 페이지 목록 끝-->
		</div>
		<!-- 여기까지 본문-->
	</div>
</body>
</html>
