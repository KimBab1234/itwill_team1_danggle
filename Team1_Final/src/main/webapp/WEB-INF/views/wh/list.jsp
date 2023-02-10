<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>창고 목록</title>
<meta name='viewport' content='width=device-width, initial-scale=1'>
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
<script src="https://kit.fontawesome.com/5fad4a5f29.js" crossorigin="anonymous"></script>
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
	#listForm {
		width: 1024px;
		max-height: 610px;
	}
	
	h2 {
		text-align: center;
	}
	
	table {
		width: 1024px;
	}
	
	#tr_top {
		background: #b09f76;
		text-align: center;
	}
	
	.wh_td {
		text-align: center;
	
	}
	
	#subject {
		text-align: left;
		padding-left: 20px;
	}
	
	
	
	#emptyArea {
		width: 1024px;
		text-align: center;
	}
	
	#buttonArea {
		width: 1024px;
		text-align: right;
		margin-top: 10px;
	}
	
	a {
		text-decoration: none;
	}
	
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
	.FormBtn {
	background-color: #736643;
	border: none;
	cursor: pointer;
	color: #fff;
	height: 27px;
	width: 80px;
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
	<div style="display: flex; width: 1800px;">
		<div style="width: 300px; margin-top: 0px; margin-right: 0px; border-right:solid 1px; border-color: #BDBDBD;">
				<jsp:include page="../inc/wh_left.jsp"></jsp:include>
		</div>

		
	<!-- 게시판 리스트 -->
	<div align="center" style="width: 1300px;">
	<h2>창고 목록</h2>
	<section id="buttonArea">
		<form action="WhList" style="width: 700px;">      
			<select name="searchType">
				<option value="code" <c:if test="${searchType eq 'code' }"></c:if>>창고 코드</option>
				<option value="name" <c:if test="${searchType eq 'name' }"></c:if>>창고명</option>
				<option value="code_name" <c:if test="${searchType eq 'code_name' }"></c:if>>창고 코드&창고명</option>
			</select>
		<input type="text" name="keyword" value="${param.keyword }">
		<input type="submit" class= "hrFormBtn"  value="검색">
		&nbsp;&nbsp;
		<input type="button" class ="FormBtn" value="신규 등록" onclick="location.href='WhRegistForm'" />
		</form>
	</section>
	<div align="center" style="width: 1300px;">
	<table style="width: 1300px;">
		<tr id="tr_top">
			<td>창고 코드</td>
			<td width="150px">창고명</td>
			<td>구분</td>
			<td>관리자명</td>
			<td>사용여부</td>
			<td>창고 내부구역 추가</td>
			<td>창고 구역 내 위치 추가</td>
			<td>구역 및 위치 삭제</td>
			
			
		</tr>
		<c:if test="${whList.size() > 0}" >
			<c:forEach begin="1" end="${whList.size()-1}" var="i" >
				<c:if test="${whList.get(i-1).WH_CD != whList.get(i).WH_CD}">
					<tr class="wh_td">
						<td>
						<a href="WhDetail?WH_CD=${whList.get(i).WH_CD}&pageNum=${param.pageNum}">
						${whList.get(i).WH_CD}
						</a>
						</td>
						<td id="name">
						
						<a href="WhDetail?WH_CD=${whList.get(i).WH_CD}&pageNum=${param.pageNum}">
						${whList.get(i).WH_NAME}
						</a>
						</td>
						<td>
							<c:choose>
								<c:when test="${whList.get(i).WH_GUBUN eq 1 }">내부</c:when>
								<c:when test="${whList.get(i).WH_GUBUN eq 2 }">외부</c:when>
							</c:choose>
						</td>
						<td>${whList.get(i).WH_MAN_NAME}</td>
						<td>
							<c:choose>
								<c:when test="${whList.get(i).WH_GUBUN eq 1 }">사용</c:when>
								<c:when test="${whList.get(i).WH_GUBUN eq 2 }">미사용</c:when>
							</c:choose>
						</td>
					<td>
						<a href="WhAreaRegist?WH_CD=${whList.get(i).WH_CD}&pageNum=${param.pageNum}">
						<i style='font-size:13px; color: #000080;' class='fas'>&#xf067;</i>
						</a>
					</td>
<!-- 					<td>창고 구역 내 위치 추가 -->
<%-- 						<a href="WhLocationRegist?WH_AREA_CD=${wh_area.WH_AREA_CD}&pageNum=${pageNum}"> --%>
<!-- 						<i style='font-size:13px; color: #000080;' class='fas'>&#xf067;</i> -->
<!-- 						</a> -->
<!-- 					</td> -->
					</tr>
				</c:if>
				<c:if test="${whList.get(i).WH_AREA_CD != 0 && whList.get(i-1).WH_AREA_CD != whList.get(i).WH_AREA_CD}">
					<tr style="text-align: left">
						<td></td>
						<td colspan="4"><i class="fa-solid fa-right-long"></i>
						<a href="StockViewList?WH_CD=${whList.get(i).WH_CD}&WH_AREA_CD=${whList.get(i).WH_AREA_CD}&pageNum=${pageNum}">${whList.get(i).WH_AREA}</a>
						<input type="button" class="Btn" value="구역 이름 수정" onclick="location.href='WhAreaModifyForm?WH_AREA_CD=${whList.get(i).WH_AREA_CD}&pageNum=${param.pageNum }'"></td>
						<td></td>
						<td class="wh_td"><!-- 위치추가 버튼 -->
						<a href="WhLocationRegist?WH_AREA_CD=${whList.get(i).WH_AREA_CD}&pageNum=${param.pageNum}">
						<i style='font-size:13px; color: #000080;' class='fas'>&#xf067;</i></a>
						</td>
						<td class="wh_td"><!-- 구역 삭제 버튼 -->
						<a href="WhAreaDeleteForm?WH_AREA_CD=${whList.get(i).WH_AREA_CD}&pageNum=${param.pageNum}">
						<i style='font-size:13px; color: #000080;' class='fas'>&#xf068;</i>
						</a>
						</td>
					</tr>
				</c:if>
				<c:if test="${whList.get(i).WH_LOC_IN_AREA_CD != 0 && whList.get(i-1).WH_LOC_IN_AREA_CD != whList.get(i).WH_LOC_IN_AREA_CD}">
					<tr align="left">
						<td></td>
						<td colspan="5"><i class="fa-solid fa-right-long"></i><i class="fa-solid fa-right-long"></i> 
						<a href="StockViewList?WH_LOC_IN_AREA_CD=${whList.get(i).WH_LOC_IN_AREA_CD}&pageNum=${param.pageNum}">${whList.get(i).WH_LOC_IN_AREA}</a>
						<input type="button" class="Btn" value="위치 이름 수정" onclick="location.href='WhLocationModifyForm?WH_LOC_IN_AREA_CD=${whList.get(i).WH_LOC_IN_AREA_CD}&pageNum=${param.pageNum }'"></td>
						<td></td>
						<td class="wh_td"><!--  위치 삭제 버튼 -->
						<a href="WhLocationDelete?WH_AREA_CD=${whList.get(i).WH_AREA_CD}&WH_LOC_IN_AREA_CD=${whList.get(i).WH_LOC_IN_AREA_CD }&pageNum=${param.pageNum}">
						<i style='font-size:13px; color: #000080;' class='fas'>&#xf068;</i>
						</a>
						</td>
						
					</tr>
				</c:if>
			</c:forEach>
		</c:if>
	</table>
	
	</div>
	</div>
	</div>
	<div  id="pageList" align="center" style="width: 2100px;">
	
	<c:choose>
					<c:when test="${empty param.pageNum }">
						<c:set var="pageNum" value="1"/>
					</c:when>
					<c:otherwise>
						<c:set var="pageNum" value="${param.pageNum}"/>
					</c:otherwise>
				</c:choose>
		<!-- 
		현재 페이지 번호(pageNum)가 1보다 클 경우에만 [이전] 링크 동작
		=> 클릭 시 BoardList.bo 서블릿 주소 요청하면서 
		   현재 페이지 번호(pageNum) - 1 값을 page 파라미터로 전달
		-->
		
		<c:choose>
			<c:when test="${pageNum > 1}">
				<input type="button" class="hrFormBtn" value="이전" onclick="location.href='WhList?pageNum=${pageNum - 1}'">
			</c:when>
			<c:otherwise>
				<input type="button" class="hrFormBtn" value="이전">
			</c:otherwise>
		</c:choose>
		&nbsp;&nbsp;&nbsp;
			
		<!-- 페이지 번호 목록은 시작 페이지(startPage)부터 끝 페이지(endPage) 까지 표시 -->
		<c:forEach var="i" begin="${pageInfo.startPage }" end="${pageInfo.endPage }">
			<!-- 단, 현재 페이지 번호는 링크 없이 표시 -->
			<c:choose>
				<c:when test="${pageNum eq i}">
					${i }
				</c:when>
				<c:otherwise>
					<a href="WhList?pageNum=${i }">${i }</a>
				</c:otherwise>
			</c:choose>
			&nbsp;
		</c:forEach>
		&nbsp;
		<!-- 현재 페이지 번호(pageNum)가 총 페이지 수보다 작을 때만 [다음] 링크 동작 -->
		<c:choose>
			<c:when test="${pageNum <pageInfo.maxPage}">
				<input type="button" class="hrFormBtn" value="다음" onclick="location.href='WhList?pageNum=${pageNum + 1}'">
			</c:when>
			<c:otherwise>
				<input type="button" class="hrFormBtn" value="다음">
			</c:otherwise>
		</c:choose>
	</div>
	
</body>
</html>