package com.blackberry.s20240130103.lsl.dao;

import java.util.List;

import com.blackberry.s20240130103.lsl.model.LslBoardComm;
import com.blackberry.s20240130103.lsl.model.LslCommReply;
import com.blackberry.s20240130103.lsl.model.LslboardFile;

public interface LslDao {


	/* 게시판 */  
	
	// 자유 게시판 리스트 
	int totalBoardFree();
	List<LslBoardComm> boardFreeList(LslBoardComm lslBoardComm);

	// 질문 게시판 리스트 
	int totalBoardAsk();
	List<LslBoardComm> boardAskList(LslBoardComm lslBoardComm);


	// 게시판 검색 
	int totalBoardSearchFree(LslBoardComm lslBoardComm, String keyword, String type);
	List<LslBoardComm> boardFreeSearch(LslBoardComm lslBoardComm, String keyword, String type, int start, int end);
	
	int totalBoardSearchAsk(LslBoardComm lslBoardComm, String keyword, String type);
	List<LslBoardComm> boardAskSearch(LslBoardComm lslBoardComm, String keyword, String type, int start, int end);


	// 게시판 상세 페이지
	LslBoardComm boardAskContents(int cboard_no);
	LslBoardComm boardFreeContents(int cboard_no);
	
	// 게시판 상세 페이지 댓글 카운트
	int boardReplyCnt(int cboard_no);


	// 게시판 글쓰기
	int boardFreeWriteInsert(LslBoardComm lslBoardComms);
	int boardAskWriteInsert(LslBoardComm lslBoardComm);

	// 게시판 글 삭제
	int deleteFreeBoard(LslBoardComm lslBoardComms);
	int deleteAskBoard(LslBoardComm lslBoardComm);

	// 게시판 조회수 
	int boardFreeViewCnt(LslBoardComm lslBoardComm);
	int boardAskViewCnt(LslBoardComm lslBoardComm);

	// 게시판 글 수정 페이지
	LslBoardComm boardFreeModify(int cboard_no);
	LslBoardComm boardAskModify(int cboard_no);

	
	// 게시판 글 수정
	int  boardUpdate(LslBoardComm lslBoardComm);
	
	// 게시판 파일 업로드
	void saveBoardFile(LslboardFile lslboardFile);
	
	// 게시판 파일 상세 내역
	List<LslboardFile> boardAskFiles(int cboard_no);
	List<LslboardFile> boardFreeFile(int cboard_no);
		
	// 게시판 기존 데이터 삭제
	void deleteBoardOldData(int cboard_no);
		

	
	/* 댓글 */  
	
	// 댓글 리스트 
	List<LslCommReply> replyBoardFreeAskList(int cboard_no);
	
	// 댓글 등록 
	int insertBoardReply(LslCommReply lslCommReply);

	// 댓글 삭제 
	int deleteBoardReply(int creply_no);

	// 댓글 수정
	int modifyBoardReply(LslCommReply lslCommReply);
	
	
	// 대댓글 
	
	// 댓글 등록 
	int insertBoardReReply(LslCommReply lslCommReply);
	
	// 대댓글 리스트 
	LslCommReply reReply(LslCommReply lslCommReply);
	
	// 대댓글 업데이트 
	int  updateReply(LslCommReply lslCommReply);
	void deleteBoardFile(LslboardFile file);
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	

	

	
	
	
	






	









}