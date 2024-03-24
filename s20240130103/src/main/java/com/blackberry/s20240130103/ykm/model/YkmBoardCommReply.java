package com.blackberry.s20240130103.ykm.model;

import java.util.Date;

import lombok.Data;

@Data
public class YkmBoardCommReply {
	
	// Reply 댓글
	private int creply_no;			// 댓글 번호
	private String creply_content;	// 내용
	private Date creply_date;		// 작성일
	private int creply_group;		// 그룹 - 부모 댓글: 0
	private int creply_level;		// 레벨 - 댓글 순서
	private int creply_indent;		// 들여쓰기
	private int cboard_no;			// 게시글 번호
	private Long user_no;			// 유저 번호
	private int creply_delete_chk; 	// 댓글 삭제 상태
	private Date comm_update_date;	// 업데이트 날짜

	
	// Users 조회용
	private String user_id;			// 유저ID
	private String user_profile;	// 유저 프로필 사진
	private String user_nic;		// 유저 닉네임
	
}
