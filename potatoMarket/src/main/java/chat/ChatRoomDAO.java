package chat;

import java.util.ArrayList;

public class ChatRoomDAO {
	private static ChatRoomDAO instance = new ChatRoomDAO();
	
	private ChatRoomDAO() {}
	
	public static ChatRoomDAO getInstance() {
		return instance;
	}
	
	public ArrayList<ChatRoomDTO> bringAll() {
		ArrayList<ChatRoomDTO> list = new ArrayList<ChatRoomDTO>();
		
		// TODO 데이터베이스에서 모든 데이터 리스트를 불러온다
		list.add(new ChatRoomDTO(1, 1234, 2345, 10));
		return list;
	}
	

}
