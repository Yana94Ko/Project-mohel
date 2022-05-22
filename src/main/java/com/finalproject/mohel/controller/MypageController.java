package com.finalproject.mohel.controller;

import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.finalproject.mohel.MohelApplication;
import com.finalproject.mohel.service.KakaoAPI;
import com.finalproject.mohel.service.MemberService;
import com.finalproject.mohel.service.MypageService;
import com.finalproject.mohel.vo.BoardVO;
import com.finalproject.mohel.vo.MemberVO;
import com.finalproject.mohel.vo.PagingVO;

@Controller
@RequestMapping("/mypage/")
public class MypageController {
	
	@Inject
	MypageService service;
	@Inject
	MemberService memberService;
	@Inject
	KakaoAPI kakao;
	
	ModelAndView mav = new ModelAndView();
	HttpHeaders headers = new HttpHeaders();
	ResponseEntity<String> entity = null;

	@GetMapping("userEdit")
	public String userEdit() {
		return "/mypage/userEdit";
	}
	
	@PostMapping("userEditOk")
	public ResponseEntity<String> userEditOk(MemberVO vo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO userInfo = (MemberVO)session.getAttribute("userInfo");
		vo.setEmail(userInfo.getEmail());
		
		MohelApplication.profileImgUpload(vo, request);
		
		headers.add("content-Type", "text/html;charset=utf-8");
		
		String imgRealPath = "";

		StringBuffer body = new StringBuffer();
		if(service.updateUserInfo(vo)==1) {
			if(!((MemberVO)session.getAttribute("userInfo")).getProfile().equals(vo.getProfile())) {
				imgRealPath = session.getServletContext().getRealPath(((MemberVO)session.getAttribute("userInfo")).getProfile());
			}
			session.setAttribute("userInfo", memberService.selectMember(vo));
			
			body.append("<script>");
			body.append("alert('회원 정보가 수정되었습니다.');");
			body.append("location.href='/mypage/userEdit';");
			body.append("</script>");
			entity = new ResponseEntity<String>(body.toString(), headers, HttpStatus.OK);
		}else {
			if(!((MemberVO)session.getAttribute("userInfo")).getProfile().equals(vo.getProfile())) {
				imgRealPath = session.getServletContext().getRealPath(vo.getProfile());
			}
			
			body.append("<script>");
			body.append("alert('회원 정보 수정에 실패하였습니다.\\n비밀번호를 확인해주세요.');");
			body.append("location.href='/mypage/userEdit';");
			body.append("</script>");
			entity = new ResponseEntity<String>(body.toString(), headers, HttpStatus.BAD_REQUEST);
		}
		
		File f = new File(imgRealPath);
		if(f.exists()) {
			f.delete();
		}
		
		return entity;
	}
	
	@GetMapping("myWrite")
	public ModelAndView myWrite(String category, PagingVO pVO, HttpSession session) {
		MemberVO userInfo = (MemberVO)session.getAttribute("userInfo");
		
		pVO.setOnePageRecord(10);
		pVO.setTotalRecord(service.boardTotalRecord(userInfo.getNickname(), category, pVO));
		
		if(category!=null && category.equals("")) category=null;
		List<BoardVO> BoardList = service.selectMyBoardList(userInfo.getNickname(), category, pVO);
		
		LocalDate now = LocalDate.now();
		for (BoardVO boardVO : BoardList) {
			String[] writeDateTime = boardVO.getWritedate().split(" ");
			String[] writeDate = writeDateTime[0].split("-");
			String[] writeTime = writeDateTime[1].split(":");
			
			if(!now.toString().split("-")[0].equals(writeDate[0])) {
				boardVO.setWritedate(writeDateTime[0]);
			}else if(!writeDateTime[0].equals(now.toString())) {
				boardVO.setWritedate(writeDate[1]+"-"+writeDate[2]);
			}else {
				boardVO.setWritedate(writeTime[0]+":"+writeTime[1]);
			}
		}
		
		mav.addObject("myBoardList", BoardList);
		mav.addObject("pVO", pVO);
		mav.addObject("category", category);
		mav.setViewName("/mypage/myWrite");
		
		return mav;
	}
	
	@GetMapping("myComment")
	public ModelAndView myComment(String category, PagingVO pVO, HttpSession session) {
		MemberVO userInfo = (MemberVO)session.getAttribute("userInfo");
		
		pVO.setOnePageRecord(10);
		pVO.setTotalRecord(service.replyTotalRecord(userInfo.getNickname(), category, pVO));
		
		if(category!=null && category.equals("")) category=null;
		List<HashMap<String, Object>> replyList = service.selectMyReplyList(userInfo.getNickname(), category, pVO);
		
		LocalDate now = LocalDate.now();
		for (HashMap<String, Object> reply : replyList) {
			String[] writeDateTime = reply.get("writedate").toString().split("T");
			String[] writeDate = writeDateTime[0].split("-");
			String[] writeTime = writeDateTime[1].split(":");
			
			if(!now.toString().split("-")[0].equals(writeDate[0])) {
				reply.put("writedate", writeDateTime[0]);
			}else if(!writeDateTime[0].equals(now.toString())) {
				reply.put("writedate", writeDate[1]+"-"+writeDate[2]);
			}else {
				reply.put("writedate", writeTime[0]+":"+writeTime[1]);
			}
		}
		
		mav.addObject("myReplyList", replyList);
		mav.addObject("pVO", pVO);
		mav.addObject("category", category);
		mav.setViewName("/mypage/myComment");
		
		return mav;
	}
	
	@GetMapping("myExercise")
	public ModelAndView myExercise(PagingVO pVO, HttpSession session) {
		MemberVO userInfo = (MemberVO)session.getAttribute("userInfo");
		
		pVO.setOnePageRecord(10);
		pVO.setTotalRecord(service.myExerciseTotalRecord(userInfo.getNickname(), pVO));
		
		mav.addObject("myExerciseList", service.selectMyExercise(userInfo.getNickname(), pVO));
		mav.addObject("pVO", pVO);
		mav.setViewName("/mypage/myExercise");
		
		return mav;
	}
	
	@GetMapping("userDel")
	public String userDel() {
		return "/mypage/userDel";
	}
	
	@PostMapping("userDelOk")
	public ResponseEntity<String> userDelOk(HttpSession session) {
		if(session.getAttribute("kakao")!=null && (boolean)session.getAttribute("kakao")) {
			kakao.disconnKakao((String)session.getAttribute("accessToken"));
		}
		service.deleteMember((MemberVO)session.getAttribute("userInfo"));
		session.invalidate();
		
		StringBuffer body = new StringBuffer();
		body.append("<script>");
		body.append("alert('회원정보가 삭제되었습니다.');");
		body.append("location.href='/';");
		body.append("</script>");
		
		return new ResponseEntity<String>(body.toString(), headers, HttpStatus.OK);
	}
	
	@PostMapping("pwdCk")
	@ResponseBody
	public int pwdCk(String pwd, HttpSession session) {
		MemberVO userInfo = (MemberVO)session.getAttribute("userInfo");
		userInfo.setPwd(pwd);
		if(memberService.selectMember(userInfo)!=null) {
			return 1;
		}else {
			return 0;
		}
	}
}