package com.finalproject.mohel.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.finalproject.mohel.VO.ExerciseReplyVO;

@Mapper
@Repository
public interface ExerciseReplyDAO {
	//댓글등록
		public int exerciseReplyWrite(ExerciseReplyVO vo);
		//댓글목록
		public List<ExerciseReplyVO> exerciseReplyList(int no);
		//댓글수정
		public int exerciseEdit(ExerciseReplyVO vo);
		//댓글삭제
		public int exerciseReplyDel(int no, String nickname);
}