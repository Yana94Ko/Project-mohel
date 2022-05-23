package com.finalproject.mohel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalproject.mohel.dao.MyFoodDAO;
import com.finalproject.mohel.vo.BoardVO;
import com.finalproject.mohel.vo.MyFoodVO;
import com.finalproject.mohel.vo.ReplyVO;

@Service
public class MyFoodServiceImpl implements MyFoodService {
	@Autowired
	MyFoodDAO dao;

	@Override
	public List<MyFoodVO> getMyFood(String date, String nickname) {
		return dao.getMyFood(date, nickname);
	}

	@Override
	public int getRecommendCalorie(String nickname) {
		return dao.getRecommendCalorie(nickname);
	}

	@Override
	public int updateMyFood(String nickname, String date, MyFoodVO vo) {
		return dao.updateMyFood(nickname, date, vo);
	}

	@Override
	public int insertMyFood(String nickname, MyFoodVO vo, String date) {
		return dao.insertMyFood(nickname, vo, date);
	}

	@Override
	public int everyFoodWriteOk(BoardVO bvo) {
		return dao.everyFoodWriteOk(bvo);
	}

	@Override
	public List<BoardVO> everyFoodList(int pageNum) {
		return dao.everyFoodList(pageNum);
	}

	@Override
	public List<String> bestUser() {
		return dao.bestUser();
	}

	@Override
	public BoardVO everyFoodView(int no) {
		return dao.everyFoodView(no);
	}

	@Override
	public int hitUp(int no) {
		return dao.hitUp(no);
	}
	
	@Override
	public List<ReplyVO> everyFoodReply(int no) {
		return dao.everyFoodReply(no);
	}

	@Override
	public int everyFoodReplyOk(int no, String nickname, String contents) {
		return dao.everyFoodReplyOk(no, nickname, contents);
	}

	@Override
	public int everyFoodDel(BoardVO bvo) {
		return dao.everyFoodDel(bvo);
	}

	@Override
	public int everyFoodReplyDel(int no) {
		return dao.everyFoodReplyDel(no);
	}

	@Override
	public int replyEditOk(ReplyVO rvo) {
		return dao.replyEditOk(rvo);
	}

	@Override
	public BoardVO getDBImg(int boardNo) {
		return dao.getDBImg(boardNo);
	}

	@Override
	public int updateBoardWithFile(BoardVO bvo) {
		return dao.updateBoardWithFile(bvo);
	}

	@Override
	public int updateBoardNoFile(BoardVO bvo) {
		return dao.updateBoardNoFile(bvo);
	}

}
