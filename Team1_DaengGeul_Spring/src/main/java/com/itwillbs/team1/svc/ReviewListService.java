package com.itwillbs.team1.svc;

import java.sql.Connection;
import java.util.List;

import com.itwillbs.team1.dao.ReviewDAO;
import com.itwillbs.team1.db.JdbcUtil;
import com.itwillbs.team1.vo.ReviewBean;

public class ReviewListService {

	public List<ReviewBean> getReviewList(String keyword, String id, String product_idx, int startRow, int listLimit) {
		System.out.println("ReviewListService - getReviewList()");
		List<ReviewBean> reviewList = null;
		
		//공통작업 1. Connection 객체 가져오기
		Connection con = JdbcUtil.getConnection();
		
		//공통작업 2. BoardDAO 객체 가져오기
		ReviewDAO dao = ReviewDAO.getInstance();
		
		//공통작업 3. BoardDAO 객체에 Connection 객체 전달하기
		dao.setConnection(con);
		
		//
		reviewList = dao.selectReviewList(keyword, id, product_idx, startRow, listLimit);
		
		JdbcUtil.commit(con);
		
		//공통작업 4. Connection 객체 반환하기
		JdbcUtil.close(con);
		
		return reviewList;
		
	}

	public int getReviewdListCount(String keyword, String id, String product_idx) {
		int listCount = 0;
		
		//공통작업 1. Connection 객체 가져오기
		Connection con = JdbcUtil.getConnection();
		
		//공통작업 2. BoardDAO 객체 가져오기
		ReviewDAO dao = ReviewDAO.getInstance();
		
		//공통작업 3. BoardDAO 객체에 Connection 객체 전달하기
		dao.setConnection(con);
		
		//
		listCount = dao.selectReviewListCount(keyword, id, product_idx);
		
		//공통작업 4. Connection 객체 반환하기
		JdbcUtil.close(con);
		
		return listCount;
		
	}

}
