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
	public ArrayList<StockVO> selectStockDetail(@Param("sql")String sql,@Param("startRow") int startRow,@Param("listLimit") int listLimit);
	public ArrayList<WhVO> selectWhList(@Param("search")String search,@Param("startRow") int startRow, @Param("listLimit")int listLimit);
	public int insertStockHistory(StockVO stock);
	public int insertStock(StockVO stock);
	public Integer isExistStock(@Param("PRODUCT_CD")int product_cd, @Param("WH_LOC_IN_AREA_CD")int WH_LOC_IN_AREA_CD);
	public int selectNewStockCD();
	public int updateOutStock(StockVO stock);
	public int updateOutSchStock(StockVO stock);
	
	
}
