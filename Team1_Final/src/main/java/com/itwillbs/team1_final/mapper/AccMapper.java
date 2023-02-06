package com.itwillbs.team1_final.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.itwillbs.team1_final.vo.AccVO;

public interface AccMapper {

	int insertAcc(AccVO acc);

	List<AccVO> accList(@Param("searchType") String searchType
					   ,@Param("keyword") String keyword
					   ,@Param("startRow") int startRow
					   ,@Param("listLimit") int listLimit);

	AccVO accView(String BUSINESS_NO);

	int accDelete(AccVO acc);

	int accModify(AccVO acc);

	int accListCount(@Param("searchType") String searchType,
					 @Param("keyword") String keyword);

}
