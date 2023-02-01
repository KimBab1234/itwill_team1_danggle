package com.itwillbs.team1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.itwillbs.team1.vo.WishlistVO;

public interface WishMapper {

	int insertWish(
			@Param("product_idx") String product_idx,
			@Param("sId") String sId);

	int selectWishlistCount(
			@Param("sId") String sId,
			@Param("searchType") String searchType,
			@Param("keyword") String keyword);

	List<WishlistVO> selectWishlist(
			@Param("sId") String sId, 
			@Param("searchType") String searchType, 
			@Param("keyword") String keyword, 
			@Param("startRow") int startRow, 
			@Param("listLimit") int listLimit);

	int deleteWish(
			@Param("sId") String sId,
			@Param("listArr") String[] listArr);

	
}
