package com.finalproject.mohel.service;

import java.util.HashMap;
import java.util.List;

import com.finalproject.mohel.vo.BoardVO;
import com.finalproject.mohel.vo.MemberVO;
import com.finalproject.mohel.vo.PagingVO;

public interface MypageService {

	public int updateUserInfo(MemberVO vo);
	public List<BoardVO> selectMyBoardList(String nickname, String category, PagingVO pVO);
	public int boardTotalRecord(String nickname, String category, PagingVO pVO);
	public List<HashMap<String, Object>> selectMyReplyList(String nickname, String category, PagingVO pVO);
	public int replyTotalRecord(String nickname, String category, PagingVO pVO);
	public List<HashMap<String, String>> selectMyExercise(String nickname, PagingVO pVO);
	public int myExerciseTotalRecord(String nickname, PagingVO pVO);
	public int deleteMember(MemberVO vo);
}
