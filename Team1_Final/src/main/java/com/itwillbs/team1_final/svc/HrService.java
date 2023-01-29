package com.itwillbs.team1_final.svc;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.team1_final.mapper.HrMapper;
import com.itwillbs.team1_final.vo.HrVO;

@Service
public class HrService {
	
	@Autowired
	HrMapper mapper;
	
	public int registHr() {
		return mapper.insertHr();
	}
	public ArrayList<HrVO> getHrInfoJoin() {
		return mapper.selectInfoForJoin();
	}
	public ArrayList<HrVO> getDepartSearch(String keyword) {
		return mapper.selectDepartSearch(keyword);
	}

}
