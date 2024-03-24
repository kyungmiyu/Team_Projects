package com.blackberry.s20240130103.lhs.model;

import java.util.Date;

import lombok.Data;

@Data
public class BoardComm {
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
	private Date comm_update_date;
	
	//----------조회용-----------
	private String user_id;
	private String user_name;
	private String user_profile;
	private String comm_content;
	private int reply_cnt;
	
	private String searchkind;
	private String searchValue;
	//----------페이징용----------
	private String pageNum;
	private int start;
	private int end;
	private String currentPage;
}
