package action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

public class MemberLogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("===========================");
		System.out.println("MemberLogoutAction 진입");
		ActionForward forward=new ActionForward();
		
		//session 로그아웃 처리하기
		request.getSession().invalidate(); //1. 아예 초기화
		request.getSession().removeAttribute("sId"); //2. 해당 attribute만 삭제
		
		forward.setPath("./");
		forward.setRedirect(true);
		
		return forward;
	}

}
