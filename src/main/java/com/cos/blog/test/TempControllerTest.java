package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {
	
	
	// http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome");

		return "/home.html";
	}
	
	@GetMapping("/temp/jsp")
	public String tempJSP() {
	      // prefix: /WEB-INF/views/
	     //  suffix: .jsp
		 //  풀네임 : /WEB-INF/views/File.jsp
		return "File";
	}
}
