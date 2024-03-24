package com.blackberry.s20240130103.yhs.service;

import java.util.List;

import com.blackberry.s20240130103.yhs.model.Ask;


public interface AskService {

	int totalAsk(Ask ask);
	
	List<Ask> listAsk(Ask ask);
	
	Ask askContent(Ask ask);
	
	int insertAsk(Ask ask);
	
	int deleteAsk(int admin_no);

	Ask selectAskResponse(Ask ask);
	
}
