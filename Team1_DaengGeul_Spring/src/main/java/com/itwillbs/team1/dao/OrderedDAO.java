package com.itwillbs.team1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.itwillbs.team1.db.JdbcUtil;
import com.itwillbs.team1.vo.OrderBean;

public class OrderedDAO { //싱글톤디자인패턴

	private OrderedDAO() {} //new 못하게 생성자 잠금
	private static OrderedDAO instance = new OrderedDAO(); //외부에서 변경 못하게 1개만 만들고 잠궈놓기 (공용객체)
	private Connection con; //commit, rollback을 한번에 처리해야하므로 con 객체 1개만 만들어서 관리
	private PreparedStatement pstmt=null, pstmt2=null;
	private ResultSet rs=null, rs2=null;

	public static OrderedDAO getInstance() { //외부에서 인스턴스 받아오기 위해 public static
		return instance;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}


	//=====================주문 내역 저장=====================
	public boolean insertOrderList(OrderBean order) {

		System.out.println("DAO - insertOrderList 진입");
		boolean isSuccessOrder=false;

		try {
			int insertCount =0;

			///orderInfo에 들어갈 데이터
			String sql = "INSERT INTO orderInfo VALUES(?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, order.getOrder_merchant_uid()); //주문번호=결제번호
			pstmt.setString(2, order.getOrder_imp_uid());  //아임포트 고유번호
			pstmt.setInt(3, order.getOrder_total_pay());
			pstmt.setString(4, order.getOrder_payment());
			pstmt.setInt(5, (-1)*order.getOrder_point());
			pstmt.setString(6, order.getOrder_name());
			pstmt.setString(7, order.getOrder_address());
			pstmt.setString(8, order.getOrder_phone());

			insertCount = pstmt.executeUpdate();

			int prodInsertCount =0;

			if(insertCount>0) {

				for(int i=0; i<order.getOrder_prod_cnt().size(); i++) {
					///orderProd에 들어갈 데이터
					sql = "INSERT INTO orderprod VALUES(?,?,?,?,?,'결제완료',?)";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, order.getOrder_merchant_uid());
					pstmt.setString(2, order.getOrder_prod_idx().get(i));
					pstmt.setString(3, order.getOrder_prod_opt().get(i));
					pstmt.setInt(4, order.getOrder_prod_cnt().get(i));
					pstmt.setInt(5, order.getOrder_prod_price().get(i));
					pstmt.setString(6, order.getOrder_prod_name().get(i));
//					System.out.println(pstmt);
					insertCount = pstmt.executeUpdate();
					if(insertCount > 0) {
						prodInsertCount++;
					}
				}

				if(prodInsertCount==order.getOrder_prod_cnt().size()) {
					isSuccessOrder = true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}

		return isSuccessOrder;
	}

	//=====================주문 내역 가져오기=====================
	public ArrayList<OrderBean> selectOrderList(String id, String period) {

		System.out.println("DAO - selectOrderList 진입");
		ArrayList<OrderBean> orderList=null;

		try {

			orderList = new ArrayList<>();

			String sql = "SELECT * FROM order_view WHERE member_id=? AND order_date BETWEEN date(now()-INTERVAL "+ period +") and date(now()) ORDER BY order_idx desc;";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
//			System.out.println(pstmt);
			rs=pstmt.executeQuery();

			List<String> idxArr = new ArrayList<>();
			while(rs.next()) {
				OrderBean order = new OrderBean();
				order.setOrder_merchant_uid(rs.getString("order_idx"));
				order.setOrder_total_pay(rs.getInt("order_total_pay"));
				order.setOrder_date(rs.getDate("order_date"));
				idxArr.add(rs.getString("order_idx"));
				orderList.add(order);
			}



			for(OrderBean order : orderList) {

				ArrayList<String> prod_idx = new ArrayList<>();
				ArrayList<String> name = new ArrayList<>();
				ArrayList<String> status = new ArrayList<>();
				ArrayList<String> opt = new ArrayList<>();
				ArrayList<Integer> price = new ArrayList<>();
				ArrayList<Integer> cnt = new ArrayList<>();
				sql = "SELECT * FROM orderprod WHERE order_idx=?"; 
				pstmt2 = con.prepareStatement(sql);
				pstmt2.setString(1, order.getOrder_merchant_uid());
				rs2=pstmt2.executeQuery();
				while(rs2.next()) {
					prod_idx.add(rs2.getString(2));
					price.add(rs2.getInt(5));
					cnt.add(rs2.getInt(4));
					opt.add(rs2.getString(3));
					status.add(rs2.getString(6));
					name.add(rs2.getString(7));
				}

				order.setOrder_prod_price(price);
				order.setOrder_status(status);
				order.setOrder_prod_name(name);
				order.setOrder_prod_idx(prod_idx);
				order.setOrder_prod_opt(opt);
				order.setOrder_prod_cnt(cnt);
			}

			////리뷰 썼는지 유무 확인
			for(OrderBean order : orderList) {

				ArrayList<String> prod_idx = order.getOrder_prod_idx();
				ArrayList<String> review = new ArrayList<>();

				for(String prod : prod_idx) {
					sql = "SELECT * FROM review WHERE product_idx=? AND order_idx=?";
					pstmt2 = con.prepareStatement(sql);
					pstmt2.setString(1, prod);
					pstmt2.setString(2, order.getOrder_merchant_uid());
					rs2=pstmt2.executeQuery();
					if(rs2.next()) {
						review.add("Y");
					} else {
						review.add("N");
					}
				}
				order.setReview_write(review);
			}

			////상품이미지 가져오기
			for(OrderBean order : orderList) {

				ArrayList<String> prod_idx = order.getOrder_prod_idx();
				ArrayList<String> img = new ArrayList<>();

				for(String prod : prod_idx) {
					
					if(prod.charAt(0)=='B') {
						sql = "SELECT book_img FROM book WHERE book_idx=?";
					} else if(prod.charAt(0)=='G') {
						sql = "SELECT goods_img FROM goods WHERE goods_idx=?";
					}
					pstmt2 = con.prepareStatement(sql);
					pstmt2.setString(1, prod);
					rs2=pstmt2.executeQuery();
					
					if(rs2.next()) {
						img.add(rs2.getString(1));
					} else {
						img.add("-");
					}
				}
				order.setOrder_prod_img(img);
//				System.out.println(order.getOrder_prod_img());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			if(rs2!=null) {
				JdbcUtil.close(rs2);
				JdbcUtil.close(pstmt2);
			}
		}

		return orderList;
	}

	//=====================주문 상세 내역 가져오기=====================
	public OrderBean selectOrderDetail(String id, String order_idx) {

		System.out.println("DAO - selectOrderDetail 진입");
		OrderBean order =null;

		try {
			String sql = "SELECT * from orderinfo WHERE substring(order_idx,1,length(?))=? AND order_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			pstmt.setString(3, order_idx);
			rs=pstmt.executeQuery();

			if(rs.next()) {
				order = new OrderBean();
				order.setOrder_total_pay(rs.getInt("order_total_pay"));
				int start = id.length();
				order.setOrder_date(java.sql.Date.valueOf(order_idx.substring(start,start+4)+"-"+order_idx.substring(start+4,start+6)+"-"+order_idx.substring(start+6,start+8)));
				order.setOrder_address(rs.getString("order_address"));
				order.setOrder_name(rs.getString("order_name"));
				order.setOrder_phone(rs.getString("order_phone"));
				order.setOrder_payment(rs.getString("order_payment"));
				order.setOrder_point(rs.getInt("order_point"));

			}

			sql = "SELECT * FROM orderprod WHERE order_idx=? AND substr(order_idx,1,length(order_idx)-14)=?";

			pstmt2 = con.prepareStatement(sql);
			pstmt2.setString(1, order_idx );
			pstmt2.setString(2, id);
			rs2=pstmt2.executeQuery();

			ArrayList<String> prod_idx = new ArrayList<>();
			ArrayList<String> name = new ArrayList<>();
			ArrayList<String> status = new ArrayList<>();
			ArrayList<String> opt = new ArrayList<>();
			ArrayList<Integer> price = new ArrayList<>();
			ArrayList<Integer> cnt = new ArrayList<>();

			while(rs2.next()) {
				prod_idx.add(rs2.getString(2));
				price.add(rs2.getInt(5));
				cnt.add(rs2.getInt(4));
				opt.add(rs2.getString(3));
				status.add(rs2.getString(6));
				name.add(rs2.getString(7));
			}
			order.setOrder_prod_price(price);
			order.setOrder_status(status);
			order.setOrder_prod_name(name);
			order.setOrder_prod_idx(prod_idx);
			order.setOrder_prod_opt(opt);
			order.setOrder_prod_cnt(cnt);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(rs2);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(pstmt2);
		}

		return order;
	}

	//=======================(지선)멤버 포인트 업데이트=======================
	public boolean updateMemberPoint(String id, int point) {

		System.out.println("DAO - updateMemberPoint 진입");
		boolean isSuccessUpdate = false;

		try {
			String sql = "UPDATE member SET member_point=member_point+? where member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, point);
			pstmt.setString(2, id);
			int updateCount = pstmt.executeUpdate();

			if(updateCount>0) {
				isSuccessUpdate=true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return isSuccessUpdate;
	}



}