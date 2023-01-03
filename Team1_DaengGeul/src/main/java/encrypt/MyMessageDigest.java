package encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MyMessageDigest {
	private String hashAlgorithm = ""; // 해싱 알고리즘명 저장할 변수

	// 알고리즘명을 전달받아 초기화할 생성자
	public MyMessageDigest(String hashAlgorithm) {
		this.hashAlgorithm = hashAlgorithm;
	}

	// 해싱(=단방향 암호화) 작업을 수행할 hashing() 메서드 정의
	// => 파라미터: 평문 문자열		리턴타입: 암호문
	public String hashing(String strPlainText) {
		String strCiperText = ""; // 암호화 결과 문자열(= 암호문)을 저장할 변수
		
		try {
			MessageDigest md = MessageDigest.getInstance(hashAlgorithm);
			
			byte[] byteData = strPlainText.getBytes();
//			System.out.println(Arrays.toString(byteData));
			
			md.update(byteData);
			
			byte[] digestResult = md.digest();
//			System.out.println(Arrays.toString(digestResult));
			
			// for문을 사용하여 암호문 배열 크기만큼 반복
			for(int i = 0; i < digestResult.length; i++) {
				if(digestResult[i] < 0) {
					strCiperText += Integer.toHexString(digestResult[i] & 0xFF).toUpperCase();
				}
			}
			
//			System.out.println(strCiperText);
		} catch (NoSuchAlgorithmException e) {
			System.out.println(hashAlgorithm + " 알고리즘이 존재하지 않습니다");
			e.printStackTrace();
		}
		
		return strCiperText;
	}

}