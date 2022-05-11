package com.finalproject.mohel.service;

import java.util.List;

import com.finalproject.mohel.vo.BoardVO;
import com.finalproject.mohel.vo.PagingVO;

public interface BoardService {
	
	public int boardInsert(BoardVO vo);
	
	public List<BoardVO> boardList(String category , PagingVO pVO);
	//상세보기
	public BoardVO boardSelect(int no);
	//조회수 증가
	public void hit(int no);
	//글 수정
	public int boardUpdate(BoardVO vo);
	//글 삭제
	public int boardDelete(int no, String nickname);

	public int totalRecord(String category, PagingVO pVO);
}
