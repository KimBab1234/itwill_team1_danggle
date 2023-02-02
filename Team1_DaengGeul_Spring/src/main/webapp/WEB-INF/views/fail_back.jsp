<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<script>
	
	let result = "${result}";
	
	if(result=="true"){
		
		let ment = "추천 도서에 등록되었습니다. \n추천 도서 목록으로 이동하시겠습니까?";
		
		if(confirm(ment)){
			location.href="RecommendBookList.ad";
		}else{
			history.back();
		}
	}
	
	alert('${msg}');
	history.back();
	
	
</script>