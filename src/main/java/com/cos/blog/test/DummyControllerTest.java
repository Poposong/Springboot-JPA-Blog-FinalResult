package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	
	@Autowired // 의존성 주입(DI)
	private UserRepository userRepository;
	
	
	
	// Select-1) id에 해당하는 컬럼만 브라우저에서 보여준다.
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
		@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다. id : "+ id);
			}
		});

		return user;
	}
		
	// Select-2) 전체 컬럼을 모두 가져와서 브라우저에서 보여준다.
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	// Select-3) 한 페이지당 2건에 데이터를 리턴받아 볼 예정
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
		Page<User> paging = userRepository.findAll(pageable);
		
		List<User> list = paging.getContent();
		
		return list;
	}
	
	
	// save 함수는 id를 전달하지 않으면 Insert를 해주고
	// id를 전달하면서 해당 id에 대한 데이터가 있으면 update를 해주고
	// id를 전달하면서 해당 id에 대한 데이터가 없으면 Insert를 해준다.
	// Insert-1) email, password 수정
	@Transactional // 함수 종료시에 자동 commit이 됨
	// 더티체킹 : 데이터 변경을 감지! -> DB 수정 
 	@PostMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) { // json 데이터를 요청 => Java Object(MessageConverter의 Jackson 라이브러리가 변환해서 받아준다.)
		System.out.println("id : "+id);
		System.out.println("password : "+requestUser.getPassword());
		System.out.println("email : "+ requestUser.getEmail());
		
		//1. db에서 실제 값을 들고옴 (영속화 : spring내부에 해당 데이터를 들고 있는 것. 영속성 컨텍스트에 보관)
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패했습니다");
		});
		//2. 영속화된 데이터를 받아서 수정하기
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//3. 수정된 데이터 리턴하기
		return user;
		//4. 함수 종료시 => 트랜젝션 종료 => 영속화 되어있는 데이터를 DB에 갱신(flush) => commit  
		// 1~4 의 과정을 더티체킹이라고 한다.


	}
	
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
			return "삭제에 실패하였습니다. 해당 id는 존재하지 않습니다";
		}
		return "삭제완료";
	}

	
//	 요청 : 웹브라우저
//	 user 객체 = 자바 오브젝트
//	 변환 (웹브라우저가 이해할 수 있는 데이터) -> json(Gson라이브러리)
//	 스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
//	 만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
//	 user 오브젝트를 json으로 변환해서 브라우저에게 던져준다.
	
	
	
	// http://localhost:8000/blog/dummy/join (요청)
	// http의 body에 username, password, email 데이터를 가지고 (요청)
	@PostMapping("/dummy/join")
	public String join(User user) { // key=value 형태로 데이터를 받는다.
		System.out.println("id : "+ user.getId());
		System.out.println("username : "+user.getUsername());
		System.out.println("password : "+user.getPassword());
		System.out.println("email : "+user.getEmail());
		System.out.println("role : "+user.getRole());
		System.out.println("createDate : "+user.getCreateDate());
		
		user.setRole(RoleType.USER);
		
		userRepository.save(user);
		return "회원가입 완료";
	}
}
