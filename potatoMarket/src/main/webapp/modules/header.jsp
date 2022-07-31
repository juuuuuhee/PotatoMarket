<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<link rel="stylesheet" href="../css/module.css">
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<%-- <% String log=(String) session.getAttribute("log"); %> --%>

<c:set var="log" value="${sessionScope.log}"/>

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
		<c:choose>
			<c:when test="${empty log}">
				<button id="button" onclick="location.href='./login_page'">로그인</button>
			</c:when>
			<c:otherwise>
				<button id="button">로그아웃</button>
			</c:otherwise>
		</c:choose>
		<button id="button" onclick="location.href='./join_page'">회원가입</button>
	</div>
</div>