package com.blackberry.s20240130103.kph.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blackberry.s20240130103.kph.model.KphBoardProject;
import com.blackberry.s20240130103.kph.model.KphBoardProjectFile;
import com.blackberry.s20240130103.kph.model.KphBoardProjectReply;
import com.blackberry.s20240130103.kph.model.KphEval;
import com.blackberry.s20240130103.kph.model.KphProject;
import com.blackberry.s20240130103.kph.model.KphProjectTask;
import com.blackberry.s20240130103.kph.model.KphTask;
import com.blackberry.s20240130103.kph.model.KphUserProject;
import com.blackberry.s20240130103.kph.model.KphUsers;
import com.blackberry.s20240130103.kph.model.KphUserBoardProject;
import com.blackberry.s20240130103.kph.model.KphUserBoardProjectReply;
import com.blackberry.s20240130103.kph.service.KphPaging;
import com.blackberry.s20240130103.kph.service.KphProjectService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;

import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequiredArgsConstructor
public class KphProjectController {
	
	private final KphProjectService kphProjectService;
	
	/*워크스페이스 - 메인 */

	@GetMapping("mainLogic")
	public String mainLogic(HttpServletRequest request, Model model) {
		System.out.println("KphProjectController mainLogic start...");
		HttpSession session = request.getSession();
		if(session.getAttribute("user_no") == null || session.getAttribute("user_no") == "") {
			return "redirect:/";
		}
		Long user_no = (Long)session.getAttribute("user_no");
		
		Map<String, Object> mainLogic = kphProjectService.mainLogic(user_no);
		List<KphUsers> addressUserList = kphProjectService.addressUserList(user_no);
		
		model.addAttribute("projectList", mainLogic.get("projectList"));
		model.addAttribute("totalCompTaskCount", mainLogic.get("totalCompTaskCount"));
		model.addAttribute("totalUnCompTaskCount", mainLogic.get("totalUnCompTaskCount"));
		model.addAttribute("totalTaskCount", mainLogic.get("totalTaskCount"));
		model.addAttribute("addressUserList", addressUserList);
		return "main";
	}
	
	@GetMapping("projectAddForm")
	public String projectAddForm() {
		return "kph/projectAddForm";
	}
	
	@PostMapping("projectAdd")
	public String projectAdd(KphProject project, HttpServletRequest request) {
		System.out.println("KphProjectController projectAdd start...");
		HttpSession session = request.getSession();
		Long user_no = (Long)session.getAttribute("user_no");
		
		project.setUser_no(user_no);
				
		int result = kphProjectService.projectAdd(project);
		System.out.println("KphProjectController projectAdd result=>" + result);
		return "redirect:/main";
	}
	
	@PostMapping("evalForm")
	public String evalForm(HttpServletRequest request, Model model) {
		System.out.println("KphProjectController evalForm start...");
		
		HttpSession session = request.getSession();
		Long user_no = (Long)session.getAttribute("user_no");
		Long project_no = Long.parseLong(request.getParameter("project_no"));
		KphUserProject kphUserProject = new KphUserProject();
		kphUserProject.setProject_no(project_no);
		kphUserProject.setUser_no(user_no);
		
		// 본인 제외 나머지 프로젝트 참여 인원
		List<KphUsers> userList = kphProjectService.userListByProjectNoExceptOwn(kphUserProject);
		
		System.out.println("KphProjectController evalForm userList size=>" + userList.size());
		model.addAttribute("userList", userList);
		model.addAttribute("userListSize", userList.size());
		model.addAttribute("project_no", project_no);
		
		return "kph/evalForm";
	}
	
	@PostMapping("eval")
	public String eval(HttpServletRequest request) {
		System.out.println("KphProjectController eval start...");
		HttpSession session = request.getSession();
		Long user_no = (Long)session.getAttribute("user_no");
		Long project_no = Long.parseLong(request.getParameter("project_no"));
		
		int userListSize = Integer.parseInt(request.getParameter("userListSize"));
		for (int i=0; i<userListSize; i++) {
			Long puser_no = Long.parseLong(request.getParameter("user" + i));
			int eval_score = Integer.parseInt(request.getParameter("user" + i +"_score"));
			KphEval eval = new KphEval();
			eval.setUser_no(user_no);
			eval.setProject_no(project_no);
			eval.setPuser(puser_no);
			eval.setEval_score(eval_score);
			int result = kphProjectService.eval(eval);
			System.out.println("KphProjectController eval user" + i + " eval result =>" + result);
		}
		
		return "redirect:/main";
	}
	
	@PostMapping("projectAddressSearch")
	@ResponseBody
	public List<KphUsers> projectAddressSearch(@RequestBody KphUsers user, HttpServletRequest request) {
		
		Long user_no = (Long)request.getSession().getAttribute("user_no");
		user.setUser_no(user_no);
		System.out.println(user);
		
		List<KphUsers> userList = null;
		
		if (user.getUser_name() == "") {
			userList = kphProjectService.addressUserList(user_no);
			System.out.println(userList);
		} else {
			userList = kphProjectService.addressUserListByName(user);
			System.out.println(userList);
		}
		
		return userList;
	}
	
	@GetMapping("totalTaskList")
	public String totalTaskList(KphProjectTask kphProjectTask, HttpServletRequest request, Model model) {
		
		System.out.println("KphProjectController totalTaskList start...");
		
		Long user_no = (Long)request.getSession().getAttribute("user_no");
		kphProjectTask.setUser_no(user_no);
		
		int totaProjectlTaskCount = kphProjectService.totalProjectTaskCountByKeyword(kphProjectTask);
		System.out.println("KphProjectController totalTaskList totalTaskCount=>" + totaProjectlTaskCount);
		
		KphPaging kphPaging = new KphPaging(totaProjectlTaskCount, kphProjectTask.getCurrentPage());
		
		kphProjectTask.setStart(kphPaging.getStart());
		kphProjectTask.setEnd(kphPaging.getEnd());
		kphProjectTask.setUser_no(user_no);
		
		List<KphProjectTask> totalProjectTaskList = kphProjectService.totalProjectTaskList(kphProjectTask);
		System.out.println("KphProjectController totalTaskList totalProjectTaskList.size=>"+ totalProjectTaskList.size());
		
		model.addAttribute("totalProjectTaskList", totalProjectTaskList);
		model.addAttribute("kphPaging", kphPaging);
		model.addAttribute("keyword", kphProjectTask.getKeyword());
		model.addAttribute("searchFilter", kphProjectTask.getSearchFilter());
		model.addAttribute("sortFilter", kphProjectTask.getSortFilter());
		model.addAttribute("searchKeyword", kphProjectTask.getKeyword());
		model.addAttribute("clickedNav", kphProjectTask.getClickedNav());
		
		return "kph/totalTaskList";
	}
	
	@PostMapping("taskSearch")
	@ResponseBody
	public Map<String, Object> taskSearch(@RequestBody KphProjectTask kphProjectTask, HttpServletRequest request) {
		
		System.out.println("KphProjectController taskSearch start...");
		
		Long user_no = (Long)request.getSession().getAttribute("user_no");
		kphProjectTask.setUser_no(user_no);
		
		int totalTaskCount = kphProjectService.totalProjectTaskCountByKeyword(kphProjectTask);
		System.out.println("KphProjectController taskSearch totalTaskCount=>" + totalTaskCount);
		
		KphPaging kphPaging = new KphPaging(totalTaskCount, kphProjectTask.getCurrentPage());
		
		kphProjectTask.setStart(kphPaging.getStart());
		kphProjectTask.setEnd(kphPaging.getEnd());
		
		List<KphProjectTask> projectTaskList = kphProjectService.totalProjectTaskList(kphProjectTask);
		System.out.println("KphProjectController taskSearch projectTaskList.size=>"+ projectTaskList.size());
		
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("projectTaskList", projectTaskList);
		response.put("kphPaging", kphPaging);
				
		return response;
	}
	
	
	/*워크스페이스 - 프로젝트 홈 */
	
	private int isUserInProject(HttpServletRequest request) {
		
		Long user_no = (Long)request.getSession().getAttribute("user_no");
		Long project_no = Long.parseLong(request.getParameter("project_no"));
		
		KphUserProject kphUserProject = new KphUserProject();
		kphUserProject.setProject_no(project_no);
		kphUserProject.setUser_no(user_no);
		
		int isUserInProject = kphProjectService.isUserInProject(kphUserProject);
		
		return isUserInProject;
	}
	
	@GetMapping("detailProject")
	public String detailProject(HttpServletRequest request, Model model) {
		
		String resultPage = "redirect:/main";
		Long session_user_no = (Long)request.getSession().getAttribute("user_no");
		
		if (isUserInProject(request) > 0) {
			Long project_no = Long.parseLong(request.getParameter("project_no"));
			KphTask kphTask = new KphTask();
			kphTask.setProject_no(project_no);
			
			System.out.println("KphProjectController detailProject start...");
			
			Map<String, Object> detailProject = kphProjectService.detailProject(kphTask); 
			
			int unCompTaskListCount = ((List<KphTask>)detailProject.get("unCompTaskList")).size();
			int compTaskListCount = ((List<KphTask>)detailProject.get("compTaskList")).size();
			
			model.addAttribute("taskList", detailProject.get("taskList"));
			model.addAttribute("project_no", kphTask.getProject_no());
			model.addAttribute("projectMemberList", detailProject.get("projectMemberList"));
			model.addAttribute("unCompTaskListCount", unCompTaskListCount);
			model.addAttribute("compTaskListCount", compTaskListCount);
			model.addAttribute("projectLeader_no", detailProject.get("projectLeader_no"));
			model.addAttribute("isProjectCompleted", detailProject.get("isProjectCompleted"));
			model.addAttribute("session_user_no", session_user_no);
			
			resultPage = "kph/detailProject";
		}
		
		return resultPage;
	}
	
	@PostMapping("taskFilter")
	@ResponseBody
	public List<KphTask> taskFilter(@RequestBody Map<String, Object> requestMap) {
		
		System.out.println("KphProjectController taskFilter start...");
		
		String keyword = (String)requestMap.get("keyword");
		Long  project_no = Long.parseLong((String)requestMap.get("project_no"));
		
		System.out.println(keyword);
		System.out.println(project_no);
		
		List<KphTask> taskList = null;
		KphTask kphTask = new KphTask();
		kphTask.setProject_no(project_no);
		
		if(keyword.equals("미완료 과업")) {
			taskList = (List<KphTask>)kphProjectService.detailProject(kphTask).get("unCompTaskList");
		} else if(keyword.equals("완료 과업")) {
			taskList = (List<KphTask>)kphProjectService.detailProject(kphTask).get("compTaskList");
			System.out.println(taskList);
		} else {
			taskList = (List<KphTask>)kphProjectService.detailProject(kphTask).get("taskList");
		}
		
		return taskList;
	}
	
	@PostMapping("taskAddForm")
	public String taskAddForm(@RequestParam("project_no") Long project_no, HttpServletRequest request, Model model) {
		
		List<KphUsers> projectMemberList = kphProjectService.projectMemberList(project_no);
		
		model.addAttribute("project_no", project_no);
		model.addAttribute("projectMemberList", projectMemberList);
		return "kph/taskAddForm";
	}
	
	@PostMapping("taskAdd")
	public String taskAdd(@RequestParam("user_no") List<Long> userNoList, KphTask kphTask, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		System.out.println("KphProjectController taskAdd start...");
		
		String task_end = request.getParameter("task_end_day") + " " + request.getParameter("task_end_time");
		System.out.println(task_end);
		kphTask.setTask_end(task_end);
		
		int result = kphProjectService.taskAdd(userNoList, kphTask);
		System.out.println("KphProjectController taskAdd result=> " + result);
		
		redirectAttributes.addAttribute("project_no", kphTask.getProject_no());
		
		return "redirect:/detailProject";
	}
	
	@GetMapping("userAuthority")
	@ResponseBody
	public int userAuthority(@RequestParam("projectLeader_no") Long projectLeader_no, HttpServletRequest request) {
		int userAuthority = 0;
		Long user_no = (Long)request.getSession().getAttribute("user_no");
		System.out.println(user_no);
		System.out.println(projectLeader_no);
		
		if(user_no.equals(projectLeader_no)) {
			userAuthority = 1;
		}
		System.out.println(userAuthority);
		return userAuthority;
	}
	
	@GetMapping("projectMemberAddForm")
	public String projectMemberAddForm(HttpServletRequest request, Model model) {
		
		String resultPage = "redirect:/main";
						
		if (isUserInProject(request) > 0) {
			
			Long user_no = (Long)request.getSession().getAttribute("user_no");
			Long project_no = Long.parseLong(request.getParameter("project_no"));
			
			KphUserProject kphUserProject = new KphUserProject();
			kphUserProject.setProject_no(project_no);
			kphUserProject.setUser_no(user_no);
			
			List<KphUsers> addressUserList = kphProjectService.addressUserListExceptProjectMember(kphUserProject);
			
			model.addAttribute("addressUserList", addressUserList);
			model.addAttribute("project_no", project_no);
			
			resultPage = "kph/projectMemberAddForm";
		}
		
		return resultPage;
	}
	
	@PostMapping("projectMemberAdd")
	public String taskMemberAdd(@RequestParam("user_no") List<Long> userNoList, @RequestParam("project_no") Long project_no, HttpServletRequest request) {
		System.out.println("KphProjectController taskMemberAdd start...");
		
		if(userNoList != null) {
			KphUserProject kphUserProject = new KphUserProject();
			kphUserProject.setProject_no(project_no);
			int result = kphProjectService.projectMemberAdd(kphUserProject, userNoList);
			System.out.println(result);
		}
		
		return "redirect:/detailProject?project_no=" + String.valueOf(project_no);
	}
	
	@GetMapping("projectUpdateForm")
	public String projectUpdateForm(KphProject kphProject, Model model, HttpServletRequest request) {
		System.out.println("KphProjectController projectUpdateForm start...");
		
		String resultPage = "redirect:/main";
		
		if (isUserInProject(request) > 0) {
			KphProject project = kphProjectService.getProjectByProjectNo(kphProject);
			System.out.println("KphProjectController projectUpdateForm project=>" + project);
			model.addAttribute("project", project);
			resultPage = "kph/projectUpdateForm";
		}
		
		return resultPage;
	}
	
	@PostMapping("projectUpdate")
	public String projectUpdate(KphProject kphProject) {
		System.out.println("KphProjectController projectUpdate start...");
		int result = kphProjectService.projectUpdate(kphProject);
		System.out.println("KphProjectController projectUpdate result=> " + result);
		return "redirect:/detailProject?project_no=" + kphProject.getProject_no().toString();
	}
	
	@PostMapping("projectDelete")
	@ResponseBody
	public String projectDelete(KphProject kphProject) {
		System.out.println("KphProjectController projectDelete start...");
		System.out.println(kphProject);
		int result = kphProjectService.projectDelete(kphProject);
		return String.valueOf(result);
	}
	
	@PostMapping("projectEnd")
	@ResponseBody
	public String projectEnd(KphProject kphProject) {
		System.out.println("KphProjectController projectEnd start...");
		System.out.println(kphProject);
		int result = kphProjectService.projectEnd(kphProject);
		return String.valueOf(result);
	}
	
	@PostMapping("projectMemberDelete")
	@ResponseBody
	public String projectMemberDelete(KphUserProject kphUserProject) {
		System.out.println("KphProjectController projectMemberDelete start...");
		System.out.println(kphUserProject);
		int result = kphProjectService.projectMemberDelete(kphUserProject);
		return String.valueOf(result);
	}
	
	@GetMapping("taskUpdateForm")
	public String taskUpdateForm(KphTask kphTask, Model model, HttpServletRequest request) {
		System.out.println("KphProjectController taskUpdateForm start...");
		
		String resultPage = "redirect:/main";
		
		if (isUserInProject(request) > 0) {
			// 유저리스트는 해당 과업에 포함되어 있는지가 0과 1로 구분되어 있음
			KphTask task = kphProjectService.taskIncludingProjectMember(kphTask);
			System.out.println(task);
			model.addAttribute("task", task);
			model.addAttribute("projectMemberList", task.getUsers());
			resultPage = "kph/taskUpdateForm";
		}
		
		return resultPage;
	}
	
	@PostMapping("taskUpdate")
	public String taskUpdate(@RequestParam("user_no") List<Long> userNoList, KphTask kphTask, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		System.out.println("KphProjectController taskUpdate start...");
		System.out.println(kphTask);
		String task_end = request.getParameter("task_end_day") + " " + request.getParameter("task_end_time");
		kphTask.setTask_end(task_end);
		
		int result = kphProjectService.taskUpdate(userNoList, kphTask);
		System.out.println("KphProjectController taskUpdate result=> " + result);
		
		redirectAttributes.addAttribute("project_no", kphTask.getProject_no());
		
		return "redirect:/detailProject";
	}
	
	@PostMapping("taskDelete")
	@ResponseBody
	public String taskDelete(KphTask kphTask) {
		System.out.println("KphProjectController taskDelete start...");
		System.out.println(kphTask);
		int result = kphProjectService.taskDelete(kphTask);
		return String.valueOf(result);
	}
	
	@PostMapping("taskCompUpdate")
	@ResponseBody
	public String taskCompUpdate(KphTask kphTask) {
		System.out.println("KphProjectController taskCompUpdate start...");
		System.out.println(kphTask);
		int result = kphProjectService.taskCompUpdate(kphTask);
		return String.valueOf(result);
	}
	
	
	/*워크스페이스 - 공유 게시판 - 게시물 상세 */
	@GetMapping("detailBoardProject")
	public String detailBoardProject(KphBoardProject kphBoardProject, HttpServletRequest request, Model model) {
		System.out.println("KphProjectController detailBoardProject start...");
		String resultPage = "redirect:/main";
		
		if (isUserInProject(request) > 0) {
			KphUserBoardProject board = kphProjectService.getBoardProject(kphBoardProject);
			model.addAttribute("board", board);
			model.addAttribute("fileList", board.getFileList());
			model.addAttribute("replyListGroups", board.getReplyMapByGroup());
			model.addAttribute("replyListSize", board.getReplyMapByGroup().size());
			model.addAttribute("project_no", kphBoardProject.getProject_no());
			Long user_no = (Long)request.getSession().getAttribute("user_no");
			model.addAttribute("session_user_no", user_no);
			resultPage = "kph/detailBoardProject";
		}
		
		return resultPage;
	}
	
	@PostMapping("boardProjectReplyAdd")
	@ResponseBody
	public KphUserBoardProjectReply boardProjectReplyAdd(KphBoardProjectReply reply, HttpServletRequest request) {
		System.out.println("KphProjectController boardProjectReplyAdd start...");
		System.out.println("KphProjectController boardProjectReplyWrite reply=>" + reply);
		Long user_no = (Long)request.getSession().getAttribute("user_no");
		reply.setUser_no(user_no);
		
		KphUserBoardProjectReply resultReply = kphProjectService.boardProjectReplyAdd(reply);
		return resultReply;
	}
	
	@PostMapping("boardProjectReplyReplyAdd")
	@ResponseBody
	public KphUserBoardProjectReply boardProjectReplyReplyAdd(KphBoardProjectReply reply, HttpServletRequest request) {
		System.out.println("KphProjectController boardProjectReplyReplyAdd start...");
		Long user_no = (Long)request.getSession().getAttribute("user_no");
		
		KphUserBoardProjectReply resultReply = null;
		String content = reply.getPreply_content();
		
		String pattern = "^@\\S+\\s+\\S[\\s\\S]";
		Pattern regex = Pattern.compile(pattern);
		Matcher matcher = regex.matcher(content);
		
		if (matcher.find()) {
			
			String tag = content;
			if(content.indexOf(" ") != -1) {
				tag = content.substring(0, content.indexOf(" "));
			} 
			
			reply.setTagName(tag.substring(1));
			boolean isUserInIndentZero = kphProjectService.isUserInIndentZero(reply);
			
			if(isUserInIndentZero) {
				reply.setPreply_indent(1);
				reply.setUser_no(user_no);
			} else {
				reply.setPreply_indent(0);
				reply.setUser_no(user_no);
			}
			
			resultReply = kphProjectService.boardProjectReplyReplyAdd(reply);
			
		} else {
			reply.setPreply_indent(0);
			reply.setUser_no(user_no);
			resultReply = kphProjectService.boardProjectReplyReplyAdd(reply);
		}
		
		return resultReply;
	}
	
	@PostMapping("boardProjectReplyDelete")
	@ResponseBody
	public String boardProjectReplyDelete(KphBoardProjectReply reply) {
		System.out.println("KphProjectController boardProjectReplyDelete start...");
		System.out.println(reply);
		String result= null;
		
		int deleteResult = kphProjectService.boardProjectReplyDelete(reply);
		
		if(deleteResult > 0) {
			result = "성공";
		}
		
		return result;
	}
	
	@PostMapping("boardProjectDelete")
	public String boardProjectDelete(KphBoardProject board ,HttpServletRequest request) {
		System.out.println("KphProjectController boardProjectDelete start...");
		kphProjectService.boardProjectDelete(board);
		return "redirect:/boardProject?project_no=" + board.getProject_no();
	}
	
	@GetMapping("boardProjectFileDownload")
	public void boardProjectFileDownload(KphBoardProjectFile file, HttpServletRequest request, HttpServletResponse response) {
		KphBoardProjectFile fileInformation = kphProjectService.getBoardProjectFile(file);
		try {
			if(fileInformation != null) {
				String filePath = request.getSession().getServletContext().getRealPath("/upload/boardProjectFile/") + fileInformation.getPboard_file_name();
				File boardProjectFile = new File(filePath);
				
				if(boardProjectFile.exists()) {
					 
  	                 // MIME 타입을 결정하지 못할 경우 "application/octet-stream"
					 String mimeType = URLConnection.guessContentTypeFromName(boardProjectFile.getName());
					 if(mimeType == null) {
						 mimeType = "application/octet-stream";
					 }
					 
					 response.setContentType(mimeType);
					 // 다운로드 시 파일 이름을 설정
		             // Content-Disposition은 HTTP 헤더의 하나로 주로 웹 서버가 클라이언트(브라우저 등)에게 응답의 본문 처리 방식 정보를 제공
		             response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileInformation.getPboard_file_user_name(), "UTF-8") + "\"");
 		             // HTTP 응답의 Content-Length 헤더를 파일 크기로 설정. 이는 다운로드 진행 상황 표시에 사용.
		             response.setContentLength((int)boardProjectFile.length());
		             // 파일을 읽기 위한 입력 스트림을 생성.
		             InputStream inputStream = new BufferedInputStream(new FileInputStream(boardProjectFile));
		             // 응답의 출력 스트림으로 파일 내용을 복사. 이 과정에서 사용자가 파일을 다운로드
		             FileCopyUtils.copy(inputStream, response.getOutputStream());
				}
			}
			
		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
	}
	
	@PostMapping("boardProjectReplyEdit")
	@ResponseBody
	public KphUserBoardProjectReply boardProjectReplyEdit(KphBoardProjectReply reply) {
		System.out.println("KphProjectController boardProjectReplyEdit start...");
		KphUserBoardProjectReply resultReply = kphProjectService.updateBoardProjectReply(reply);
		return resultReply;
	}
	
	@PostMapping("boardProjectReplyReplyEdit")
	@ResponseBody
	public KphUserBoardProjectReply boardProjectReplyReplyEdit(KphBoardProjectReply reply, HttpServletRequest request) {
		System.out.println("KphProjectController boardProjectReplyReplyEdit start...");
		Long user_no = (Long)request.getSession().getAttribute("user_no");
		
		KphUserBoardProjectReply resultReply = null;
		String content = reply.getPreply_content();
		
		String pattern = "^@\\S+\\s+\\S[\\s\\S]";
		Pattern regex = Pattern.compile(pattern);
		Matcher matcher = regex.matcher(content);
		
		if (matcher.find()) {
			
			String tag = content;
			if(content.indexOf(" ") != -1) {
				tag = content.substring(0, content.indexOf(" "));
			} 
			
			reply.setTagName(tag.substring(1));
			boolean isUserInIndentZero = kphProjectService.isUserInIndentZero(reply);
			
			if(isUserInIndentZero) {
				reply.setPreply_indent(1);
				reply.setUser_no(user_no);
			} else {
				reply.setPreply_indent(0);
				reply.setUser_no(user_no);
			}
			
			resultReply = kphProjectService.boardProjectReplyReplyUpdate(reply);
			
		} else {
			reply.setPreply_indent(0);
			reply.setUser_no(user_no);
			resultReply = kphProjectService.boardProjectReplyReplyUpdate(reply);
		}
		
		return resultReply;
	}
	
	
}
