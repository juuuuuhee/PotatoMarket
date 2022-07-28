<%@page import="chat.ChatRoomDAO"%>
<%@page import="chat.ChatRoomDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	// 데이터베이스에서 채팅방 리스트를 읽어서 채팅방 목록을 출력한다.
	ArrayList<ChatRoomDTO> roomList = ChatRoomDAO.getInstance().bringAll();
	boolean roomNumZero = true; // 채팅방이 존재하지 않는다
	if (roomList.size() == 0) {
		roomNumZero = false; // 채팅방이 존재한다
	}

	%>
	
	
	
	<%
	// roomList를 출력한다
	for(int i = 0; i < roomList.size(); i++) {

	}
	%>


</body>
</html>