package com.spring.member.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.member.dao.MemberDAO;
import com.spring.member.vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	
	private MemberDAO memberDAO;
	public void setMemberDAO(MemberDAO memberDAO)
	{
		this.memberDAO = memberDAO;
	}
	@Override
	public List listMembers() throws DataAccessException {
		List membersList = null;
		membersList = memberDAO.selectAllMemberList();
		return membersList;
	}
	@Override
	public int addMember(MemberVO memberVO) throws DataAccessException {
		return memberDAO.insertMember(memberVO);
	}
	@Override
	public int removeMember(String id) throws DataAccessException {
		return memberDAO.deleteMember(id);
	}
	

}