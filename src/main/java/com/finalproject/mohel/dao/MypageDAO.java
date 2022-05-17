package com.finalproject.mohel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.finalproject.mohel.vo.BoardVO;
import com.finalproject.mohel.vo.MemberVO;

@Mapper 
@Repository
public interface MypageDAO {
	public int updateUserInfo(MemberVO vo);
	public List<BoardVO> selectMyBoardList(String nickname, String category);
}
