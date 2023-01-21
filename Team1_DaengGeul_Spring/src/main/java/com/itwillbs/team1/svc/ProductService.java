package com.itwillbs.team1.svc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.team1.mapper.ProductMapper;
import com.itwillbs.team1.vo.ProductBean;
import com.itwillbs.team1.vo.ProductOptBean;

@Service
public class ProductService {

	@Autowired
	private ProductMapper mapper;
	
	///////////////// 경민 	/////////////////
	public ProductBean selectFileName(String product_idx) {
		System.out.println("selectFileName - 파일 이름 조회");
		return mapper.selectFileName(product_idx);
	}

	public int removeProduct(String product_idx) {
		System.out.println("removeProduct - 상품 삭제");
		return mapper.deleteProduct(product_idx);
	}
	
	public int updateBook(ProductBean product) {
		System.out.println("updateBook - 책 수정 서비스 페이지");
		return mapper.updateBook(product);
	}
	public ProductBean getBook(String product_idx) {
		System.out.println("ProductEditService(getBook) - 책 수정");
		return mapper.selectBook(product_idx);
	}
	public ProductBean getGoods(String product_idx) {
		System.out.println("ProductEditService(getGoods) - 굿즈 수정");
		return mapper.selectGoods(product_idx);
	}
	
	public int bookregistration(ProductBean book) {
		System.out.println("bookregistration - 책 등록 서비스 페이지");
		return mapper.regiBook(book);
	}
	
	public int goodsregistration(ProductBean goods) {
		System.out.println("goodsregistration - 굿즈 등록 서비스 페이지");
		return mapper.regiGoods(goods);
	}
	
	///////////////// 경민+지선 /////////////////
	
	////////상품 1개 가져오기
	public ProductBean getProduct(String product_idx) {
		System.out.println("ProductDetailService- getProduct 진입");
		
		String type="";
		if(product_idx.charAt(0)=='B' ) {
			type="book_sort_view";
		} else if(product_idx.charAt(0)=='G') {
			type="goods_sort_view";
		}
		
		ProductBean product = mapper.selectProduct(type, product_idx); 
		if(product_idx.charAt(0)=='G') {
			product.setOption(mapper.selectProductOpt(product_idx)); 
		}
		
		return product;
	}
	
	////////상품 갯수 계산
	public int getProductListCount(String type, String sort, String keyword) {

		String sql = "";
		if(type.equals("B")) {
			sql = "SELECT COUNT(*) FROM book";
			if(sort.equals("genre")) {
				sql += " WHERE genre='"+ keyword +"'";
			} else if(sort.equals("search")) {
				sql += " WHERE name LIKE '%"+ keyword +"%'";
			} else if(sort.equals("recomm")) {
				sql = "SELECT COUNT(*) From book join recommend_book r on r.product_idx=book.product_idx";
			} else if(sort.equals("disc")) {
				sql += " WHERE discount >0";
			}
		} else if(type.equals("G")) {
			sql = "SELECT COUNT(*) FROM goods";
		}
		
		return mapper.selectProductCount(sql);
	}
	
	////////상품 목록 가져오기
	public List<ProductBean> getProductList(String product_type, String sort, String keyword, int pageStartRow, int pageProductCount) {
		System.out.println("ProductListService- getProductList 진입");
		
		///책인지 굿즈인지 테이블 구분
		if(product_type.equals("B")) {
			product_type="book_sort_view";
		} else if(product_type.equals("G")) {
			product_type="goods_sort_view";
		}
		
		
		String[] sortArr = sort.split("_");
		sort = ""; ///sort 초기화
		
		//언더바가 2개 들어가는건 검색, 장르 뿐임
		if(sortArr.length > 1) {
			if(sortArr[1].equals("search")) { //검색이면 name으로 찾기
				sort = " WHERE name LIKE '%"+keyword+"%'";
			} else if(sortArr[1].equals("genre")){
				sort = " WHERE genre='"+keyword+"'"; //search가 아니면 장르뿐임
			}
		}
		
		if(sortArr[0].equals("priceup")) { //높은 가격순으로
			sort += " ORDER BY dis_price DESC";
		} else if(sortArr[0].equals("pricedown")) { //낮은 가격 순으로
			sort += " ORDER BY dis_price ASC";
		} else if(sortArr[0].equals("best")) { //판매량 높은 순으로
			sort += " ORDER BY sel_count DESC";
		} else if(sortArr[0].equals("new")) {  //신상품 순으로
			sort += " ORDER BY date DESC";
		} else if(sortArr[0].equals("disc")) { //할인율 높은 순으로
			sort += " WHERE discount > 0 ORDER BY discount DESC";
		} else if(sortArr[0].equals("recomm")) {  //운영자 추천도서
			sort += " JOIN recommend_book r on r.product_idx=book_sort_view.product_idx";
		} else if(sortArr[0].equals("star")) {
			sort += " ORDER BY review_score DESC";
		}
		
		String sql = "SELECT * FROM "+product_type + sort + " LIMIT " + pageStartRow + "," + pageProductCount;
	
		return mapper.selectProductList(sql);
	}
	
	
}