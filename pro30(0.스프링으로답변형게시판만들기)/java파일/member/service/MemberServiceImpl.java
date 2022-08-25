package com.myspring.pro30.member.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.pro30.member.dao.MemberDAO;
import com.myspring.pro30.member.vo.MemberVO;



@Service("memberService")
@Transactional(propagation = Propagation.REQUIRED)
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO memberDAO;

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
	
	//추가구현코드
	@Override
	public MemberVO modMember(String id) throws DataAccessException {
		
		return memberDAO.modMember(id);
	}
	
	//추가구현코드
	@Override
	public int updateMember(MemberVO memberVO) throws DataAccessException {
		
		return memberDAO.updateMember(memberVO);
	}
	
	
	@Override
	public MemberVO login(MemberVO memberVO) throws Exception {
		
		return memberDAO.loginById(memberVO);
	}
	
	

}
