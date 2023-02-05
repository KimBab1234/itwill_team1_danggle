package com.itwillbs.team1_final.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.itwillbs.team1_final.vo.AccVO;
import com.itwillbs.team1_final.vo.HrVO;
import com.itwillbs.team1_final.vo.PdVO;

public interface OutMapper {

	List<AccVO> selectAcc(
			@Param("searchType") String searchType,
			@Param("keyword") String keyword);

	ArrayList<HrVO> selectEmp(String keyword);

	ArrayList<PdVO> selectPd(
			@Param("searchType") String searchType,
			@Param("keyword") String keyword);

}
