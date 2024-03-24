package com.blackberry.s20240130103.lhs.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.blackberry.s20240130103.kdw.model.Message;

import lombok.RequiredArgsConstructor;
@Repository
@RequiredArgsConstructor
public class HeaderMessageDaoImpl implements HeaderMessageDao {
	
	private final SqlSession session;
	
	@Override
	public List<Message> getThreeMessage(String userNo) {
		List<Message> headerThreeMessageList = session.selectList("LhsHeaderThreeMessage", userNo);
		return headerThreeMessageList;
	}
	
	@Override
	public int selectNoReadMessageCnt(String userNo) {
		int noReadMessageCnt = session.selectOne("LhsHeaderNoReadMessageCnt", userNo);
		return noReadMessageCnt;
	}
	
	@Override
	public int selectNoReadAddressRequestCnt(String userNo) {
		int noReadAddressRequestCnt = session.selectOne("LhsHeaderNoReadAddressReqeustCnt",userNo);
		return noReadAddressRequestCnt;
	}
	
	@Override
	public List<String> getFourAddressRequestUserName(String userNo) {
		List<String> fourAddressRequestUserName = session.selectList("LhsHeaderNoReadAddressRequestUserName", userNo);
		return fourAddressRequestUserName;
	}
}
