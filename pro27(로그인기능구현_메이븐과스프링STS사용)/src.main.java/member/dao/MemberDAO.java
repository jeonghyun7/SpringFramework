package com.spring.pro27.member.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.pro27.member.vo.MemberVO;

public interface MemberDAO {
	
	public List<MemberVO> selectAllMemberList() throws DataAccessException;
	//public MemberVO selectMemberById(String id) throws DataAccessException;
	public int insertMember(MemberVO memberVO) throws DataAccessException;
	//public int updateMember(MemberVO memberVO) throws DataAccessException;
	public int deleteMember(String id) throws DataAccessException;
	
	public MemberVO loginById(MemberVO memberVO) throws DataAccessException;
}
