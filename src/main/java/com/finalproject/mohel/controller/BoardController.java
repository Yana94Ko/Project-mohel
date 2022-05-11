package com.finalproject.mohel.controller;

import java.nio.charset.Charset;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.finalproject.mohel.service.BoardService;
import com.finalproject.mohel.vo.BoardVO;
import com.finalproject.mohel.vo.PagingVO;
@RestController
@RequestMapping("/board/")
public class BoardController {
	@Inject
	BoardService service; 
	//게시판
	@GetMapping("boardList")
	public ModelAndView boardList(String category,PagingVO pVO) {
		ModelAndView mav = new ModelAndView();
		//pVO.setTotalRecord(service.totalRecord(pVO));
		int totalRecord=service.totalRecord(category,pVO);
		pVO.setTotalRecord(totalRecord);
		List<BoardVO> list=service.boardList(category, pVO);
		String title="자유게시판";
		if(category.equals("challenge")) {
			title="챌린지게시판";
		}
		else if(category.equals("ba")) {
			title="Before&After";
		}
		mav.addObject("pVO",pVO);
		mav.addObject("title",title); 
		mav.addObject("list",list);
		mav.addObject("category",category);
		mav.setViewName("/board/boardList");
		return mav;
	}
	// 글쓰기 폼
	@GetMapping("boardWrite")
	public ModelAndView boardWrite(String category) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("category",category);
		mav.setViewName("/board/boardWrite");
		return mav;
	}
	//글 등록
	@PostMapping("boardWriteOk")
	public ResponseEntity<String> boardWriteOk(BoardVO vo, HttpServletRequest request){
		vo.setNickname("ㅇㅇ"); 
		//vo.setNickname(request.getRemoteAddr()); 
		//글쓴이-session로그인 아이디를 구한다
		//vo.setNickname((String)request.getSession().getAttribute("logId"));
		
		ResponseEntity<String> entity = null; //데이터와 처리 상태를 가진다
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "text/html; charset=utf-8");
		//header.setContentType(new MediaType("text", "html", Charset.forName("UTF-8")));
		try {
			service.boardInsert(vo);
			String msg = "<script>";
				   msg += "alert('글이 등록되었습니다');";
				   msg += "location.href='/board/boardList?category="+vo.getCategory()+"';";
				   msg += "</script>";
			entity = new ResponseEntity<String>(msg, header, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			String msg = "<script>";
				   msg += "alert('글 등록에 실패하였습니다');";
				   msg += "history.back();";
				   msg += "</script>";
			entity = new ResponseEntity<String>(msg, header, HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	//글 보기 
		@GetMapping("boardView")
		public ModelAndView boardView(int no,String category) {
			ModelAndView mav = new ModelAndView();
			service.hit(no);
			String title="자유게시판";
			if(category.equals("challenge")) {
				title="챌린지게시판";
			}
			else if(category.equals("ba")) {
				title="Before&After";
			}
			
			mav.addObject("title",title);
			mav.addObject("category",category);
			mav.addObject("vo", service.boardSelect(no));
			mav.setViewName("board/boardView");
			return mav;
		}
	//글 수정
	@GetMapping("boardEdit")
	public ModelAndView boardEdit(int no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", service.boardSelect(no));
		mav.setViewName("board/boardEdit");
		return mav;
	}
	//글 수정
	@PostMapping("boardEditOk")
	public ResponseEntity<String> boardEditOk(BoardVO vo, HttpSession session) {
		ResponseEntity<String> entity = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("text", "html", Charset.forName("UTF-8")));
		
		vo.setNickname((String)session.getAttribute("logId"));
		try {
			int result = service.boardUpdate(vo);
			if(result>0) {//수정 성공
				entity = new ResponseEntity<String>(getEditSuccessMessage(vo.getNo()), headers, HttpStatus.OK);
			}else {//수정 실패
				entity = new ResponseEntity<String>(getEditFailMessage(), headers, HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(getEditFailMessage(), headers, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	public String getEditFailMessage() {//글수정 메시지
		String msg = "<script>";
		   	   msg += "alert('글 수정에 실패했습니다.\\n수정 폼으로 이동합니다.');";
		       msg += "history.back();";
		       msg += "</script>";
		return msg;
	}
	public String getEditSuccessMessage(int no) {
		String msg = "<script>";
			   msg += "alert('글 수정에 성공했습니다.\\n글 상세보기로 이동합니다.');";
			   msg += "location.href='/board/boardView?no="+no+"';"; 
			   msg += "</script>";
		return msg;
	}
	//글 삭제
		@GetMapping("boardDel")
		public ModelAndView boardDel(int no, HttpSession session){
			String nickname = (String)session.getAttribute("logId");
			
			int result = service.boardDelete(no, nickname);
			
			ModelAndView mav = new ModelAndView();
			if(result>0) {//삭제 성공시
				mav.setViewName("redirect:boardList");
			}else {//삭제 실패시
				mav.addObject("no", no);
				mav.setViewName("redirect:boardView");
			}
			return mav;
		}
}