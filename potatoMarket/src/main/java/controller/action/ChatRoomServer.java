package controller.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;

import chat.ChatHistoryDAO;
import chat.ChatHistroyDTO;

@ServerEndpoint(value = "/chatRoom")
public class ChatRoomServer {

	// 의문점
	// 1. Collections를 쓸 필요가? 무슨말이지?
	// 2. 브라우저Session에 저장할 필요 없이 그냥 바로 chatRoomInfos에 저장하면 되는가?
	private static List<chatRoomInfo> chatRoomInfos = new ArrayList<>();

	private class chatRoomInfo {
		// 유저 1, 2에 대한 유저 코드와 소켓정보
		// chatRoomInfo는 하나의 채팅방 역할을 한다
		// 채팅룸 코드. 맨 처음 접속했을때 파라미터로 받은 chatRoom_code를 이용해서(chatRoom_code는 데이터베이스에서 뽑아낸
		// 코드이다)
		// List에 저장된 채팅방 정보를 읽는다
		Session user1Socket;
		int user1Code;
		Session user2Socket;
		int user2Code;
		int chatRoom_code;
	}

	// 소켓이 열렸을때
	@OnOpen
	// 새로운 유저가 서버를 열었을때
	public void handleOpen(Session userSocket) {
		// 파라미터로 넘어온 chatRoom_code를 이용해서 검색한다
		// 만약 리스트에 존재하지 않는다면 새롭게 생성한다
		// 만약 리스트에 존재하면 해당 배열 안에 저장한다
		System.out.println("서버는 브라우저와 연결했다");

	}

	// 메세지를 서버가 받았을때
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
		// ㄴ open이면 DB에서 데이터를 읽어서 userSocket.send(message) 한다
		// ㄴ

		// String으로 받은 json타입
		// JSONParser로 JSONObject로 변환
//		JSONParser parser = new JSONParser();
//		JSONObject jsonObject = (JSONObject) parser.parse(message);
//
//		// JSON 객체의 값 읽어서 출력하기

		try {
			// String을 JSON화 시키기
			JSONObject jsonObject = new JSONObject(message);

			// 받아온 메시지로부터 값을 추출한다
			String type = jsonObject.getString("type");
			String chatRoom_code = jsonObject.getString("chatRoom_code");
			int logCode = jsonObject.getInt("logCode");
			String msg = jsonObject.getString("message");

			if (type.equals("new_message")) {
				// 해당 메시지를 채팅하고 있는 상대방에게 건낸다
				// 만약 상대방이 접속중이라면 메시지를 발송한다
				Session partnerSocket = findPartnerSocket(userSocket, chatRoom_code);
				if (partnerSocket != null) { // 상대방이 접속중일때
					System.out.println("이거 실행됐니?");
					JSONObject obj = new JSONObject(
							"{ \"sendUserCode\" : " + logCode + ", \"chatContents\" : " + msg + "}");
					partnerSocket.getBasicRemote().sendText(obj.toString()); // Object 타입으로 브라우저로 보낸다

					ChatHistroyDTO chat = new ChatHistroyDTO(Integer.parseInt(chatRoom_code), logCode, msg, 0);
					ChatHistoryDAO chatHistoryDAO = new ChatHistoryDAO();
					chatHistoryDAO.saveChatHistory(chat);
				} else { // 상대방이 접속중이 아닐때

					ChatHistroyDTO chat = new ChatHistroyDTO(Integer.parseInt(chatRoom_code), logCode, msg, 1);
					ChatHistoryDAO chatHistoryDAO = new ChatHistoryDAO();
					chatHistoryDAO.saveChatHistory(chat);
				}

				// 메시지를 데이터베이스에 저장한다

			} else if (type.equals("open")) {
				// 처음으로 채팅창을 열었을때 DB에서 채팅내역을 불러와서 저장한다
				ChatHistoryDAO chatHistoryDAO = new ChatHistoryDAO();
				List<ChatHistroyDTO> history = chatHistoryDAO.bringHistroy(jsonObject.getInt("chatRoom_code"));
				for (int i = 0; i < history.size(); i++) {
					// 만약 보내는 사람의 코드를 ...
					int sendUserCode = history.get(i).getAddUser();
					String chatContents = history.get(i).getChat_contents();
					JSONObject obj = new JSONObject(
							"{ \"sendUserCode\" : " + sendUserCode + ", \"chatContents\" : " + chatContents + "}");
					userSocket.getBasicRemote().sendText(obj.toString()); // Object 타입으로 브라우저로 보낸다

				}

				// chatRoomInfos에 소켓정보를 입력한다.
				// 먼저 해당 방이 존재하는지 확인하고 없다면 새로운 요소로 추가한다
				// 만약 존재한다면 소켓을 해당 방에 저장한다

				int chatRoomIdx = findRoomIdx(Integer.parseInt(chatRoom_code));

				if (chatRoomIdx == -1) {
					// 아직 만들어진 채팅방이 없다
					// chatRoomInfos에 새롭게 방을 추가한다

					chatRoomInfo room = new chatRoomInfo();
					room.user1Socket = userSocket;
					room.user1Code = logCode;
					room.chatRoom_code = Integer.parseInt(chatRoom_code);

					chatRoomInfos.add(room);
					System.out.println("list에 새로운 방을 추가함");

				} else {
					// 상대방이 접속중이다
					// 해당 chatRoom에 접속한다

					chatRoomInfo room = chatRoomInfos.get(chatRoomIdx);
					if (room.user1Code == logCode) {
						room.user1Socket = userSocket;
					} else if (room.user2Code == logCode) {
						room.user2Socket = userSocket;
					} else if (room.user1Code == 0) {
						room.user1Socket = userSocket;
						room.user1Code = logCode;
					} else if (room.user2Code == 0) {
						room.user2Socket = userSocket;
						room.user2Code = logCode;
					}

				}

			}
		} catch (Exception err) {
			System.out.println("Exception : " + err.toString());
		}

	}

	// 채팅방에 접속했을때 해당하는 채팅방이 존재하는지 여부 판단하여 반환함
	private int findRoomIdx(int chatRoom_code) {
		int chatRoomIdx = -1;
		for (int i = 0; i < chatRoomInfos.size(); i++) {
			chatRoomInfo room = chatRoomInfos.get(i);
			if (chatRoom_code == room.chatRoom_code) {
				chatRoomIdx = i;
			}
		}
		return chatRoomIdx;
	}

	// 메시지를 보냈을때 상대방의 소켓을 읽는 작업
	private Session findPartnerSocket(Session userSocket, String chatRoom_code) {
		// 채팅방코드를 통해서 chatRoomInfos에서 검색한다
		// userSocket과 일치하지 않는 상대방 소켓을 검색하고, 해당 소켓이 만약 존재한다면 그것을 반환한다
		// 만약 존재하지 않는다면 null을 반환한다
		int roomCode = Integer.parseInt(chatRoom_code);
		for (int i = 0; i < chatRoomInfos.size(); i++) {
			chatRoomInfo chatRoom = chatRoomInfos.get(i);
			if (chatRoom.chatRoom_code == roomCode) {
				Session partnerSocket = userSocket == chatRoom.user1Socket ? chatRoom.user2Socket
						: chatRoom.user1Socket;
				if (partnerSocket != null) {
					return partnerSocket;
				}
				return null;
			}
		}
		return null;
	}

	// 소켓이 닫혔을때
	@OnClose
	// 해당 유저가 소켓을 닫았을때
	public void handleClose(Session userSocket) {
		System.out.println("서버는 브라우저와 연결이 끊겼다");
		// chatRoomInfos에서 해당 소켓 정보를 지운다
		// 소켓정보다 둘다 없으면 배열에서 삭제한다
		int idx = -1;
		for (int i = 0; i < chatRoomInfos.size(); i++) {
			if (userSocket == chatRoomInfos.get(i).user1Socket || userSocket == chatRoomInfos.get(i).user2Socket) {
				idx = i;
			}
		}
		System.out.println("chatRoomInfos.get(idx)" + idx);
		chatRoomInfo room = chatRoomInfos.get(idx);
		if (room.user1Socket != null && room.user2Socket != null) {
			if (room.user1Socket == userSocket) {
				room.user1Socket = null;
				room.user1Code = 0;

			} else {
				room.user2Socket = null;
				room.user2Code = 0;
			}
			System.out.println("소켓리스트 채팅방 2명 중 한명만 나감");

		} else { // 채팅방에 둘중 한명만 접속중이다
			chatRoomInfos.remove(idx);
			System.out.println("소켓리스트에서 채팅방을 제거함");
		}
	}

}
	