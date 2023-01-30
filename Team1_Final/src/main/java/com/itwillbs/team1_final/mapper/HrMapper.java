package com.itwillbs.team1_final.mapper;

import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

import com.itwillbs.team1_final.vo.HrVO;

@Transactional
public interface HrMapper {

	public int insertHr(HrVO newEmp);
	public ArrayList<HrVO> selectGradeInfo();
	public ArrayList<HrVO> selectDepartSearch(String keyword);
	
}
