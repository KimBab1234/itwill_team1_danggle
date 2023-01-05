package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.JdbcUtil;
import vo.AuthBean;
import vo.MemberBean;

public class MemberDAO { // MemberDAO
	// ------------ 싱글톤 디자인 패턴을 활용한 MemberDAO 인스턴스 생성 작업 -------------
	private MemberDAO() {}
	
	private static MemberDAO instance = new MemberDAO();
	public static MemberDAO getInstance() {
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
	
	
	// ---------------------- 회원가입(INSERT) ------------------------------
	public int insertMember(MemberBean member) {
		int insertCount = 0;
		
		try {
			String sql = "INSERT INTO member"
					+ " VALUES(?,?,?,?,?,?,?,?,?,now(),?,?,?,'Y')";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getMember_id());
			pstmt.setString(2, member.getMember_passwd());
			pstmt.setString(3, member.getMember_name());
			pstmt.setString(4, member.getMember_phone());
			pstmt.setString(5, member.getMember_email());
			pstmt.setString(6, member.getMember_postcode());
			pstmt.setString(7, member.getMember_roadAddress());
			pstmt.setString(8, member.getMember_jibunAddress());
			pstmt.setString(9, member.getMember_addressDetail());
			pstmt.setString(10, member.getMember_gender());
			pstmt.setInt(11, member.getMember_point());
			pstmt.setString(12, member.getMember_coupon());
			
			insertCount = pstmt.executeUpdate();
			
			sql = "DELETE FROM auth"
					+ " 	WHERE auth_id=?";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setString(1, member.getMember_id());
			pstmt2.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt2);
			JdbcUtil.close(pstmt);
		}
		
		return insertCount;
	} // 회원가입 끝
	// ----------------------------------------------------------------------

	

	// -------------------------- 로그인(SELECT) ----------------------------
	public boolean loginMember(String id, String passwd) {
		boolean loginSuccess = false;
		
		try {
			String sql = "SELECT member_id"
					+ " FROM member"
					+ " WHERE member_id=?"
					+ "		 AND member_passwd=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				loginSuccess = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return loginSuccess;
	} // 로그인 끝
	// ----------------------------------------------------------------------

	

	// ------------------------ 회원정보(SELECT) ----------------------------
	public MemberBean getMemberInfo(String id) {
		MemberBean member = null;
		
		try {
			String sql = "SELECT *"
					+ " FROM member"
					+ " WHERE member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member = new MemberBean();
				member.setMember_id(rs.getString("member_id"));
				member.setMember_passwd(rs.getString("member_passwd"));
				member.setMember_name(rs.getString("member_name"));
				member.setMember_phone(rs.getString("member_phone"));
				member.setMember_email(rs.getString("member_email"));
				member.setMember_postcode(rs.getString("member_postcode"));
				member.setMember_roadAddress(rs.getString("member_roadAddress"));
				member.setMember_jibunAddress(rs.getString("member_jibunAddress"));
				member.setMember_addressDetail(rs.getString("member_addressDetail"));
				member.setMember_join_date(rs.getDate("member_join_date"));
				member.setMember_gender(rs.getString("member_gender"));
				member.setMember_point(rs.getInt("member_point"));
				member.setMember_coupon(rs.getString("member_coupon"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return member;
	} // 회원정보 끝
	// ----------------------------------------------------------------------

	

	// ------------ 관리자 회원목록 조회 + 회원이름 검색(SELECT) ------------
	public List<MemberBean> selectMemberList(String keyword, int startRow, int listLimit) {
		List<MemberBean> memberList = null;
		
		try {
			String sql = "SELECT * FROM member"
							+ " WHERE member_name"
							+ " LIKE ? "
							+ " ORDER BY member_name"
							+ " LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, listLimit);
			rs = pstmt.executeQuery();
			
			memberList = new ArrayList<MemberBean>();
			
			while(rs.next()) {
				MemberBean member = new MemberBean();
				member.setMember_id(rs.getString("member_id"));
				member.setMember_name(rs.getString("member_name"));
				member.setMember_email(rs.getString("member_email"));
				member.setMember_gender(rs.getString("member_gender"));
				member.setMember_postcode(rs.getString("member_postcode"));
				member.setMember_join_date(rs.getDate("member_join_date"));
				
				memberList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return memberList;
	} // 관리자 회원목록 조회 + 회원이름 검색 끝
	// ----------------------------------------------------------------------
	
	
	
	// ------------ 관리자 회원목록 목록 개수 조회(SELECT / COUNT) ----------
	public int selectMemberListCount(String keyword) {
		int listCount = 0;
		
		try {
			String sql = "SELECT COUNT(*) "
								+ "FROM member "
								+ "WHERE member_name LIKE ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return listCount;
	} // 관리자 회원목록 목록 개수 조회 끝
	// ----------------------------------------------------------------------

	

	// ------------ 회원수정/탈퇴 시 아이디/비밀번호 확인(SELECT) -----------
	public boolean isRightUser(String id, String passwd) {
		boolean isRightUser = false;
		
		try {
			String sql = "SELECT *"
					+ " FROM member"
					+ " WHERE member_id=?"
					+ 	" AND member_passwd=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				isRightUser = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

		return isRightUser;
	} // 회원수정/탈퇴 시 아이디/비밀번호 확인 끝
	// ----------------------------------------------------------------------


	
	// ---------------------- 회원탈퇴(DELETE) ------------------------------
	public int deleteMember(String id) {
		int deleteCount = 0;
		
		// 외래키 설정이 되어있기 때문에 자식테이블 데이터를 먼저 삭제해야 함
		try {
			String sql ="DELETE FROM wish"
					+ "  WHERE member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			
			sql = "DELETE FROM member"
					+		 " WHERE member_id=?";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setString(1, id);

			deleteCount = pstmt2.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			JdbcUtil.close(pstmt2);
			JdbcUtil.close(pstmt);
		}
		
		return deleteCount;
	} // 회원탈퇴 끝
	// ----------------------------------------------------------------------

	

	// ------------- 회원정보 수정 및 비밀번호 변경(UPDATE) -----------------
	public int updateMember(MemberBean member, String Passwd) {
		int updateCount = 0;
		
		try {
			String sql = "UPDATE member"
					+ 	" SET"
					+ 		" member_passwd=?"
					+ 		" ,member_name=?"
					+ 		" ,member_email=?"
					+ 		" ,member_postcode=?"
					+ 		" ,member_roadAddress=?"
					+ 		" ,member_jibunAddress=?"
					+ 		" ,member_addressDetail=?"
					+ 		" ,member_phone=?"
					+ 		" WHERE member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, Passwd);
			pstmt.setString(2, member.getMember_name());
			pstmt.setString(3, member.getMember_email());
			pstmt.setString(4, member.getMember_postcode());
			pstmt.setString(5, member.getMember_roadAddress());
			pstmt.setString(6, member.getMember_jibunAddress());
			pstmt.setString(7, member.getMember_addressDetail());
			pstmt.setString(8, member.getMember_phone());
			pstmt.setString(9, member.getMember_id());
			
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			JdbcUtil.close(pstmt);
		}
		
		return updateCount;
	} // 회원정보 수정 및 비밀번호 변경 끝
	// ----------------------------------------------------------------------

	
	
	// ----------------- 회원가입 아이디 중복확인(SELECT) -------------------
	public boolean selectMemberId(String id) {
		boolean isExist = false;
		
		try {
			String sql = "SELECT member_id FROM member WHERE member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				isExist = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return isExist;
	} // 회원가입 아이디 중복확인 끝
	// ----------------------------------------------------------------------
	
	
	
	// ----------------- 회원가입 이메일 중복확인(SELECT) -------------------
	public boolean selectMemberEmail(String email) {
		boolean isExistEmail = false;
		
		try {
			String sql = "SELECT * FROM member"
					+ " 	WHERE member_email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				isExistEmail = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return isExistEmail;
	}
	// ----------------------------------------------------------------------
	
	
	
	// --------------------- 회원 아이디 찾기(SELECT) -----------------------
	public String searchId(String email) {
		String findId = null;

		try {
			String sql = "SELECT member_id FROM member WHERE member_email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				findId = rs.getString("member_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return findId;
	} // 회원 아이디 찾기 끝	
	// ----------------------------------------------------------------------



	// ---------------- 인증메일 전송 전 회원 검색(SELECT) ------------------
	public String[] SearchNE(String id) {
		String[] findInfo = null;
		
		try {
			String sql = "SELECT member_name, member_email"
					+ " 	FROM member"
					+ " 	WHERE member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				findInfo = new String[] {
					rs.getString("member_name"), rs.getString("member_email")
				};
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		
		return findInfo;
	} // 인증메일 전송 전 회원 검색 끝
	// ----------------------------------------------------------------------

	

	// ---------------- 인증코드 등록(SELECT/INSERT/UPDATE) -----------------
	public int registAuth(AuthBean auth) {
		int registCount = 0;
		
		// 인증코드 존재 유무 확인
		try {
			String sql = "SELECT *"
					+ " 	FROM auth"
					+ " 	WHERE auth_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, auth.getAuth_id());
			rs = pstmt.executeQuery();
			
			// 인증코드 발급 갱신
			if(rs.next()) {
				sql = "UPDATE auth"
						+ " 	SET authCode=?"
						+ " 	WHERE auth_id=?";
				pstmt2 = con.prepareStatement(sql);
				pstmt2.setString(1, auth.getAuthCode());
				pstmt2.setString(2, auth.getAuth_id());
				registCount = pstmt2.executeUpdate();
			
			// 인증코드 발급 신규
			} else {
				sql = "INSERT INTO auth VALUES(?,?)";
				pstmt2 = con.prepareStatement(sql);
				pstmt2.setString(1, auth.getAuth_id());
				pstmt2.setString(2, auth.getAuthCode());
				registCount = pstmt2.executeUpdate();
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt2);
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return registCount;
		
	} // 인증코드 등록 끝
	// ----------------------------------------------------------------------



	// ---------------------- 인증코드 조회(SELECT) -------------------------
	public String selectAuthCode(String id) {
		String selectedCertNum = null;
		
		try {
			String sql = "SELECT authCode"
					+ " 	FROM auth"
					+ " 	WHERE auth_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				selectedCertNum = rs.getString("authCode");				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return selectedCertNum;
	} // 인증코드 조회 끝
	// ----------------------------------------------------------------------



	// -------------------- 임시 비밀번호 발급(UPDATE) ----------------------
	public void UpdateTempPasswd(String id, String tempPasswd) {
		
		try {
			String sql = "UPDATE member"
					+ " 	SET member_passwd=?"
					+ " 	WHERE member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, tempPasswd);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
			
			sql = "DELETE FROM auth"
					+ " 	WHERE auth_id=?";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setString(1, id);
			pstmt2.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt2);
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	} // 임시 비밀번호 발급 끝
	// ----------------------------------------------------------------------


	
} // MemberDAO 끝


