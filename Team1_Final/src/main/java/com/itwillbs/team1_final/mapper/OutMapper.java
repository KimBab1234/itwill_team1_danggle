package com.itwillbs.team1_final.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.itwillbs.team1_final.vo.AccVO;
import com.itwillbs.team1_final.vo.HrVO;
import com.itwillbs.team1_final.vo.OutListInfoVO;
import com.itwillbs.team1_final.vo.OutPdVO;
import com.itwillbs.team1_final.vo.OutSchVo;
import com.itwillbs.team1_final.vo.PdVO;

public interface OutMapper {

	// 거래처 검색
	List<AccVO> selectAcc(
			@Param("searchType") String searchType,
			@Param("keyword") String keyword);

	// 담당자 검색 ( + 출고 예정 목록 조회 - 담당자)
	ArrayList<HrVO> selectEmp(String keyword);

	// 품목 검색
	ArrayList<PdVO> selectPd(
			@Param("searchType") String searchType,
			@Param("keyword") String keyword);

	// 출고예정코드 추가 또는 증가
	int selectToday(String oUT_TODAY);

	// 출고 예정 등록
	void insertOutSch(OutSchVo outSch);

	// 출고 예정 품목 등록
	int insertOutSchPd(OutPdVO product);

	// 출고 예정 목록 조회
	List<OutSchVo> selectOutSch(String keyword);
	
	// 출고 예정 목록 조회 - 출고 예정 품목
	List<OutPdVO> selectOutSchPd(String sOut_schesule_cd);

	List<OutPdVO> selectPdList(String product_cd);

	List<OutListInfoVO> selectoutListInfo(String sOut_schedule_cd);

	

}
