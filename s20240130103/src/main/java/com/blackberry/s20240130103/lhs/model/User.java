package com.blackberry.s20240130103.lhs.model;


import java.util.Date;

import lombok.Data;

@Data
public class User {
	private Long user_no;
	private String user_id;
	private String user_pw;
	private String user_name;
	private String user_nic;
	private String user_email;
	private String user_phone;
	private String user_profile;
	private int user_delete_chk;
	private Date user_date;
	private Date user_update_date;
	private int user_rank_big;
	private int user_rank_mid;
	
	
	//---------조회용----------
	private String comm_content;
	
	private String searchkind;
	private String searchValue;
	//----------페이징용----------
	private String pageNum;
	private int start;
	private int end;
	private String currentPage;
}
