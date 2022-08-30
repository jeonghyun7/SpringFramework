package com.myboot02.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myboot02.member.vo.TestVO;

@Mapper
@Repository("testDAO")
public interface TestDAO {
	
	public List<TestVO> listTests() throws DataAccessException;
	

}
