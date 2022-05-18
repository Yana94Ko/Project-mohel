package com.finalproject.mohel.service;

import java.util.List;

import com.finalproject.mohel.vo.BoardVO;
import com.finalproject.mohel.vo.MemberVO;
import com.finalproject.mohel.vo.PagingVO;

public interface MypageService {

	public int updateUserInfo(MemberVO vo);
	public List<BoardVO> selectMyBoardList(String nickname, String category, PagingVO pVO);
	public int totalRecord(String nickname, String category, PagingVO pVO);
}