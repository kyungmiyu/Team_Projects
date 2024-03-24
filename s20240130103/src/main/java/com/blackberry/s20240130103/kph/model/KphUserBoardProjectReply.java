package com.blackberry.s20240130103.kph.model;

import lombok.Data;

@Data
public class KphUserBoardProjectReply {

	private Long preply_no;
	private String preply_content;
	private String preply_date;
	private Long preply_group;
	private int preply_level;
	private int preply_indent;
	private Long pboard_no;
	private int preply_delete_chk;
	private String preply_update_date;
	
	private Long user_no;
	private String user_id;
	private String user_pw;
	private String user_name;
	private String user_nic;
	private String user_email;
	private String user_phone;
	private String user_profile;
	private int user_delete_chk;
	private String user_date;
	
	// 조회용
	private Long session_user_no;
	
}
