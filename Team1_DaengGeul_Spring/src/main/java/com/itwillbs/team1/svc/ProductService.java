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
	
	public ProductBean selectFileName(String product_idx) {
		System.out.println("selectFileName - 파일 이름 조회");
		return mapper.selectFileName(product_idx);
	}

	public int removeProduct(String product_idx) {
		System.out.println("removeProduct - 상품 삭제");
		return mapper.deleteProduct(product_idx);
	}
	
	public ProductBean getProduct(String product_idx) {
		System.out.println("ProductDetailService- getProduct 진입");
		
		ProductBean product = mapper.selectProduct(product_idx); 
		product.setOption(mapper.selectProductOpt(product_idx)); 
		
		return product;
	}
	
	public ArrayList<ProductBean> getProductList(int startRow, int listLimit) {
		System.out.println("상품 목록 조회 서비스 페이지");
		return mapper.selectProductList(startRow, listLimit);
	}
	
	public int getProductListCount() {
		// 한 페이지에서 조회할 목록 개수 계산
		return mapper.getProductListCount();
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
	
	public List<ProductBean> getProductList(String product_type, String sort, String keyword, int pageStartRow, int pageProductCount) {
		System.out.println("ProductListService- getProductList 진입");
		return mapper.selectProductList(product_type, sort, keyword, pageStartRow,pageProductCount);
	}
	
	public int getProductCount(String product_type, String sort, String keyword) {
		System.out.println("ProductListService- getProductCount 진입");
		return mapper.selectProductCount(product_type, sort, keyword);
	}
	
	public int bookregistration(ProductBean book) {
		System.out.println("bookregistration - 책 등록 서비스 페이지");
		return mapper.regiBook(book);
	}
	
	public int goodsregistration(ProductBean goods) {
		System.out.println("goodsregistration - 굿즈 등록 서비스 페이지");
		return mapper.regiGoods(goods);
	}
	
}
