package com.blackberry.s20240130103.kph.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import com.blackberry.s20240130103.kph.dao.KphProjectDao;
import com.blackberry.s20240130103.kph.model.KphBoardProject;
import com.blackberry.s20240130103.kph.model.KphBoardProjectFile;
import com.blackberry.s20240130103.kph.model.KphBoardProjectReply;
import com.blackberry.s20240130103.kph.model.KphEval;
import com.blackberry.s20240130103.kph.model.KphProject;
import com.blackberry.s20240130103.kph.model.KphProjectTask;
import com.blackberry.s20240130103.kph.model.KphTask;
import com.blackberry.s20240130103.kph.model.KphUserBoardProjectReply;
import com.blackberry.s20240130103.kph.model.KphUserProject;
import com.blackberry.s20240130103.kph.model.KphUsers;
import com.blackberry.s20240130103.kph.model.KphUserBoardProject;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KphProjectServiceImp implements KphProjectService {

	private final KphProjectDao kphProjectDao;
	
	@Override
	public Map<String, Object> mainLogic(Long user_no) {
		System.out.println("KphProjectServiceImp mainLogic start...");
		Map<String, Object> mainLogic = new HashMap<String, Object>();
		
		// 프로젝트 리스트 + 과업 설정
		List<KphProject> projectList = kphProjectDao.projectList(user_no);
		Iterator<KphProject> projectIter = projectList.iterator();
		
		int totalCompTaskCount = 0;
		int totalUnCompTaskCount = 0;
		
		while(projectIter.hasNext()) {
			KphProject kphProject = projectIter.next();
			kphProject.setUser_no(user_no);
			int isEvalByUser = kphProjectDao.isEvalByUser(kphProject);
			List<KphTask> unComptaskList = kphProjectDao.unCompTaskListByProjectNo(kphProject.getProject_no());
			List<KphTask> compTaskList = kphProjectDao.compTaskListByProjectNo(kphProject.getProject_no());
			kphProject.setUncomp_task_count(unComptaskList.size());
			kphProject.setComp_task_count(compTaskList.size());
			kphProject.setIsEvalByUser(isEvalByUser);
			totalCompTaskCount += compTaskList.size();
			totalUnCompTaskCount += unComptaskList.size();
		}
		
		int totalTaskCount = totalCompTaskCount+totalUnCompTaskCount;
		
		mainLogic.put("projectList", projectList);
		mainLogic.put("totalCompTaskCount", totalCompTaskCount);
		mainLogic.put("totalUnCompTaskCount", totalUnCompTaskCount);
		mainLogic.put("totalTaskCount", totalTaskCount);
		
		return mainLogic;
	}

	@Override
	public int projectAdd(KphProject project) {
		System.out.println("KphProjectServiceImp projectAdd start...");
		int result = kphProjectDao.projectAdd(project);
		System.out.println("KphProjectServiceImp projectAdd result => " + result);
		return result;
	}

	@Override
	public List<KphUsers> userListByProjectNoExceptOwn(KphUserProject kphUserProject) {
		System.out.println("KphProjectServiceImp userListByProjectNo start...");
		List<KphUsers> userList = kphProjectDao.userListByProjectNoExceptOwn(kphUserProject);
		return userList;
	}


	@Override
	public int eval(KphEval eval) {
		System.out.println("KphProjectServiceImp eval start...");
		int result = kphProjectDao.eval(eval);
		return result;
	}


	@Override
	public List<KphUsers> addressUserList(Long user_no) {
		System.out.println("KphProjectServiceImp addressUserList start...");
		List<KphUsers> addressUserList = kphProjectDao.addressUserList(user_no);
		return addressUserList;
	}


	@Override
	public List<KphUsers> addressUserListByName(KphUsers user) {
		System.out.println("KphProjectServiceImp addressUserListByName start...");
		List<KphUsers> addressUserList = kphProjectDao.addressUserListByName(user);
		return addressUserList;
	}


	@Override
	public List<KphProjectTask> totalProjectTaskList(KphProjectTask kphProjectTask) {
		System.out.println("KphProjectServiceImp totalTaskList start...");
		List<KphProjectTask> totalProjectTaskList = kphProjectDao.totalProjectTaskList(kphProjectTask);
		return totalProjectTaskList;
	}


	@Override
	public int totalProjectTaskCountByKeyword(KphProjectTask kphProjectTask) {
		System.out.println("KphProjectServiceImp totalTaskCountByKeyword start...");
		int totalTaskCount = kphProjectDao.totalProjectTaskCountByKeyword(kphProjectTask);
		return totalTaskCount;
	}

	@Override
	public int isUserInProject(KphUserProject kphUserProject) {
		int result = kphProjectDao.isUserInProject(kphUserProject);
		return result;
	}

	@Override
	@Transactional
	public Map<String, Object> detailProject(KphTask kphTask) {
		System.out.println("KphProjectServiceImp detailProject start...");
		Map<String, Object> detailProject = new HashMap<String, Object>();
		
		// 과업 리스트 세팅
		List<KphTask> taskList = kphProjectDao.taskListByProjectNo(kphTask);
		Iterator<KphTask> taskIt = taskList.iterator();
		
		while (taskIt.hasNext()) {
			KphTask task = taskIt.next();
			task.setUsers(kphProjectDao.UserListInTask(task));
		}
		
		detailProject.put("taskList", taskList);
		
		// 완료/미완료 과업 세팅
		List<KphTask> unCompTaskList = new ArrayList<KphTask>();
		List<KphTask> compTaskList = new ArrayList<KphTask>();
		Iterator<KphTask> taskListIt = taskList.iterator();
		
		while (taskListIt.hasNext()) {
			KphTask task = taskListIt.next();
			if(task.getTask_comp_chk() == 0) {
				unCompTaskList.add(task);
			} else {
				compTaskList.add(task);
			}
		}
		
		detailProject.put("unCompTaskList", unCompTaskList);
		detailProject.put("compTaskList", compTaskList);
		
		// 프로젝트 멤버 세팅
		List<KphUsers> projectMemberList = kphProjectDao.projectMemberList(kphTask.getProject_no());
		detailProject.put("projectMemberList", projectMemberList);
		
		// 프로젝트 팀장 세팅
		Long projectLeader_no = kphProjectDao.projectLeaderNo(kphTask.getProject_no());
		detailProject.put("projectLeader_no", projectLeader_no);
		
		// 프로젝트 완료 여부 세팅
		int isProjectCompleted = kphProjectDao.isProjectCompleted(kphTask.getProject_no());
		detailProject.put("isProjectCompleted", isProjectCompleted);
		
		return detailProject;
	}

	@Override
	public List<KphUsers> projectMemberList(Long project_no) {
		System.out.println("KphProjectServiceImp detailProject start...");
		List<KphUsers> projectMemberList = kphProjectDao.projectMemberList(project_no);
		return projectMemberList;
	}

	@Override
	public int taskAdd(List<Long> userNoList, KphTask kphTask) {
		System.out.println("KphProjectServiceImp taskAdd start...");
		int result = kphProjectDao.taskAdd(userNoList, kphTask);
		return result;
	}

	@Override
	public List<KphUsers> addressUserListExceptProjectMember(KphUserProject kphUserProject) {
		System.out.println("KphProjectServiceImp addressUserListExceptProjectMember start...");
		List<KphUsers> addressUserList = kphProjectDao.addressUserListExceptProjectMember(kphUserProject);
		return addressUserList;
	}

	@Override
	@Transactional
	public int projectMemberAdd(KphUserProject kphUserProject, List<Long> userNoList) {
		System.out.println("KphProjectServiceImp projectMemberAdd start...");
		
		int result = 0;
		
		Iterator<Long> userNoListIt = userNoList.iterator();
		while (userNoListIt.hasNext()) {
			Long user_no = userNoListIt.next();
			kphUserProject.setUser_no(user_no);
			result = kphProjectDao.projectMemberAdd(kphUserProject);
			System.out.println("projectMemberInsert result=> " + result);
		}
		
		return result;
	}

	@Override
	public KphProject getProjectByProjectNo(KphProject kphProject) {
		System.out.println("KphProjectServiceImp getProjectByProjectNo start...");
		KphProject project = kphProjectDao.getProjectByProjectNo(kphProject);
		project.setProject_start(project.getProject_start().substring(0, project.getProject_start().indexOf(" ")));
		project.setProject_end(project.getProject_end().substring(0, project.getProject_end().indexOf(" ")));
		return project;
	}

	@Override
	public int projectUpdate(KphProject kphProject) {
		System.out.println("KphProjectServiceImp projectUpdate start...");
		int result = kphProjectDao.projectUpdate(kphProject);
		return result;
	}

	@Override
	public int projectDelete(KphProject kphProject) {
		System.out.println("KphProjectServiceImp projectDelete start...");
		int result = kphProjectDao.projectDelete(kphProject);
		return result;
	}
	
	@Override
	public int projectEnd(KphProject kphProject) {
		System.out.println("KphProjectServiceImp projectEnd start...");
		int result = kphProjectDao.projectEnd(kphProject);
		return result;
	}

	@Override
	public int projectMemberDelete(KphUserProject kphUserProject) {
		System.out.println("KphProjectServiceImp projectMemberDelete start...");
		int result = kphProjectDao.projectMemberDelete(kphUserProject);
		return result;
	}

	@Override
	public KphTask taskIncludingProjectMember(KphTask kphTask) {
		System.out.println("KphProjectServiceImp taskIncludingProjectMember start...");
		KphTask task = kphProjectDao.getTask(kphTask);
		task.setUsers(kphProjectDao.projectMemberListIncludingIsInTask(task));
		return task;
	}

	@Override
	public int taskUpdate(List<Long> userNoList, KphTask kphTask) {
		System.out.println("KphProjectServiceImp taskUpdate start...");
		int result = kphProjectDao.taskUpdate(userNoList, kphTask);
		return result;
	}

	@Override
	public int taskDelete(KphTask kphTask) {
		System.out.println("KphProjectServiceImp taskDelete start...");
		int result = kphProjectDao.taskDelete(kphTask);
		return result;
	}

	@Override
	public int taskCompUpdate(KphTask kphTask) {
		System.out.println("KphProjectServiceImp taskCompUpdate start...");
		int result = kphProjectDao.taskCompUpdate(kphTask);
		return result;
	}

	@Override
	@Transactional
	public KphUserBoardProject getBoardProject(KphBoardProject kphBoardProject) {
		System.out.println("KphProjectServiceImp getBoardProject start...");
		
		KphUserBoardProject boardProject = new KphUserBoardProject();
		
		kphProjectDao.IncreaseBoardProjectCnt(kphBoardProject);
		boardProject = kphProjectDao.getBoardProjectByPboardNo(kphBoardProject);
		boardProject.setPboard_content(boardProject.getPboard_content().replace("\n","<br>"));
		boardProject.setFileList(kphProjectDao.boardProjectFileList(boardProject));
		
		List<Long> replyGroupNoList = kphProjectDao.replyGroupListByPboardNo(boardProject.getPboard_no());
		List<KphUserBoardProjectReply> replyList = kphProjectDao.boardProjectReplyList(boardProject);
		Map<Long, List<KphUserBoardProjectReply>> replyMapByGroup = new LinkedHashMap<>();
		
		Iterator<Long> replyGroupNoListIt = replyGroupNoList.iterator();
		while (replyGroupNoListIt.hasNext()) {
			Long replyGroupNo = replyGroupNoListIt.next();
			List<KphUserBoardProjectReply> madeReplyList = new ArrayList<>();
			replyMapByGroup.put(replyGroupNo, madeReplyList);
		}
		
		Iterator<KphUserBoardProjectReply> replyListIt = replyList.iterator();
		while (replyListIt.hasNext()) {
			KphUserBoardProjectReply reply = replyListIt.next();
			reply.setPreply_content(reply.getPreply_content().replace("\n","<br>"));
			replyMapByGroup.get(reply.getPreply_group()).add(reply);
		}
		
		boardProject.setReplyMapByGroup(replyMapByGroup);
		boardProject.setReplyCnt(kphProjectDao.boardProjectReplyCnt(kphBoardProject.getPboard_no()));
		
		return boardProject;
	}
	
	@Override
	public KphUserBoardProjectReply boardProjectReplyAdd(KphBoardProjectReply reply) {
		System.out.println("KphProjectServiceImp boardProjectReplyAdd start...");
		KphUserBoardProjectReply resultReply = kphProjectDao.boardProjectReplyAdd(reply);
		resultReply.setPreply_content(reply.getPreply_content().replace("\n","<br>"));
		return resultReply;
	}
	
	@Override
	public boolean isUserInIndentZero(KphBoardProjectReply reply) {
		System.out.println("KphProjectServiceImp isUserInIndentZero start...");
		boolean result = false;
		int isUserInIndentTwo = kphProjectDao.isUserInIndentZero(reply);
		
		if (isUserInIndentTwo > 0) {
			result = true;
		}
		
		return result;
	}
	
	@Override
	public KphUserBoardProjectReply boardProjectReplyReplyAdd(KphBoardProjectReply reply) {
		System.out.println("KphProjectServiceImp boardProjectReplyReplyAdd start...");
		KphUserBoardProjectReply resultReply = kphProjectDao.boardProjectReplyReplyAdd(reply);
		resultReply.setPreply_content(reply.getPreply_content().replace("\n","<br>"));
		return resultReply;
	}
	
	@Override
	public int boardProjectReplyDelete(KphBoardProjectReply reply) {
		System.out.println("KphProjectServiceImp boardProjectReplyDelete start...");
		int result = kphProjectDao.boardProjectReplyDelete(reply.getPreply_no());
		return result;
	}
	
	@Override
	public void boardProjectDelete(KphBoardProject board) {
		System.out.println("KphProjectServiceImp boardProjectReplyDelete start...");
		kphProjectDao.boardProjectDelete(board);
	}
	
	@Override
	public KphBoardProjectFile getBoardProjectFile(KphBoardProjectFile file) {
		System.out.println("KphProjectServiceImp getBoardProjectFile start...");
		KphBoardProjectFile fileInformation = kphProjectDao.getBoardProjectFile(file);
		return fileInformation;
	}
	
	@Override
	public KphUserBoardProjectReply updateBoardProjectReply(KphBoardProjectReply reply) {
		System.out.println("KphProjectServiceImp updateBoardProjectReply start...");
		KphUserBoardProjectReply resultReply = kphProjectDao.updateBoardProjectReply(reply);
		resultReply.setPreply_content(reply.getPreply_content().replace("\n","<br>"));
		return resultReply;
	}
	
	@Override
	public KphUserBoardProjectReply boardProjectReplyReplyUpdate(KphBoardProjectReply reply) {
		System.out.println("KphProjectServiceImp boardProjectReplyReplyUpdate start...");
		KphUserBoardProjectReply resultReply = kphProjectDao.boardProjectReplyReplyUpdate(reply);
		resultReply.setPreply_content(reply.getPreply_content().replace("\n","<br>"));
		return resultReply;
	}
	
}
