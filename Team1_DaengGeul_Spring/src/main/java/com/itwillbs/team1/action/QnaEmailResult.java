package com.itwillbs.team1.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.team1.java_mail.GoogleMailAuthenticator;
import com.itwillbs.team1.svc.QnaDetailService;
import com.itwillbs.team1.vo.ActionForward;
import com.itwillbs.team1.vo.QnaBean;

public class QnaEmailResult implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = null;
	
		try {
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
				Address receiverAddress = new InternetAddress("ths8190@gmail.com");
				mailMessage.setHeader("content-type", "text/html; charset=UTF-8");
				mailMessage.setFrom(senderAddress);
				mailMessage.addRecipient(RecipientType.TO, receiverAddress);
				mailMessage.setSubject(title);
				mailMessage.setContent(content, "text/html; charset=UTF-8");
				System.out.println(title);
				System.out.println(content);
				mailMessage.setSentDate(new Date());
				Transport.send(mailMessage);
				
			} catch(Exception e){
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		
		return forward;
	}

}
