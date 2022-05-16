package com.finalproject.mohel.service;

import java.util.List;

import com.finalproject.mohel.vo.MyFoodVO;

public interface MyFoodService {
	public List<MyFoodVO> getMyFood(String date, String nickname);
}
