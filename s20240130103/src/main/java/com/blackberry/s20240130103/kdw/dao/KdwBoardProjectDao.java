package com.blackberry.s20240130103.kdw.dao;

import java.util.List;

import com.blackberry.s20240130103.kdw.model.BoardProject;
import com.blackberry.s20240130103.kdw.model.BoardProjectFile;

public interface KdwBoardProjectDao {
	
	// 게시글 개수
	int totPboardListCnt(Long userNo, Long projectNo);
	// 게시글 리스트
	List<BoardProject> getProjectBoardList(Long userNo, Long projectNo, int start, int end);
	
	// ! ========== 검색기능 ========== !
	// 검색한 게시글 개수
	int searchPboardListCnt(Long userNo, Long projectNo, String keyword, String type);
	// 검색한 게시글 리스트
	List<BoardProject> searchProjectBoardList(Long userNo, Long projectNo, String keyword, String type, int start, int end);
	
	// 글쓰기 + 업로드
	void writeSave(BoardProject boardProject);
	void savePboardFile(BoardProjectFile boardProjectFile);
	
	// 글수정
	void updateSave(BoardProject boardProject);
	// 파일 첨부 목록
	BoardProjectFile getBoardFiles(Long pboardNo, int pboardFileNo);
	// 파일삭제
	void deleteFilesByPboardNo(Long pboardNo, int pboardFileNo);
	// 파일 상세(사이즈)
	BoardProjectFile getFileDetails(Long pboardNo, int pboardFileNo);
	

	

}
