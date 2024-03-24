package com.blackberry.s20240130103.lsl.Service;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.blackberry.s20240130103.lsl.dao.LslDao;
import com.blackberry.s20240130103.lsl.model.LslBoardComm;
import com.blackberry.s20240130103.lsl.model.LslCommReply;
import com.blackberry.s20240130103.lsl.model.LslboardFile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class LslServiceImpl implements LslService {

	private final LslDao ld;

	// 자유 게시판 토탈 카운트 
	@Override
	public int totalBoardFree() {
		System.out.println("LslServiceImpl totalBoardFree Start...");
		int totalBoardFreeCnt = ld.totalBoardFree();
		System.out.println("LslServiceImpl totalBoardFreeListCnt -> " + totalBoardFreeCnt);
		return totalBoardFreeCnt;
	}
	
	// 자유 게시판 리스트 
	@Override
	public List<LslBoardComm> boardFreeList(LslBoardComm lslBoardComm) {
		List<LslBoardComm> boardFreeList = null;
		boardFreeList = ld.boardFreeList(lslBoardComm);
		System.out.println("LslServiceImpl boardFreeList.size() ->" + boardFreeList.size());
		return boardFreeList;
	}

	//  자유 게시판 리스트 검색 카운트
	@Override
	public int totalBoardSearchFree(LslBoardComm lslBoardComm, String keyword, String type) {
		int totalBoardSearchFree = ld.totalBoardSearchFree(lslBoardComm, keyword, type);
		return totalBoardSearchFree;
	}
	
	// 자유 게시판 리스트 검색 
	@Override
	public List<LslBoardComm> boardFreeSearch(LslBoardComm lslBoardComm, String keyword, String type, int start, int end) {
	 List<LslBoardComm> boardFreeSearch = null;
	 System.out.println("LslServiceImpl boardFreeSearch Start...");
	  boardFreeSearch = ld.boardFreeSearch(lslBoardComm, keyword, type, start, end);
	  System.out.println("EmpServiceImpl boardFreeSearch.size() ->"+boardFreeSearch.size());
	 return boardFreeSearch;
	}

	// 자유 게시판 글 삭제
	@Override
	public int deleteFreeBoard(LslBoardComm lslBoardComm) {
		int deleteFreeBoard = ld.deleteFreeBoard(lslBoardComm);
		return deleteFreeBoard;
	}
	
	// 게시판 글 수정 페이지 
	@Override
	public LslBoardComm boardFreeModify(int cboard_no) {
		System.out.println("1시작");
		LslBoardComm boardFreeModify = ld.boardFreeModify(cboard_no);
		return boardFreeModify;
	}
	
	@Override
	public LslBoardComm boardAskModify(int cboard_no) {
		LslBoardComm boardAskModify = ld.boardAskModify(cboard_no);
		return boardAskModify;
	}
	
	// 질문 게시판 토탈 카운트 
	@Override
	public int totalBoardAsk() {
		System.out.println("LslServiceImpl totalBoardAsk Start...");
		int totalBoardAskCnt = ld.totalBoardAsk();
		System.out.println("LslServiceImpl totalBoardAskCnt -> "+totalBoardAskCnt);
		return totalBoardAskCnt;
	}
	
	// 질문 게시판 리스트 
	@Override
	public List<LslBoardComm> boardAskList(LslBoardComm lslBoardComm) {
	 List<LslBoardComm> boardAskList = null;
	 boardAskList = ld.boardAskList(lslBoardComm);
	 System.out.println("LslServiceImpl boardAskList.size() ->" + boardAskList.size());
	 return boardAskList;
	}

	// 질문 게시판 리스트 검색 카운트
	@Override
	public int totalBoardSearchAsk(LslBoardComm lslBoardComm , String keyword, String type) {
		int totalBoardSearchAsk = ld.totalBoardSearchAsk(lslBoardComm, keyword, type);
		return totalBoardSearchAsk;
	}
	
	// 질문 게시판 리스트 검색 
	@Override
	public List<LslBoardComm> boardAskSearch(LslBoardComm lslBoardComm, String keyword, String type, int start, int end) {
		 List<LslBoardComm> boardAskSearch = null;
		 System.out.println("LslServiceImpl boardAskSearch Start...");
		 boardAskSearch = ld.boardAskSearch(lslBoardComm, keyword, type, start, end);
		 System.out.println("EmpServiceImpl boardAskSearch.size() ->"+boardAskSearch.size());
		 return boardAskSearch;
	}
	
	// 질문 게시판 글 삭제 
	@Override
	public int deleteAskBoard(LslBoardComm lslBoardComm) {
		int deleteAskBoard = ld.deleteAskBoard(lslBoardComm);
		return deleteAskBoard;

	}
	
	// 질문 게시판 글 상세 내역 
	@Override
	public LslBoardComm boardAskContents(int cboard_no) {
		System.out.println("LslServiceImpl boardAskContents Start...");
		LslBoardComm boardAskContents = ld.boardAskContents(cboard_no);
		System.out.println("EmpServiceImpl boardAskContents -> " + boardAskContents);
		return boardAskContents;
	}
	
	// 질문 게시판 파일 상세 내역 
	@Override
	public List<LslboardFile> boardAskFile(int cboard_no) {
		List<LslboardFile> boardAskFiles = ld.boardAskFiles(cboard_no);
		return boardAskFiles;
	}

	// 자유 게시판 글 상세 내역 
	@Override
	public LslBoardComm boardFreeContents(int cboard_no) {
		System.out.println("LslServiceImpl boardAskContents Start...");
		LslBoardComm boardFreeContents = ld.boardFreeContents(cboard_no);
		System.out.println("EmpServiceImpl boardAskContents -> " + boardFreeContents);
		return boardFreeContents;
	}
	
	// 질문 게시판 파일 상세 내역 
	@Override
	public List<LslboardFile> boardFreeFile(int cboard_no) {
		List<LslboardFile> boardFreeFile = ld.boardFreeFile(cboard_no);
		return boardFreeFile;
	}
	
	// 게시판 글 상세 페이지 댓글 카운트 
	@Override
	public int boardReplyCnt(int cboard_no) {
		int boardReplyCnt = ld.boardReplyCnt(cboard_no);
		return boardReplyCnt;
	}

	// 질문 게시판 글쓰기 
	@Override
	public void boardAskWriteInsert(LslBoardComm lslBoardComm, MultipartFile[] multipartFile, String boardfilePath) {
		ld.boardAskWriteInsert(lslBoardComm);
		fileUpload(lslBoardComm,multipartFile,boardfilePath);
	}
	
	// 파일 이름 가져오기 
	@Override
	public List<LslboardFile> boardFileNames(MultipartFile[] multipartFiles, String boardFilePath) {
		List<LslboardFile> boardFileNames = new ArrayList<>();
	    for (MultipartFile file : multipartFiles) {
	        try {
	        	LslboardFile updateFile = new LslboardFile();
	            String fileName = boardFileSave(file, boardFilePath);
	            updateFile.setCboard_file_name(fileName);
	            updateFile.setCboard_file_user_name(file.getOriginalFilename());
	            boardFileNames.add(updateFile);
	        } catch (Exception e) {
	            e.printStackTrace();
	           System.out.println("boardFileNames Exception -> " + e.getMessage());
	        }
	    }
	    return boardFileNames;
	}
	
	public String boardFileSave(MultipartFile multipartFile, String boardfilePath) throws Exception {
		String orignalFileName = multipartFile.getOriginalFilename();
		String fileEx = orignalFileName.substring(orignalFileName.lastIndexOf("."));
	    //  중복되지 않는 파일명 생성(UUID, Date)
	    String fileName = UUID.randomUUID().toString()+fileEx;
	    // 3. 파일 저장
	    File boardFile = new File(boardfilePath, fileName);
	    Files.copy(multipartFile.getInputStream(), boardFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
	    return fileName;
	}
	
	// 질문 게시판 조회수 
	@Override
	public int boardAskViewCnt(LslBoardComm lslBoardComm) {
		int boardAskViewCnt = ld.boardAskViewCnt(lslBoardComm);
		return boardAskViewCnt;
	}

	// 자유 게시판 글 쓰기 
	@Override
	public void boardFreeWriteInsert(LslBoardComm lslBoardComm, MultipartFile[] multipartFile, String boardfilePath) {
		ld.boardFreeWriteInsert(lslBoardComm);
		fileUpload(lslBoardComm,multipartFile,boardfilePath);
	}

	// 자유 게시판 리스트 조회수 
	@Override
	public int boardFreeViewCnt(LslBoardComm lslBoardComm) {
		int boardFreeViewCnt = ld.boardFreeViewCnt(lslBoardComm);
		return boardFreeViewCnt;
	}

	//게시판 댓글 리스트 
	@Override
	public List<LslCommReply> replyBoardFreeAskList(int cboard_no) {
		System.out.println("LslServiceImpl replyBoardFreeList Start...");
		List<LslCommReply> replyBoardFreeAskList = ld.replyBoardFreeAskList(cboard_no);	
		System.out.println("LslServiceImpl replyBoardFreeList.size() ->" + replyBoardFreeAskList.size());	
		return replyBoardFreeAskList;
	}
	
	// 댓글 등록 
	@Override
	public int insertBoardReply(LslCommReply lslCommReply) {
		int boardFreeAskResult = ld.insertBoardReply(lslCommReply);
		return boardFreeAskResult;
	}	
	
	// 댓글 삭제 
	@Override
	public int deleteBoardReply(int creply_no) {
		int boardFreeAskResult = ld.deleteBoardReply(creply_no);	
		return boardFreeAskResult;
	}
	
	// 댓글 수정
	@Override
	public int modifyBoardReply(LslCommReply lslCommReply) {
		int boardFreeAskResult = ld.modifyBoardReply(lslCommReply);
		return boardFreeAskResult;
	}
	
	// 대댓글 등록
	@Override
	public int insertBoardReReply(LslCommReply lslCommReply) {
		int boardReReplyResult = ld.insertBoardReReply(lslCommReply);
		return boardReReplyResult;
	}
		
	// 리스트 
	@Override
	public LslCommReply reReply(LslCommReply lslCommReply) {
		LslCommReply reReply = ld.reReply(lslCommReply);
		return reReply;
	}
	
	// 대댓글 업데이트
	@Override
	public int updateReply(LslCommReply lslCommReply) {
		int updateReply =  ld.updateReply(lslCommReply);
		return updateReply;
	}		
	
	//게시판수정
	@Override
	public int boardUpdate(LslBoardComm lslBoardComm, MultipartFile[] multipartFile, String boardfilePath,
			String deleteFileFiles) {
		int boardUpdate = ld.boardUpdate(lslBoardComm);
		if(deleteFileFiles.length() != 0) {
			String[] deleteFileNum = deleteFileFiles.split("-");
			List<LslboardFile> fileList = boardFreeFile(lslBoardComm.getCboard_no());
			for(String str : deleteFileNum) {
				for(LslboardFile file : fileList) {
					if(file.getCboard_file_cnt() == Integer.parseInt(str)) {
						ld.deleteBoardFile(file);
						fileDelete(file.getCboard_file_name(),boardfilePath);
					}
				}
			}
		}
		fileUpload(lslBoardComm,multipartFile,boardfilePath);
		return boardUpdate;
	}
		
	//파일업로드
	private void fileUpload(LslBoardComm lslBoardComm,MultipartFile[] multipartFile, String boardfilePath) {
		if (multipartFile != null && multipartFile.length > 0) {
			File file = new File(boardfilePath);
			if (!file.exists()) {
				boolean check = file.mkdirs();
				if(!check) {
					System.out.println("디렉토리 생성 실패 ->" + boardfilePath);		
				}
			}
			for(MultipartFile files : multipartFile) {
				if(!files.isEmpty()) {
					System.out.println("files => " +files.getOriginalFilename());			
					try {
						String boardFileName = boardFileSave(files, boardfilePath);
						//File baordFile = new File(boardfilePath, boardFileName);
						//DB 저장
						LslboardFile lslboardFile = new LslboardFile();							
						lslboardFile.setCboard_no(lslBoardComm.getCboard_no());
						System.out.println("file cboard_no ->" + lslBoardComm.getCboard_no());
						lslboardFile.setCboard_file_name(boardFileName);
						lslboardFile.setCboard_file_user_name(files.getOriginalFilename());
						ld.saveBoardFile(lslboardFile);		
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("파일 저장 오류  ->" + e.getMessage());
						}
				}
			}
		}
	}
	
	private void fileDelete(String fileName,String filePath) {
		File file = new File(filePath+fileName);
		try {
			if(file.exists()) {
				file.delete();
			}
		}catch (Exception e) {
			System.out.println("fileDelete exception e : " + e.getMessage());
		}
		
	}


	
	
}