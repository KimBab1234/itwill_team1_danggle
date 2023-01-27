package com.itwillbs.team1.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.itwillbs.team1.vo.OrderProdBean;
import com.itwillbs.team1.vo.ProductBean;
import com.itwillbs.team1.vo.ProductOptBean;

@Mapper
@Transactional(rollbackFor = Exception.class)
public interface ProductMapper {
	
	public int selectIdx(); // 상품번호 조회
	
	public int insertProduct(@Param("product") ProductBean product, @Param("isBook") Boolean isBook); // 상품등록

	public ArrayList<ProductBean> selectProductList(int startRow,  int listLimit);  // 목록 조회

	public int getProductListCount();  // 페이지 계산

	public ProductBean selectBook(String product_idx);  // 책 상세 페이지

	public ProductBean selectGoods(String product_idx);  // 굿즈 상세페이지

	public int updateBook(ProductBean book);  // 책 수정

	public int updateGoods(ProductBean goods);  // 굿즈 수정

	public ProductBean selectFileName(String product_idx);  // 이미지 파일명 조회

	public int deleteProduct(String product_idx);  // 상품 삭제

	public int recommendBook(ProductBean book);  // 추천 도서 등록

	public ArrayList<ProductBean> selectRecoBook();  // 추천 도서 목록 조회

	public int deleteRecoBook(String product_idx);  // 추천 도서 삭제


	//=====================상품 1개 가져오기=====================
	public ProductBean selectProduct(@Param("type") String type, @Param("idx") String idx); 
	public ArrayList<ProductOptBean> selectProductOpt(String idx); 

	//=====================장바구니 정보 가져오기=====================
	public ArrayList<ProductBean> selectProductList(Set<String> productSet); 

	//=====================상품 리스트 가져오기=====================
	public List<ProductBean> selectProductList(String sql); 

	//=====================리스트별 상품 갯수 가져오기=====================
	public int selectProductCount(String sql); 

	//=====================상품 발송 후 상품 재고, 판매량 업데이트=====================
	public int updateProductSell(@Param("prod") OrderProdBean prod, @Param("type") String type); 
	public int updateProductOptSell(@Param("prod") OrderProdBean prod); 
	
	//=====================주문한 상품 정보 가져오기=====================
	public ArrayList<ProductBean> selectOrderProductList(String order_list);

	

	


}
