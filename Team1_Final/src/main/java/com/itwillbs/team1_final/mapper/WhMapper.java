package com.itwillbs.team1_final.mapper;

import java.util.List;

import com.itwillbs.team1_final.vo.WhVO;

public interface WhMapper {

	List<WhVO> selectWhList(String searchType, String keyword, int startRow, int listLimit);

	int selectWhListCount(String searchType, String keyword);

}
