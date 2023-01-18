package com.itwillbs.team1.java_mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class GoogleMailAuthenticator extends Authenticator{
									// Authenticator클래스를 상속받아 해당 클래스 메서드 활용
	// 인증정보 관리할 객체 선언
	private PasswordAuthentication passwordAuthentication;

	// 생성자에 인증정보를 입력받은 인스턴트 객체 선언
	public GoogleMailAuthenticator() {
		passwordAuthentication = new PasswordAuthentication("ths8190", "infkqsfpkxsxosml");
	}
	
	// 인증정보를 외부로 리턴하기 위한 오버라이딩
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return passwordAuthentication;
	}
	
}
