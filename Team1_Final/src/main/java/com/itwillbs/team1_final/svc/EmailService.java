package com.itwillbs.team1_final.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.itwillbs.team1_final.mapper.HrMapper;

@Service
public class EmailService{

	@Autowired
	private MailSender mailSender;

	@Autowired
	private HrMapper mapper;
	
	public void sendEmail(String email) {

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
		// --------------------------------------------------------------------

		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setFrom("ljs930102@naver.com");
		smm.setTo(email);
		smm.setSubject("신규 사원 등록 완료. 임시 비밀번호가 발급되었습니다.");
		smm.setText("임시 비밀번호가 발급되었습니다. 로그인 후 비밀번호 변경을 해주세요. 임시 비밀번호 :" + tempPasswd);
		
		mapper.insertTempPass(email,tempPasswd);
		
		mailSender.send(smm);

	}

}
