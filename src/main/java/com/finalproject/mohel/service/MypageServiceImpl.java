package com.finalproject.mohel.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.finalproject.mohel.dao.MypageDAO;
import com.finalproject.mohel.vo.MemberVO;

@Service
public class MypageServiceImpl implements MypageService {

	@Inject
	MypageDAO dao;

	@Override
	public int updateUserInfo(MemberVO vo) {
		return dao.updateUserInfo(vo);
	}
}
