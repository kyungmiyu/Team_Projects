package com.blackberry.s20240130103.kdw.model;

import lombok.Data;

@Data
public class BoardProjectReply {
	
	private   Long preply_no;  		   // 댓글번호(SEQ)
	private String preply_content; 	   // 댓글내용
	private String preply_date; 	   // 작성일
	private    int preply_group; 	   // 댓글그룹
	private    int preply_level; 	   // 댓글레벨
	private    int prely_indent; 	   // 댓글들여쓰기
	private   Long pboard_no;   	   // 글번호
	private   Long user_no;			   // 작성자번호
	private    int preply_delete_chk;  // 삭제상태
	private String preply_update_date; // 최종수정일자

}
