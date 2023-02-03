package com.itwillbs.team1_final.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.itwillbs.team1_final.mapper.HrMapper;

@Service
public class EmailService{

	@Autowired
	private MailSender mailSender;

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
		
		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setFrom("ljs930102@naver.com");
		smm.setTo(email);
		smm.setSubject("댕글댕글 신규 사원 등록 완료. 임시 비밀번호가 발급되었습니다.");
		smm.setText("댕글댕글 신규 사원 등록 완료! \\n 임시 비밀번호가 발급되었습니다. \\n 로그인 후 비밀번호 변경을 해주세요. \\n 임시 비밀번호 :" + tempPasswd);
		
		
//		mapper.insertTempPass(email,tempEncodedPass);
		
		/////프로젝트 하는동안은 1234로 고정
		mapper.insertTempPass(email,"$2a$10$YYOpNkIbQ5FNhuyUIiUtv.4yXc9bBv7IDPMmcEQZJYACYOCqEE4IK");
		
		mailSender.send(smm);
		
	}

}
