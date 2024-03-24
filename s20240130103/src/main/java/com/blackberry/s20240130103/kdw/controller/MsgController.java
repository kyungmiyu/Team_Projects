package com.blackberry.s20240130103.kdw.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.blackberry.s20240130103.kdw.model.Message;
import com.blackberry.s20240130103.kdw.model.MessageFile;
import com.blackberry.s20240130103.kdw.service.MsgPaging;
import com.blackberry.s20240130103.kdw.service.MsgService;
import com.blackberry.s20240130103.kph.model.KphUsers;
import com.blackberry.s20240130103.kph.service.KphProjectService;
import com.blackberry.s20240130103.lhs.model.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequiredArgsConstructor
@Slf4j
public class MsgController {
	
	private final MsgService msgService;
	private final KphProjectService kphProjectService;

	/* ========== 쪽지함 ========== */
	// 받은 쪽지함 리스트
	@GetMapping(value = "msgReceivebox")
	public String getReceivedMessages(HttpServletRequest request, Model model,
	        @RequestParam(name = "currentPage", defaultValue = "1") String currentPage,
	        @RequestParam(name = "keyword", required = false) String keyword,
	        @RequestParam(name = "type", defaultValue = "all") String type)  {
	    log.info("MsgController getReceivedMessages start...");
	    
	    System.out.println("Received keyword: {}"+ keyword);
	    System.out.println("Received type: {}"+ type);
	    
	    // HttpSession에서 로그인한 사용자 정보 가져오기 (쪽지 받는 사람)
	    Long msgReceiver = (Long) request.getSession().getAttribute("user_no");

	    // 받은 쪽지 수 가져오기
	    int totReceiveMsgCnt = 0;
	    int totUnreadReceiveMsgCnt = 0;
	    // 검색 결과를 가져오는 로직 추가
	    if (keyword != null && !keyword.isEmpty()) {
	        totReceiveMsgCnt = msgService.searchReceiveMsgCnt(msgReceiver, keyword, type);
	    } else {
	        totReceiveMsgCnt = msgService.totReceiveMsgCnt(msgReceiver);
	    }
	    // 안 읽은 쪽지 수 가져오기 & 검색 시 안 읽은 쪽지 수
	    if (keyword != null && !keyword.isEmpty()) {
	    	totUnreadReceiveMsgCnt = msgService.searchTotUnreadReceiveMsgCnt(msgReceiver, keyword, type);
	    } else {
	    	totUnreadReceiveMsgCnt = msgService.totUnreadReceiveMsgCnt(msgReceiver);
	    }
	    
	    
	    // 페이징 처리
	    MsgPaging page = new MsgPaging(totReceiveMsgCnt, currentPage);

	    // 로그인한 사용자 정보를 기반으로 받은 쪽지 목록 가져오기 (검색 결과 또는 전체 결과, 페이징 정보 전달)
	    List<Message> receivedMessages;
	    if (keyword != null && !keyword.isEmpty()) {
	        receivedMessages = msgService.searchReceivedMessages(msgReceiver, keyword, type, page.getStart(), page.getEnd());
	    } else {
	        receivedMessages = msgService.getReceivedMessages(msgReceiver, page.getStart(), page.getEnd());
	    }
	    // 전체삭제하고 페이지가 비었을때
	    if(receivedMessages.isEmpty()) {
	    	MsgPaging page2 = new MsgPaging(totReceiveMsgCnt,String.valueOf(Integer.parseInt(currentPage)-1) );
	    	if (keyword != null && !keyword.isEmpty()) {
		        receivedMessages = msgService.searchReceivedMessages(msgReceiver, keyword, type, page2.getStart(), page2.getEnd());
		    } else {
		        receivedMessages = msgService.getReceivedMessages(msgReceiver, page2.getStart(), page2.getEnd());
		    }
	    }
	    
	    System.out.println("MsgController getReceivedMessages 전체쪽지" + totReceiveMsgCnt);
	    System.out.println("MsgController getReceivedMessages 읽지않은쪽지" + totUnreadReceiveMsgCnt);
	    // 'Model'에 받은 쪽지 목록과 페이징 정보를 담아서 전달
	    model.addAttribute("msgReceiver", msgReceiver);
	    model.addAttribute("totReceiveMsgCnt", totReceiveMsgCnt);
	    model.addAttribute("totUnreadReceiveMsgCnt", totUnreadReceiveMsgCnt);
	    model.addAttribute("receivedMessages", receivedMessages);
	    model.addAttribute("page", page);
	    model.addAttribute("keyword", keyword);
	    model.addAttribute("type", type);

	    return "kdw/msgReceivebox";
	}
	
	/* ========== 보낸 쪽지함 리스트 불러오기 ========== */
	@GetMapping(value = "msgSendbox")
	public String getSendMessages(HttpServletRequest request, Model model,
	        @RequestParam(name = "currentPage", defaultValue = "1") String currentPage,
	        @RequestParam(name = "keyword", required = false) String keyword,
	        @RequestParam(name = "type", defaultValue = "all") String type) {
	    log.info("MsgController getSendMessages start...");
	    
	    System.out.println("Received keyword: {}"+ keyword);
	    System.out.println("Received type: {}"+ type);
	    
	    // HttpSession에서 로그인한 사용자 정보 가져오기 (쪽지 받는 사람)
	    Long msgSender = (Long) request.getSession().getAttribute("user_no");

	    // 보낸 쪽지 수 가져오기 (totMsgCnt 메서드를 이용)
	    int totSentMsgCnt = 0;
	    int totUnreadSentMsgCnt = 0;
	    
	    // 검색 결과를 가져오는 로직 추가
	    if (keyword != null && !keyword.isEmpty()) {
	    	totSentMsgCnt = msgService.searchSentMsgCnt(msgSender, keyword, type);
	    } else {
	    	totSentMsgCnt = msgService.totSentMsgCnt(msgSender);
	    }
	    if (keyword != null && !keyword.isEmpty()) {
	    	totUnreadSentMsgCnt = msgService.searchTotUnreadSentMsgCnt(msgSender, keyword, type);
	    } else {
	    	totUnreadSentMsgCnt = msgService.totUnreadSentMsgCnt(msgSender);
	    }
	    
	    
	    // 페이징 처리
	    MsgPaging page = new MsgPaging(totSentMsgCnt, currentPage);

	    // 로그인한 사용자 정보를 기반으로 보낸 쪽지 목록 가져오기 (getSentMessages 메서드 사용)
	    List<Message> sentMessages;
	    
	    if (keyword != null && !keyword.isEmpty()) {
	    	sentMessages = msgService.searchSentMessages(msgSender, keyword, type, page.getStart(), page.getEnd());
	    } else {
	    	sentMessages = msgService.getSentMessages(msgSender, page.getStart(), page.getEnd());
	    }
	    
	    
	    // 전체삭제하고 페이지가 비었을때
	    if(sentMessages.isEmpty()) {
	    	MsgPaging page2 = new MsgPaging(totSentMsgCnt,String.valueOf(Integer.parseInt(currentPage)-1) );
	    	if (keyword != null && !keyword.isEmpty()) {
	    		sentMessages = msgService.searchSentMessages(msgSender, keyword, type, page2.getStart(), page2.getEnd());
		    } else {
		    	sentMessages = msgService.getSentMessages(msgSender, page2.getStart(), page2.getEnd());
		    }
	    }
	    System.out.println("MsgController getSendMessages 전체쪽지" + totSentMsgCnt);
	    System.out.println("MsgController getSendMessages 읽지않은쪽지" + totUnreadSentMsgCnt);
	    
	    // 'Model'에 보낸 쪽지 목록과 페이징 정보를 담아서 전달
	    model.addAttribute("msgSender", msgSender);
	    model.addAttribute("totSentMsgCnt", totSentMsgCnt);
	    model.addAttribute("totUnreadSentMsgCnt", totUnreadSentMsgCnt);
	    model.addAttribute("sentMessages", sentMessages);
	    model.addAttribute("page", page);
	    model.addAttribute("keyword", keyword);
	    model.addAttribute("type", type);

	    return "kdw/msgSendbox";
	}
	
	// 쪽지 보관함 리스트 가져오기
	@GetMapping(value = "msgStorebox")
	public String getStoreboxMessages(HttpServletRequest request, Model model,
	        @RequestParam(name = "currentPage", defaultValue = "1") String currentPage,
	        @RequestParam(name = "keyword", required = false) String keyword,
	        @RequestParam(name = "type", defaultValue = "all") String type) {
	    log.info("MsgController getStoreboxMessages start...");
	    
	    System.out.println("Received keyword: {}"+ keyword);
	    System.out.println("Received type: {}"+ type);
	    
	    // HttpSession에서 로그인한 사용자 정보 가져오기 (전체 쪽지)
	    Long storeboxUserNo = (Long) request.getSession().getAttribute("user_no");
	    
	    // msg_store_chk 값이 1인 쪽지들만 가져오기
	    int totStoredMsgCnt = 0;
	    int totUnreadStoredMsgCnt = 0;
	    
	    // 검색 결과를 가져오는 로직 추가
	    if (keyword != null && !keyword.isEmpty()) {
	    	totStoredMsgCnt = msgService.searchStoredMsgCnt(storeboxUserNo, keyword, type);
	    } else {
	    	totStoredMsgCnt = msgService.totStoredMsgCnt(storeboxUserNo);
	    }
	    if (keyword != null && !keyword.isEmpty()) {
	    	totUnreadStoredMsgCnt = msgService.searchTotUnreadStoredMsgCnt(storeboxUserNo, keyword, type);
	    } else {
	    	totUnreadStoredMsgCnt = msgService.totUnreadStoredMsgCnt(storeboxUserNo);
	    }
	    
	    
	    // 페이징 처리
	    MsgPaging page = new MsgPaging(totStoredMsgCnt, currentPage);
	    // 로그인한 사용자 정보를 기반으로 보낸 쪽지 목록 가져오기
	    List<Message> storedMessages;
	    
	    if (keyword != null && !keyword.isEmpty()) {
	    	storedMessages = msgService.searchStoredMessages(storeboxUserNo, keyword, type, page.getStart(), page.getEnd());
	    } else {
	    	storedMessages = msgService.getStoredMessages(storeboxUserNo, page.getStart(), page.getEnd());
	    }
	    
	    // 전체삭제하고 페이지가 비었을때
	    if(storedMessages.isEmpty()) {
	    	MsgPaging page2 = new MsgPaging(totStoredMsgCnt,String.valueOf(Integer.parseInt(currentPage)-1) );
	    	if (keyword != null && !keyword.isEmpty()) {
	    		storedMessages = msgService.searchStoredMessages(storeboxUserNo, keyword, type, page2.getStart(), page2.getEnd());
		    } else {
		    	storedMessages = msgService.getStoredMessages(storeboxUserNo, page2.getStart(), page2.getEnd());
		    }
	    }
	    
	    // 'Model'에 보관된 쪽지 목록과 페이징 정보를 담아서 전달
	    model.addAttribute("totStoredMsgCnt", totStoredMsgCnt);
	    model.addAttribute("totUnreadStoredMsgCnt", totUnreadStoredMsgCnt);
	    model.addAttribute("storedMessages", storedMessages);
	    model.addAttribute("page", page);
	    model.addAttribute("storeboxUserNo", storeboxUserNo);
	    model.addAttribute("keyword", keyword);
	    model.addAttribute("type", type);

	    return "kdw/msgStorebox";
	}
	
    // 휴지통 리스트 가져오기
    @GetMapping(value = "msgTrashbox")
    public String getTrashboxMessages(HttpServletRequest request, Model model,
	        @RequestParam(name = "currentPage", defaultValue = "1") String currentPage,
	        @RequestParam(name = "keyword", required = false) String keyword,
	        @RequestParam(name = "type", defaultValue = "all") String type) {
        log.info("MsgController getTrashboxMessages start...");
        
	    System.out.println("Received keyword: {}"+ keyword);
	    System.out.println("Received type: {}"+ type);
        
        // HttpSession에서 로그인한 사용자 정보 가져오기 (휴지통 쪽지)
        Long trashboxUserNo = (Long) request.getSession().getAttribute("user_no");

        // msg_trash_chk 값이 1인 쪽지들만 가져오기
        int totTrashMsgCnt = 0;
        int totUnreadTrashMsgCnt = 0;
        
	    // 검색 결과를 가져오는 로직 추가
	    if (keyword != null && !keyword.isEmpty()) {
	    	totTrashMsgCnt = msgService.searchTrashMsgCnt(trashboxUserNo, keyword, type);
	    } else {
	    	totTrashMsgCnt = msgService.totTrashMsgCnt(trashboxUserNo);
	    }
	    // 읽지않은 쪽지개수
	    if (keyword != null && !keyword.isEmpty()) {
	    	totUnreadTrashMsgCnt = msgService.searchTotUnreadTrashMsgCnt(trashboxUserNo, keyword, type);
	    } else {
	    	totUnreadTrashMsgCnt = msgService.totUnreadTrashMsgCnt(trashboxUserNo);
	    }
	    
	    
        // 페이징 처리
        MsgPaging page = new MsgPaging(totTrashMsgCnt, currentPage);
        
        // 로그인한 사용자 정보를 기반으로 휴지통 쪽지 목록 가져오기
        List<Message> trashMessages;
        
	    if (keyword != null && !keyword.isEmpty()) {
	    	trashMessages = msgService.searchTrashMessages(trashboxUserNo, keyword, type, page.getStart(), page.getEnd());
	    } else {
	    	trashMessages = msgService.getTrashMessages(trashboxUserNo, page.getStart(), page.getEnd());
	    }
	    
	    // 전체삭제하고 페이지가 비었을때
	    if(trashMessages.isEmpty()) {
	    	MsgPaging page2 = new MsgPaging(totTrashMsgCnt,String.valueOf(Integer.parseInt(currentPage)-1) );
	    	if (keyword != null && !keyword.isEmpty()) {
	    		trashMessages = msgService.searchTrashMessages(trashboxUserNo, keyword, type, page2.getStart(), page2.getEnd());
		    } else {
		    	trashMessages = msgService.getTrashMessages(trashboxUserNo, page2.getStart(), page2.getEnd());
		    }
	    }
        
        // 'Model'에 보관된 휴지통 쪽지 목록과 페이징 정보를 담아서 전달
        model.addAttribute("totTrashMsgCnt", totTrashMsgCnt);
        model.addAttribute("totUnreadTrashMsgCnt", totUnreadTrashMsgCnt);
        model.addAttribute("trashMessages", trashMessages);
        model.addAttribute("page", page);
        model.addAttribute("trashboxUserNo", trashboxUserNo);
	    model.addAttribute("keyword", keyword);
	    model.addAttribute("type", type);

        return "kdw/msgTrashbox";
    }
    
    // ========== 받은 쪽지 읽기 & 보낸 쪽지 읽기  ========== 
    
    // 받은 쪽지 읽기 및 첨부 파일 정보 불러오기
    @GetMapping(value = "msgReadReceived")
    public String readReceivedMessageInfo(@RequestParam("msg_no") Long msgNo, HttpServletRequest request, Model model) {
        log.info("MsgController readReceivedMessage start...");
        
        // 현재 로그인한 사용자
        Long userNo = (Long) request.getSession().getAttribute("user_no");
        
        // msgNo를 사용하여 해당 쪽지 정보 및 첨부 파일 정보 가져오기
        Message receivedMessageInfo = msgService.getReceivedMessageByInfo(msgNo);
        
        // 쪽지가 존재하지 않는 경우 에러 페이지로
        if (receivedMessageInfo == null) {
            return "redirect:/errorPage";
        }
        
        // 현재 로그인한 사용자가 쪽지의 수신자가 아니라면 에러 페이지로
        if (!receivedMessageInfo.getMsg_receiver().equals(userNo)) {
            return "redirect:/errorPage";
        }
        
        // 파일첨부가된 쪽지 리스트 = (다운로드 기능)
        List<MessageFile> fileMsgs = msgService.getMessageFiles(msgNo);
        // 보낸사람,받는사람 번호로 닉네임, 아이디 가져오기
        Long senderNo = receivedMessageInfo.getMsg_sender();
        Long receiverNo =  receivedMessageInfo.getMsg_receiver();
        User senderUser = msgService.findUserDetailsById(senderNo);
        User receiverUser = msgService.findUserDetailsById(receiverNo);
        
        // 보낸사람 닉네임,아이디 정보
        model.addAttribute("senderUser", senderUser);
        // 받은사람 닉네임,아이디 정보;
        model.addAttribute("receiverUser", receiverUser);
        // 쪽지에 대한 정보
        model.addAttribute("receivedMessageInfo", receivedMessageInfo);
        model.addAttribute("fileMsgs", fileMsgs); // 첨부 파일 정보 모델에 추가

        log.info("MsgController readReceivedMessage receivedMessageInfo => {}", receivedMessageInfo);
        log.info("MsgController readReceivedMessage fileMsgs.size() => {}", fileMsgs.size());
        return "kdw/msgReadReceived";
    }
    
    // 보낸쪽지 읽기('msg_readdate'가 'null'이면 'msg_readdate'업데이트)
    @GetMapping(value = "msgReadSent")
    public String readSentMessageInfo(@RequestParam("msg_no") Long msgNo, HttpServletRequest request, Model model) {
        log.info("MsgController readSentMessage start...");
        
        
        // 현재 로그인한 사용자
        Long userNo = (Long) request.getSession().getAttribute("user_no");
        // msgNo를 사용하여 해당 쪽지 정보 가져오기 및 읽은 시간 업데이트
        Message sentMessageInfo = msgService.getSentMessageByInfo(msgNo);
        
        // 쪽지가 존재하지 않는 경우
        if (sentMessageInfo == null) {
            return "redirect:/errorPage";
        }
        
        // 현재 로그인한 사용자가 쪽지의 수신자가 아니라면 에러 페이지로 리다이렉트
        if (!sentMessageInfo.getMsg_sender().equals(userNo)) {
            return "redirect:/errorPage";
        }
        // 파일첨부가된 쪽지 리스트 = (다운로드 기능)
        List<MessageFile> fileMsgs = msgService.getMessageFiles(msgNo); 
        
        // 보낸사람,받는사람 번호로 닉네임, 아이디 가져오기
        Long senderNo = sentMessageInfo.getMsg_sender();
        Long receiverNo =  sentMessageInfo.getMsg_receiver();
        User senderUser = msgService.findUserDetailsById(senderNo);
        User receiverUser = msgService.findUserDetailsById(receiverNo);
        
        // 보낸사람 닉네임,아이디 정보
        model.addAttribute("senderUser", senderUser);
        // 받은사람 닉네임,아이디 정보;
        model.addAttribute("receiverUser", receiverUser);
        
        model.addAttribute("sentMessageInfo", sentMessageInfo);
        model.addAttribute("fileMsgs", fileMsgs); // 첨부 파일 정보 모델에 추가
        log.info("MsgController readSentMessage sentMessageInfo => " + sentMessageInfo);
        log.info("MsgController readReceivedMessage fileMsgs.size() => {}", fileMsgs.size());
        // 보낸쪽지 읽기 페이지로 이동
        return "kdw/msgReadSent";
    }
    
    
    /* ========== 다운로드 기능 =========== */
	// 파일 상세정보 조회와 다운로드
	@GetMapping("/downloadFile")
	public void downloadFile(@RequestParam("msgNo") Long msgNo, @RequestParam("fileCnt") int fileCnt, 
											HttpServletRequest request, HttpServletResponse response) {
	    try {
	        MessageFile messageFile = msgService.getFileDetail(msgNo, fileCnt);
	     // 파일 정보가 존재하는지 확인합니다.
	        if (messageFile != null) {
	            // 파일이 저장된 실제 경로를 설정합니다. 웹 애플리케이션의 /upload/msgFile/ 디렉토리 아래에 위치합니다.
	            // 이 경로는 애플리케이션 서버에 따라 다를 수 있으므로, 동적으로 경로를 구합니다.
	            String filePath = request.getSession().getServletContext().getRealPath("/upload/msgFile/") + messageFile.getMsg_file_name();
	            File file = new File(filePath);

	            // 파일이 실제로 존재하는지 확인합니다.
	            if (file.exists()) {
	                // 파일의 MIME 타입을 추정 예를 들어 ".pdf" 확장자를 가진 파일의 경우 "application/pdf"가 됨
	                // 파일 이름을 바탕으로 MIME 타입을 결정하지 못할 경우 "application/octet-stream"을 사용
	                String mimeType = URLConnection.guessContentTypeFromName(file.getName());
	                if (mimeType == null) {
	                    mimeType = "application/octet-stream";
	                }
	                
	                // HTTP 응답의 Content-Type 헤더를 설정 이는 브라우저에게 파일의 타입을 알려주어 올바르게 처리
	                response.setContentType(mimeType);
	                // 파일을 다운로드할 때 사용자에게 보여줄 파일 이름을 설정. 파일 이름이 URL 인코딩을 사용하여 올바르게 전송
	                // Content-Disposition은 HTTP 헤더의 하나로 주로 웹 서버가 클라이언트(브라우저 등)에게 응답의 본문이 어떻게 처리되어야 하는지를 알려주는 데 사용
	                response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(messageFile.getMsg_file_user_name(), "UTF-8") + "\"");
	                // HTTP 응답의 Content-Length 헤더를 파일 크기로 설정. 이는 다운로드 진행 상황 표시에 사용.
	                response.setContentLength((int) file.length());

	                // 파일을 읽기 위한 입력 스트림을 생성.
	                InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
	                // 응답의 출력 스트림으로 파일 내용을 복사. 이 과정에서 사용자가 파일을 다운로드
	                FileCopyUtils.copy(inputStream, response.getOutputStream());
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	
    /* ========== 버튼 기능 구현 =========== */
	
    // 답장쓰기 버튼 -> 답장쓰기 View 이동
    @GetMapping(value = "msgReply")
    public String msgReplyPage(HttpServletRequest request, Model model, 
    			@RequestParam(value = "sender", required = false) Long senderUserNo,
    			@RequestParam(value = "msgTitle", required = false) String msgTitle) {
    log.info("MsgController msgReplyPage start...");
    // 세션에서 보내는 사람의 아이디 가져오기
    Long senderId = (Long) request.getSession().getAttribute("user_no");
    // 유저 테이블에서 모든 사용자 목록 가져오기
    List<User> userList = msgService.getAllUsers();
    
    if (senderUserNo != null) {
        User senderUser = msgService.findUserDetailsById(senderUserNo);
        System.out.println("MsgController msgReplyPage userNo: " + senderUserNo);
        System.out.println("MsgController msgReplyPage senderUser: " + senderUser);
        // 읽기 페이지에서 받은 보낸 사람 정보 -> 답장쓰기 받는사람
        model.addAttribute("receiverUser", senderUser);
        model.addAttribute("senderUserNo", senderUserNo);
    }
    
    String replyTitle = "Re: " + msgTitle;
    
    model.addAttribute("replyTitle", replyTitle);
    model.addAttribute("senderId", senderId);
    model.addAttribute("userList", userList);
    
    return "kdw/msgReply";
    }
	
    // 쪽지쓰기 페이지로 이동(쪽지쓰기 버튼)
	@GetMapping(value = "msgWrite")
	public String msgWritePage(HttpServletRequest request, Model model) {
	    System.out.println("MsgController msgWritePage Start...");
	    // 세션에서 보내는 사람의 아이디 가져오기
	    Long senderId = (Long) request.getSession().getAttribute("user_no");
	    
	    // 유저 테이블에서 모든 사용자 목록 가져오기
	    List<User> userList = msgService.getAllUsers();
	    
	    // 워크스페이스 페이지에서 요청하는 받는사람(닉네임+아이디로 인풋 입력)
	    String userNoParam = request.getParameter("user_no");
	    Long userNo = null;
	    User userNicId = null;

	    System.out.println("MsgController msgWritePage userNoParam: " + userNoParam);
	    
	    if (userNoParam != null && !userNoParam.isEmpty()) {
	        try {
	            userNo = Long.parseLong(userNoParam);
	            userNicId = msgService.findUserDetailsById(userNo);
	            model.addAttribute("receiverNick", userNicId.getUser_nic());
	            model.addAttribute("receiverUserId", userNicId.getUser_id());
	        } catch (NumberFormatException e) {
	        	e.printStackTrace();
	        }
	    }

	    // 모델에 데이터 추가 (세션 유저no, 유저리스트, receiverId)
	    model.addAttribute("senderId", senderId);
	    model.addAttribute("userList", userList);
	    // 워크스페이스 페이지에서 요청하는 받는사람
	    model.addAttribute("userNo", userNo);

	    return "kdw/msgWrite";
	}
    
    // 쪽지 보내기 - 멀티 업로드(보내기 버튼)
	@ResponseBody
    @PostMapping(value = "msgSent")
    public String sendMsg (Message message, @RequestParam("files") MultipartFile[] files, HttpServletRequest request) {

    	System.out.println("MsgController sendMsg message : " + message);
        try {
        	
			String msgReceivers = request.getParameter("msg_receivers");

			// 쉼표로 문자열을 분리하고, 공백을 제거한 후 Long 타입으로 변환합니다.

			List<Long> receiversList = Arrays.stream(msgReceivers.split(",")).map(String::trim) // 배열들 한번에 공백 삭제
																			 .map(Long::parseLong) // 타입'Long'으로 파싱
																			 // 변환된 Long 값들을 List<Long>으로 수집
																			 .collect(Collectors.toList());
			// HttpSession에서 로그인한 사용자 정보 가져오기 (쪽지 보내는 사람)
			Long msgSender = (Long) request.getSession().getAttribute("user_no");
			// 업로드 경로 설정
            String path = request.getSession().getServletContext().getRealPath("/upload/msgFile/");
            
            message.setMsg_sender(msgSender);
            
            // 각 수신자에 대해 메시지 전송 로직을 반복 실행합니다.
            for(Long receiver : receiversList) {
                message.setMsg_receiver(receiver); // 수신자 설정
                msgService.sendMsg(message, files, path); // 메시지 전송 서비스 호출
            }
            return "Message sent successfully";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("MsgController sendMsg Exception ->" + e.getMessage());
            return "Error sending message";
        }
    }
	// 보내기 성공 페이지 이동
	@GetMapping("/msgSentSuccessPage")
	public String msgSentSuccessPage() {
	    return "kdw/msgSent";
	}
    
    // 주소록 버튼 클릭시 -> 주소록 모달 페이지 이동
    @GetMapping("/getAddressBookList")
    @ResponseBody
    public List<KphUsers> getAddressBookList(HttpServletRequest request) {
    	System.out.println("MsgController getAddressBookList start...");
        Long userNo = Long.parseLong(request.getSession().getAttribute("user_no").toString());
        System.out.println("MsgController getAddressBookList userNo: " + userNo);
        List<KphUsers> addressUserList = kphProjectService.addressUserList(userNo);
        System.out.println("MsgController getAddressBookList addressUserList.size(): " + addressUserList.size());
        return addressUserList;
    }
    
    
    // '보관' 버튼 클릭 시 msg_store_chk를 1로 업데이트
    @PostMapping(value = "updateMsgStoreStatus")
    public void updateMsgStoreStatus(@RequestBody Map<String, List<Long>> requestData, HttpServletResponse response) {
        List<Long> msgNos = requestData.get("msgNos");
        log.info("MsgController updateMsgStoreStatus 시작...");
        try {
            msgService.updateMsgStoreStatus(msgNos);
            System.out.println("쪽지 보관 성공");
        } catch (Exception e) {
            log.error("쪽지 보관에 실패했습니다.", e);
            e.printStackTrace();
            System.out.println("MsgController updateMsgStoreStatus Exception ->" + e.getMessage());
        }
    }
    
    // '삭제' 버튼 클릭 시 msg_delete_chk를 1로 업데이트
    @PostMapping(value = "updateMsgDeleteStatus")
    public void updateMsgDeleteStatus(@RequestBody Map<String, List<Long>> requestData, HttpServletResponse response) {
        List<Long> msgNos = requestData.get("msgNos");
        log.info("MsgController updateMsgDeleteStatus start...");
        
        try {
            msgService.updateMsgDeleteStatus(msgNos);
            System.out.println("쪽지 삭제 성공");
        } catch (Exception e) {
            log.error("쪽지 삭제에 실패했습니다.", e);
            e.printStackTrace();
            System.out.println("MsgController updateMsgDeleteStatus Exception ->" + e.getMessage());
        }
    }
    
    
    // '영구 삭제' 버튼 클릭 시 쪽지 영구 삭제
    @PostMapping(value = "/permanentDeleteMessages")
    public void permanentDeleteMessages(@RequestBody Map<String, List<Long>> requestData,HttpServletRequest request, HttpServletResponse response) {
        List<Long> msgNos = requestData.get("msgNos");
        log.info("MsgController permanentDeleteMessages start...");
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        try {
            for (Long msgNo : msgNos) {
                // 첨부된 파일 목록 조회
                List<MessageFile> files = msgService.getMessageFiles(msgNo);

                // 파일 시스템에서 파일 삭제
                for (MessageFile file : files) {
                    String filePath = request.getSession().getServletContext().getRealPath("/upload/msgFile/") + file.getMsg_file_name();
                    File f = new File(filePath);
                    if (f.exists() && f.delete()) {
                        log.info("File deleted successfully: " + filePath);
                    } else {
                        log.error("Failed to delete the file: " + filePath);
                    }
                }

                // 데이터베이스에서 파일 정보 삭제
                msgService.deleteMessageFilesByMsgNo(msgNo);

                // 쪽지 정보 삭제
                msgService.permanentDeleteMessages(Arrays.asList(msgNo));
            }

            response.getWriter().write("쪽지와 관련 파일들이 성공적으로 삭제되었습니다.");
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            log.error("쪽지 영구 삭제에 실패했습니다.", e);
        }
    }
    

    /* ========== 버튼 기능 구현 END =========== */
    
    
}
