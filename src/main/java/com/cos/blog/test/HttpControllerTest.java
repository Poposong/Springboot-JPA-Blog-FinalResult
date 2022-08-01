package com.cos.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

// @Controller : 사용자가 요청 → 응답(HTML파일)
// @RestController : 사용자가 요청 → 응답(Data)

@RestController
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest : ";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = new Member(1, "code_zero", "1234", "aaa1234@naver.com");
		System.out.println(TAG + "getter : "+m.getId());
		m.setId(5000);
		System.out.println(TAG + "getter : "+m.getId());
		return "lombok test 완료";
	}

	// http://localhost:8080/http/get (select)
	@GetMapping("/http/get") /*query string 형식으로 넘긴다. id=1234&username=code_zero&password=0000&email=aaa1234@naver.com */
	public String getTest(Member m) {
		return "get요청";	
	}
	
	// http://localhost:8080/http/post (Insert)
	@PostMapping("/http/post")
	public String postTest(Member m) {
		return "post 요청: "+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail() ;
	}
	

	// http://localhost:8080/http/put (update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청: "+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail() ;
	}
	
	// http://localhost:8080/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
