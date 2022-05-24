package com.finalproject.mohel.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.finalproject.mohel.dao.BoardDAO;
import com.finalproject.mohel.vo.BoardVO;
import com.finalproject.mohel.vo.PagingVO;

@Service
public class BoardServiceImpl implements BoardService {
	@Inject
	BoardDAO dao;
	@Override
	public int boardInsert(BoardVO vo) {
		return dao.boardInsert(vo);
	}
	@Override
	public List<BoardVO> boardList(String category, PagingVO pVO) {
		return dao.boardList(category,pVO);
	}
	@Override
	public BoardVO boardSelect(int no) {
		return dao.boardSelect(no);
	}
	@Override
	public void hit(int no) {
		dao.hit(no);
	}
	@Override
	public int boardUpdate(BoardVO vo) {
		return dao.boardUpdate(vo);
	}
	@Override
	public int boardDelete(int no, String nickname) {
		return dao.boardDelete(no,nickname);
	}
	@Override
	public int totalRecord(String category, PagingVO pVO) {
		return dao.totalRecord(category, pVO);
	}
	@Override
	public void adminBoardDelete(int no) {
		dao.adminBoardDelete(no);
	}
	@Override
	public int adminTotalRecord(PagingVO pVO) {
		return dao.adminTotalRecord(pVO);
	}

}
