package com.finalproject.mohel.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.finalproject.mohel.vo.MemberVO;

@Mapper 
@Repository
public interface MypageDAO {
	public int updateUserInfo(MemberVO vo);
}
