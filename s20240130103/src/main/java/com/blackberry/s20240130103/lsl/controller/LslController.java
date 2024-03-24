package com.blackberry.s20240130103.lsl.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.blackberry.s20240130103.lsl.Service.BoardFreeAskPaging;
import com.blackberry.s20240130103.lsl.Service.LslService;
import com.blackberry.s20240130103.lsl.model.LslBoardComm;
import com.blackberry.s20240130103.lsl.model.LslboardFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LslController {

	private final LslService ls;

	/* 자유 게시판 */

	// 자유 게시판 리스트 및 Paging
	@RequestMapping(value = "boardFree")
	public String boardFreeList(Model model, LslBoardComm lslBoardComm) {
		System.out.println("LslController boardFreeList Start...");
		int totalBoardFree = ls.totalBoardFree();
		System.out.println("LslController totalBoardFreeList ->" + totalBoardFree);

		// paging 작업
		BoardFreeAskPaging bfpage = new BoardFreeAskPaging(totalBoardFree, lslBoardComm.getCurrentPage());
		lslBoardComm.setStart(bfpage.getStart());
		lslBoardComm.setEnd(bfpage.getEnd());
		System.out.println(lslBoardComm);

		List<LslBoardComm> boardFreeList = ls.boardFreeList(lslBoardComm);
		System.out.println("LslController boardFreeList.size() ->" + boardFreeList.size());

		model.addAttribute("boardFreeList", boardFreeList);
		model.addAttribute("totalBoardFree", totalBoardFree);
		model.addAttribute("bfpage", bfpage);

		return "lsl/boardFree";
	}

	
	
	// 자유 게시판 검색
	@GetMapping(value = "boardFreeSearch")
	public String boardFreeSearch(LslBoardComm lslBoardComm, Model model, @RequestParam(name="type",  defaultValue = "all") String type, @RequestParam(name="keyword", required = false) String keyword) {
		System.out.println("LslController boardFreeSearch Start...");
		System.out.println("Search Type : " + type);
		System.out.println("Search Keyword : " + keyword );
		
		// 검색 게시글 수 가져오기 
		int totalBoardSearchFree = ls.totalBoardSearchFree(lslBoardComm, keyword, type);
		System.out.println("LslController totalBoardFreeList ->" + totalBoardSearchFree);

		
		// paging 작업
		BoardFreeAskPaging bfpage = new BoardFreeAskPaging(totalBoardSearchFree, lslBoardComm.getCurrentPage());
		
		List<LslBoardComm> boardFreeList = ls.boardFreeSearch(lslBoardComm,keyword, type, bfpage.getStart(), bfpage.getEnd() );
		System.out.println("LslController boardFreeList boardFreeSearch.size() ->" + boardFreeList.size());
		System.out.println(boardFreeList);

		
		model.addAttribute("totalBoardSearchFree", totalBoardSearchFree);
		model.addAttribute("bfpage", bfpage);
		model.addAttribute("boardFreeList", boardFreeList);
		model.addAttribute("keyword", keyword);
		model.addAttribute("type", type);

		return "lsl/boardFree";
	}

	
	
	// 자유 게시판 상세내용
	@GetMapping(value = "boardFreeContents")
	public String boardFreeContents(HttpServletRequest request, Model model, LslBoardComm lslBoardComm) {
		int cboard_no = Integer.parseInt(request.getParameter("cboard_no"));
		
		System.out.println("boardFreeContents cboard_no : " + cboard_no);
		LslBoardComm boardFreeContents = ls.boardFreeContents(cboard_no);
		System.out.println("LslController replyBoardFreeList Start..");

		// 자유게시판 조회수
		int boardFreeViewCnt = ls.boardFreeViewCnt(lslBoardComm);

		// 자유 게시판 댓글수
		int boardReplyCnt = ls.boardReplyCnt(cboard_no);
		
		// 파일 첨부 된 글
		List<LslboardFile> boardFreeFile = ls.boardFreeFile(cboard_no);
		
		model.addAttribute("boardFreeFile",boardFreeFile);
		model.addAttribute("boardReplyCnt", boardReplyCnt);
		model.addAttribute("boardFreeViewCnt", boardFreeViewCnt);
		model.addAttribute("boardFreeContents", boardFreeContents);
		return "lsl/boardFreeContents";
	}

	
	
	// 자유 게시판 파일 다운로드 
	@GetMapping("/boardFreeFileDownload")
	public void boardFreeFileDownload(@RequestParam("cboard_file_name") String fileName,  @RequestParam("cboard_file_user_name") String userFileName,  HttpServletRequest request, HttpServletResponse response) {
		System.out.println("boardFileUpload cboard_no ->" + fileName);
		System.out.println("boardFileUpload fileCount ->" + userFileName);

	    // 파일 경로 설정
	    String boardfilePath = request.getSession().getServletContext().getRealPath("/upload/boardFile/") + fileName;
        File file = new File(boardfilePath);

	        
	        // 파일이 존재하는 경우에만 처리
	        if (file.exists()) {
	            // MIME 타입 설정
	            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
	            if (mimeType == null) {
	                mimeType = "application/octet-stream";
	            }
	            response.setContentType(mimeType);
	            
	            try {
	            // 파일 다운로드 설정
	            response.setContentType("application.octet-stream");
	            response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(userFileName, "UTF-8") + "\"");
	            response.setContentLength((int) file.length());
	            
	            // 파일 다운로드 실행
	            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
	            FileCopyUtils.copy(inputStream, response.getOutputStream());
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("LslController boardFileUpload Exception ->" + e.getMessage());
	    }
	        }
	}
	
	// 자유 게시판 글 작성 페이지
	@GetMapping(value = "boardFreeWrite")
	public String boardFreeWrite() {
		System.out.println("LslController boardFreeWrite Start...");
		return "lsl/boardFreeWrite";
	}

	// 자유 게시판 글쓰기
	@PostMapping(value = "freeWrite")
	public String freeWrite(HttpServletRequest request, LslBoardComm lslBoardComm, @RequestParam("files") MultipartFile[] multipartFile, Model model) {
		System.out.println("LslController freeWrite Start...");
		
		
		try {
			Long user_no = (Long) request.getSession().getAttribute("user_no");
		

			String boardfilePath = request.getSession().getServletContext().getRealPath("/upload/boardFile/");

			
			
			lslBoardComm.setUser_no(user_no);

			ls.boardFreeWriteInsert(lslBoardComm, multipartFile, boardfilePath);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("LslController askWrite Exception ->" + e.getMessage());
		}
		

		return "redirect:/boardFree";

	}

	
	// 자유 게시판 글 삭제
	@RequestMapping("/deleteFreeBoard")
	public String deleteFreeBoard(HttpServletRequest request, LslBoardComm lslBoardComm) {
		System.out.println("LslController deleteBoard Start...");
		int cboard_no = Integer.parseInt(request.getParameter("cboard_no"));
		lslBoardComm.setCboard_no(cboard_no);
		int deleteFreeBoard = ls.deleteFreeBoard(lslBoardComm);

		System.out.println("LslController deleteBoard ->" + deleteFreeBoard);

		return "forward:boardFree";
	}

	
	/* 질문 게시판 */

	// 질문 게시판 리스트 및 페이징
	@RequestMapping(value = "boardAsk")
	public String boardAskList(HttpServletRequest request, Model model, LslBoardComm lslBoardComm) {
		System.out.println("LslController boardAskList Start...");
		int totalBoardAsk = ls.totalBoardAsk();
		System.out.println("LslController totalBoardAsk ->" + totalBoardAsk);
		
		// paging 작업
		BoardFreeAskPaging bapage = new BoardFreeAskPaging(totalBoardAsk, lslBoardComm.getCurrentPage());
		System.out.println("LslController boardAskList  bapage->" + bapage);
		lslBoardComm.setStart(bapage.getStart());
		lslBoardComm.setEnd(bapage.getEnd());
		System.out.println("LslController boardAskList  lslBoardComm->" + lslBoardComm);

		// 질문 게시판 리스트
		List<LslBoardComm> boardAskList = ls.boardAskList(lslBoardComm);
		System.out.println("LslController boardFreeList.size() ->" + boardAskList.size());

		model.addAttribute("totalBoardAsk", totalBoardAsk);
		model.addAttribute("boardAskList", boardAskList);
		model.addAttribute("bapage", bapage);

		return "lsl/boardAsk";
	}

	
	
	// 질문 게시판 검색
	@GetMapping(value = "boardAskSearch")
	public String boardAskSearch(LslBoardComm lslBoardComm, Model model, @RequestParam(name="type",  defaultValue = "all") String type, @RequestParam(name="keyword") String keyword) {
		System.out.println("LslController boardAskList Start...");
		
	
		System.out.println("Search Type : " + type);
		System.out.println("Search Keyword : " + keyword );
		
		
		lslBoardComm.setKeyword(keyword);
		lslBoardComm.setType(type);
	
		
		int totalBoardSearchAsk = ls.totalBoardSearchAsk(lslBoardComm, keyword, type);
		
		// paging 작업
		BoardFreeAskPaging bapage = new BoardFreeAskPaging(totalBoardSearchAsk, lslBoardComm.getCurrentPage());
		

		// 질문 게시판 리스트
		List<LslBoardComm> boardAskList = ls.boardAskSearch(lslBoardComm, keyword, type, bapage.getStart(), bapage.getEnd());
		System.out.println("LslController boardFreeList.size() ->" + boardAskList.size());

		model.addAttribute("totalBoardSearchAsk", totalBoardSearchAsk);
		model.addAttribute("boardAskList", boardAskList);
		model.addAttribute("bapage", bapage);
		model.addAttribute("keyword", keyword);
		model.addAttribute("type", type);

		return "lsl/boardAsk";
	}

	
	
	// 질문 게시판 상세내용 // 파일 불러오기 
	@GetMapping(value = "boardAskContents")
	public String boardAskContents(HttpServletRequest request, Model model, LslBoardComm lslBoardComm) {
		int cboard_no = Integer.parseInt(request.getParameter("cboard_no"));
		LslBoardComm boardAskContents = ls.boardAskContents(cboard_no);

		// 질문 게시판 조회수
		int boardAskViewCnt = ls.boardAskViewCnt(lslBoardComm);

		// 질문 게시판 댓글수
		int boardReplyCnt = ls.boardReplyCnt(cboard_no);
		
		// 파일 첨부 된 글 
		List<LslboardFile> boardAskFile = ls.boardAskFile(cboard_no);
		
		//프로필 
		model.addAttribute("boardAskFile",boardAskFile);
		model.addAttribute("boardReplyCnt", boardReplyCnt);
		model.addAttribute("boardAskViewCnt", boardAskViewCnt);
		model.addAttribute("boardAskContents", boardAskContents);
		return "lsl/boardAskContents";
	}
	
	
	// 질문 게시판 파일 다운로드 
	@GetMapping("boardAskFileDownload")
	public void boardAskFileDownload(@RequestParam("cboard_file_name") String fileName, 
	                                 @RequestParam("cboard_file_user_name") String userFileName, 
	                                 HttpServletRequest request, 
	                                 HttpServletResponse response) {
	    System.out.println("boardFileUpload cboard_no ->" + fileName);
	    System.out.println("boardFileUpload fileCount ->" + userFileName);
	    
	    
	    String boardfilePath = request.getSession().getServletContext().getRealPath("/upload/boardFile/") + fileName;
        File file = new File(boardfilePath);
        
	 
	        // 파일 경로 설정
	        
	        // 파일이 존재하는 경우에만 처리
	        if (file.exists()) {
	            // MIME 타입 설정
	            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
	            if (mimeType == null) {
	                mimeType = "application/octet-stream";
	            }
	            response.setContentType(mimeType);
	            
	            try {
	            // 파일 다운로드 설정
	            response.setContentType("application.octet-stream");
	            response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(userFileName, "UTF-8") + "\"");
	            response.setContentLength((int) file.length());
	            
	            // 파일 다운로드 실행
	            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
	            FileCopyUtils.copy(inputStream, response.getOutputStream());
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("LslController boardFileUpload Exception ->" + e.getMessage());
	    }
	        }
	}

	
	
	// 질문 게시판 글 작성 페이지
	@GetMapping(value = "boardAskWrite")
	public String boardAskWrite() {
		System.out.println("LslController boardAskWrite Start...");
		return "lsl/boardAskWrite";
	}

	
	
	// 질문 게시판 글쓰기 / 파일 업로드
	@PostMapping(value = "boardAskWrite")
	public String askWrite(HttpServletRequest request, LslBoardComm lslBoardComm,
			@RequestParam("files") MultipartFile[] multipartFile) {
		System.out.println("LslController askWrite Start...");

		try {
			Long user_no = (Long) request.getSession().getAttribute("user_no");

			String boardfilePath = request.getSession().getServletContext().getRealPath("/upload/boardFile/");

			lslBoardComm.setUser_no(user_no);

			ls.boardAskWriteInsert(lslBoardComm, multipartFile, boardfilePath);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("LslController askWrite Exception ->" + e.getMessage());
		}

		return "redirect:/boardAsk";

	}

	
	
	// 질문 게시판 글 삭제
	@RequestMapping("/deleteAskBoard")
	public String deleteAskBoard(HttpServletRequest request, LslBoardComm lslBoardComm) {
		System.out.println("LslController deleteAskBoard Start...");
		int cboard_no = Integer.parseInt(request.getParameter("cboard_no"));
		lslBoardComm.setCboard_no(cboard_no);
		int deleteAskBoard = ls.deleteAskBoard(lslBoardComm);

		System.out.println("LslController deleteAskBoard ->" + deleteAskBoard);

		return "forward:boardAsk";
	}

	

	
	/* 공통 사용 */

	// 게시판 글 수정 페이지 요청
		@GetMapping(value = "boardFreeModify")
		public String boardFreeAskModify(HttpServletRequest request, Model model, LslBoardComm lslBoardComm) {
			
			int cboard_no = Integer.parseInt(request.getParameter("cboard_no"));
			
			
			System.out.println("boardFreeAskModify cboard_no -> " +cboard_no );
			
			String boardType = request.getParameter("boardType");
			System.out.println("글게시판 수정 페이지 보드타입 :" + boardType);
			
			
			LslBoardComm boardModifyContents;
			
			List<LslboardFile> boardFiles;
		
			
			if (request.getParameter("boardType").equals("free")) {
				boardModifyContents = ls.boardFreeModify(cboard_no);
				boardFiles  = ls.boardFreeFile(cboard_no);
				
				System.out.println(boardModifyContents);
			} else {
				boardModifyContents = ls.boardAskModify(cboard_no);
				boardFiles = ls.boardAskFile(cboard_no);
			}
	
			model.addAttribute("boardFiles",boardFiles);
			model.addAttribute("boardType", boardType);
			model.addAttribute("boardModifyContents", boardModifyContents);
			return "lsl/boardFreeModify";
		}

	// 게시판 글 수정 처리
		@PostMapping("/boardFreeAskUpdate")
		public String boardFreeAskUpdate(@RequestParam("files") MultipartFile[] multipartFile, 
										@RequestParam("boardType") String boardType,
										HttpServletRequest request, 
										Model model, 
										LslBoardComm lslBoardComm, 
										LslboardFile lslboardFile,
										@Nullable @RequestParam("deleteFiles")String deleteFileFiles) {
		    // 게시글 번호, 유저 번호, 게시판 타입을 가져옴
		    int cboard_no = Integer.parseInt(request.getParameter("cboard_no"));
		    Long user_no = (Long) request.getSession().getAttribute("user_no");
		    //String boardType = request.getParameter("boardType");
		    System.out.println("boardType = "+boardType);
		    
		    // 서버에 저장된 파일 경로 설정
		    String boardfilePath = request.getSession().getServletContext().getRealPath("/upload/boardFile/");
		    // 유저 번호와 게시글 번호 설정
		    lslBoardComm.setUser_no(user_no);
		    lslboardFile.setCboard_no(cboard_no);

		    // 게시글 정보 업데이트
		    lslBoardComm.setCboard_no(cboard_no);
		    lslBoardComm.setBoardType(boardType);
		    
		    int  boardUpdate = ls.boardUpdate(lslBoardComm,multipartFile,boardfilePath,deleteFileFiles);
		    model.addAttribute("boardUpdate", boardUpdate);
		  
		    // 보드 타입에 따라 페이지 이동
		    if ("free".equals(boardType)) {
		        return "redirect:/boardFree";
		    } else {
		        return "redirect:/boardAsk";
		    }
		}
	 
		
		


}