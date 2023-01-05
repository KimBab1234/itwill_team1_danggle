package svc;

import java.sql.Connection;
import java.util.List;

import dao.NoticeDAO;
import db.JdbcUtil;
import vo.NoticeBean;

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
