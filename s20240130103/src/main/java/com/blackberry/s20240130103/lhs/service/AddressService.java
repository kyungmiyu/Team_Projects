package com.blackberry.s20240130103.lhs.service;

import java.util.List;

import com.blackberry.s20240130103.kph.model.KphUsers;
import com.blackberry.s20240130103.lhs.model.Address;

public interface AddressService {

	int addresschkcnt(Address address);

	int addressAdd(Address address);

	List<KphUsers> addressRequestList(long addressUserNo);

	List<KphUsers> addressResponseList(long addressUserNo);

	int addressResponsePermit(Address address);

	int addressResponseDeny(Address address);

	int addressRequestDelete(Address address);

}
