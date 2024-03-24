package com.blackberry.s20240130103.yhs.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.blackberry.s20240130103.yhs.model.Ask;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor

public class AskDaoImpl implements AskDao {
	// Mybatis DB 연동 
    private final SqlSession session;
	@Override
	public int totalAsk(Ask ask) {
		int totAskCount = 0;
		System.out.println("AskDaoImpl Start total..." );

		try {
			totAskCount = session.selectOne("askTotal",ask);
			System.out.println("AskDaoImpl totalAsk totAskCount->" +totAskCount);
		} catch (Exception e) {
			System.out.println("AskDaoImpl totalAsk Exception->"+e.getMessage());
		}
		return totAskCount;	
	}
	
	@Override
	public List<Ask> listAsk(Ask ask) {
		List<Ask> askList = null;
		System.out.println("AskDaoImpl listAsk Start ..." );
		try {
			//                             Map ID        parameter
			askList = session.selectList("yhsAskListAll", ask);
			System.out.println("AskDaoImpl listAsk askList.size()->"+askList.size());
		} catch (Exception e) {
			System.out.println("AskDaoImpl listAsk e.getMessage()->"+e.getMessage());
		}
		return askList;
	}
	
	
	 @Override 
	 public Ask askContent(Ask ask) {
	 System.out.println("AskDaoImpl askContent Start..."); 
	 System.out.println("AskDaoImpl askConten ask->"+ask);

	 Ask askContent = session.selectOne("yhsAskContent", ask);
	 System.out.println("AskDaoImpl askContent -> " + askContent); 
	 return askContent; 
	 }
	 
	@Override
	public List<Ask> askListSearch(Ask ask) {
		List<Ask> askListSearch = null;
		System.out.println("AskDaoImpl askListSearch Start...");
			
		try {
			askListSearch = session.selectList("yhsAskListSearch", ask);
		} catch (Exception e) {
			System.out.println("AskDaoImpl askListSearch Exception ->" + e.getMessage());
		}
		return askListSearch;
	}

	@Override
	public int insertAsk(Ask ask) {
		int result = 0;
		System.out.println("AskDaoImpl insert Start..." );
		try {
			result = session.insert("yhsInsertAsk",ask);
			System.out.println("AskDaoImpl insert ask->" + ask);
		} catch (Exception e) {
			System.out.println("AskDaoImpl insert Exception->"+e.getMessage());
		}
		return result;
	}
	
	@Override
	public int deleteAsk(int admin_no) {
		System.out.println("AskDaoImpl delete start..");
		int result = 0;
		System.out.println("AskDaoImpl delete empno->"+admin_no);
		try {
			result  = session.delete("yhsDeleteAsk",admin_no);
			System.out.println("AskDaoImpl delete result->"+result);
		} catch (Exception e) {
			System.out.println("AskDaoImpl delete Exception->"+e.getMessage());
		}
		return result;
	}
	
	@Override
	public Ask selectAskResponse(Ask ask) {
		Ask askResponse = session.selectOne("yhsSelectAskResponseOne", ask);
		return askResponse;
	}
	 
}
