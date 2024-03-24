package com.blackberry.s20240130103.lsl.model;

import java.util.Date;

import lombok.Data;

@Data
public class LslUser {
	
	private Long user_no;
	private String user_id;
	private String user_pw;
	private String user_name;
	private String user_nic;
	private String user_email;
	private String user_phon;
	private String user_profile;
	private int user_delet_chk;
	private Date user_date;
	

}
