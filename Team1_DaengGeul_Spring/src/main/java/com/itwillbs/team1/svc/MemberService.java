package com.itwillbs.team1.svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.team1.mapper.MemberMapper;
import com.itwillbs.team1.vo.AuthVO;
import com.itwillbs.team1.vo.MemberVO;

@Service
public class MemberService {

	@Autowired
	private MemberMapper mapper;

	public int joinMember(MemberVO member) {
		return mapper.insertMember(member);
	}

	public int checkId(String id) {
		return mapper.selectMemberId(id);
	}

	public int checkEmail(String email) {
		return mapper.selectMemberEmail(email);
	}

	public int checkAuth(AuthVO auth) {
		return mapper.selectAuth(auth);
	}

	public void replaceCert(AuthVO auth) {
		mapper.updateCert(auth);
	}

	public void createCert(AuthVO auth) {
		mapper.insertCert(auth);
	}

	public String checkCertNum(String id) {
		return mapper.selectCertNum(id);
	}

	public void deleteAuth(String id) {
		mapper.deleteCert(id);
	}
	
	public String getPasswd(String member_id) {
		return mapper.selectPasswd(member_id);
	}

	public MemberVO getMemberInfo(String id) {
		return mapper.selectMemberInfo(id);
	}

	public int modifyMemberInfo(MemberVO member, String newPasswd) {
		return mapper.updateMemberInfo(member, newPasswd);
	}

	public int removeMember(String id) {
		return mapper.deleteMember(id);
	}

	public String searchId(MemberVO member) {
		return mapper.selectMemberSearchId(member);
	}

	public MemberVO SearchNE(String id) {
		return mapper.selectNE(id);
	}

	public void registTempPasswd(String member_id, String tempPasswd) {
		mapper.updateTempPasswd(member_id, tempPasswd);
	}

	public List<MemberVO> getMemberList(String searchType, String keyword, int startRow, int listLimit) {
		return mapper.selectMemberList(searchType, keyword, startRow, listLimit);
	}

	public int getMemberListCount(String searchType, String keyword) {
		return mapper.selectMemberListCount(searchType, keyword);
	}
	

}

