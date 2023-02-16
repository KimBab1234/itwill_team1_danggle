package com.itwillbs.team1.svc;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwillbs.team1.dao.ReviewDAO;
import com.itwillbs.team1.db.JdbcUtil;
import com.itwillbs.team1.mapper.OrderMapper;
import com.itwillbs.team1.mapper.ProductMapper;
import com.itwillbs.team1.mapper.ReviewMapper;
import com.itwillbs.team1.vo.OrderBean;
import com.itwillbs.team1.vo.OrderProdBean;
import com.itwillbs.team1.vo.ProductBean;
import com.itwillbs.team1.vo.ReviewBean;

@Service
public class ReviewService {

	@Autowired
	private ReviewMapper mapper;

	@Autowired
	private OrderMapper mapper2;

	//=====================주문 내역 가져오기=====================
	public boolean isReviewWriter(int review_idx, String review_passwd) {
		return mapper.isReviewWriter(review_idx, review_passwd);
	}

	public boolean removeReview(int review_idx) {
		boolean isDeleteSuccess = false;
		int deleteCount = mapper.deleteReview(review_idx);
		// 리턴받은 결과를 판별하여 commit, rollback
		if(deleteCount > 0) {
			isDeleteSuccess = true;
		}
		return isDeleteSuccess;
	}

	// 리뷰 등록
	public int registReview(ReviewBean review) {
		System.out.println("ReviewWriteProService - registReview()");

		int insertCount = mapper.insertReview(review);
		int updateCount = 0;
		if(insertCount > 0) { // 성공시
			updateCount = mapper2.updateMemberPoint(review.getMember_id(), 500);
		}
		if(updateCount>0) {
			
		} 
		return updateCount;
	}

	// 리뷰 목록
	public List<ReviewBean> listReview(@Param("member_id") String member_id, @Param("product_idx")String product_idx, @Param("keyword")String keyword, @Param("startRow")int startRow,
			@Param("listLimit")int listLimit) {
		return mapper.selectReviewList(keyword, member_id, product_idx, startRow, listLimit);
	}

	// 리뷰 갯수
	public int reviewListCount(String searchType) {
		// TODO Auto-generated method stub
		return 0;
	}


	
	
	
}
