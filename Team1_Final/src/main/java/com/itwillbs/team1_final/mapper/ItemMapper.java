package com.itwillbs.team1_final.mapper;

import java.util.List;

import com.itwillbs.team1_final.vo.ItemVO;

public interface ItemMapper {

	int insertItem(ItemVO item);

	List<ItemVO> selectItemList(String searchType, String keyword, int startRow, int listLimit);

}
