package com.itwillbs.team1.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.team1.encrypt.MyMessageDigest;
import com.itwillbs.team1.svc.MemberUpdateProService;
import com.itwillbs.team1.vo.ActionForward;
import com.itwillbs.team1.vo.MemberBean;

public class MemberUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;

		MemberBean member = new MemberBean();
		member.setMember_id(request.getParameter("id"));
		// 유저확인용 기존비밀번호, 암호화된 비밀번호가 저장되어 있으므로 암호화하여 확인
		MyMessageDigest md = new MyMessageDigest("SHA-256");
		String passwd = md.hashing(request.getParameter("oldPasswd"));
		member.setMember_passwd(passwd);
		// ------------------------------------------------------------------------------
		member.setMember_name(request.getParameter("name"));
		member.setMember_email(request.getParameter("email1")+ "@" + request.getParameter("email2"));
		member.setMember_postcode(request.getParameter("postcode"));
		member.setMember_roadAddress(request.getParameter("roadAddress"));
		member.setMember_jibunAddress(request.getParameter("jibunAddress"));
		member.setMember_addressDetail(request.getParameter("addressDetail"));
		member.setMember_phone(request.getParameter("phone1")
				+ "-" + request.getParameter("phone2")
				+ "-" + request.getParameter("phone3"));
		
		// 변경 시, 새 비밀번호와 확인용 비밀번호
		String newPasswd = request.getParameter("newPasswd");
		String newPasswd2 = request.getParameter("newPasswd2");

		// 암호화 시, 널스트링이나 공백까지 암호화하므로 
		// 추가적으로 해싱 조건 필요
		if(newPasswd != null && newPasswd != "") {
			newPasswd = md.hashing(newPasswd);
			newPasswd2 = md.hashing(newPasswd2);			
		}
		
		MemberUpdateProService service = new MemberUpdateProService();
		int updateCount = service.updateMember(member, newPasswd, newPasswd2);
		
		try {
			if(!newPasswd.equals(newPasswd2)) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('변경할 비밀번호를 일치시켜주세요!')");
				out.println("history.back()");
				out.println("</script>");
				
			} else if(updateCount > 0){
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('회원정보 수정 완료!')");
				out.println("location.href='MemberInfo.me?id=" + request.getParameter("id") + "'");
				out.println("</script>");
				
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('비밀번호를 확인하세요!')");
				out.println("history.back()");
				out.println("</script>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}