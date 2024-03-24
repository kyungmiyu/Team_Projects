package com.blackberry.s20240130103.lhs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blackberry.s20240130103.kph.model.KphUsers;
import com.blackberry.s20240130103.lhs.dao.AddressDao;
import com.blackberry.s20240130103.lhs.model.Address;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

	private final AddressDao addressDao;
	
	@Override
	public int addresschkcnt(Address address) {
		int result = addressDao.addresschkcnt(address);
		return result;
	}
	
	@Override
	public int addressAdd(Address address) {
		int result = addressDao.addressAdd(address);
		return result;
	}

	@Override
	public List<KphUsers> addressRequestList(long addressUserNo) {
		List<KphUsers> addressRequestList = addressDao.addressRequestList(addressUserNo);
		return addressRequestList;
	}
	
	@Override
	public List<KphUsers> addressResponseList(long addressUserNo) {
		List<KphUsers> addressResponseList = addressDao.addressResponseList(addressUserNo);
		return addressResponseList;
	}
	
	@Override
	public int addressResponsePermit(Address address) {
		int result = addressDao.addressResponsePermit(address);
		return result;
	}
	
	@Override
	public int addressResponseDeny(Address address) {
		int result = addressDao.addressDelete(address);
		return result;
	}
	
	@Override
	public int addressRequestDelete(Address address) {
		int result = addressDao.addressDelete(address);
		return result;
	}
}
