package com.blackberry.s20240130103.lhs.service;

import java.util.List;
import java.util.Optional;

import com.blackberry.s20240130103.lhs.domain.User;

public interface UserService {
	void joinUser(User user);

	Optional<User> findUserById(String id);

	int joinIdChk(String id);

	int loginChk(User user);

	User findUserByNo(String userNo);

	void updateUser(User user, String userNo);

	int updatePasswordUser(String userNo, String oldPassword, String newPassword);

	int chkPasswordUser(String passwd, String userNo);

	List<User> findIdByemail(String email);

	Optional<User> findPassByIdEmail(User user);

	int passwordChange(User user);

}
