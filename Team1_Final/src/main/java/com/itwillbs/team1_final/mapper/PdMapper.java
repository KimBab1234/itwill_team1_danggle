package com.itwillbs.team1_final.mapper;

import java.util.ArrayList;
import java.util.List;

import com.itwillbs.team1_final.vo.PdVO;

public interface PdMapper {

	//품목 등록
	int insertPd(PdVO product);

	//품목 목록 조회
	List<PdVO> selectPdList(String searchType, String keyword, int startRow, int listLimit);

	// 품목 그룹(소) 선택
	ArrayList<PdVO> selectPd_group_bottom_Search(String keyword);

	// 품목 구분 선택
	ArrayList<PdVO> selectPd_type_Search(String keyword);

}
