package com.itwillbs.team1_final.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.team1_final.mapper.OutMapper;

@Service
public class OutService {

	@Autowired
	OutMapper mapper;
	
	
}
