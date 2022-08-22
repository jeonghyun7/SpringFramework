package com.spring.pro27.member.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.pro27.member.vo.MemberVO;

public interface MemberService {

	public List listMembers() throws DataAccessException;
	public int addMember(MemberVO memberVO) throws DataAccessException;
	public int removeMember(String id) throws DataAccessException;
	
	
}