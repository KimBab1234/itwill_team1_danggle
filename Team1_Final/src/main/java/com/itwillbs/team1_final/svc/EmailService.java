package com.itwillbs.team1_final.svc;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.itwillbs.team1_final.mapper.HrMapper;

@Service
public class EmailService{

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private HrMapper mapper;

	public void sendEmail(String empNo) {

		///임시비밀번호 6자리 생성
		// ------------------------ 임시 비밀번호 생성 ------------------------
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
				'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		String tempPasswd = "";

		int idx = 0;
		for (int i = 0; i < 6; i++) {
			idx = (int) (charSet.length * Math.random());
			tempPasswd += charSet[idx];
		}

		//		////임시비밀번호 암호화
		//		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String tempEncodedPass = passwordEncoder.encode(tempPasswd);

		// --------------------------------------------------------------------

		String email = mapper.selectEmpEmail(empNo);

		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setFrom("ljs930102@naver.com");
			helper.setTo(email);
			helper.setSubject("댕글댕글 신규 사원 등록 완료. 임시 비밀번호가 발급되었습니다.");

			StringBuffer sb = new StringBuffer();
			sb.append("<html><head>");
			sb.append("<meta http-equiv='Content-Type' content='text/html; charset=euc-kr'>");
			sb.append("<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\"><link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin><link href=\"https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap\" rel=\"stylesheet\">\r\n"
					+ "<style> *{ font-family: 'Gowun Dodum', sans-serif; }</style></head>\r\n");
			sb.append("<body><img src=\"http://itwillbs3.cdn1.cafe24.com/img/logo2.png\" style=\"width:250px; vertical-align: top;\"><br>");
			sb.append("<h3>댕글댕글 신규 사원 등록 완료!</h3>");
			sb.append("<h3>임시 비밀번호가 발급되었습니다.</h3>");
			sb.append("<h3>로그인 후 비밀번호 변경을 해주세요.</h3>");
			sb.append("<h2>임시 비밀번호 : " + tempPasswd + "</h2>");
			sb.append("</body></html>");
			helper.setText(sb.toString(), true);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		mapper.insertTempPass(email,tempEncodedPass);

		mailSender.send(message);

	}

}
