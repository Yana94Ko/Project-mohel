package com.finalproject.mohel.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.finalproject.mohel.dao.MemberDAO;

@Service
public class MemberServiceIpml implements MemberService {
	
	@Inject
	MemberDAO dao;

	@Override
	public int searchNickname(String nickname) {
		return dao.searchNickname(nickname);
	}
	
}
