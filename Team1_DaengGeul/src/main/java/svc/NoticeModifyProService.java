package svc;

import java.sql.Connection;

import dao.NoticeDAO;
import db.JdbcUtil;
import vo.NoticeBean;

public class NoticeModifyProService {

	public boolean isNoticeWriter(NoticeBean notice) {
		
		boolean isNoticeWriter = false;
		
		Connection con = JdbcUtil.getConnection();
		NoticeDAO dao = NoticeDAO.getInstance();
		dao.setConnection(con);
		
		isNoticeWriter = dao.isNoticeWriter(notice.getNotice_idx());
		
		JdbcUtil.close(con);
		
		return isNoticeWriter;
	}

	public boolean modifyNotice(NoticeBean notice) {
		
		boolean isModifySuccess = false;
		
		Connection con=JdbcUtil.getConnection();
		
		NoticeDAO dao = NoticeDAO.getInstance();
		
		dao.setConnection(con);
		
		int modifyCount = dao.updateNotice(notice);
		if(modifyCount>0) {
			JdbcUtil.commit(con);
			isModifySuccess=true;
		}else {
			JdbcUtil.rollback(con);
		}
		JdbcUtil.close(con);
		
		return isModifySuccess;
	}

}
