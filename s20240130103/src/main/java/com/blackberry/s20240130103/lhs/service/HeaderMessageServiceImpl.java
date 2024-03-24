package com.blackberry.s20240130103.lhs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blackberry.s20240130103.kdw.model.Message;
import com.blackberry.s20240130103.lhs.dao.HeaderMessageDao;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class HeaderMessageServiceImpl implements HeaderMessageService {

	private final HeaderMessageDao headerMessageDao;
	
	@Override
	public List<Message> getThreeMessage(String userNo) {
		List<Message> headerThreeMessageList = headerMessageDao.getThreeMessage(userNo);
		return headerThreeMessageList;
	}
	
	@Override
	public int selectNoReadMessageCnt(String userNo) {
		int noReadMessageCnt = headerMessageDao.selectNoReadMessageCnt(userNo);
		return noReadMessageCnt;
	}
	
	@Override
	public int selectNoReadAddressRequestCnt(String userNo) {
		int noReadAddressRequestCnt = headerMessageDao.selectNoReadAddressRequestCnt(userNo);
		return noReadAddressRequestCnt;
	}
	
	@Override
	public List<String> getFourAddressRequestUserName(String userNo) {
		List<String> fourAddressRequestUserName = headerMessageDao.getFourAddressRequestUserName(userNo);
		return fourAddressRequestUserName;
	}

}
