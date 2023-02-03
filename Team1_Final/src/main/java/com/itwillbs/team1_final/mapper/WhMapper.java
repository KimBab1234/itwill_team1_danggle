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

	List<WhVO> selectWhArea(String WH_CD);

	int insertWhLocation(WhVO wh);

	List<WhVO> selectWhLocation(int WH_AREA_CD);

}
