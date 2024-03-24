package com.blackberry.s20240130103.kph.model;

import lombok.Data;

@Data
public class KphUsers {

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
	
	// 조회용 테이블
	private double user_score;
	private int is_user_in_task;
}
