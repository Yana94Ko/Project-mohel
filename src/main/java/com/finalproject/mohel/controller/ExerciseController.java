package com.finalproject.mohel.controller;



import java.nio.charset.Charset;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.finalproject.mohel.VO.BoardVO;
import com.finalproject.mohel.VO.ExercisePagingVO;
import com.finalproject.mohel.VO.ExerciseVO;
import com.finalproject.mohel.service.ExerciseService;

@RestController
public class ExerciseController {

	@Inject
	ExerciseService service;
//	@Autowired
//	ExerciseService ExerciseService;

	@GetMapping("/exercise/exerciseList")
	public ModelAndView exerciseList(ExercisePagingVO pVO, String category) {
		ModelAndView mav = new ModelAndView();
		
		//pVO.setTotalRecord(service.totalRecord(pVO));
		
		mav.addObject("lst", service.exerciseList(pVO));
		mav.addObject("pVO", pVO);
		mav.addObject("category", category);
		
		mav.setViewName("exercise/exerciseList");
		return mav;
	}
	
	@GetMapping("/exercise/exerciseWrite")
	public ModelAndView exerciseWrite(String category, String nickname, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("category", category);
		//mav.addObject("nickname", nickname);
		session.setAttribute("nickname", nickname);
		mav.setViewName("exercise/exerciseWrite");
		return mav;
	}
	
	@PostMapping("/exercise/exerciseWrite")
	public ModelAndView exerciseWritepost(BoardVO VO, ExercisePagingVO pVO, int no) {
		ModelAndView mav = new ModelAndView();	
		//JSONObject courseResiveData = new JSONObject(rVO.getCourseSendData());
		mav.addObject("lst", service.exerciseSelect(no));
		mav.addObject("VO", VO );
		mav.setViewName("exercise/exerciseWrite");
		return mav;
	}
	
	@PostMapping("/exercise/exerciseWriteOk")
    public ResponseEntity<String> exerciseWriteOk(BoardVO vo, HttpServletRequest request){
		vo.setNickname((String)request.getSession().getAttribute("nickname"));
		ResponseEntity<String> entity = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("text", "html",Charset.forName("UTF-8")));
        try {
			//�۵�� ����
			service.exerciseInsert(vo);
			
			//�� ������� �̵�
			String msg = "<script>alert('���� ��ϵǾ����ϴ�.');location.href='/exercise/exerciseList';</script>";
			entity = new ResponseEntity<String>(msg, headers, HttpStatus.OK);
		} catch (Exception e) {
			// �۵�� ����
			e.printStackTrace();
			//�� ��� ������
			String msg = "<script>alert('�۵���� ���� �Ͽ����ϴ�.');history.back();</script>";
			entity = new ResponseEntity<String>(msg, headers, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	//�� ����
	@GetMapping("/exercise/exerciseView")
	public ModelAndView exerciseView(@RequestParam("no") int no, BoardVO vo, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		service.cntHit(no); // ��ȸ�� ����
		mav.addObject("vo", service.exerciseSelect(no));
		service.cntHit(no); // ��ȸ�� ����
		mav.addObject("nickname",(String)session.getAttribute("nickname"));
		String nickname = (String)session.getAttribute("nickname");
		if (nickname != null) {
			mav.addObject("resolveStatus", service.resolveStatus(nickname, no));
		}else{
			mav.addObject("nickname", "����");
		}

		mav.setViewName("exercise/exerciseView");
		return mav;
	}

	//�� ����
	@GetMapping("/exercise/exerciseEdit")
	public ModelAndView exerciseEdit(int no, BoardVO vo, HttpSession session) {
		System.out.println(no);
		ModelAndView mav = new ModelAndView();
		//mav.addObject("lst2", service.exerciseMemberShow(no));
		mav.addObject("vo", service.exerciseSelect(no));
		//mav.addObject("vo", service.exerciseSelect(no));
		String nickname = (String)session.getAttribute("nickname");
		if (nickname != null) {
			mav.addObject("resolveStatus", service.resolveStatus(nickname, no));
		}else{
			mav.addObject("nickname", "����");
		}
		mav.setViewName("exercise/exerciseEdit");
		return mav;
	}
	
	@PostMapping("/exercise/exerciseEditOk")
	public ResponseEntity<String> exerciseEditOk(BoardVO vo, HttpSession session) {
		//vo.setNickname((String)session.getAttribute("nickName"));
		ResponseEntity<String> entity =null;
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8");
		try {

			int result =service.exerciseUpdate(vo);
			System.out.println(result);
			//System.out.println(vo.getApplicantMax());
			String msg="<script>alert('���� �����Ǿ����ϴ�.');location.href='/exercise/exerciseView?no="+vo.getNo()+"';</script>";
			entity=new ResponseEntity<String>(msg, headers, HttpStatus.OK);
			
		}catch (Exception e) {
			e.printStackTrace();
			String msg = "<script>alert('�� ���� ���� �Ͽ����ϴ�.'); history.go(-1);</script>";
			entity=new ResponseEntity<String>(msg, headers, HttpStatus.BAD_REQUEST);
		}
		return entity;
				
	}
	// �� ����
	@GetMapping("/exercise/exerciseDel")
	public ModelAndView exerciseDel(BoardVO vo, int no, HttpSession session, ModelAndView mav) {
		String nickname = (String)session.getAttribute("nickName");
		vo.setNickname((String)session.getAttribute("logId"));
		int result = service.exerciseDelete(no, nickname);
		if(result>0) {
			//������
			System.out.println("�� ���� ����");
			mav.setViewName("redirect:exerciseList");
			
		} else {
			//���� �ȵ�
			System.out.println("���� �����Ͽ����ϴ�.");
			mav.addObject("no", no);
			mav.setViewName("redirect:exerciseView");
		}
		
		return mav;
	}
	// �� ����
	@GetMapping("/exercise/exerciseReview")
	public ModelAndView exerciseReview(int no, BoardVO vo) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", service.exerciseSelect(no));
		mav.addObject("lst2", service.exerciseMemberShow(no));
		mav.setViewName("exercise/exerciseReview");
		return mav;
	}
	
	
	// ����� � ����Ʈ
	@GetMapping("/exercise/every_exerciseList")
	public ModelAndView every_exerciseList(ExercisePagingVO pVO) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("lst", service.every_exerciseList(pVO));
		mav.addObject("pVO", pVO);
	
		mav.setViewName("exercise/every_exerciseList");
		return mav;
	}
	
	// ����� � �۾���
	@GetMapping("/exercise/every_exerciseWrite")
	public ModelAndView every_exerciseWrite() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("exercise/every_exerciseWrite");
		return mav;
	}
	
	@PostMapping("/exercise/every_exerciseWrite")
	public ModelAndView every_exerciseWritepost(ExerciseVO VO, ExercisePagingVO pVO) {
		ModelAndView mav = new ModelAndView();	
		//JSONObject courseResiveData = new JSONObject(rVO.getCourseSendData());
		mav.addObject("lst", service.every_exerciseList(pVO));
		mav.addObject("VO", VO );
		mav.setViewName("exercise/every_exerciseWrite");
		return mav;
	}
	@PostMapping("/exercise/every_exerciseWriteOk")
    public ResponseEntity<String> every_exerciseWriteOk(ExerciseVO vo, HttpServletRequest request){
		//vo.setnickname((String)request.getSession().getAttribute("nickname"));
		//System.out.println("title>>>"+vo.getTitle());
		ResponseEntity<String> entity = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("text", "html",Charset.forName("UTF-8")));
        try {
			//�۵�� ����
			service.every_exerciseInsert(vo);
			
			//�� ������� �̵�
			String msg = "<script>alert('���� ��ϵǾ����ϴ�.');location.href='/exercise/every_exerciseList';</script>";
			entity = new ResponseEntity<String>(msg, headers, HttpStatus.OK);
		} catch (Exception e) {
			// �۵�� ����
			e.printStackTrace();
			//�� ��� ������
			String msg = "<script>alert('�۵���� ���� �Ͽ����ϴ�.');history.back();</script>";
			entity = new ResponseEntity<String>(msg, headers, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	// ����� � �ۺ���
	@GetMapping("/exercise/every_exerciseView")
	public ModelAndView every_exerciseView(String category, String nickname, HttpSession session, int no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("category", category);
		//mav.addObject("nickname", nickname);
		service.cntHit(no); // ��ȸ�� ����
		mav.addObject("vo", service.every_exerciseSelect(no));
		session.setAttribute("nickname", nickname);
		mav.setViewName("exercise/every_exerciseView");
		return mav;
	}
	// ����� � �� ����
	@GetMapping("/exercise/every_exerciseEdit")
	public ModelAndView every_exerciseEdit(int no, ExerciseVO vo, HttpSession session) {
		//System.out.println(no);
		ModelAndView mav = new ModelAndView();
		//mav.addObject("lst2", service.exerciseMemberShow(no));
		mav.addObject("vo", service.every_exerciseSelect(no));
		//mav.addObject("vo", service.exerciseSelect(no));
		String nickname = (String)session.getAttribute("nickname");
		if (nickname != null) {
			mav.addObject("resolveStatus", service.resolveStatus(nickname, no));
		}else{
			mav.addObject("nickname", "����");
		}
		mav.setViewName("exercise/every_exerciseEdit");
		return mav;
	}
	
	@PostMapping("/exercise/every_exerciseEditOk")
	public ResponseEntity<String> every_exerciseEditOk(ExerciseVO vo, HttpSession session) {
		//vo.setNickname((String)session.getAttribute("nickName"));
		ResponseEntity<String> entity =null;
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8");
		try {

			int result =service.every_exerciseUpdate(vo);
			System.out.println(result);
			//System.out.println(vo.getApplicantMax());
			String msg="<script>alert('���� �����Ǿ����ϴ�.');location.href='/exercise/every_exerciseView?no="+vo.getNo()+"';</script>";
			entity=new ResponseEntity<String>(msg, headers, HttpStatus.OK);
			
		}catch (Exception e) {
			e.printStackTrace();
			String msg = "<script>alert('�� ���� ���� �Ͽ����ϴ�.'); history.go(-1);</script>";
			entity=new ResponseEntity<String>(msg, headers, HttpStatus.BAD_REQUEST);
		}
		return entity;
				
	}
	// ����� � �� ����
	@GetMapping("/exercise/every_exerciseDel")
	public ModelAndView every_exerciseDel(BoardVO bvo, ExerciseVO vo, int no, HttpSession session, ModelAndView mav) {
		String nickname = (String)session.getAttribute("nickname");
		bvo.setNickname((String)session.getAttribute("logId"));
		int result = service.every_exerciseDelete(no, nickname);
		if(result>0) {
			//������
			mav.setViewName("redirect:every_exerciseList");
			
		} else {
			//���� �ȵ�
			System.out.println("���� �����Ͽ����ϴ�.");
			mav.addObject("no", no);
			mav.setViewName("redirect:every_exerciseView");
		}
		
		return mav;
	}
}