package com.itwillbs.team1.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.itwillbs.team1.vo.OrderBean;
import com.itwillbs.team1.vo.OrderProdBean;
import com.itwillbs.team1.vo.ReviewBean;

@Mapper
@Transactional(rollbackFor = Exception.class)
public interface ReviewMapper {

	// 리뷰 쓰기
	public int insertReview(ReviewBean review);
	// 리뷰 목록 
	public List<ReviewBean> selectReviewList(String keyword, String member_id, String product_idx,int startRow, int listLimit);  
	// 리뷰 리스트 수
	public int selectReviewListCount(String keyword, String id, String product_idx); 
	//리뷰 조회
	public ReviewBean selectReview(int review_idx, String sId);
	// 리뷰 조회수 증가
	public int updateReadCount(int review_idx);
	// 리뷰 글번호 조회
	public boolean isReviewWriter(int review_idx, String review_passwd);
	// 리뷰 삭제
	public int deleteReview(int review_idx);
	// 리뷰 수정
	public int updateReview(ReviewBean review);
	// 리뷰 좋아요 누른 아이디 , 글번호 추가 
	public int insertReviewLike(int review_idx, String member_id, String review_like_done);
	
//	public List<ReviewBean> selectReviewList(String searchType, String keyword, int startRow, int listLimit);
	

}
