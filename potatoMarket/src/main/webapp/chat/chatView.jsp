<%@page import="chat.ChatRoomDAO"%>
<%@page import="user.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

	// session에 저장된 로그인된 유저 정보를 가져온다
	UserDTO loginUser = (UserDTO) session.getAttribute("log");
	int loginCode = loginUser.getCode();
	System.out.println("loginCode : " + loginCode);

	// 채팅 상대방의 정보(코드)를 가져온다
	int partnerCode = ChatRoomDAO.getInstance().bringPartnerCode(chatRoom_code, loginCode);
	%>
	<jsp:include page="/modules/header.jsp"></jsp:include>
	
	<main>
		<br>
		<div id=chatRoom_name>
			<h1>채팅방</h1> 
			<input type="button" value="뒤로가기" onclick="location.href=`./chatList`">
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
		<jsp:include page="/modules/footer.jsp"></jsp:include>
	</div>
	
	<script type="text/javascript">
		// 해당 경로는 파라미터 값으로 넘겨야하나? 채팅방 코드를 파라미터로 넘긴다
		// 해당 경로는 고유한 페이지를 가져야하나? ㄴㄴ
		let chatCode = '<%=chatRoom_code%>';
		let logCode = '<%=loginCode%>';
		
		const socket = new WebSocket("ws://localhost:8082/potatoMarket/chatRoom");
		const chatBlock = document.getElementById("chatBlock");
		let message = document.getElementById("textMessage");
		  
		// OPEN 내가 처음 채팅방에 접속했을때
		socket.onopen = function() {
			addMyMsg("서버와 연결됐습니다");
			// 첫 연결일때 
			const openMsg = makeMessage("open", "접속성공", chatCode, logCode);
			socket.send(openMsg);
		}
	
		// 닫혔을때		
		socket.onclose = function() {
			socket["chatRoom_code"] = chatRoom_code;
			console.log("브라우저는 서버와 연결이 끊겼다");
			alert("닫혔다!");
			
			// test
			console.log(socket.chatRoom_code);
			
		}
		
		// 메시지를 받았을 때
		// 상대방이 메시지를 보냈을때
		socket.onmessage = function(message) {
			// 메시지를 JSON화 시킨다
			let msg = JSON.parse(message.data);
			console.log(msg)
			console.log(msg.sendUserCode);
			if (msg.sendUserCode == logCode) {
				console.log('받아온 메시지는 로그인된 정보와 똑같습니다');
				addMyMsg(msg.chatContents);
			} else {
				console.log("받아온 메시지는 로그인된 정보와 다릅니다");
				addYourMsg(msg.chatContents);
			}

			// 스크롤을 항상 아래로 옮긴다
			const top = $('#chatBlock').prop('scrollHeight');
			$('#chatBlock').scrollTop(top);
		};
		
		// 내가 메시지를 보낼때
		function sendMessage() {
			let message = document.getElementById("textMessage");
			const msg = makeMessage("new_message", message.value, chatCode, logCode); 
			// { "type" : "new_message" ,"message" : message.value, "chatRoom_code" : chatCode, "logCode" : logCode } 이 타입이 String으로 반환되서 돌아온다
			
			addMyMsg(message.value);
			
			socket.send(msg);
			
			message.value = "";

			// 스크롤을 항상 아래로 옮긴다
			const top = $('#chatBlock').prop('scrollHeight');
			$('#chatBlock').scrollTop(top);
		}
		
		// 텍스트 박스에서 엔터를 누르면
		// keyCode 13은 엔터이다.
		function enter() {
			if (event.keyCode === 13) {
				// 서버로 메시지 전송
				// sendMessage();
				chkTextBlank();
				// form에 의해 자동 submit을 막는다.
				return false;
			}
			return true;
		}
		
		// JSON 채팅을 String타입으로 바꾼다
		function makeMessage(type, message, chatRoom_code, logCode) {
			const msg = {type, message, chatRoom_code, logCode}
			return JSON.stringify(msg);
		}
		
		// 텍스트를 입력 안하고 보내려고 하면 막는다
		function chkTextBlank() {
			event.preventDefault();
			let essential = $('#textMessage').val();
			console.log(essential);
			console.log(typeof essential);
			
			if(essential){
				console.log("여기가 실행됨");
				$('#check').css('display', 'none');
				sendMessage();
			}else{
				$('#check').css('display', 'block');
			}
		}
		
		function addMyMsg(message) {
			let tmp = `
				<li class="me"> 
					<div class="name">
						<h2>나</h2>
					</div>
					<div class="message">
						` + message + `
					</div>
				</li>`;
			console.log(tmp);
			$("#chatBlock").append(tmp);
		}
		
		function addYourMsg(message) {
			let tmp = `
				<li class="you"> 
					<div class="name">
						<h2>상대방</h2>
					</div>
					<div class="message">
						` + message + `
					</div>
				</li>`;
			console.log(tmp);
			$("#chatBlock").append(tmp);
		}

		
	
	</script>


</body>
</html>