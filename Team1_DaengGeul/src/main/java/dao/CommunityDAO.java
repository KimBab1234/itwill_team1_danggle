package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.JdbcUtil;
import vo.CommunityBean;
import vo.Like_community;
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
	PreparedStatement pstmt = null, pstmt2 = null, pstmt3 = null;
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
	public List<CommunityBean> selectCommunityList(int type, String keyword, int startRow, int listLimit) {
		List<CommunityBean> communityList = null;

		try {
			String sql = "SELECT * FROM community WHERE board_subject LIKE ? AND board_type=? ORDER BY board_idx DESC LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, type);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, listLimit);
			
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

	// 글삭제
	public int deleteBoard(int board_idx) {
		int successDelete = 0;

		try {
			String sql = "DELETE FROM community WHERE board_idx = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_idx);

			successDelete = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("구문오류 deleteBoard");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return successDelete;
	}

	// 글 목록 갯수 조회
	public int listCount(String keyword, int board_type) {
		int listCount = 0;

		try {
			String sql = " SELECT COUNT(*) FROM community WHERE board_subject LIKE ? AND board_type =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, board_type);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - selectBoardList()");
			e.printStackTrace();
		}  finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return listCount;
	}

	// 댓글 삭제
	public int replyDelete(int board_idx, int reply_idx) {
		int deleteCount = 0;

		try {
			String sql = "DELETE FROM reply WHERE board_idx=? AND reply_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_idx);
			pstmt.setInt(2, reply_idx);

			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 replyDelete");
			e.printStackTrace();
		}
		return deleteCount;
	}
	// 글 수정
	public int successModify(CommunityBean community) {
		int successCount = 0;

		try {
			String sql = "UPDATE community SET board_subject = ? "
					+ " , board_content = ?";
			if(community.getBoard_file() != null) {
				sql  += ", board_file=? , board_real_file = ?"; 
			}
			sql	 += " WHERE board_idx = ?"; 

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, community.getBoard_subject());
			pstmt.setString(2, community.getBoard_content());
			if(community.getBoard_file() != null) {
				pstmt.setString(3, community.getBoard_file());	
				pstmt.setString(4, community.getBoard_real_file());	
				pstmt.setInt(5, community.getBoard_idx());
			} else {
				pstmt.setInt(3, community.getBoard_idx());
			}
			System.out.println(pstmt);
			successCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문오류 - CommunityModify");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return successCount;
	}
	
	// 커뮤니티 추천 갯수
		public int communityLikeCount(int idx) {
			int successCount = 0;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				String sql = "SELECT COUNT(*) FROM like_community WHERE board_idx=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, idx);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					successCount = rs.getInt(1);
				} 
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류! CommunityLikeCount");
				e.printStackTrace();
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
			return successCount;
		}

		// 커뮤니티 추천
		public int communityLike(Like_community like) {
			int successLike = 0;
			int deleteReadCount = 0;
			try {
				String sql = "SELECT * FROM like_community WHERE board_idx =? AND member_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, like.getBoard_idx());
				pstmt.setString(2, like.getMember_id());
				rs = pstmt.executeQuery();

				if(!rs.next()){
					sql = "INSERT INTO like_community VALUES (?,?)";
					pstmt2 = con.prepareStatement(sql);
					pstmt2.setInt(1, like.getBoard_idx());
					pstmt2.setString(2, like.getMember_id());

					successLike = pstmt2.executeUpdate();

					if(successLike > 0) {
						sql = "UPDATE community SET board_readcount= board_readcount-1 WHERE board_idx = ?";
						pstmt3 = con.prepareStatement(sql);
						pstmt3.setInt(1, like.getBoard_idx());
						deleteReadCount = pstmt3.executeUpdate();
					}
				} else {
					successLike = 0;
				}
				System.out.println("successLike갯수 : "+successLike);
			} catch (SQLException e) {
				System.out.println("SQL 구문오류 communityLike");
				e.printStackTrace();
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
				if(pstmt2!=null) {
					JdbcUtil.close(pstmt2);
				}
				if(deleteReadCount > 0) {
					JdbcUtil.close(pstmt3);
				}
			}
			return successLike;
		}

		// 커뮤니티 추천 중복확인
		public boolean duplicateLike(Like_community like) {
			boolean duplicationLike = false;

			try {
				String sql = "SELECT * FROM like_community WHERE board_idx = ? AND member_id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, like.getBoard_idx());
				pstmt.setString(2, like.getMember_id());

				rs = pstmt.executeQuery();

				if(rs.next()) {
					duplicationLike = true;
				}
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 duplicateLike");
				e.printStackTrace();
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
			return duplicationLike;
		}

		// 커뮤니티 추천 취소
		public int communityDeleteLike(Like_community like , int board_idx) {
			int successDelete = 0;
			int deleteCount = 0;
			try {
				String sql = "DELETE FROM like_community WHERE board_idx=? AND member_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, like.getBoard_idx());
				pstmt.setString(2, like.getMember_id());
				successDelete = pstmt.executeUpdate();
				if(successDelete > 0) {
					sql = "UPDATE community SET board_readcount = board_readcount-1 WHERE board_idx=?";
					pstmt2 = con.prepareStatement(sql);
					pstmt2.setInt(1, board_idx);
					deleteCount = pstmt2.executeUpdate();
				}
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 communityDeleteLike");
				e.printStackTrace();
			} finally {
				JdbcUtil.close(pstmt);
				if(deleteCount > 0) {
					JdbcUtil.close(pstmt2);
				}
			}
			return successDelete;
		}
		
}




















