package com.itwillbs.team1.svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.team1.mapper.WishMapper;
import com.itwillbs.team1.vo.WishlistVO;

@Service
public class WishService {

	@Autowired
	private WishMapper mapper;

	public int registWish(String product_idx, String sId) {
		return mapper.insertWish(product_idx, sId);
	}

	public int getWishlistCount(String sId, String searchType, String keyword) {
		return mapper.selectWishlistCount(sId, searchType, keyword);
	}

	public List<WishlistVO> wishlist(String sId, String searchType, String keyword, int startRow, int listLimit) {
		return mapper.selectWishlist(sId, searchType, keyword, startRow, listLimit);
	}

	public int cancelWish(String sId, String[] listArr) {
		return mapper.deleteWish(sId, listArr);
	}

	
	
	
}

