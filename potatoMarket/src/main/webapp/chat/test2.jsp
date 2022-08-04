<%@page import="user.UserDAO"%>
<%@page import="user.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String id = "banna";
	String password = "2345";
	UserDTO dto = UserDAO.getInstance().getUser(id, password);
	session.setAttribute("log", dto);

	%>

	<input type="button" value="예약하기" onclick='location.href="../chatView?chatRoom_code=1111"'>

</body>
</html>