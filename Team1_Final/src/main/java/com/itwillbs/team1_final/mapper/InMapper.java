package com.itwillbs.team1_final.mapper;

import java.util.ArrayList;
import java.util.List;

import com.itwillbs.team1_final.vo.AccVO;
import com.itwillbs.team1_final.vo.HrVO;
import com.itwillbs.team1_final.vo.InPdVO;
import com.itwillbs.team1_final.vo.InVO;
import com.itwillbs.team1_final.vo.PdVO;

public interface InMapper {

	ArrayList<HrVO> selectHrList(String keyword); // 사원 검색

	ArrayList<PdVO> selectProductList(String keyword); // 품목 검색

	List<AccVO> selectAcc(String keyword); // 거래처 검색

	int selectToday(String today); // 입고예정코드 조회

	void insertIncoming(InVO in); // 입고예정등록

	int insertIncomingProduct(InPdVO product);

}
