package com.finalproject.mohel.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalproject.mohel.dao.ReplyDAO;
import com.finalproject.mohel.vo.ReplyVO;
@Service
public class ReplyServiceImpl implements ReplyService{
	
	@Autowired
	ReplyDAO dao;

	@Override
	public int replyWrite(ReplyVO vo) {
		return dao.replyWrite(vo);
	}

	@Override
	public List<ReplyVO> replyList(int no) {
		return dao.replyList(no);
	}

	@Override
	public int replyEdit(ReplyVO vo) {
		return dao.replyEdit(vo);
	}

	@Override
	public int replyDel(int no, String nickname) {
		return dao.replyDel(no, nickname);
	}

}
