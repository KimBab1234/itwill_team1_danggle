package com.itwillbs.team1_final.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.itwillbs.team1_final.vo.HrVO;

@Transactional
public interface HrMapper {

	public int selectEmpIdx();
	public String selectEmpEmail(String empNo);
	public int insertEmp(HrVO newEmp);
	public ArrayList<HrVO> selectGradeInfo();
	public ArrayList<HrVO> selectDepartSearch(String keyword);
	public int selectEmpListCount(@Param("search") String search);
	public ArrayList<HrVO> selectEmpList(@Param("search") String search,@Param("startRow") int startRow,@Param("listLimit") int listLimit);
	public HrVO selectEmpDetail(String empNo);
	public int updateEmp(HrVO newEmp);
	public int insertTempPass(@Param("email") String email, @Param("pass") String pass);
	public HrVO selectEmpPassPriv(@Param("loginType") String loginType, @Param("loginData") String loginData);
	
}
