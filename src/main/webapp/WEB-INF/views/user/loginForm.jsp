<%@ page language="java" contentType="text/html; charset=UTF-16" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<!-- ctrl shift F 자동 정렬 -->
<div class="container">
	<form action="/auth/loginProc" action="#" method="post">
		<div class="form-group">
			<label for="username">Username</label>
			<input type="text" name="username" class="form-control" placeholder="Enter username" id="username">
		</div>
		
		<div class="form-group">
			<label for="password">Password</label>
			<input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
		</div>
		
		
		<button id="btn-login" class="btn btn-primary">로그인</button>
		
	</form>
	
</div>


<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>

