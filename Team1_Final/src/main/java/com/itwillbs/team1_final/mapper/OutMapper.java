package com.itwillbs.team1_final.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.itwillbs.team1_final.vo.AccVO;

public interface OutMapper {

	List<AccVO> selectAcc(
			@Param("searchType") String searchType,
			@Param("keyword") String keyword);

}
