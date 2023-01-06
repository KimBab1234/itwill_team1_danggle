package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.JdbcUtil;
import vo.CommunityBean;
import vo.Reply;

public class CommunityDAO {
	private CommunityDAO() {}

	private static CommunityDAO instance = new CommunityDAO();

	public static CommunityDAO getInstance() {
		return instance;
	}

	private Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}

	// ------------------------------------- DB작업 -------------------------------------
	// 공통으로 사용할 객체
	PreparedStatement pstmt = null, pstmt2 = null;
	ResultSet rs = null;

	// 글쓰기 작업
	public int writeCommunityBoard(CommunityBean community) {
		int successCount = 0;

		try {
			String sql = "INSERT INTO community VALUES(?,?,?,?,now(),?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, community.getBoard_idx());
			pstmt.setInt(2, community.getBoard_type());
			pstmt.setString(3, community.getBoard_subject());
			pstmt.setString(4, community.getBoard_content());
			pstmt.setString(5, community.getMember_id());
			pstmt.setString(6, community.getBoard_file());
			pstmt.setString(7, community.getBoard_real_file());
			pstmt.setInt(8, 0);

			successCount = pstmt.executeUpdate();

			System.out.println(pstmt);
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 - INSERT");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return successCount;
	}

	// 커뮤니티 글 목록 작업
	public List<CommunityBean> selectCommunityList(int type) {
		List<CommunityBean> communityList = null;

		try {
			String sql = "SELECT * FROM community WHERE board_type=? ORDER BY board_idx ASC";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, type);

			rs = pstmt.executeQuery();

			communityList = new ArrayList<CommunityBean>();

			while(rs.next()) {
				CommunityBean community = new CommunityBean();
				community.setBoard_content(rs.getString("board_content"));
				community.setBoard_subject(rs.getString("board_subject"));
				community.setBoard_idx(rs.getInt("board_idx"));
				community.setBoard_file(rs.getString("board_file"));
				community.setBoard_readCount(rs.getInt("board_readcount"));
				community.setMember_id(rs.getString("member_id"));
				community.setBoard_date(rs.getDate("board_date"));

				communityList.add(community);
			}
		} catch (SQLException e) {
			System.out.println("구문오류 communityList");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return communityList;
	}
	
	// 커뮤니티 상세화면
	public CommunityBean selectDetailList(int idx) {
		CommunityBean community = null;

		try {
			String sql = "SELECT * FROM community WHERE board_idx =? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				community = new CommunityBean();
				community.setBoard_idx(rs.getInt("board_idx"));
				community.setBoard_type(rs.getInt("board_type"));
				community.setBoard_readCount(rs.getInt("board_readcount"));
				community.setMember_id(rs.getString("member_id"));
				community.setBoard_subject(rs.getString("board_subject"));
				community.setBoard_content(rs.getString("board_content"));
				community.setBoard_file(rs.getString("board_file"));
				community.setBoard_date(rs.getDate("board_date"));
			}
			System.out.println(pstmt);
		} catch (SQLException e) {
			System.out.println("구문오류 CommunityDetail");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return community;
	}
	
	// 커뮤니티 댓글 작성
	public int writeReply(Reply reply) {
		int successReply = 0;
		
		try {
			String sql = "INSERT INTO reply VALUES(?,?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reply.getReply_idx());
			pstmt.setInt(2, reply.getBoard_idx());
			pstmt.setInt(3, reply.getBoard_type());
			pstmt.setString(4, reply.getMember_id());
			pstmt.setString(5, reply.getReply_content());
			
			successReply = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("구문오류 WriteReply");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return successReply;
	}
	
	// 커뮤니티 댓글 목록
	public ArrayList<Reply> selectReplyList(int idx) {
		ArrayList<Reply> replyList = null;
		
		try {
			String sql = "SELECT * FROM reply WHERE board_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			
			rs = pstmt.executeQuery();
			
			replyList = new ArrayList<Reply>();
			while(rs.next()) {
				Reply reply = new Reply();
				reply.setReply_idx(rs.getInt("reply_idx"));
				reply.setBoard_idx(rs.getInt("board_idx"));
				reply.setBoard_type(rs.getInt("board_type"));
				reply.setMember_id(rs.getString("member_id"));
				reply.setReply_content(rs.getString("reply_content"));
				reply.setDate(rs.getDate("date"));
				replyList.add(reply);
			}
		} catch (SQLException e) {
			System.out.println("구문오류 ReplyList");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return replyList;
	}


}
