package com.finalproject.mohel.controller;



import java.io.Console;
import java.io.File;
import java.nio.charset.Charset;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.finalproject.mohel.service.ExerciseService;
import com.finalproject.mohel.vo.BoardVO;
import com.finalproject.mohel.vo.ExerciseMemberVO;
import com.finalproject.mohel.vo.ExercisePagingVO;
import com.finalproject.mohel.vo.ExerciseVO;
import com.finalproject.mohel.vo.MemberVO;


@RestController
public class ExerciseController {

	@Inject
	ExerciseService service;
//	@Autowired
//	ExerciseService ExerciseService;

	@GetMapping("/exercise/exerciseList")
	public ModelAndView exerciseList(ExercisePagingVO pVO, String category, String nickname, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		if(nickname==null) {
			MemberVO user=(MemberVO)session.getAttribute("userInfo");
			if(user!=null) {
				nickname=user.getNickname();
			}
		}
		pVO.setTotalRecord(service.totalRecord(pVO, nickname));
		System.out.println(nickname);
		mav.addObject("lst", service.exerciseList(pVO, nickname));
		mav.addObject("pVO", pVO);
		mav.addObject("category", category);
		mav.addObject("nickname", nickname);
		
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
    public ResponseEntity<String> exerciseWriteOk(BoardVO vo, HttpServletRequest request, MultipartHttpServletRequest mr){

		//vo.setNickname((String)request.getSession().getAttribute("nickname"));
		MemberVO mvo = (MemberVO)request.getSession().getAttribute("userInfo");
		vo.setNickname(mvo.getNickname());

		ResponseEntity<String> entity = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("text", "html",Charset.forName("UTF-8")));
       
        mr = (MultipartHttpServletRequest) request;

		String path = request.getSession().getServletContext().getRealPath("/img/exercise"); // 파일 업로드를 위한 업로드 위치의 절대 주소
        System.out.println(path);
        
    	MultipartFile file = mr.getFile("filename");
        
    	String orgFileName = file.getOriginalFilename();
        int point = orgFileName.lastIndexOf(".");
        String ext = orgFileName.substring(point+1);
        			
        File f = new File(path, System.currentTimeMillis()+"."+ext);//업로드한 파일
        			
        orgFileName = f.getName();

        //String filename = file.getOriginalFilename();
		//File uploadFile = new File(path, filename);
        try {
        	file.transferTo(f);
			//vo.setImg1(file.getOriginalFilename());
        	vo.setImg1(orgFileName);
        	
			//글등록 성공
        	service.exerciseInsert(vo);
			
			
			//글 목록으로 이동
			String msg = "<script>alert('글이 등록되었습니다.');location.href='/exercise/exerciseList';</script>";
			entity = new ResponseEntity<String>(msg, headers, HttpStatus.OK);
		} catch (Exception e) {
			// 글등록 실패
			e.printStackTrace();
			//글 등록 폼으로
			String msg = "<script>alert('글등록을 실패 하였습니다.');history.back();</script>";
			entity = new ResponseEntity<String>(msg, headers, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	//글 보기
	@GetMapping("/exercise/exerciseView")
	public ModelAndView exerciseView(@RequestParam("no") int no, BoardVO vo, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo",service.exerciseSelect(no));
	
		service.cntHit(no); // 조회수 증가
		//mav.addObject("nickName",(String)session.getAttribute("nickName"));

		String nickname = (String)session.getAttribute("nickname");
		if (nickname != null) {
			mav.addObject("resolveStatus", service.resolveStatus(nickname, no));
		}
		
		mav.setViewName("exercise/exerciseView");
		return mav;
	}

	//글 수정
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
			mav.addObject("nickname", nickname);
		}
		mav.setViewName("exercise/exerciseEdit");
		return mav;
	}
	
	@PostMapping("/exercise/exerciseEditOk")
	public ResponseEntity<String> exerciseEditOk(BoardVO vo, HttpSession session, HttpServletRequest request,MultipartHttpServletRequest mr) {
		MemberVO mvo = (MemberVO)request.getSession().getAttribute("userInfo");
		vo.setNickname(mvo.getNickname());
		ResponseEntity<String> entity =null;
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("text", "html",Charset.forName("UTF-8")));
        
        mr = (MultipartHttpServletRequest) request;
	
		
		String path = request.getSession().getServletContext().getRealPath("/img/exercise"); // 파일 업로드를 위한 업로드 위치의 절대 주소
        System.out.println(path);
        
    	MultipartFile file = mr.getFile("filename");
        
    	String orgFileName = file.getOriginalFilename();
        int point = orgFileName.lastIndexOf(".");
        String ext = orgFileName.substring(point+1);
        			
        File f = new File(path, System.currentTimeMillis()+"."+ext);//업로드한 파일
        			
        orgFileName = f.getName();

		try {
			file.transferTo(f);
			vo.setImg1(orgFileName);
			
			int result =service.exerciseUpdate(vo);
			System.out.println(result);
			//System.out.println(vo.getApplicantMax());
			String msg="<script>alert('글이 수정되었습니다.');location.href='/exercise/exerciseView?no="+vo.getNo()+"';</script>";
			entity=new ResponseEntity<String>(msg, headers, HttpStatus.OK);
			
		}catch (Exception e) {
			e.printStackTrace();
			String msg = "<script>alert('글 수정 실패 하였습니다.'); history.go(-1);</script>";
			entity=new ResponseEntity<String>(msg, headers, HttpStatus.BAD_REQUEST);
		}
		return entity;
				
	}
	// 글 삭제
	@GetMapping("/exercise/exerciseDel")
	public ModelAndView exerciseDel(BoardVO vo, int no, HttpSession session, ModelAndView mav) {
		String nickname = (String)session.getAttribute("nickName");
		vo.setNickname((String)session.getAttribute("logId"));
		int result = service.exerciseDelete(no, nickname);
		if(result>0) {
			//삭제됨
			System.out.println("글 삭제 성공");
			mav.setViewName("redirect:exerciseList");
			
		} else {
			//삭제 안됨
			System.out.println("삭제 실패하였습니다.");
			mav.addObject("no", no);
			mav.setViewName("redirect:exerciseView");
		}
		
		return mav;
	}
	// 글 보기
	@GetMapping("/exercise/exerciseReview")
	public ModelAndView exerciseReview(int no, BoardVO vo) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", service.exerciseSelect(no));
		mav.addObject("lst2", service.exerciseMemberShow(no));
		mav.setViewName("exercise/exerciseReview");
		return mav;
	}
	
	
	// 모두의 운동 리스트
	@GetMapping("/exercise/every_exerciseList")
	public ModelAndView every_exerciseList(ExercisePagingVO pVO) {
		ModelAndView mav = new ModelAndView();
		pVO.setTotalRecord(service.totalRecord1(pVO));
		
		mav.addObject("lst", service.every_exerciseList(pVO));
		mav.addObject("pVO", pVO);
	
		mav.setViewName("exercise/every_exerciseList");
		return mav;
	}
	
	// 모두의 운동 글쓰기
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
    public ResponseEntity<String> every_exerciseWriteOk(ExerciseVO vo, HttpServletRequest request, MultipartHttpServletRequest mr, ExerciseMemberVO emvo){
		MemberVO mvo = (MemberVO)request.getSession().getAttribute("userInfo");
		if(mvo!=null) {
			vo.setNickname(mvo.getNickname());
		}
		ResponseEntity<String> entity = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("text", "html",Charset.forName("UTF-8")));
       

        mr = (MultipartHttpServletRequest) request;
		MultipartFile file = mr.getFile("filename");
		
		String path = request.getSession().getServletContext().getRealPath("/img/every_exercise"); // 파일 업로드를 위한 업로드 위치의 절대 주소
        System.out.println(path);
       
        String orgFileName = file.getOriginalFilename();
        int point = orgFileName.lastIndexOf(".");
        String ext = orgFileName.substring(point+1);
        
        String filename = System.currentTimeMillis()+"."+ext;
        
        
        try {
        	if(!file.getOriginalFilename().equals("")) {
        		File f = new File(path, filename);
                orgFileName = f.getName();
            	file.transferTo(f);
            	vo.setImg(filename);
        	}
			
			//글등록 성공
        	service.every_exerciseInsert(vo);
        	ExerciseVO vo2=service.every_exerciseLastWriteNo(vo.getNickname());
			emvo.setExerciseNo(vo2.getNo());
			emvo.setNickname(vo.getNickname());
			service.exerciseMemberInsert(emvo);
			System.out.println("어떻게 들어갔니 "+emvo.getNickname()+"/"+emvo.getExerciseNo());
			service.exerciseMemberUpdate(emvo);

			//참가자 중 확정자 수 확인하는 함수
			int applicantCnt = service.exerciseMemberCnt(emvo.getExerciseNo());
			System.out.println(applicantCnt+"명");
			//확정자 수 update 함수
			service.exerciseApplicantCntSet(emvo.getExerciseNo(), applicantCnt);
			
			//글 목록으로 이동
			String msg = "<script>alert('글이 등록되었습니다.');location.href='/exercise/every_exerciseList';</script>";
			entity = new ResponseEntity<String>(msg, headers, HttpStatus.OK);
		} catch (Exception e) {
			// 글등록 실패
			e.printStackTrace();
			//글 등록 폼으로
			String msg = "<script>alert('글등록을 실패 하였습니다.');history.back();</script>";
			entity = new ResponseEntity<String>(msg, headers, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	

	// 모두의 운동 글보기
	@GetMapping("/exercise/every_exerciseView")
	public ModelAndView every_exerciseView(ExerciseVO vo, HttpSession session, int no, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		//System.out.println("뷰로 왔어요");
		MemberVO mvo = (MemberVO)request.getSession().getAttribute("userInfo");
		if(mvo!=null) {
			mav.addObject("nickname",mvo.getNickname());
			vo.setNickname(mvo.getNickname());
		}
		
		service.every_cntHit(no); // 조회수 증가
		ExerciseVO vo2 =service.every_exerciseSelect(no);
		String jsonStr=vo2.getPlaceinfo();
		System.out.println(jsonStr);
		JSONObject obj=new JSONObject(jsonStr);
		String addr=obj.getString("place_name");
		String x=obj.getString("x");
		String y=obj.getString("y");
		System.out.println(x+"gggggggggg"+y);
		mav.addObject("vo", vo2);
		mav.addObject("placeinfo",addr);
		mav.addObject("x",x);
		mav.addObject("y",y);
		mav.addObject("emvo",service.exerciseMemberShow(no));
		mav.setViewName("exercise/every_exerciseView");
		return mav;
	}
	// 모두의 운동 글 수정
	@GetMapping("/exercise/every_exerciseEdit")
	public ModelAndView every_exerciseEdit(int no, ExerciseVO vo, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		ExerciseVO vo2 =service.every_exerciseSelect(no);
		String jsonStr=vo2.getPlaceinfo();
		//System.out.println(jsonStr);
		JSONObject obj=new JSONObject(jsonStr);
		String addr=obj.getString("place_name");
		String x=obj.getString("x");
		String y=obj.getString("y");
		//System.out.println(x+"gggggggggg"+y);
		mav.addObject("vo", vo2);
		mav.addObject("placeinfo",addr);
		mav.addObject("x",x);
		mav.addObject("y",y);
		String nickname = (String)session.getAttribute("nickname");
		if (nickname != null) {
			mav.addObject("resolveStatus", service.resolveStatus(nickname, no));
		}else{
			mav.addObject("nickname", nickname);
		}
		mav.setViewName("exercise/every_exerciseEdit");
		return mav;
	}
	
	@PostMapping("/exercise/every_exerciseEditOk")
	public ResponseEntity<String> every_exerciseEditOk(ExerciseVO vo, HttpSession session,@RequestParam("filename") MultipartFile file ) {
		//vo.setNickname((String)request.getSession().getAttribute("nickname"));
		MemberVO mvo = (MemberVO)session.getAttribute("userInfo");
		vo.setNickname(mvo.getNickname());
		
		ResponseEntity<String> entity =null;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("text", "html",Charset.forName("UTF-8")));
		//MultipartHttpServletRequest mr  = (MultipartHttpServletRequest) request;
			
			
			String path = session.getServletContext().getRealPath("/img/every_exercise"); // 파일 업로드를 위한 업로드 위치의 절대 주소
	        System.out.println(path);
	        
	    	//MultipartFile file = mr.getFile("filename");
	    	String orgFileName=null;
	    	File f=null;
	        if(file!=null && !file.isEmpty()) {
		    	orgFileName = file.getOriginalFilename();
		        int point = orgFileName.lastIndexOf(".");
		        String ext = orgFileName.substring(point+1);
		        			
		        f = new File(path, System.currentTimeMillis()+"."+ext);//업로드한 파일
            		        			
		        orgFileName = f.getName();
		        
	        }
	        
		try {
			 if(file!=null && !file.isEmpty()) {
				 file.transferTo(f);
			 }
			 
			vo.setImg(orgFileName);
			System.out.println(vo.toString());
			int result =service.every_exerciseUpdate(vo);
			System.out.println("result ="+result);
			
			//System.out.println(vo.getApplicantMax());
			if(result>0) {
			String msg="<script>alert('글이 수정되었습니다.');location.href='/exercise/every_exerciseView?no="+vo.getNo()+"';</script>";
			entity=new ResponseEntity<String>(msg, headers, HttpStatus.OK);
			}else {
				String msg = "<script>alert('글 수정 실패!'); history.go(-1);</script>";
				entity=new ResponseEntity<String>(msg, headers, HttpStatus.BAD_REQUEST);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			String msg = "<script>alert('글 수정 실패 하였습니다.'); history.go(-1);</script>";
			entity=new ResponseEntity<String>(msg, headers, HttpStatus.BAD_REQUEST);
		}
		return entity;
				
	}
	// 모두의 운동 글 삭제
	@GetMapping("/exercise/every_exerciseDel")
	public ModelAndView every_exerciseDel(BoardVO bvo, ExerciseVO vo, int no, HttpSession session, ModelAndView mav) {
		String nickname = (String)session.getAttribute("nickname");
		bvo.setNickname((String)session.getAttribute("logId"));
		int result = service.every_exerciseDelete(no, nickname);
		if(result>0) {
			//삭제됨
			mav.setViewName("redirect:every_exerciseList");
			
		} else {
			//삭제 안됨
			System.out.println("삭제 실패하였습니다.");
			mav.addObject("no", no);
			mav.setViewName("redirect:every_exerciseView");
		}
		
		return mav;
	}
	// 모두의 운동 참가신청(작성자 외)
	@ResponseBody
	@GetMapping("/exercise/excerciseMemberOk")
	public int excerciseMemberOk (int exerciseNo, ExerciseMemberVO mvo, HttpServletRequest request) {
		MemberVO logMvo = (MemberVO)request.getSession().getAttribute("userInfo");
		mvo.setNickname(logMvo.getNickname());
		mvo.setExerciseNo(exerciseNo);
		int result = 0;
		try {

			System.out.println(exerciseNo+"번글");
			result = service.exerciseMemberInsert(mvo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	//모두의 운동 참가신청 취소(작성자 외)
	@ResponseBody
	@GetMapping("/exercise/excerciseMemberCancel")
	public void excerciseMemberCancel (int exerciseNo, ExerciseMemberVO mvo, ExerciseVO vo, HttpServletRequest request) {
		MemberVO logMvo = (MemberVO)request.getSession().getAttribute("userInfo");
		mvo.setNickname(logMvo.getNickname());
		mvo.setExerciseNo(exerciseNo);
		try {
			service.exerciseMemberDelete(mvo);
			//참가자 중 확정자 수 확인하는 함수
			int applicantCnt = service.exerciseMemberCnt(exerciseNo);
			System.out.println(applicantCnt+"명");
			//확정자 수 update 함수
			service.exerciseApplicantCntSet(exerciseNo, applicantCnt);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//모두의 운동 참가신청 수락(작성자)
	@ResponseBody
	@GetMapping("/exercise/excerciseStateOk")
	public boolean ridingStateOk(ExerciseVO vo,ExerciseMemberVO mvo) { 
		try { 
			System.out.println(mvo.getExerciseNo()+mvo.getNickname());
			service.exerciseMemberUpdate(mvo);
			//참가자 중 확정자 수 확인하는 함수
			int applicantCnt = service.exerciseMemberCnt(mvo.getExerciseNo());
			System.out.println(applicantCnt+"명");
			//확정자 수 update 함수
			service.exerciseApplicantCntSet(mvo.getExerciseNo(), applicantCnt);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
	}
	//모두의 운동 참가신청 거절(작성자)
	@ResponseBody
	@GetMapping("/exercise/excerciseStateDel")
	public boolean ridingStateDel(ExerciseVO vo,ExerciseMemberVO mvo) {
		try { 
			service.exerciseMemberDelete(mvo);
			return true;
		} catch (Exception e) { 
			System.out.println(e);
			e.printStackTrace();
			return false;
		}
	}
}