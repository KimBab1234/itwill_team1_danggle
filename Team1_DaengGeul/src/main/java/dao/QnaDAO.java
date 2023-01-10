package dao;

import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import db.JdbcUtil;
import vo.QnaBean;

public class QnaDAO {
private QnaDAO() {
		
	}
	private static QnaDAO instance = new QnaDAO();
	public static QnaDAO getInstance() {
		return instance;
	}
	
	private Connection con;
	

	public void setConnection(Connection con) {
		this.con = con;
	}


	public int insertQna(QnaBean qna, String sId) {
		
		int insertCount = 0;
		
		PreparedStatement pstmt = null, pstmt2 = null;
		ResultSet rs = null;
		try {
		int qna_idx = 1;
		
		String sql = "SELECT MAX(qna_idx) FROM qna";//글 번호 중 가장 큰 거 뽑아
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {//조회결과가 있다면?
			qna_idx = rs.getInt(1) +1;//기존에 있는 게시물 번호 중 가장 큰 것에+1해서 새 게시물 번호로 줌
		}
		
		sql = "INSERT INTO qna VALUES (?,?,?,?,?,?,now(),?,?,?)";
		
		pstmt2 = con.prepareStatement(sql);
		pstmt2.setInt(1, qna_idx);
		pstmt2.setString(2, sId);
		pstmt2.setString(3, qna.getQna_subject());
		pstmt2.setString(4, qna.getQna_content());
		pstmt2.setString(5, qna.getQna_file());
		pstmt2.setString(6, qna.getQna_original_file());
		pstmt2.setInt(7, qna_idx);
		pstmt2.setInt(8, 0);
		pstmt2.setInt(9, 0);
		
		
		insertCount= pstmt2.executeUpdate();
		System.out.println("pstmt" + pstmt);
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(pstmt2);
		}
		
		return insertCount;
	}


	public List<QnaBean> selectQnaList(String sId,String keyword, int startRow, int listLimit) {
		
	    List<QnaBean> qnaList = null;
		
	    PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		
		String sql = "SELECT * FROM qna WHERE qna_re_ref IN (SELECT qna_idx FROM qna WHERE member_id=?) AND qna_subject LIKE ? ORDER BY "
				+ "qna_re_ref DESC, qna_re_seq ASC LIMIT ?,?";
		

		if(sId.equals("admin")) {
			sql = "SELECT * FROM qna WHERE qna_subject LIKE ? ORDER BY qna_re_ref DESC, qna_re_seq ASC LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, listLimit);
		} else {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, sId);
			pstmt.setString(2, "%" + keyword + "%");
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, listLimit);
		}
		
	    //조건문으로 sql문을 두개 써서 관리자일 경우에는 member_id 파라미터가 필요 없다 그냥 다 보여줄거니까
		
		
	
		
		rs = pstmt.executeQuery();
		
		qnaList = new ArrayList<QnaBean>();
		
		while(rs.next()) {
			QnaBean qna = new QnaBean();
			qna.setQna_content(rs.getString("qna_content"));
			qna.setQna_idx(rs.getInt("qna_idx"));
			qna.setQna_subject(rs.getString("qna_subject"));
			qna.setQna_file(rs.getString("qna_file"));
			qna.setQna_original_file(rs.getString("qna_original_file"));
			qna.setMember_id(rs.getString("member_id"));
			qna.setQna_re_ref(rs.getInt("qna_re_ref"));
			qna.setQna_re_lev(rs.getInt("qna_re_lev"));
			qna.setQna_re_seq(rs.getInt("qna_re_seq"));
			qna.setQna_date(rs.getTimestamp("qna_date"));
			
			qnaList.add(qna);
			
		}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return qnaList;
	}


	public int selectQnaListCount(String keyword) {
		
		int listCount = 0;
		
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
			
		 try {
		 String sql = "SELECT COUNT(*) FROM qna WHERE qna_subject LIKE ?";
		 				
		 pstmt = con.prepareStatement(sql);
		 System.out.println(pstmt);
		 pstmt.setString(1, "%" + keyword + "%");
		 
		 rs = pstmt.executeQuery();
		 
		 if(rs.next()) {
				listCount = rs.getInt(1);
		 }
		 } catch (SQLException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }finally {
			 JdbcUtil.close(rs);
			 JdbcUtil.close(pstmt);
		 }
		 
		return listCount;
	}


	public QnaBean selectQna(int qna_idx) {
		
		QnaBean qna = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		String sql = "SELECT *FROM qna WHERE qna_idx = ?";
		pstmt = con.prepareStatement(sql);
		
		pstmt.setInt(1, qna_idx);
		
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			qna = new QnaBean();
			qna.setQna_idx(rs.getInt("qna_idx"));
			qna.setMember_id(rs.getString("member_id"));
			qna.setQna_subject(rs.getString("qna_subject"));
			qna.setQna_content(rs.getString("qna_content"));
			qna.setQna_file(rs.getString("qna_file"));
			qna.setQna_original_file(rs.getString("qna_file"));
			qna.setQna_date(rs.getTimestamp("qna_date"));
			qna.setQna_re_ref(rs.getInt("qna_re_ref"));
			qna.setQna_re_lev(rs.getInt("qna_re_lev"));
			qna.setQna_re_seq(rs.getInt("qna_re_seq"));
			           
		
		}
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally {
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
		
	}
		
		return qna;
	}


	public boolean isQnaWriter(int qna_idx, String sId) {
		boolean isQnaWriter = false;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		String sql = "SELECT * FROM qna WHERE qna_idx = ? AND member_id = ?";
		
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, qna_idx);
		pstmt.setString(2, sId);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			isQnaWriter = true;
		}
		} catch (SQLException e) {
			System.out.println("BoardDAO - isBoardWriter()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return isQnaWriter;
	}


	public int deleteQna(int qna_idx) {
		
		int deleteCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
		String sql = "DELETE FROM qna WHERE qna_idx=?";
		
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, qna_idx);
		deleteCount = pstmt.executeUpdate();
		System.out.println(pstmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
		}
		return deleteCount;
	}


	public int updateQna(QnaBean qna) {

		int modifyCount = 0;
		PreparedStatement pstmt = null;
		
		try {
		String sql = "UPDATE qna SET qna_subject=?,qna_content=?,qna_file=?,qna_original_file=? WHERE qna_idx=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, qna.getQna_subject());
		pstmt.setString(2, qna.getQna_content());
		pstmt.setString(3, qna.getQna_file());
		pstmt.setString(4, qna.getQna_original_file());
		pstmt.setInt(5, qna.getQna_idx());
		
		modifyCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
		
	}
		return modifyCount;
	}


	public int insertReplyQna(QnaBean qna) {
		int insertCount = 0;
		PreparedStatement pstmt = null, 
				pstmt2 = null;
		ResultSet rs = null;
		
		try {
		int qna_idx = 1;
		
		String sql = "SELECT MAX(qna_idx) FROM qna";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			qna_idx = rs.getInt(1) + 1;
		}
		
		int ref = qna.getQna_re_ref();//원본글의 참조글 번호
		int lev = qna.getQna_re_lev();//원본글의 들여쓰기 레벨
		int seq = qna.getQna_re_seq();//원본글의 순서번호
			System.out.println("ref 확인 : " + ref + lev + seq);
			//기존 답글들에 대한 순서번호 증가
			//원본글의 참조글 번호와 같고 순서번호보다 큰 레코드들의 순서번호를 +1 씩 증가
			sql = "UPDATE qna SET qna_re_seq=qna_re_seq + 1 WHERE qna_re_ref=? AND qna_re_seq>?";
			
			pstmt2=con.prepareStatement(sql);
			pstmt2.setInt(1,ref);
			pstmt2.setInt(2,seq);
			pstmt2.executeUpdate();
			
			JdbcUtil.close(pstmt2);
			
			lev++;
			seq++;
			
			sql = "INSERT INTO qna VALUES (?,?,?,?,?,?,now(),?,?,?)";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setInt(1, qna_idx);
			pstmt2.setString(2, qna.getMember_id());
			pstmt2.setString(3, qna.getQna_subject());
			pstmt2.setString(4, qna.getQna_content());
			pstmt2.setString(5, qna.getQna_file());
			pstmt2.setString(6, qna.getQna_original_file());
			pstmt2.setInt(7, ref);
			pstmt2.setInt(8, lev);
			pstmt2.setInt(9, seq);
			
			insertCount = pstmt2.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			
		}
		return insertCount;
	}

}
