package com.itwillbs.team1.mapper;

import java.util.ArrayList;

import com.itwillbs.team1.vo.OrderBean;

public interface OrderMapper {

	//=====================주문 내역 저장=====================
	public boolean insertOrderList(OrderBean order); 

	//=====================주문 내역 가져오기=====================
	public ArrayList<OrderBean> selectOrderList(String id, String period); 

	//=====================주문 상세 내역 가져오기=====================
	public OrderBean selectOrderDetail(String id, String order_idx); 

	//=======================(지선)멤버 포인트 업데이트=======================
	public boolean updateMemberPoint(String id, int point); 
	
}
