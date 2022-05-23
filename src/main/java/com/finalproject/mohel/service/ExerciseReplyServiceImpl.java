package com.finalproject.mohel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalproject.mohel.dao.ExerciseReplyDAO;
import com.finalproject.mohel.vo.ExerciseReplyVO;


@Service
public class ExerciseReplyServiceImpl implements ExerciseReplyService{
	@Autowired
	ExerciseReplyDAO dao;

	@Override
	public int exerciseReplyWrite(ExerciseReplyVO vo) {
		return dao.exerciseReplyWrite(vo);
	}

	@Override
	public List<ExerciseReplyVO> exerciseReplyList(int no) {
		return dao.exerciseReplyList(no);
	}

	@Override
	public int exerciseReplyEdit(ExerciseReplyVO vo) {
		return dao.exerciseReplyEdit(vo);
	}

	@Override
	public int exerciseReplyDel(int no, String nickname) {
		return dao.exerciseReplyDel(no, nickname);
	}
	


}