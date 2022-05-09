package com.finalproject.mohel.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.finalproject.mohel.vo.MemberVO;

@Mapper 
@Repository
public interface MemberDAO {
	public int searchNickname(String nickname);
	public int searchEmail(String email);
	public int insertMember(MemberVO vo);
	public MemberVO selectMember(MemberVO vo);
}
