package svc;

import java.sql.Connection;

import dao.NoticeDAO;
import db.JdbcUtil;

public class NoticeDeleteProService {

	public boolean isNoticeWriter(int notice_idx) {
		
		boolean isNoticeWriter = false;
		
		Connection con=JdbcUtil.getConnection();
		
		NoticeDAO dao = NoticeDAO.getInstance();
		
		dao.setConnection(con);
		
		isNoticeWriter = dao.isNoticeWriter(notice_idx);
		
		JdbcUtil.close(con);
		
		return isNoticeWriter;
	}

	public boolean removeNotice(int notice_idx) {
		
		boolean isDeleteSuccess = false;
		
		Connection con=JdbcUtil.getConnection();
		
		NoticeDAO dao = NoticeDAO.getInstance();
		
		dao.setConnection(con);
		
		int deleteCount = dao.deleteNotice(notice_idx);
		
		if(deleteCount > 0) {
			JdbcUtil.commit(con);
			isDeleteSuccess = true;
		} else {
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		
		return isDeleteSuccess;
	}


}
