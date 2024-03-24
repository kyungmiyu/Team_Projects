package com.blackberry.s20240130103.lsl.model;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;


import lombok.Data;

@Data
public class LslBoardComm {
	
	// board_comm
	private int cboard_no;
	private String cboard_title;
	private String cboard_content;
	private int cboard_viewcnt;
	private Date cboard_date;
	private Long user_no;
	private int cboard_delete_chk;
	private int comm_big;
	private int comm_mid;
	private int comm_big2;
	private int comm_mid2;
	private String comm_update_date;
	

	// file 저장용 
	private MultipartFile[] multipartFile;
	

	// board_file_comm
	private int cboard_file_cnt;
	private String cboard_file_name;
	private String cboard_file_user_name;
	
	// File 다운로드용
	private List<LslboardFile> boardFiles;

	// 조회용
	private String search;
	private String keyword;
	private String type;
	private String pageNum;

	// Page 정보
	private int start;
	private int end;
	private String currentPage;
	private int pageBlock;
	private int rowPage;
	private int startPage;			private int endPage;
	private int total;				private int totalPage;
	
	
	// 댓글 조회용
	private Long creply_no;
	private int creply_cnt;
	
	// 회원 조회
	private String user_nic;
	private String user_id;
	private String user_name;
	private String user_profile;
	
	
	
	// 보드 타입
	private String boardType;
}
