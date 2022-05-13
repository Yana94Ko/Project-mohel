package com.finalproject.mohel;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.finalproject.mohel.vo.MemberVO;

@SpringBootApplication
public class MohelApplication {

	public static void main(String[] args) {
		SpringApplication.run(MohelApplication.class, args);
	}
	
	public static void fileUpload(MemberVO vo, HttpServletRequest request) {
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)request;
		
		String profilePath = "/img/profile/";
//		배포시 폴더경로 변경
//		String path = "D:\\study\\Multi Campus\\Project-mohel\\profile";
		String path = request.getSession().getServletContext().getRealPath(profilePath);
		MultipartFile file = mr.getFile("profileImg");
		if(!file.getOriginalFilename().equals("")) {
			String orgFileName = file.getOriginalFilename();
			int point = orgFileName.lastIndexOf(".");
			String ext = orgFileName.substring(point+1);
			
			orgFileName = System.currentTimeMillis()+"."+ext;
			File f = new File(path, orgFileName);
			
			try {
				file.transferTo(f);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			vo.setProfile(profilePath+orgFileName);
		}
	}

}
