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

	// [ 출고 예정 ]
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

	// 출고 관련 등록
	public int registOutSchAndPd(OutSchVo outSch) {
		// 출고 예정 등록
		mapper.insertOutSch(outSch);
		
		// 출고예정품목 등록
		String out_schedule_cd = outSch.getOUT_SCHEDULE_CD();
		List<Integer> codeList = outSch.getPRODUCT_CD_Arr();
		List<Integer> qtyList = outSch.getOUT_SCHEDULE_QTY_Arr();
		List<Date> dateList = outSch.getPD_OUT_DATE_Arr();
		List<String> remarkList = outSch.getPD_REMARKS_Arr();
		List<Integer> stockList = outSch.getSTOCK_CD_Arr();
		
		int count = 0;
		
		for(int i = 0; i < dateList.size(); i++) {
			OutPdVO product = new OutPdVO();
			
			if(dateList.get(i).toString().equals("1900-01-01")) {
				break;
			}
			
			product.setPRODUCT_CD(codeList.get(i));
			product.setOUT_SCHEDULE_CD(out_schedule_cd);
			product.setOUT_SCHEDULE_QTY(qtyList.get(i));
			product.setPD_OUT_DATE(dateList.get(i));
			product.setPD_REMARKS(remarkList.get(i));
			product.setSTOCK_CD(stockList.get(i));
			
			count += mapper.insertOutSchPd(product);
		}
		return count;
	}

	// 출고 예정 품목 목록 조회
	public List<OutSchVo> searchOutSchList(String keyword) {
		
		List<OutSchVo> outSch = mapper.selectOutSch(keyword);
		
		for(int i = 0; i < outSch.size(); i++) {
			// 유형
			if(outSch.get(i).getOUT_TYPE_NAME().equals("1")) {
				outSch.get(i).setOUT_TYPE_NAME("발주서");
			} else {
				outSch.get(i).setOUT_TYPE_NAME("구매");
			}
			
			// 종결여부
			if(outSch.get(i).getOUT_COMPLETE().equals("0")) {
				outSch.get(i).setOUT_COMPLETE("종결");
			} else {
				outSch.get(i).setOUT_COMPLETE("취소");
			}
			
			// 품목명[규격] ex)코카콜라 500ml 외 1건
			String outSchCd = outSch.get(i).getOUT_SCHEDULE_CD();
			
			int extraPdCount = mapper.selectExtraPdCount(outSchCd);
			if(extraPdCount > 1) {
				OutSchVo pdInfo = mapper.selectOutPdName(outSchCd);
				outSch.get(i).setPRODUCT_NAME(pdInfo.getPRODUCT_NAME() + "[" + pdInfo.getSIZE_DES() + "]" + " 외 " + (extraPdCount-1) + "건");
			} else {
				OutSchVo pdInfo = mapper.selectOutPdName(outSchCd);
				outSch.get(i).setPRODUCT_NAME(pdInfo.getPRODUCT_NAME() + "[" + pdInfo.getSIZE_DES() + "]");
			}
			
		}
		
		return outSch;
	}
	
	// 출고 예정 종결여부 변경
	public int modifyCom(String outSchCd, String comStatus) {
		return mapper.outUpdateCom(outSchCd, comStatus);
	}
	
	// 출고 예정 품목 개별 목록 조회
	public List<OutPdVO> getProgress(String outSchCdList) {
		return mapper.selectPdInfo(outSchCdList);
	}

	
	// [ 출고 처리 ]
	// 출고 처리 목록 조회
	public List<OutSchVo> searchOutProList(String keyword) {
		List<OutSchVo> outProSch = mapper.selectOutProSch(keyword);
		
		for(int i =0; i < outProSch.size(); i++) {
			// 품목명[규격] ex)코카콜라 [500ml]
			String pdName = outProSch.get(i).getPRODUCT_NAME();
			String sizeD = outProSch.get(i).getSIZE_DES();
			
			outProSch.get(i).setSIZE_DES(pdName + "[" + sizeD + "]");
		}
		return outProSch;
	}

	// 출고 예정 수정 품목 조회
	public OutSchVo searchOutUpdatePd(String pd_outSch_cd, String product_name) {
		OutSchVo out = mapper.selectOutUpdatePd(pd_outSch_cd, product_name);
		
		// PD_OUT_SCHEDULE_CD 를 오늘일자만 추출
		String pd_Sch_cd = out.getPD_OUT_SCHEDULE_CD();
		out.setPD_OUT_SCHEDULE_CD(pd_Sch_cd.split("-")[0]);
		
		return out;
	}

	// 출고 예정 품목 수정
	public int modifyOutSchPd(OutSchVo outSchPd) {
		return mapper.updateOutSchPd(outSchPd);
	}	
	
}