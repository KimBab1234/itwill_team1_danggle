package com.itwillbs.team1_final.svc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.team1_final.mapper.PdMapper;
import com.itwillbs.team1_final.vo.PdVO;

@Service
public class PdService {

	@Autowired
	private PdMapper mapper;
	
	//품목 등록
	public int registPd(PdVO product) {
		return mapper.insertPd(product);
	}

	//품목 목록 조회
	public List<PdVO> getPdList(String searchType, String keyword, int startRow, int listLimit) {
		
		String search = "";
		if(searchType!=null && !searchType.equals("") ) {
			search = "WHERE " + searchType + " LIKE '%" + keyword + "%'";
		}
		return mapper.selectPdList(searchType, keyword, startRow, listLimit, search);
	}

	// 품목 그룹(소) 선택
	public ArrayList<PdVO> getPd_group_bottom_Search(String keyword) {
		return mapper.selectPd_group_bottom_Search(keyword);
	}

	// 품목 구분 선택
	public ArrayList<PdVO> getPd_type_Search(String keyword) {
		return mapper.selectPd_type_Search(keyword);
	}

	// 거래처 선택
	public ArrayList<PdVO> getBusiness_no_Search(String keyword) {
		return mapper.selectBusiness_no_Search(keyword);
	}

	// 품목 그룹(소) 등록
	public int registPd_group_bottom(PdVO product) {
		return mapper.insertPd_group_bottom(product);
	}

	// 품목 그룹(대) 선택
	public ArrayList<PdVO> getPd_group_top_Search(String keyword) {
		return mapper.selectPd_group_top_Search(keyword);
	}

	// 품목 삭제
	public int removeProduct(int product_CD) {
		return mapper.deletePd(product_CD);
	}

	// 품목 수정 하기 전 품목 조회
	public PdVO getProduct(int PRODUCT_CD) {
		
		PdVO product = mapper.selectPd(PRODUCT_CD);
		product.setPRODUCT_IMAGE(product.getPRODUCT_IMAGE().split("_")[1]);
		return product;
		
	}

	public List<String> getImgList(String deleteProdArr) {
		return mapper.selectImgList(deleteProdArr);
	}

}
