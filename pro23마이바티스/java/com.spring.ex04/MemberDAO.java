package com.spring.ex04;

import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.spring.ex01.MemberVO;

public class MemberDAO {

private static SqlSessionFactory sqlMapper=null;
	
	public static SqlSessionFactory getInstance() {
		if(sqlMapper==null) {
			try {
				String resource = "mybatis/SqlMapConfig.xml";
//MemberDAO의 각 메서드 호출 시 src/mybatis/SqlMapConfig.xml에서 설정정보를 읽은후 데이터베이스와의 연동을 준비합니다.
				Reader reader = Resources.getResourceAsReader(resource);
				sqlMapper = new SqlSessionFactoryBuilder().build(reader);
// 마이비티스를 이용하는 sqlMapper객체를 가져옵니다.
				reader.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return sqlMapper;
	}
	
	public List<MemberVO> selectAllMemberList() {
		sqlMapper=getInstance();
		SqlSession session=sqlMapper.openSession();
		List<MemberVO> memlist=null;
		memlist=session.selectList("mapper.member.selectAllMemberList");
		return memlist;
		//memlist의 값이 호출했던 서블릿의 dao.selectAllMemberList()메서드 위치로 반환
	}
	
	public MemberVO selectMemberById(String id) {
		sqlMapper=getInstance();
		SqlSession session=sqlMapper.openSession();
		MemberVO memberVO=(MemberVO)session.selectOne("mapper.member.selectMemberById", id);
		return memberVO;
	}
	
	public List<MemberVO> selectMemberByPwd(int pwd) {
		sqlMapper=getInstance();
		SqlSession session=sqlMapper.openSession();
		List<MemberVO> membersList = null;
		membersList=session.selectList("mapper.member.selectMemberByPwd", pwd);
		return membersList;
	}
	
	public int insertMember(MemberVO memberVO) {
		sqlMapper=getInstance();
		SqlSession session=sqlMapper.openSession();
		int result=0;
		result=session.insert("mapper.member.insertMember", memberVO);
		session.commit();
		return result;
	}
	
	public int insertMember2(Map<String,String> memberMap) {
		sqlMapper=getInstance();
		SqlSession session=sqlMapper.openSession();
		int result=session.insert("mapper.member.insertMember2", memberMap);
		session.commit();
		return result;
	}
	
	public int updateMember(MemberVO memberVO) {
		sqlMapper=getInstance();
		SqlSession session=sqlMapper.openSession();
		int result=session.update("mapper.member.updateMember", memberVO);
		session.commit();
		return result;
	}
	
	public int deleteMember(String id) {
		sqlMapper=getInstance();
		SqlSession session=sqlMapper.openSession();
		int result=0;
		result=session.delete("mapper.member.deleteMember", id);
		session.commit();
		return result;
		
	}
	
	public List<MemberVO> searchMember(MemberVO memberVO){
		sqlMapper=getInstance();
		SqlSession session=sqlMapper.openSession();
		List<MemberVO> list=session.selectList("mapper.member.searchMember", memberVO);
		return list;
	}
	
	public List<MemberVO> foreachSelect(List<String> nameList) {
		sqlMapper=getInstance();
		SqlSession session=sqlMapper.openSession();
		List<MemberVO> list=session.selectList("mapper.member.foreachSelect", nameList);
		return list;
	}
	
	public int foreachInsert(List<MemberVO> memList) {
		sqlMapper=getInstance();
		SqlSession session=sqlMapper.openSession();
		int result=session.insert("mapper.member.foreachInsert", memList);
		session.commit();
		return result;
	}
	
	public List<MemberVO> selectLike(String name){
		sqlMapper=getInstance();
		SqlSession session=sqlMapper.openSession();
		System.out.println(name);
		List<MemberVO> list=session.selectList("mapper.member.selectLike", name);
		return list;
	}
}

