<%@page import="chat.ChatRoomDTO"%>
<%@page import="java.util.ArrayList"%>
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
	int logincode = -1;
	Object loginCodeObject = session.getAttribute("log");
	int cnt = 0;
	
	if (loginCodeObject != null) {
		UserDTO user = (UserDTO) loginCodeObject;
		cnt = ChatRoomDAO.getInstance().getNotReadNum(user.getCode());
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
			<input type="text" name="keyword" placeholder="물품을 검색해보세요.">
			<input id="button" type="submit" value="검색">
		</form>
	</div>
	
	<div class="log_join">
		<ul class="headerUL">
			<c:choose>
				<c:when test="${log == null}">
					<li><a id="button" href="./login_page"><img src="resource/login.png" width="50px" height="50px"></a></li>
					<li><a id="button" href="./join_page"><img src="resource/join.png" width="50px" height="50px"></a></li>
				</c:when>
				<c:otherwise>
					<li><c:out value="${sessionScope.log.getId()}님 환영합니다!" /></li>
					<li><a id="button" href="./myPage"><img src="resource/my.png" width="60px" height="60px"></a></li>

					<li class="alert">
						<a id="button" href="./chatList">
							<%if(cnt > 0){%>
							<span class="note-num"><%=cnt%></span>
							<%} %>
							<img src="resource/chat.png" width="50px" height="50px">
						</a>			
					</li>
					<li><a id="button" href="./action?command=logout"><img src="resource/logout.png" width="40px" height="40px"></a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
	
</div>