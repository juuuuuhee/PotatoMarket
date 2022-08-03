package chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DbManager;

public class ChatRoomDAO {
	private static ChatRoomDAO instance = new ChatRoomDAO();

	private ChatRoomDAO() {
	}

	public static ChatRoomDAO getInstance() {
		return instance;
	}

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// 채팅방에 들어갈때 대화상대 코드를 반환함
	public int bringPartnerCode(int chatRoom_code, int loginCode) {
		int tmpSellerCode = bringSellerCode(chatRoom_code, loginCode);
		int tmpBuyerCode = bringBuyerCode(chatRoom_code, loginCode);

		if (tmpSellerCode != -1) {
			return tmpSellerCode;
		} else {
			return tmpBuyerCode;
		}
	}

	// 채팅방 상대방이 구매자일때 구매자코드를 반환함
	private int bringBuyerCode(int chatRoom_code, int loginCode) {
		int partnerCode = -1;

		conn = DbManager.getConnection("potatoMarket");
		try {
			String sql1 = "select * from chatRoom where chat_code = ? and buyer_code = ?";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, chatRoom_code);
			pstmt.setInt(2, loginCode);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				partnerCode = rs.getInt(2);
			}

			System.out.println("파트너 반환 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("파트너 반환 실패");

		} finally {
			rs = null;
			pstmt = null;
			conn = null;
		}

		return partnerCode;
	}

	// 채팅방 상대방이 판매자면 판매자 코드를 반환한다
	private int bringSellerCode(int chatRoom_code, int loginCode) {
		int partnerCode = -1;

		conn = DbManager.getConnection("potatoMarket");
		try {
			String sql1 = "select * from chatRoom where chat_code = ? and seller_code = ?";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, chatRoom_code);
			pstmt.setInt(2, loginCode);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				partnerCode = rs.getInt(3);
			}

			System.out.println("파트너 반환 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("파트너 반환 실패");

		} finally {
			rs = null;
			pstmt = null;
			conn = null;
		}

		return partnerCode;

	}

}
