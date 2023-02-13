package com.itwillbs.team1.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.itwillbs.team1.action.Action;
import com.itwillbs.team1.action.ReviewDeleteProAction;
import com.itwillbs.team1.action.ReviewDetailAction;
import com.itwillbs.team1.action.ReviewLikeUpdateAction;
import com.itwillbs.team1.action.ReviewListAction;
import com.itwillbs.team1.action.ReviewModifyFormAction;
import com.itwillbs.team1.action.ReviewModifyProAction;
import com.itwillbs.team1.action.ReviewWriteFormAction;
import com.itwillbs.team1.action.ReviewWriteProAction;
import com.itwillbs.team1.vo.ActionForward;
import com.itwillbs.team1.vo.ReviewBean;

@Controller
public class ReviewFrontController {
	
	// ===============================================================================
	// 리뷰쓰기 폼
	@GetMapping(value = "ReviewWriteForm.re" )
	public String review_write() {
		System.out.println("리뷰쓰기 폼");
		return "review/review_write";
	}
	
	@PostMapping(value = "ReviewWritePro.re")
	public String review_writePro(@ModelAttribute ReviewBean review, Model model, HttpSession session) {
		System.out.println("리뷰쓰기 작업");
		
		return "";
	}
	
	if(command.equals("/ReviewWriteForm.re")) {
		System.out.println("리뷰쓰기 폼");
		action= new ReviewWriteFormAction();
		forward = action.execute(request, response);	
	
	}else if(command.equals("/ReviewWritePro.re")) {
		System.out.println("리뷰쓰기 작업");
		action= new ReviewWriteProAction();
		forward = action.execute(request, response);
		
	}else if(command.equals("/ReviewList.re")) {
		System.out.println("리뷰 목록 작업");
		action = new ReviewListAction();
		forward = action.execute(request, response);
		
	}else if(command.equals("/ReviewDetail.re")) {
		System.out.println("리뷰 디테일 작업");
		action = new ReviewDetailAction();
		forward = action.execute(request, response);
		
	}else if(command.equals("/ReviewDeleteForm.re")) {
		System.out.println("리뷰 삭제 폼 작업");
		forward = new ActionForward();
		forward.setPath("review/review_delete.jsp");
		forward.setRedirect(false);
		
	}else if(command.equals("/ReviewDeletePro.re")) {
		System.out.println("리뷰 삭제 프로 작업");
		action = new ReviewDeleteProAction();
		forward = action.execute(request, response);
		
	} else if(command.equals("/ReviewModifyForm.re")) {
		System.out.println("리뷰 수정 폼 작업");
		action = new ReviewModifyFormAction();
		forward = action.execute(request, response);
		
	} else if(command.equals("/ReviewModifyPro.re")) {
		System.out.println("리뷰 수정 프로 작업");
		action = new ReviewModifyProAction();
		forward = action.execute(request, response);
		
	} else if(command.equals("/ReviewLikeUpdate.re")) {
		System.out.println("리뷰 좋아요 작업");
		action = new ReviewLikeUpdateAction();
		forward = action.execute(request, response);
	}	
	

	

}
