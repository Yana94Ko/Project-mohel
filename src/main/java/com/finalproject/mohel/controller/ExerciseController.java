package com.finalproject.mohel.controller;

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

import com.finalproject.mohel.MohelApplication;
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
		//System.out.println(nickname);
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
	
	@PostMapping("/exercise/exerciseWriteOk")
    public ResponseEntity<String> exerciseWriteOk(BoardVO vo, HttpServletRequest request, MultipartHttpServletRequest mr){

		//vo.setNickname((String)request.getSession().getAttribute("nickname"));
		MemberVO mvo = (MemberVO)request.getSession().getAttribute("userInfo");
		vo.setNickname(mvo.getNickname());

		ResponseEntity<String> entity = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("text", "html",Charset.forName("UTF-8")));
       
        mr = (MultipartHttpServletRequest) request;

		String path = request.getSession().getServletContext().getRealPath("/img/exercise"); // ?????? ???????????? ?????? ????????? ????????? ?????? ??????
        //System.out.println(path);
        
    	MultipartFile file = mr.getFile("filename");
    	//?????? ?????? ???
    	if(!file.getOriginalFilename().equals("")) {
	    	String orgFileName = file.getOriginalFilename();
	        int point = orgFileName.lastIndexOf(".");
	        String ext = orgFileName.substring(point+1);
	        			
	        File f = new File(path, System.currentTimeMillis()+"."+ext);//???????????? ??????
	        			
	        orgFileName = f.getName();
	        //String filename = file.getOriginalFilename();
			//File uploadFile = new File(path, filename);
	        
	        try {
	        	file.transferTo(f);
				//vo.setImg1(file.getOriginalFilename());
	        	vo.setImg1(orgFileName);
	        	
				//????????? ??????
	        	service.exerciseInsert(vo);
				
				
				//??? ???????????? ??????
				String msg = "<script>alert('?????? ?????????????????????.');location.href='/exercise/exerciseList';</script>";
				entity = new ResponseEntity<String>(msg, headers, HttpStatus.OK);
			} catch (Exception e) {
				// ????????? ??????
				e.printStackTrace();
				//??? ?????? ?????????
				String msg = "<script>alert('???????????? ?????? ???????????????.');history.back();</script>";
				entity = new ResponseEntity<String>(msg, headers, HttpStatus.BAD_REQUEST);
			}
			return entity;
    	}else {
    		try {	        	
				//????????? ??????
	        	service.exerciseInsert(vo);
				
				
				//??? ???????????? ??????
				String msg = "<script>alert('?????? ?????????????????????.');location.href='/exercise/exerciseList';</script>";
				entity = new ResponseEntity<String>(msg, headers, HttpStatus.OK);
			} catch (Exception e) {
				// ????????? ??????
				e.printStackTrace();
				//??? ?????? ?????????
				String msg = "<script>alert('???????????? ?????? ???????????????.');history.back();</script>";
				entity = new ResponseEntity<String>(msg, headers, HttpStatus.BAD_REQUEST);
			}
			return entity;
    	}
	}
	//??? ??????
	@GetMapping("/exercise/exerciseView")
	public ModelAndView exerciseView(@RequestParam("no") int no, BoardVO vo, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo",service.exerciseSelect(no));
	
		service.cntHit(no); // ????????? ??????
		//mav.addObject("nickName",(String)session.getAttribute("nickName"));

		String nickname = (String)session.getAttribute("nickname");
		if (nickname != null) {
			mav.addObject("resolveStatus", service.resolveStatus(nickname, no));
		}
		
		mav.setViewName("exercise/exerciseView");
		return mav;
	}

	//??? ??????
	@GetMapping("/exercise/exerciseEdit")
	public ModelAndView exerciseEdit(int no, BoardVO vo, HttpSession session) {
		//System.out.println(no);
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
	
		
		String path = request.getSession().getServletContext().getRealPath("/img/exercise"); // ?????? ???????????? ?????? ????????? ????????? ?????? ??????
        //System.out.println(path);
        
    	MultipartFile file = mr.getFile("filename");
    	//?????? ?????? ???
    	if(!file.getOriginalFilename().equals("")) {
	    	String orgFileName = file.getOriginalFilename();
	        int point = orgFileName.lastIndexOf(".");
	        String ext = orgFileName.substring(point+1);
	        			
	        File f = new File(path, System.currentTimeMillis()+"."+ext);//???????????? ??????
	        			
	        orgFileName = f.getName();
	
			try {
				file.transferTo(f);
				vo.setImg1(orgFileName);
				
				int result =service.exerciseUpdate(vo);
				//System.out.println(result);
				//System.out.println(vo.getApplicantMax());
				String msg="<script>alert('?????? ?????????????????????.');location.href='/exercise/exerciseView?no="+vo.getNo()+"';</script>";
				entity=new ResponseEntity<String>(msg, headers, HttpStatus.OK);
				
			}catch (Exception e) {
				e.printStackTrace();
				String msg = "<script>alert('??? ?????? ?????? ???????????????.'); history.go(-1);</script>";
				entity=new ResponseEntity<String>(msg, headers, HttpStatus.BAD_REQUEST);
			}
			return entity;
    	}else {
    		try {				
				int result =service.exerciseUpdate(vo);
				//System.out.println(result);
				//System.out.println(vo.getApplicantMax());
				String msg="<script>alert('?????? ?????????????????????.');location.href='/exercise/exerciseView?no="+vo.getNo()+"';</script>";
				entity=new ResponseEntity<String>(msg, headers, HttpStatus.OK);
				
			}catch (Exception e) {
				e.printStackTrace();
				String msg = "<script>alert('??? ?????? ?????? ???????????????.'); history.go(-1);</script>";
				entity=new ResponseEntity<String>(msg, headers, HttpStatus.BAD_REQUEST);
			}
			return entity;
    	}
				
	}
	// ??? ??????
	@GetMapping("/exercise/exerciseDel")
	public ModelAndView exerciseDel(BoardVO vo, int no, HttpSession session, ModelAndView mav) {
		String nickname = (String)session.getAttribute("nickName");
		vo.setNickname((String)session.getAttribute("logId"));
		int result = service.exerciseDelete(no, nickname);
		if(result>0) {
			//?????????
			//System.out.println("??? ?????? ??????");
			mav.setViewName("redirect:exerciseList");
			
		} else {
			//?????? ??????
			//System.out.println("?????? ?????????????????????.");
			mav.addObject("no", no);
			mav.setViewName("redirect:exerciseView");
		}
		
		return mav;
	}
	// ??? ??????
	@GetMapping("/exercise/exerciseReview")
	public ModelAndView exerciseReview(int no, BoardVO vo) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", service.exerciseSelect(no));
		mav.addObject("lst2", service.exerciseMemberShow(no));
		mav.setViewName("exercise/exerciseReview");
		return mav;
	}
	
	
	// ????????? ?????? ?????????
	@GetMapping("/exercise/every_exerciseList")
	public ModelAndView every_exerciseList(ExercisePagingVO pVO) {
		System.out.println("???????????? ??????");
		ModelAndView mav = new ModelAndView();
		pVO.setTotalRecord(service.totalRecord1(pVO));
		
		mav.addObject("lst", service.every_exerciseList(pVO));
		mav.addObject("pVO", pVO);
	
		mav.setViewName("exercise/every_exerciseList");
		return mav;
	}
	
	// ????????? ?????? ?????????
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
		
		String path = request.getSession().getServletContext().getRealPath("/img/every_exercise"); // ?????? ???????????? ?????? ????????? ????????? ?????? ??????
        //System.out.println(path);
       
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
			
			//????????? ??????
        	service.every_exerciseInsert(vo);
        	ExerciseVO vo2=service.every_exerciseLastWriteNo(vo.getNickname());
			emvo.setExerciseNo(vo2.getNo());
			emvo.setNickname(vo.getNickname());
			service.exerciseMemberInsert(emvo);
			//System.out.println("????????? ???????????? "+emvo.getNickname()+"/"+emvo.getExerciseNo());
			service.exerciseMemberUpdate(emvo);

			//????????? ??? ????????? ??? ???????????? ??????
			int applicantCnt = service.exerciseMemberCnt(emvo.getExerciseNo());
			//System.out.println(applicantCnt+"???");
			//????????? ??? update ??????
			service.exerciseApplicantCntSet(emvo.getExerciseNo(), applicantCnt);
			
			//??? ???????????? ??????
			String msg = "<script>alert('?????? ?????????????????????.');location.href='/exercise/every_exerciseList';</script>";
			entity = new ResponseEntity<String>(msg, headers, HttpStatus.OK);
		} catch (Exception e) {
			// ????????? ??????
			e.printStackTrace();
			//??? ?????? ?????????
			String msg = "<script>alert('???????????? ?????? ???????????????.');history.back();</script>";
			entity = new ResponseEntity<String>(msg, headers, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	

	// ????????? ?????? ?????????
	@GetMapping("/exercise/every_exerciseView")
	public ModelAndView every_exerciseView(ExerciseVO vo, HttpSession session, int no, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		//System.out.println("?????? ?????????");
		MemberVO mvo = (MemberVO)request.getSession().getAttribute("userInfo");
		if(mvo!=null) {
			mav.addObject("nickname",mvo.getNickname());
			vo.setNickname(mvo.getNickname());
		}
		
		service.every_cntHit(no); // ????????? ??????
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
		mav.addObject("emvo",service.exerciseMemberShow(no));
		mav.setViewName("exercise/every_exerciseView");
		return mav;
	}
	// ????????? ?????? ??? ??????
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
			
			
			String path = session.getServletContext().getRealPath("/img/every_exercise"); // ?????? ???????????? ?????? ????????? ????????? ?????? ??????
	        //System.out.println(path);
	        
	    	//MultipartFile file = mr.getFile("filename");
	    	String orgFileName=null;
	    	File f=null;
	        if(file!=null && !file.isEmpty()) {
		    	orgFileName = file.getOriginalFilename();
		        int point = orgFileName.lastIndexOf(".");
		        String ext = orgFileName.substring(point+1);
		        			
		        f = new File(path, System.currentTimeMillis()+"."+ext);//???????????? ??????
            		        			
		        orgFileName = f.getName();
		        
	        }
	        
		try {
			 if(file!=null && !file.isEmpty()) {
				 file.transferTo(f);
			 }
			 
			vo.setImg(orgFileName);
			//System.out.println(vo.toString());
			int result =service.every_exerciseUpdate(vo);
			//System.out.println("result ="+result);
			
			//System.out.println(vo.getApplicantMax());
			if(result>0) {
			String msg="<script>alert('?????? ?????????????????????.');location.href='/exercise/every_exerciseView?no="+vo.getNo()+"';</script>";
			entity=new ResponseEntity<String>(msg, headers, HttpStatus.OK);
			}else {
				String msg = "<script>alert('??? ?????? ??????!'); history.go(-1);</script>";
				entity=new ResponseEntity<String>(msg, headers, HttpStatus.BAD_REQUEST);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			String msg = "<script>alert('??? ?????? ?????? ???????????????.'); history.go(-1);</script>";
			entity=new ResponseEntity<String>(msg, headers, HttpStatus.BAD_REQUEST);
		}
		return entity;
				
	}
	// ????????? ?????? ??? ??????
	@GetMapping("/exercise/every_exerciseDel")
	public ModelAndView every_exerciseDel(BoardVO bvo, ExerciseVO vo, int no, HttpSession session, ModelAndView mav) {
		MemberVO userInfo = (MemberVO)session.getAttribute("userInfo");
		String nickname = userInfo.getNickname();
		
		int result = service.every_exerciseDelete(no, nickname);
		if(result>0) {
			//?????????
			mav.setViewName("redirect:every_exerciseList");
			String imgRealPath = "";
			if(vo.getImg()!=null && vo.getImg().startsWith("/img/")) {
				imgRealPath = session.getServletContext().getRealPath(vo.getImg());
			}
			MohelApplication.removeImg(imgRealPath);
		} else {
			//?????? ??????
			mav.addObject("no", no);
			mav.setViewName("redirect:every_exerciseView");
		}
		return mav;
	}
	// ????????? ?????? ????????????(????????? ???)
	@ResponseBody
	@GetMapping("/exercise/excerciseMemberOk")
	public int excerciseMemberOk (int exerciseNo, ExerciseMemberVO mvo, HttpServletRequest request) {
		MemberVO logMvo = (MemberVO)request.getSession().getAttribute("userInfo");
		mvo.setNickname(logMvo.getNickname());
		mvo.setExerciseNo(exerciseNo);
		int result = 0;
		try {

			//System.out.println(exerciseNo+"??????");
			result = service.exerciseMemberInsert(mvo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	//????????? ?????? ???????????? ??????(????????? ???)
	@ResponseBody
	@GetMapping("/exercise/excerciseMemberCancel")
	public void excerciseMemberCancel (int exerciseNo, ExerciseMemberVO mvo, ExerciseVO vo, HttpServletRequest request) {
		MemberVO logMvo = (MemberVO)request.getSession().getAttribute("userInfo");
		mvo.setNickname(logMvo.getNickname());
		mvo.setExerciseNo(exerciseNo);
		try {
			service.exerciseMemberDelete(mvo);
			//????????? ??? ????????? ??? ???????????? ??????
			int applicantCnt = service.exerciseMemberCnt(exerciseNo);
			//System.out.println(applicantCnt+"???");
			//????????? ??? update ??????
			service.exerciseApplicantCntSet(exerciseNo, applicantCnt);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//????????? ?????? ???????????? ??????(?????????)
	@ResponseBody
	@GetMapping("/exercise/excerciseStateOk")
	public boolean ridingStateOk(ExerciseVO vo,ExerciseMemberVO mvo) { 
		try { 
			//System.out.println(mvo.getExerciseNo()+mvo.getNickname());
			service.exerciseMemberUpdate(mvo);
			//????????? ??? ????????? ??? ???????????? ??????
			int applicantCnt = service.exerciseMemberCnt(mvo.getExerciseNo());
			//System.out.println(applicantCnt+"???");
			//????????? ??? update ??????
			service.exerciseApplicantCntSet(mvo.getExerciseNo(), applicantCnt);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
	}
	//????????? ?????? ???????????? ??????(?????????)
	@ResponseBody
	@GetMapping("/exercise/excerciseStateDel")
	public boolean ridingStateDel(ExerciseVO vo,ExerciseMemberVO mvo) {
		try { 
			service.exerciseMemberDelete(mvo);
			return true;
		} catch (Exception e) { 
			//System.out.println(e);
			e.printStackTrace();
			return false;
		}
	}
}