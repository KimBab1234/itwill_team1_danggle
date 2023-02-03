package com.itwillbs.team1_final.svc;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.team1_final.mapper.InMapper;
import com.itwillbs.team1_final.vo.HrVO;

@Service
public class InService {
	@Autowired
	private InMapper mapper;

//	public ArrayList<HrVO> getHrList(String keyword) {
//		return mapper.selectHrList(keyword);
//	}
	
}
