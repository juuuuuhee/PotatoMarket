<%--
  Created by IntelliJ IDEA.
  User: juhee
  Date: 2022/07/26
  Time: 4:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page import="user.UserDAO"%>
<%@page import="user.UserDTO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
	<%
	// 아이템 코드
//	int item_code = Integer.parseInt(request.getParameter("item_code"));
	int item_code = 3;
	
	// 로그인한 유저 코드
	String id = "voyager";
	String password = "Juhee9142!";
	UserDTO dto = UserDAO.getInstance().getUser(id, password);
	session.setAttribute("log", dto);
	%>
	
	<form action="./action">
		<input type="hidden" name="command" value="intoChatRoom">
		<input type="hidden" name="item_code" value="<%=item_code%>">
		<input type="submit" value="채팅하기">
	</form>

</body>
</html>
