package com.itwillbs.team1_final.svc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.team1_final.mapper.InMapper;
import com.itwillbs.team1_final.vo.AccVO;
import com.itwillbs.team1_final.vo.HrVO;
import com.itwillbs.team1_final.vo.InPdVO;
import com.itwillbs.team1_final.vo.InVO;
import com.itwillbs.team1_final.vo.PdVO;

@Service
public class InService {
	@Autowired
	private InMapper mapper;
	
	// 사원 검색
	public ArrayList<HrVO> getHrList(String keyword) {
		return mapper.selectHrList(keyword);
	}
	
	// 품목 검색
	public ArrayList<PdVO> getProductList(String keyword) {
		return mapper.selectProductList(keyword);
	}
	
	// 거래처 검색
	public List<AccVO> searchAcc(String keyword) {
		return mapper.selectAcc(keyword);
	}

	// 입고예정코드 조회
	public int searchToday(String today) {
		return mapper.selectToday(today);
	}
	
	// 입고 예정 품목 등록
	public int regiIncoming(InVO in) {
		
		// 입고 예정 등록
		mapper.insertIncoming(in);
		String product_idx = in.getIN_SCHEDULE_CD();
		// 입고예정품목 등록
		List<String> nameArr = in.getPRODUCT_NAME();
		List<Integer> codeArr = in.getPRODUCT_CD();
		List<String> sizeArr = in.getSIZE_DES();
		List<Integer> qtyArr = in.getIN_SCHEDULE_QTY();
		List<Date> dateArr = in.getIN_PD_DATE();
		List<String> remarkArr = in.getIN_PD_REMARKS();
		
		int count = 0;
		
		for(int i = 0; i < dateArr.size(); i++) {
			InPdVO product = new InPdVO();
			
			if(dateArr.get(i).toString().equals("1900-01-01")) {
				break;
			}
			product.setPRODUCT_CD(codeArr.get(i));
			product.setPRODUCT_NAME(nameArr.get(i));
			product.setSIZE_DES(sizeArr.get(i));
			product.setIN_SCHEDULE_QTY(qtyArr.get(i));
			product.setIN_PD_SCHEDULE_CD(product_idx);
			product.setIN_PD_DATE(dateArr.get(i));
			product.setIN_PD_REMARKS(remarkArr.get(i));
			
			count += mapper.insertIncomingProduct(product);
			
			
		}
		
		return count;
	}

	
	
}
