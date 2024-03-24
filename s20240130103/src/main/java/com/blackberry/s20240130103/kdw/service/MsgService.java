package com.blackberry.s20240130103.kdw.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.blackberry.s20240130103.kdw.model.Message;
import com.blackberry.s20240130103.kdw.model.MessageFile;
import com.blackberry.s20240130103.lhs.model.User;

import jakarta.servlet.http.HttpServletRequest;

public interface MsgService {
	
	// 전체 유저 리스트 가져와서 뿌려주기(나중에 주소록으로 바꿔야함)
	List<User> getAllUsers();
	
	// 받은쪽지함 쪽지 리스트
	List<Message> getReceivedMessages(Long msgReceiver, int start, int end);
	// 보낸쪽지함 쪽지 리스트
	List<Message> getSentMessages(Long msgSender, int start, int end);
	// 쪽지보관함 쪽지 리스트
	List<Message> getStoredMessages(Long storeboxUserNo, int start, int end);
	// 휴지통 쪽지 리스트
	List<Message> getTrashMessages(Long trashboxUserNo, int start, int end);
	
	// 받은쪽지 개수
	int totReceiveMsgCnt(Long msgReceiver);
	// 받은쪽지중 읽지 않은 쪽지 개수 
	int totUnreadReceiveMsgCnt(Long msgReceiver);
	
	// 보낸쪽지 개수
	int totSentMsgCnt(Long msgSender);
	// 보낸쪽지중 읽지 않은 쪽지 개수 
	int totUnreadSentMsgCnt(Long msgSender);
	
	// 쪽지보관함 쪽지 개수
	int totStoredMsgCnt(Long storeboxUserNo);
	// 쪽지보관함중 읽지 않은 쪽지 개수
	int totUnreadStoredMsgCnt(Long storeboxUserNo);
	// 휴지통 쪽지 개수
	int totTrashMsgCnt(Long trashboxUserNo);
	// 휴지통중 읽지 않은 쪽지 개수
	int totUnreadTrashMsgCnt(Long trashboxUserNo);
	
	// 받은쪽지함 쪽지 정보
	Message getReceivedMessageByInfo(Long msgNo);
	// 받은쪽지 읽은시간 업데이트
	void updateReadDate(Long msgNo);
	// 보낸쪽지함 쪽지 정보 읽음 업데이트(할 필요없음)
	Message getSentMessageByInfo(Long msgNo);
	
	// 보관버튼 클릭시 보관함으로 이동
	void updateMsgStoreStatus(List<Long> msgNos);
	// 삭제버튼 클릭시 휴지통으로 이동
	void updateMsgDeleteStatus(List<Long> msgNos);
	// 영구삭제버튼 클릭시 해당 쪽지 영구삭제
	void permanentDeleteMessages(List<Long> msgNos);
	void deleteMessageFilesByMsgNo(Long msgNo);
	// 쪽지 보내기 (파일 업로드 포함)
	void sendMsg(Message message, MultipartFile[] files, String path);
	
	// ======== 쪽지에 첨부된 파일 다운로드 기능 ========
	// 파일이 첨부된 쪽지 목록 리스트 불러오기
	List<MessageFile> getMessageFiles(Long msgNo);
	// 파일 상세 
	MessageFile getFileDetail(Long msgNo, int fileCnt);
	
	// ========= !! 검색기능 !! ========

	// ======= 받은쪽지함 검색 =======
	// 검색한 쪽지 개수
	int searchReceiveMsgCnt(Long msgReceiver, String keyword, String type);
	// 검색한 읽지않은 쪽지개수
	int searchTotUnreadReceiveMsgCnt(Long msgReceiver, String keyword, String type);
	// 검색한 리스트
	List<Message> searchReceivedMessages(Long msgReceiver, String keyword, String type, int start, int end);
	
	// ======= 보낸쪽지함 검색 =======
	// 검색한 쪽지 개수
	int searchSentMsgCnt(Long msgSender, String keyword, String type);
	// 검색한 읽지않은 쪽지개수
	int searchTotUnreadSentMsgCnt(Long msgSender, String keyword, String type);
	// 검색한 리스트
	List<Message> searchSentMessages(Long msgSender, String keyword, String type, int start, int end);
	
	// ======= 쪽지보관함 검색 =======
	// 검색한 쪽지 개수
	int searchStoredMsgCnt(Long storeboxUserNo, String keyword, String type);
	// 검색한 읽지않은 쪽지개수
	int searchTotUnreadStoredMsgCnt(Long storeboxUserNo, String keyword, String type);
	// 검색한 리스트
	List<Message> searchStoredMessages(Long storeboxUserNo, String keyword, String type, int start, int end);
	
	// ======= 휴지통 검색 =======
	// 검색한 쪽지 개수
	int searchTrashMsgCnt(Long trashboxUserNo, String keyword, String type);
	// 검색한 읽지않은 쪽지개수
	int searchTotUnreadTrashMsgCnt(Long trashboxUserNo, String keyword, String type);
	// 검색한 리스트
	List<Message> searchTrashMessages(Long trashboxUserNo, String keyword, String type, int start, int end);

	
	// 워크스패이스 쪽지 아이콘 누르면 인풋창에 해당유저 아이디(히든인풋) 닉네임 박히기
	// 닉네임,아이디 가져오기
	User findUserDetailsById(Long userNo);

	

	

	

	

	



	

	


	



	
	

	
	
	
	
	

	
	
	
	
    

    

	

}
