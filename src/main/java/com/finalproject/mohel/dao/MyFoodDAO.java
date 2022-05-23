package com.finalproject.mohel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.finalproject.mohel.vo.MyFoodVO;
import com.finalproject.mohel.vo.PagingVO;

@Mapper
@Repository
public interface MyFoodDAO {
	public List<MyFoodVO> getMyFood(String date, String nickname);
	public List<MyFoodVO> adminGetMyFood(PagingVO pVO);
    public int totalRecord(PagingVO pVO);
    public void adminMyFoodDel(int no);
}
