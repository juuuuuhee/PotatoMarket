<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<title>채팅방</title>
</head>
<body>
	<%
		int chatRoom_code = Integer.parseInt(request.getParameter("chatRoom_code"));
		System.out.println(chatRoom_code);
	%>
	<!--  채팅 영역 -->
	<form>
		<!-- 텍스트 박스에 채팅의 내용을 작성한다. -->
		<input id="textMessage" type="text" onkeydown="return enter()">
		<!-- 서버로 메시지를 전송하는 버튼 -->
		<input onclick="sendMessage()" value="Send" type="button">
		<input type="hidden" class="chatRoomCode" value=<%=chatRoom_code %>>
	</form>
	<br/>
	<!-- 서버와 메시지를 주고 받는 콘솔 텍스트 영역 -->
	<textarea id="messageTextArea" rows="10" cols="50" disabled="disabled"></textarea>
	
	
	<script type="text/javascript">
		// 해당 경로는 파라미터 값으로 넘겨야하나? 채팅방 코드를 파라미터로 넘긴다
		// 해당 경로는 고유한 페이지를 가져야하나? ㄴㄴ
		let chatCode = '<%=chatRoom_code%>';
		console.log("chatCode : ", chatCode);
		
		const socket = new WebSocket("ws://localhost:8080/potatoMarket/chatRoom");
		const messageTextArea = document.getElementById("messageTextArea");
		let message = document.getElementById("textmessage");
		
		// 내가 처음 채팅방에 접속했을때
		socket.onopen = function() {
			messageTextArea.value += "Server connect ... \n";
			console.log("브라우저는 서버와 연결했다");
			// 첫 연결일때 
			makeMessage("",);
			
			// TODO 데이터베이스에서 읽은 채팅내역을 뿌려줘야한다
		}
	
		// 애러가 났을때
		socket.onerror = function() {
			messageTextArea.value += "error ... \n";
		}
		
		socket.onclose = function() {
			console.log("브라우저는 서버와 연결이 끊겼다");
			
		}
		
		// 상대방이 메시지를 보냈을때(메시지를 받았을 때)
		socket.onmessage = function(message) {
			console.log("message : ", message);
			messageTextArea.value += "상대방 : " + message.data + "\n";
		};
		
		function sendMessage() {
			var message = document.getElementById("textMessage");
			messageTextArea.value += "  나 : " + message.value + "\n";
			socket.send(message.value);
			message.value = "";
		}
		
		
		// 텍스트 박스에서 엔터를 누르면
		// keyCode 13은 엔터이다.
		function enter() {
			if (event.keyCode === 13) {
				// 서버로 메시지 전송
				sendMessage();
				// form에 의해 자동 submit을 막는다.
				return false;
			}
			return true;
		}
		
		// JSON 채팅을 String타입으로 바꾼다
		function makeMessage(type, message) {
			const msg = {type, message}
			return JSON.string(msg);
		}
		
		
	
	</script>


</body>
</html>