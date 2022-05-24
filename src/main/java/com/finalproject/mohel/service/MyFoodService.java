package com.finalproject.mohel.service;

import java.util.List;

import com.finalproject.mohel.vo.BoardVO;
import com.finalproject.mohel.vo.MyFoodVO;
import com.finalproject.mohel.vo.ReplyVO;

import com.finalproject.mohel.vo.PagingVO;

public interface MyFoodService {
	public List<MyFoodVO> getMyFood(String date, String nickname);
	public int getRecommendCalorie(String nickname);
	public int updateMyFood(String nickname, String date, MyFoodVO vo);
	public int insertMyFood(String nickname, MyFoodVO vo, String date);
	public int everyFoodWriteOk(BoardVO bvo);
	public List<BoardVO> everyFoodList(int pageNum);
	public List<String> bestUser();
	public BoardVO everyFoodView(int no);
	public int hitUp(int no);
	public List<ReplyVO> everyFoodReply(int no);
	public int everyFoodReplyOk(int no, String nickname, String contents);
	public int everyFoodDel(BoardVO bvo);
	public int everyFoodReplyDel(int no);
	public int replyEditOk(ReplyVO rvo);
	public BoardVO getDBImg(int boardNo);
	public int updateBoardWithFile(BoardVO bvo);
	public int updateBoardNoFile(BoardVO bvo);
	public List<MyFoodVO> adminGetMyFood( PagingVO pVO);
    public int totalRecord(PagingVO pVO);
    public void adminMyFoodDel(int no);
    public List<BoardVO> everyFoodForMain();
}
