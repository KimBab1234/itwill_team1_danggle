<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script>
if('${closePop}'!='') {
	alert('${msg}');
	opener.location.reload();
	this.close();
} else {
	alert('${msg}');
	history.back();
}
</script>