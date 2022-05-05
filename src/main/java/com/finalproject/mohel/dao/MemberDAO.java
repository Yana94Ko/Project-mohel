package com.finalproject.mohel.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper 
@Repository
public interface MemberDAO {
	public int searchNickname(String nickname);
}
