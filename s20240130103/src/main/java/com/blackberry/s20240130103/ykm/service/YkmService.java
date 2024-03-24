package com.blackberry.s20240130103.ykm.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.blackberry.s20240130103.ykm.model.YkmBoardComm;
import com.blackberry.s20240130103.ykm.model.YkmBoardCommFile;
import com.blackberry.s20240130103.ykm.model.YkmBoardCommReply;

public interface YkmService {

	/* 스터디 게시판 */
	
	int writePost(YkmBoardComm ykmBoardComm, String studyFilePath, List<MultipartFile> fileList); // 글작성
	
	List<YkmBoardComm> getPostList(YkmBoardComm ykmBoardComm); // 전체 게시글 리스트 조회
	
	YkmBoardComm getPost(int cboard_no); // 글 보여주기
	
	int updatePost(YkmBoardComm ykmBoardComm, String studyFilePath, List<MultipartFile> fileList, String deleteFileFiles); // 글 수정

	int deletePost(int cboard_no); // 글 삭제
	
	int increseViewCount(int cboard_no); // 글 조회수

	List<YkmBoardCommFile> getFileList(int cboard_no); // 게시글에 파일 목록 보여주기
	
	int getTotalCount(YkmBoardComm ykmBoardComm); // (페이징) 전체 글 카운트
	
	
	/* 스터디 게시판 댓글 */
	List<YkmBoardCommReply> getCommentList(int cboard_no); // 댓글 리스트 조회

	int writeComment(YkmBoardCommReply ykmBoardCommReply); // 댓글 작성

	int deleteComment(int creply_no); // 댓글 삭제

	int updateComment(YkmBoardCommReply ykmBoardCommReply); // 댓글 수정

	int countComment(int cboard_no); // 댓글 개수 카운트

	int updateRecruitment(YkmBoardComm ykmBoardComm); // 모집여부 값 변경

	int writeReply(YkmBoardCommReply ykmBoardCommReply); // 대댓글 작성
		
	int updateGroup(YkmBoardCommReply ykmBoardCommReply); // 대댓글 그룹
	
	YkmBoardCommReply getReplyNo(YkmBoardCommReply ykmBoardCommReply); // 대댓글 번호 조회
	
	
	/* 공모전 게시판 */
	List<YkmBoardComm> getCntPostList(YkmBoardComm ykmBoardComm); // 게시글 리스트
	
	int getCntTotalCount(YkmBoardComm ykmBoardComm); // (페이징) 전체 게시글 카운트



	
	
}
