package com.blackberry.s20240130103.lsl.model;

import java.util.Date;

import lombok.Data;

@Data
public class LslCommReply {
	
	// 댓글 번호 Seq
	private int creply_no; // 댓글 번호 
	private String creply_content;
	private Date creply_date;
	private int creply_group;  // 댓글 그룹번호 
	private int creply_level;  // 댓글 레벨 
	private int creply_indent; //  댓글 들여쓰기 
	
	
	
	
	private String parent_user_id; //  부모 댓글 아이디 
	private int parent_creply_no;
	private int parent_creply_level;
	private int parent_creply_indent;
	
	
	
	// 조회용 댓글 카운트
	private int creply_cnt;
	
	
	// 글번호 FK
	private int cboard_no; // 글번호 
	

	// 조회용 유저 정보 
	private Long  user_no;   
	private String user_id;
	private String user_name;
	private String user_nic; 
	private String user_profile;
	private int creply_delete_chk;

}
