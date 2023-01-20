package com.itwillbs.team1.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.itwillbs.team1.vo.OrderBean;

public interface OrderMapper {

	//=====================주문 내역 저장=====================
	public boolean insertOrderList(OrderBean order); 

	//=====================주문 내역 가져오기=====================
	public ArrayList<OrderBean> selectOrderList(@Param("id") String id, @Param("period") String period); 

	//=====================주문 상세 내역 가져오기=====================
	public OrderBean selectOrderDetail(@Param("id") String id, @Param("order_idx") String order_idx); 

	//=======================(지선)멤버 포인트 업데이트=======================
	public boolean updateMemberPoint(@Param("id") String id, @Param("point") int point); 
	
}
