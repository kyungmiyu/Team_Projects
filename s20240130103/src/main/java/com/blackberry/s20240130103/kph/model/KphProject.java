package com.blackberry.s20240130103.kph.model;

import lombok.Data;

@Data
public class KphProject {

	private Long project_no;
	private String project_title;
	private String project_content;
	private String project_start;
	private String project_end;
	private Long user_no;
	private String project_date;
	private int project_delete_chk;
	private int project_comp_chk;
	private String project_update_date;
	
	// 조회용 컬럼
	private int comp_task_count; // 완료된 과업 갯수
	private int uncomp_task_count; // 미완료된 과업 갯수
	private int isEvalByUser; // 조회하는 유저에 의해 평가되었는지 여부
	
}
