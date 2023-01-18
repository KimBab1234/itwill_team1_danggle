package com.itwillbs.team1.svc;

import java.sql.Connection;
import java.util.List;

import com.itwillbs.team1.dao.NoticeDAO;
import com.itwillbs.team1.db.JdbcUtil;
import com.itwillbs.team1.vo.NoticeBean;

public class NoticeListService {

	public List<NoticeBean> getNoticeList(String keyword, int startRow, int listLimit) {
		List<NoticeBean> noticeList = null;
		
		Connection con=JdbcUtil.getConnection();
		
		NoticeDAO dao = NoticeDAO.getInstance();
		
		dao.setConnection(con);
		noticeList=  dao.selectNoticeList(keyword,startRow,listLimit);
		
		JdbcUtil.close(con);
		
		
		return noticeList;
	}

	public int getNoticeListCount(String keyword) {
		
		int listCount = 0;
		Connection con=JdbcUtil.getConnection();
		
		NoticeDAO dao = NoticeDAO.getInstance();
		
		dao.setConnection(con);
		
		
		listCount =  dao.selectNoticeListCount(keyword);
		
		JdbcUtil.close(con);
		
		return listCount;
	}

}
