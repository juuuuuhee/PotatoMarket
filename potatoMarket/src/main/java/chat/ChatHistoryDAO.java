package chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DbManager;

public class ChatHistoryDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// 채팅방에 접속했을때 해당 채팅방에 채팅기록을 데이터베이스에서 가져와서 반환한다
	public List<ChatHistroyDTO> bringHistroy(int chatRoom_code) {
		List<ChatHistroyDTO> history = new ArrayList<>();
		conn = DbManager.getConnection("potatoMarket");
		String sql = "select * from chatHistory where chatRoom_code = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, chatRoom_code);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int chat_code = rs.getInt(2);
				int addUser = rs.getInt(3);
				String chat_contents = rs.getString(4);

				ChatHistroyDTO chat = new ChatHistroyDTO(chat_code, addUser, chat_contents);
				history.add(chat);
			}

			System.out.println("채팅기록 반환 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("채팅기록 반환 실패");

		} finally {
			rs = null;
			pstmt = null;
			conn = null;
		}

		return history;
	}
	
	public boolean saveChatHistory(ChatHistroyDTO chat) {
		conn = DbManager.getConnection("potatoMarket");
		String sql = "insert into chatHistory values (0, ? , ?, ?, sysdate(),sysdate())";
		boolean chk = false;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, chat.getChatRoom_code());
			pstmt.setInt(2, chat.getAddUser());
			pstmt.setString(3, chat.getChat_contents());

			if (pstmt.executeUpdate() != 0) {
				chk = true;
			}

			System.out.println("채팅기록 저장 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("채팅기록 저장 실패");

		} finally {
			rs = null;
			pstmt = null;
			conn = null;
		}

		return chk;
	}

}
