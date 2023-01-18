package com.itwillbs.team1.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.team1.svc.MemberInfoService;
import com.itwillbs.team1.vo.ActionForward;
import com.itwillbs.team1.vo.MemberBean;

public class OrderPayFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("===========================");
		System.out.println("OrderPayFormAction 진입");
		ActionForward forward=new ActionForward();
		

		//멤버 테이블에서 적립금, 쿠폰 정보 가져오기
		String id = (String)request.getSession().getAttribute("sId");
		MemberInfoService moService = new MemberInfoService();
		MemberBean member = moService.getMemberInfo(id);
		request.setAttribute("member", member);
		request.setAttribute("totalPay", request.getParameter("totalPay"));
		
		forward.setPath("order/pay.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
