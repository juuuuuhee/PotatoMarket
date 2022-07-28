<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<title>Insert itle here</title>
</head>
<body>
	<div class="template" style="display: none">
		<form>
			<input type="text" class="message" onkeydown="if(event.keyCode === 13) return false;">
			<input value="Send" type="button" onclick="sendMessage()">
		</form>
		<br>
		<textarea rows="10" cols="50" class="console" disabled="disabled"></textarea>
	</div>
	
	<script type="text/javascript">
		// 해당 경로는 파라미터 값으로 넘겨야하나?
		// 해당 경로는 고유한 페이지를 가져야하나?
		let webSocket = new WebSocket("ws://localhost:8080/chat/boardsocket");
		webSocket.onopen = function(message) {};
		webSocket.onclose = function(message) {};
	
		let messageTextArea = document.getElementById("messageTextArea");
		
		webSocket.onopen = function(message) {
			messageTextArea.value += "Server connect ... \n";
		}
		
		// 접속이 끊기는 경우
		webSocekt.onclose = function(message) {};
	
		webSocket.onerror = function(message) {
			messageTextArea.value += "error ... \n";
		}
		
		webSocekt.onmessage = function(message) {
			messageTextArea.value += "(operator) ==> " + message.data + "\n";
		};
		
		function sendMessage() {
			let messageTextArea.value += "(me) => " + message.value + "\n";
			webSocket.send(message.value);
			message.value = "";
			
		}
	
	
	</script>


</body>
</html>