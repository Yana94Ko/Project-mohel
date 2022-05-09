package com.finalproject.mohel.controller;

import java.time.LocalDate;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.gson.Gson;

@Controller
public class MyFoodController {
	@GetMapping("/myFoodMain")
	public String myFoodMain(String year, String month, String date, Model model) {
		LocalDate current = LocalDate.now();
		if(year==null) {
			System.out.println("year 비었음");
			current = LocalDate.now();
		}else {
			System.out.println("year 안 비었음");
			current = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(date));
		}
		
		HashMap<String, Integer> map = new HashMap<>();
		map.put("year", current.getYear());
		map.put("month", current.getMonthValue());
		map.put("date", current.getDayOfMonth());
		map.put("day", current.getDayOfWeek().getValue());
		
		model.addAttribute("dateJson", new Gson().toJson(map));
		
		return "food/myFoodMain";
	}
	
	@GetMapping("/everyFoodWrite")
	public String everyFoodWrite() {
		return "food/everyFoodWrite";
	}
}
