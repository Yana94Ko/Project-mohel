package com.finalproject.mohel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalproject.mohel.dao.MyFoodDAO;
import com.finalproject.mohel.vo.MyFoodVO;

@Service
public class MyFoodServiceImpl implements MyFoodService {
	@Autowired
	MyFoodDAO dao;

	@Override
	public List<MyFoodVO> getMyFood(String date, String nickname) {
		return dao.getMyFood(date, nickname);
	}

}
