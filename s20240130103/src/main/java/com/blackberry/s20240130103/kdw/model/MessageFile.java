package com.blackberry.s20240130103.kdw.model;


import lombok.Data;

@Data
public class MessageFile {
	private Long msg_no; // 쪽지번호
	private int msg_file_cnt; // 쪽지 순서번호 (MAX)
	private String msg_file_name; // 첨부파일 가공 식별 저장명
	private String msg_file_user_name; // 첨부파일 오리지날 유저명
	
}

