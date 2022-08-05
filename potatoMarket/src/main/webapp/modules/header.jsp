<%@page import="user.UserDTO"%>
<%@page import="chat.ChatRoomDAO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<link rel="stylesheet" href="./css/module.css">
</head>
<%-- <% String log=(String) session.getAttribute("log"); %> --%>

<c:set var="log" value="${sessionScope.log}" />

	<%
	// 아직 읽지 않은 채팅 개수를 읽는다
	// session에 저장된 로그인된 유저 정보를 가져온다
	Object loginUserObject = session.getAttribute("log");
	int cnt = -1;
	if (loginUserObject != null) {
		UserDTO login_User = (UserDTO) loginUserObject;
		int login_Code = login_User.getCode();
		System.out.println("loginCode : " + login_Code);
		cnt = ChatRoomDAO.getInstance().getNotReadNum(login_Code);
		System.out.println("안읽은 채팅개수 : " + cnt);
	}
	%>


<div class="header">
	<div class="potato_icon">
		<a class="log" href="index.jsp">
			<img src="resource/potato.png" width="60px" height="60px">
		</a>
		<a href="index.jsp"><p class="logo">감자마켓</p></a>
	</div>
	
	<div>
		<form class="main_form" action="./itemList">
			<input type="text" placeholder="물품을 검색해보세요.">
			<input id="button" type="submit" value="검색">
		</form>
	</div>
	
	<div class="log_join">
		<c:choose>
			<c:when test="${log == null}">
				<button id="button" onclick="location.href='./login_page'">로그인</button>
				<button id="button" onclick="location.href='./join_page'">회원가입</button>
			</c:when>
			
			<c:otherwise>
				<c:out value="${sessionScope.log.getId()}님 환영합니다!"/>
				<button id="button" onclick="location.href='./myPage'">마이페이지</button>
				<button id="button" onclick="location.href='./chatList'">채팅</button>
				<c:if test="<%=cnt > 0%>">
					<span class="note-num"><%=cnt %></span>
				</c:if>
				<button id="button" onclick="location.href='./action?command=logout'">로그아웃</button>
			</c:otherwise>
		</c:choose>
	</div>
	
</div>