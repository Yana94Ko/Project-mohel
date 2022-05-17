package com.finalproject.mohel.service;

import java.util.List;

import com.finalproject.mohel.vo.BoardVO;
import com.finalproject.mohel.vo.MemberVO;

public interface MypageService {

	public int updateUserInfo(MemberVO vo);
	public List<BoardVO> selectMyBoardList(String nickname, String category);
}
