package com.blackberry.s20240130103.kph.model;

import java.util.List;

import lombok.Data;

@Data
public class KphBoardProjectReply {

	private Long preply_no;
	private String preply_content;
	private String preply_date;
	private Long preply_group;
	private int preply_level;
	private int preply_indent;
	private Long pboard_no;
	private Long user_no;
	private int preply_delete_chk;
	private String preply_update_date;
	
	// 조회용
	private String tagName;
	
}
