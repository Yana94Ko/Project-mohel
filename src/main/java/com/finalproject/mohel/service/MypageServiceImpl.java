package com.finalproject.mohel.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.finalproject.mohel.dao.MypageDAO;
import com.finalproject.mohel.vo.BoardVO;
import com.finalproject.mohel.vo.MemberVO;

@Service
public class MypageServiceImpl implements MypageService {

	@Inject
	MypageDAO dao;

	@Override
	public int updateUserInfo(MemberVO vo) {
		return dao.updateUserInfo(vo);
	}

	@Override
	public List<BoardVO> selectMyBoardList(String nickname, String category) {
		return dao.selectMyBoardList(nickname, category);
	}
}