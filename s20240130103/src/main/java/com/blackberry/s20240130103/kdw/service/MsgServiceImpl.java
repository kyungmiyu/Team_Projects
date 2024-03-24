package com.blackberry.s20240130103.kdw.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.blackberry.s20240130103.kdw.dao.MsgDao;
import com.blackberry.s20240130103.kdw.model.Message;
import com.blackberry.s20240130103.kdw.model.MessageFile;
import com.blackberry.s20240130103.lhs.model.User;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional // 마이바티스에서도 가능한가?
@Slf4j
public class MsgServiceImpl implements MsgService {
	
	private final MsgDao msgDao;

	// 유저 전체 리스트 가져오기
	@Override
	public List<User> getAllUsers() {
		log.info("MsgServiceImpl getAllUsers start...");
		return msgDao.getAllUsers();
	}

	// ========== 각 쪽지함 쪽지 개수 가져오기 ============
	// 받은 쪽지 개수
	@Override
	public int totReceiveMsgCnt(Long msgReceiver) {
		log.info("MsgServiceImpl totReceiveMsgCnt start...");
		int totReceiveMsgCnt = msgDao.totReceiveMsgCnt(msgReceiver);
		log.info("MsgServiceImpl totReceiveMsgCnt totMsgCnt->" + totReceiveMsgCnt);
		return totReceiveMsgCnt;
	}
	// 받은 쪽지중 읽지 않은 쪽지 개수 
	@Override
	public int totUnreadReceiveMsgCnt(Long msgReceiver) {
		log.info("MsgServiceImpl totUnreadReceiveMsgCnt start...");
		int totUnreadReceiveMsgCnt = msgDao.totUnreadReceiveMsgCnt(msgReceiver);
		log.info("MsgServiceImpl totUnreadReceiveMsgCnt totMsgCnt->" + totUnreadReceiveMsgCnt);
		return totUnreadReceiveMsgCnt;
	}
	
	// 보낸 쪽지 개수
	@Override
	public int totSentMsgCnt(Long msgSender) {
		log.info("MsgServiceImpl totSendMsgCnt start...");
		int totSentMsgCnt = msgDao.totSentMsgCnt(msgSender);
		log.info("MsgServiceImpl totSendMsgCnt totMsgCnt->" + totSentMsgCnt);
		return totSentMsgCnt;
	}
	// 보낸 쪽지중 읽지 않은 쪽지 개수 
	@Override
	public int totUnreadSentMsgCnt(Long msgSender) {
		log.info("MsgServiceImpl totUnreadSentMsgCnt start...");
		int totUnreadSentMsgCnt = msgDao.totUnreadSentMsgCnt(msgSender);
		log.info("MsgServiceImpl totUnreadSentMsgCnt totMsgCnt->" + totUnreadSentMsgCnt);
		return totUnreadSentMsgCnt;
	}
	
	// 보관함 쪽지 개수
	@Override
	public int totStoredMsgCnt(Long storeboxUserNo) {
		log.info("MsgServiceImpl totStoredMsgCnt start...");
		int totStoredMsgCnt = msgDao.totStoredMsgCnt(storeboxUserNo);
		log.info("MsgServiceImpl totStoredMsgCnt totMsgCnt->" + totStoredMsgCnt);
		return totStoredMsgCnt;
	}
	// 보낸 쪽지중 읽지 않은 쪽지 개수 
	@Override
	public int totUnreadStoredMsgCnt(Long msgSender) {
		log.info("MsgServiceImpl totUnreadStoredMsgCnt start...");
		int totUnreadStoredMsgCnt = msgDao.totUnreadStoredMsgCnt(msgSender);
		log.info("MsgServiceImpl totUnreadStoredMsgCnt totMsgCnt->" + totUnreadStoredMsgCnt);
		return totUnreadStoredMsgCnt;
	}
	
	// 휴지통 쪽지 개수
	@Override
	public int totTrashMsgCnt(Long trashboxUserNo) {
		log.info("MsgServiceImpl totTrashMsgCnt start...");
		int totTrashMsgCnt = msgDao.totTrashMsgCnt(trashboxUserNo);
		log.info("MsgServiceImpl totTrashMsgCnt totMsgCnt->" + totTrashMsgCnt);
		return totTrashMsgCnt;
	}
	// 보낸 쪽지중 읽지 않은 쪽지 개수 
	@Override
	public int totUnreadTrashMsgCnt(Long msgSender) {
		log.info("MsgServiceImpl totUnreadTrashMsgCnt start...");
		int totUnreadTrashMsgCnt = msgDao.totUnreadTrashMsgCnt(msgSender);
		log.info("MsgServiceImpl totUnreadTrashMsgCnt totMsgCnt->" + totUnreadTrashMsgCnt);
		return totUnreadTrashMsgCnt;
	}
	
	// ========== 각 쪽지함 리스트 가져오기 ============
	// 받은 쪽지 리스트
	@Override
	public List<Message> getReceivedMessages(Long msgReceiver, int start, int end) {
		log.info("MsgServiceImpl getReceivedMessages start...");
		return msgDao.getReceivedMessages(msgReceiver, start, end);
	}

	// 보낸 쪽지 리스트
	@Override
	public List<Message> getSentMessages(Long msgSender, int start, int end) {
		log.info("MsgServiceImpl getSentMessages start...");
		return msgDao.getSentMessages(msgSender, start, end);
	}

	// 보관함 쪽지 리스트
	@Override
	public List<Message> getStoredMessages(Long storeboxUserNo, int start, int end) {
		log.info("MsgServiceImpl getStoredMessages start...");
		return msgDao.getStoredMessages(storeboxUserNo, start, end);
	}

	// 휴지통 쪽지 리스트
	@Override
	public List<Message> getTrashMessages(Long trashboxUserNo, int start, int end) {
		log.info("MsgServiceImpl getTrashMessages start...");
		return msgDao.getTrashMessages(trashboxUserNo, start, end);
	}

	// ========== 받은쪽지 읽기 & 보낸쪽지 읽기 읽은시간 업데이트============
	// 받은 쪽지 정보
	@Override
	public Message getReceivedMessageByInfo(Long msgNo) {
		log.info("MsgServiceImpl getReceivedMessageById start...");
		Message receivedMessageInfo = msgDao.getMessageByInfo(msgNo);

		// 읽은 시간이 'null'이면 업데이트
		if (receivedMessageInfo.getMsg_readdate() == null) {
			updateReadDate(msgNo);
			log.info("msg_readdate update... msgNo => ", msgNo);

			// 업데이트 후에는 새로 받아옴
			receivedMessageInfo = msgDao.getMessageByInfo(msgNo);
		}
		return receivedMessageInfo;
	}

	// 받은 쪽지 읽은 시간 업데이트
	@Override
	public void updateReadDate(Long msgNo) {
		log.info("MsgServiceImpl updateReadDate start...");
		msgDao.updateReadDate(msgNo);
	}

	// 보낸 쪽지 정보
	@Override
	public Message getSentMessageByInfo(Long msgNo) {
		log.info("MsgServiceImpl getSentMessageById start...");
		Message sentMessageInfo = msgDao.getMessageByInfo(msgNo);
		log.info("MsgServiceImpl getSentMessageById msgNo => " + msgNo);
		log.info("MsgServiceImpl getSentMessageById sentMessageInfo => " + sentMessageInfo);
		return sentMessageInfo;
	}

	@Override
	public void updateMsgStoreStatus(List<Long> msgNos) {
		log.info("MsgServiceImpl updateMsgStoreStatus start...");
		msgDao.updateMsgStoreStatus(msgNos);
	}

	@Override
	public void updateMsgDeleteStatus(List<Long> msgNos) {
		log.info("MsgServiceImpl updateMsgDeleteStatus start...");
		msgDao.updateMsgDeleteStatus(msgNos);
	}

	// 쪽지 영구 삭제 서비스 구현
	@Override
	public void permanentDeleteMessages(List<Long> msgNos) {
		log.info("MsgServiceImpl permanentDeleteMessages start...");
		msgDao.permanentDeleteMessages(msgNos);
	}
	@Override
	public void deleteMessageFilesByMsgNo(Long msgNo) {
		log.info("MsgServiceImpl deleteMessageFilesByMsgNo start...");
		msgDao.deleteMessageFilesByMsgNo(msgNo);
	}

	
	// 쪽지 보내기
	@Override
	public void sendMsg(Message message, MultipartFile[] files, String path) {
		log.info("MsgServiceImpl sendMsg start...");

		// 쪽지 정보 DB에 저장
		msgDao.sendMsg(message);

		// 파일 업로드 처리
		if (files != null && files.length > 0) {
		    File file = new File(path);

		    if (!file.exists()) {
		        boolean check = file.mkdirs();

		        // 디렉토리 생성에 실패한 경우 콘솔에 출력
		        if (!check) {
		            System.err.println("Failed to create directory: " + path);
		        }
		    }
		    for (MultipartFile f : files) {
		        if (!f.isEmpty()) {
		            System.out.println("file => " + f.getOriginalFilename());

		            try {
		                // HDD에 저장
		                String fileName = saveFile(f, path);
		                File uploadedFile = new File(path, fileName); // 파일 저장 후 생성된 파일 객체
		                
		                // DB에 저장
		                MessageFile messageFile = new MessageFile();
		                messageFile.setMsg_no(message.getMsg_no()); // 쪽지 번호 설정
		                messageFile.setMsg_file_name(fileName); // 첨부파일 가공 식별 저장명 설정
		                messageFile.setMsg_file_user_name(f.getOriginalFilename()); // 첨부파일 오리지날 유저명 설정
		                
		                // DB에 파일 정보 저장
		                msgDao.saveMessageFile(messageFile);
		            } catch (IOException e) {
		                e.printStackTrace();
		                System.err.println("Error saving file: " + e.getMessage());
		            }
		        }
		    }
		}
		log.info("MsgServiceImpl sendMsg end...");
	}
	// 파일 저장 : 다수의 유저에게 반복처리할때는 
	public String saveFile(MultipartFile multipartFile, String path) throws IOException {
	    String originalFilename = multipartFile.getOriginalFilename();
	    String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
	    String fileName = UUID.randomUUID().toString() + fileExtension;

	    File targetFile = new File(path, fileName);
	    // 파일 복사
	    // MultipartFile.transferTo() 대신에 파일의 내용을 읽고 지정된 경로에 쓰는 방식으로 변경
	    // 임시 파일 관리는 프레임워크 또는 서버가 담당 : 요청 처리가 완료되면 자동으로 이러한 임시 파일을 정리
	    Files.copy(multipartFile.getInputStream(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

	    return fileName;
	}
	
	/* // 단일 객체 할떄 사용하던 메서드
	 * // 파일 저장 
	 * public String saveFile(MultipartFile multipartFile, String path)
	 * throws IOException { // 1. 중복되지 않는 파일명 생성(UUID, Date) String fileName =
	 * UUID.randomUUID().toString();
	 * 
	 * // 2. 확장자 String originalFilename = multipartFile.getOriginalFilename();
	 * String fileExtension =
	 * originalFilename.substring(originalFilename.lastIndexOf(".")); fileName +=
	 * fileExtension;
	 * 
	 * // 3. 파일 저장 File file = new File(path, fileName);
	 * multipartFile.transferTo(file);
	 * 
	 * return fileName; }
	 */
	
	// =========== 첨부 파일 다운로드 ============
	// 파일 첨부된 쪽지들 리스트 불러오기
    @Override
    public List<MessageFile> getMessageFiles(Long msgNo) {
        log.info("MsgServiceImpl getMessagesWithFiles start...");
        List<MessageFile> messagesWithFiles = msgDao.getMessageFiles(msgNo);
        log.info("MsgServiceImpl getMessagesWithFiles messagesWithFiles size: " + messagesWithFiles.size());
        return messagesWithFiles;
    }
	// 첨부파일 상세정보
	@Override
	public MessageFile getFileDetail(Long msgNo, int fileCnt) {
        log.info("MsgServiceImpl getFileDetail start...");
        MessageFile fileDetail = msgDao.getFileDetail(msgNo, fileCnt);
        log.info("MsgServiceImpl getFileDetail fileDetail: " + fileDetail);
        return fileDetail;
	}
	
	// ======== 받은 쪽지함 검색기능 ========
	// 받은 쪽지함 검색기능
	// 검색 결과 개수
	@Override
	public int searchReceiveMsgCnt(Long msgReceiver, String keyword, String type) {
	    log.info("MsgServiceImpl searchReceiveMsgCnt start...");
	    int searchReceivedMsgCount = msgDao.searchReceiveMsgCnt(msgReceiver, keyword, type);
	    System.out.println("MsgServiceImpl searchReceivedMsgCount: " + searchReceivedMsgCount);
	    return searchReceivedMsgCount;
	}
	// 검색 받은쪽지중 읽지않은 쪽지개수 
	@Override
	public int searchTotUnreadReceiveMsgCnt(Long msgReceiver, String keyword, String type) {
		log.info("MsgServiceImpl searchTotUnreadReceiveMsgCnt start...");
		int searchTotUnreadReceiveMsgCnt = msgDao.searchTotUnreadReceiveMsgCnt(msgReceiver, keyword, type);
		log.info("MsgServiceImpl searchTotUnreadReceiveMsgCnt totMsgCnt->" + searchTotUnreadReceiveMsgCnt);
		return searchTotUnreadReceiveMsgCnt;
	}
	// 검색 결과 리스트
	@Override
	public List<Message> searchReceivedMessages(Long msgReceiver, String keyword, String type, int start, int end) {
	    log.info("MsgServiceImpl searchReceivedMessages start...");
	    List<Message> searchReceivedMsgList = msgDao.searchReceivedMessages(msgReceiver, keyword, type, start, end);
	    System.out.println("MsgServiceImpl searchReceivedMsgList.size(): " + searchReceivedMsgList.size());
	    return searchReceivedMsgList;
	}
	// ======== 보낸 쪽지함 검색기능 ========
	// 검색 결과 개수
	@Override
	public int searchSentMsgCnt(Long msgSender, String keyword, String type) {
	    log.info("MsgServiceImpl searchSentMsgCnt start...");
	    int searchSentMsgCount = msgDao.searchSentMsgCnt(msgSender, keyword, type);
	    System.out.println("MsgServiceImpl searchSentMsgCount: " + searchSentMsgCount);
	    return searchSentMsgCount;
	}
	// 검색 보낸쪽지중 읽지않은 쪽지개수 
	@Override
	public int searchTotUnreadSentMsgCnt(Long msgSender, String keyword, String type) {
		log.info("MsgServiceImpl searchTotUnreadSentMsgCnt start...");
		int searchTotUnreadSentMsgCnt = msgDao.searchTotUnreadSentMsgCnt(msgSender, keyword, type);
		log.info("MsgServiceImpl searchTotUnreadSentMsgCnt totMsgCnt->" + searchTotUnreadSentMsgCnt);
		return searchTotUnreadSentMsgCnt;
	}
	// 검색 결과 리스트
	@Override
	public List<Message> searchSentMessages(Long msgSender, String keyword, String type, int start, int end) {
	    log.info("MsgServiceImpl searchSentMessages start...");
	    List<Message> searchSentMsgList = msgDao.searchSentMessages(msgSender, keyword, type, start, end);
	    System.out.println("MsgServiceImpl searchSentMsgList.size(): " + searchSentMsgList.size());
	    return searchSentMsgList;
	}
	// ======== 쪽지 보관함 검색기능 ========
	// 검색 결과 개수
	@Override
	public int searchStoredMsgCnt(Long storeboxUserNo, String keyword, String type) {
	    log.info("MsgServiceImpl searchStoredMsgCnt start...");
	    int searchStoredMsgCount = msgDao.searchStoredMsgCnt(storeboxUserNo, keyword, type);
	    System.out.println("MsgServiceImpl searchStoredMsgCount: " + searchStoredMsgCount);
	    return searchStoredMsgCount;
	}
	// 검색 쪽지보관함중 읽지않은 쪽지개수 
	@Override
	public int searchTotUnreadStoredMsgCnt(Long storeboxUserNo, String keyword, String type) {
		log.info("MsgServiceImpl searchTotUnreadStoredMsgCnt start...");
		int searchTotUnreadStoredMsgCnt = msgDao.searchTotUnreadStoredMsgCnt(storeboxUserNo, keyword, type);
		log.info("MsgServiceImpl searchTotUnreadStoredMsgCnt totMsgCnt->" + searchTotUnreadStoredMsgCnt);
		return searchTotUnreadStoredMsgCnt;
	}
	// 검색 결과 리스트
	@Override
	public List<Message> searchStoredMessages(Long storeboxUserNo, String keyword, String type, int start, int end) {
	    log.info("MsgServiceImpl searchStoredMessages start...");
	    List<Message> searchStoredMsgList = msgDao.searchStoredMessages(storeboxUserNo, keyword, type, start, end);
	    System.out.println("MsgServiceImpl searchStoredMsgList.size(): " + searchStoredMsgList.size());
	    return searchStoredMsgList;
	}
	
	// ======== 휴지통 검색기능 ========
	// 검색 결과 개수
	@Override
	public int searchTrashMsgCnt(Long trashboxUserNo, String keyword, String type) {
	    log.info("MsgServiceImpl searchTrashMsgCnt start...");
	    int searchTrashMsgCount = msgDao.searchTrashMsgCnt(trashboxUserNo, keyword, type);
	    System.out.println("MsgServiceImpl searchTrashMsgCount: " + searchTrashMsgCount);
	    return searchTrashMsgCount;
	}
	// 검색 휴지통중 읽지않은 쪽지개수 
	@Override
	public int searchTotUnreadTrashMsgCnt(Long trashboxUserNo, String keyword, String type) {
		log.info("MsgServiceImpl searchTotUnreadTrashMsgCnt start...");
		int searchTotUnreadTrashMsgCnt = msgDao.searchTotUnreadTrashMsgCnt(trashboxUserNo, keyword, type);
		log.info("MsgServiceImpl searchTotUnreadTrashMsgCnt totMsgCnt->" + searchTotUnreadTrashMsgCnt);
		return searchTotUnreadTrashMsgCnt;
	}
	// 검색 결과 리스트
	@Override
	public List<Message> searchTrashMessages(Long trashboxUserNo, String keyword, String type, int start, int end) {
	    log.info("MsgServiceImpl searchTrashMessages start...");
	    List<Message> searchTrashMsgList = msgDao.searchTrashMessages(trashboxUserNo, keyword, type, start, end);
	    System.out.println("MsgServiceImpl searchTrashMsgList.size(): " + searchTrashMsgList.size());
	    return searchTrashMsgList;
	}
	
	// ======= 유저넘버로 닉네임 가져오기 닉네임 가져오기 =======
	// 워크스패이스 쪽지 아이콘 누르면 인풋창에 해당유저 아이디(히든인풋) 닉네임 박히기
	@Override
	public User findUserDetailsById(Long userNo) {
		User getUserNicUserId = msgDao.getUserNicUserId(userNo);
		return getUserNicUserId;
	}












	
	

	
}
