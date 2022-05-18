package com.finalproject.mohel.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.finalproject.mohel.vo.BoardVO;
import com.finalproject.mohel.vo.MemberVO;
import com.finalproject.mohel.vo.PagingVO;

@Mapper 
@Repository
public interface MypageDAO {
	
	public int updateUserInfo(MemberVO vo);
	public List<BoardVO> selectMyBoardList(String nickname, String category, PagingVO pVO);
	public int boardTotalRecord(String nickname, String category, PagingVO pVO);
	public List<HashMap<String, Object>> selectMyReplyList(String nickname, String category, PagingVO pVO);
	public int replyTotalRecord(String nickname, String category, PagingVO pVO);
}
