package com.itwillbs.team1_final.svc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.team1_final.mapper.InMapper;
import com.itwillbs.team1_final.vo.AccVO;
import com.itwillbs.team1_final.vo.HrVO;
import com.itwillbs.team1_final.vo.InListVO;
import com.itwillbs.team1_final.vo.InPdVO;
import com.itwillbs.team1_final.vo.InVO;
import com.itwillbs.team1_final.vo.PdVO;
import com.itwillbs.team1_final.vo.StockVO;

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
	
	// 입고 예정 목록
	public ArrayList<InListVO> getScheduleList() { 
		ArrayList<InListVO> inList = mapper.selectSchedule();

		for(int i = 0; i < inList.size(); i++) {
			
			int nameCount = mapper.selectCountName(inList.get(i).getIN_SCHEDULE_CD());
			if(nameCount == 1) {
				
				if(!inList.get(i).getSIZE_DES().equals("")) {
					String product_size = inList.get(i).getPRODUCT_NAME() + "[" + inList.get(i).getSIZE_DES() + "]";
					inList.get(i).setPRODUCT_NAME(product_size);
				}
				
			}else {
				
				if(!inList.get(i).getSIZE_DES().equals("")) {
					String product_size = inList.get(i).getPRODUCT_NAME() + "[" + inList.get(i).getSIZE_DES() + "] 외 " + (nameCount - 1) + "건";
					inList.get(i).setPRODUCT_NAME(product_size);
				}
			}
		}
		
		for(int i = 0; i < inList.size(); i++) {
			if(inList.get(i).getIN_COMPLETE().equals("0")) {
				String status = "종결";
				inList.get(i).setIN_COMPLETE(status);
			}else if(inList.get(i).getIN_COMPLETE().equals("1")) {
				String status = "취소";
				inList.get(i).setIN_COMPLETE(status);
			}
			
		}
		return inList;
	}
	
	// 입고 예정 목록 ( 전체 / 진행중 / 완료)
	public ArrayList<InListVO> getScheduleList(String keyword) {
		ArrayList<InListVO> inList = mapper.selectScheduleStatus(keyword);

		for(int i = 0; i < inList.size(); i++) {
			int nameCount = mapper.selectCountName(inList.get(i).getIN_SCHEDULE_CD());
			if(nameCount == 1) {
				
				if(!inList.get(i).getSIZE_DES().equals("")) {
					String product_size = inList.get(i).getPRODUCT_NAME() + "[" + inList.get(i).getSIZE_DES() + "]";
					inList.get(i).setPRODUCT_NAME(product_size);
				}
				
			}else {
				
				if(!inList.get(i).getSIZE_DES().equals("")) {
					String product_size = inList.get(i).getPRODUCT_NAME() + "[" + inList.get(i).getSIZE_DES() + "] 외 " + (nameCount - 1) + "건";
					inList.get(i).setPRODUCT_NAME(product_size);
				}
			}
		}
		
		for(int i = 0; i < inList.size(); i++) {
			if(inList.get(i).getIN_COMPLETE().equals("0")) {
				String status = "종결";
				inList.get(i).setIN_COMPLETE(status);
			}else if(inList.get(i).getIN_COMPLETE().equals("1")) {
				String status = "취소";
				inList.get(i).setIN_COMPLETE(status);
			}
			
		}
		return inList;
	}
	
	// 입고처리 진행상태
	public ArrayList<InPdVO> getProgress(String keyword) {
		return mapper.selectProductProgress(keyword);
	}
	
	// 입고 예정 수정 폼
	public ArrayList<InVO> getinProduct(String keyword) {
		ArrayList<InVO> proList = new ArrayList<InVO>();
		// 입고 예정 조회
		proList = mapper.selectInProduct(keyword);
		
		return proList;
	}
	
	// 입고 예정 목록 종결 / 취소 변환
	public int modifyComplete(String keyword, String com_status) {
		return mapper.updateCom(keyword, com_status);
	}
	
	// 입고 처리 목록
	public ArrayList<InListVO> getProgressList() {
		ArrayList<InListVO> inList = mapper.selectProgressList();
		
		for(int i = 0; i < inList.size(); i++) {
			if(!inList.get(i).getSIZE_DES().equals("")) {
				String product_size = inList.get(i).getPRODUCT_NAME() + "[" + inList.get(i).getSIZE_DES() + "]";
				inList.get(i).setPRODUCT_NAME(product_size);
			}
		}
		return inList;
	}

	public InPdVO getProductInfo(String product_cd, String product_name) {
		InPdVO productInfo = mapper.selectProductInfo(product_cd, product_name);
		
		// 입고 예정 코드 다시 날짜(String)으로 바꿔주기
		String pro_cd = productInfo.getIN_PD_SCHEDULE_CD();
		productInfo.setIN_PD_SCHEDULE_CD(pro_cd.split("-")[0]);

		return productInfo;
		
	}

	public int modifyProduct(String product_cd, String product_name, InPdVO product) { // 입고 예정 수정
		mapper.updateIncoming(product_cd, product);
		int updateCount = mapper.updateIncomingProduct(product_cd, product_name, product);
		
		return updateCount;
	}

	public int createStockCD() {
		int stock_cd = mapper.selectStockCd();
		stock_cd += 1;
		
		return stock_cd;
	}

	public ArrayList<InPdVO> getstockList(String keyword) { // 재고번호 조회
		ArrayList<InPdVO> stockList = mapper.selectStock(keyword);
		
		for(int i = 0; i < stockList.size(); i++) {
			stockList.get(i).setWh_name(stockList.get(i).getWh_name()+ "_" +stockList.get(i).getWh_area());
		}
		
		for(int i = 0; i < stockList.size(); i++) {
			if(!stockList.get(i).getSIZE_DES().equals("")) {
				String product_size = stockList.get(i).getPRODUCT_NAME() + "[" + stockList.get(i).getSIZE_DES() + "]";
				stockList.get(i).setPRODUCT_NAME(product_size);
			}
		}
		
		return stockList;
	}

	

	public boolean updateQTY(StockVO stock) {
		boolean isSuccess=false;
		int updateCount = mapper.updateQty(stock);
		if(updateCount>0) {
			isSuccess=true;
		}
		int qty = mapper.select_sched_qty(stock);
		stock.setIN_QTY(qty);
		System.out.println("입고 예정 수량 : " + stock.getIN_SCHEDULE_QTY());
		System.out.println("입고 수량 : " + stock.getIN_QTY());
		
		if(stock.getIN_QTY().equals(stock.getIN_SCHEDULE_QTY())) {
			System.out.println("둘이 같음");
			mapper.updateComplete(stock);
		}else {
			System.out.println("다름");
		}
		return isSuccess;
	}

	

	



	
	
}
