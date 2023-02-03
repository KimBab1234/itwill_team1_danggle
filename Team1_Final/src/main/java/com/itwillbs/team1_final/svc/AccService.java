package com.itwillbs.team1_final.svc;

import java.util.List;

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

	public List<AccVO> getAccList() {
		return mapper.accList();
	}
	
	
}
