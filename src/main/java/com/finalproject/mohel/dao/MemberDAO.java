package com.finalproject.mohel.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.finalproject.mohel.vo.MemberVO;
import com.finalproject.mohel.vo.PagingVO;

@Mapper 
@Repository
public interface MemberDAO {
	public int searchNickname(String nickname);
	public int searchEmail(String email);
	public int insertMember(MemberVO vo);
	public MemberVO selectMember(MemberVO vo);
	//최근 방문일 저장용
	public void setRecentvisit(MemberVO vo);
	//관리자 페이지 리스트
    public List<MemberVO> memberList(PagingVO pVO);
    public int totalRecord(PagingVO pVO);
	//관리자 페이지 회원수정 및 정지
    public void adminUpdate(MemberVO vo) throws Exception;
    //관리자 페이지 회원정보 상세보기
    public MemberVO adminView(String nickname);
    //관리자 페이지 회원 삭제
    public int adminDelete(String nickname);
	public int updatePwd(MemberVO vo);
	//관리자 페이지 통계 관련
    public List<Map<String,String>> genderCount();
    public Map<String,String> ageCount();
    public Map<String,String> joinCount(String currentTime);
}
