package com.blackberry.s20240130103.lhs.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blackberry.s20240130103.lhs.dao.AdminDao;
import com.blackberry.s20240130103.lhs.model.BoardAdmin;
import com.blackberry.s20240130103.lhs.model.BoardComm;
import com.blackberry.s20240130103.lhs.model.Reply;
import com.blackberry.s20240130103.lhs.model.User;
import com.blackberry.s20240130103.lsl.model.LslboardFile;
import com.blackberry.s20240130103.yhs.model.Ask;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService{
	
	private final AdminDao adminDao;
	
	@Override
	public Map<String, Long> selectTablesCnt() {
		Map<String, Long> tableCntMap = adminDao.selectTablesCnt();
		return tableCntMap;
	}
	
	@Override
	public List<Map<String, Long>> selectUserJoinCnt() {
		List<Map<String, Long>> userJoinCntList = adminDao.selectUserJoinCnt();
		return userJoinCntList;
	}
	
	@Override
	public List<User> selectUsersDeleteRequest() {
		List<User> deleteUserList = adminDao.selectUsersDeleteRequest();
		return deleteUserList;
	}
	
	@Override
	public List<BoardAdmin> selectBoardAdminList() {
		List<BoardAdmin> boardAdminList = adminDao.selectBoardAdminList();
		return boardAdminList;
	}
	
	@Override
	public int selectBoardCommCnt(BoardComm board) {
		int boardCommCnt = adminDao.selectBoardCommCnt(board);
		return boardCommCnt;
	}
	
	@Override
	public List<BoardComm> selectBoardCommList(BoardComm board) {
		List<BoardComm> boardCommList = adminDao.selectBoardCommList(board);
		return boardCommList;
	}
	
	@Override
	public BoardComm selectBoard(BoardComm board) {
		BoardComm detailBoard = adminDao.selectBoard(board);
		return detailBoard;
	}
	
	@Override
	public int deleteBoard(BoardComm board) {
		int result = adminDao.deleteBoard(board);
		return result;
	}
	
	@Override
	public int selectUsersCnt(User user) {
		int result = adminDao.selectUsersCnt(user);
		return result;
	}
	
	@Override
	public List<User> selectUsersList(User user) {
		List<User> userList = adminDao.selectUsersList(user);
		return userList;
	}
	
	@Override
	public User selectUserDetail(User user) {
		User userDetail = adminDao.selectUserDetail(user);
		return userDetail;
	}
	
	@Override
	public int deleteUser(User user) {
		int result = adminDao.deleteUser(user);
		return result;
	}
	
	@Override
	public int selectReplyCnt(Reply reply) {
		int replyCnt = adminDao.selectReplyCnt(reply);
		return replyCnt;
	}
	
	@Override
	public List<Reply> selectReplyList(Reply reply) {
		List<Reply> replyList = adminDao.selectReplyList(reply);
		return replyList;
	}
	
	@Override
	public int deleteReply(Reply reply) {
		int result = adminDao.deleteReply(reply);
		return result;
	}
	
	@Override
	public int selectAskCnt(Ask ask) {
		int askCnt = adminDao.selectAskCnt(ask);
		return askCnt;
	}
	
	@Override
	public List<Ask> selectAskList(Ask ask) {
		List<Ask> askList = adminDao.selectAskList(ask);
		return askList;
	}
	
	@Override
	public Ask selectAskDetail(Ask ask) {
		Ask askDetail = adminDao.selectAskDetail(ask);
		return askDetail;
	}
	
	@Override
	public int insertAskResponse(Ask ask) {
		int result = adminDao.insertAskResponse(ask);
		int updateresult = adminDao.updateAskResponse(ask);
		return result;
	}
	
	@Override
	public List<LslboardFile> selectBoardFileList(int cboard_no) {
		List<LslboardFile> boardFileList = adminDao.selectBoardFileList(cboard_no);
		return boardFileList;
	}
}
