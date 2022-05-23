package com.finalproject.mohel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.finalproject.mohel.vo.BoardVO;
import com.finalproject.mohel.vo.PagingVO;
@Mapper
@Repository
public interface BoardDAO {
	
	public int boardInsert(BoardVO vo);
	
	public BoardVO boardSelect(int no);
	
	public void hit(int no);
	
	public int boardUpdate(BoardVO vo);
	
	public int boardDelete(int no, String nickname);

	public List<BoardVO> boardList(String category , PagingVO pVO);

	public int totalRecord(String category, PagingVO pVO);
	
	//관리자 글삭제
	public void adminBoardDelete(int no);
	//관리자 전체 글 갯수
	public int adminTotalRecord(PagingVO pVO);
}
