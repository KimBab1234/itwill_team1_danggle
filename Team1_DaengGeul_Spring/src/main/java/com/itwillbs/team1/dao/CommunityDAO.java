package com.itwillbs.team1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itwillbs.team1.db.JdbcUtil;
import com.itwillbs.team1.vo.CommunityBean;
import com.itwillbs.team1.vo.Like_community;
import com.itwillbs.team1.vo.Like_reply;
import com.itwillbs.team1.vo.Reply;

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

	// ------------------------------------------------ 중요 ------------------------------------------------------
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
				community.setBoard_readcount(rs.getInt("board_readcount"));
				community.setMember_id(rs.getString("member_id"));
				community.setBoard_date(rs.getDate("board_date"));

				communityList.add(community);
			}

			// -------------------------------- 중요!! ----------------------------------------
			// 리스트에 추천 갯수 뽑는 부분
			for(int i = 0; i < communityList.size(); i++) {
				sql = "SELECT count(*) FROM like_community WHERE board_idx = ?";
				pstmt2 = con.prepareStatement(sql);
				pstmt2.setInt(1, communityList.get(i).getBoard_idx());

				rs = pstmt2.executeQuery();
				if(rs.next()) {
					communityList.get(i).setBoard_likecount(rs.getInt(1));
				}
			}

			// 리스트에 댓글 갯수 뽑는 부분
			for(CommunityBean community :  communityList) {
				sql = "SELECT count(*) FROM reply WHERE board_idx = ?";
				pstmt3 = con.prepareStatement(sql);
				pstmt3.setInt(1, community.getBoard_idx());

				rs = pstmt3.executeQuery();
				if(rs.next()) {
					community.setBoard_replycount(rs.getInt(1));
				}
			}

			//			 향상된 for 문도 가능함
			//			 for(CommunityBean community :  communityList) {
			//				 sql = "SELECT count(*) FROM like_community WHERE board_idx = ?";
			//				 pstmt2 = con.prepareStatement(sql);
			//				 pstmt2.setInt(1, community.getBoard_idx());
			//				 
			//				 rs = pstmt2.executeQuery();
			//				 if(rs.next()) {
			//					 community.setBoard_likecount(rs.getInt(1));
			//				 }
			//			 }

		} catch (SQLException e) {
			System.out.println("구문오류 communityList");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(pstmt2);
			JdbcUtil.close(pstmt3);
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
				community.setBoard_readcount(rs.getInt("board_readcount"));
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
	public ArrayList<Reply> selectReplyList(int idx, String id) {
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

			for(Reply reply : replyList) {
				sql = "SELECT COUNT(*) FROM like_reply WHERE reply_idx = ?";
				pstmt2 = con.prepareStatement(sql);
				pstmt2.setInt(1, reply.getReply_idx());

				rs = pstmt2.executeQuery();

				if(rs.next()) {
					reply.setReply_likecount(rs.getInt(1));
				}
			}

			for(Reply reply : replyList) {
				sql = "SELECT * FROM like_reply WHERE reply_idx = ? AND member_id = ? AND board_idx=?";
				pstmt3 = con.prepareStatement(sql);
				pstmt3.setInt(1,reply.getReply_idx());
				pstmt3.setString(2, id);
				pstmt3.setInt(3, reply.getBoard_idx());
				rs = pstmt3.executeQuery();

				if(rs.next()) {
					reply.setReply_likeduplicate(1);
				} else {
					reply.setReply_likeduplicate(0);
				}
			}
			
		} catch (SQLException e) {
			System.out.println("구문오류 ReplyList");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(pstmt2);
			JdbcUtil.close(pstmt3);
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

	// 조회수 증가
	public int updateReadcount(int idx) {
		int updateCount = 0;

		try {
			// 글번호가 일치하는 레코드의 조회수(readcount) 1만큼 증가
			String sql = "UPDATE community SET board_readcount = board_readcount+1 WHERE board_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("구문오류 board_readCount");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(pstmt);
		}
		return updateCount;
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

	// 댓글 좋아요
	public int replyLike(Like_reply like) {
		int successLike = 0;
		int deleteReadCount = 0;

		try {
			String sql = "SELECT * FROM like_reply WHERE board_idx =? AND member_id=? AND reply_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, like.getBoard_idx());
			pstmt.setString(2, like.getMember_id());
			pstmt.setInt(3, like.getReply_idx());
			rs = pstmt.executeQuery();
			System.out.println(pstmt);

			if(!rs.next()){
				sql = "INSERT INTO like_reply VALUES (?,?,?)";
				pstmt2 = con.prepareStatement(sql);
				pstmt2.setInt(1, like.getReply_idx());
				pstmt2.setString(2, like.getMember_id());
				pstmt2.setInt(3, like.getBoard_idx());
				System.out.println(pstmt2);
				successLike = pstmt2.executeUpdate();

				if(successLike > 0) {
					sql = "UPDATE community SET board_readcount= board_readcount-1 WHERE board_idx = ?";
					pstmt3 = con.prepareStatement(sql);
					pstmt3.setInt(1, like.getBoard_idx());
					System.out.println(pstmt3);
					deleteReadCount = pstmt3.executeUpdate();
				} 
			} else {
				successLike = 0;
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 like_reply");
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
	
	// 추천취소
	public int replyLikeDelete(Like_reply like) {
		int successDelete = 0;
		
		try {
			String sql = "DELETE FROM like_reply WHERE reply_idx = ? AND member_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, like.getReply_idx());
			pstmt.setString(2, like.getMember_id());
			
			successDelete = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 replyLikeDelete");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return successDelete;
	}



}




















