package com.itwillbs.team1_final.mapper;

import java.util.ArrayList;

import com.itwillbs.team1_final.vo.HrVO;

public interface HrMapper {

	public int insertHr();
	public ArrayList<HrVO> selectInfoForJoin();
	
}
