package com.blackberry.s20240130103.ykm.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blackberry.s20240130103.ykm.dao.YkmBoardDao;
import com.blackberry.s20240130103.ykm.model.YkmBoardComm;
import com.blackberry.s20240130103.ykm.model.YkmBoardCommFile;
import com.blackberry.s20240130103.ykm.model.YkmBoardCommReply;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class YkmServiceImpl implements YkmService {

	private final YkmBoardDao ykmBoardDao;

	/* 스터디 게시판 */

	// 게시판 글쓰기
	@Override
	public int writePost(YkmBoardComm ykmBoardComm, String studyFilePath, List<MultipartFile> fileList) {
		System.out.println("YkmServiceImpl writePost start---*");
		int result = ykmBoardDao.writePost(ykmBoardComm);
		fileUpload(ykmBoardComm,studyFilePath,fileList);
		return result;
	}
	
	// 파일 업로드
	private void fileUpload(YkmBoardComm ykmBoardComm, String studyFilePath, List<MultipartFile> fileList) {
		for (MultipartFile file : fileList) {
			if (!file.isEmpty()) {

				// 파일 저장
				String fileName = file.getOriginalFilename();

				String fileExt = fileName.substring(fileName.lastIndexOf("."));
				String fileUuid = UUID.randomUUID().toString() + fileExt;

				File files = new File(studyFilePath, fileUuid);
				try {
					Path uploadPath = Paths.get(studyFilePath + File.separator + fileUuid);

					Files.copy(file.getInputStream(), files.toPath(), StandardCopyOption.REPLACE_EXISTING);

					YkmBoardCommFile ykmBoardCommFile = new YkmBoardCommFile();
					System.out.println("YkmServiceImpl writePost ykmBoardCommFile : " + ykmBoardCommFile);

					ykmBoardCommFile.setCboard_no(ykmBoardComm.getCboard_no());
					System.out.println(
							"YkmServiceImpl writePost ykmBoardComm.getCboard_no() " + ykmBoardComm.getCboard_no());

					ykmBoardCommFile.setCboard_file_name(fileUuid);
					ykmBoardCommFile.setCboard_file_user_name(fileName);

					ykmBoardDao.saveFileList(ykmBoardCommFile);

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("YkmServiceImpl saveFileList error : " + e.getMessage());
				}
			}
		}
	}
	
	// 파일 삭제
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

	// 게시판 리스트
	@Override
	public List<YkmBoardComm> getPostList(YkmBoardComm ykmBoardComm) {
		System.out.println("YkmServiceImpl getPostList start---*");
		List<YkmBoardComm> getPostList = ykmBoardDao.getPostList(ykmBoardComm);
		System.out.println("YkmServiceImpl getPostList result --> " + getPostList.size());
		return getPostList;
	}

	// 게시글 보여주기
	@Override
	public YkmBoardComm getPost(int cboard_no) {
		return ykmBoardDao.getPost(cboard_no);
	}

	
		
	// 글 수정
	@Override
	public int updatePost(YkmBoardComm ykmBoardComm, String studyFilePath, List<MultipartFile> fileList,String deleteFileFIles) {
		System.out.println("YkmServiceImpl updatePost start---*");
		fileUpload(ykmBoardComm,studyFilePath,fileList);
		if(deleteFileFIles.length() != 0) {
			String[] deleteFileNum = deleteFileFIles.split("-");
			List<YkmBoardCommFile> uploadfileList = getFileList(ykmBoardComm.getCboard_no());
			for(String str : deleteFileNum) {
				for(YkmBoardCommFile file : uploadfileList) {
					if(file.getCboard_file_cnt() == Integer.parseInt(str)) {
						ykmBoardDao.deleteBoardFile(file);
						fileDelete(file.getCboard_file_name(),studyFilePath);
					}
				}
			}
		}
		return ykmBoardDao.updatePost(ykmBoardComm);
	}

	// 업로드 된 파일 리스트 조회
	@Override
	public List<YkmBoardCommFile> getFileList(int cboard_no) {
		System.out.println("YkmServiceImpl getFileList start---*");
		List<YkmBoardCommFile> getFileList = ykmBoardDao.getFileList(cboard_no);
		return getFileList;
	}
	
	// 글 삭제
	@Override
	public int deletePost(int cboard_no) {
		System.out.println("YkmServiceImpl deletePost start---*");
		return ykmBoardDao.deletePost(cboard_no);
	}

	// 게시판 조회수 카운트
	@Override
	public int increseViewCount(int cboard_no) {
		return ykmBoardDao.increseViewcount(cboard_no);
	}

	// 게시판 모집 상태 변경 (모집중 / 모집완료)
	@Override
	public int updateRecruitment(YkmBoardComm ykmBoardComm) {
		System.out.println("YkmServiceImpl updateRecruitment start---*");
		return ykmBoardDao.updateRecruitment(ykmBoardComm);
	}

	// 페이징 전체 게시글 조회
	@Override
	public int getTotalCount(YkmBoardComm ykmBoardComm) {
		System.out.println("YkmServiceImpl getTotalCount start ---*");
		System.out.println("YkmServiceImpl getTotalCount ykmBoardComm"+ykmBoardComm);
		int result = ykmBoardDao.getTotalCount(ykmBoardComm);
		System.out.println("YkmServiceImpl getTotalCount result : " + result);
		return result;
	}

	/* 댓글 */
	// 댓글 리스트
	@Override
	public List<YkmBoardCommReply> getCommentList(int cboard_no) {
		List<YkmBoardCommReply> getCommentList = ykmBoardDao.getCommentList(cboard_no);
		System.err.println("YkmServiceImpl getCommentList result --> " + getCommentList.size());
		return getCommentList;
	}

	// 댓글 등록
	@Override
	public int writeComment(YkmBoardCommReply ykmBoardCommReply) {
		return ykmBoardDao.writeComment(ykmBoardCommReply);
	}

	// 댓글 삭제
	@Override
	public int deleteComment(int creply_no) {
		return ykmBoardDao.deleteComment(creply_no);
	}

	// 댓글 수정
	@Override
	public int updateComment(YkmBoardCommReply ykmBoardCommReply) {
		return ykmBoardDao.updateComment(ykmBoardCommReply);
	}

	// 댓글 개수 카운트
	@Override
	public int countComment(int cboard_no) {
		return ykmBoardDao.countComment(cboard_no);
	}

	// 대댓글 등록
	@Override
	public int writeReply(YkmBoardCommReply ykmBoardCommReply) {
		System.out.println("YkmServiceImpl writeReply start ---*");
		int result = ykmBoardDao.writeReply(ykmBoardCommReply);
		System.out.println("YkmServiceImpl writeReply : " + result);
		return result;
	}
	
	// 대댓글 번호 조회
	@Override
	public YkmBoardCommReply getReplyNo(YkmBoardCommReply ykmBoardCommReply) {
		YkmBoardCommReply replyValue = ykmBoardDao.getReplyNo(ykmBoardCommReply);
		return replyValue;
	}

	// 대댓글 그룹핑
	@Override
	public int updateGroup(YkmBoardCommReply ykmBoardCommReply) {
		int result = ykmBoardDao.updateGroup(ykmBoardCommReply);
		return result;
	}
		
	/* 공모전 리스트 */
	@Override
	public List<YkmBoardComm> getCntPostList(YkmBoardComm ykmBoardComm) {
		System.out.println("YkmServiceImpl getCntPostList start ---*");
		List<YkmBoardComm> getCntPostList = ykmBoardDao.getCntPostList(ykmBoardComm);
		System.out.println("YkmServiceImpl getCntPostList result");
		return getCntPostList;
	}

	// 공모전 전체 게시글 카운트
	@Override
	public int getCntTotalCount(YkmBoardComm ykmBoardComm) {
		System.out.println("YkmServiceImpl getCntTotalCount start ---*");
		int result = ykmBoardDao.getCntTotalCount(ykmBoardComm);
		System.out.println("YkmServiceImpl getCntTotalCount result : " + result);
		return result;
	}

}