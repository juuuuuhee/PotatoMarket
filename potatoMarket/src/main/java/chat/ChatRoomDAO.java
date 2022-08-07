package chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Random;

import item.ItemDAO;
import item.ItemDTO;
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

	// 입력한 유저코드와 연관이 있는 모든 채팅방 리스트를 불러온다
	public ArrayList<ChatRoomDTO> bringAllChatRoom(int userCode) {
		System.out.println("시작" + userCode);
		ArrayList<ChatRoomDTO> rooms = new ArrayList<>();
		conn = DbManager.getConnection("potatoMarket");
		try {
			String sql = "select * from chatRoom where buyer_code = ? or seller_code = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userCode);
			pstmt.setInt(2, userCode);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println("한번");
				int chat_code = rs.getInt(1);
				int seller_code = rs.getInt(2);
				int buyer_code = rs.getInt(3);
				int item_code = rs.getInt(4);

				ChatRoomDTO room = new ChatRoomDTO(chat_code, seller_code, buyer_code, item_code);
				rooms.add(room);
			}

			System.out.println("채팅방들 반환 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("채팅방들 반환 실패");

		} finally {
			rs = null;
			pstmt = null;
			conn = null;
		}
		System.out.println(rooms.size());
		return rooms;
	}

	// 채팅방 코드를 반환시킨다
	// 만약 처음 대화를 나누는 상대라면 랜덤한 채팅방코드를 반환한다
	// 만약 이미 대화를 나눈 상대라면 해당 채팅방코드를 반환한다
	public int getChatRoomCode(int userCode, int itemCode) {
		int chatRoomCode = -1;

		// 해당 아이템 판매자 코드를 얻는다
		ItemDTO item = ItemDAO.getInstance().getdata(itemCode);
		int sellerCode = item.getUser_code();

		chatRoomCode = findChatRoomCode(userCode, sellerCode, itemCode);
		if (chatRoomCode == -1) {
			System.out.println("처음으로 대화하는 상대입니다");
			chatRoomCode = rChatRoomCode();
			makeChatRoom(chatRoomCode, userCode, sellerCode, itemCode);
		} else {
			System.out.println("이미 대화를 나눈 상대입니다");
		}

		return chatRoomCode;
	}

	// 나, 상대방, 상품 세가지를 조합해서 데이터베이스에 채팅방을 만든다
	private void makeChatRoom(int chatRoomCode, int userCode, int sellerCode, int itemCode) {
		String sql = "insert into chatRoom values (?, ?, ?, ?, sysdate(), sysdate())";
		conn = DbManager.getConnection("potatoMarket");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, chatRoomCode);
			pstmt.setInt(2, sellerCode);
			pstmt.setInt(3, userCode);
			pstmt.setInt(4, itemCode);

			if (pstmt.executeUpdate() == 1) {
				System.out.println("랜덤한 코드로 채팅방 생성 성공");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("랜덤한 코드로 채팅방 생성 성공");
		} finally {
			rs = null;
			pstmt = null;
			conn = null;
		}

	}

	// 구매자코드, 판매자코드, 아이템 코드 세가지를 조합해서 채팅방이 존재하는지,
	// 존재하면 해당 채팅방코드를 반환한다
	private int findChatRoomCode(int userCode, int sellerCode, int itemCode) {
		int chatRoomCode = -1;

		String sql = "select * from chatRoom where seller_code = ? and buyer_code = ? and item_code = ?";
		conn = DbManager.getConnection("potatoMarket");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sellerCode);
			pstmt.setInt(2, userCode);
			pstmt.setInt(3, itemCode);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("채팅코드가 발견되었습니다");
				chatRoomCode = rs.getInt(1);
			} else {
				System.out.println("발견된 코딩값이 없습니다");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs = null;
			pstmt = null;
			conn = null;
		}

		return chatRoomCode;
	}

	// 채팅하기를 눌렀을때 랜덤한 4자리 채팅코드를 만든다
	private int rChatRoomCode() {
		int rCode = -1;
		Random rand = new Random();
		String sql = "select * from chatRoom where chat_code = ?";
		conn = DbManager.getConnection("potatoMarket");
		try {
			while (true) {
				rCode = rand.nextInt(8999) + 1000;
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, rCode);
				int cnt = pstmt.executeUpdate();
				// 중복된 코드가 없다면 해당 코드를 가진채로 반환한다
				if (cnt == 0) {
					break;
				}
			}

			System.out.println("rChatRoomCode - 랜덤채팅코드 반환 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("rChatRoomCode - 랜덤채팅코드 반환 실패");

		} finally {
			rs = null;
			pstmt = null;
			conn = null;
		}

		return rCode;

	}

	// 채팅방 코드를 이용해서 채팅방 데이터를 불러온다
	public ChatRoomDTO getData(int chatRoom_code) {
		ChatRoomDTO chatRoom = null;
		conn = DbManager.getConnection("potatoMarket");
		try {
			String sql1 = "select * from chatRoom where chat_code = ?";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, chatRoom_code);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int chat_code = rs.getInt(1);
				int seller_code = rs.getInt(2);
				int buyer_code = rs.getInt(3);
				int item_code = rs.getInt(4);

				chatRoom = new ChatRoomDTO(chat_code, seller_code, buyer_code, item_code);
			}

			System.out.println("ChatRoomDTO getData - 채팅방 반환 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ChatRoomDTO getData - 채팅방 반환 실패");

		} finally {
			rs = null;
			pstmt = null;
			conn = null;
		}

		return chatRoom;

	}

	// 한개의 채팅방에서 읽지 않은 채팅의 개수를 구한다
	public int getNotReadNumInChatRoom(int chatRoom_code, int userCode) {
		int cnt = 0;
		String sql = "select notRead_num, recentAdd_code from chatRoom where chat_code = ?";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, chatRoom_code);
			rs = pstmt.executeQuery();
			rs.next();
			int recentAdd_code = rs.getInt(2);

			if (recentAdd_code != userCode) {
				System.out.println("하나의 채팅방 안읽은 개수 불러오기 성공");
				cnt = rs.getInt(1);
			}

		} catch (Exception e) {
			System.out.println("채팅방 안읽은 개수 불러오기 실패");
			e.printStackTrace();
		}
		return cnt;
	}

	public int getNotReadNumInChatRoom2(int chatRoom_code) {
		int cnt = 0;
		String sql = "select notRead_num from chatRoom where chat_code = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, chatRoom_code);
			rs = pstmt.executeQuery();
			rs.next();
			
			cnt = rs.getInt(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	// ** 테스트 메소드 **
	// 채팅방 리스트를 불러올때 쓰는 메소드
	// 한번 쓸때 하나의 채팅방 정보를 담아서 반환한다
	// { "안읽은 개수", "상대방 아이디", "아이템 제목", "아이템사진"}
	public ChatRoomInfo bringChatList(int chatRoom_code, int userCode, int partnerCode) throws SQLException {
		ChatRoomInfo chatRoomInfo = new ChatRoomInfo();
		try {
			int chatNum = getNotReadNumInChatRoom(chatRoom_code, userCode);
			String partnerId = bringPartnerId(chatRoom_code, partnerCode);
			
			ItemDTO itemInfo = getItemData(chatRoom_code);
			String itemTitle = itemInfo.getItem_tilte();
			String itemPic = itemInfo.getItem_pic();

			chatRoomInfo.setNotRead_num(chatNum);
			chatRoomInfo.setPartnerId(partnerId);
			chatRoomInfo.setItemTitle(itemTitle);
			chatRoomInfo.setItemPic(itemPic);

			System.out.println("채팅리스트의 모든 정보를 담는데 성공");
		} catch (Exception e) {
			System.out.println("채팅리스트의 모든 정보를 담는데 실패");
		}

		return chatRoomInfo;
	}

	// 상대 아이디를 불러온다
	private String bringPartnerId(int chatRoom_code, int partnerCode) {
		String partnerId = "";
		String sql = "select user_id from users where user_code = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, partnerCode);
			rs = pstmt.executeQuery();
			rs.next();
			partnerId = rs.getString(1);

		} catch (Exception e) {
		}

		return partnerId;
	}

	// 채팅방코드를 이용해서 아이템 정보를 반환한다
	private ItemDTO getItemData(int chatRoom_code) {
		ItemDTO item = null;
		String sql = "select * from items where item_code = (select item_code from chatRoom where chat_code = ?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, chatRoom_code);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int itemcode = rs.getInt(1);
				int booking_code = rs.getInt(2);
				int user_code = rs.getInt(3);
				String item_title = rs.getString(4);
				String item_contents = rs.getString(5);
				int item_price = rs.getInt(6);
				Timestamp created_At = rs.getTimestamp(7);
				Timestamp modified_At = rs.getTimestamp(8);
				int item_seiling = rs.getInt(9);
				String item_pic = rs.getString(10);
				int cate_code = rs.getInt(11);

				item = new ItemDTO(itemcode, booking_code, cate_code, item_price, item_seiling, user_code, item_title,
						item_contents, item_pic, created_At, modified_At);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return item;
	}

	// chatRoom에서 아직 읽지 않은 모든 메시지의 개수를 구한다;

	// 모든 읽지 않은 채팅의 개수를 구하는 방법
	// 1. 하나의 채팅방에 대해서,
	// 가장 최근의 메시지가 상대방이고, '읽지않음'상태일때 해당 채팅방의 읽지않은 메시지의 개수(notRead_num)를 구한다.
	// chatHistory에서 채팅방코드를 이용해서 가장 최근의 메시지를 검출한다
	// 해당 메시지의 추가자가 로그인한 유저가 아니라면,
	// Q. 근데 너무 번거롭지 않나? 결국 sql을 두번 반복하게 되는건데 ...
	// 2. 유저가 가진 모든 채팅방에 대해서 1번을 반복하면서 개수를 더한다.
	// 3. 그러면 한 명의 유저가 읽지 않은 모든 채팅의 개수를 구할 수 있다.

	// 채팅리스트에서도 상단의 1번만 적용하면 되기 때문에 메소드를 재활용 할 수 있다.
	// 먼저 모든 채팅방에 대해서 정보를 반환하는 함수를 작성하도록 하자

	public ArrayList<ChatRoomInfo> bringAllChatRoomInfos(ArrayList<ChatRoomDTO> rooms, int loginCode) {
		ArrayList<ChatRoomInfo> chatRoomInfos = new ArrayList<>();
		conn = DbManager.getConnection("potatoMarket");
		try {
			for (int i = 0; i < rooms.size(); i++) {
				ChatRoomDTO room = rooms.get(i);

				int seller_code = room.getSeller_code();
				int buyer_code = room.getBuyer_code();
				int partnerCode = seller_code == loginCode ? buyer_code : seller_code;

				ChatRoomInfo chatRoomInfo = bringChatList(rooms.get(i).getChat_code(), loginCode, partnerCode);
				chatRoomInfos.add(chatRoomInfo);

			}
			System.out.println("모든 채팅리스트 불러오기 성공");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("모든 채팅리스트 불러오기 실패");
		} finally {
			try {
				if (rs != null)
					rs.close();
				else if (pstmt != null)
					pstmt.close();
				else if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}

		return chatRoomInfos;
	}

	// header.jsp에서 아직 읽지 않은 모든 메시지 개수를 계산해서 반환한다
	public int getNotReadNum(int userCode) {
		int cnt = 0;
		// 로그인한 유저의 모든 채팅방 리스트를 불러온다
		ArrayList<ChatRoomDTO> rooms = ChatRoomDAO.getInstance().bringAllChatRoom(userCode);
		conn = DbManager.getConnection("potatoMarket");
		try {
			for (int i = 0; i < rooms.size(); i++) {
				ChatRoomDTO room = rooms.get(i);
				cnt += getNotReadNumInChatRoom(room.getChat_code(), userCode);
				System.out.println("cnt : "+ cnt);
			}

			System.out.println("header 안읽은 채팅개수 불러오기 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("header 안읽은 채팅개수 불러오기 실패");
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

		return cnt;
	}

	// chatRoom 데이터베이스에서 안읽은 채팅개수를 1개 늘린다
	public void plusNotRead_Num(int chatRoom_code, int logCode) {
		conn = DbManager.getConnection("potatoMarket");
		String sql = "update chatRoom set notRead_num = ?, recentAdd_code = ? where chat_code = ?";
		try {
			int cnt = getNotReadNumInChatRoom2(chatRoom_code) + 1;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cnt);
			pstmt.setInt(2, logCode);
			pstmt.setInt(3, chatRoom_code);
			pstmt.execute();
			System.out.println("cnt : " + cnt);
			
			System.out.println("안읽은 개수 하나 늘리기 성공");
		} catch (Exception e) {
			System.out.println("안읽은 개수 하나 늘리기 실패");
			
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
