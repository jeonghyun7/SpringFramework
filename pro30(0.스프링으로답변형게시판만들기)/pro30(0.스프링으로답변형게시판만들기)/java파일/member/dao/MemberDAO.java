package com.myspring.pro30.member.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myspring.pro30.member.vo.MemberVO;



public interface MemberDAO {
	
	public List<MemberVO> selectAllMemberList() throws DataAccessException;
	
	public int insertMember(MemberVO memberVO) throws DataAccessException;
	
	public int deleteMember(String id) throws DataAccessException;
	
	//추가구현코드
	public MemberVO modMember(String id) throws DataAccessException;
	//추가구현코드
	public int updateMember(MemberVO memberVO) throws DataAccessException;
	
	public MemberVO loginById(MemberVO memberVO) throws DataAccessException;
}