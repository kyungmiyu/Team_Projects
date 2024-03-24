package com.blackberry.s20240130103.kph.model;

import lombok.Data;

@Data
public class KphUserProject {

	private Long project_no;
	private Long user_no;
	private int comm_big;
	private int comm_mid;

	// 조회용
	private String user_name;
	
}
