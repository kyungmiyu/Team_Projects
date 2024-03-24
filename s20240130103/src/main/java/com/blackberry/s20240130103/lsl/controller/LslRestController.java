package com.blackberry.s20240130103.lsl.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blackberry.s20240130103.lsl.Service.LslService;
import com.blackberry.s20240130103.lsl.model.LslCommReply;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LslRestController {
	
	private final LslService ls;
	
	
	@GetMapping("/userno")
	public Long boardUserNo(HttpServletRequest request) {
	    Long user_no = (Long) request.getSession().getAttribute("user_no");
	    System.out.println("sessionUseNo ->" + user_no);
	    return user_no;
	}
	
	
	
	@GetMapping("/getUserInfo")
	public String getUserNic(HttpServletRequest request) {
		String user_nic = (String)request.getSession().getAttribute("user_nic");
		return user_nic;
		
	}
	
	// 댓글
	
	// 게시판 댓글 리스트
	@GetMapping("/reply")
	public List<LslCommReply> replyBoardFreeAskList(@RequestParam("cboard_no") int cboard_no,HttpServletRequest request, Model model) {
	    System.out.println("LslRestController replyBoardFreeList Start..");
	    List<LslCommReply> replyBoardFreeAskList = ls.replyBoardFreeAskList(cboard_no);
	    System.out.println("LslRestController replyBoardFreeList.size() ->" + replyBoardFreeAskList.size());
	    
	   
	    // 댓글 목록에 댓글 번호를 추가
	    for (LslCommReply reply : replyBoardFreeAskList) {
	        // 각 댓글의 사용자 프로필을 가져옴
	        String userProfile = reply.getUser_profile();
	        System.out.println("userProfile : " + userProfile);
	        
	        // 필요한 작업 수행
	        
	        // 댓글 번호를 가져와서 설정
	        reply.setCreply_no(reply.getCreply_no());
	    }
	    
	    return replyBoardFreeAskList;
	}
   
    
   //  게시판 댓글 등록 
	@PostMapping("/replys/insert")
	public int insertBoardReply(@RequestBody LslCommReply lslCommReply, HttpServletRequest request) {
		Long user_no = (Long) request.getSession().getAttribute("user_no");
		System.out.println("LslRestController insertBoardReply Start....");
		lslCommReply.setUser_no(user_no);
		int boardFreeAskResult = ls.insertBoardReply(lslCommReply);
		System.out.println("LslRestController boardFreeAskResult ->" + boardFreeAskResult);
		return boardFreeAskResult;
	}
	
	// 게시판 댓글 삭제 
	@DeleteMapping("/replys/delete")
	public int deleteBoardReply(@RequestParam("creply_no") int creply_no ) {
		System.out.println("LslRestController deleteBoardReply Start....");
		int boardFreeAskResult = ls.deleteBoardReply(creply_no);
		
		return boardFreeAskResult;
	}
	
	@PutMapping("/replys/modify")
	public int modifyBoardReply(@RequestBody LslCommReply lslCommReply) {
		System.out.println("modifyBoardReply : " + lslCommReply);
		return ls.modifyBoardReply(lslCommReply);
	}



	// 대댓글 등록
		@PostMapping("/rereplys/reinsert")
		public int reinsertBoardReply(@RequestBody LslCommReply lslCommReply, HttpServletRequest request) {
			Long user_no = (Long) request.getSession().getAttribute("user_no");
		    System.out.println("reinsertBoardReply user_no" + user_no);
		    System.out.println("test : " + lslCommReply);
		    
		    lslCommReply.setUser_no(user_no);
		    
		    System.out.println("reinsertBoardReply user_no" + user_no);

		    LslCommReply reReply = ls.reReply(lslCommReply);
		    System.out.println("reReply ->>" +reReply);
		    
		    lslCommReply.setCreply_group(reReply.getCreply_group());
		    lslCommReply.setCreply_level(reReply.getCreply_level() +1);
		    lslCommReply.setCreply_indent(reReply.getCreply_indent() +1);
		   
		  int update =  ls.updateReply(lslCommReply);
		   
		   int boardReReplyResult = ls.insertBoardReReply(lslCommReply);
		   
		  return boardReReplyResult;
		}



}
