package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.CommonDeleteProAction;
import action.CommonDetailAction;
import action.CommonListAction;
import action.CommonModifyFormAction;
import action.CommonModifyProAction;
import action.CommonWriteProAction;
import action.QnaDetailAction;
import action.QnaWriteProAction;
import vo.ActionForward;

@WebServlet("*.co")
public class CommonFrontController extends HttpServlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String command = request.getServletPath();
		
		Action action = null;
		ActionForward forward = null;
    
		
		if (command.equals("/CommonWriteForm.co")) {
			forward = new ActionForward();
			forward.setPath("customer/common_write.jsp");
			forward.setRedirect(false);
		}else if(command.equals("/CommonWritePro.co")) {
			action = new CommonWriteProAction();
			forward = action.execute(request, response);
		}else if(command.equals("/CommonList.co")) {
			action = new CommonListAction();
			forward = action.execute(request, response);
		}else if(command.equals("/CommonDetail.co")) {
			action = new CommonDetailAction();
			forward = action.execute(request, response);
		}else if (command.equals("/CommonDeleteForm.co")) {
			forward = new ActionForward();
			forward.setPath("customer/common_delete.jsp");
			forward.setRedirect(false);
		}else if(command.equals("/CommonDeletePro.co")) {
			action = new CommonDeleteProAction();
			forward = action.execute(request, response);
		} else if(command.equals("/CommonModifyForm.co")) {
			action = new CommonModifyFormAction();
			forward = action.execute(request, response);
		} else if(command.equals("/CommonModifyPro.co")) {
			action = new CommonModifyProAction();
			forward = action.execute(request, response);
		} 
		
		
	
	if (forward != null) {
		if (forward.isRedirect()) {
			response.sendRedirect(forward.getPath());
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
			dispatcher.forward(request, response);
		}
	}
}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}

