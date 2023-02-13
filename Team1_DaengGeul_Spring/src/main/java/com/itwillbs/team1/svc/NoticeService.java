package com.itwillbs.team1.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.team1.mapper.NoticeMapper;
import com.itwillbs.team1.vo.NoticeBean;

@Service
public class NoticeService {

	@Autowired
	private NoticeMapper mapper;

	public int registNotice(NoticeBean notice) {
		
		return mapper.insertNotice(notice);
	}
	
	
}
