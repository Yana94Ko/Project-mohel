package com.finalproject.mohel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.finalproject.mohel.vo.ReplyVO;
@Mapper
@Repository
public interface ReplyDAO {
	//댓글 등록
	public int replyWrite(ReplyVO vo);
	//댓글 목록
	public List<ReplyVO> replyList(int no);
	//댓글 수정
	public int replyEdit(ReplyVO vo);
	//댓글 삭제
	public int replyDel(int boardNo, String nickname);
}
