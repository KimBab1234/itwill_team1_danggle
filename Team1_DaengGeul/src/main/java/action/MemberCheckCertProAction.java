package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberCheckCertProService;
import vo.ActionForward;

public class MemberCheckCertProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		try {
			String id = request.getParameter("id");
			String certNum = request.getParameter("certNum");
//			System.out.println("액션 id: " + id + ", certNum : " + certNum); // 파라미터 확인용
			
			MemberCheckCertProService service = new MemberCheckCertProService();
			String selectedCertNum = service.getAuthCode(id);
			
			// 입력받은 인증코드와 조회한 인증코드 일치여부 확인
			if(selectedCertNum != null) {
				if(certNum.equals(selectedCertNum)) {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.print("true");
				} else {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.print("false");
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
