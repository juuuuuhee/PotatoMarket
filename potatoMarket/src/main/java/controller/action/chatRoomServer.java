package controller.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint(value = "/chatRoom")
public class chatRoomServer {
	
	// 의문점
	// 1. Collections를 쓸 필요가? 무슨말이지?
	// 2. 브라우저Session에 저장할 필요 없이 그냥 바로 chatRoomInfos에 저장하면 되는가?
	private static List<chatRoomInfo> chatRoomInfos = new ArrayList<>();
	
	private class chatRoomInfo {
		// 유저 1, 2에 대한 유저 코드와 소켓정보
		// chatRoomInfo는 하나의 채팅방 역할을 한다
		// 채팅룸 코드. 맨 처음 접속했을때 파라미터로 받은 chatRoom_code를 이용해서(chatRoom_code는 데이터베이스에서 뽑아낸 코드이다)
		// List에 저장된 채팅방 정보를 읽는다
		Session buyerSocket;
		int buyer_code;
		Session sellerSocket;
		int seller_code;
		int chatRoom_code;
	}
	
	// ws:/chatRoom으로 넘어올때 데이터를 함께 넘기는 방법?
	int chatRoom_code;
	
	@OnOpen
	// 새로운 유저가 서버를 열었을때
	public void handleOpen(Session userSocket) {
		// 파라미터로 넘어온 chatRoom_code를 이용해서 검색한다
		// 만약 리스트에 존재하지 않는다면 새롭게 생성한다
		// 만약 리스트에 존재하면 해당 배열 안에 저장한다
		System.out.println("서버는 브라우저와 연결했다");
		
	}
	
	@OnMessage
	// '서버'가 해당 유저로부터 메세지를 받았을때? 이게 맞다
	// '유저'가 서버로부터 메세지를 받았을때? 이건 틀림
	// 브라우저1, 서버, 브라우저2는 각각 다른 OnMessage가 존재한다
	
	// 넘겨받은 웹소켓으로 chatRoomInfos에 검색을 한다
	// 상대방의 소켓정보를 손에 넣는다
	// 해당 소켓.send(message)를 하면 된다
	public void handleMessage(String message, Session userSocket) throws IOException {
		// chatRoomInfos에서 상대방의 접속유무를 판단한다
		// 만약 접속해있다면 상대방의 소켓을 이용해서 메세지를 보낸다(send)
		// 접속 유무와 상관없이 메시지를 데이터베이스에 저장한다 
		
		// 1. 받은 메시지를 JSON으로 변형한다
		// 2. JSON의 타입을 확인한다
		//    ㄴ open이면 DB에서 데이터를 읽어서 userSocket.send(message) 한다
		//	  ㄴ 
	}
	
	@OnClose
	// 해당 유저가 소켓을 닫았을때
	public void handleClose(Session userSocket) {
		System.out.println("서버는 브라저와 연결이 끊겼다");
		// chatRoomInfos에서 해당 소켓 정보를 지운다
		// 소켓정보다 둘다 없으면 배열에서 삭제한다
		
	}
	
	
}
