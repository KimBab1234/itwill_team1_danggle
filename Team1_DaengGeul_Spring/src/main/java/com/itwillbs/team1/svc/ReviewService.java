package com.itwillbs.team1.svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.team1.mapper.OrderMapper;
import com.itwillbs.team1.mapper.ReviewMapper;
import com.itwillbs.team1.vo.ReviewBean;

@Service
public class ReviewService {

	@Autowired
	private ReviewMapper mapper;

	@Autowired
	private OrderMapper mapper2;

	// 리뷰 등록
	public int registReview(ReviewBean review) {

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
	public List<ReviewBean> listReview(String member_id, String product_idx, String keyword, int startRow, int listLimit) {
		return mapper.selectReviewList(keyword, member_id, product_idx, startRow, listLimit);
	}

	// 리뷰 수 조회
	public int reviewListCount(String member_id, String keyword, String product_idx) {
		return mapper.selectReviewListCount(keyword, member_id, product_idx);
	}

	// 리뷰 조회
	public ReviewBean getReview(int review_idx, String member_id) {
		return mapper.selectReview(review_idx, member_id);
	}

	// 조회수 증가
	public void increaseReadcount(int review_idx) {
		mapper.updateReadcount(review_idx);
	}
	
	// 리뷰 좋아요 조회
	public String selectReviewLike(int review_idx, String member_id) {
		
		boolean selectReviewLike = mapper.selectreviewLike(review_idx, member_id);
		
		String review_like_done = "N";
		
		if(selectReviewLike) {
			review_like_done = "Y";
		} 
		
		System.out.println("selectReviewLike : " + selectReviewLike);
		
		return review_like_done;
	}

	// 리뷰 비밀번호 일치여부 판별
	public ReviewBean isReviewWriter(int review_idx, String review_passwd) {
		return mapper.selectReviewWriter(review_idx, review_passwd);
	}

	// 리뷰 삭제
	public int removeReview(int review_idx) {
		return mapper.deleteReview(review_idx);
	}

	// 리뷰 수정
	public int modifyReview(ReviewBean review) {
		return mapper.updateReview(review);
	}

	// 리뷰 좋아요 추가
	public int insertReviewLike(int review_idx, String member_id, String review_like_done) {
		
		 int review_like_count = mapper.insertReviewLike(review_idx, member_id, review_like_done);
		
		return review_like_count;
	}

	// 리뷰 좋아요 삭제
	public int deleteReviewLike(int review_idx, String member_id, String review_like_done) {
		
		int review_like_count = mapper.deleteReviewLike(review_idx, member_id, review_like_done);
		
		return review_like_count;
	}

	// 리뷰 좋아요 업데이트
	public int updateReviewLike(int review_like_count, int review_idx) {
		return mapper.updateReviewLike(review_like_count, review_idx);
	}

	// 리뷰 좋아요 갯수 조회
	public int getReviewLikeCount(int review_idx) {
		return mapper.getReviewLikeCount(review_idx);
	}






	
	
	
}
