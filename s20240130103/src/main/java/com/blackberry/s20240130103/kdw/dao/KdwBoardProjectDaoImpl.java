package com.blackberry.s20240130103.kdw.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.blackberry.s20240130103.kdw.model.BoardProject;
import com.blackberry.s20240130103.kdw.model.BoardProjectFile;
import com.blackberry.s20240130103.kdw.model.MessageFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class KdwBoardProjectDaoImpl implements KdwBoardProjectDao {
	
	private final SqlSession session;
	
	// 게시글 개수
	@Override
	public int totPboardListCnt(Long userNo, Long projectNo) {
        int totPboardListCnt = 0;
        try {
	        Map<String, Object> paramMap = new HashMap<>();
	        paramMap.put("userNo", userNo);
	        paramMap.put("projectNo", projectNo);
        	
        	totPboardListCnt = session.selectOne("totPboardListCnt", paramMap);
            System.out.println("KdwBoardProjectDaoImpl totPboardListCnt totReceiveMsgCnt->" + totPboardListCnt);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("KdwBoardProjectDaoImpl totPboardListCnt Exception ->" + e.getMessage());
        }
        return totPboardListCnt;
	}
	
	// 게시글 리스트
	@Override
	public List<BoardProject> getProjectBoardList(Long userNo, Long projectNo, int start, int end) {
	    List<BoardProject> pboardList = null;
	    System.out.println("KdwBoardProjectDaoImpl getProjectBoardList start...");

	    try {
	        Map<String, Object> paramMap = new HashMap<>();
	        paramMap.put("userNo", userNo);
	        paramMap.put("projectNo", projectNo);
	        paramMap.put("start", start);
	        paramMap.put("end", end);

	        pboardList = session.selectList("kdwPboardListAll", paramMap);
	        System.out.println("KdwBoardProjectDaoImpl getProjectBoardList pboardList.size()->" + pboardList.size());
	        System.out.println("KdwBoardProjectDaoImpl getProjectBoardList start&end: " + start +", "+ end);
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("KdwBoardProjectDaoImpl getProjectBoardList e.getMessage()->" + e.getMessage());
	    }
	    return pboardList;
	}
	
	// ! ========== 검색기능 ========== !
	// 검색한 게시글 개수
	@Override
	public int searchPboardListCnt(Long userNo, Long projectNo, String keyword, String type) {
		System.out.println("KdwBoardProjectDaoImpl searchPboardListCnt Start...");
		int searchResultCount = 0;
		try {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("userNo", userNo);
			paramMap.put("projectNo", projectNo);
			paramMap.put("keyword", keyword);
			paramMap.put("type", type);
			
			searchResultCount = session.selectOne("kdwSearchPboardListCnt", paramMap);
			System.out.println("KdwBoardProjectDaoImpl searchPboardListCnt searchResultCount: " + searchResultCount);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("KdwBoardProjectDaoImpl searchPboardListCnt searchResultCount Error", e.getMessage());
		}
		return searchResultCount;
	}
	// 검색한 게시글 리스트
	@Override
	public List<BoardProject> searchProjectBoardList(Long userNo, Long projectNo, String keyword, String type, int start, int end) {
		System.out.println("KdwBoardProjectDaoImpl searchProjectBoardList Start...");
		List<BoardProject> searchProjectBoardList = null;
		try {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("userNo", userNo);
			paramMap.put("projectNo", projectNo);
			paramMap.put("keyword", keyword);
			paramMap.put("type", type);
			paramMap.put("start", start);
			paramMap.put("end", end);
			searchProjectBoardList = session.selectList("kdwSearchPboardList", paramMap);
			System.out.println("KdwBoardProjectDaoImpl searchProjectBoardList userNo:" + userNo);
			System.out.println("KdwBoardProjectDaoImpl searchProjectBoardList projectNo:" + projectNo);
			System.out.println("KdwBoardProjectDaoImpl searchProjectBoardList keyword:" + keyword);
			System.out.println("KdwBoardProjectDaoImpl searchProjectBoardList type:" + type);
			System.out.println("KdwBoardProjectDaoImpl searchProjectBoardList start:" + start);
			System.out.println("KdwBoardProjectDaoImpl searchProjectBoardList end:" + end);
			System.out.println("KdwBoardProjectDaoImpl searchProjectBoardList searchPboardList.size(): " + searchProjectBoardList.size());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("KdwBoardProjectDaoImpl searchProjectBoardList searchPboardList Error", e.getMessage());
		}
		return searchProjectBoardList;
	}
	// 글쓰기
	@Override
	public void writeSave(BoardProject boardProject) {
    	System.out.println("KdwBoardProjectDaoImpl writeSave start...");
        try {
			session.insert("kdwWriteSave", boardProject);
			System.out.println("KdwBoardProjectDaoImpl writeSave success boardProject: " + boardProject);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("KdwBoardProjectDaoImpl writeSave error boardProject: " + boardProject);
		}
		
	}
	// 첨부파일 업로드
	@Override
	public void savePboardFile(BoardProjectFile boardProjectFile) {
        try {
            session.insert("kdwSavePboardFile", boardProjectFile);
            System.out.println("KdwBoardProjectDaoImpl savePboardFile success: " + boardProjectFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	// 글 수정
	@Override
	public void updateSave(BoardProject boardProject) {
	    log.info("KdwBoardProjectDaoImpl updateSave start...");
	    try {
	    // 게시글 정보 업데이트
	    session.update("kdwUpdateSave", boardProject);
	    System.out.println("KdwBoardProjectDaoImpl updateSave success: " + boardProject);
	    } catch (Exception e) {
	    	log.info("KdwBoardProjectDaoImpl updateSave end...");
	    	e.printStackTrace();
	    }
	}
	
	// 파일 첨부목록
	@Override
	public BoardProjectFile getBoardFiles(Long pboardNo, int pboardFileNo) {
        log.info("KdwBoardProjectDaoImpl getBoardFiles start...");
        BoardProjectFile files = null;
        try {
        	Map<String, Object> paramMap = new HashMap<>();
        	paramMap.put("pboardNo", pboardNo);
        	paramMap.put("pboardFileNo", pboardFileNo);
            files = session.selectOne("kdwGetBoardWithFiles", paramMap);
            
            System.out.println("KdwBoardProjectDaoImpl getBoardFiles pboardNo: " + pboardNo);
            System.out.println("KdwBoardProjectDaoImpl getBoardFiles pboardFileNo: " + pboardFileNo);
        } catch (Exception e) {
            log.error("Error getting board with files: {}", e.getMessage());
            e.printStackTrace();
        }
        return files;
	}
	// 파일 삭제
	@Override
	public void deleteFilesByPboardNo(Long pboardNo, int pboardFileNo) {
	    System.out.println("KdwBoardProjectDaoImpl deleteFilesByPboardNo start...");
	    
	    try {
        	Map<String, Object> paramMap = new HashMap<>();
        	paramMap.put("pboardNo", pboardNo);
        	paramMap.put("pboardFileNo", pboardFileNo);
        	
	        session.delete("kdwdeleteFilesByPboardNo", paramMap);
	        System.out.println("KdwBoardProjectDaoImpl deleteFilesByPboardNo pboardNo" + pboardNo);
	        System.out.println("KdwBoardProjectDaoImpl deleteFilesByPboardNo pboardFileNo: " + pboardFileNo);
	    } catch (Exception e) {
	    	System.out.println("KdwBoardProjectDaoImpl deleteFilesByPboardNo Error pboardNo: " + pboardNo);
	        e.printStackTrace();
	    }
	    
	}
	// 파일 상세정보(사이즈)
	@Override
	public BoardProjectFile getFileDetails(Long pboardNo, int pboardFileNo) {
        log.info("KdwBoardProjectDaoImpl getFileDetails start...");
        BoardProjectFile fileDetails = null;
        try {
        	Map<String, Object> paramMap = new HashMap<>();
        	paramMap.put("pboardNo", pboardNo);
        	paramMap.put("pboardFileNo", pboardFileNo);
        	fileDetails = session.selectOne("kdwGetFileDetails", paramMap);
            
        } catch (Exception e) {
            log.error("Error getting board with files: {}", e.getMessage());
            e.printStackTrace();
        }
        return fileDetails;
	}


	

}
