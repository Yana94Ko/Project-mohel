package com.finalproject.mohel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalproject.mohel.dao.MyFoodDAO;
import com.finalproject.mohel.vo.MyFoodVO;
import com.finalproject.mohel.vo.PagingVO;

@Service
public class MyFoodServiceImpl implements MyFoodService {
	@Autowired
	MyFoodDAO dao;

	@Override
	public List<MyFoodVO> getMyFood(String date, String nickname) {
		return dao.getMyFood(date, nickname);
	}

	@Override
	public List<MyFoodVO> adminGetMyFood(PagingVO pVO) {
		return dao.adminGetMyFood(pVO);
	}

	@Override
	public int totalRecord(PagingVO pVO) {
		return dao.totalRecord(pVO);
	}

	@Override
	public void adminMyFoodDel(int no) {
		dao.adminMyFoodDel(no);
	}

}
