package com.itwillbs.team1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.itwillbs.team1.vo.AuthVO;
import com.itwillbs.team1.vo.MemberVO;

public interface MemberMapper {

	int insertMember(MemberVO member);

	int selectMemberId(String id);

	int selectMemberEmail(String email);

	int selectAuth(AuthVO auth);

	void updateCert(AuthVO auth);

	void insertCert(AuthVO auth);

	String selectCertNum(String id);

	void deleteCert(String id);
	
	String selectPasswd(String member_id);

	MemberVO selectMemberInfo(String id);

	int updateMemberInfo(
			@Param("member") MemberVO member,
			@Param("newPasswd") String newPasswd);

	int deleteMember(String id);

	String selectMemberSearchId(MemberVO member);

	MemberVO selectNE(String id);

	void updateTempPasswd(
			@Param("member_id") String member_id,
			@Param("tempPasswd") String tempPasswd);

	List<MemberVO> selectMemberList(
							@Param("searchType") String searchType,
							@Param("keyword") String keyword,
							@Param("startRow") int startRow,
							@Param("listLimit") int listLimit);

	int selectMemberListCount(
			@Param("searchType") String searchType,
			@Param("keyword") String keyword);

}
