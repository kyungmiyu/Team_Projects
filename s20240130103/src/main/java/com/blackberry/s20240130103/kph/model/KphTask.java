package com.blackberry.s20240130103.kph.model;

import java.util.List;

import lombok.Data;

@Data
public class KphTask {
	
	private Long project_no;
	private Long task_no;
	private String task_title;
	private String task_start;
	private String task_end;
	private int task_comp_chk;
	
	// 조회용
	private List<KphUsers> users;
	
	// 프로시져 용
	private Long po_task_no; 
	
}
