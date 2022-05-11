package com.finalproject.mohel.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.finalproject.mohel.service.MyFoodService;
import com.finalproject.mohel.vo.MyFoodVO;
import com.google.gson.Gson;

@Controller
public class MyFoodController {
	@Autowired
	MyFoodService service;
	
	@GetMapping("/myFoodMain")
	public String myFoodMain(String year, String month, String date, Model model) {
		LocalDate current = LocalDate.now();
		if(year==null) {
			current = LocalDate.now();
		}else {
			current = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(date));
		}
		String dateParam = String.format("%d-%02d-%02d", current.getYear(), current.getMonthValue(), current.getDayOfMonth());
		List<MyFoodVO> list = service.getMyFood(dateParam+"%", "ㅇㅇ");
		
		
		HashMap<String, Integer> map = new HashMap<>();
		map.put("year", current.getYear());
		map.put("month", current.getMonthValue());
		map.put("date", current.getDayOfMonth());
		map.put("day", current.getDayOfWeek().getValue());
		
		model.addAttribute("dateJson", new Gson().toJson(map));
		model.addAttribute("today", dateParam);
		model.addAttribute("foodList", new Gson().toJson(list));
		
		return "food/myFoodMain";
	}
	
	@GetMapping("/everyFoodWrite")
	public String everyFoodWrite() {
		return "food/everyFoodWrite";
	}
}
