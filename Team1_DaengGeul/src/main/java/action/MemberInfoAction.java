package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberInfoService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		String sId = (String)session.getAttribute("sId"); // 인증되지 않은 경로 접근 방지를 위한 세션 아이디
//		System.out.println("Info sId : " + sId); // 세션 아이디 정상 전달 확인
		String id = request.getParameter("id"); // 회원 정보 조회를 위해 전달 받은 아이디
//		System.out.println("info id : " + id); // 회원 아이디 정상 전달 확인
		
		try {
			if(sId != null && !sId.equals("")) {
				// 관리자용 - 목록 아이디값 전달
				if(sId.equals("admin")) {
					MemberInfoService service = new MemberInfoService();
					MemberBean member = service.getMemberInfo(id);
					
					// 회원정보수정을 위해 이메일 주소/도메인 분리 작업
					String email = member.getMember_email();
					String email1 = null;
					String email2 = null;

					String tempEmail[] = email.split("@");
					if (tempEmail != null && tempEmail.length == 2) {
						email1 = tempEmail[0];
						email2 = tempEmail[1];
					}
					
					// 회원정보수정을 위해 전화번호 분리 작업
					String phone = member.getMember_phone();
					String phone1 = null;
					String phone2 = null;
					String phone3 = null;
					
					String tempPhone[] = phone.split("-");
					if (tempPhone != null && tempPhone.length == 3) {
						phone1 = tempPhone[0];
						phone2 = tempPhone[1];
						phone3 = tempPhone[2];
					}

					request.setAttribute("email1", email1);
					request.setAttribute("email2", email2);
					request.setAttribute("phone1", phone1);
					request.setAttribute("phone2", phone2);
					request.setAttribute("phone3", phone3);
					
					request.setAttribute("member", member);
					forward = new ActionForward();
					forward.setPath("member/member_info.jsp");
					forward.setRedirect(false);
					
				// 회원용 - 로그인된 세션아이디값 전달
				} else {
					MemberInfoService service = new MemberInfoService();
					MemberBean member = service.getMemberInfo(sId);
					
					// 회원정보수정을 위해 이메일 주소/도메인 분리 작업
					String email = member.getMember_email();
					String email1 = null;
					String email2 = null;

					String tempEmail[] = email.split("@");
					if (tempEmail != null && tempEmail.length == 2) {
						email1 = tempEmail[0];
						email2 = tempEmail[1];
					}
					
					// 회원정보수정을 위해 전화번호 분리 작업
					String phone = member.getMember_phone();
					String phone1 = null;
					String phone2 = null;
					String phone3 = null;
					
					String tempPhone[] = phone.split("-");
					if (tempPhone != null && tempPhone.length == 3) {
						phone1 = tempPhone[0];
						phone2 = tempPhone[1];
						phone3 = tempPhone[2];
					}

					request.setAttribute("email1", email1);
					request.setAttribute("email2", email2);
					
					request.setAttribute("phone1", phone1);
					request.setAttribute("phone2", phone2);
					request.setAttribute("phone3", phone3);
					
					request.setAttribute("member", member);
					forward = new ActionForward();
					forward.setPath("member/member_info.jsp");
					forward.setRedirect(false);
				}
				
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('잘못된 접근입니다!')");
				out.println("history.back()");
				out.println("</script>");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}