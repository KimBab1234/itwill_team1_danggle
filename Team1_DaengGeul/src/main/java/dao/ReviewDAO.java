package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.JdbcUtil;
import vo.ReviewBean;

public class ReviewDAO {
	
	// 싱글톤 디자인 패턴을 활용한 ReviewDAO 인스턴스 생성 작업
	private ReviewDAO() {}
	
	private static ReviewDAO instance = new ReviewDAO();

	public static ReviewDAO getInstance() {
		return instance;
	}
	// -----------------------------------------------------------------
	// 데이터베이스 접근에 사용할 Connection 객체
	private Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}
	//-------------------------------------------------------------------
	// 리뷰 쓰기
	public int insertReview(ReviewBean review) {
		
		int insertCount = 0;
		
		PreparedStatement pstmt = null, pstmt2 = null;
		ResultSet rs = null;
		
		try {
			int review_idx = 1; // 새 글 번호
			
			String sql = "SELECT MAX(review_idx) FROM review";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				review_idx = rs.getInt(1) + 1; // 기존 게시물 번호 중 가장 큰 번호(= 조회 결과) + 1
			}
			System.out.println("새 글 번호 : " + review_idx);
			JdbcUtil.close(rs);
			sql = "INSERT INTO review VALUES(?,?,?,?,?,?,?,?,now(),?,0)";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setInt(1, review_idx);
			pstmt2.setString(2, review.getProduct_idx()); // review.getProduct_id()
			pstmt2.setString(3, review.getMember_id());
			pstmt2.setString(4, review.getReview_subject());
			pstmt2.setString(5, review.getReview_passwd());
			pstmt2.setInt(6, review.getReview_score());
			pstmt2.setString(7, review.getReview_content());
			pstmt2.setInt(8, review.getReview_readcount());
			pstmt2.setString(9, review.getOrder_idx());
			
			insertCount = pstmt2.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 - insertReview()");
			e.printStackTrace();
		} finally {
			
			JdbcUtil.close(pstmt);
			JdbcUtil.close(pstmt2);
		}
		return insertCount; 
	}
	
	// 리뷰 목록 
	public List<ReviewBean> selectReviewList(String keyword, String id, String product_idx,int startRow, int listLimit) {
		
		List<ReviewBean> reviewList = null;
		
		ReviewBean review = null;
	
		PreparedStatement pstmt = null, pstmt2 = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM review WHERE review_subject LIKE ? AND member_id=? LIMIT ?,?";
			if(product_idx!=null) { //상품별 리뷰목록
				sql = "SELECT * FROM review WHERE review_subject LIKE ? AND product_idx=? LIMIT ?,?";
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			if(product_idx!=null) { //상품별 리뷰목록
				pstmt.setString(2, product_idx);
			} else {
				pstmt.setString(2, id);
			}
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, listLimit);
			rs = pstmt.executeQuery();
			
			reviewList = new ArrayList<ReviewBean>();
			
			while(rs.next()) {
				review = new ReviewBean();
				
				review.setReview_idx(rs.getInt("review_idx"));
				review.setProduct_idx(rs.getString("product_idx"));
				review.setMember_id(rs.getString("member_id"));
				review.setReview_subject(rs.getString("review_subject"));
				review.setReview_score(rs.getInt("review_score"));
				review.setReview_content(rs.getString("review_content"));
				review.setReview_readcount(rs.getInt("review_readcount"));
				review.setReview_date(rs.getDate("review_date"));
				review.setReview_like_count(rs.getInt("review_like_count"));
				
				reviewList.add(review);
			}
			
	
				
			
		} catch (SQLException e) {
			System.out.println("ReviewDAO - selectReviewList()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return reviewList;
		
	}
	
	// 리뷰 리스트 수
	public int selectReviewListCount(String keyword, String id, String product_idx) {
		
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(*) FROM review WHERE review_subject LIKE ? AND member_id=?";
			
			if(product_idx!=null) { //상품별 리뷰목록
				sql = "SELECT COUNT(*) FROM review WHERE review_subject LIKE ? AND product_idx=?";
			}
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");

			if(product_idx!=null) { //상품별 리뷰목록
				pstmt.setString(2, product_idx);
			} else {
				pstmt.setString(2, id);
			}
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("ReviewDAO - selectReviewListCount()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return listCount;
		
	}
	
	//리뷰 조회
	public ReviewBean selectReview(int review_idx, String sId) {
		
		ReviewBean review = null;
		
		PreparedStatement pstmt = null, pstmt2 = null;
		ResultSet rs =null;
		
		try {
			String sql = "SELECT * FROM review WHERE review_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, review_idx);
			
			rs = pstmt.executeQuery(); 
//			System.out.println("pstmt:" + pstmt);
			if(rs.next()) { 
				review = new ReviewBean(); 
				
				review.setReview_idx(rs.getInt("review_idx"));
				review.setProduct_idx(rs.getString("product_idx"));
				review.setMember_id(rs.getString("member_id"));
				review.setReview_subject(rs.getString("review_subject"));
				review.setReview_score(rs.getInt("review_score"));
				review.setReview_content(rs.getString("review_content"));
				review.setReview_readcount(rs.getInt("review_readcount"));
				review.setReview_date(rs.getDate("review_date"));
				review.setReview_like_count(rs.getInt("review_like_count"));
				
//				System.out.println(review);
				
			}
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			
			/*
			 * 2. 리뷰조회 끝난뒤에 review_like에 id, idx 조회해서
				sql = "SELECT * FROM review_like WHERE review_idx=? AND member_id=?";
				있으면 review.review_like_done='Y', 아니면 N으로 저장
			 */
			sql = "SELECT * FROM review_like WHERE review_idx=? AND member_id=?";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setInt(1, review_idx);
			pstmt2.setString(2, sId);
			
			rs = pstmt2.executeQuery(); 
			
			if(rs.next()) {
				review.setReview_like_done("Y");
			}else {
				review.setReview_like_done("N");
			}
			
//			System.out.println("pstmt2: " + pstmt2);
			
		} catch (SQLException e) {
			System.out.println("ReviewBean- selectReview()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt2);
		}
		
		return review;
		
	}
	
	// 리뷰 조회수 증가
	public int updateReadCount(int review_idx) {
		int updateCount = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE review SET review_readcount=review_readcount+1 WHERE review_idx=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, review_idx);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("ReviewDAO - updateReadCount()");
			e.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
		}		

		return updateCount;
		
	}
	
	// 리뷰 글번호 조회
	public boolean isReviewWriter(int review_idx, String review_passwd) {
		
		boolean isReviewWriter = false;
		
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		try {
			String sql = "SELECT * FROM review WHERE review_idx=? AND review_passwd=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, review_idx);
			pstmt.setString(2, review_passwd);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				isReviewWriter = true;
				
			}
		} catch (SQLException e) {
			System.out.println("ReviewDAO- isReviewWriter()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return isReviewWriter;
	}
	
	// 리뷰 삭제
	public int deleteReview(int review_idx) {
		
		int deleteCount = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "DELETE FROM review WHERE review_idx=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, review_idx);
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return deleteCount;
	}
	
	// 리뷰 수정
	public int updateReview(ReviewBean review) {
		
		int updateCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE review"
					+ " SET"
					+ " review_subject = ?"
					+ " , review_content = ?"
					+ " , review_score = ?"
					+ " WHERE"
							+ " review_idx = ? AND"
							+ " review_passwd = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, review.getReview_subject());
			pstmt.setString(2, review.getReview_content());
			pstmt.setInt(3, review.getReview_score());
			pstmt.setInt(4, review.getReview_idx());
			pstmt.setString(5, review.getReview_passwd());
			
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return updateCount;
	}

	// 리뷰 좋아요 누른 아이디 , 글번호 추가 
	public int insertReviewLike(int review_idx, String member_id, String review_like_done) {
		System.out.println("ReviewDAO - insertReviewlike()");
		int review_like_count = -1;
		int insertCount=0;
		ResultSet rs = null;
		PreparedStatement pstmt = null, pstmt2=null, pstmt3=null;
		
		try {
			System.out.println("review_like_done:" + review_like_done);
			String sql=null;
			if(review_like_done.equals("N")) {
				
			sql = "INSERT INTO review_like VALUES(?,?)";
			
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, review_idx);
				pstmt.setString(2, member_id);
				System.out.println(pstmt);
				insertCount = pstmt.executeUpdate();
				
			}else if(review_like_done.equals("Y")) {
			
			 sql = "DELETE FROM review_like WHERE review_idx=? AND member_id=?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, review_idx);
				pstmt.setString(2, member_id);
				System.out.println(pstmt);
				insertCount = pstmt.executeUpdate();
				
			}
			
			if(insertCount>0) {
				sql = "update review "
						+ "set review_like_count = ("
						+ "select count(*) "
						+ "from review_like "
						+ "where review_idx = ?) "
						+ "where review_idx = ?";
	
				pstmt2 = con.prepareStatement(sql);
				pstmt2.setInt(1,review_idx);
				pstmt2.setInt(2, review_idx);
	
				int updateCount = pstmt2.executeUpdate();
	
				if(updateCount > 0) {
					sql = "SELECT review_like_count FROM review WHERE review_idx=?";
					pstmt3 = con.prepareStatement(sql);
					pstmt3.setInt(1,  review_idx);
					rs = pstmt3.executeQuery();
					if(rs.next()) {
						review_like_count = rs.getInt(1);
//						System.out.println("review_like_count: " + rv.getReview_like_count());
					}
				}
			}

		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 - insertReview_like()");
			e.printStackTrace();
		} finally {
		  JdbcUtil.close(rs);
			JdbcUtil.close(pstmt3);
			JdbcUtil.close(pstmt2);
			JdbcUtil.close(pstmt);
		}
		return review_like_count; 
	}
	
	// 상품 이미지 및 이름 조회
	public ReviewBean selectProduct(String product_idx) {
		ReviewBean review = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		try {
			if(product_idx.charAt(0) == 'B') {
				String sql = "SELECT book_img, book_name FROM book WHERE book_idx=?";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, product_idx);
				
				rs = pstmt.executeQuery(); 
				if(rs.next()) { 
					review = new ReviewBean(); 
					
					review.setProduct_img(rs.getString("book_img"));
					review.setProduct_name(rs.getString("book_name"));
//					System.out.println(review);
					
				}
			}else {
				String sql = "SELECT goods_img, goods_name FROM goods WHERE goods_idx=?";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, product_idx);
				
				rs = pstmt.executeQuery(); 
				if(rs.next()) { 
					review = new ReviewBean(); 
					
					review.setProduct_img(rs.getString("goods_img"));
					review.setProduct_name(rs.getString("goods_name"));
//					System.out.println(review);
					
				}
			}
			
		} catch (SQLException e) {
			System.out.println("ReviewBean- selectProduct()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return review;
	}

	
}
