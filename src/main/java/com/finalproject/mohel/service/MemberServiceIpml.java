package com.finalproject.mohel.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.finalproject.mohel.dao.MemberDAO;
import com.finalproject.mohel.vo.MemberVO;

@Service
public class MemberServiceIpml implements MemberService {
	
	@Inject
	MemberDAO dao;

	@Override
	public int searchNickname(String nickname) {
		return dao.searchNickname(nickname);
	}

	@Override
	public int searchEmail(String email) {
		return dao.searchEmail(email);
	}

	@Override
	public int insertMember(MemberVO vo) {
		return dao.insertMember(vo);
	}

	@Override
	public MemberVO selectMember(MemberVO vo) {
		return dao.selectMember(vo);
	}

	@Override
	public int updatePwd(MemberVO vo) {
		return dao.updatePwd(vo);
	}
	
}
