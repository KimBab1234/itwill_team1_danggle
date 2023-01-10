<%@page import="java_mail.GoogleMailAuthenticator"%>
<%@page import="javax.mail.Transport"%>
<%@page import="java.util.Date"%>
<%@page import="javax.mail.Message.RecipientType"%>
<%@page import="javax.mail.internet.InternetAddress"%>
<%@page import="javax.mail.Address"%>
<%@page import="javax.mail.Message"%>
<%@page import="javax.mail.internet.MimeMessage"%>
<%@page import="javax.mail.Session"%>
<%@page import="java.util.Properties"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    request.setCharacterEncoding("UTF-8");
    String sender = request.getParameter("sender");
    String title = request.getParameter("title");
    String content = request.getParameter("content");
 	
    try{
    Properties properties = System.getProperties();
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.port", "587");
  
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
    
    GoogleMailAuthenticator authenticator = new GoogleMailAuthenticator();
    Session mailSession = Session.getDefaultInstance(properties, authenticator);
    Message mailMessage = new MimeMessage(mailSession); 
    Address senderAddress = new InternetAddress(sender,sender);
    Address receiverAddress = new InternetAddress("ssh268@naver.com");
    mailMessage.setHeader("content-type", "text/html; charset=UTF-8");
    mailMessage.setFrom(senderAddress);
    mailMessage.addRecipient(RecipientType.TO, receiverAddress);
    mailMessage.setSubject(title);
    mailMessage.setContent(content, "text/html; charset=UTF-8");
    mailMessage.setSentDate(new Date());
    Transport.send(mailMessage);
   
    } catch(Exception e){
    	e.printStackTrace();
    	out.println("<h3>SMTP 서버 설정 또는 서비스 문제 문제 발생!</h3>");
    }
    %>
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/default.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">

<style>
* {
   font-family: 'Gowun Dodum', sans-serif;
   url: @import url('https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap');
   }
h2 {
		text-align: center;
		
	}
</style>

</head>
<body>

	<header>
		<!-- Login, Join 링크 표시 영역 -->
		<jsp:include page="/inc/top.jsp"></jsp:include>
		<jsp:include page="../inc/main.jsp"></jsp:include>
	</header>
	<hr>
	<div style="width: 1920px; display: flex; margin-left: 10px; min-height: 500px;">
		<div style="width: 500px;">
			<jsp:include page="../inc/customer_left.jsp"></jsp:include>
        </div>
	<!-- 왼쪽 메뉴바 세트 끝 -->
	
	<!-- 상단 이미지, 큰 정보 감싸는 곳 -->
		<div style="width: 1000px; margin-left: 20px; ">
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<h3><img src ="img/re.gif">&nbsp;&nbsp;메일이 정상적으로 발송되었습니다!&nbsp;&nbsp;<img src ="img/re.gif"></h3>

	</div>
	</div>
	<footer>
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
	</footer>
</body>
</html>