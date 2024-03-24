package com.blackberry.s20240130103.kph.model;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class KphBoardProject {

	private Long pboard_no;
	private Long project_no;
	private String pboard_title;
	private String pboard_content;
	private String pboard_date;
	private int pboard_cnt;
	private Long user_no;
	private int pboard_delete_chk;
	private String pboard_update_date;
	
}
