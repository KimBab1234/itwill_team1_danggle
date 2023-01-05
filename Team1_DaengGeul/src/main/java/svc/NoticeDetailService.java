package svc;

import java.sql.Connection;

import dao.NoticeDAO;
import db.JdbcUtil;
import vo.NoticeBean;

public class NoticeDetailService {

	public NoticeBean getNotice(int notice_idx, boolean isUpdateReadcount) {
		NoticeBean notice = null;
		
		Connection con=JdbcUtil.getConnection();
		
		NoticeDAO dao = NoticeDAO.getInstance();
		
		dao.setConnection(con);
		
		notice = dao.selectNotice(notice_idx);
		
		if(notice != null) {
			int updateCount = dao.updateReadCount(notice_idx);
			
			if(updateCount>0) {
				JdbcUtil.commit(con);
				
				notice.setNotice_readcount(notice.getNotice_readcount()+1);
			}
		}
		
		JdbcUtil.close(con);
		
		return notice;
	}

}
