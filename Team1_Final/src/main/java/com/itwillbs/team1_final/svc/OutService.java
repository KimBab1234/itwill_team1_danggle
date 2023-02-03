package com.itwillbs.team1_final.svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.team1_final.mapper.OutMapper;
import com.itwillbs.team1_final.vo.AccVO;

@Service
public class OutService {

	@Autowired
	OutMapper mapper;

	public List<AccVO> searchAcc(String searchType, String keyword) {
		return mapper.selectAcc(searchType, keyword);
	}
	
	
}
