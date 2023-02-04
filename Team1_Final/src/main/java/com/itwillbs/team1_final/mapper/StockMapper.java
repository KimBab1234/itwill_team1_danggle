package com.itwillbs.team1_final.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.itwillbs.team1_final.vo.StockVO;

@Transactional
public interface StockMapper {

	public int selectStockListCount(@Param("search") String search);
	public ArrayList<StockVO> selectStockList(@Param("search") String search,@Param("startRow") int startRow,@Param("listLimit") int listLimit);
	public StockVO selectStockDetail(String stockNo);
	public int updateStock(@Param("stockNo") String stockNo);
	
}
