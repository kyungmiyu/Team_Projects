package com.blackberry.s20240130103.lhs.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blackberry.s20240130103.lhs.model.BoardAdmin;
import com.blackberry.s20240130103.lhs.model.BoardComm;
import com.blackberry.s20240130103.lhs.model.Reply;
import com.blackberry.s20240130103.lhs.model.User;
import com.blackberry.s20240130103.lhs.service.AdminService;
import com.blackberry.s20240130103.lhs.service.LhsPaging;
import com.blackberry.s20240130103.lhs.service.UserService;
import com.blackberry.s20240130103.lsl.model.LslboardFile;
import com.blackberry.s20240130103.yhs.model.Ask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;

@Controller
@RequiredArgsConstructor
public class LhsAdminController {
	
	private final AdminService adminService;
	private final UserService userService;
	
	@GetMapping("/adminMain")
	public String adminMain(Model model) {
		Map<String, Long> tableCntMap = adminService.selectTablesCnt();
		List<User> deleteRequestUserList = adminService.selectUsersDeleteRequest();
		List<BoardAdmin> boardAdminList = adminService.selectBoardAdminList();
		model.addAttribute("cntMap",tableCntMap);
		model.addAttribute("deleteUserList", deleteRequestUserList);
		model.addAttribute("boardAdminList", boardAdminList);
		return "admin/admin_main";
	}
	
	@GetMapping("userCntListAjax")
	@ResponseBody
	public String listMapUserCnt(){
		List<Map<String, Long>> userJoinCntList = adminService.selectUserJoinCnt();
		Gson gson = new GsonBuilder().setDateFormat("MM-dd").create();
		String jsonstr = gson.toJson(userJoinCntList);
		return jsonstr;
	}
	
	@GetMapping("admin_boardList")
	public String adminBoardList(BoardComm board,Model model) {
		int cboardCnt = adminService.selectBoardCommCnt(board);
		LhsPaging paging = new LhsPaging(cboardCnt, board.getCurrentPage());
		board.setStart(paging.getStart());
		board.setEnd(paging.getEnd());
		List<BoardComm> cboardList = adminService.selectBoardCommList(board);
		model.addAttribute("paging", paging);
		model.addAttribute("boardList", cboardList);
		model.addAttribute("searchkind", board.getSearchkind());
		model.addAttribute("searchValue", board.getSearchValue());
		return "admin/admin_boardList";
	}
	
	@GetMapping("admin_cboard_detail")
	public String adminBoardDetail(BoardComm board,Model model) {
		BoardComm detailBoard = adminService.selectBoard(board);
		List<LslboardFile> boardFileList = adminService.selectBoardFileList(board.getCboard_no());
		if(!boardFileList.isEmpty()) {
			model.addAttribute("fileList", boardFileList);
		}
		model.addAttribute("board", detailBoard);
		return "admin/admin_boardDetail";
	}
	
	@GetMapping("admin_boardDelete")
	public String adminBoardDelete(BoardComm board) {
		int result = adminService.deleteBoard(board);
		return "redirect:/admin_boardList";
	}
	
	@GetMapping("admin_users")
	public String adminUserList(User user,Model model) {
		int userCnt = adminService.selectUsersCnt(user);
		LhsPaging paging = new LhsPaging(userCnt, user.getCurrentPage());
		user.setStart(paging.getStart());
		user.setEnd(paging.getEnd());
		List<User> userList = adminService.selectUsersList(user);
		model.addAttribute("paging", paging);
		model.addAttribute("userList", userList);
		model.addAttribute("searchkind", user.getSearchkind());
		model.addAttribute("searchValue", user.getSearchValue());
		return "admin/admin_userList";
	}
	
	@GetMapping("admin_user_detail")
	public String adminUserDetail(User user,Model model) {
		User userdetail = adminService.selectUserDetail(user);
		model.addAttribute("user", userdetail);
		return "admin/admin_userDetail";
	}
	
	@GetMapping("admin_userDelete")
	public String adminUserDelete(User user,Model model) {
		int result = adminService.deleteUser(user);
		return "redirect:/admin_users";
	}
	
	@GetMapping("admin_reply")
	public String adminReplyList(Reply reply,Model model) {
		int replyCnt = adminService.selectReplyCnt(reply);
		LhsPaging paging = new LhsPaging(replyCnt, reply.getCurrentPage());
		reply.setStart(paging.getStart());
		reply.setEnd(paging.getEnd());
		List<Reply> replyList = adminService.selectReplyList(reply);
		model.addAttribute("currentPage", reply.getCurrentPage());
		model.addAttribute("replyList", replyList);
		model.addAttribute("paging", paging);
		model.addAttribute("searchkind", reply.getSearchkind());
		model.addAttribute("searchValue", reply.getSearchValue());
		return "admin/admin_replyList";
	}
	
	@GetMapping("admin_replyDelte")
	public String adminReplyDelete(Reply reply,RedirectAttributes redirect) {
		int result = adminService.deleteReply(reply);
		redirect.addAttribute("currentPage", reply.getCurrentPage());
		return "redirect:/admin_reply";
	}
	
	@GetMapping("adminRegisterForm")
	public String adminRegisterForm() {
		return "admin/admin_AddForm";
	}
	
	@PostMapping("adminRegister")
	public String adminRegister(com.blackberry.s20240130103.lhs.domain.User user) {
		userService.joinUser(user);
		return "redirect:/adminMain";
	}
	
	@GetMapping("admin_ask")
	public String adminAskList(Ask ask,Model model) {
		int askCnt = adminService.selectAskCnt(ask);
		LhsPaging paging = new LhsPaging(askCnt, ask.getCurrentPage());
		ask.setStart(paging.getStart());
		ask.setEnd(paging.getEnd());
		List<Ask> askList = adminService.selectAskList(ask);
		model.addAttribute("askList", askList);
		model.addAttribute("paging", paging);
		model.addAttribute("searchkind", ask.getSearch());
		model.addAttribute("searchValue", ask.getKeyword());
		return "admin/admin_askList";
	}
	
	@GetMapping("admin_ask_detail")
	public String adminAskDetail(Ask ask,Model model) {
		Ask askDetail = adminService.selectAskDetail(ask);
		model.addAttribute("ask", askDetail);
		return "admin/admin_askDetail";
	}
	
	@GetMapping("admin_ask_responseForm")
	public String adminAskResponseForm(Ask ask,Model model) {
		Ask askDetail = adminService.selectAskDetail(ask);
		model.addAttribute("ask", askDetail);
		return "admin/admin_askResponse";
	}
	
	@PostMapping("admin_ask_responseWrite")
	public String adminAskResponseWrite(Ask ask,HttpServletRequest request) {
		ask.setUser_no((Long)request.getSession().getAttribute("user_no"));
		int result = adminService.insertAskResponse(ask);
		return "redirect:/admin_ask";
	}
	
	@GetMapping("adminFileDownload")
	public void downloadFile(@RequestParam("cboard_file_name")String filename,
							@RequestParam("cboard_file_user_name")String userFileName,
							HttpServletRequest request,
							HttpServletResponse response) {
		String filePath = request.getSession().getServletContext().getRealPath("/upload/boardFile/") + filename;
		File file = new File(filePath);
		if(file.exists()) {
			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
			
			response.setContentType(mimeType);
            try {
				response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(userFileName, "UTF-8") + "\"");
				response.setContentLength((int) file.length());
	            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
	            FileCopyUtils.copy(inputStream, response.getOutputStream());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
