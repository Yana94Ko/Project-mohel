package com.finalproject.mohel.service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.finalproject.mohel.dao.MypageDAO;
import com.finalproject.mohel.vo.BoardVO;
import com.finalproject.mohel.vo.MemberVO;
import com.finalproject.mohel.vo.PagingVO;

@Service
public class MypageServiceImpl implements MypageService {

	@Inject
	MypageDAO dao;

	@Override
	public int updateUserInfo(MemberVO vo) {
		return dao.updateUserInfo(vo);
	}

	@Override
	public List<BoardVO> selectMyBoardList(String nickname, String category, PagingVO pVO) {
		return dao.selectMyBoardList(nickname, category, pVO);
	}

	@Override
	public int boardTotalRecord(String nickname, String category, PagingVO pVO) {
		return dao.boardTotalRecord(nickname, category, pVO);
	}

	@Override
	public List<HashMap<String, Object>> selectMyReplyList(String nickname, String category, PagingVO pVO) {
		return dao.selectMyReplyList(nickname, category, pVO);
	}

	@Override
	public int replyTotalRecord(String nickname, String category, PagingVO pVO) {
		return dao.replyTotalRecord(nickname, category, pVO);
	}
	
	

	
}