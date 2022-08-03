<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
	<link rel="stylesheet" href="./css/module.css">
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<%-- <% String log=(String) session.getAttribute("log"); %> --%>

<c:set var="log" value="${sessionScope.log}" />

<div class="header">
	<div class="potato_icon">
		<a class="log" href="index.jsp">
			<img src="resource/potato.png" width="60px" height="60px">
		</a>
	</div>
	<div>
		<form class="main_form">
			<input type="text" placeholder="물품을 검색해보세요.">
			<input id="button" type="submit" value="검색">
		</form>
	</div>
	<div class="log_join">
		<c:choose>
			<c:when test="${empty log}">
				<button id="button" onclick="location.href='./login_page'">로그인</button>
				<button id="button" onclick="location.href='./join_page'">회원가입</button>
			</c:when>
			<c:otherwise>
				<button>마이페이지</button>
				<button id="button" onclick="location.href='./chatList'">채팅</button>
				<button id="button" onclick="location.href='./action?command=logout'">로그아웃</button>
			</c:otherwise>
		</c:choose>
	</div>
</div>