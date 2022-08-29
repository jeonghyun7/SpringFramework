package com.myboot03.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myboot03.member.vo.MemberVO;

@Mapper
@Repository("memberDAO")
public interface MemberDAO {
	
	public List<MemberVO> selectAllMemberList() throws DataAccessException;
	
	public int insertMember(MemberVO memberVO) throws DataAccessException;
	
	public int deleteMember(String id) throws DataAccessException;
	
	//수정했음
	public MemberVO selectMemberById(String id) throws DataAccessException;
	
	public int updateMember(MemberVO memberVO) throws DataAccessException;
	
	public MemberVO loginById(MemberVO memberVO) throws DataAccessException;
}
