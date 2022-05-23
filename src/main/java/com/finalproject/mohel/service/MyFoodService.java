package com.finalproject.mohel.service;

import java.util.List;

import com.finalproject.mohel.vo.MyFoodVO;
import com.finalproject.mohel.vo.PagingVO;

public interface MyFoodService {
	public List<MyFoodVO> getMyFood(String date, String nickname);
	public List<MyFoodVO> adminGetMyFood( PagingVO pVO);
    public int totalRecord(PagingVO pVO);
    public void adminMyFoodDel(int no);
}
