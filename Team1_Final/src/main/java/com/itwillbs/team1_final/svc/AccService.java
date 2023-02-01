package com.itwillbs.team1_final.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.team1_final.mapper.AccMapper;
import com.itwillbs.team1_final.vo.AccVO;

@Service
public class AccService {
	
	@Autowired
	AccMapper mapper;

	public int insertAcc(AccVO acc) {
		return mapper.insertAcc(acc);
	}
	
	
}
