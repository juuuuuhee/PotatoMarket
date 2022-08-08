<%@page import="chat.ChatRoomInfo"%>
<%@page import="user.UserDAO"%>
<%@page import="item.ItemDTO"%>
<%@page import="item.ItemDAO"%>
<%@page import="user.UserDTO"%>
<%@page import="chat.ChatRoomDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="chat.ChatRoomDAO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/chatList.css">
<title>채팅방 목록</title>
</head>
<body>
	<div>
		<%@include file="../modules/header.jsp"%>
	</div>

	<%
	// session에 저장된 로그인된 유저 정보를 가져온다
	UserDTO loginUser = (UserDTO) session.getAttribute("log");
	int loginCode = loginUser.getCode();
	System.out.println("loginCode : " + loginCode);
	
	// 판매완료가 되었다면 알림창을 띄운다
	Object chkObj =  request.getAttribute("chk");
	if (chkObj != null) {
		%> <script> alert("판매완료로 수정되었습니다"); </script> <%
	}
	%>

	<div class="main">
		<p class="logo title">채팅방 목록</p>
		<div class="chatList">
			<%
			// 로그인 된 유저의 모든 채팅방을 불러온다
			ArrayList<ChatRoomDTO> rooms = ChatRoomDAO.getInstance().bringAllChatRoom(loginCode);
			ChatRoomDAO chatRoomDAO = ChatRoomDAO.getInstance();
			// ##3
			ArrayList<ChatRoomInfo> chatRoomInfos = chatRoomDAO.bringAllChatRoomInfos(rooms, loginCode);

			for (int i = 0; i < chatRoomInfos.size(); i++) {
				ChatRoomInfo chatRoomInfo = chatRoomInfos.get(i);

				int chat_code = rooms.get(i).getChat_code();
				int count = chatRoomInfo.getNotRead_num();
				String title = chatRoomInfo.getItemTitle();
				String id = chatRoomInfo.getPartnerId();
				String pic = chatRoomInfo.getItemPic();
				int item_selling = chatRoomInfo.getItem_selling();
				
				System.out.println("item_selling : " + item_selling);
				
				%>
				<a href="./action?command=chatView&chatRoom_code=<%=chat_code%>">
						<ul class="alert">
						<c:if test="<%=count > 0%>">
							<span class="note-num2"><%=count%></span>
						</c:if>
						<% if (item_selling == 1) { %>
							<div class="chatBlock soldOut" style="color : grey; filter: brightness(0.5);">
							<div>
								<p class="itemTitle" >
									<h1> 판매 완료된 제품</h1>
								</p>
								<br>
								<p class="userInfo">
									상대ID : <%=id%>
								</p>
							</div>
						<% } else if (item_selling == 0) { %>
							<div class="chatBlock" >
							<div>
								<p class="itemTitle">
									<h1><%=title%></h1>
								</p>
								<br>
								<p class="userInfo">
									상대ID : <%=id%>
								</p>
							</div>
						<% } %>					
							<img class="itemPic" src="<%=pic%>">
						</div>
					</ul>
				</a>
				<%
				}
				%>
		</div>
	</div>

	<div class="footer">
		<%@include file="../modules/footer.jsp"%>
	</div>
</body>
</html>