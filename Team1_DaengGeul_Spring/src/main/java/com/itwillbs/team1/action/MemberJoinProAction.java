package com.itwillbs.team1.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.team1.encrypt.MyMessageDigest;
import com.itwillbs.team1.svc.MemberJoinProService;
import com.itwillbs.team1.vo.ActionForward;
import com.itwillbs.team1.vo.MemberBean;

public class MemberJoinProAction implements Action {

	// ---------------------------------- 회원가입 ---------------------------------------
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;

		String name = request.getParameter("name");
		String email = request.getParameter("email1")+ "@" + request.getParameter("email2");
		
		MemberBean member = new MemberBean();
		member.setMember_id(request.getParameter("id"));
		// ----------------- 패스워드 암호화(해싱) 기능 추가 ----------------------
		MyMessageDigest md = new MyMessageDigest("SHA-256");
		member.setMember_passwd(md.hashing(request.getParameter("passwd")));
		// ------------------------------------------------------------------------
		member.setMember_name(name);
		member.setMember_gender(request.getParameter("gender"));
		member.setMember_email(email);
		member.setMember_phone(request.getParameter("phone1")
						+ "-" + request.getParameter("phone2")
						+ "-" + request.getParameter("phone3"));
		member.setMember_postcode(request.getParameter("postcode"));
		member.setMember_roadAddress(request.getParameter("roadAddress"));
		member.setMember_jibunAddress(request.getParameter("jibunAddress"));
		member.setMember_addressDetail(request.getParameter("addressDetail"));
		member.setMember_point(1500);
		member.setMember_coupon("WelcomeCoupon");
		
//		System.out.println(member); // 값 정상적으로 전달완료 확인
		
		MemberJoinProService service = new MemberJoinProService();
		boolean isJoinSuccess = service.joinMember(member);
		
		try {
			// 회원가입 실패 시
			if(!isJoinSuccess) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('회원가입 실패!')");
				out.println("history.back()");
				out.println("</script>");
				
			} else {
				// 회원가입 성공 시
				forward = new ActionForward();
				request.setAttribute("name", name);
				request.setAttribute("email", email);
				forward.setPath("member/member_join_result.jsp");
				forward.setRedirect(false);
				
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return forward;
	}
	// -----------------------------------------------------------------------------------
	
}