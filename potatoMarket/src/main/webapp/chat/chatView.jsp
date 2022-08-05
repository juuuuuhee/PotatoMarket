<%@page import="user.UserDAO"%>
<%@page import="chat.ChatRoomDTO"%>
<%@page import="item.ItemDTO"%>
<%@page import="item.ItemDAO"%>
<%@page import="chat.ChatRoomDAO"%>
<%@page import="user.UserDTO"%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<link rel="stylesheet" href="./css/chatView.css">
<title>채팅방</title>
</head>
<body>
	<%@include file="/modules/header.jsp" %>
	<%
	// 파라미터로 받은 값을 session에 저장한다
	Object chatRoom_codeParam = request.getParameter("chatRoom_code");
	if (chatRoom_codeParam != null) {
		String code = (String) chatRoom_codeParam;
		session.setAttribute("chatRoom_code", code);
	}

	// session에 저장된 값을 읽어온다
	int chatRoom_code = Integer.parseInt((String) session.getAttribute("chatRoom_code"));
	System.out.println("chatRoom_code : " + chatRoom_code);
	
	// 채팅방 코드를 이용해서 아이템 코드를 불러온다. '상품으로' 버튼에 사용된다
	ChatRoomDTO chatRoomInfo = ChatRoomDAO.getInstance().getData(chatRoom_code);
	
	// session에 저장된 로그인된 유저 정보를 가져온다
	UserDTO loginUser = (UserDTO) session.getAttribute("log");
	int loginCode = loginUser.getCode();
	System.out.println("loginCode : " + loginCode);

	// 대화 상대방 이름을 뽑아서 상단에 표시한다
	// 채팅 상대방의 정보(코드)를 가져온다
	int partnerCode = ChatRoomDAO.getInstance().bringPartnerCode(chatRoom_code, loginCode);
	UserDTO partnerDTO = UserDAO.getInstance().getUserData(partnerCode);

	%>
	
	<input type="hidden" name="chatRoom_code" value="<%=chatRoom_code %>">
	<input type="hidden" name="loginCode" value="<%=loginCode %>">
	
	<main>
		<br>
		<div id=chatRoom_name>
			<h1><%=partnerDTO.getId() %>님과의 채팅방</h1> 
			<div>
				<input type="button" value="상품으로" onclick="location.href='./itemView?code=<%=chatRoomInfo.getItem_code()%>'">
				<input type="button" value="뒤로가기" onclick="location.href=`./chatList`">
			</div>
		</div>
		<br>
		<!--  채팅 영역 -->
		<div id="chat">
			<form>
				<ul id="chatBlock">
				</ul>
				
				<br>
				<div id="inputText">
					<input id="textMessage" type="text" onkeydown="return enter()">
					<!-- 서버로 메시지를 전송하는 버튼 -->
					<input id="sendMessage" onclick=chkTextBlank() value="Send" type="button">
					<input type="hidden" class="chatRoomCode" value=<%=chatRoom_code%>>
				</div>
				<span id="check">채팅을 입력해주세요</span>
			</form>
	
		</div>
		
	
	</main>
	<div class="footer">
		<%@include file="/modules/footer.jsp" %>
	</div>
	<script src="./script/chatView.js"></script>

</body>
</html>