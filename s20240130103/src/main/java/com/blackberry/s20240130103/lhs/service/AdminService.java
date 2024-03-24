package com.blackberry.s20240130103.lhs.service;

import java.util.List;
import java.util.Map;

import com.blackberry.s20240130103.lhs.model.BoardAdmin;
import com.blackberry.s20240130103.lhs.model.BoardComm;
import com.blackberry.s20240130103.lhs.model.Reply;
import com.blackberry.s20240130103.lhs.model.User;
import com.blackberry.s20240130103.lsl.model.LslboardFile;
import com.blackberry.s20240130103.yhs.model.Ask;

public interface AdminService {

	Map<String, Long> selectTablesCnt();

	List<Map<String, Long>> selectUserJoinCnt();

	List<User> selectUsersDeleteRequest();

	List<BoardAdmin> selectBoardAdminList();

	int selectBoardCommCnt(BoardComm board);

	List<BoardComm> selectBoardCommList(BoardComm board);

	BoardComm selectBoard(BoardComm board);

	int deleteBoard(BoardComm board);

	int selectUsersCnt(User user);

	List<User> selectUsersList(User user);

	User selectUserDetail(User user);

	int deleteUser(User user);

	List<Reply> selectReplyList(Reply reply);

	int selectReplyCnt(Reply reply);

	int deleteReply(Reply reply);

	int selectAskCnt(Ask ask);

	List<Ask> selectAskList(Ask ask);

	Ask selectAskDetail(Ask ask);

	int insertAskResponse(Ask ask);

	List<LslboardFile> selectBoardFileList(int cboard_no);

}
