package com.itwillbs.team1_final.mapper;

import java.util.ArrayList;

import com.itwillbs.team1_final.vo.HrVO;
import com.itwillbs.team1_final.vo.PdVO;

public interface InMapper {

	ArrayList<HrVO> selectHrList(String keyword);

	ArrayList<PdVO> selectProductList(String keyword);

}
