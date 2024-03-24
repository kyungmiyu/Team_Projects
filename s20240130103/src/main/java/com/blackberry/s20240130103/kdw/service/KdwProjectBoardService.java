package com.blackberry.s20240130103.kdw.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.blackberry.s20240130103.kdw.model.BoardProject;
import com.blackberry.s20240130103.kdw.model.BoardProjectFile;

public interface KdwProjectBoardService {
	
	/* ======== 글 조회 ========= */
	// 게시글 개수
	int totPboardListCnt(Long userNo, Long projectNo);
	// 게시글 리스트
	List<BoardProject> getProjectBoardList(Long userNo, Long projectNo, int start, int end);
	
	// ! ========== 검색기능 ========== !
	// 검색한 게시글 개수
	int searchPboardListCnt(Long userNo, Long projectNo, String keyword, String type);
	// 검색한 게시글 리스트
	List<BoardProject> searchProjectBoardList(Long userNo, Long projectNo, String keyword, String type, int start, int end);
	
	/* ======== 글 쓰기 & 업로드 ========= */
	void writeSave(BoardProject boardProject, MultipartFile[] files, String path);
	
	/* ======== 글 수정 ========= */
	// 글 수정
	void updateSave(BoardProject boardProject, MultipartFile[] files, String path);
	// 파일 목록 가져오기
	BoardProjectFile getBoardProjectFiles(Long pboardNo, int pboardFileNo);
	// 파일 삭제
	void deleteFilesByPboardNo(Long pboardNo, int pboardFileNo);
	// 파일사이즈
	long getFileSize(String filePath);
	// 파일 상세정보
	BoardProjectFile getFileDetails(Long pboardNo, int pboardFileNo);


}
