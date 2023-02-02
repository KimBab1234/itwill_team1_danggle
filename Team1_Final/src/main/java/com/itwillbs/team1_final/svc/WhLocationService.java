package com.itwillbs.team1_final.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.team1_final.mapper.WhLocationMapper;

@Service
public class WhLocationService {

	@Autowired
	private WhLocationMapper mapper;
	
	
}
