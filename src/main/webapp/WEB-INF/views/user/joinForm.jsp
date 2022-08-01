<%@ page language="java" contentType="text/html; charset=UTF-16" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<!-- ctrl shift F 자동 정렬 -->
<div class="container">
	<form>
		<div class="form-group">
			<label for="username">Username</label> <input type="text" class="form-control" placeholder="Enter username" id="username">
		</div>
		<div class="form-group">
			<label for="password">Password</label> <input type="password" class="form-control" placeholder="Enter password" id="password">
		</div>
		<div class="form-group">
			<label for="email">Email</label> <input type="email" class="form-control" placeholder="Enter email" id="email">
		</div>
	</form>
	
	<button id="btn-save" class="btn btn-primary" >회원가입완료</button>
</div>


<!-- <script src="http://code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script> -->
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>

