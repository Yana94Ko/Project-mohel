package com.finalproject.mohel.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.finalproject.mohel.dao.ExerciseDAO;
import com.finalproject.mohel.vo.BoardVO;
import com.finalproject.mohel.vo.ExerciseMemberVO;
import com.finalproject.mohel.vo.ExercisePagingVO;
import com.finalproject.mohel.vo.ExerciseVO;


@Service
public class ExerciseServiceImpl implements ExerciseService{

	@Inject
	ExerciseDAO dao;
	
	@Override
	public int resolveStatus(String nickname, int no) {
		return dao.resolveStatus(nickname,no);
	}

	@Override
	public List<ExerciseVO> exerciseList(ExercisePagingVO pVO, String nickname) {
		return dao.exerciseList(pVO, nickname);
	}

	@Override
	public int exerciseInsert(BoardVO vo) {
		return dao.exerciseInsert(vo);
	}

	@Override
	public BoardVO exerciseSelect(int no) {
		return dao.exerciseSelect(no);
	}

	@Override
	public int exerciseUpdate(BoardVO vo) {
		return dao.exerciseUpdate(vo);
	}

	@Override
	public int exerciseDelete(int no, String nickname) {
		return dao.exerciseDelete(no, nickname);
	}

	@Override
	public void cntHit(int no) {
		dao.cntHit(no);
	}

	@Override
	public int totalRecord(ExercisePagingVO pVO, String nickname) {
		return dao.totalRecord(pVO, nickname);
	}

	

	@Override
	public int totalRecord1(ExercisePagingVO pVO) {
		return dao.totalRecord1(pVO);
	}

	@Override
	public int exerciseMemberInsert(ExerciseMemberVO mvo) {
		return dao.exerciseMemberInsert(mvo);
	}

	@Override
	public int exerciseMemberUpdate(ExerciseMemberVO mvo) {
		return dao.exerciseMemberUpdate(mvo);
	}

	@Override
	public List<ExerciseMemberVO> exerciseMemberShow(int no) {
		return dao.exerciseMemberShow(no);
	}

	@Override
	public int exerciseMemberDelete(ExerciseMemberVO mvo) {
		return dao.exerciseMemberDelete(mvo);
	}

	@Override
	public int exerciseStateUpdate(ExerciseVO vo) {
		return dao.exerciseStateUpdate(vo);
	}

	@Override
	public int exerciseStateDel(ExerciseVO vo) {
		return dao.exerciseStateDel(vo);
	}

	@Override
	public int exerciseReviewWrite(ExerciseVO vo) {
		return dao.exerciseReviewWrite(vo);
	}

	@Override
	public List<ExerciseVO> exerciseReviewList(int no) {
		return dao.exerciseReviewList(no);
	}

	@Override
	public int exerciseCountUp(ExerciseVO vo) {
		return dao.exerciseCountUp(vo);
	}

	@Override
	public List<ExerciseVO> every_exerciseList(ExercisePagingVO pVO) {
		return dao.every_exerciseList(pVO);
	}

	@Override
	public int every_exerciseInsert(ExerciseVO vo) {
		return dao.every_exerciseInsert(vo);
	}

	
	@Override
	public ExerciseVO every_exerciseLastWriteNo(String nickname) {
		return dao.every_exerciseLastWriteNo(nickname);
	}

	@Override
	public ExerciseVO every_exerciseSelect(int no) {
		return dao.every_exerciseSelect(no);
	}

	@Override
	public int every_exerciseUpdate(ExerciseVO vo) {
		return dao.every_exerciseUpdate(vo);
	}

	@Override
	public int every_exerciseDelete(int no, String nickname) {
		return dao.every_exerciseDelete(no, nickname);
	}

	@Override
	public void every_cntHit(int no) {
		dao.every_cntHit(no);
	}

	@Override
	public BoardVO getFilename(int no) {
		return dao.getFilename(no);
	}

	@Override
	public int exerciseMemberCnt(int exerciseNo) {
		return dao.exerciseMemberCnt(exerciseNo);
	}
	public List<ExerciseVO> home_exercise(ExercisePagingVO pVO) {
		return dao.home_exercise(pVO);
	}


	@Override
	public void exerciseApplicantCntSet(int exerciseNo, int applicantCnt) {
		dao.exerciseApplicantCntSet(exerciseNo, applicantCnt);
	}
}