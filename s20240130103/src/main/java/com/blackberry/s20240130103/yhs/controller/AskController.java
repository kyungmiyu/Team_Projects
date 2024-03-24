package com.blackberry.s20240130103.yhs.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blackberry.s20240130103.lhs.service.LhsPaging;
import com.blackberry.s20240130103.yhs.model.Ask;
import com.blackberry.s20240130103.yhs.service.AskService;
import com.blackberry.s20240130103.yhs.service.AskServiceImpl;
import com.blackberry.s20240130103.yhs.service.Paging;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AskController {
	private final AskService askService;
	
	@GetMapping("askMain")
	public String askMain() {
		return "yhs/askMain";
	}
	
	@GetMapping("askList") 
	public String askList(HttpServletRequest request , Model model, Ask ask) {
		System.out.println("AskController askList start...");
		HttpSession session = request.getSession();
		Long user_no = (Long)session.getAttribute("user_no");
		System.out.println("AskController askList user_no->"+user_no);
		ask.setUser_no(user_no);
	    int totalAsk =  askService.totalAsk(ask);
		System.out.println("AskController Start totalAsk->"+totalAsk );
//		
		// Paging 작업
		Paging page = new Paging(totalAsk, ask.getCurrentPage());
		System.out.println("test page : " + page );
//		// Parameter ask --> Page만 추가 Setting
		ask.setStart(page.getStart());   // 시작시 1
		ask.setEnd(page.getEnd());       // 시작시 10 
		System.out.println("AskController askList ask->"+ask);

		List<Ask> listAsk = askService.listAsk(ask);
		System.out.println("AskController  listAsk.size()=>" + listAsk.size());

		model.addAttribute("listAsk" , listAsk);
		model.addAttribute("page", page);
		
		return "yhs/ask";
	}
	
	// 자유 게시판 상세내용
	// 세션이 필요할 때 - >
	@GetMapping(value = "askContent")
	public String askContent(HttpServletRequest request, Model model) {
		String admin_no = request.getParameter("admin_no");
		Ask ask = new Ask();
		ask.setUser_no((Long)request.getSession().getAttribute("user_no"));
		ask.setAdmin_no(Integer.parseInt(admin_no));
		System.out.println("AskController askContent ask->"+ask);
		Ask askContent = askService.askContent(ask);
		System.out.println("AskController askContent askContent->"+askContent);
		if(askContent.getAdmin_reply_chk() == 1) {
			Ask responseAsk = askService.selectAskResponse(ask);
			System.out.println(responseAsk);
			responseAsk.setAdmin_content(responseAsk.getAdmin_content().replace("\r\n","<br>"));
			model.addAttribute("askResponse", responseAsk);
		}
		model.addAttribute("askContent", askContent);
		return "yhs/askContent";
	}
	
	@GetMapping(value = "askForm")
	public String askForm(HttpServletRequest request, Model model) {
		System.out.println("AskController Start askForm...");
		
		String adminStart = request.getParameter("admin_start");
		// 세션에서 보내는 사람의 아이디 가져오기
        Long userNo = (Long) request.getSession().getAttribute("user_no");
        
     // 모델에 데이터 추가 (세션ID, 유저리스트)
        model.addAttribute("userNo", userNo);

		return "yhs/askForm";
	}

	@PostMapping(value = "askWrite")
	public String askWrite(Ask ask ,Model model,HttpServletRequest request ) {
		System.out.println("AskController Start askWrite..." );
		Long userNo = (Long) request.getSession().getAttribute("user_no");
		ask.setUser_no(userNo);
		System.out.println("AskController askWrite ask->" + ask);
		int insertResult = askService.insertAsk(ask);
		System.out.println("AskController insertResult insertResult->"+insertResult );
		return "redirect:/askList";

	}
	@GetMapping(value = "askContentReply")
	public String askContentReply() {
		System.out.println("askContentReply");
		return "yhs/askContentReply";
	}
	
	@RequestMapping(value="deleteAsk")
	public String deleteAsk(Ask ask, Model model) {
		System.out.println("AskController Start delete..." );
		// name -> Service, dao , mapper
		int result = askService.deleteAsk(ask.getAdmin_no());
		return "redirect:/askList";
	}
	
	
	
}
