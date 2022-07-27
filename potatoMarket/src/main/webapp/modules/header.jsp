<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<link rel="stylesheet" href="css/module.css">
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<% String log=(String) session.getAttribute("log"); %>
<div class="header">
	<div>
		<a class="log" href="index.jsp">
			<h1>감자!</h1>
		</a>
	</div>
	<div>
		<form>
			<input type="text" placeholder="물품을 검색해보세요."> <input
				type="submit" value="검색">
		</form>
	</div>
	<div>
		<% if(log == null){ %>
		<button id="button" onclick="location.href='./login_page'">로그인</button>
		<% }else{ %>
		<button id="button">로그아웃</button>
		<% }%>
		<button id="button">회원가입</button>
	</div>
</div>