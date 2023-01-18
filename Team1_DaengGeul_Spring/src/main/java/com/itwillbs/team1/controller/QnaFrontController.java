package com.itwillbs.team1.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.team1.action.Action;
import com.itwillbs.team1.action.CommonDeleteProAction;
import com.itwillbs.team1.action.CommonDetailAction;
import com.itwillbs.team1.action.CommonListAction;
import com.itwillbs.team1.action.CommonModifyFormAction;
import com.itwillbs.team1.action.CommonModifyProAction;
import com.itwillbs.team1.action.CommonWriteProAction;
import com.itwillbs.team1.action.QnaDeleteProAction;
import com.itwillbs.team1.action.QnaDetailAction;
import com.itwillbs.team1.action.QnaEmailResult;
import com.itwillbs.team1.action.QnaListAction;
import com.itwillbs.team1.action.QnaModifyFormAction;
import com.itwillbs.team1.action.QnaModifyProAction;
import com.itwillbs.team1.action.QnaReplyFormAction;
import com.itwillbs.team1.action.QnaReplyProAction;
import com.itwillbs.team1.action.QnaWriteProAction;
import com.itwillbs.team1.vo.ActionForward;

@WebServlet("*.cu")
public class QnaFrontController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String command = request.getServletPath();
		
		Action action = null;
		ActionForward forward = null;
		
		if (command.equals("/QnaWriteForm.cu")) {
			forward = new ActionForward();
			forward.setPath("customer/qna_write.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/QnaWritePro.cu")) {
			action = new QnaWriteProAction();
			forward = action.execute(request, response);
		} else if(command.equals("/QnaList.cu")) {
			action = new QnaListAction();
			forward = action.execute(request, response);
		} else if(command.equals("/QnaDetail.cu")) {
			action = new QnaDetailAction();
			forward = action.execute(request, response);
		}  else if (command.equals("/QnaDeleteForm.cu")) {
			System.out.println("글 삭제 작업");
			forward = new ActionForward();
			forward.setPath("customer/qna_delete.jsp");
			forward.setRedirect(false);
			System.out.println("글번호" + request.getParameter("qna_idx"));
		} else if (command.equals("/QnaDeletePro.cu")) {
//			System.out.println("글 삭제 작업2");
//			System.out.println("글번호22" + request.getParameter("qna_idx"));
			action=new QnaDeleteProAction();
			forward = action.execute(request, response);
		} else if (command.equals("/QnaModifyForm.cu")) {
			action = new QnaModifyFormAction();
			forward = action.execute(request, response);
		}else if(command.equals("/QnaModifyPro.cu")) {
			action = new QnaModifyProAction();
			forward = action.execute(request, response);
		}else if(command.equals("/QnaReplyForm.cu")) {
			action = new QnaReplyFormAction();
			forward = action.execute(request, response);
		}else if(command.equals("/QnaReplyPro.cu")) {
			action = new QnaReplyProAction();
			forward = action.execute(request, response);
		}else if (command.equals("/CommonWriteForm.cu")) {
			forward = new ActionForward();
			forward.setPath("customer/common_write.jsp");
			forward.setRedirect(false);
		}else if(command.equals("/CommonWritePro.cu")) {
			action = new CommonWriteProAction();
			forward = action.execute(request, response);
		}else if(command.equals("/CommonList.cu")) {
			action = new CommonListAction();
			forward = action.execute(request, response);
		}else if(command.equals("/CommonDetail.cu")) {
			action = new CommonDetailAction();
			forward = action.execute(request, response);
		}else if (command.equals("/CommonDeleteForm.cu")) {
			forward = new ActionForward();
			forward.setPath("customer/common_delete.jsp");
			forward.setRedirect(false);
		}else if(command.equals("/CommonDeletePro.cu")) {
			action = new CommonDeleteProAction();
			forward = action.execute(request, response);
		} else if(command.equals("/CommonModifyForm.cu")) {
			action = new CommonModifyFormAction();
			forward = action.execute(request, response);
		} else if(command.equals("/CommonModifyPro.cu")) {
			action = new CommonModifyProAction();
			forward = action.execute(request, response);
		} else if (command.equals("/MailForm.cu")) {
			forward = new ActionForward();
			forward.setPath("mail/mail_form.jsp");
			forward.setRedirect(false);
		} else if (command.equals("/MailPro.cu")) {
			forward = new ActionForward();
			forward.setPath("mail/mail_Pro.jsp");
			forward.setRedirect(false);
		} else if (command.equals("/MailResult.cu")) {
			action = new QnaEmailResult();
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



