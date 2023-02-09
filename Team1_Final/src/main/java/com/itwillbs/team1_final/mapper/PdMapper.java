package com.itwillbs.team1_final.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.itwillbs.team1_final.vo.PdVO;

public interface PdMapper {

	//품목 등록
	int insertPd(PdVO product);

	//품목 목록 조회
	List<PdVO> selectPdList(@Param("searchType")String searchType, @Param("keyword")String keyword, 
			@Param("startRow")int startRow, @Param("listLimit")int listLimit, @Param("search")String search );

	// 품목 그룹(소) 선택
	ArrayList<PdVO> selectPd_group_bottom_Search(String keyword);

	// 품목 구분 선택
	ArrayList<PdVO> selectPd_type_Search(String keyword);

	// 거래처 선택
	ArrayList<PdVO> selectBusiness_no_Search(String keyword);

	// 품목 그룹 등록
	int insertPd_group_bottom(PdVO product);

	// 품목 그룹(대) 선택
	ArrayList<PdVO> selectPd_group_top_Search(String keyword);

	// 품목 삭제
	int deletePd(int product_CD);

	// 품목 수정 전 품목 조회
	PdVO selectPd(int PRODUCT_CD);
	
	// 품목 삭제 전 이미지 파일이름 얻어오기
	List<String> selectImgList(String deleteProdArr);

	// 품목 수정
	int updatePd(PdVO product);



}
