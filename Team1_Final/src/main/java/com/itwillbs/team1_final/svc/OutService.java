package com.itwillbs.team1_final.svc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.team1_final.mapper.OutMapper;
import com.itwillbs.team1_final.vo.AccVO;
import com.itwillbs.team1_final.vo.HrVO;
import com.itwillbs.team1_final.vo.OutPdVO;
import com.itwillbs.team1_final.vo.OutSchVo;
import com.itwillbs.team1_final.vo.PdVO;

@Service
public class OutService {

	@Autowired
	OutMapper mapper;

	// 거래처 검색
	public List<AccVO> searchAcc(String searchType, String keyword) {
		return mapper.selectAcc(searchType, keyword);
	}

	// 담당자 검색
	public ArrayList<HrVO> getHrList(String keyword) {
		return mapper.selectEmp(keyword);
	}

	// 품목 검색
	public ArrayList<PdVO> getPdList(String searchType, String keyword) {
		return mapper.selectPd(searchType, keyword);
	}

	// 출고예정코드 추가 또는 증가
	public int searchToday(String oUT_TODAY) {
		return mapper.selectToday(oUT_TODAY);
	}

	// 출고예정품목 등록
	public int registOutSchAndPd(OutSchVo outSch) {
		// 출고 예정 등록
		mapper.insertOutSch(outSch);
		
		// 출고예정품목 등록
		String out_schedule_cd = outSch.getOUT_SCHEDULE_CD();
		List<Integer> codeList = outSch.getPRODUCT_CD();
		List<Integer> qtyList = outSch.getOUT_SCHEDULE_QTY();
		List<Date> dateList = outSch.getPD_OUT_DATE();
		List<String> remarkList = outSch.getPD_REMARKS();
		
		int count = 0;
		
		for(int i = 0; i < dateList.size(); i++) {
			OutPdVO product = new OutPdVO();
			
			if(dateList.get(i).toString().equals("1900-01-01")) {
				break;
			}
			
			product.setPRODUCT_CD(codeList.get(i));
			product.setOUT_SCHEDULE_CD(out_schedule_cd);
			product.setOUT_SCHEDULE_QTY(qtyList.get(i));
			product.setOUT_DATE(dateList.get(i));
			product.setREMARKS(remarkList.get(i));
			
			count += mapper.insertOutSchPd(product);
		}
		return count;
	}

	public List<OutSchVo> searchOutSchList(String keyword) {
		List<OutSchVo> outSch = mapper.selectOutSch(keyword);
		
		String pdKeyword = "";
		String searchType = "";
		for(int i = 0; i < outSch.size(); i++) {
			String sEmp_num = outSch.get(i).getEMP_NUM();
			String sBusiness_no = outSch.get(i).getBUSINESS_NO();
			
			// 담당자 이름 조회
			List<HrVO> hrInfo = mapper.selectEmp(sEmp_num);
			String emp_name = hrInfo.get(1);
			outSch.get(i).setEMP_NAME(emp_name);

			// 품목 정보 조회
			List<PdVO> pdInfo = mapper.selectPd(searchType, pdKeyword);
			String product_name = pdInfo.get(1);
			String size_des = pdInfo.get(2);
			
			outSch.get(i).setPRODUCT_NAME(product_name);
			outSch.get(i).setSIZE_DES(size_des);
			
		}
		
		
		
		return outSch;
	}
	
	
}
