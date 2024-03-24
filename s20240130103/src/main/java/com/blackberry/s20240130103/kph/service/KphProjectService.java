package com.blackberry.s20240130103.kph.service;

import java.util.List;
import java.util.Map;

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

public interface KphProjectService {
	
	Map<String, Object> mainLogic(Long user_no);

	int projectAdd(KphProject project);

	List<KphUsers> userListByProjectNoExceptOwn(KphUserProject kphUserProject);

	int eval(KphEval eval);

	List<KphUsers> addressUserList(Long user_no);

	List<KphUsers> addressUserListByName(KphUsers user);

	List<KphProjectTask> totalProjectTaskList(KphProjectTask kphProjectTask);

	int totalProjectTaskCountByKeyword(KphProjectTask kphProjectTask);

	int isUserInProject(KphUserProject kphUserProject);

	Map<String, Object> detailProject(KphTask kphTask);

	List<KphUsers> projectMemberList(Long project_no);

	int taskAdd(List<Long> userNoList, KphTask kphTask);

	List<KphUsers> addressUserListExceptProjectMember(KphUserProject kphUserProject);

	int projectMemberAdd(KphUserProject kphUserProject, List<Long> userNoList);

	KphProject getProjectByProjectNo(KphProject kphProject);

	int projectUpdate(KphProject kphProject);

	int projectDelete(KphProject kphProject);

	int projectMemberDelete(KphUserProject kphUserProject);

	KphTask taskIncludingProjectMember(KphTask kphTask);

	int taskUpdate(List<Long> userNoList, KphTask kphTask);

	int taskDelete(KphTask kphTask);

	int taskCompUpdate(KphTask kphTask);

	KphUserBoardProject getBoardProject(KphBoardProject kphBoardProject);

	KphUserBoardProjectReply boardProjectReplyAdd(KphBoardProjectReply reply);

	boolean isUserInIndentZero(KphBoardProjectReply reply);

	KphUserBoardProjectReply boardProjectReplyReplyAdd(KphBoardProjectReply reply);

	int boardProjectReplyDelete(KphBoardProjectReply reply);

	void boardProjectDelete(KphBoardProject board);

	KphBoardProjectFile getBoardProjectFile(KphBoardProjectFile file);

	int projectEnd(KphProject kphProject);

	KphUserBoardProjectReply updateBoardProjectReply(KphBoardProjectReply reply);

	KphUserBoardProjectReply boardProjectReplyReplyUpdate(KphBoardProjectReply reply);

}
