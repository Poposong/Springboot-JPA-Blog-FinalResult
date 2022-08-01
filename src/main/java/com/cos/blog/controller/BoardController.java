package com.cos.blog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.config.auth.PrincipalDetail;

@Controller
public class BoardController {
	@GetMapping({"","/"})
	public String index(@AuthenticationPrincipal PrincipalDetail principal) { // 컨트롤러에서 세션을 어떻게 찾는지?
    //  /WEB-INF/views/index.jsp
		// 앞으로 세션은 @AuthenticationPrincipal 어노테이션을 사용해서 찾는다.
		// Spring의 Security에서 로그아웃은 /logout이 기본값이다.
		System.out.println("로그인 사용자 아이디 : "+principal.getUsername());
		return "index"; 
	}
}
