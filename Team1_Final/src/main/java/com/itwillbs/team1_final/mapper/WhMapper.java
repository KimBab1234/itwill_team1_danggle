package com.itwillbs.team1_final.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.itwillbs.team1_final.vo.WhVO;

public interface WhMapper {


	List<WhVO> selectWhList(@Param("searchType")String searchType,
			@Param("keyword")String keyword,
			@Param("startRow")int startRow,
			@Param("listLimit")int listLimit);

	int selectWhListCount(@Param("searchType")String searchType,
			@Param("keyword")String keyword);

	int selectWhCode(String WH_CD);

	int insertWhCode(WhVO wh);

	WhVO selectWh(String wH_CD);

	int deleteWh(String wH_CD);

	int updateWh(WhVO wh);

	int insertWhArea(WhVO wh);

	List<WhVO> selectWhArea(@Param("wH_CD") String wH_CD,@Param("wH_AREA") String wH_AREA,@Param("wH_AREA_CD") String wH_AREA_CD);

	List<WhVO> selectLocationList(@Param("wH_AREA_CD") String wH_AREA_CD,@Param("wH_LOC_IN_AREA") String wH_LOC_IN_AREA,
									@Param("wH_LOC_IN_AREA_CD") String wH_LOC_IN_AREA_CD);

	int insertWhLocation(WhVO wh);

}
