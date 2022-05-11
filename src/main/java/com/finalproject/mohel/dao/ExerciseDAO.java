package com.finalproject.mohel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.finalproject.mohel.vo.BoardVO;
import com.finalproject.mohel.vo.ExerciseMemberVO;
import com.finalproject.mohel.vo.ExercisePagingVO;
import com.finalproject.mohel.vo.ExerciseVO;

@Mapper
@Repository
public interface ExerciseDAO {
	//참가 여부
	public int resolveStatus(String nickname,int no);
	public List<ExerciseVO> exerciseList(ExercisePagingVO pVO);
	public int exerciseInsert(BoardVO vo); 
	//public int exerciseSelect(BoardVO vo);
	public BoardVO exerciseSelect(int no);
	public int exerciseUpdate(BoardVO vo);
	public int exerciseDelete(int no, String nickname);
	
	public BoardVO getFilename(int no);
	
	public void cntHit (int no);

	//총레코드수
	public int totalRecord(ExercisePagingVO pVO);
	public int totalRecord1(ExerciseVO vo, ExercisePagingVO pVO);
	public int totalRecord2(ExerciseVO vo, ExercisePagingVO pVO);
	public int totalRecord3(ExerciseVO vo, ExercisePagingVO pVO);
	
	public int exerciseMemberInsert(ExerciseVO vo);
	public int exerciseMemberUpdate(ExerciseMemberVO vo);
	public List<ExerciseVO> exerciseMemberShow(int no);
	public int exerciseMemberDelete(ExerciseMemberVO vo);
	
	public int exerciseStateUpdate(ExerciseVO vo);
	public int exerciseStateDel(ExerciseVO vo);
	
	//후기등록
	public int exerciseReviewWrite(ExerciseVO vo);
	//후기목록
	public List<ExerciseVO> exerciseReviewList(int no);
	
	//라이딩 참가 횟수
	public int exerciseCountUp(ExerciseVO vo);
	

	//-------------------------------------------
	// 모두의 운동
	
	public List<ExerciseVO> every_exerciseList(ExercisePagingVO pVO);
	public int every_exerciseInsert(ExerciseVO vo); 
	public ExerciseVO every_exerciseSelect(int no);
	public int every_exerciseUpdate(ExerciseVO vo);
	public int every_exerciseDelete(int no, String nickname);
	
	public void every_cntHit (int no);
}