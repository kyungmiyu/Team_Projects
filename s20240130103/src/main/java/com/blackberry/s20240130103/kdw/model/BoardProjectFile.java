package com.blackberry.s20240130103.kdw.model;

import lombok.Data;

@Data
public class BoardProjectFile {
	
	private Long pboard_no;               // 글번호
	private int	pboard_file_no;           // 파일순서번호(MAX)
	private String pboard_file_name;      // 첨부파일 파일명
	private String pboard_file_user_name; // 파일유저 저장명
}
