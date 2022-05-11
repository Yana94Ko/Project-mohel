package com.finalproject.mohel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.finalproject.mohel.vo.MyFoodVO;

@Mapper
@Repository
public interface MyFoodDAO {
	public List<MyFoodVO> getMyFood(String date, String nickname);
}
