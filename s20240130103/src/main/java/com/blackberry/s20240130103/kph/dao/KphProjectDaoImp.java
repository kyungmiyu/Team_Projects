package com.blackberry.s20240130103.kph.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.blackberry.s20240130103.kph.model.KphBoardProject;
import com.blackberry.s20240130103.kph.model.KphBoardProjectFile;
import com.blackberry.s20240130103.kph.model.KphBoardProjectReply;
import com.blackberry.s20240130103.kph.model.KphEval;
import com.blackberry.s20240130103.kph.model.KphProject;
import com.blackberry.s20240130103.kph.model.KphProjectTask;
import com.blackberry.s20240130103.kph.model.KphTask;
import com.blackberry.s20240130103.kph.model.KphUserBoardProjectReply;
import com.blackberry.s20240130103.kph.model.KphUserProject;
import com.blackberry.s20240130103.kph.model.KphUserTask;
import com.blackberry.s20240130103.kph.model.KphUsers;
import com.blackberry.s20240130103.kph.model.KphUserBoardProject;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class KphProjectDaoImp implements KphProjectDao {

	private final SqlSession session;
	private final PlatformTransactionManager transactionManager;

	@Override
	public int projectAdd(KphProject project) {
		System.out.println("KphProjectDaoImp projectAdd start...");
		session.insert("kphProjectInsertProc", project);
		return 1;
	}

	@Override
	public List<KphProject> projectList(Long user_no) {
		System.out.println("KphProjectDaoImp projectList start...");
		return session.selectList("kphProjectListByUserNo", user_no);
	}
	
	@Override
	public List<KphTask> unCompTaskListByProjectNo(Long project_no) {
		return session.selectList("kphUnCompTaskListByProjectNo", project_no);
	}

	@Override
	public List<KphTask> compTaskListByProjectNo(Long project_no) {
		return session.selectList("kphCompTaskListByProjectNo", project_no);
	}

	@Override
	public int isEvalByUser(KphProject kphProject) {
		return session.selectOne("kphProjectIsEvalByUser", kphProject);
	}

	@Override
	public List<KphUsers> userListByProjectNoExceptOwn(KphUserProject kphUserProject) {
		System.out.println("KphProjectDaoImp userListByProjectNo start...");
		List<KphUsers> userList = session.selectList("kphUserListByProjectNoExceptOwn", kphUserProject);
		return userList;
	}
	
	@Override
	public int isProjectCompleted(Long project_no) {
		return session.selectOne("KphIsProjectCompleted", project_no);
	}

	@Override
	public int eval(KphEval eval) {
		System.out.println("KphProjectDaoImp eval start...");
		int result = session.update("kphEvalInsert", eval);
		return result;
	}

	@Override
	public List<KphUsers> addressUserList(Long user_no) {
		System.out.println("KphProjectDaoImp addressUserList start...");
		return session.selectList("kphAddressUserList", user_no);
	}

	@Override
	public List<KphUsers> addressUserListByName(KphUsers user) {
		System.out.println("KphProjectDaoImp addressUserListByName start...");
		return session.selectList("kphAddressUserListByName", user);
	}

	@Override
	public List<KphProjectTask> totalProjectTaskList(KphProjectTask kphProjectTask) {
		System.out.println("KphProjectDaoImp addressUserListByName start...");
		return session.selectList("kphTotalTaskList", kphProjectTask);
	}

	@Override
	public int totalProjectTaskCountByKeyword(KphProjectTask kphProjectTask) {
		System.out.println("KphProjectDaoImp totalTaskCountByKeyword start...");
		return session.selectOne("kphTotalTaskCountByKeyword", kphProjectTask);
	}
	
	@Override
	public int isUserInProject(KphUserProject kphUserProject) {
		return session.selectOne("kphIsUserInProject", kphUserProject);
	}
	
	@Override
	public Long projectLeaderNo(Long project_no) {
		return session.selectOne("kphProjectLeaderNo", project_no);
	}
		
	@Override
	public List<KphTask> taskListByProjectNo(KphTask kphTask) {
		System.out.println("KphProjectDaoImp taskListByProjectNo start...");
		return session.selectList("kphTaskListByProjectNo", kphTask);
	}

	@Override
	public List<KphUsers> UserListInTask(KphTask task) {
		System.out.println("KphProjectDaoImp UserListInTask start...");
		return session.selectList("kphUserListInTask", task);
	}

	@Override
	public List<KphUsers> projectMemberList(Long project_no) {
		System.out.println("KphProjectDaoImp userListInProject start...");
		return session.selectList("kphUserListByProjectNo", project_no);
	}

	@Override
	public int taskAdd(List<Long> userNoList, KphTask kphTask) {
		System.out.println("KphProjectDaoImp taskAdd start...");
		
		int result = 0;
		TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try {
			System.out.println("KphProjectDaoImp taskAdd kphTask=> " + kphTask);
			session.insert("kphTaskInsertProc", kphTask);
			System.out.println("KphProjectDaoImp taskAdd insertTaskNo=> " + kphTask.getPo_task_no());
			
			for (int i = 0; i < userNoList.size(); i++) {
				KphUserTask kphUserTask = new KphUserTask();
				kphUserTask.setProject_no(kphTask.getProject_no());
				kphUserTask.setTask_no(kphTask.getPo_task_no());
				kphUserTask.setUser_no(userNoList.get(i));
				int userTaskInsertResult = session.insert("kphUserTaskInsert", kphUserTask);
				System.out.println("KphProjectDaoImp taskAdd insertTaskNo=> " + userTaskInsertResult);
			}
			
			result = 1;
			transactionManager.commit(txStatus);
		} catch (Exception e) {
			result = -1;
			e.printStackTrace();
			transactionManager.rollback(txStatus);
		}
		
		return result;
	}

	@Override
	public List<KphUsers> addressUserListExceptProjectMember(KphUserProject kphUserProject) {
		System.out.println("KphProjectDaoImp addressUserListExceptProjectMember start...");
		return session.selectList("kphAddressUserListExceptProjectMember", kphUserProject);
	}

	@Override
	public int projectMemberAdd(KphUserProject kphUserProject) {
		System.out.println("KphProjectDaoImp projectMemberAdd start...");
		return session.insert("kphProjectMemberAdd", kphUserProject);
	}

	@Override
	public KphProject getProjectByProjectNo(KphProject kphProject) {
		System.out.println("KphProjectDaoImp getProjectByProjectNo start...");
		return session.selectOne("kphGetProjectByProjectNo", kphProject);
	}

	@Override
	public int projectUpdate(KphProject kphProject) {
		System.out.println("KphProjectDaoImp projectUpdate start...");
		return session.update("kphProjectUpdate", kphProject);
	}

	@Override
	public int projectDelete(KphProject kphProject) {
		System.out.println("KphProjectDaoImp projectDelete start...");
		return session.update("kphProjectDelete", kphProject);
	}

	@Override
	public int projectEnd(KphProject kphProject) {
		System.out.println("KphProjectDaoImp projectDelete start...");
		return session.update("KphProjectEnd", kphProject);
	}

	@Override
	public int projectMemberDelete(KphUserProject kphUserProject) {
		System.out.println("KphProjectDaoImp projectMemberDelete start...");
		return session.delete("kphProjectMemberDelete", kphUserProject);
	}

	@Override
	public KphTask getTask(KphTask kphTask) {
		System.out.println("KphProjectDaoImp getTask start...");
		KphTask task = session.selectOne("kphGetTask", kphTask);
		return task;
	}

	@Override
	public List<KphUsers> projectMemberListIncludingIsInTask(KphTask task) {
		System.out.println("KphProjectDaoImp getTask start...");
		return session.selectList("kphProjectMemberListIncludingIsInTask", task);
	}

	@Override
	public int taskUpdate(List<Long> userNoList, KphTask kphTask) {
		System.out.println("KphProjectDaoImp taskUpdate start...");
		
		int result = 0;
		TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try {
			session.delete("kphUserTaskDelete", kphTask);
			session.update("kphTaskUpdateProc", kphTask);
			
			for (int i = 0; i < userNoList.size(); i++) {
				KphUserTask kphUserTask = new KphUserTask();
				kphUserTask.setProject_no(kphTask.getProject_no());
				kphUserTask.setTask_no(kphTask.getPo_task_no());
				kphUserTask.setUser_no(userNoList.get(i));
				session.insert("kphUserTaskInsert", kphUserTask);
			}
			
			result = 1;
			transactionManager.commit(txStatus);
		} catch (Exception e) {
			result = -1;
			e.printStackTrace();
			transactionManager.rollback(txStatus);
		}
		
		return result;
	}

	@Override
	public int taskDelete(KphTask kphTask) {
		System.out.println("KphProjectDaoImp projectDelete start...");
		int totalResult = 0;
		
		TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try {
			int result1 = session.delete("kphUserTaskDelete", kphTask);
			int result2 = session.delete("kphTaskDelete", kphTask);
			
			if(result1 == 1 && result2 == 1) {
				totalResult = 1;
			}
			
			transactionManager.commit(txStatus);
		} catch (Exception e) {
			totalResult = -1;
			e.printStackTrace();
			transactionManager.rollback(txStatus);
		}
		
		return totalResult;
	}

	@Override
	public int taskCompUpdate(KphTask kphTask) {
		System.out.println("KphProjectDaoImp taskCompUpdate start...");
		kphTask = session.selectOne("kphGetTask", kphTask);
		return session.update("kphTaskCompUpdate", kphTask);
	}

	@Override
	public KphUserBoardProject getBoardProjectByPboardNo(KphBoardProject kphBoardProject) {
		System.out.println("KphProjectDaoImp getBoardProject start...");
		KphUserBoardProject boardProject = new KphUserBoardProject();
		boardProject = session.selectOne("kphGetBoardProjectByPboardNo", kphBoardProject);
		return boardProject;
	}
	
	@Override
	public void IncreaseBoardProjectCnt(KphBoardProject kphBoardProject) {
		session.update("KphIncreaseBoardProjectCnt", kphBoardProject);
	}
	
	@Override
	public List<KphBoardProjectFile> boardProjectFileList(KphUserBoardProject boardProject) {
		return session.selectList("KphBoardProjectFileList", boardProject);
	}
	
	@Override
	public List<Long> replyGroupListByPboardNo(Long pboard_no) {
		return session.selectList("KphReplyGroupListByPboardNo", pboard_no);
	}
	
	@Override
	public List<KphUserBoardProjectReply> boardProjectReplyList(KphUserBoardProject boardProject) {
		return session.selectList("KphBoardProjectReplyList", boardProject);
	}
	
	@Override
	public int boardProjectReplyCnt(Long pboard_no) {
		return session.selectOne("KphBoardProjectReplyCnt", pboard_no);
	}

	@Override
	public KphUserBoardProjectReply boardProjectReplyAdd(KphBoardProjectReply reply) {
		System.out.println("KphProjectDaoImp boardProjectReplyAdd start...");
		
		TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
		KphUserBoardProjectReply resultReply = null;
		
		try {
			session.insert("KphboardProjectReplyAdd", reply);
			System.out.println(reply);
			resultReply = session.selectOne("KphUserBoardProjectReplyByPreplyNo", reply.getPreply_no());
			System.out.println(resultReply);
			transactionManager.commit(txStatus);
		} catch (Exception e) {
			e.printStackTrace();
			transactionManager.rollback(txStatus);
		}
		
		return resultReply;
	}
	
	@Override
	public int isUserInIndentZero(KphBoardProjectReply reply) {
		System.out.println("KphProjectDaoImp isUserInIndentZero start...");
		int result = session.selectOne("KphIsUserInIndentZero", reply);
		return result;
	}
	
	@Override
	public KphUserBoardProjectReply boardProjectReplyReplyAdd(KphBoardProjectReply reply) {
		System.out.println("KphProjectDaoImp boardProjectReplyReplyAdd start...");
		
		TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
		KphUserBoardProjectReply resultReply = null;
		
		try {
			session.insert("KphboardProjectReplyReplyAdd", reply);
			System.out.println(reply);
			resultReply = session.selectOne("KphUserBoardProjectReplyByPreplyNo", reply.getPreply_no());
			System.out.println(resultReply);
			transactionManager.commit(txStatus);
		} catch (Exception e) {
			e.printStackTrace();
			transactionManager.rollback(txStatus);
		}
		
		return resultReply;
	}
	
	@Override
	public int boardProjectReplyDelete(Long preply_no) {
		return session.update("KphBoardProjectReplyDelete", preply_no);
	}
	
	@Override
	public void boardProjectDelete(KphBoardProject board) {
		session.update("KphBoardProjectDelete", board);
	}
	
	@Override
	public KphBoardProjectFile getBoardProjectFile(KphBoardProjectFile file) {
		return session.selectOne("KphGetBoardProjectFile", file);
	}
	
	@Override
	public KphUserBoardProjectReply updateBoardProjectReply(KphBoardProjectReply reply) {
		
		TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
		KphUserBoardProjectReply resultReply = null;
		
		try {
			session.update("KphUpdateBoardProjectReply", reply);
			resultReply = session.selectOne("KphUserBoardProjectReplyByPreplyNo", reply.getPreply_no());
			transactionManager.commit(txStatus);
		} catch (Exception e) {
			e.printStackTrace();
			transactionManager.rollback(txStatus);
		}
		
		return resultReply;
	}
	
	@Override
	public KphUserBoardProjectReply boardProjectReplyReplyUpdate(KphBoardProjectReply reply) {
		System.out.println("KphProjectDaoImp boardProjectReplyReplyAdd start...");
		
		TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
		KphUserBoardProjectReply resultReply = null;
		
		try {
			session.update("KphboardProjectReplyReplyUpdate", reply);
			System.out.println(reply);
			resultReply = session.selectOne("KphUserBoardProjectReplyByPreplyNo", reply.getPreply_no());
			System.out.println(resultReply);
			transactionManager.commit(txStatus);
		} catch (Exception e) {
			e.printStackTrace();
			transactionManager.rollback(txStatus);
		}
		
		return resultReply;
	}
	
}
