package com.blackberry.s20240130103.kdw.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.blackberry.s20240130103.kdw.model.Message;
import com.blackberry.s20240130103.kdw.model.MessageFile;
import com.blackberry.s20240130103.lhs.model.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Repository
@RequiredArgsConstructor
@Slf4j
public class MsgDaoImpl implements MsgDao {
	
	private final SqlSession session;
	
    // 전체 유저 리스트 (나중에 주소록가져와야함)
	@Override
	public List<User> getAllUsers() {
	    log.info("MsgDaoImpl getAllUsers start...");
	    try {
	        return session.selectList("kdwGetAllUsers");
	    } catch (Exception e) {
	        log.error("Error occurred while retrieving all users: {}", e.getMessage());
	        throw new RuntimeException("Failed to retrieve all users", e);
	    }
	}
	
	// =========== 각 쪽지함 개수 가져오기 ============
	// 받은 쪽지 개수
    @Override
    public int totReceiveMsgCnt(Long msgReceiver) {
        int totReceiveMsgCnt = 0;
        try {
            totReceiveMsgCnt = session.selectOne("totReceiveMsgCnt", msgReceiver);
            System.out.println("MsgServiceImpl totMsgCnt totReceiveMsgCnt->" + totReceiveMsgCnt);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("MsgServiceImpl totMsgCnt Exception ->" + e.getMessage());
        }
        return totReceiveMsgCnt;
    }
    // 받은 쪽지중 읽지 않은 쪽지 개수 
    @Override
    public int totUnreadReceiveMsgCnt(Long msgReceiver) {
        int totUnreadReceiveMsgCnt = 0;
        try {
        	totUnreadReceiveMsgCnt = session.selectOne("totUnreadReceiveMsgCnt", msgReceiver);
            System.out.println("MsgServiceImpl totMsgCnt totReceiveMsgCnt->" + totUnreadReceiveMsgCnt);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("MsgServiceImpl totMsgCnt Exception ->" + e.getMessage());
        }
        return totUnreadReceiveMsgCnt;
    }
    // 보낸 쪽지 개수
    @Override
    public int totSentMsgCnt(Long msgSender) {
        int totSentMsgCnt = 0;
        try {
            totSentMsgCnt = session.selectOne("totSentMsgCnt", msgSender);
            System.out.println("MsgServiceImpl totSentMsgCnt->" + totSentMsgCnt);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("MsgServiceImpl totSentMsgCnt Exception ->" + e.getMessage());
        }
        return totSentMsgCnt;
    }
    // 보낸 쪽지중 읽지 않은 쪽지 개수 
    @Override
    public int totUnreadSentMsgCnt(Long msgSender) {
        int totUnreadSentMsgCnt = 0;
        try {
        	totUnreadSentMsgCnt = session.selectOne("totUnreadSentMsgCnt", msgSender);
            System.out.println("MsgServiceImpl totMsgCnt totUnreadSentMsgCnt->" + totUnreadSentMsgCnt);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("MsgServiceImpl totMsgCnt Exception ->" + e.getMessage());
        }
        return totUnreadSentMsgCnt;
    }
    
    // 보관함 쪽지 개수
    @Override
    public int totStoredMsgCnt(Long storeboxUserNo) {
        int totStoredMsgCnt = 0;
        try {
            totStoredMsgCnt = session.selectOne("totStoredMsgCnt", storeboxUserNo);
            System.out.println("MsgServiceImpl totStoredMsgCnt->" + totStoredMsgCnt);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("MsgServiceImpl totStoredMsgCnt Exception ->" + e.getMessage());
        }
        return totStoredMsgCnt;
    }
    // 보관함 쪽지중 읽지 않은 쪽지 개수
    @Override
    public int totUnreadStoredMsgCnt(Long storeboxUserNo) {
        int totUnreadStoredMsgCnt = 0;
        try {
        	totUnreadStoredMsgCnt = session.selectOne("totUnreadStoredMsgCnt", storeboxUserNo);
            System.out.println("MsgServiceImpl totMsgCnt totUnreadStoredMsgCnt->" + totUnreadStoredMsgCnt);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("MsgServiceImpl totMsgCnt Exception ->" + e.getMessage());
        }
        return totUnreadStoredMsgCnt;
    }
    
    // 휴지통 쪽지 개수
    @Override
    public int totTrashMsgCnt(Long trashboxUserNo) {
        int totTrashMsgCnt = 0;
        try {
            totTrashMsgCnt = session.selectOne("totTrashMsgCnt", trashboxUserNo);
            System.out.println("MsgServiceImpl totTrashMsgCnt->" + totTrashMsgCnt);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("MsgServiceImpl totTrashMsgCnt Exception ->" + e.getMessage());
        }
        return totTrashMsgCnt;
    }
    // 휴지통 쪽지중 읽지 않은 쪽지 개수
    @Override
    public int totUnreadTrashMsgCnt(Long trashboxUserNo) {
        int totUnreadTrashMsgCnt = 0;
        try {
        	totUnreadTrashMsgCnt = session.selectOne("totUnreadTrashMsgCnt", trashboxUserNo);
            System.out.println("MsgServiceImpl totMsgCnt totUnreadTrashMsgCnt->" + totUnreadTrashMsgCnt);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("MsgServiceImpl totMsgCnt Exception ->" + e.getMessage());
        }
        return totUnreadTrashMsgCnt;
    }
    
	// =========== 각 쪽지함 리스트 가져오기 ============
	// 받은 쪽지 리스트 가져오기
	@Override
	public List<Message> getReceivedMessages(Long msgReceiver, int start, int end) {
	    List<Message> receivedMessages = null;
	    System.out.println("MsgDaoImpl getReceivedMessages start...");

	    try {
	        Map<String, Object> paramMap = new HashMap<>();
	        paramMap.put("msgReceiver", msgReceiver);
	        paramMap.put("start", start);
	        paramMap.put("end", end);

	        receivedMessages = session.selectList("kdwReceivedMessagesAll", paramMap);
	        System.out.println("MsgDaoImpl getReceivedMessages receivedMessages.size()->" + receivedMessages.size());
	        System.out.println("MsgDaoImpl getReceivedMessages start&end" + + start +", "+ end);
	    } catch (Exception e) {
	        e.printStackTrace(); // 에러 상세 내용 출력
	        System.out.println("MsgDaoImpl getReceivedMessages e.getMessage()->" + e.getMessage());
	    }

	    return receivedMessages;
	}
	
	// 보낸 쪽지 리스트 가져오기
	@Override
	public List<Message> getSentMessages(Long msgSender, int start, int end) {
	    List<Message> sentMessages = null;
	    System.out.println("MsgDaoImpl getSentMessages start...");

	    try {
	        Map<String, Object> paramMap = new HashMap<>();
	        paramMap.put("msgSender", msgSender);
	        paramMap.put("start", start);
	        paramMap.put("end", end);

	        sentMessages = session.selectList("kdwSentMessagesAll", paramMap);
	        System.out.println("MsgDaoImpl getSentMessages SentMessages.size()->" + sentMessages.size());
	        System.out.println("MsgDaoImpl getSentMessages start&end" + start +", "+ end);
	    } catch (Exception e) {
	        e.printStackTrace(); // 에러 상세 내용 출력
	        System.out.println("MsgDaoImpl getSentMessages e.getMessage()->" + e.getMessage());
	    }

	    return sentMessages;
	}
	
	// 보관함 리스트 가져오기
    @Override
    public List<Message> getStoredMessages(Long storeboxUserNo, int start, int end) {
	    List<Message> storedMessages = null;
	    System.out.println("MsgDaoImpl getStoredMessages start...");

	    try {
	        Map<String, Object> paramMap = new HashMap<>();
	        paramMap.put("storeboxUserNo", storeboxUserNo);
	        paramMap.put("start", start);
	        paramMap.put("end", end);

	        storedMessages = session.selectList("kdwStoredMessagesAll", paramMap);
	        System.out.println("MsgDaoImpl getSentMessages storedMessages.size()->" + storedMessages.size());
	        System.out.println("MsgDaoImpl getSentMessages start&end" + start +", "+ end);
	    } catch (Exception e) {
	        e.printStackTrace(); // 에러 상세 내용 출력
	        System.out.println("MsgDaoImpl getSentMessages e.getMessage()->" + e.getMessage());
	    }
	    return storedMessages;
    }
    
	// 휴지통 리스트 가져오기
	@Override
	public List<Message> getTrashMessages(Long trashboxUserNo, int start, int end) {
	    List<Message> trashMessages = null;
	    System.out.println("MsgDaoImpl getTrashMessages start...");

	    try {
	        Map<String, Object> paramMap = new HashMap<>();
	        paramMap.put("trashboxUserNo", trashboxUserNo);
	        paramMap.put("start", start);
	        paramMap.put("end", end);

	        trashMessages = session.selectList("kdwTrashMessagesAll", paramMap);
	        System.out.println("MsgDaoImpl getTrashMessages trashMessages.size()->" + trashMessages.size());
	        System.out.println("MsgDaoImpl getTrashMessages start&end" + start +", "+ end);
	    } catch (Exception e) {
	        e.printStackTrace(); // 에러 상세 내용 출력
	        System.out.println("MsgDaoImpl getTrashMessages e.getMessage()->" + e.getMessage());
	    }
	    return trashMessages;
	}
    
	
	// =========== 쪽지 읽기 ============
	// 받은 & 보낸 쪽지 정보 가져오기
	@Override
	public Message getMessageByInfo(Long msgNo) {
	    System.out.println("MsgDaoImpl getReceivedMessageById start...");
	    Message messageInfo = null;

	    try {
	        // 메시지 가져오기
	        messageInfo = session.selectOne("kdwGetMessageById", msgNo);
	        System.out.println("MsgDaoImpl getReceivedMessageById messageInfo->" + messageInfo);
	        System.out.println("MsgDaoImpl getReceivedMessageById msgNo->" + msgNo);
	    } catch (Exception e) {
	        e.printStackTrace(); // 에러 상세 내용 출력
	        System.out.println("MsgDaoImpl getReceivedMessageById e.getMessage()->" + e.getMessage());
	    }
	    return messageInfo;
	}
	// 받은쪽지 read_date 업데이트 메소드
	public void updateReadDate(Long msgNo) {
	    try {
	        // 메시지를 읽은 시간 업데이트
	        session.update("kdwUpdateReadDate", msgNo);
	        System.out.println("MsgDaoImpl updateReadDate success msgNo" + msgNo);
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("MsgDaoImpl updateReadDate Exception ->" + e.getMessage());
	    }
	}
    
    // 보관 버튼 클릭시 msg_store_chk = 1업데이트
    @Override
    public void updateMsgStoreStatus(List<Long> msgNos) {
    	System.out.println("MsgDaoImpl updateMsgStoreStatus start...");
    	try {
			session.update("kdwUpdateMsgStoreStatus", msgNos);
			System.out.println("MsgDaoImpl updateMsgStoreStatus success msgNos" + msgNos);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    // 삭제 버튼 클릭시 msg_delete_chk = 1 업데이트
	@Override
	public void updateMsgDeleteStatus(List<Long> msgNos) {
    	System.out.println("MsgDaoImpl updateMsgDeleteStatus start...");
    	try {
			session.update("kdwUpdateMsgDeleteStatus", msgNos);
			System.out.println("MsgDaoImpl updateMsgDeleteStatus success msgNos" + msgNos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 쪽지 영구 삭제 구현
	@Override
	public void permanentDeleteMessages(List<Long> msgNos) {
	    System.out.println("MsgDaoImpl permanentDeleteMessages start...");
	    try {
	        session.delete("kdwPermanentDeleteMessages", msgNos);
	        System.out.println("MsgDaoImpl permanentDeleteMessages success msgNos" + msgNos);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	// 첨부파일 쪽지 삭제 
	@Override
	public void deleteMessageFilesByMsgNo(Long msgNo) {
	    log.info("Deleting message files for msgNo: {}", msgNo);
	    try {
	        session.delete("kdwDeleteMessageFilesByMsgNo", msgNo);
	        System.out.println("MsgDaoImpl deleteMessageFilesByMsgNo success msgNo" + msgNo);
	    } catch (Exception e) {
	        log.error("Error occurred while deleting message files for msgNo: {}", msgNo, e);
	        e.printStackTrace();
	    }
	}

	// 쪽지 보내기
    @Override
    public void sendMsg(Message message) {
    	System.out.println("MsgDaoImpl sendMsg start...");
        try {
			session.insert("kdwSentMsg", message);
			System.out.println("MsgDaoImpl sendMsg success msgNos" + message);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    // 파일 저장
    @Override
    public void saveMessageFile(MessageFile messageFile) {
        try {
            session.insert("kdwSaveMessageFile", messageFile);
            System.out.println("MsgDaoImpl saveMessageFile success: " + messageFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 쪽지에 첨부된 모든 파일 정보 가져오기
    @Override
    public List<MessageFile> getMessageFiles(Long msgNo) {
        log.info("MsgDaoImpl getMessageFiles start...");
        List<MessageFile> files = null;
        try {
            files = session.selectList("kdwGetMessagesWithFiles", msgNo);
        } catch (Exception e) {
            log.error("Error getting messages with files: {}", e.getMessage());
            e.printStackTrace();
        }
        return files;
    }
	// 첨부 파일 상세 정보
	@Override
	public MessageFile getFileDetail(Long msgNo, int fileCnt) {
	    log.info("MsgDaoImpl getFileDetail start...");
	    MessageFile getFileDetail = null;
	    try {
	        Map<String, Object> params = new HashMap<>();
	        params.put("msgNo", msgNo);
	        params.put("fileCnt", fileCnt);
	        getFileDetail = session.selectOne("kdwGetFileDetail", params);
	        log.info("MsgDaoImpl getFileDetail success: {}", getFileDetail);
	    } catch (Exception e) {
	        log.error("Error getting file detail: {}", e.getMessage(), e);
	    }
	    return getFileDetail;
	}
	
    // ========== 검색 기능 구현 ==========
    // ======= 받은 쪽지함 검색기능 ======
    // 검색된 쪽지 개수
    @Override
    public int searchReceiveMsgCnt(Long msgReceiver, String keyword, String type) {
        log.info("MsgDaoImpl searchReceiveMsgCnt start...");
        int searchResultCount = 0;
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("msgReceiver", msgReceiver);
            paramMap.put("keyword", keyword);
            paramMap.put("type", type);

            searchResultCount = session.selectOne("kdwSearchReceiveMsgCnt", paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error occurred while searching received messages count: {}", e.getMessage());
        }
        return searchResultCount;
    }
    // 검색된 읽지않은 쪽지개수
	@Override
	public int searchTotUnreadReceiveMsgCnt(Long msgReceiver, String keyword, String type) {
        log.info("MsgDaoImpl searchTotUnreadReceiveMsgCnt start...");
        int searchUnreadResultCount = 0;
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("msgReceiver", msgReceiver);
            paramMap.put("keyword", keyword);
            paramMap.put("type", type);

            searchUnreadResultCount = session.selectOne("kdwSearchUnreadReceiveMsgCnt", paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error occurred while searching received messages count: {}", e.getMessage());
        }
        return searchUnreadResultCount;
	}

    // 검색 리스트 가져오기
    @Override
    public List<Message> searchReceivedMessages(Long msgReceiver, String keyword, String type, int start, int end) {
        log.info("MsgDaoImpl searchReceivedMessages start...");
        List<Message> searchResultMessages = null;
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("msgReceiver", msgReceiver);
            paramMap.put("keyword", keyword);
            paramMap.put("type", type);
            paramMap.put("start", start);
            paramMap.put("end", end);

            searchResultMessages = session.selectList("kdwSearchReceivedMessages", paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error occurred while searching received messages: {}", e.getMessage());
        }
        return searchResultMessages;
    }
    // ======= 보낸 쪽지함 검색기능 ======
    // 검색된 쪽지 개수
	@Override
	public int searchSentMsgCnt(Long msgSender, String keyword, String type) {
        log.info("MsgDaoImpl searchSentMsgCnt start...");
        int searchResultCount = 0;
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("msgSender", msgSender);
            paramMap.put("keyword", keyword);
            paramMap.put("type", type);

            searchResultCount = session.selectOne("kdwSearchSentMsgCnt", paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error occurred while searching sent messages count: {}", e.getMessage());
        }
        return searchResultCount;
	}
	// 검색된 읽지않은 쪽지개수
	@Override
	public int searchTotUnreadSentMsgCnt(Long msgSender, String keyword, String type) {
        log.info("MsgDaoImpl searchTotUnreadSentMsgCnt start...");
        int searchUnreadResultCount = 0;
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("msgSender", msgSender);
            paramMap.put("keyword", keyword);
            paramMap.put("type", type);

            searchUnreadResultCount = session.selectOne("kdwSearchUnreadSentMsgCnt", paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error occurred while searching received messages count: {}", e.getMessage());
        }
        return searchUnreadResultCount;
	}
	// 검색 리스트 가져오기
	@Override
	public List<Message> searchSentMessages(Long msgSender, String keyword, String type, int start, int end) {
        log.info("MsgDaoImpl searchSentMessages start...");
        List<Message> searchResultMessages = null;
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("msgSender", msgSender);
            paramMap.put("keyword", keyword);
            paramMap.put("type", type);
            paramMap.put("start", start);
            paramMap.put("end", end);

            searchResultMessages = session.selectList("kdwSearchSentMessages", paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error occurred while searching sent messages: {}", e.getMessage());
        }
        return searchResultMessages;
	}
    // ======= 쪽지 보관함 검색기능 ======
    // 검색된 쪽지 개수
	@Override
	public int searchStoredMsgCnt(Long storeboxUserNo, String keyword, String type) {
        log.info("MsgDaoImpl searchStoredMsgCnt start...");
        int searchResultCount = 0;
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("storeboxUserNo", storeboxUserNo);
            paramMap.put("keyword", keyword);
            paramMap.put("type", type);

            searchResultCount = session.selectOne("kdwSearchStoredMsgCnt", paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error occurred while searching Stored messages count: {}", e.getMessage());
        }
        return searchResultCount;
	}
	// 검색된 읽지않은 쪽지개수
	@Override
	public int searchTotUnreadStoredMsgCnt(Long storeboxUserNo, String keyword, String type) {
        log.info("MsgDaoImpl searchTotUnreadStoredMsgCnt start...");
        int searchUnreadResultCount = 0;
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("storeboxUserNo", storeboxUserNo);
            paramMap.put("keyword", keyword);
            paramMap.put("type", type);

            searchUnreadResultCount = session.selectOne("kdwSearchUnreadStoredMsgCnt", paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error occurred while searching Stored messages count: {}", e.getMessage());
        }
        return searchUnreadResultCount;
	}

	// 검색 리스트 가져오기
	@Override
	public List<Message> searchStoredMessages(Long storeboxUserNo, String keyword, String type, int start, int end) {
        log.info("MsgDaoImpl searchStoredMessages start...");
        List<Message> searchResultMessages = null;
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("storeboxUserNo", storeboxUserNo);
            paramMap.put("keyword", keyword);
            paramMap.put("type", type);
            paramMap.put("start", start);
            paramMap.put("end", end);

            searchResultMessages = session.selectList("kdwSearchStoredMessages", paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error occurred while searching Stored messages: {}", e.getMessage());
        }
        return searchResultMessages;
	}
    // ======= 휴지통 검색기능 ======
    // 검색된 쪽지 개수
	@Override
	public int searchTrashMsgCnt(Long trashboxUserNo, String keyword, String type) {
        log.info("MsgDaoImpl searchTrashMsgCnt start...");
        int searchResultCount = 0;
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("trashboxUserNo", trashboxUserNo);
            paramMap.put("keyword", keyword);
            paramMap.put("type", type);

            searchResultCount = session.selectOne("kdwSearchTrashMsgCnt", paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error occurred while searching Trash messages count: {}", e.getMessage());
        }
        return searchResultCount;
	}
	// 검색된 읽지않은 쪽지개수
	@Override
	public int searchTotUnreadTrashMsgCnt(Long trashboxUserNo, String keyword, String type) {
        log.info("MsgDaoImpl searchTotUnreadTrashMsgCnt start...");
        int searchUnreadResultCount = 0;
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("trashboxUserNo", trashboxUserNo);
            paramMap.put("keyword", keyword);
            paramMap.put("type", type);

            searchUnreadResultCount = session.selectOne("kdwSearchUnreadTrashMsgCnt", paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error occurred while searching Trash messages count: {}", e.getMessage());
        }
        return searchUnreadResultCount;
	}
	// 검색 리스트 가져오기
	@Override
	public List<Message> searchTrashMessages(Long trashboxUserNo, String keyword, String type, int start, int end) {
        log.info("MsgDaoImpl searchTrashMessages start...");
        List<Message> searchResultMessages = null;
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("trashboxUserNo", trashboxUserNo);
            paramMap.put("keyword", keyword);
            paramMap.put("type", type);
            paramMap.put("start", start);
            paramMap.put("end", end);

            searchResultMessages = session.selectList("kdwSearchTrashMessages", paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error occurred while searching Trash messages: {}", e.getMessage());
        }
        return searchResultMessages;
	}
	
	
	// 유저넘버로 닉네임+id 가져오기
	@Override
	public User getUserNicUserId(Long receiverId) {
	    log.info("MsgDaoImpl getUserNicUserId start...");
	    User getUserNicUserId = null;
	    try {
	    	getUserNicUserId = session.selectOne("kdwGetUserNicUserId", receiverId);
	    	log.info("MsgDaoImpl getUserNicUserId getUserNicUserId : " + getUserNicUserId);
	    } catch (Exception e) {
	        log.error("Error occurred while retrieving user nic and user ID for receiver ID: {}", receiverId, e);
	        throw new RuntimeException("Unable to retrieve user details for receiver ID: " + receiverId, e);
	    }
	    return getUserNicUserId;
	}
















	
	







}
