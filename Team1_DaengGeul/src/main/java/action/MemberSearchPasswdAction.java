package action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import encrypt.MyMessageDigest;
import java_mail.GoogleMailAuthenticator;
import svc.MemberSearchPasswdService;
import svc.SearchNEService;
import vo.ActionForward;

public class MemberSearchPasswdAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		String id = request.getParameter("id");
		
		// ------------------------ 임시 비밀번호 생성 ------------------------
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
				'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		String tempPasswd = "";
		
		int idx = 0;
		for (int i = 0; i < 10; i++) {
			idx = (int) (charSet.length * Math.random());
			tempPasswd += charSet[idx];
		}
		// --------------------------------------------------------------------
		
		
		// ---------------------- 임시비밀번호 메일전송 -----------------------
		String name = null;
		String email = null;
		
		SearchNEService service = new SearchNEService();
		String[] findInfo = service.SearchNE(id);
		
		name = findInfo[0];
		email = findInfo[1];
		
		try {
			request.setCharacterEncoding("UTF-8");
			
			String sender = "ths8190@gmail.com";
			String receiver = email;
			String title = "댕글댕글 임시 비밀번호입니다.";
			String content = name + "님, 임시 비밀번호를 발급하였습니다.<br>"
					+ "임시 비밀번호는 <b>"+ tempPasswd + "</b> 입니다.<br>"
					+ "로그인 후, 반드시 비밀번호를 변경하여 사용하세요.";
			
			Properties properties = System.getProperties();
			
			properties.put("mail.smtp.host", "smtp.gmail.com"); // 구글(Gmail) SMTP 서버 주소
			properties.put("mail.smtp.auth", "true"); // SMTP 서버에 대한 인증 여부 설정
			properties.put("mail.smtp.port", "587"); // SMTP 서비스 포트 설정
			
			properties.put("mail.smtp.starttls.enable", "true"); // TLS 인증 프로토콜 사용 여부 설정
			properties.put("mail.smtp.ssl.protocols", "TLSv1.2"); // 인증 프로토콜 버전 설정
			
			GoogleMailAuthenticator authenticator = new GoogleMailAuthenticator();
			javax.mail.Session mailSession = javax.mail.Session.getDefaultInstance(properties, authenticator);
			Message mailMessage = new MimeMessage(mailSession);
			
			Address senderAddress = new InternetAddress(sender, "DaengGeul.com");
			Address receiverAddress = new InternetAddress(receiver);
			mailMessage.setHeader("content-type", "text/html; charset=UTF-8");
			mailMessage.setFrom(senderAddress);
			mailMessage.addRecipient(RecipientType.TO , receiverAddress);
			mailMessage.setSubject(title);
			mailMessage.setContent(content, "text/html; charset=UTF-8");
			mailMessage.setSentDate(new Date());
			
			Transport.send(mailMessage);
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
		}
		// --------------------------------------------------------------------
		
		
		// ------------------------ 임시비밀번호 저장 -------------------------
		MyMessageDigest md = new MyMessageDigest("SHA-256");
		tempPasswd = md.hashing(tempPasswd);
		MemberSearchPasswdService service2 = new MemberSearchPasswdService();
		service2.searchPasswd(id, tempPasswd);
		// --------------------------------------------------------------------
		
		forward = new ActionForward();
		forward.setPath("member/member_search_passwd_result.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
