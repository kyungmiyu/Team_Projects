package com.blackberry.s20240130103.lhs.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.blackberry.s20240130103.kph.model.KphUsers;
import com.blackberry.s20240130103.lhs.model.Address;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AddressDaoImpl implements AddressDao {
	
	private final SqlSession session;

	@Override
	public int addresschkcnt(Address address) {
		int result = session.selectOne("lhsAddresschkcnt", address);
		return result;
	}
	
	@Override
	public int addressAdd(Address address) {
		int result = session.insert("lhsAddressAdd", address);
		return result;
	}
	
	@Override
	public List<KphUsers> addressRequestList(long addressUserNo) {
		List<KphUsers> addressRequestList = session.selectList("lhsAddressRequestList", addressUserNo);
		return addressRequestList;
	}
	
	@Override
	public List<KphUsers> addressResponseList(long addressUserNo) {
		List<KphUsers> addressResponseList = session.selectList("lhsAddressResponseList", addressUserNo);
		return addressResponseList;
	}
	
	@Override
	public int addressResponsePermit(Address address) {
		int result = session.update("lhsAddressResponsePermit", address);
		return result;
	}
	
	@Override
	public int addressDelete(Address address) {
		int result = session.delete("lhsAddressDelete", address);
		if(result == 0) {
			result = session.delete("lhsAddressDelete2", address);
		}
		return result;
	}

}
