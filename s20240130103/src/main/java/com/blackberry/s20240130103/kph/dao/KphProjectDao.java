package com.blackberry.s20240130103.kph.dao;

import java.util.List;
import java.util.Map;

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

public interface KphProjectDao {

	int projectAdd(KphProject project);

	List<KphProject> projectList(Long user_no);

	List<KphTask> unCompTaskListByProjectNo(Long project_no);

	List<KphTask> compTaskListByProjectNo(Long project_no);

	int isEvalByUser(KphProject kphProject);

	List<KphUsers> userListByProjectNoExceptOwn(KphUserProject kphUserProject);

	int eval(KphEval eval);

	List<KphUsers> addressUserList(Long user_no);

	List<KphUsers> addressUserListByName(KphUsers user);

	List<KphProjectTask> totalProjectTaskList(KphProjectTask kphProjectTask);

	int totalProjectTaskCountByKeyword(KphProjectTask kphProjectTask);
	
	int isUserInProject(KphUserProject kphUserProject);

	List<KphTask> taskListByProjectNo(KphTask kphTask);

	List<KphUsers> UserListInTask(KphTask task);

	List<KphUsers> projectMemberList(Long project_no);

	int taskAdd(List<Long> userNoList, KphTask kphTask);

	Long projectLeaderNo(Long project_no);

	List<KphUsers> addressUserListExceptProjectMember(KphUserProject kphUserProject);

	int projectMemberAdd(KphUserProject kphUserProject);

	KphProject getProjectByProjectNo(KphProject kphProject);

	int projectUpdate(KphProject kphProject);

	int projectDelete(KphProject kphProject);

	int projectMemberDelete(KphUserProject kphUserProject);

	KphTask getTask(KphTask kphTask);

	List<KphUsers> projectMemberListIncludingIsInTask(KphTask task);

	int taskUpdate(List<Long> userNoList, KphTask kphTask);

	int taskDelete(KphTask kphTask);

	int taskCompUpdate(KphTask kphTask);

	KphUserBoardProject getBoardProjectByPboardNo(KphBoardProject kphBoardProject);

	void IncreaseBoardProjectCnt(KphBoardProject kphBoardProject);

	List<KphBoardProjectFile> boardProjectFileList(KphUserBoardProject boardProject);

	List<Long> replyGroupListByPboardNo(Long pboard_no);

	List<KphUserBoardProjectReply> boardProjectReplyList(KphUserBoardProject boardProject);

	int boardProjectReplyCnt(Long pboard_no);

	KphUserBoardProjectReply boardProjectReplyAdd(KphBoardProjectReply reply);

	int isUserInIndentZero(KphBoardProjectReply reply);

	KphUserBoardProjectReply boardProjectReplyReplyAdd(KphBoardProjectReply reply);

	int boardProjectReplyDelete(Long preply_no);

	void boardProjectDelete(KphBoardProject board);

	KphBoardProjectFile getBoardProjectFile(KphBoardProjectFile file);

	int projectEnd(KphProject kphProject);

	int isProjectCompleted(Long project_no);

	KphUserBoardProjectReply updateBoardProjectReply(KphBoardProjectReply reply);

	KphUserBoardProjectReply boardProjectReplyReplyUpdate(KphBoardProjectReply reply);

}
