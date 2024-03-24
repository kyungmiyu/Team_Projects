package com.blackberry.s20240130103.lhs.model;

import java.util.Date;

import lombok.Data;
@Data
public class Reply {
	// 댓글 번호 Seq
	private int creply_no; // 댓글 번호 
	private String creply_content;
	private Date creply_date;
	private int creply_group;  // 댓글 그룹번호 
	private int creply_level;  // 댓글 레벨 
	private int creply_indent; //  댓글 들여쓰기 
	private Long  user_no; 
	private int cboard_no; 
	private int creply_delete_chk;
	
	private String cboard_title;
	private String user_id;

	private String searchkind;
	private String searchValue;
	//----------페이징용----------
	private String pageNum;
	private int start;
	private int end;
	private String currentPage;
}
