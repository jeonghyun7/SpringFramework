package com.spring.pro27.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.pro27.member.dao.MemberDAO;
import com.spring.pro27.member.vo.MemberVO;

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
/*수정하기 구현은 아직.	
	public MemberVO selectMemberById(String id) throws DataAccessException {
		MemberVO memberVO = null;
		memberVO = memberDAO.selectMemberById(id);
		return memberVO;
	}
*/	
	@Override
	public int addMember(MemberVO memberVO) throws DataAccessException {
		return memberDAO.insertMember(memberVO);
	}
	
	
/*수정하기 구현은 아직.	
	@Override
	public int modMember(MemberVO memberVO) throws DataAccessException {
		
		return memberDAO.updateMember(memberVO);
	}
*/
	@Override
	public int removeMember(String id) throws DataAccessException {
		return memberDAO.deleteMember(id);
	}
	@Override
	public MemberVO login(MemberVO memberVO) throws Exception {
		
		return memberDAO.loginById(memberVO);
	}
	
	

}