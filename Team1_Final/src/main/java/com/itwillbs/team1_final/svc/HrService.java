package com.itwillbs.team1_final.svc;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwillbs.team1_final.mapper.HrMapper;
import com.itwillbs.team1_final.vo.HrVO;

@Service
@Transactional
public class HrService {

	@Autowired
	HrMapper mapper;

	public int getEmpIdx() {
		return mapper.selectEmpIdx();
	}
	
	public int registEmp(HrVO newEmp) {
		return mapper.insertEmp(newEmp);
	}
	
	public String getEmpEmail(String empNo) {
		return mapper.selectEmpEmail(empNo);
	}
	
	public ArrayList<HrVO> getGradeInfo() {
		return mapper.selectGradeInfo();
	}
	public ArrayList<HrVO> getDepartSearch(String keyword) {
		return mapper.selectDepartSearch(keyword);
	}
	public ArrayList<HrVO> getEmpList(String workType, String searchType, String keyword, int startRow, int listLimit) {

		String search = "WHERE WORK_CD="+workType;
		if(searchType!=null && !searchType.equals("") ) {
			search += " AND " + searchType + " LIKE '%" + keyword + "%'";
		}

		return mapper.selectEmpList(search,startRow,listLimit);
	}
	public int getEmpListCount(String searchType, String keyword, String workType) {

		String search = "WHERE WORK_CD="+workType;
		if(searchType!=null && !searchType.equals("") ) {
			search += " AND " + searchType + " LIKE '%" + keyword + "%'";
		}

		return mapper.selectEmpListCount(search);
	}
	public HrVO getEmpDetail(String empNo) {
		return mapper.selectEmpDetail(empNo);
	}

	public int updateEmp(HrVO newEmp) {
		return mapper.updateEmp(newEmp);
	}

	public int insertTempPass(String email, String Pass) {
		return mapper.insertTempPass(email, Pass);
	}

	public HrVO getLoginEmpInfo(String loginType, String loginData) {
		return mapper.selectEmpPassPriv(loginType, loginData );
	}
	
	
	
	

}
