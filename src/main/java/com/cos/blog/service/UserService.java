package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해준다. IOC를 해준다.
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder; // ~ > DI가 되면서 의존성 주입이 가능해진다.
	
	@Transactional // 이때 회원가입() 메소드에서 하나의 트랜젝션에서라도 오류가 발생하면 rollback한다.
	public void 회원가입(User user) {
		
		String rawPassword = user.getPassword(); // 1234 원문
		String encPassword = encoder.encode(rawPassword); // 해쉬화	
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user); //  여기서 오류가 발생하면 바로 GlobalExceptionHandler class 를 호출한다.
	}

	
	

}


/*
 //	@Transactional(readOnly = true)  // Select 할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료 (정합성 유지)
//	public User 로그인(User user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());  여기서 오류가 발생하면 바로 GlobalExceptionHandler class 를 호출한다.
//		
//	}
 */
