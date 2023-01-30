package com.itwillbs.team1_final.svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.team1_final.mapper.ItemMapper;
import com.itwillbs.team1_final.vo.ItemVO;

@Service
public class ItemService {

	@Autowired
	private ItemMapper mapper;
	
	//품목 등록
	public int registItem(ItemVO item) {
		return mapper.insertItem(item);
	}

	//품목 목록 조회
	public List<ItemVO> getItemList(String searchType, String keyword, int startRow, int listLimit) {
		return mapper.selectItemList(searchType, keyword, startRow, listLimit);
	}

}
