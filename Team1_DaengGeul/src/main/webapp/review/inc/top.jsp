<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	function logout() {
		let isLogout = confirm("로그아웃 하시겠습니까?");
		
		if(isLogout) {
			location.href = "MemberLogout.me";
		}
	}
</script>
<div id="member_area">
	
	<a href="./">Home</a> 
	<c:choose>
		<%-- 로그인 상태가 아닐 경우 Login, Join 링크 표시 --%>
		<%-- EL 을 사용하여 세션 객체 접근 시 sessionScope.속성명 으로 접근 --%>
		<c:when test="${empty sessionScope.sId }">
			| <a href="MemberLoginForm.me">Login</a> 
			| <a href="MemberJoinForm.me">Join</a>
		</c:when>
		<%-- 로그인 상태일 경우 아이디 표시, Logout 링크 표시 --%>
		<c:otherwise>
			| <a href="MemberInfo.me">${sessionScope.sId }</a>님 
			| <a href="javascript:logout()">Logout</a> 
			
			<%-- <a href="MemberLogout.me">Logout</a> 도 가능 내가 컨트롤러에 해둠--%>
			
			<%-- 만약, 로그인 된 세션 아이디가 "admin" 일 경우 관리자페이지 링크(MemberList.me) 추가 --%>
			<c:if test='${sessionScope.sId eq "admin" }'>
				| <a href="MemberList.me">관리자 페이지</a> 
			</c:if>			
		</c:otherwise>
	</c:choose>
	
	
<%-- 	<c:choose> --%>
		
<%-- 		<c:when test="${sId != null && sId eq 'admin'}"> --%>
<%-- 			<a href="./">Home</a> | <a>${sId }님</a> | <a href="MemberLogout.me">Logout</a> | <a href="MemberList.me">관리자 페이지</a> --%>
<%-- 		</c:when> --%>
		
<%-- 		<c:when test="${sId != null }"> --%>
<%-- 			<a href="./">Home</a> | <a>${sId }님</a> | <a href="MemberLogout.me">Logout</a> --%>
<%-- 		</c:when> --%>
		
<%-- 		<c:otherwise> --%>
<!-- 			<a href="./">Home</a> | <a href="MemberLoginForm.me">Login</a> | <a href="MemberJoinForm.me">Join</a> -->
<%-- 		</c:otherwise> --%>
		
<%-- 	</c:choose> --%>
	
	
</div>
    
    
    
    
    