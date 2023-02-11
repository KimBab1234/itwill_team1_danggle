package com.itwillbs.team1_final.mapper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.itwillbs.team1_final.vo.AccVO;
import com.itwillbs.team1_final.vo.HrVO;
import com.itwillbs.team1_final.vo.InListVO;
import com.itwillbs.team1_final.vo.InPdVO;
import com.itwillbs.team1_final.vo.InVO;
import com.itwillbs.team1_final.vo.PdVO;
import com.itwillbs.team1_final.vo.StockVO;

public interface InMapper {

	ArrayList<HrVO> selectHrList(String keyword); // 사원 검색

	ArrayList<PdVO> selectProductList(String keyword); // 품목 검색

	List<AccVO> selectAcc(String keyword); // 거래처 검색

	int selectToday(String today); // 입고예정코드 조회

	void insertIncoming(InVO in); // 입고예정등록

	int insertIncomingProduct(InPdVO product); // 입고예정 상품 등록

	ArrayList<InListVO> selectSchedule(); // 입고예정목록

	ArrayList<InListVO> selectScheduleStatus(String keyword); // 입고예정목록(전체/진행중/완료)

	ArrayList<InPdVO> selectProductProgress(String keyword); // 입고처리 진행상태

	ArrayList<InVO> selectInProduct(String keyword); // 입고 예정 조회

	int selectCountName(String in_SCHEDULE_CD); // 이름 만들 때 

	int updateCom(@Param("keyword") String keyword, @Param("com_status") String com_status); // 입고 예정 목록 종결 여부 전환

	ArrayList<InListVO> selectProgressList(); // 입고 처리 목록

	InPdVO selectProductInfo(@Param("product_cd") String product_cd, @Param("product_name") String product_name, @Param("IN_PD_DATE") Date IN_PD_DATE); // 입고 예정 상품 불러오기

	void updateIncoming(@Param("product_cd") String product_cd, @Param("product") InPdVO product); // 입고 예정 수정

	int updateIncomingProduct(@Param("product_cd") String product_cd, @Param("product_name") String product_name, @Param("product") InPdVO product);

	int selectStockCd(); // 재고번호 조회

	ArrayList<InPdVO> selectStock(String keyword); // 재고번호 검색 조회

	int updateQty(StockVO stock); // 입고 수량 수정

	void updateComplete(StockVO stock); // 입고 완료 상태변경

	int select_sched_qty(StockVO stock);  // 입고 수량 조회

	ArrayList<InListVO> selectProgressIngList(String keyword); // 입고 처리 키워드 목록 조회

	

}
