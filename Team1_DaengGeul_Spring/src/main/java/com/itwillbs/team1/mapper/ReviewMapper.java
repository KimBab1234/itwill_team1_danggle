package com.itwillbs.team1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.itwillbs.team1.vo.ReviewBean;

@Mapper
@Transactional(rollbackFor = Exception.class)
public interface ReviewMapper {

	// 리뷰 쓰기
	public int insertReview(ReviewBean review);
	
	// 리뷰 목록 
	public List<ReviewBean> selectReviewList(@Param("keyword")String keyword, 
											@Param("member_id")String member_id, 
											@Param("product_idx") String product_idx, 
											@Param("startRow")int startRow, 
											@Param("listLimit")int listLimit);  
	
	// 리뷰 리스트 수
	public int selectReviewListCount(@Param("keyword")String keyword, 
									@Param("member_id")String member_id, 
									@Param("product_idx")String product_idx); 
	
	//리뷰 조회
	public ReviewBean selectReview(@Param("review_idx")int review_idx, 
								@Param("member_id")String member_id);
	
	// 리뷰 조회수 증가
	public void updateReadcount(int review_idx);
	
	// 리뷰 좋아요 조회
	public boolean selectreviewLike(@Param("review_idx")int review_idx, 
									@Param("member_id")String member_id);
	
	// 리뷰 비밀번호 일치여부 판별
	public ReviewBean selectReviewWriter(@Param("review_idx")int review_idx, 
										@Param("review_passwd")String review_passwd);
	
	// 리뷰 삭제
	public int deleteReview(int review_idx);
	
	// 리뷰 수정
	public int updateReview(ReviewBean review);
	
	// 리뷰 좋아요 추가(누른 아이디 , 글번호 추가) 
	public int insertReviewLike(@Param("review_idx")int review_idx, 
								@Param("member_id")String member_id, 
								@Param("review_like_done")String review_like_done);

	// 리뷰 좋아요 취소
	public int deleteReviewLike(@Param("review_idx")int review_idx, 
								@Param("member_id")String member_id, 
								@Param("review_like_done")String review_like_done);

	// 리뷰 좋아요 업데이트
	public int updateReviewLike(@Param("review_like_count")int review_like_count, 
								@Param("review_idx")int review_idx);

	// 리뷰 좋아요 갯수 조회
	public int getReviewLikeCount(int review_idx);




	


}
