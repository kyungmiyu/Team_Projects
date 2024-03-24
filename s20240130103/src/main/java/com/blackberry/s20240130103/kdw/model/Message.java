package com.blackberry.s20240130103.kdw.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Message {
	
    private Long msg_no;
    private String msg_title;
    private String msg_content;
    private String msg_createdate;
    private String msg_readdate;
    private Long msg_sender;
    private Long msg_receiver;
    private int msg_delete_chk;
    private int msg_store_chk;
    
    //조회용
    private String user_profile;
    
    // 첨부파일 여부 확인용
    private String first_file_name;
    
    // 멀티 파일 업로드 저장용 
    private MultipartFile[] files;
    
    // 멀티 파일 다운로드 정보용
    private List<MessageFile> fileMsgs;
    
    // 검색용
	private String keyword; // 검색 키워드
	private String type; // 검색 타입
	
	private String senderNic; // 보낸 사람의 닉네임
	private String receiverNic; // 받는 사람 닉네임
	private String storedNic; // 보관함 닉네임
	private String trashedNic; // 휴지통 닉네임
}
