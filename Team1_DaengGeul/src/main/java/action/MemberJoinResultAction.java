package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import encrypt.MyMessageDigest;
import java_mail.GoogleMailAuthenticator;
import svc.MemberJoinProService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberJoinResultAction implements Action {

	// ---------------------------------- 회원가입 완료 ---------------------------------------
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;

		try {
		//  ----------------- 회원가입 축하메일 & 쿠폰 지급 --------------------
		request.setCharacterEncoding("UTF-8");

		String sender = "ths8190@gmail.com";
//		String receiver = request.getParameter("email");
		String receiver = "iun05@naver.com";
		String title = "댕글댕글에 가입해주셔서 감사합니다!";
		String content = request.getParameter("name") + "님 댕글댕글의 회원이 되어주셔서 감사합니다!<br>"
				+ "감사의 선물로 웰컴쿠폰과 적립금 1,500원이 지급되었습니다.<br>"
				+ "첫 구매 시, 필기구/책갈피 중 선택하여 받으실 수 있습니다.<br>"
				+ "적립금은 구매금액의 5%가 적립되며 현금과 동일하게 사용 가능합니다."
				+ "쿠폰은 마이페이지 쿠폰함에서 확인 가능합니다.";
		//					System.out.println(sender + ", " + receiver + ", " + title + ", " + content);

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

		//  ----------------- 회원가입 축하메일 & 쿠폰 지급 끝 --------------------


	} catch (IOException e) {
		e.printStackTrace();
	} catch (AddressException e) {
		e.printStackTrace();
	} catch (MessagingException e) {
		e.printStackTrace();
	}

	return forward;
}
// -----------------------------------------------------------------------------------

}