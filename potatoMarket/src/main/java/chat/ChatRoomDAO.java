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
		
		// TODO �����ͺ��̽����� ��� ������ ����Ʈ�� �ҷ��´�
		list.add(new ChatRoomDTO(1, 1234, 2345, 10));
		return list;
	}
	

}
