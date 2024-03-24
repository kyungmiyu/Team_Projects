package com.blackberry.s20240130103.ykm.model;

import lombok.Data;

@Data
public class YkmBoardCommFile {
	
	// BoardCommFile
	private Long user_no; 					// 유저 번호
	private int cboard_no;					// 게시글 번호
	private int cboard_file_cnt;			// 청부파일 순서
	private String cboard_file_name;		// 첨부파일 데이터베이스 저장 이름(UUID)
	private String cboard_file_user_name;	// 첨부파일 유저가 업로드한 파일 이름

	
}
