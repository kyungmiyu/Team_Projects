package com.blackberry.s20240130103.ykm.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.blackberry.s20240130103.ykm.model.YkmBoardComm;
import com.blackberry.s20240130103.ykm.model.YkmBoardCommFile;
import com.blackberry.s20240130103.ykm.model.YkmBoardCommReply;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class YkmBoardDaoImpl implements YkmBoardDao {

	private final SqlSession session;

	/* 스터디 게시판 */
	
	// 스터디 게시판 게시글 조회
	@Override
	public List<YkmBoardComm> getPostList(YkmBoardComm ykmBoardComm) {
		System.out.println("YkmBoardDaoImpl getPostList start ---*");
		List<YkmBoardComm> getPostList = session.selectList("ykmGetPostList", ykmBoardComm);
		//System.out.println("YkmBoardDaoImpl getPostList result --> " + getPostList.size());

		return getPostList;
	}

	// 게시글 보여주기
	@Override
	public YkmBoardComm getPost(int cboard_no) {
		System.out.println("YkmBoardDaoImpl getPost start ---*");
		YkmBoardComm getPost = session.selectOne("ykmGetPost", cboard_no);
		// System.out.println("getPost result --> "+getPost);
		return getPost;
	}

	// 글 등록
	@Override
	public int writePost(YkmBoardComm ykmBoardComm) {
		System.out.println("YkmBoardDaoImpl writePost start---*");
		int result = session.insert("ykmWritePost", ykmBoardComm);
		System.out.println("YkmBoardDaoImpl writePost result=>" + result);
		return result;
	}

	// 파일 업로드 (저장)
	@Override
	public int saveFileList(YkmBoardCommFile ykmBoardCommFile) {
		System.out.println("YkmBoardDaoImpl saveFileList ykmBoardCommFile : "+ykmBoardCommFile);
		int result = session.insert("ykmSaveFileList", ykmBoardCommFile);
		return result;
	}
	
	
	// 글 수정
	@Override
	public int updatePost(YkmBoardComm ykmBoardComm) {
		System.out.println("YkmBoardDaoImpl updatePost start ---*");
		int updatePost = session.update("ykmUpdatePost", ykmBoardComm);
		return updatePost;
	}

	// 글 삭제
	@Override
	public int deletePost(int cboard_no) {
		System.out.println("YkmBoardDaoImpl deletePost start ---*");
		int deletePost = session.delete("ykmDeletePost", cboard_no);
		//System.out.println("YkmBoardDaoImpl deletePost deletePost --> " + deletePost);
		return deletePost;
	}

	// 게시글 조회수 카운드
	@Override
	public int increseViewcount(int cboard_no) {
		return session.update("ykmIncreseViewCount", cboard_no);
	}

	// 모집중, 모집완료 수정
	@Override
	public int updateRecruitment(YkmBoardComm ykmBoardComm) {
		System.out.println("YkmBoardDaoImpl updateRecruitment start ---*");
		return session.update("ykmUpdateRecruitment", ykmBoardComm);
	}
	
	// 페이징, 전체 게시글 카운트 조회
	@Override
	public int getTotalCount(YkmBoardComm ykmBoardComm) {
		System.out.println("YkmBoardDaoImpl getTotalCount start ---*");
		int result = session.selectOne("ykmGetTotalCount", ykmBoardComm);
		//System.out.println("YkmBoardDaoImpl getTotalCount result : " + result );
		return result;
	}
	
	// 업로드 된 파일 정보 불러오기
	@Override
	public List<YkmBoardCommFile> getFileList(int cboard_no) {
		System.out.println("YkmBoardDaoImpl getFileList start ---*");
		List<YkmBoardCommFile> getFileList = session.selectList("ykmGetFileList",cboard_no);
		System.out.println("YkmBoardDaoImpl getFileList :"+getFileList.size());
        return getFileList;
	}
	
	// 파일 삭제
	@Override
	public void deleteBoardFile(YkmBoardCommFile file) {
		int result = session.delete("ykmDeleteBoardFile",file);
	}
	
	/* 대댓글 */
	@Override
	public int writeReply(YkmBoardCommReply ykmBoardCommReply) {
		System.out.println("YkmBoardDaoImpl writeReply start ---*");
		int result = session.insert("ykmWriteReply", ykmBoardCommReply);
		System.out.println("YkmBoardDaoImpl writeReply : "+ result);
		return result;
	}

	/* 스터디 댓글 REST API */
	
	// 댓글 리스트 조회
	@Override
	public List<YkmBoardCommReply> getCommentList(int cboard_no) {
		System.out.println("YkmBoardDaoImpl getCommentList start ---*");
		List<YkmBoardCommReply> getCommentList = session.selectList("ykmGetCommentList", cboard_no);
		System.out.println("YkmBoardDaoImpl getCommentList finish ---*");
		return getCommentList;
	}

	// 댓글 등록
	@Override
	public int writeComment(YkmBoardCommReply ykmBoardCommReply) {
		System.out.println("YkmBoardDaoImpl writeComment start ---*");
		int result = session.insert("ykmWriteComment", ykmBoardCommReply);
		//System.out.println("YkmBoardDaoImpl writeComment --> " + result);
		//System.out.println("YkmBoardDaoImpl writeComment finish ---*");
		return result;
	}

	// 댓글 삭제
	@Override
	public int deleteComment(int creply_no) {
		System.out.println("ykmBoardDaoImpl deleteComment start ---*");
		int result = session.delete("ykmDeleteComment", creply_no);
		return result;
	}

	// 댓글 수정
	@Override
	public int updateComment(YkmBoardCommReply ykmBoardCommReply) {
		return session.update("ykmUpdateComment", ykmBoardCommReply);
	}

	// 댓글 개수 카운트
	@Override
	public int countComment(int cboard_no) {
		System.out.println("YkmBoardDaoImpl countComment result ==> "+cboard_no);
		Integer result = session.selectOne("ykmCountComment", cboard_no);
		return (result != null) ? result : 0;
	}
	
	@Override
	public YkmBoardCommReply getReplyNo(YkmBoardCommReply ykmBoardCommReply) {
		YkmBoardCommReply replyValue = session.selectOne("ykmGetReplyNo", ykmBoardCommReply);
		return replyValue;
	}
	
	@Override
	public int updateGroup(YkmBoardCommReply ykmBoardCommReply) {
		int result = session.update("ykmUpdateGroup", ykmBoardCommReply);
		return result;
	}


	
	
	/* 공모전 게시판 */
	
	// 공모전 전체 게시글 리스트 조회
	@Override
	public List<YkmBoardComm> getCntPostList(YkmBoardComm ykmBoardComm) {
		System.out.println("YkmBoardDaoImpl getCntPostList start ---*");
		List<YkmBoardComm> getCntPostList = session.selectList("ykmGetCntPostList", ykmBoardComm);
		return getCntPostList;
	}

	// 전체 게시글 카운트 (페이징)
	@Override
	public int getCntTotalCount(YkmBoardComm ykmBoardComm) {
		System.out.println("YkmBoardDaoImpl getCntTotalCount start ---*");
		int result = session.selectOne("ykmGetCntTotalCount", ykmBoardComm);
		System.out.println("YkmBoardDaoImpl ykmGetCntTotalCount result " + result);
		return result;
	}

	// 게시글 작성
	@Override
	public int writeCntPost(YkmBoardComm ykmBoardComm) {
		System.out.println("YkmBoardDaoImpl writeCntPost start ---*");
		int writeCntPost = session.insert("ykmWriteCntPost", ykmBoardComm);
		return writeCntPost;
	}

}
