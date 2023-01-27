package com.itwillbs.team1.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.itwillbs.team1.vo.OrderBean;
import com.itwillbs.team1.vo.OrderProdBean;

@Mapper
@Transactional(rollbackFor = Exception.class)
public interface OrderMapper {

	//=====================주문 내역 저장=====================
	public int insertOrder(OrderBean order); 
	public int insertOrderProd(OrderProdBean order); 

	//=====================주문 내역 가져오기=====================
	public ArrayList<OrderBean> selectOrderList(@Param("id") String id, @Param("period") String period); 
	public ArrayList<OrderProdBean> selectOrderProdList(@Param("order_idx") String order_idx); 

	//=====================주문 상세 내역 가져오기=====================
	public OrderBean selectOrderDetail(@Param("id") String id, @Param("order_idx") String order_idx); 

	//=======================(지선)멤버 포인트 업데이트=======================
	public int updateMemberPoint(@Param("id") String id, @Param("point") int point); 
	
}
