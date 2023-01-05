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
    out.println("<h3>메일이 정상적으로 발송되었습니다</h3>");
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
</head>
<body>

</body>
</html>