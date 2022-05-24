package com.finalproject.mohel.controller;

import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.finalproject.mohel.service.BoardService;
import com.finalproject.mohel.service.MyFoodService;
import com.finalproject.mohel.vo.BoardVO;
import com.finalproject.mohel.vo.MemberVO;
import com.finalproject.mohel.vo.MyFoodVO;
import com.finalproject.mohel.vo.PagingVO;
import com.finalproject.mohel.vo.ReplyVO;

@Controller
public class MyFoodController {
    @Autowired
    MyFoodService service;
    @Autowired
    BoardService bservice;
    
    @GetMapping("/myFoodMain")
    public String myFoodMain(String year, String month, String date, Model model, HttpSession session) {
        LocalDate current = LocalDate.now();
        if(year==null) {
            current = LocalDate.now();
        }else {
            current = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(date));
        }
        String dateParam = String.format("%d-%02d-%02d", current.getYear(), current.getMonthValue(), current.getDayOfMonth());
        MemberVO user = (MemberVO)session.getAttribute("userInfo");
        List<MyFoodVO> list = service.getMyFood(dateParam+"%", user.getNickname());

        HashMap<String, Integer> map = new HashMap<>();
        map.put("year", current.getYear());
        map.put("month", current.getMonthValue());
        map.put("date", current.getDayOfMonth());
        map.put("day", current.getDayOfWeek().getValue());
        
        model.addAttribute("dateJson", new JSONObject(map));
        model.addAttribute("today", dateParam);
        model.addAttribute("foodList", new JSONArray(list));
        model.addAttribute("recommendCal", service.getRecommendCalorie(user.getNickname()));
        
        return "food/myFoodMain";
    }
    
    @GetMapping("/everyFoodWrite")
    public String everyFoodWrite() {
        return "food/everyFoodWrite";
    }
    
    @PostMapping("/myFoodMain/saveInfo")
    @ResponseBody
    public String saveInfo(String action, String today, MyFoodVO vo, HttpSession session) {
    	String msg = "";
    	int result = 0;
    	String nickname = ((MemberVO)session.getAttribute("userInfo")).getNickname();
    	
    	if(action.equals("UPDATE")) {
			result = service.updateMyFood(nickname, today+"%", vo);
			if(result==1) {
				msg = "식단이 변경되었습니다";
			}else {
				msg = "식단이 변경되지 않았습니다!";
			}
    	}else if(action.equals("INSERT")) {
    		LocalDate current = LocalDate.now();
    		String date = String.format("%d-%02d-%02d", current.getYear(), current.getMonthValue(), current.getDayOfMonth());
    		String dateParam = date.equals(today) ? null : today+" 00:00:00"; 
    		result = service.insertMyFood(nickname, vo, dateParam);
    		if(result==1) {
    			msg = "식단이 생성되었습니다";
    		}else {
    			msg = "식단 생성에 실패했습니다";
    		}
    	}
    	return msg;
    }
    
    @PostMapping("/everyFoodWriteOk")
    public String everyFoodWriteOk(BoardVO bvo, HttpServletRequest request) {
    	String path = request.getSession().getServletContext().getRealPath("/img/food");
    	//System.out.println("실제 경로 = "+path);

    	try {
			MultipartHttpServletRequest mr = (MultipartHttpServletRequest)request;

			List<MultipartFile> files = mr.getFiles("images");
			//System.out.println("업로드 파일 수 -> "+files.size());
			
			if(files!=null) {
				for(int i=0; i<files.size(); i++) {
					MultipartFile mf = files.get(i);
					
					String orgFileName = mf.getOriginalFilename();
					//System.out.println(i+1+"번째 파일명 -> "+ orgFileName );
					int point = orgFileName.lastIndexOf(".");
					String ext = orgFileName.substring(point);
					String newFileName = System.currentTimeMillis()+i+ext;
					//System.out.println("변환후 파일명 -> "+newFileName);
					File f = new File(path, newFileName);
					
					try {
						mf.transferTo(f);
						//System.out.println(f);
					}catch(Exception ee) {
						ee.printStackTrace();
					}
					
					if(i==0) {
						bvo.setImg1(newFileName);
					}else if(i==1) {
						bvo.setImg2(newFileName);
					}else {
						bvo.setImg3(newFileName);
					}
				}
				bvo.setCategory("everyMeal");
				bvo.setNickname(((MemberVO)request.getSession().getAttribute("userInfo")).getNickname());
				service.everyFoodWriteOk(bvo);
				//System.out.println("글쓰기 완료");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
    	
    	return "redirect:/everyFoodList?pageNum=1";
    }
    
    @GetMapping("/everyFoodList")
    public String everyFoodList(PagingVO pvo, Model model) {
    	//게시물 정보
    	String[] partname = {"", "아침", "오전간식", "점심", "오후간식", "저녁"};
    	List<BoardVO> list = service.everyFoodList(pvo.getPageNum());
    	for(BoardVO bvo:list) {
    		bvo.setContents(partname[Integer.parseInt(bvo.getMeals())]);
    		JSONArray jarr = new JSONArray(bvo.getFoodcalories());
    		int alen = jarr.length();
    		int sum = 0;
    		for(int i=0; i<alen; i++) {
    			sum += jarr.getInt(i);
    		}
    		bvo.setSumcalories(sum);
    		bvo.setWritedate(bvo.getWritedate().substring(0, 10));
    	}
    	model.addAttribute("everyMealList", list);
    	
    	//페이지네이션 정보
    	int totalRecord = bservice.totalRecord("everyMeal", pvo);
    	int totalPage = totalRecord/6;
    	if(totalRecord%6!=0) totalPage++;
    	pvo.setTotalPage(totalPage);
    	model.addAttribute("pvo", pvo);
    	
    	//베스트유저 정보
    	model.addAttribute("bestUser", service.bestUser());
    	
    	return "food/everyFoodList";
    }
    
    @GetMapping("/everyFoodView")
    public String everyFoodView(int no, Model model) {
    	service.hitUp(no);
    	String[] partname = {"", "아침", "오전간식", "점심", "오후간식", "저녁"};
    	BoardVO bvo = service.everyFoodView(no);
    	bvo.setMeals(partname[Integer.parseInt(bvo.getMeals())]);
    	JSONArray jarr = new JSONArray(bvo.getFoodcalories());
		int alen = jarr.length();
		int sum = 0;
		for(int i=0; i<alen; i++) {
			sum += jarr.getInt(i);
		}
		bvo.setSumcalories(sum);
    	model.addAttribute("bvo", bvo);
    	model.addAttribute("reply", service.everyFoodReply(no));
    	return "food/everyFoodView";
    }
    
    @PostMapping("/everyFoodReplyOk")
    public String everyFoodReplyOk(int no, String contents, HttpSession session) {
    	//String referer = request.getHeader("Referer"); //헤더에서 이전 페이지 주소를 가지고 옴
    	String nickname = ((MemberVO)session.getAttribute("userInfo")).getNickname();
    	service.everyFoodReplyOk(no, nickname, contents);
    	return "redirect:/everyFoodView?no="+no;
    }
    
    @GetMapping("/everyFoodEdit")
    public String everyFoodEdit(int no, Model model) {
    	String[] partname = {"", "아침", "오전간식", "점심", "오후간식", "저녁"};
    	BoardVO bvo = service.everyFoodView(no);
    	bvo.setMeals(partname[Integer.parseInt(bvo.getMeals())]);
    	model.addAttribute("bvo", bvo);
    	return "food/everyFoodEdit";
    }
    
    @PostMapping("/everyFoodEditOk")
    public String everyFoodEditOk(BoardVO bvo, HttpServletRequest request) {
    	String path = request.getSession().getServletContext().getRealPath("/img/food");
    	//System.out.println("실제 경로 = "+path);

    	try {
			MultipartHttpServletRequest mr = (MultipartHttpServletRequest)request;

			List<MultipartFile> files = mr.getFiles("images");
			bvo.setCategory("everyMeal");
			bvo.setNickname(((MemberVO)request.getSession().getAttribute("userInfo")).getNickname());
			
			if(files.get(0).getSize()>0) {
				//System.out.println("파일있는부분 진입");
				BoardVO bvof = service.getDBImg(bvo.getNo());
				for(int i=1; i<=3; i++) {
					String img = null;
					if(i==1) {
						img = bvof.getImg1();
					}else if(i==2) {
						img = bvof.getImg2();
					}else {
						img = bvof.getImg3();
					}
					
					if(img!=null) {
						File f = new File(path, img);
						//System.out.println("삭제할 파일명 = "+img);
						//System.out.println("삭제여부 = "+f.delete());
					}
				}
				for(int i=0; i<files.size(); i++) {
					MultipartFile mf = files.get(i);
					
					String orgFileName = mf.getOriginalFilename();
					//System.out.println(i+1+"번째 파일명 -> "+ orgFileName );
					int point = orgFileName.lastIndexOf(".");
					String ext = orgFileName.substring(point);
					String newFileName = System.currentTimeMillis()+i+ext;
					//System.out.println("변환후 파일명 -> "+newFileName);
					File f = new File(path, newFileName);
					
					try {
						mf.transferTo(f);
						//System.out.println(f);
					}catch(Exception ee) {
						ee.printStackTrace();
					}
					
					if(i==0) {
						bvo.setImg1(newFileName);
					}else if(i==1) {
						bvo.setImg2(newFileName);
					}else {
						bvo.setImg3(newFileName);
					}
				}
				//DB UPDATE
				service.updateBoardWithFile(bvo);
			}else {
				//System.out.println("파일없는 부분 진입");
				service.updateBoardNoFile(bvo);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
    	
    	return "redirect:/everyFoodView?no="+bvo.getNo();
    }
    
    @GetMapping("/everyFoodDel")
    public String everyFoodDel(BoardVO bvo, HttpSession session) {
    	String nickname = ((MemberVO)session.getAttribute("userInfo")).getNickname();
    	bvo.setNickname(nickname);
    	int result = service.everyFoodDel(bvo);
    	if(result>0) {
    		return "redirect:/everyFoodList";
    	}else {
    		return "redirect:/everyFoodView?no="+bvo.getNo();
    	}
    }
    
    @PostMapping("/everyFoodReplyDel")
    @ResponseBody
    public int everyFoodReplyDel(int no) {
    	return service.everyFoodReplyDel(no);
    }
    
    @PostMapping("/replyEditOk")
    public String replyEditOk(ReplyVO rvo) {
    	service.replyEditOk(rvo);
    	return "redirect:/everyFoodView?no="+rvo.getBoardNo();
    }
}