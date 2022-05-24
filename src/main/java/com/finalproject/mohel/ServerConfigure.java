package com.finalproject.mohel;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class ServerConfigure implements WebMvcConfigurer {
	
	static String[] interceptorArr = {
			"/mypage/*",
			"/exercise/*",
			"/board/boardWrite","/board/boardWriteOk","/board/boardEdit","/board/boardEditOk","/board/boardDel",
			"/reply/writeOk","/reply/editOk","/reply/del",
			"/exercise/every_exerciseWrite","/exercise/every_exerciseWriteOk","/exercise/every_exerciseEdit","/exercise/every_exerciseEditOk","/exercise/every_exerciseDel",
			"/exercise/exerciseReplyWriteOk","/exercise/exerciseReplyEditOk","/exercise/exercisegReplyDel",
			"/exercise/excerciseMemberOk","/exercise/excerciseMemberCancel","/exercise/excerciseStateOk","/exercise/excerciseStateDel",
			"/myFoodMain","/everyFoodWrite","/myFoodMain/saveInfo","/everyFoodWriteOk","/everyFoodReplyOk","/everyFoodEdit","/everyFoodEditOk","/everyFoodDel","/everyFoodReplyDel","/replyEditOk"
		};
	
	static String[] adminInterceptorArr = {"/admin/*"};
	
	private static final List<String> URL_PATERRNS = Arrays.asList(interceptorArr);
	private static final List<String> ADMIN_URL_PATERRNS = Arrays.asList(adminInterceptorArr);
	private static final List<String> URL_PATERRNS_RESET_PWD = Arrays.asList("/member/codeCheck", "/member/resetPwd", "resetPwdOk");
	
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns(URL_PATERRNS);
		registry.addInterceptor(new AdminLoginInterceptor()).addPathPatterns(ADMIN_URL_PATERRNS);
		registry.addInterceptor(new ResetPwdInterceptor()).addPathPatterns(URL_PATERRNS_RESET_PWD);
	}
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		commonsMultipartResolver.setDefaultEncoding("UTF-8");
		return commonsMultipartResolver;
	}
}
