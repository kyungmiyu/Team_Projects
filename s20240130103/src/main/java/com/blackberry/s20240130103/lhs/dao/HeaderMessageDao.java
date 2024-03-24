package com.blackberry.s20240130103.lhs.dao;

import java.util.List;

import com.blackberry.s20240130103.kdw.model.Message;

public interface HeaderMessageDao {

	List<Message> getThreeMessage(String userNo);

	int selectNoReadMessageCnt(String userNo);

	int selectNoReadAddressRequestCnt(String userNo);

	List<String> getFourAddressRequestUserName(String userNo);

}
