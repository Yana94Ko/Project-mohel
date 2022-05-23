package com.finalproject.mohel.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.finalproject.mohel.dao.MemberDAO;
import com.finalproject.mohel.vo.MemberVO;
import com.finalproject.mohel.vo.PagingVO;

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
	public void setRecentvisit(MemberVO vo) {
		dao.setRecentvisit(vo);
	}

	@Override
	public MemberVO selectMember(MemberVO vo) {
		return dao.selectMember(vo);
	}

	@Override
	public List<MemberVO> memberList(PagingVO pVO) {
		return dao.memberList(pVO);
	}

	@Override
	public int totalRecord(PagingVO pVO) {
		return dao.totalRecord(pVO);
	}

	@Override
	public void adminUpdate(MemberVO vo) throws Exception {
		dao.adminUpdate(vo);
	}

	@Override
	public MemberVO adminView(String nickname) {
		return dao.adminView(nickname);
	}

	@Override
	public int adminDelete(String nickname) {
		return dao.adminDelete(nickname);
	}
	public int updatePwd(MemberVO vo) {
		return dao.updatePwd(vo);
	}
}
