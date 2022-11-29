// 해당 경로는 파라미터 값으로 넘겨야하나? 채팅방 코드를 파라미터로 넘긴다
// 해당 경로는 고유한 페이지를 가져야하나? ㄴㄴ
let chatCode = $('input[name=chatRoom_code]').val();;
let logCode = $('input[name=loginCode]').val();;

console.log(chatCode);
console.log("안녕");

// const socket = new WebSocket("ws://13.125.228.16/potatoMarket/chatRoom");
const socket = new WebSocket("ws://localhost:8080/potatoMarket/chatRoom");

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
	socket["chatRoom_code"] = chatCode;
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
	const msg = { type, message, chatRoom_code, logCode }
	return JSON.stringify(msg);
}

// 텍스트를 입력 안하고 보내려고 하면 막는다
function chkTextBlank() {
	event.preventDefault();
	let essential = $('#textMessage').val();
	console.log(essential);
	console.log(typeof essential);

	if (essential) {
		console.log("여기가 실행됨");
		$('#check').css('display', 'none');
		sendMessage();
	} else {
		$('#check').css('display', 'block');
	}
}

function addMyMsg(message) {
	let tmp = `
				<li class="me"> 
					<div class="name">
						<h4>나</h4>
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
						<h4>상대방</h4>
					</div>
					<div class="message">
						` + message + `
					</div>
				</li>`;
	console.log(tmp);
	$("#chatBlock").append(tmp);
}
