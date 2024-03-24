package com.blackberry.s20240130103.ykm.model;

import java.util.Date;


import lombok.Data;

@Data
public class YkmBoardComm {

	// BoardComm
	private int cboard_no;			// 게시글 번호
	private String cboard_title;	// 제목
	private String cboard_content;	// 내용
	private int cboard_viewcnt;		// 조회수
	private Date cboard_date;		// 작성 날짜
	private Long user_no;			// 유저 번호
	private int cboard_delete_chk;	// 삭제 상태 (0 > 1)
	private int comm_big;			// 게시판 대분류 - 정보게시판: 200
	private int comm_mid;			// 게시판 중분류	- 공모전:10 스터디:20
	private int comm_big2;			// 스터디 모집 여부
	private int comm_mid2;			// 모집중(10), 모집여부(20)
	private Date comm_update_date;	// 업데이트 작성일
	
	// BoardCommFile
	private int cboard_file_cnt;	 // 파일 순서
	//private String cboard_file_name; // 파일 이름
	
	// Users 조회용
	private String user_id;			 // 유저ID
	private String user_nic;		 // 유저 닉네임
	private String user_profile;	 // 유저 프로필
	
	// 댓글 카운트
	private int countComment;		 // 댓글 카운트(상세보기)
	private int reply_count;		 // 댓글 카운트(게시물 리스트)

	// 검색 조회용
	private String type; 			 // 검색 타입
	private String keyword;  		 // 검색 키워드
	
	// 페이징 조회용
	private String pageNum;			 // 페이지 번호
	private int start;  			 // 시작
	private int end; 				 // 끝
	private String currentPage;	     // 현재 페이지 번호
	
	
}
