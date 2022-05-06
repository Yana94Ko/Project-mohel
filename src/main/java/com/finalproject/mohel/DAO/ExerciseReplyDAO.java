package com.finalproject.mohel.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.finalproject.mohel.VO.ExerciseReplyVO;

@Mapper
@Repository
public interface ExerciseReplyDAO {
	//´ñ±Ûµî·Ï
		public int exerciseReplyWrite(ExerciseReplyVO vo);
		//´ñ±Û¸ñ·Ï
		public List<ExerciseReplyVO> exerciseReplyList(int no);
		//´ñ±Û¼öÁ¤
		public int exerciseEdit(ExerciseReplyVO vo);
		//´ñ±Û»èÁ¦
		public int exerciseReplyDel(int no, String nickname);
}
