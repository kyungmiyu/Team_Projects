package com.blackberry.s20240130103.ykm.dao;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.blackberry.s20240130103.ykm.model.YkmBoardComm;
import com.blackberry.s20240130103.ykm.model.YkmBoardCommFile;
import com.blackberry.s20240130103.ykm.model.YkmBoardCommReply;

public interface YkmBoardDao {
	
	/* 스터디 게시판 */
	List<YkmBoardComm> getPostList(YkmBoardComm ykmBoardComm); // 게시글 리스트 조회
	
	YkmBoardComm getPost(int cboard_no); // 작성글 상세보기
	
	int writePost(YkmBoardComm ykmBoardComm); // 글 작성

	int updatePost(YkmBoardComm ykmBoardComm); // 글 수정
	
	int deletePost(int cboard_no); // 글 삭제
	
	int increseViewcount(int cboard_no);	// 댓글 조회수
	
	int updateRecruitment(YkmBoardComm ykmBoardComm); // 모집여부
	
	int saveFileList(YkmBoardCommFile ykmBoardCommFile); // 파일 DB 저장
	
	List<YkmBoardCommFile> getFileList(int cboard_no); // 파일 리스트 불러오기
	
	
	/* 스터디 게시판 댓글 REST API	*/
	List<YkmBoardCommReply> getCommentList(int cboard_no); // 댓글 리스트 조회

	int writeComment(YkmBoardCommReply ykmBoardCommReply); // 댓글 작성

	int deleteComment(int creply_no); // 댓글 삭제

	int updateComment(YkmBoardCommReply ykmBoardCommReply); // 댓글 수정

	int countComment(int cboard_no); // 게시글 상세보기 댓글 카운트

	int getTotalCount(YkmBoardComm ykmBoardComm); // (페이징) 전체 게시글 카운트

	int writeReply(YkmBoardCommReply ykmBoardCommReply); // 대댓글 > 중복이므로 수정 필요

	YkmBoardCommReply getReplyNo(YkmBoardCommReply ykmBoardCommReply); // 댓글 번호 조회

	int updateGroup(YkmBoardCommReply ykmBoardCommReply); // 같은 댓글 그룹핑
	

	
	/* 공모전 게시판 */
	List<YkmBoardComm> getCntPostList(YkmBoardComm ykmBoardComm); // 게시판 리스트 조회

	int getCntTotalCount(YkmBoardComm ykmBoardComm); // 전체 게시글 카운트

	int writeCntPost(YkmBoardComm ykmBoardComm); // 글 작성, 파일 업로드

	void deleteBoardFile(YkmBoardCommFile file);


}
