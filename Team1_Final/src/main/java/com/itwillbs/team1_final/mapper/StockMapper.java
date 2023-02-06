package com.itwillbs.team1_final.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.itwillbs.team1_final.vo.StockVO;
import com.itwillbs.team1_final.vo.WhVO;

@Transactional
public interface StockMapper {

	public int selectStockListCount(@Param("search") String search);
	public ArrayList<StockVO> selectStockList(@Param("search") String search,@Param("startRow") int startRow,@Param("listLimit") int listLimit);
	public StockVO selectStockDetail(String stockNo);
	public ArrayList<WhVO> selectWhList(@Param("search")String search,@Param("startRow") int startRow, @Param("listLimit")int listLimit);
	public int insertStockHistory(StockVO stock);
	public int insertStock(StockVO stock);
	public int selectNewStockCD();
	
}
