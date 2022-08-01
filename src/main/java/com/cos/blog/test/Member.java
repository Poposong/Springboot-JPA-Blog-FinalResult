package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Getter
// @Setter
// @Data : Getter와 Setter 모두 만들어준다.
// @AllArgsConstructor : 생성자를 만들어준다.
// final을 사용하는 이유 ? 데이터의 불변성을 유지하기 위해서
// @RequiredArgsConstructor : 자료형 앞에 final이 붙은 변수들만 생성자로 만들어준다.
// @NoArgsConstructor : 비어있는 생성자

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	private int id;
	private String username;
	private String password;
	private String email;
	
	
	
	
}
