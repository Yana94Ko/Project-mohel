package com.finalproject.mohel.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.finalproject.mohel.service.BoardService;
import com.finalproject.mohel.service.ExerciseReplyService;
import com.finalproject.mohel.service.ExerciseService;
import com.finalproject.mohel.service.MemberService;
import com.finalproject.mohel.service.MyFoodService;
import com.finalproject.mohel.service.ReplyService;
import com.finalproject.mohel.vo.ExercisePagingVO;
import com.finalproject.mohel.vo.ExerciseVO;
import com.finalproject.mohel.vo.MemberVO;
import com.finalproject.mohel.vo.PagingVO;


@RequestMapping("/admin/")
@Controller // 현재의 클래스를 controller bean에 등록시킴
public class AdminController {
	@Autowired
	MemberService memberService;
	@Autowired
	BoardService boardService;
	@Autowired
	ExerciseService exerciseService;
	@Autowired
	ExerciseReplyService exerciseReplyService;
	@Autowired
	MyFoodService myFoodService;
	@Autowired
	ReplyService replyService;

	// 관리자 메인 페이지
	@GetMapping("adminMain")
	public String adminMain(Model model) {
		model.addAttribute("adminMain", model);
		return("admin/adminMain");
	}
	// 관리자 회원관리 페이지
	@GetMapping("adminMember") 
	public ModelAndView memberList(Model model, HttpServletRequest request,  PagingVO pVO) { 
		ModelAndView mav = new ModelAndView();
		pVO.setOnePageRecord(10);
		pVO.setTotalRecord(memberService.totalRecord(pVO));
		mav.addObject("memberList", memberService.memberList(pVO));
		mav.addObject("pVO", pVO);
		
		mav.setViewName("admin/adminMember"); 
		return mav; 
	}
	//관리자 페이지 회원정보 상세보기	  
	@RequestMapping("adminMember") 
	public ModelAndView adminMember(@RequestParam String nickname) { 
		ModelAndView mav = new ModelAndView();
		MemberVO vo = memberService.adminView(nickname);
		System.out.println(vo.getActive()+vo.getNickname());
		mav.addObject("av", vo);
		mav.setViewName("admin/adminMember");
		return mav;
	}
	//관리자 페이지 회원정보 수정
	@RequestMapping(value="adminMemberEdit", method = RequestMethod.POST) 
	public String AdminUpdate(MemberVO vo) throws Exception {
		vo.setNickname((String)vo.getNickname());
		memberService.adminUpdate(vo); 
		return "redirect:/admin/adminMember"; 
	}
	// 관리자 페이지 ajax 회원정보 가져오기
	@RequestMapping("adminMember2")
	@ResponseBody 
	public MemberVO adminView2(@RequestParam String nickname){ 
		return memberService.adminView(nickname); 
	}
	//관리자 페이지 회원정보 삭제
	@PostMapping("MemberDelete")
    @ResponseBody
    public int memberDelete(@RequestBody MemberVO vo) {
        return memberService.adminDelete(vo.getNickname());
    }
	// 관리자 게시판 관리 페이지
	@GetMapping("adminBoard") 
	public ModelAndView allSelect(String category,PagingVO pVO) {
		ModelAndView mav = new ModelAndView();
		pVO.setOnePageRecord(10);
		pVO.setTotalRecord(boardService.totalRecord(category,pVO));
		
		mav.addObject("lst", boardService.boardList(category,pVO)); 
		mav.addObject("pVO", pVO);
		mav.addObject("category", category);
		mav.setViewName("admin/adminBoard"); 
		return mav; 
	}
	//관리자 페이지 게시판 글 상세 보기
	@RequestMapping("adminBoardView") 
	public ModelAndView adminBoardView(@RequestParam("no") int no) { 
		ModelAndView mav = new ModelAndView();
	
		mav.addObject("vo", boardService.boardSelect(no));
		mav.setViewName("admin/adminBoardView");
	
		return mav; 
	}
	//관리자 페이지 게시판 글 삭제하기
	@GetMapping("adminBoardDelete") 
	public ModelAndView adminBoardDelete(String category, int no, ModelAndView mav) { 
		boardService.adminBoardDelete(no);
		//카테고리별 이동할 페이지 지정
		mav.setViewName("redirect:/admin/adminBoard?category="+category); 
		return mav; 
	}
	
	// 관리자 모두의 운동 리스트
	@GetMapping("adminEveryExercise") 
	public ModelAndView adminEveryExercise(ExercisePagingVO pVO) {
		ModelAndView mav = new ModelAndView();
		pVO.setOnePageRecord(10);
		pVO.setTotalRecord(exerciseService.totalRecord1(pVO));
		mav.addObject("lst", exerciseService.every_exerciseList(pVO));
		mav.addObject("pVO", pVO);
		mav.setViewName("admin/adminEveryExercise");
		return mav; 
	}
	// 관리자 모두의 운동 글 상세보기
	@GetMapping("adminEveryExerciseView") 
	public ModelAndView adminEveryExerciseView(int no,ExercisePagingVO pVO) {
		ModelAndView mav = new ModelAndView();
		ExerciseVO vo =exerciseService.every_exerciseSelect(no);
		String jsonStr=vo.getPlaceinfo();
		JSONObject obj=new JSONObject(jsonStr);
		String addr=obj.getString("address_name");
		mav.addObject("vo", vo);
		mav.addObject("placeinfo",addr);
		mav.setViewName("admin/adminEveryExerciseView");
		return mav; 
	}
	//관리자 페이지 게시판 글 삭제하기
	@GetMapping("adminEveryExerciseDelete") 
	public ModelAndView adminEveryExerciseDelete(String nickname, int no, ModelAndView mav) { 
		int result = exerciseService.every_exerciseDelete(no, nickname);
		if(result>0) {
			//삭제됨
			mav.setViewName("redirect:adminEveryExercise");
			
		} else {
			//삭제 안됨
			System.out.println("삭제 실패하였습니다.");
			mav.addObject("no", no);
			mav.setViewName("redirect:adminEveryExercise");
		} 
		return mav; 
	}
	// 관리자 나만의 식단 리스트
	@GetMapping("adminMyMeal")
	public ModelAndView adminMyMeal(PagingVO pVO) {
		ModelAndView mav = new ModelAndView();
		pVO.setOnePageRecord(10);
		pVO.setTotalRecord(myFoodService.totalRecord(pVO));
		mav.addObject("lst", myFoodService.adminGetMyFood(pVO));
		mav.addObject("pVO", pVO);
		mav.setViewName("admin/adminMyMeal");
		return mav; 
	}
	// 관리자 나만의 식단 삭제
	@GetMapping("adminMyMealDel")
	public ModelAndView adminMyMealDel(int no, PagingVO pVO) {
		ModelAndView mav = new ModelAndView();
		myFoodService.adminMyFoodDel(no);
		mav.setViewName("redirect:adminMyMeal");
		return mav; 
	}
}
