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

	// 채팅방에 접속했을때 채팅방에 채팅기록을 데이터베이스에서 가져와서 반환한다
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

				ChatHistroyDTO chat = new ChatHistroyDTO(chat_code, addUser, chat_contents, 0);
				history.add(chat);
			}

			System.out.println("채팅기록 반환 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("채팅기록 반환 실패");

		} finally {
			try {
				if (rs != null)
					rs.close();
				else if (pstmt != null)
					pstmt.close();
				else if (conn != null)
					conn.close();
			} catch (Exception e2) {
			}
		}

		return history;
	}

	// 메세지를 보내면 데이터베이스에 채팅기록을 저장한다
	public boolean saveChatHistory(ChatHistroyDTO chat) {
		conn = DbManager.getConnection("potatoMarket");
		String sql = "insert into chatHistory values (0, ?, ?, ?, sysdate(), sysdate(), ?)";
		boolean chk = false;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, chat.getChatRoom_code());
			pstmt.setInt(2, chat.getAddUser());
			pstmt.setString(3, chat.getChat_contents());
			pstmt.setInt(4, chat.getReadChat());

			if (pstmt.executeUpdate() != 0) {
				chk = true;
			}

			System.out.println("채팅기록 저장 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("채팅기록 저장 실패");

		} finally {
			try {
				if (rs != null)
					rs.close();
				else if (pstmt != null)
					pstmt.close();
				else if (conn != null)
					conn.close();
			} catch (Exception e2) {
			}
		}

		return chk;
	}

	// 채팅방에 들어섰을때 채팅 읽음처리 해준다
	public void changeReadChat(int chatRoom_code, int partnerCode) {
		String sql = "UPDATE chatHistory SET readChat = REPLACE(readChat, 1, 0) where chatRoom_code = ?  and addUser = ?";
		conn = DbManager.getConnection("potatoMarket");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, chatRoom_code);
			pstmt.setInt(2, partnerCode);
			pstmt.execute();

			System.out.println("changeReadChat - 채팅 읽음처리 완료");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("changeReadChat - 채팅 읽음처리 실패");

		} finally {
			try {
				if (rs != null)
					rs.close();
				else if (pstmt != null)
					pstmt.close();
				else if (conn != null)
					conn.close();
			} catch (Exception e2) {
			}
		}
	}

	public void changeNotRead_num(int chatRoom_code, int loginCode) {
		conn = DbManager.getConnection("potatoMarket");
		try {
			String sql = "select recentAdd_code from chatRoom where chat_code = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, chatRoom_code);
			rs = pstmt.executeQuery();
			rs.next();
			int recentAdd_code = rs.getInt(1);

			if (recentAdd_code != loginCode) {
				sql = "update chatRoom set notRead_num = 0 where chat_code = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, chatRoom_code);
				pstmt.execute();

				System.out.println("읽음처리 완료");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("읽음처리 실패");
		} finally {
			try {
				if (rs != null)
					rs.close();
				else if (pstmt != null)
					pstmt.close();
				else if (conn != null)
					conn.close();
			} catch (Exception e2) {
			}
		}

	}
}
