package com.blackberry.s20240130103.kph.model;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class KphUserBoardProject {

	private Long pboard_no;
	private Long project_no;
	private String pboard_title;
	private String pboard_content;
	private String pboard_date;
	private int pboard_cnt;
	private int pboard_delete_chk;
	private String pboard_update_date;
	
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
	private List<KphBoardProjectFile> fileList;
	private Map<Long, List<KphUserBoardProjectReply>> replyMapByGroup;
	private int replyCnt;
	
}
