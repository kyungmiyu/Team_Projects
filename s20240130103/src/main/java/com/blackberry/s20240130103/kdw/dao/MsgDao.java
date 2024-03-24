package com.blackberry.s20240130103.kdw.dao;

import java.util.List;

import com.blackberry.s20240130103.kdw.model.Message;
import com.blackberry.s20240130103.kdw.model.MessageFile;
import com.blackberry.s20240130103.lhs.model.User;

public interface MsgDao {
	
	// 전체유저 리스트
	List<User> getAllUsers();
	
	// 받은쪽지함 리스트
	List<Message> getReceivedMessages(Long msgReceiver, int start, int end);
	// 보낸쪽지함 리스트
	List<Message> getSentMessages(Long msgSender, int start, int end);
	// 쪽지보관함 리스트
	List<Message> getStoredMessages(Long storeboxUserNo, int start, int end);
	// 휴지통 리스트
	List<Message> getTrashMessages(Long trashboxUserNo, int start, int end);
	
	// 받은쪽지 쪽지 개수
	int totReceiveMsgCnt(Long msgReceiver);
	// 받은쪽지 읽지 않은 개수
	int totUnreadReceiveMsgCnt(Long msgReceiver); 
	
	// 보낸쪽지 쪽지 개수
	int totSentMsgCnt(Long msgSender);
	int totUnreadSentMsgCnt(Long msgSender);
	
	// 쪽지보관함 쪽지 개수
	int totStoredMsgCnt(Long storeboxUserNo);
	int totUnreadStoredMsgCnt(Long storeboxUserNo);
	
	// 휴지통 쪽지 개수
	int totTrashMsgCnt(Long trashboxUserNo);
	int totUnreadTrashMsgCnt(Long trashboxUserNo);
	
	
	
	// 받은쪽지 & 보낸쪽지 쪽지정보 가져오기
	Message getMessageByInfo(Long msgNo);
	// 받은쪽지 읽은시간 업데이트
	void updateReadDate(Long msgNo);
	// ========= !! 버튼 기능 !! ========
	// 보관 버튼 클릭시 msg_store_chk = 1
	void updateMsgStoreStatus(List<Long> msgNos);
	// 삭제 버튼 클릭시 msg_delete_chk = 1
	void updateMsgDeleteStatus(List<Long> msgNos);
	// 영구삭제버튼 클릭시 해당 쪽지 영구삭제
	void permanentDeleteMessages(List<Long> msgNos);
	void deleteMessageFilesByMsgNo(Long msgNo);
	// 쪽지 보내기 버튼 클릭스 쪽지 보내기 Insert
	void sendMsg(Message message);
	// !! 파일 업로드 !! 저장 처리
	void saveMessageFile(MessageFile messageFile);
	
	// ======== 쪽지에 첨부된 파일 다운로드 기능 ========
	// 파일이 첨부된 쪽지 목록 리스트 불러오기
	List<MessageFile> getMessageFiles(Long msgNo);
	// 첨부파일 상세정보
	MessageFile getFileDetail(Long msgNo, int fileCnt);
	
	// ========= !! 검색기능 !! ========
	
	// ========== 받은 쪽지함 검색기능 ===========
	// 검색한 쪽지개수
	int searchReceiveMsgCnt(Long msgReceiver, String keyword, String type);
	// 검색한 읽지않은 쪽지개수
	int searchTotUnreadReceiveMsgCnt(Long msgReceiver, String keyword, String type);
	// 검색한 쪽지리스트
	List<Message> searchReceivedMessages(Long msgReceiver, String keyword, String type, int start, int end);
	
	// ========== 보낸 쪽지함 검색기능 ===========
	// 검색한 쪽지개수
	int searchSentMsgCnt(Long msgSender, String keyword, String type);
	// 검색한 읽지않은 쪽지개수
	int searchTotUnreadSentMsgCnt(Long msgSender, String keyword, String type);
	// 검색한 쪽지리스트
	List<Message> searchSentMessages(Long msgSender, String keyword, String type, int start, int end);
	
	// ========== 쪽지 보관함 검색기능 ===========
	// 검색한 쪽지개수
	int searchStoredMsgCnt(Long storeboxUserNo, String keyword, String type);
	// 검색한 읽지않은 쪽지개수
	int searchTotUnreadStoredMsgCnt(Long storeboxUserNo, String keyword, String type);
	// 검색한 쪽지리스트
	List<Message> searchStoredMessages(Long storeboxUserNo, String keyword, String type, int start, int end);
	
	// ========== 휴지통 검색기능 ===========
	// 검색한 쪽지개수
	int searchTrashMsgCnt(Long trashboxUserNo, String keyword, String type);
	// 검색한 읽지않은 쪽지개수
	int searchTotUnreadTrashMsgCnt(Long trashboxUserNo, String keyword, String type);
	// 검색한 쪽지리스트
	List<Message> searchTrashMessages(Long trashboxUserNo, String keyword, String type, int start, int end);

	
	
	// 워크스패이스 쪽지 아이콘 누르면 인풋창에 해당유저 아이디(히든인풋) 닉네임 박히기
	// 유저넘버로 닉네임 가져오기
	User getUserNicUserId(Long receiverId);

	

	


	

	

	

	

	

	


	


	


	



	
	
	

	
	

	
	

	
	

	
	
}
