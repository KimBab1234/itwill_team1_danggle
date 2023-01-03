package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import db.JdbcUtil;
import vo.ProductBean;

public class ProductDAO {
	private ProductDAO() {}
	
	private static ProductDAO instance = new ProductDAO();
	public static ProductDAO getInstance() {
		return instance;
	}
	
	private Connection con = null;
	public void setCon(Connection con) {
		this.con = con;
	}
	
	PreparedStatement pstmt, pstmt2, pstmt3 = null;
	ResultSet rs = null;
	
	//=================================================
	//=====================경민========================
	//=================================================
	
	public int regiBook(ProductBean book) { // 책 등록
		int insertCount = 0;
		
		try {
			// product 테이블 idx 최대값에서 +1
			// (책, 굿즈 공통으로 쓸 idx)
			int idx = 1;
			String sql = "SELECT MAX(idx) FROM product";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				idx = rs.getInt(idx) + 1;
			}
			// 책, 굿즈 구분할 product_idx
			String product_idx = "B" + idx; 
			sql = "INSERT INTO product VALUES(?,?)";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setInt(1, idx);
			pstmt2.setString(2, product_idx);
			pstmt2.executeUpdate();

			sql = "INSERT INTO book VALUES(?,?,?,?,?,?,?,?,0,?,?,?,?)";
			pstmt3 = con.prepareStatement(sql);
			pstmt3.setString(1, product_idx);
			pstmt3.setString(2, book.getName());
			pstmt3.setString(3, book.getBook_genre());
			pstmt3.setString(4, book.getBook_writer());
			pstmt3.setString(5, book.getBook_publisher());
			pstmt3.setInt(6, book.getPrice());
			pstmt3.setInt(7, book.getQuantity());
			pstmt3.setDate(8, book.getBook_date());
			pstmt3.setString(9, book.getDetail());
			pstmt3.setInt(10, book.getDiscount());
			pstmt3.setString(11, book.getImg());
			pstmt3.setString(12, book.getDetail_img());

			insertCount = pstmt3.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("sql 구문 오류 - regiBook(책 등록)");
			e.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt3);
			JdbcUtil.close(pstmt2);
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return insertCount;
	}
	
	public int regiGoods(ProductBean goods) { // 굿즈 등록
		int insertCount = 0;
		
		try {
			int idx = 1;
			
			String sql = "SELECT MAX(idx) FROM product";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				idx = rs.getInt(idx) + 1;
			}
			
			String product_idx = "G" + idx;
			sql = "INSERT INTO product VALUES(?,?)";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setInt(1, idx);
			pstmt2.setString(2, product_idx);
			pstmt2.executeUpdate();
			
			sql = "INSERT INTO goods VALUES(?,?,?,?,?,?,?,?,0,now())";
			pstmt3 = con.prepareStatement(sql);
			pstmt3.setString(1, product_idx);
			pstmt3.setString(2, goods.getName());
			pstmt3.setInt(3, goods.getPrice());
			pstmt3.setInt(4, goods.getQuantity());
			pstmt3.setString(5, goods.getDetail());
			pstmt3.setString(6, goods.getImg());
			pstmt3.setString(7, goods.getDetail_img());
			pstmt3.setInt(8, goods.getDiscount());
			System.out.println(pstmt3);

			insertCount = pstmt3.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("sql 구문 오류 - regiGoods(굿즈 등록)");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt3);
			JdbcUtil.close(pstmt2);
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return insertCount;
	}
	
	public ArrayList<ProductBean> selectProductList(int startRow,  int listLimit) { // 목록 조회
		ArrayList<ProductBean> productList = null;
		
		try {
			// 책 목록 조회
			String sql = "SELECT * FROM book ORDER BY book_idx LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, listLimit);
			
			rs = pstmt.executeQuery();
			
			productList = new ArrayList<ProductBean>();
			while(rs.next()) {
				ProductBean book = new ProductBean();
				book.setProduct_idx(rs.getString("book_idx"));
				book.setName(rs.getString("book_name"));
				book.setQuantity(rs.getInt("book_quantity"));
				book.setPrice(rs.getInt("book_price"));
				book.setDiscount(rs.getInt("book_discount"));
				
				productList.add(book);
			}
			JdbcUtil.close(rs);
			
			// 굿즈 목록 조회
			sql = "SELECT * FROM goods ORDER BY goods_idx"; 
			pstmt2 = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, listLimit);

			rs = pstmt2.executeQuery();
			
			while(rs.next()) {
				ProductBean goods = new ProductBean();
				goods.setProduct_idx(rs.getString("goods_idx"));
				goods.setName(rs.getString("goods_name"));
				goods.setQuantity(rs.getInt("goods_quantity"));
				goods.setPrice(rs.getInt("goods_price"));
				goods.setDiscount(rs.getInt("goods_discount"));
				
				productList.add(goods);
			}
		} catch (SQLException e) {
			System.out.println("sql 구문 오류 - selectProductList(상품 목록 조회)");
			e.printStackTrace();
		}
		
		return productList;
	}
	
	public int getProductListCount() {
		int listCount = 0;
		
		try {
			String sql = "SELECT COUNT(*) FROM book";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("sql 구문 오류 - getProductListCount (전체 페이지 개수 조회)");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

		return listCount;
	}

	
	public ProductBean selectBook(String product_idx) { // 책 상세 페이지
		ProductBean book = null;
		
		try {
			String sql = "SELECT * FROM book WHERE book_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, product_idx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				book = new ProductBean();
				book.setProduct_idx(rs.getString("book_idx"));
				book.setName(rs.getString("book_name"));
				book.setBook_writer(rs.getString("book_writer"));
				book.setBook_publisher(rs.getString("book_publisher"));
				book.setBook_date(rs.getDate("book_date"));
				book.setBook_genre(rs.getString("book_genre"));
				book.setPrice(rs.getInt("book_price"));
				book.setDiscount(rs.getInt("book_discount"));
				book.setQuantity(rs.getInt("book_quantity"));
				book.setImg(rs.getString("book_img"));
				book.setDetail(rs.getString("book_detail"));
				book.setDetail_img(rs.getString("book_detail_img"));
			}
			
		} catch (Exception e) {
			System.out.println("sql 구문 오류 - selectBook(책 수정 상세페이지)");
			e.printStackTrace();
		}
		
		return book;
	}
	
	public ProductBean selectGoods(String product_idx) { // 굿즈 상세페이지
		ProductBean goods = null;
		
		try {
			String sql = "SELECT * FROM goods WHERE goods_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, product_idx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				goods = new ProductBean();
				
				goods.setProduct_idx(rs.getString("goods_idx"));
				goods.setName(rs.getString("goods_name"));
				goods.setPrice(rs.getInt("goods_price"));
				goods.setQuantity(rs.getInt("goods_quantity"));
				goods.setDetail(rs.getString("goods_detail"));
				goods.setImg(rs.getString("goods_img"));
				goods.setDetail_img(rs.getString("goods_detail_img"));
				goods.setDiscount(rs.getInt("goods_discount"));
				
			}
			
		} catch (SQLException e) {
			System.out.println("sql 구문 오류 - selectGoods(굿즈 수정 상세페이지)");
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return goods;
	}
	
	public int updateBook(ProductBean book) { // 책 수정
int updateCount = 0;
		
		try {
			String sql = "UPDATE book SET book_name=?, book_genre=?, book_writer=?, book_publisher=?"
					+ ", book_price=?, book_quantity=?, book_date=?, book_detail=?, book_discount=?"
					+ ", book_img=?, book_detail_img=? WHERE book_idx=?";
					
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, book.getName());
			pstmt.setString(2, book.getBook_genre());
			pstmt.setString(3, book.getBook_writer());
			pstmt.setString(4, book.getBook_publisher());
			pstmt.setInt(5, book.getPrice());
			pstmt.setInt(6, book.getQuantity());
			pstmt.setDate(7, book.getBook_date());
			pstmt.setString(8, book.getDetail());
			pstmt.setInt(9, book.getDiscount());
			pstmt.setString(10, book.getImg());
			pstmt.setString(11, book.getDetail_img());
			pstmt.setString(12, book.getProduct_idx());
			
			updateCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("sql 구문 오류 - updateBook(책 수정)");
			e.printStackTrace();
		}
		
		return updateCount;
	
	}
	
	
	public int updateGoods(ProductBean goods) { // 굿즈 수정
		int updateCount = 0;
		
		try {
			String sql = "UPDATE goods SET goods_name=?, goods_price=?, goods_quantity=?, goods_detail=?, goods_discount=?"
					+ ", goods_img=?, goods_detail_img=? WHERE goods_idx=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, goods.getName());
			pstmt.setInt(2, goods.getPrice());
			pstmt.setInt(3, goods.getQuantity());
			pstmt.setString(4, goods.getDetail());
			pstmt.setInt(5, goods.getDiscount());
			pstmt.setString(6, goods.getImg());
			pstmt.setString(7, goods.getDetail_img());
			pstmt.setString(8, goods.getProduct_idx());
			
			updateCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("sql 구문 오류 - updateGoods(굿즈 수정)");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return updateCount;
	}

	public ProductBean selectFileName(String product_idx) { // 상품 삭제
		ProductBean product = null;
		String sql ="";
		try {
			
			if(product_idx.substring(0, 1).equals("B")) {
				sql = "SELECT book_img, book_detail_img FROM book WHERE book_idx=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, product_idx);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					product = new ProductBean();
					
					product.setImg(rs.getString("book_img"));
					product.setDetail_img(rs.getString("book_detail_img"));
					
				}
				
			}else {
				sql = "SELECT goods_img, goods_detail_img FROM goods WHERE goods_idx=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, product_idx);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					product = new ProductBean();
					
					product.setImg(rs.getString("goods_img"));
					product.setDetail_img(rs.getString("goods_detail_img"));
					
				}
			}
			
		} catch (SQLException e) {
			System.out.println("sql 구문 오류 - selectFileName(파일 이름 조회)");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return product;
	}

	public int deleteProduct(String product_idx) { // 상품 삭제
		int deleteCount = 0;
		String sql = "";
		try {
			
			if(product_idx.substring(0, 1).equals("B")) { // 책 삭제
				sql = "DELETE FROM book where book_idx=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, product_idx);
				
				deleteCount = pstmt.executeUpdate();
				
			}else { // 굿즈 삭제
				
				sql = "DELETE FROM goods WHERE goods_idx=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, product_idx);
				
				deleteCount = pstmt.executeUpdate();
			}
			
			sql = "DELETE FROM product where product_idx=?"; // 상품번호(공통 테이블) 삭제
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setString(1, product_idx);
			pstmt2.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("sql 구문 오류 - deleteProduct(상품 삭제)");
			e.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt2);
			JdbcUtil.close(pstmt);
		}
		
		return deleteCount;
	}

	//=================================================
	//=====================지선========================
	//=================================================


	//=====================상품 1개 가져오기=====================
	public ProductBean selectProduct(String idx) {

		System.out.println("DAO - selectBook 진입");
		ProductBean product = null;

		try {
			String sql = null;

			if(idx.charAt(0)=='B') {
				sql = "SELECT book_idx, book_name, book_price, book_quantity, book_detail, book_discount, book_img, book_detail_img FROM book WHERE book_idx=?";
			} else if(idx.charAt(0)=='G') {
				sql = "SELECT goods_idx, goods_name, goods_price, goods_quantity, goods_detail, goods_discount, goods_img, goods_detail_img FROM goods WHERE goods_idx=?";
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, idx);
			rs=pstmt.executeQuery();

			product = new ProductBean();

			if(rs.next()) {
				product.setProduct_idx(rs.getString(1));
				product.setName(rs.getString(2));
				product.setPrice(rs.getInt(3));
				product.setQuantity(rs.getInt(4));
				product.setDetail(rs.getString(5).replaceAll(System.lineSeparator(), "<br>"));
				product.setDiscount(rs.getInt(6));
				product.setImg(rs.getString(7));
				product.setDetail_img(rs.getString(8));
				product.setDis_price(rs.getInt(3)*(100-rs.getInt(6))/100);
			}

			if(idx.charAt(0)=='B') {
				sql = "SELECT book_genre, book_writer, book_publisher, book_date FROM book WHERE book_idx=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, idx);
				rs=pstmt.executeQuery();

				if(rs.next()) {
					product.setBook_genre(rs.getString(1));
					product.setBook_writer(rs.getString(2));
					product.setBook_publisher(rs.getString(3));
					product.setBook_date(rs.getDate(4));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

		return product;
	}

	//=====================장바구니 정보 가져오기=====================
	public ArrayList<ProductBean> selectProductList(Set<String> productSet) {

		System.out.println("DAO - selectProductList 진입");
		ArrayList<ProductBean> productList=null;

		try {

			productList = new ArrayList<>();

			for(String idx : productSet) {

				String sql = null;

				if(idx.charAt(0)=='B') {
					sql = "SELECT book_idx, book_name, book_price, book_discount, book_img FROM book WHERE book_idx=?";
				} else if(idx.charAt(0)=='G') {
					sql = "SELECT goods_idx, goods_name, goods_price, goods_discount, goods_img FROM goods WHERE goods_idx=?";
				}
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, idx);
				rs=pstmt.executeQuery();

				if(rs.next()) {

					ProductBean product = new ProductBean();

					product.setProduct_idx(rs.getString(1));
					product.setName(rs.getString(2));
					product.setPrice(rs.getInt(3));
					product.setDiscount(rs.getInt(4));
					product.setDis_price(rs.getInt(3)*(100-rs.getInt(4))/100);
					product.setImg(rs.getString(5));
					productList.add(product);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

		return productList;
	}

	//=====================상품 리스트 가져오기=====================
	public List<ProductBean> selectProductList(String type, String sort, String keyword, int pageStartRow, int pageProductCount) {

		System.out.println("DAO - selectProductList 진입");
		LinkedList<ProductBean> productList=null;

		try {
			String sql = "";
			boolean book_chk = false;
			if(type.equals("B")) {
				sql = "SELECT book_idx, book_name, book_price, book_discount, book_img, book_price*(100-book_discount)/100 'dis_price', ifnull(sel_count,0) 'sel_count', regi_date, book_genre, book_writer, book_publisher From book_sort_view";
				book_chk = true;
			} else if(type.equals("G")) {
				sql = "SELECT goods_idx, goods_name, goods_price, goods_discount, goods_img, goods_price*(100-goods_discount)/100 'dis_price', goods_date 'regi_date' FROM goods";
			}

			String[] sortArr = sort.split("_");

			//언더바가 2개 들어가는건 검색, 장르 뿐임
			if(sortArr.length > 1) {
				if(sortArr[1].equals("search")) { //검색이면 name으로 찾기
					sql += " WHERE book_name LIKE '%"+keyword+"%'";
				} else if(sortArr[1].equals("genre")){
					sql += " WHERE book_genre='"+keyword+"'"; //search가 아니면 장르뿐임
				}
			}
			
			if(sortArr[0].equals("priceup")) { //높은 가격순으로
				sql += " ORDER BY dis_price DESC";
			} else if(sortArr[0].equals("pricedown")) { //낮은 가격 순으로
				sql += " ORDER BY dis_price ASC";
			} else if(sortArr[0].equals("best")) { //판매량 높은 순으로
				sql += " ORDER BY sel_count DESC";
			} else if(sortArr[0].equals("new")) {  //신상품 순으로
				sql += " ORDER BY regi_date DESC";
			} else if(sortArr[0].equals("star")) {  //별점 좋은 순으로
				
			} else if(sortArr[0].equals("disc")) { //할인율 높은 순으로
				sql += " WHERE book_discount > 0 ORDER BY book_discount DESC";
			} else if(sortArr[0].equals("recomm")) {  //운영자 추천도서
				sql = "SELECT book_idx, book_name, book_price, book_discount, book_img, book_price*(100-book_discount)/100 'dis_price', ifnull(sel_count,0) 'sel_count', regi_date, book_genre, book_writer, book_publisher"
					+ " From book_sort_view join recommend_book r on r.product_idx=book_sort_view.book_idx";
			} 
			
			sql += " LIMIT ?,?"; //1페이지당 상품 12개씩 가져오는건 책, 굿즈 동일함
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pageStartRow);
			pstmt.setInt(2, pageProductCount);
//			System.out.println(pstmt);
			rs=pstmt.executeQuery();

			productList = new LinkedList<>();

			while(rs.next()) {
				ProductBean product = new ProductBean();

				product.setProduct_idx(rs.getString(1));
				product.setName(rs.getString(2));
				product.setPrice(rs.getInt(3));
				product.setDiscount(rs.getInt(4));
				product.setImg(rs.getString(5));
				product.setDis_price(rs.getInt(6));
				product.setSel_count(rs.getInt(7));
				product.setBook_date(rs.getDate(8));
				
				if(book_chk) {
					product.setBook_genre(rs.getString(9));
					product.setBook_writer(rs.getString(10));
					product.setBook_publisher(rs.getString(11));
				}
				productList.add(product);
			}


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

		return productList;

	}
	
	//=====================리스트별 상품 갯수 가져오기=====================
	public int selectProductCount(String type, String sort, String keyword) {

		System.out.println("DAO - selectProductCount 진입");
		int productCount =0;
		System.out.println(sort);
		try {
			String sql = "";
			if(type.equals("B")) {
				sql = "SELECT COUNT(*) FROM book";
				if(sort.equals("genre")) {
					sql += " WHERE book_genre='"+ keyword +"'";
				} else if(sort.equals("search")) {
					sql += " WHERE book_name LIKE '%"+ keyword +"%'";
				} else if(sort.equals("recomm")) {
					sql = "SELECT COUNT(*) From book join recommend_book r on r.product_idx=book.book_idx";
				} else if(sort.equals("disc")) {
					sql += " WHERE book_discount >0";
				}
			} else if(type.equals("G")) {
				sql = "SELECT COUNT(*) FROM goods";
			}
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				productCount = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

		return productCount;

	}
	
	//=====================상품 발송 후 상품 재고, 판매량 업데이트=====================
		public boolean updateProductSell(ArrayList<String> orderArr, ArrayList<Integer> countList) {

			System.out.println("DAO - updateProductSell 진입");
			int successCount =0;
			boolean isSuccessUpdate=false;
			
			try {
				
				String sql = "";
				int i=0;
				for(String productIdx : orderArr) {
					
					int productCount = countList.get(i++);
					
					if(productIdx.charAt(0)=='B') {
						sql = "UPDATE book SET book_sel_count=book_sel_count+?, book_quantity=book_quantity-? WHERE book_idx=?";
					} else if(productIdx.charAt(0)=='G') {
						sql = "UPDATE goods SET goods_sel_count=goods_sel_count+?, goods_quantity=goods_quantity-? WHERE goods_idx=?";
					}
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, productCount);
					pstmt.setInt(2, productCount);
					pstmt.setString(3, productIdx);
					
					successCount+= pstmt.executeUpdate();
				}
				
				//모두 정상 반영됐으면
				if(successCount==orderArr.size()) {
					isSuccessUpdate=true;
				}
				

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JdbcUtil.close(pstmt);
			}

			return isSuccessUpdate;

		}
		
		
		//=====================주문한 상품 정보 가져오기=====================
		public ArrayList<ProductBean> selectOrderProductList(String order_list) {

			System.out.println("DAO - selectOrderProductList 진입");
			ArrayList<ProductBean> productList=null;

			try {

				productList = new ArrayList<>();

				String[] productSet = order_list.split(", ");
				
				for(String idx : productSet) {

					String sql = null;

					if(idx.charAt(0)=='B') {
						sql = "SELECT  book_name, book_img FROM book WHERE book_idx=?";
					} else if(idx.charAt(0)=='G') {
						sql = "SELECT  goods_name, goods_img FROM goods WHERE goods_idx=?";
					}
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, idx);
					rs=pstmt.executeQuery();

					if(rs.next()) {

						ProductBean product = new ProductBean();

						product.setName(rs.getString(2));
						product.setPrice(rs.getInt(3));
						product.setImg(rs.getString(5));
						
						productList.add(product);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}

			return productList;
		}


}
