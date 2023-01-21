package action;

import java.io.IOException;
import java.io.PrintWriter;
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

import java_mail.GoogleMailAuthenticator;
import svc.SearchNEService;
import svc.registAuthService;
import vo.ActionForward;
import vo.AuthBean;

public class MemberSendCertProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		// 회원가입/비밀번호 찾기를 나누기 위한 파라미터
		String type = request.getParameter("type");
		String id = request.getParameter("id");
//		System.out.println(type); // 파라미터 확인용
		
		// 회원가입 이메일 인증코드 발급
		if(type.equals("join")) {
			// ------------------- 인증번호를 전송할 회원 이메일 -----------------------
			String email = request.getParameter("email1")+ "@" + request.getParameter("email2");
			// -------------------------------------------------------------------------
			
			
			// ----------------------- 인증코드 생성 및 저장 ---------------------------
			char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
					'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
			String certNum = "";
			
			// 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 구문을 작성함
			int idx = 0;
			for (int i = 0; i < 10; i++) {
				idx = (int) (charSet.length * Math.random());
				certNum += charSet[idx];
			}
//			System.out.println("회원가입 id : " +  id); // 파라미터 확인용
//			System.out.println("회원가입 인증번호 : " + certNum); // 파라미터 확인용
			
			// 인증코드 확인을 위해 auth 테이블에 저장
			AuthBean auth = new AuthBean();
			auth.setAuth_id(id);
			auth.setAuthCode(certNum);
			
			registAuthService service = new registAuthService();
			service.registAuth(auth);
			// -------------------------------------------------------------------------
			
			
			// ------------------ 회원가입 이메일인증 인증코드 전송 --------------------
			try {
				request.setCharacterEncoding("UTF-8");
				
				String sender = "ths8190@gmail.com";
				String receiver = email;
				String title = "댕글댕글 회원가입 인증번호입니다.";
				String content = "인증번호는 <b>"+ certNum + "</b/> 입니다.<br>"
						+ "회원가입 창 인증번호란에 입력 후, Email 인증하기 버튼을 눌러주세요.";
				
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
			// -------------------------------------------------------------------------	
		// 회원가입 회원가입 이메일 인증 끝
			
			
		// =============================================================================
			
			
		// 비밀번호 찾기 이메일 인증코드 발급
		} else if(type.equals("cert")) {
			// ---------------------- 사용자 이름, Email 찾기 --------------------------
			String name = null;
			String email = null;
			
			SearchNEService service = new SearchNEService();
			String[] findInfo = service.SearchNE(id);
			
			name = findInfo[0];
			email = findInfo[1];
			// -------------------------------------------------------------------------
			
			
			// ----------------------- 인증코드 생성 및 저장 ---------------------------
			char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
					'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
			String certNum = "";
			
			// 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 구문을 작성함
			int idx = 0;
			for (int i = 0; i < 10; i++) {
				idx = (int) (charSet.length * Math.random());
				certNum += charSet[idx];
			}
//				System.out.println("비밀번호 찾기 id : " +  id); // 파라미터 확인용
//				System.out.println("비빌번호 찾기 인증번호 : " + certNum); // 파라미터 확인용
			
			// 인증코드 확인을 위해 auth 테이블에 저장
			AuthBean auth = new AuthBean();
			auth.setAuth_id(id);
			auth.setAuthCode(certNum);
			
			registAuthService service2 = new registAuthService();
			service2.registAuth(auth);
			// -------------------------------------------------------------------------
			
			
			// ----------------- 비밀번호 찾기 인증코드 이메일 전송 --------------------
			try {
				request.setCharacterEncoding("UTF-8");
				
				String sender = "ths8190@gmail.com";
				String receiver = email;
				String title = "댕글댕글 비밀번호 찾기 인증번호입니다.";
				String content = name + "님, 인증번호를 발급하였습니다.<br>"
						+ "인증번호는 <b>"+ certNum + "</b> 입니다.<br>"
						+ "만약, 본인의 활동이 아니시라면 비밀번호를 변경해주세요.";
				
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
			// -------------------------------------------------------------------------
			
			
		} // 비밀번호 찾기 이메일 인증코드 발급 끝
		
		return forward;
	}

}
