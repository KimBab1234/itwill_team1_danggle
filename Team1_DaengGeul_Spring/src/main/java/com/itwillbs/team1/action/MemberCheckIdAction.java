package com.itwillbs.team1.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.team1.svc.MemberCheckIdService;
import com.itwillbs.team1.vo.ActionForward;

public class MemberCheckIdAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		try {
			String id = request.getParameter("id");
			
			MemberCheckIdService service = new MemberCheckIdService(); 
			boolean isExist = service.isExistId(id);
			
			if(!isExist) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("false");
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("true");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}