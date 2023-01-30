package com.itwillbs.team1_final.svc;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwillbs.team1_final.mapper.HrMapper;
import com.itwillbs.team1_final.vo.HrVO;

@Service
@Transactional
public class HrService {
	
	@Autowired
	HrMapper mapper;
	
	public int registHr(HrVO newEmp) {
		return mapper.insertHr(newEmp);
	}
	public ArrayList<HrVO> getGradeInfo() {
		return mapper.selectGradeInfo();
	}
	public ArrayList<HrVO> getDepartSearch(String keyword) {
		return mapper.selectDepartSearch(keyword);
	}

}
