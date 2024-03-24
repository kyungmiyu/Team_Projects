package com.blackberry.s20240130103.lhs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blackberry.s20240130103.lhs.domain.Comm;
import com.blackberry.s20240130103.lhs.domain.User;
import com.blackberry.s20240130103.lhs.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public void joinUser(User user) {
		user.setUser_pw(bCryptPasswordEncoder.encode(user.getUser_pw()));
		userRepository.userJoin(user);
	}
	
	@Override
	public Optional<User> findUserById(String id) {
		return userRepository.findUserById(id);
	}
	
	@Override
	public int joinIdChk(String id) {
		Optional<User> userOp = userRepository.findUserById(id);
		if(userOp.isPresent()) {
			return 1;
		}else {
			return 0;
		}
	}
	
	@Override
	public int loginChk(User user) {
		int result = 0;
		Optional<User> userOp = userRepository.findUserById(user.getUser_id());
		if(userOp.isPresent()) { //찾은 유저가 있는지
			if(bCryptPasswordEncoder.matches(user.getUser_pw(), userOp.get().getUser_pw())) { //찾은 유저의 암호화된 비밀번호가 입력한 비밀번호와 일치하는지
				User user2 = userOp.get();
				if(user2.getUser_delete_chk()==0) { //찾은 유저의 삭제 상태가 0(삭제X)인지
					Comm comm = userRepository.findCommByBigMid(user2.getUser_rank_big(),user2.getUser_rank_mid());
					user.setUser_no(user2.getUser_no());
					user.setUser_profile(user2.getUser_profile());
					user.setUser_name(user2.getUser_name());
					user.setUser_nic(user2.getUser_nic());
					user.setComm_content(comm.getComm_content());
					result = 1;
					if(user.getComm_content().equals("관리자")) {
						result = 2;
					}
				}
			}else {
				System.out.println("UserServiceImpl 로그인 실패");
				System.out.println(user);
				result = 0;
			}
		}else {
			result = 0;
		}
		return result;
	}
	
	@Override
	public User findUserByNo(String userNo) {
		return userRepository.findUserByNo(userNo);
	}

	@Override
	public void updateUser(User user, String userNo) {
		userRepository.updateUser(user,userNo);
	}
	
	@Override
	public int updatePasswordUser(String userNo, String oldPassword, String newPassword) {
		User user = userRepository.findUserByNo(userNo);
		if(bCryptPasswordEncoder.matches(oldPassword, user.getUser_pw())) {
			userRepository.updatePasswordUser(userNo,bCryptPasswordEncoder.encode(newPassword));
			return 1;
		}else {
			return 0;
		}
	}
	
	@Override
	public int chkPasswordUser(String passwd, String userNo) {
		User user = userRepository.findUserByNo(userNo);
		if(bCryptPasswordEncoder.matches(passwd, user.getUser_pw())) {
			userRepository.deleteUser(userNo);
			return 1;
		}else {
			return 0;
		}
	}
	
	@Override
	public List<User> findIdByemail(String email) {
		List<User> user = userRepository.findIdByemail(email);
		if(!user.isEmpty()) {
			return user;
		}else {
			return null;
		}
	}
	
	@Override
	public Optional<User> findPassByIdEmail(User user) {
		Optional<User> userOp = userRepository.findPassByIdEmail(user);
		return userOp;
	}
	
	@Override
	public int passwordChange(User user) {
		User finduser = userRepository.findUserByNo(user.getUser_no().toString());
		if(!bCryptPasswordEncoder.matches(user.getUser_pw(), finduser.getUser_pw())) {
			userRepository.updatePasswordUser(user.getUser_no().toString(), bCryptPasswordEncoder.encode(user.getUser_pw()));
			return 1;
		}else {
			return 0;
		}
	}
	
}
