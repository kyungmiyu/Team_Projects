package com.blackberry.s20240130103.yhs.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Ask {
	private long user_no;
	private int admin_no;
	private Date admin_date;
	private String admin_title;	
	private String admin_content;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date admin_start;
	private long admin_reply_group;
	private long admin_reply_chk;
	private long admin_reply_user;
	
	//조회용
	private String search;
	private String keyword;
	private String pageNum;
	private int start;
	private int end;
	
	//Page 정보
	private String currentPage;

	// 회원 조회
	private String user_nic;
	private String user_id;
	private String user_name;
	
}
