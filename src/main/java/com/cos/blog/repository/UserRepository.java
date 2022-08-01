package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

// DAO
// 자동으로 bean 등록이 된다!!
// @Repository // 생략이 가능하다.
public interface UserRepository extends JpaRepository<User, Integer>{
	// UserRepository 는 User table이 관리하고 User table의 PK는 Integer이다.
	
	// Select * from where username = 1?;
	Optional<User> findByUsername(String username);
	
	
}




////JPA Naming 전략
//	// Select * from user where username = ? AND password = ?;
//	User findByUsernameAndPassword(String username, String password);
//	
//	// Native Query
//	@Query(value= "Select * from user where username = ?1 AND password = ?2", nativeQuery = true)
//	User login(String username, String password);
