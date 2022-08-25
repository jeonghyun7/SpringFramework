package com.myspring.pro30.member.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myspring.pro30.member.vo.MemberVO;



public interface MemberService {

	public List listMembers() throws DataAccessException;
	
	public int addMember(MemberVO memberVO) throws DataAccessException;
	
	public int removeMember(String id) throws DataAccessException;
	
	public MemberVO login(MemberVO memberVO) throws Exception;
	
	//추가구현코드
	public MemberVO modMember(String id) throws DataAccessException;
	//추가구현코드
	public int updateMember(MemberVO memberVO) throws DataAccessException;
}
