package com.itwillbs.team1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itwillbs.team1.db.JdbcUtil;
import com.itwillbs.team1.vo.NoticeBean;

public class NoticeDAO {
	
	private NoticeDAO() {
		
	}
	private static NoticeDAO instance = new NoticeDAO();
	public static NoticeDAO getInstance() {
		return instance;
	}
	
	private Connection con;
	

	public void setConnection(Connection con) {
		this.con = con;
	}


	public int insertNotice(NoticeBean notice) {
		
		int insertCount = 0;
		
		PreparedStatement pstmt = null, pstmt2 = null;
		ResultSet rs = null;
		
		
		try {
		String sql = "SELECT MAX(notice_idx) FROM notice";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
		
		
		
		sql = "INSERT INTO notice VALUES (null,?,?,?,?,now(),0)";
		pstmt2 = con.prepareStatement(sql);
		
		pstmt2.setString(1, notice.getNotice_subject());
		pstmt2.setString(2, notice.getNotice_content());
		pstmt2.setString(3, notice.getNotice_file());
		pstmt2.setString(4, notice.getNotice_real_file());
		
		insertCount = pstmt2.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt2);
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return insertCount;
	}


	public List<NoticeBean> selectNoticeList(String keyword, int startRow, int listLimit) {
		
		List<NoticeBean> noticeList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		String sql = "SELECT * FROM notice WHERE notice_subject LIKE ? ORDER BY notice_idx DESC "
				+ "LIMIT ?,?";
		pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, "%" + keyword + "%");
		pstmt.setInt(2, startRow);
		pstmt.setInt(3, listLimit);
		System.out.println(pstmt);
		rs = pstmt.executeQuery();
		
		noticeList = new ArrayList<NoticeBean>();
		
		while(rs.next()) {
			NoticeBean notice = new NoticeBean();
			notice.setNotice_idx(rs.getInt("notice_idx"));
			notice.setNotice_subject(rs.getString("notice_subject"));
			notice.setNotice_content(rs.getString("notice_content"));
			notice.setNotice_file(rs.getString("notice_file"));
			notice.setNotice_real_file(rs.getString("notice_real_file"));
			notice.setNotice_date(rs.getTimestamp("notice_date"));
			notice.setNotice_readcount(rs.getInt("notice_readcount"));
			
			noticeList.add(notice);
		}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			
		}
		
		return noticeList;
	}


	public int selectNoticeListCount(String keyword) {

		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(*) FROM notice WHERE notice_subject LIKE ?	";//키워드 들어간 게시물의 제목 갯수 셈
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, "%" + keyword + "%");
			
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) {
				listCount = rs.getInt(1);                   
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			
		}
		
		
		return listCount;
	}


	public NoticeBean selectNotice(int notice_idx) {
		NoticeBean notice = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		String sql = "SELECT *FROM notice WHERE notice_idx = ?";
		pstmt = con.prepareStatement(sql);
		
		pstmt.setInt(1, notice_idx);
		
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			notice = new NoticeBean();
			notice.setNotice_idx(rs.getInt("notice_idx"));
			notice.setNotice_subject(rs.getString("notice_subject"));
			notice.setNotice_content(rs.getString("notice_content"));
			notice.setNotice_file(rs.getString("notice_file"));
			notice.setNotice_real_file(rs.getString("notice_real_file"));
			notice.setNotice_date(rs.getTimestamp("notice_date"));
			notice.setNotice_readcount(rs.getInt("notice_readcount"));
			
		}
		} catch (SQLException e) {
			System.out.println("BoardDAO - selectBoardList()");
			
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			
		}
		return notice;
	}


	public int updateReadCount(int notice_idx) {

		int updateCount = 1;
		
		PreparedStatement pstmt = null;
		
		try {
		String sql = "UPDATE notice SET notice_readcount = notice_readcount+1 "
				+ "WHERE notice_idx=?";
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, notice_idx);
		System.out.println(pstmt);
		updateCount=pstmt.executeUpdate();
	
		} catch (SQLException e) {
		e.printStackTrace();
		
		}finally {
		
		JdbcUtil.close(pstmt);
		

	}
		
		return updateCount;
	}


	public boolean isNoticeWriter(int notice_idx) {
		
		boolean isNoticeWriter = false;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		String sql = "SELECT * FROM notice WHERE notice_idx=?";
		pstmt = con.prepareStatement(sql);
		
		pstmt.setInt(1, notice_idx);
		
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			isNoticeWriter = true;
		}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return isNoticeWriter;
	}


	public int deleteNotice(int notice_idx) {
		
		int deleteCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
		String sql = "DELETE FROM notice WHERE notice_idx=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, notice_idx);
		deleteCount = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
		}
		
		return deleteCount;
	}


	public int updateNotice(NoticeBean notice) {
		
		int modifyCount = 0;
		PreparedStatement pstmt = null;
		
		try {
		String sql = "UPDATE notice SET notice_subject=?, notice_content=?, notice_file=?,notice_real_file=? "
				+ "WHERE notice_idx=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, notice.getNotice_subject());
		pstmt.setString(2, notice.getNotice_content());
		pstmt.setString(3, notice.getNotice_file());
		pstmt.setString(4, notice.getNotice_real_file());
		pstmt.setInt(5, notice.getNotice_idx());
		
		modifyCount = pstmt.executeUpdate();
		
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}finally {
		JdbcUtil.close(pstmt);
	
		}
		
		return modifyCount;
	}
	
	
}
