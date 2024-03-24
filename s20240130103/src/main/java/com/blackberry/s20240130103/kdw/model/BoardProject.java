package com.blackberry.s20240130103.kdw.model;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardProject {
	
	private   Long pboard_no;          // 글번호(SEQ)
	private   Long project_no;         // 프로젝트번호
	private String pboard_title;       // 제목
	private String pboard_content; 	   // 내용
	private   Date pboard_date;        // 작성일
	private    int pboard_cnt;         // 조회수
	private   Long user_no;            // 작성자번호
	private    int pboard_delete_chk;  // 삭제상태 0 = 삭제X, 1 = 삭제O
	private String pboard_update_date; // 최종수정일자
	
	private String user_nic;            // 게시글 유저 닉네임 저장용
	private    int reply_count;        // 게시글 댓글개수 저장용
	
    // 멀티 파일 업로드 저장용 
    private MultipartFile[] files;
    
    // 검색용
	private String keyword; // 검색 키워드
	private String type; // 검색 타입
}
