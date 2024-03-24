package com.blackberry.s20240130103.kdw.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.blackberry.s20240130103.kdw.dao.KdwBoardProjectDao;
import com.blackberry.s20240130103.kdw.model.BoardProject;
import com.blackberry.s20240130103.kdw.model.BoardProjectFile;
import com.blackberry.s20240130103.kdw.model.MessageFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class KdwProjectBoardServiceImpl implements KdwProjectBoardService {
	
	private final KdwBoardProjectDao pboardDao;
	
	// 게시글 개수
	@Override
	public int totPboardListCnt(Long userNo, Long projectNo) {
		log.info("KdwProjectBoardServiceImpl totPboardListCnt start...");
		int totPboardListCnt = pboardDao.totPboardListCnt(userNo, projectNo);
		log.info("KdwProjectBoardServiceImpl totPboardListCnt totPboardListCnt->" + totPboardListCnt);
		return totPboardListCnt;
	}
	// 게시글 리스트
	@Override
	public List<BoardProject> getProjectBoardList(Long userNo, Long projectNo, int start, int end) {
	    log.info("KdwProjectBoardServiceImpl getProjectBoardList start...");
	    List<BoardProject> pboardList = pboardDao.getProjectBoardList(userNo, projectNo, start, end);
	    System.out.println("KdwProjectBoardServiceImpl getProjectBoardList pboardList.size(): " + pboardList.size());
	    return pboardList;
	}
	
	// ! ========== 검색기능 ========== !
	// 검색한 게시글 개수
	@Override
	public int searchPboardListCnt(Long userNo, Long projectNo, String keyword, String type) {
		log.info("KdwProjectBoardServiceImpl searchPboardListCnt start...");
		int searchPboardListCnt = pboardDao.searchPboardListCnt(userNo, projectNo, keyword, type);
		log.info("KdwProjectBoardServiceImpl searchPboardListCnt Cnt->" + searchPboardListCnt);
		return searchPboardListCnt;
	}
	// 검색한 게시글 리스트
	@Override
	public List<BoardProject> searchProjectBoardList(Long userNo, Long projectNo, String keyword, String type, int start, int end) {
	    log.info("KdwProjectBoardServiceImpl searchProjectBoardList start...");
	    List<BoardProject> searchProjectBoardList = pboardDao.searchProjectBoardList(userNo, projectNo, keyword, type, start, end);
	    System.out.println("KdwProjectBoardServiceImpl searchProjectBoardList.size(): " + searchProjectBoardList.size());
	    return searchProjectBoardList;
	}
	
	// ! ========== 글쓰기 & 업로드 ==========
	@Override
	public void writeSave(BoardProject boardProject, MultipartFile[] files, String path) {
		log.info("KdwProjectBoardServiceImpl sendMsg start...");

		// 쪽지 정보 DB에 저장
		pboardDao.writeSave(boardProject);

		// 파일 업로드 처리
		if (files != null && files.length > 0) {
		    File file = new File(path);

		    if (!file.exists()) {
		        boolean check = file.mkdirs();

		        // 디렉토리 생성에 실패한 경우 콘솔에 출력
		        if (!check) {
		            System.err.println("Failed to create directory: " + path);
		        }
		    }
		    for (MultipartFile f : files) {
		        if (!f.isEmpty()) {
		            System.out.println("file => " + f.getOriginalFilename());

		            try {
		                // HDD에 저장
		                String fileName = saveFile(f, path);
		                
		                // DB에 저장
		                BoardProjectFile boardProjectFile = new BoardProjectFile();
		                boardProjectFile.setPboard_no(boardProject.getPboard_no()); // 게시글 번호 설정
		                boardProjectFile.setPboard_file_name(fileName); // 첨부파일 가공 식별 저장명 설정
		                boardProjectFile.setPboard_file_user_name(f.getOriginalFilename()); // 첨부파일 오리지날 유저명 설정
		                System.out.println("KdwProjectBoardServiceImpl writeSave boardProjectFile: " + boardProjectFile);
		                // DB에 파일 정보 저장
		                pboardDao.savePboardFile(boardProjectFile);
		            } catch (IOException e) {
		                e.printStackTrace();
		                System.err.println("Error saving file: " + e.getMessage());
		            }
		        }
		    }
		}
		log.info("KdwProjectBoardServiceImpl sendMsg end...");
	}
	
	// 파일 저장
	public String saveFile(MultipartFile multipartFile, String path) throws IOException {
	    String originalFilename = multipartFile.getOriginalFilename();
	    String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
	    String fileName = UUID.randomUUID().toString() + fileExtension;

	    File targetFile = new File(path, fileName);
	    Files.copy(multipartFile.getInputStream(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

	    return fileName;
	}
	

	
	// 글 수정
	@Override
	public void updateSave(BoardProject boardProject, MultipartFile[] files, String path) {
		log.info("KdwProjectBoardServiceImpl updateSave start...");

		// 쪽지 정보 DB에 저장
		pboardDao.updateSave(boardProject);

		// 파일 업로드 처리
		if (files != null && files.length > 0) {
		    File file = new File(path);

		    if (!file.exists()) {
		        boolean check = file.mkdirs();

		        // 디렉토리 생성에 실패한 경우 콘솔에 출력
		        if (!check) {
		            System.err.println("Failed to create directory: " + path);
		        }
		    }
		    for (MultipartFile f : files) {
		        if (!f.isEmpty()) {
		            System.out.println("file => " + f.getOriginalFilename());

		            try {
		                // HDD에 저장
		                String fileName = saveFile(f, path);
		                File uploadedFile = new File(path, fileName); // 파일 저장 후 생성된 파일 객체
		                
		                // DB에 저장
		                BoardProjectFile boardProjectFile = new BoardProjectFile();
		                boardProjectFile.setPboard_no(boardProject.getPboard_no()); // 게시글 번호 설정
		                boardProjectFile.setPboard_file_name(fileName); // 첨부파일 가공 식별 저장명 설정
		                boardProjectFile.setPboard_file_user_name(f.getOriginalFilename()); // 첨부파일 오리지날 유저명 설정
		                System.out.println("KdwProjectBoardServiceImpl updateSave boardProjectFile: " + boardProjectFile);
		                // DB에 파일 정보 저장
		                pboardDao.savePboardFile(boardProjectFile);
		            } catch (IOException e) {
		                e.printStackTrace();
		                System.err.println("Error saving file: " + e.getMessage());
		            }
		        }
		    }
		}
		log.info("KdwProjectBoardServiceImpl updateSave end...");
		
	}
	
	// 파일 목록조회
	@Override
	public BoardProjectFile getBoardProjectFiles(Long pboardNo, int pboardFileNo) {
        log.info("KdwProjectBoardServiceImpl getBoardProjectFiles start...");
        BoardProjectFile boardWithFiles = pboardDao.getBoardFiles(pboardNo, pboardFileNo);
        log.info("KdwProjectBoardServiceImpl getBoardProjectFiles boardWithFiles size: " + boardWithFiles);
        return boardWithFiles;
	}
	// 파일 삭제
	@Override
	public void deleteFilesByPboardNo(Long pboardNo, int pboardFileNo) {
		log.info("KdwProjectBoardServiceImpl deleteFilesByPboardNo start...");
		pboardDao.deleteFilesByPboardNo(pboardNo, pboardFileNo);
	}
	// 파일 사이즈 가져오기
	@Override
	public long getFileSize(String filePath) {
	    try {
	        return Files.size(Paths.get(filePath));
	    } catch (IOException e) {
	        e.printStackTrace();
	        return -1; // 파일 크기를 얻을 수 없는 경우
	    }
	}
	// 파일 상세정보(사이즈)
	@Override
	public BoardProjectFile getFileDetails(Long pboardNo, int pboardFileNo) {
		BoardProjectFile getFiledetails = pboardDao.getFileDetails(pboardNo, pboardFileNo);
		return getFiledetails;
	}
	

}
