package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.JdbcUtil;
import vo.WishlistBean;

public class WishDAO { // WishDAO
	// ------------ 싱글톤 디자인 패턴을 활용한 WishDAO 인스턴스 생성 작업 -------------
	private WishDAO() {}
	
	private static WishDAO instance = new WishDAO();
	public static WishDAO getInstance() {
		return instance;
	}
	
	private Connection con;
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	// ------------------------------------- DB작업 -------------------------------------
	// 공통으로 사용할 객체
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	// --------------------- 상품 찜 등록(INSERT) -----------------------
	public boolean registWish(String product_idx, String sId) {
		boolean isRegistWish = false;
		int insertCount = 0;
		
		try {
			String sql = "INSERT INTO wish"
						+ " VALUES(?,?,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sId);
			pstmt.setString(2, product_idx);
			insertCount = pstmt.executeUpdate();
			
			if(insertCount > 0) {
				isRegistWish = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return isRegistWish;
	} // 상품 찜 등록 끝	
	// ------------------------------------------------------------------


		
	// -------------- 찜목록 조회 + 상품명 검색(SELECT) -----------------
	public List<WishlistBean> selectWishlist(String sId, String keyword, int startRow, int listLimit) {
		List<WishlistBean> wishlist = null;
		
		try {
			String sql = "SELECT *"
						+ " FROM wishlist"
						+ " WHERE member_id=?"
						+ " 	AND product_name LIKE ?"
						+ " ORDER BY product_name"
						+ " LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sId);
			pstmt.setString(2, "%" + keyword + "%");
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, listLimit);
			rs = pstmt.executeQuery();
			
			wishlist = new ArrayList<WishlistBean>();
			while(rs.next()) {
				WishlistBean wishlistBean = new WishlistBean();
				wishlistBean.setMember_id(sId);
				wishlistBean.setProduct_idx(rs.getString("product_idx"));
				wishlistBean.setWish_date(rs.getDate("wish_date"));
				wishlistBean.setProduct_name(rs.getString("product_name"));
				wishlistBean.setProduct_price(rs.getInt("product_price"));
				wishlistBean.setProduct_discount(rs.getInt("product_price")*(100-rs.getInt("product_discount"))/100);
				wishlistBean.setProduct_real_img(rs.getString("product_real_img"));
				
				wishlist.add(wishlistBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return wishlist;
	} // 찜목록 조회 + 상품명 검색 끝
	// ------------------------------------------------------------------


	
	// ------------- 찜목록 목록 개수 조회(SELECT / COUNT) --------------
	public int wishlistCount(String sId, String keyword) {
		int wishlistCount = 0;
		
		try {
			String sql = "SELECT COUNT(*) "
								+ "FROM wishlist "
								+ "WHERE member_id=?"
								+ " 	AND product_name LIKE ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sId);
			pstmt.setString(2, "%" + keyword + "%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				wishlistCount = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return wishlistCount;
	} // 찜목록 목록 개수 조회 끝
	// ------------------------------------------------------------------

	
	
	// ---------------------- 찜목록 삭제(DELETE) -----------------------
	public boolean deleteWish(String sId, String[] listArr) {
		boolean isdeleteWish = false;
		int deleteCount = 0;

		// 선택취소 시, 삭제할 찜항목이 있을 때
		if(listArr != null) {
			for(int i=0; i < listArr.length; i++) {
				System.out.println("JSP에서 받은 product_idx : "+ listArr[i]);
				try {
					String sql = "DELETE FROM wish"
							+ " WHERE member_id=?"
							+ " 	AND product_idx"
							+ "			LIKE ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, sId);
					pstmt.setString(2, "%" + listArr[i] + "%");
					deleteCount = pstmt.executeUpdate();
					
					if(deleteCount > 0) {
						isdeleteWish = true;
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					JdbcUtil.close(pstmt);
				}
				
			}
			
		// 전체취소 시
		} else {
			try {
				String sql = "DELETE FROM wish"
						+ " WHERE member_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, sId);
				deleteCount = pstmt.executeUpdate();
				
				if(deleteCount > 0) {
					isdeleteWish = true;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JdbcUtil.close(pstmt);
			}
		}
		
		
		return isdeleteWish;
	} // 찜목록 삭제 끝
	// ------------------------------------------------------------------
	
	
} // WishDAO 끝