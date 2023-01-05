package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.JdbcUtil;
import vo.CommonBean;

public class CommonDAO {
private CommonDAO() {
		
	}
	private static CommonDAO instance = new CommonDAO();
	public static CommonDAO getInstance() {
		return instance;
	}
	
	private Connection con;
	

	public void setConnection(Connection con) {
		this.con = con;
	}


	public int insertCommon(CommonBean common, String sId) {
		
		int insertCount = 0;
		
		PreparedStatement pstmt = null, pstmt2 = null;
		ResultSet rs = null;
		try {
		int common_idx = 1;
			
		String sql = "SELECT MAX(common_idx) FROM common";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {//조회결과가 있다면?
			common_idx = rs.getInt(1) +1;//기존에 있는 게시물 번호 중 가장 큰 것에+1해서 새 게시물 번호로 줌
		}
		
			
		sql = "INSERT INTO common VALUES (?,?,?)";
			
		pstmt2 = con.prepareStatement(sql);
			
		pstmt2.setInt(1, common_idx);
		pstmt2.setString(2, common.getCommon_subject());
		pstmt2.setString(3, common.getCommon_content());
			
		insertCount = pstmt2.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(pstmt2);
			
		}
		
		return insertCount;
	}


	public List<CommonBean> selectList(CommonBean common) {
		
		List<CommonBean> commonList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM common";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			commonList = new ArrayList<CommonBean>();
			
			while(rs.next()) {
				common = new CommonBean();
				common.setCommon_idx(rs.getInt("common_idx"));
				common.setCommon_subject(rs.getString("common_subject"));
				common.setCommon_content(rs.getString("common_content"));
				
				commonList.add(common);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return commonList;
	}


	public CommonBean selectCommon(int common_idx) {
		
		CommonBean common = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT *FROM common WHERE common_idx = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, common_idx);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				common = new CommonBean();
				common.setCommon_idx(rs.getInt("common_idx"));
				common.setCommon_subject(rs.getString("common_subject"));
				common.setCommon_content(rs.getString("common_content"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return common;
	}


	public boolean isCommonWriter(int common_idx, String sId) {

		boolean isCommonWriter = false;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM common WHERE common_idx = ? ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, common_idx);
			
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				isCommonWriter = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return isCommonWriter;
	}


	public int deleteCommon(int common_idx) {
		
		int deleteCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "DELETE FROM common WHERE common_idx = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, common_idx);
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
		}
		
		
		return deleteCount;
	}


	public int updateCommon(CommonBean common) {
		
		int modifyCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE common SET common_subject=?, common_content=? WHERE common_idx = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, common.getCommon_subject());
			pstmt.setString(2, common.getCommon_content());
			pstmt.setInt(3, common.getCommon_idx());
			
			modifyCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return modifyCount;
	}

}
