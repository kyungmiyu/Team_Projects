package com.blackberry.s20240130103.kph.model;

import lombok.Data;

@Data
public class KphProjectTask {

	// 프로젝트
	private Long project_no;
	private String project_title;
	private String project_content;
	private String project_start;
	private String project_end;
	private Long user_no;
	private String project_date;
	private int project_delete_chk;
	private int project_comp_chk;
	
	// 과업
	private Long task_no;
	private String task_title;
	private String task_start;
	private String task_end;
	private int task_comp_chk;
	
	// 조회용
	private String currentPage;
	private int start;
	private int end;
	private String keyword;
	private String searchFilter;
	private String sortFilter;
	private String clickedNav; // 클릭된 탭
	
}
