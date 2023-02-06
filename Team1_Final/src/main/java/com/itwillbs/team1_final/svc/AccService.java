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

	public int modifyMemberInfo() {
		return 0;
	}

	public AccVO accDetail(String BUSINESS_NO) {
		return mapper.accView(BUSINESS_NO);
	}

	public int accDelete(AccVO acc) {
		return mapper.accDelete(acc);
	}

	public int accModify(AccVO acc) {
		return mapper.accModify(acc);
	}
	
	
}
