package com.blackberry.s20240130103.ykm.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blackberry.s20240130103.ykm.model.YkmBoardComm;
import com.blackberry.s20240130103.ykm.model.YkmBoardCommFile;
import com.blackberry.s20240130103.ykm.model.YkmPaging;
import com.blackberry.s20240130103.ykm.service.YkmService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class YkmBoardController {

	private final YkmService ykmService;

	/* 스터디 게시판 */
	
	// 스터디 게시판 리스트 조회, 페이징 카운트, 검색
	@GetMapping(value = "boardStudy")
	public String boardStudy(YkmBoardComm ykmBoardComm, Model model) {
		System.out.println("YkmController boardStudy start ---*");
		// 페이징 처리를 위해 임시로 99값 넣어줌
		if (ykmBoardComm.getComm_mid2() == 0) ykmBoardComm.setComm_mid2(99);
		 
		// 전체 게시글 카운트
		int totalCount = ykmService.getTotalCount(ykmBoardComm); 
		
		// 페이징
		YkmPaging stuPage = new YkmPaging(totalCount, ykmBoardComm.getCurrentPage()); 
		ykmBoardComm.setStart(stuPage.getStart());   // 시작 페이지
		ykmBoardComm.setEnd(stuPage.getEnd()); 		 // 끝 페이지
		
		// 게시글 리스트 조회
		List<YkmBoardComm> getPostList = ykmService.getPostList(ykmBoardComm); 
		model.addAttribute("stuPage", stuPage); 						// 페이징
		model.addAttribute("getPostList", getPostList); 				// 게시글 리스트
		model.addAttribute("totalCount", totalCount); 					// 게시글 전체 카운트
		model.addAttribute("comm_mid2", ykmBoardComm.getComm_mid2()); 	// 게시판 분류
		model.addAttribute("type", ykmBoardComm.getType()); 			// 검색 타입
		model.addAttribute("keyword", ykmBoardComm.getKeyword());		// 검색 키워드
		
		return "ykm/boardStudy";
	}

	// 게시글 보여주기
	@GetMapping(value = "/post")
	public String getPost(HttpServletRequest request, Model model) {
		System.out.println("YkmController getPost start ---*");
		int cboard_no = Integer.parseInt(request.getParameter("cboard_no"));
		YkmBoardComm getPost = ykmService.getPost(cboard_no);
		
		// 조회수 카운트 메서드
		ykmService.increseViewCount(cboard_no); 
		
		// 댓글 개수 카운트 메서드
		int countComment = ykmService.countComment(cboard_no);  

		// 업로드한 파일 목록 리스트
		List<YkmBoardCommFile> getFileList = ykmService.getFileList(cboard_no); 
		for(YkmBoardCommFile file : getFileList) {
			System.out.println("test : " + file);
		}
		
		model.addAttribute("countComment",countComment); // 댓글 개수 카운트
		model.addAttribute("getPost", getPost); 		 // 게시글 
		model.addAttribute("getFileList", getFileList);  // 파일 목록
		System.out.println("YkmController getPost finish ---*");

		return "ykm/boardPost";
	}

	
	// 글 작성 시 게시판 분류
	@GetMapping(value = "/writeForm")
	public String boardWriteForm(YkmBoardComm ykmBoardComm, Model model) {
		System.out.println("YkmController boardWriteForm start ---*");
		
		// 게시글 분류
		model.addAttribute("comm_mid", ykmBoardComm.getComm_mid()); 
		model.addAttribute("comm_big", ykmBoardComm.getComm_big());
		System.out.println("YkmController boardWriteForm finish ---*");
		
		return "ykm/boardWriteForm";
	}
	
	// 글 작성
	@RequestMapping(value = "writePost")
	public String writePost(HttpServletRequest request, YkmBoardComm ykmBoardComm, 
							@Nullable @RequestParam("cboard_file_name") List<MultipartFile> fileList,RedirectAttributes redirect) {
		System.out.println("YkmController writePost start---*");
		// 세션에 저장된 로그인 한 유저 번호 
		Long user_no = (Long) request.getSession().getAttribute("user_no");
		ykmBoardComm.setUser_no(user_no);	
		
		// 파일 업로드
		String studyFilePath = request.getSession().getServletContext().getRealPath("/upload/studyBoardFile/");
		
		// 게시판 분류
		int comm_big = ykmBoardComm.getComm_big();
		int comm_mid = ykmBoardComm.getComm_mid();

		redirect.addAttribute("comm_mid", comm_mid);
		redirect.addAttribute("comm_mid", comm_big);
		
		// 글 작성 (글, 파일)
		int result = ykmService.writePost(ykmBoardComm, studyFilePath, fileList);
		
		if (comm_big == 200 && comm_mid == 10) {
			System.out.println("YkmController writePost result "+result);
			return "redirect:/boardContest";
			
		} else if (comm_big == 200 && comm_mid == 20) {
			return "redirect:/boardStudy";
		}
		return "ykm/boardWriteForm";
	}
	
	// 글 수정 폼
	@GetMapping(value = "/updateForm")
	public String UpdatePostForm(HttpServletRequest request, Model model) {
		System.out.println("updatePostForm updatePostForm start---*");
		
		// 게시글 번호
		int cboard_no = Integer.parseInt(request.getParameter("cboard_no"));
		
		// 게시글 조회
		YkmBoardComm getPost = ykmService.getPost(cboard_no);

		// 글 작성 시, 업로드 된 파일 리스트 조회
		List<YkmBoardCommFile> getFileList = ykmService.getFileList(cboard_no);
		
		model.addAttribute("getPost", getPost);
		model.addAttribute("getFileList", getFileList);
		model.addAttribute("comm_mid", getPost.getComm_mid()); // 게시판 분류
		
		// 로그인 유저
		Long currentUserNo = (Long) request.getSession().getAttribute("user_no");
		// 글 작성자
		Long postWriter = getPost.getUser_no();

		// 권한 체크 (글 작성자와 로그인 유저가 같을 경우엔 페이지 이용 가능, 아니라면 로그인 폼으로 이동)
		if (getPost != null && currentUserNo !=null && currentUserNo.equals(postWriter)) {
			return "ykm/boardUpdateForm";
		} else {
			return "lhs/loginForm";
		}
	}
	
	// 글 수정
	@RequestMapping(value = "updatePost")  
	public String updatePost(HttpServletRequest request, 
							Model model, 
							YkmBoardComm ykmBoardComm,
							@Nullable @RequestParam("cboard_file_name") List<MultipartFile> fileList,
							@Nullable @RequestParam("deleteFiles")String deleteFileFiles) {
		System.out.println("YkmController updatePost start---*");
		// 게시글 번호
		int cboard_no = Integer.parseInt(request.getParameter("cboard_no"));
		ykmBoardComm.setCboard_no(cboard_no);
		String studyFilePath = request.getSession().getServletContext().getRealPath("/upload/studyBoardFile/");
		// 글 수정 메서드
		int updatePost = ykmService.updatePost(ykmBoardComm,studyFilePath,fileList,deleteFileFiles);
		model.addAttribute("updatePost", updatePost);
		
		int comm_big = ykmBoardComm.getComm_big();
		int comm_mid =  ykmBoardComm.getComm_mid();
		
		if (comm_big == 200 && comm_mid == 10) {
			return "redirect:/boardContest";
		}

		return "redirect:/boardStudy";
		
	}
	
	// 글 삭제
	@RequestMapping(value="/deletePost")
	public String deletePost(HttpServletRequest request, Model model, YkmBoardComm ykmBoardComm) {
		System.out.println("YkmController deletePost cboard_no : " + request.getParameter("cboard_no"));
		// 게시글 번호
		int cboard_no = Integer.parseInt(request.getParameter("cboard_no"));
		ykmBoardComm.setCboard_no(cboard_no);
		
		// 글 삭제 (상태값 0 > 1로 변경, 관리자 페이지에서 최종 삭제)
		int deletePost = ykmService.deletePost(cboard_no);
		model.addAttribute("deletePost", deletePost);
	
		int comm_mid =  ykmBoardComm.getComm_mid();
		
		if (comm_mid == 10) {
			return "redirect:/boardContest";
		}
		
		return "redirect:boardStudy";
	}
	
	// 파일 다운로드
	@GetMapping(value="/fileDownload")
	public void getDownloadFile(@RequestParam("cboard_file_name") String cboard_file_name, 
								@RequestParam("cboard_file_user_name") String cboard_file_user_name,
								HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("YkmController getDownloadFile start---*");
		
		// 파일 DTO에 업로드 한 파일 이름과 저장할 파일 이름 세팅
		try {
			YkmBoardCommFile ykmBoardCommFile = new YkmBoardCommFile();
			ykmBoardCommFile.setCboard_file_name(cboard_file_name);
			ykmBoardCommFile.setCboard_file_user_name(cboard_file_user_name);
			System.out.println("YkmController getDownloadFile ykmBoardCommFile : "+ykmBoardCommFile);
		   
			// 파일이 저장될 경로 지정 
			String getFilePath = request.getSession().getServletContext().getRealPath("/upload/studyBoardFile/")+ykmBoardCommFile.getCboard_file_name();
			System.out.println("YkmController getDownloadFile getFilePath : "+getFilePath);
		   
			// 파일 객체 생성
			File file = new File(getFilePath);
		    if (file.exists()) {
		    	String mimeType = URLConnection.guessContentTypeFromName(file.getName());
		    	if (mimeType == null) {
		    	    mimeType = "application/octet-stream";
		    	}
		  
		    response.setContentType(mimeType);
	    	response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(ykmBoardCommFile.getCboard_file_user_name(), "UTF-8") + "\"");	
	    	response.setContentLength((int) file.length());
	
	    	InputStream is = new BufferedInputStream(new FileInputStream(file));
	    	FileCopyUtils.copy(is, response.getOutputStream());
		 } 
		    
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("YkmController getDownloadFile error+ " +e.getMessage());
		}
	}

	/* 공모전 게시판 */
	
	// 공모전 게시글 리스트 조회
	@GetMapping(value = "boardContest")
	public String boardContest(YkmBoardComm ykmBoardComm, Model model, HttpServletRequest request) {
		System.out.println("YkmController boardContest start ---*");

		// 게시판 분류 번호 설정
		ykmBoardComm.setComm_mid(10);
		
		// 공모전 전체 게시글 카운트
		int CnttotalCount = ykmService.getCntTotalCount(ykmBoardComm);
		
		// 페이징
		YkmPaging stuPage = new YkmPaging(CnttotalCount, ykmBoardComm.getCurrentPage());
		ykmBoardComm.setStart(stuPage.getStart());
		ykmBoardComm.setEnd(stuPage.getEnd());
	
		// 게시글 전체 리스트 조회
		List<YkmBoardComm> getCntPostList = ykmService.getCntPostList(ykmBoardComm);
		model.addAttribute("stuPage", stuPage); 					// 페이징
		model.addAttribute("getCntPostList", getCntPostList); 		// 게시글 리스트
		model.addAttribute("CnttotalCount", CnttotalCount);			// 전체 게시글 카운트
		model.addAttribute("type", ykmBoardComm.getType()); 		// 검색 타입
		model.addAttribute("keyword", ykmBoardComm.getKeyword()); 	// 검색 키워드
		model.addAttribute("comm_mid", ykmBoardComm.getComm_mid()); // 게시판 분류 번호
		
		return "ykm/boardContest";
	}

}