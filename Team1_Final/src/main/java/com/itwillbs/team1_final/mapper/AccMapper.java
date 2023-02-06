package com.itwillbs.team1_final.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.itwillbs.team1_final.vo.AccVO;

public interface AccMapper {

	int insertAcc(AccVO acc);

	List<AccVO> accList();

	AccVO accView(String BUSINESS_NO);

	int accDelete(AccVO acc);

	int accModify(AccVO acc);

}
