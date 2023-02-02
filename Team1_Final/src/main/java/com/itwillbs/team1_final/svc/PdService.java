package com.itwillbs.team1_final.svc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.team1_final.mapper.PdMapper;
import com.itwillbs.team1_final.vo.PdVO;

@Service
public class PdService {

	@Autowired
	private PdMapper mapper;
	
	//품목 등록
	public int registPd(PdVO product) {
		return mapper.insertPd(product);
	}

	//품목 목록 조회
	public List<PdVO> getPdList(String searchType, String keyword, int startRow, int listLimit) {
		return mapper.selectPdList(searchType, keyword, startRow, listLimit);
	}

	// 품목 그룹(소) 선택
	public ArrayList<PdVO> getPd_group_bottom_Search(String keyword) {
		return mapper.selectPd_group_bottom_Search(keyword);
	}

}
