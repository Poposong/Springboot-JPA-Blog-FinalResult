let index = {
	init: function(){
		$("#btn-save").on("click",()=>{ // function(){} , ()=>{} this를 바인딩하기 위해서!! 
			this.save();
		});
	},
	
	save: function(){
		let data={
			username : $("#username").val(),
			password : $("#password").val(),
			email : $("#email").val()
		}
		
		//console.log(data);
		
		// ajax호출시 default가 비동기 호출
		// ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해준다.
		$.ajax({
			type:"POST", // type:"post" 이므로 Insert를 하는구나~
			url:"/auth/joinProc",
			data:JSON.stringify(data), // 자바스크립트 오브젝트인 data를 JSON문자열로 바꾼다 // http body 데이터
			contentType:"application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
			dataType:"json" //  요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴 것은 json이라면) → javascript 오브젝트로 변경
		}).done(function(resp){ // 응답의 결과가 json이면 응답의 결과가 resp로 전달된다. 
			if(resp.status === 500){
				alert("회원가입을 하지 못했습니다. 다시 시도해주세요");
			}else{
				alert("회원가입이 완료되었습니다");
				location.href = "/";
			}
		}).fail(function(error){
			
			alert(JSON.stringify(error));
		});  // ajax 통신을 이용해서 3개의 parameter 를 json으로 변경하여 insert 요청을 한다.
	}
		
	
}

index.init();