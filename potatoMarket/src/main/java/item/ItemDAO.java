
package item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import util.Code;
import util.DbManager;

public class ItemDAO {

	private ItemDAO() {
	}

	private static ItemDAO instance = new ItemDAO();

	public static ItemDAO getInstance() {
		return instance;
	}

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// 모든 아이템 정보를 불러온다
	public ArrayList<ItemDTO> getAllItemList() {
		ArrayList<ItemDTO> itemList = new ArrayList<ItemDTO>();
		ItemDTO item;

		conn = DbManager.getConnection("potatoMarket");

		try {
			String sql = "select * from items";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				int item_code = rs.getInt(1);
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

				item = new ItemDTO(item_code, booking_code, cate_code, item_price, item_seiling, user_code, item_title,
						item_contents, item_pic, created_At, modified_At);
				System.out.print(item.getCate_code() + "????");
				itemList.add(item);
			}
			return itemList;
		} catch (Exception e) {
			System.out.println("?");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}

	// 아이템코드를 이용해서 아이템정보를 불러온다
	public ItemDTO getItem(int itemcode) {
		ItemDTO item = null;
		conn = DbManager.getConnection("potatoMarket");

		try {
			String sql = "select * from items where item_code = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, itemcode);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				itemcode = rs.getInt(1);
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

			return item;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}

	// 키워드를 포함하고 있는 모든 아이템들을 불러온다
	public ArrayList<ItemDTO> getSearchResult(String keyword) {

		ArrayList<ItemDTO> itemList = new ArrayList<ItemDTO>();
		ItemDTO item;

		conn = DbManager.getConnection("potatoMarket");

		try {
			String sql = "select * from items where (item_title like ? or item_contents like ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, '%' + keyword + '%');
			pstmt.setString(2, '%' + keyword + '%');
			rs = pstmt.executeQuery();

			while (rs.next()) {

				int item_code = rs.getInt(1);
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

				item = new ItemDTO(item_code, booking_code, cate_code, item_price, item_seiling, user_code, item_title,
						item_contents, item_pic, created_At, modified_At);

				itemList.add(item);
			}
			return itemList;
		} catch (Exception e) {
			System.out.println("?");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}

	// mypage 찜 아이템 보이기
	public ItemDTO getdata(int itemcode) {
		String sql = "select * from items where item_code = ?";
		try {
			conn = DbManager.getConnection("potatoMarket");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, itemcode);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int code = rs.getInt(1);
				int bcode = rs.getInt(2);
				int usercode = rs.getInt(3);
				String title = rs.getString(4);
				String contents = rs.getString(5);
				int price = rs.getInt(6);
				int sellchk = rs.getInt(9);
				String picture = rs.getString(10);
				int catecode = rs.getInt(11);

				ItemDTO dto = new ItemDTO(code, bcode, usercode, title, contents, price, sellchk, picture, catecode);

				return dto;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}

	// user가 판매하는 아이템 목록 메소드
	public ArrayList<ItemDTO> getOrderList(int user_code) {
		ArrayList<ItemDTO> orderItemList = new ArrayList<ItemDTO>();
		String sql = "select * from items where user_code = ?";
		conn = DbManager.getConnection("potatoMarket");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user_code);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int code = rs.getInt(1);
				int bcode = rs.getInt(2);
				int usercode = rs.getInt(3);
				String title = rs.getString(4);
				String contents = rs.getString(5);
				int price = rs.getInt(6);
				int sellchk = rs.getInt(9);
				String picture = rs.getString(10);
				int catecode = rs.getInt(11);

				ItemDTO dto = new ItemDTO(code, bcode, usercode, title, contents, price, sellchk, picture, catecode);

				orderItemList.add(dto);
			}
			return orderItemList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}

	// user가 구매하는 아이템 목록 메소드
	public ArrayList<ItemDTO> getBookingList(int user_code) {
		ArrayList<ItemDTO> orderItemList = new ArrayList<ItemDTO>();
		String sql = "select * from items where orderuser_code = ?";
		conn = DbManager.getConnection("potatoMarket");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user_code);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int code = rs.getInt(1);
				int bcode = rs.getInt(2);
				int usercode = rs.getInt(3);
				String title = rs.getString(4);
				String contents = rs.getString(5);
				int price = rs.getInt(6);
				int sellchk = rs.getInt(9);
				String picture = rs.getString(10);
				int catecode = rs.getInt(11);
				int orderusercode = rs.getInt(12);

				ItemDTO dto = new ItemDTO(code, bcode, usercode, title, contents, price, sellchk, picture, catecode,
						orderusercode);

				orderItemList.add(dto);
			}
			return orderItemList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}

	// 아이템 '등록하기' 버튼을 눌렀을때 데이터베이스에 아이템이 저장된다
	public boolean uploadItem(ItemDTO item) {
		boolean chk = false;
		String sql = "insert into items values (?, null, ?, ?, ?, ?, sysdate(), sysdate(), 0, ?, 1, null)";
		try {
			conn = DbManager.getConnection("potatoMarket");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, item.getItem_code());
			pstmt.setInt(2, item.getUser_code());
			pstmt.setString(3, item.getItem_tilte());
			pstmt.setString(4, item.getItem_contents());
			pstmt.setInt(5, item.getItem_price());
			pstmt.setString(6, item.getItem_pic());

			pstmt.execute();
			chk = true;
			System.out.println("아이템 업데이트 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("아이템 업데이트 실패");
		}

		return chk;
	}

	// 아이템 랜덤코드를 반환한다
	public int getRCode() {
		Code code = new Code();
		int rCode = -1;
		conn = DbManager.getConnection("potatoMarket");
		String sql = "select * from items where item_code = ?";

		try {
			while (true) {
				pstmt = conn.prepareStatement(sql);

				rCode = code.rCode();
				pstmt.setInt(1, rCode);
				rs = pstmt.executeQuery();
				if (!rs.next()) {
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("아이템 랜덤코드 반환 실패");

		}

		System.out.println("아이템 랜덤코드 반환 성공");
		return rCode;

	}

	// 아이템을 데이터베이스에서 지운다
	public boolean deleteItem(int item_code) {
		boolean chk = false;
		conn = DbManager.getConnection("potatoMarket");
		String sql = "delete from items where item_code = ?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, item_code);
			pstmt.execute();
			chk = true;
			System.out.println("삭제 완료");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("삭제 실패");

		}
		return chk;

	}

}
