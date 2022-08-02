<%@page import="user.UserDAO"%>
<%@page import="item.ItemDTO"%>
<%@page import="item.ItemDAO"%>
<%@page import="user.UserDTO"%>
<%@page import="chat.ChatRoomDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="chat.ChatRoomDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" href="./css/chatList.css">
<title>채팅방 목록</title>
</head>
<body>
	<%
		// session에 저장된 로그인된 유저 정보를 가져온다
		UserDTO loginUser = (UserDTO) session.getAttribute("log");
		int loginCode = loginUser.getCode();
		System.out.println("loginCode : " + loginCode);
	%>
	
	<jsp:include page="/modules/header.jsp"></jsp:include>



	<div class="main">
		<h1>채팅방 목록</h1>
		<div class="chatList">
	<%	// 로그인 된 유저의 모든 채팅방을 불러온다
		ArrayList<ChatRoomDTO> rooms = ChatRoomDAO.getInstance().bringAllChatRoom(loginCode);
		System.out.println(rooms.size());
		for(int i = 0; i < rooms.size(); i++) {
			ChatRoomDTO room = rooms.get(i);
			
			int chat_code = room.getChat_code();
			int seller_code = room.getSeller_code();
			int buyer_code = room.getBuyer_code();
			int item_code = room.getItem_code();
			
			// 아이템 정보 가져오기
			ItemDTO itemInfo = ItemDAO.getInstance().getItem(item_code);
			String item_title = itemInfo.getItem_tilte();
			
			// 채팅 상대 정보 가져오기
			int partnerCode = seller_code == loginCode ? buyer_code : seller_code;
			UserDTO userInfo = UserDAO.getInstance().getUser(partnerCode);
			String userId = userInfo.getId();
		%>
			<a href="./chatView?chatRoom_code=<%=chat_code%>">
				<div class="chatBlock">
					<p class="itemTitle">
						<h1>제목 : <%=item_title %></h1>
					</p>
					<br>
					<p class="userInfo">
						유저 아이디 : <%=userId %>
					</p>
				</div>
			</a>
			<%
			}
		%>
		</div>
	</div>

	<div class="footer">
		<jsp:include page="/modules/footer.jsp"></jsp:include>
	</div>
</body>
</html>