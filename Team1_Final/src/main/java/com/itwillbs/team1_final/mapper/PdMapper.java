package com.itwillbs.team1_final.mapper;

import java.util.List;

import com.itwillbs.team1_final.vo.PdVO;

public interface PdMapper {

	int insertPd(PdVO product);

	List<PdVO> selectPdList(String searchType, String keyword, int startRow, int listLimit);

}
