package com.itwillbs.team1_final.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.team1_final.mapper.HrMapper;

@Service
public class HrService {
	
	@Autowired
	HrMapper mapper;
	
	public int registHr() {
		return mapper.insertHr();
	}

}
