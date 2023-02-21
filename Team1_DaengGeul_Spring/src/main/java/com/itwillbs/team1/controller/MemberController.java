package com.itwillbs.team1.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwillbs.team1.java_mail.GoogleMailAuthenticator;
import com.itwillbs.team1.svc.MemberService;
import com.itwillbs.team1.svc.WishService;
import com.itwillbs.team1.vo.AuthVO;
import com.itwillbs.team1.vo.MemberPageInfo;
import com.itwillbs.team1.vo.MemberVO;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService service;
	@Autowired
	private WishService service2;	

	// ======================== 회원가입 Controller ==========================
	// 회원가입 창
	@GetMapping(value = "/MemberJoinForm")
	public String join() {
		return "member/member_join_form";
	}
	
	
	// 회원등록
	@PostMapping(value = "/MemberJoinPro")
	public String joinPro(@ModelAttribute MemberVO member, Model model,
						RedirectAttributes redirect) {
		// 비밀번호 암호화
		BCryptPasswordEncoder passwdEncoder = new BCryptPasswordEncoder();
		String securePasswd = passwdEncoder.encode(member.getMember_passwd());
		member.setMember_passwd(securePasswd);
		
		// 적립금, 쿠폰 임의입력
		member.setMember_point(1500);
		member.setMember_coupon("WelcomeCoupon");
		
		int joinCount = service.joinMember(member);
		
		if(joinCount > 0) {
			redirect.addFlashAttribute("name", member.getMember_name());
			redirect.addFlashAttribute("email1", member.getMember_email1());
			redirect.addFlashAttribute("email2", member.getMember_email2());
			return "redirect:/Welcome";
		} else {
			model.addAttribute("msg", "회원가입 실패!");
			return "fail_back";
		}
		
	}
	
	
	// 아이디 중복확인
	@GetMapping(value = "/MemberCheckId")
	@ResponseBody
	public String checkId(String id) {
		int existCount = service.checkId(id);
		
		if(existCount > 0) {
			return "true";
		} else {
			return "false";
		}
	}
	
	
	// 이메일 중복확인
	@GetMapping(value = "/MemberCheckEmail")
	@ResponseBody
	public String checkEmail(@ModelAttribute MemberVO member, String email1, String email2) {
		String email = email1 + "@" + email2;
		int existCount = service.checkEmail(email);
		
		if(existCount > 0) {
			return "true";
		} else {
			return "false";
		}
	}
	
	
	// 회원가입 성공 창
	@GetMapping(value = "/Welcome")
	public String welcome() {
		return "member/member_join_result";
	}
	
	
	// 회원가입 축하메일 전송
	@PostMapping(value = "/MemberJoinResult")
	public void joinSuccess(String name, String email1, String email2) {
		try {
			String sender = "ths8190@gmail.com";
			String receiver = email1 + "@" + email2;
			String title = "댕글댕글에 가입해주셔서 감사합니다!";
			String content = name + "님 댕글댕글의 회원이 되어주셔서 감사합니다!<br>"
					+ "감사의 선물로 웰컴쿠폰과 적립금 1,500원이 지급되었습니다.<br>"
					+ "첫 구매 시, 필기구/책갈피 중 선택하여 받으실 수 있습니다.<br>"
					+ "적립금은 구매금액의 5%가 적립되며 현금과 동일하게 사용 가능합니다."
					+ "쿠폰은 마이페이지 쿠폰함에서 확인 가능합니다.";
			
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
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	// =======================================================================
	
	
	// ======================= 이메일 인증 Controller ========================
	// Email 인증번호 보내기
	@PostMapping(value = "/MemberSendCertPro")
	@ResponseBody
	public String sendCert(String id, String type, String email1, String email2,
							@ModelAttribute AuthVO auth) {
		if(type.equals("join")) {
			// ------------------- 인증번호를 전송할 회원 이메일 -----------------------
			String email = email1 + "@" + email2;
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
			
			// 인증코드 작업을 위해 auth 테이블에 저장
			auth.setAuth_id(id);
			auth.setAuthCode(certNum);
			
			int checkCount = service.checkAuth(auth);
			if(checkCount > 0) {
				service.replaceCert(auth);
			} else {
				service.createCert(auth);
			}
			// -------------------------------------------------------------------------
			
			// ------------------ 회원가입 이메일인증 인증코드 전송 --------------------
			try {
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
			MemberVO member = service.SearchNE(id);
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
			
			// 인증코드 확인을 위해 auth 테이블에 저장
			auth.setAuth_id(id);
			auth.setAuthCode(certNum);
			if(member.getMember_id() != "") {
				int checkCount = service.checkAuth(auth);
				if(checkCount > 0) {
					service.replaceCert(auth);
				} else {
					service.createCert(auth);
				}				
			}
			// -------------------------------------------------------------------------
			
			
			// ----------------- 비밀번호 찾기 인증코드 이메일 전송 --------------------
			try {
				String sender = "ths8190@gmail.com";
				String receiver = member.getMember_email();
				String title = "댕글댕글 비밀번호 찾기 인증번호입니다.";
				String content = member.getMember_name() + "님, 인증번호를 발급하였습니다.<br>"
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
			
			
		}
		
		return "";
	}
	
	
	// Email 인증
	@GetMapping(value = "/MemberCheckCertPro")
	@ResponseBody
	public String checkSert(String id, String certNum) {
		String selectedCertNum = service.checkCertNum(id);
		
		if(selectedCertNum != null) {
			if(certNum.equals(selectedCertNum)) {
				service.deleteAuth(id);
				return "true";				
			} else {
				return "false";
			}
		} else {
			return "false";
		}
		
	}
	// =======================================================================
	
	
	// ===================== 로그인/로그아웃 Controller ======================
	// 로그인 창
	@GetMapping(value = "/MemberLoginForm")
	public String login() {
		System.out.println("로그인폼");
		return "member/member_login_form";
	}
	
	
	// 로그인
	@PostMapping(value = "/MemberLoginPro")
	public String loginPro(@ModelAttribute MemberVO member, Model model, HttpSession session){
		BCryptPasswordEncoder passwdEncoder = new BCryptPasswordEncoder();
		String passwd = service.getPasswd(member.getMember_id());
		
		if(passwd == null || !passwdEncoder.matches(member.getMember_passwd(), passwd)) {
			model.addAttribute("msg", "로그인 실패!");
			return "fail_back";
		} else {
			String keyword = "";
			String searchType = "";
			
			int wishlistCount = service2.getWishlistCount(member.getMember_id(), searchType, keyword);
			session.setAttribute("wishlistCount", wishlistCount);	
			session.setAttribute("sId", member.getMember_id());
			return "redirect:/";
		}
		
	}
	
	
	// 로그아웃
	@GetMapping(value = "/MemberLogoutPro")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	// =======================================================================
	
	
	// ========= 일반사용자(회원정보)/관리자(회원목록) Controller ============
	// 마이페이지
	@GetMapping(value = "/MemberInfo")
	public String memberInfo(String id, HttpSession session, Model model) {
		String sId = (String)session.getAttribute("sId");
		
		if(sId == null || sId.equals("")) {
			model.addAttribute("msg", "잘못된 접근입니다!");
			return "fail_back";
		} else {
			
			if(id != null && !id.equals("") && !id.equals(sId) && !sId.equals("admin")) {
				model.addAttribute("msg", "권한이 없습니다!");
				return "fail_back";
			} else if(id.equals("")) {
				model.addAttribute("msg", "잘못된 접근입니다!");
				return "fail_back";
			}
			
			MemberVO member = service.getMemberInfo(id);
			
			model.addAttribute("member", member);
			
			return "member/member_info";
		}
	}
	
	
	// 관리자 페이지
	@GetMapping(value = "/AdminMain")
	public String adminMain(HttpSession session, Model model,
				@RequestParam(defaultValue = "") String searchType,
				@RequestParam(defaultValue = "") String keyword,
				@RequestParam(defaultValue = "1") int pageNum) {
		
		String id = (String)session.getAttribute("sId");
		if(id == null || id.equals("") || !id.equals("admin")) {
			model.addAttribute("msg", "잘못된 접근입니다!");
			return "fail_back";
		} else {
			// ---------------------------------------------------------------------------
			// 페이징 처리를 위한 변수 선언
			int listLimit = 3;
			int startRow = (pageNum - 1) * listLimit; // 조회 시작 행번호 계산
			// ---------------------------------------------------------------------------
			List<MemberVO> memberList = service.getMemberList(searchType, keyword, startRow, listLimit);
			// ---------------------------------------------------------------------------
			// 1. 한 페이지에서 표시할 페이지 목록(번호) 개수 계산
			int listCount = service.getMemberListCount(searchType, keyword);
			
			// 2. 한 페이지에서 표시할 페이지 목록 개수 설정
			int pageListLimit = 5;
			
			// 3. 전체 페이지 목록 수 계산
			int maxPage = listCount / listLimit 
					+ (listCount % listLimit == 0 ? 0 : 1); 
			
			// 4. 시작 페이지 번호 계산
			int startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;
			
			// 5. 끝 페이지 번호 계산
			int endPage = startPage + pageListLimit - 1;
			
			// 6. 만약, 끝 페이지 번호(endPage)가 전체(최대) 페이지 번호(maxPage) 보다
			//    클 경우, 끝 페이지 번호를 최대 페이지 번호로 교체
			if(endPage > maxPage) {
				endPage = maxPage;
			}
			
			MemberPageInfo memberPageInfo = new MemberPageInfo(listCount, pageListLimit, maxPage, startPage, endPage);
			// ---------------------------------------------------------------------------
			
			model.addAttribute("pageNum", pageNum);
			model.addAttribute("searchType", searchType);
			model.addAttribute("keyword", keyword);
			model.addAttribute("memberList", memberList);
			model.addAttribute("memberPageInfo", memberPageInfo);
			
			return "member/member_list";
		}
		
	}
	// =======================================================================
	
	
	// ========================= 회원수정 Controller =========================
	@PostMapping(value = "/MemberUpdatePro")
	public String updateMember(@ModelAttribute MemberVO member,
							String newPasswd, String newPasswd2,
							Model model) {
		BCryptPasswordEncoder passwdEncoder = new BCryptPasswordEncoder();
		String passwd = service.getPasswd(member.getMember_id());
		
		// 패스워드 판별
		if(passwd == null || !passwdEncoder.matches(member.getMember_passwd(), passwd)) { // 실패
			model.addAttribute("msg", "비밀번호를 확인하세요!");
			return "fail_back";
		}
		
		if(!newPasswd.equals(newPasswd2)) {
			model.addAttribute("msg", "변경할 비밀번호를 일치시켜주세요!");
			return "fail_back";			
		}
		
		if(newPasswd != null && !newPasswd.equals("")) {
			newPasswd = passwdEncoder.encode(newPasswd);
		}
		
		int updateCount = service.modifyMemberInfo(member, newPasswd);
		
		if(updateCount > 0) {
			return "redirect:/MemberInfo?id=" + member.getMember_id().toLowerCase();
		} else {
			model.addAttribute("msg", "가입 실패!");
			return "fail_back";
		}
		
	}
	// =======================================================================
	
	
	// ========================= 회원탈퇴 Controller =========================
	// 회원탈퇴 창
	@GetMapping(value = "MemberDeleteForm")
	public String deleteFrom(String id, HttpSession session, Model model) {
		String sId = (String)session.getAttribute("sId");
		
		if(sId == null || sId.equals("")) {
			model.addAttribute("msg", "잘못된 접근입니다!");
			return "fail_back";
		} else {
			if(id != null && !id.equals("") && !id.equals(sId) && !sId.equals("admin")) {
				model.addAttribute("msg", "권한이 없습니다!");
				return "fail_back";
			} else if(id.equals("")) {
				model.addAttribute("msg", "잘못된 접근입니다!");
				return "fail_back";
			}
			
			if(sId.equals("admin")) {
				return "redirect:/MemberDeletePro?id=" + id;
			} else {
				return "member/member_delete";		
			}
		}
		
	}
	
	
	// 회원탈퇴
	@RequestMapping(value = "/MemberDeletePro",
			method = {RequestMethod.GET, RequestMethod.POST})
	public String deletePro(HttpSession session, String id, String passwd, Model model) {
		String sId = (String)session.getAttribute("sId");
		
		if(!sId.equals("admin")) {
			BCryptPasswordEncoder passwdEncoder = new BCryptPasswordEncoder();
			String member_passwd = service.getPasswd(id);
			
			if(passwd == null || !passwdEncoder.matches(passwd, member_passwd)) {
				model.addAttribute("msg", "권한이 없습니다!");
				return "fail_back";
			}
		}
		
		int deleteCount  = service.removeMember(id);
		
		// 삭제 성공/실패에 따른 포워딩 작업 수행
		if(deleteCount > 0) {
			if(sId.equals("admin")) {
				return "redirect:/AdminMain";
			} else {
				session.invalidate();
				return "member/member_delete_result";
			}
		} else {
			model.addAttribute("msg", "회원탈퇴 실패!");
			return "fail_back";
		}
		
	}
	// =======================================================================
	
	
	// ================= 회원 아이디/비밀번호 찾기 Controller ================
	// 아이디 찾기
	@GetMapping(value = "MemberInfoSearchForm")
	public String search() {
		return "member/member_info_search_form";
	}
	
	@PostMapping(value = "MemberSearchId")
	@ResponseBody
	public String searchId(@ModelAttribute MemberVO member) {
		String findId = service.searchId(member);
		
		if(findId != null) {
			// 찾은 아이디의 일부만 표시하기 위한 작업
			char[] idArr = findId.toCharArray();
			idArr[1] = '*';
			idArr[2] = '*';
			findId = String.valueOf(idArr);
			
			return findId;
		} else {
			return "";			
		}
		
	}
	
	
	// 비밀번호 찾기
	@PostMapping(value = "MemberSearchPasswd")
	public String searchPasswd(String id, Model model) {
		model.addAttribute("id", id);
		return "member/member_search_passwd_result";
	}
	
	
	// 임시 비밀번호 메일 전송
	@PostMapping(value = "sendTempPassword")
	public void sendTempPassword(String id) {
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
		MemberVO member = service.SearchNE(id);
		
		try {
			String sender = "ths8190@gmail.com";
			String receiver = member.getMember_email();
			String title = "댕글댕글 임시 비밀번호입니다.";
			String content = member.getMember_name() + "님, 임시 비밀번호를 발급하였습니다.<br>"
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
		BCryptPasswordEncoder passwdEncoder = new BCryptPasswordEncoder();
		tempPasswd = passwdEncoder.encode(tempPasswd);
		
		service.registTempPasswd(member.getMember_id(), tempPasswd);
		// --------------------------------------------------------------------
		
	}
	
	// =======================================================================
	
}

