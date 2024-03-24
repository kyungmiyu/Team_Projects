package com.blackberry.s20240130103.lhs.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class BoardAdmin {
	private long user_no;
	private int admin_no;
	private Date admin_date;
	private String admin_title;	
	private String admin_content;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date admin_start;
	private long admin_reply_group;
	private long admin_reply_chk;
	
	
	
	//--------- 조회용 -------------
	private String user_name;
	private String user_id;
}
