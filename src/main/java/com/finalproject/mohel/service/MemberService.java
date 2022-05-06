package com.finalproject.mohel.service;

import com.finalproject.mohel.vo.MemberVO;

public interface MemberService {
	public int searchNickname(String nickname);
	public int searchEmail(String email);
	public int insertMember(MemberVO vo);
}
