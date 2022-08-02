package item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import user.UserDTO;
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

	public ArrayList<ItemDTO> getSearchResult(String keyword) {

		ArrayList<ItemDTO> itemList = new ArrayList<ItemDTO>();
		ItemDTO item;

		conn = DbManager.getConnection("potatoMarket");

		try {
			String sql = "select * from items where (item_title like ? and ?)";
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
	
	// 아이템 코드를 이용해서 해당 아이템 정보 반환
	public ItemDTO getItem(int itemCode) {
		ItemDTO item = null;
		
		conn = DbManager.getConnection("potatoMarket");

		try {
			String sql = "select * from items where item_code = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, itemCode);
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
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

			}
			System.out.println("아이템 정보 반환 성공");
		} catch (Exception e) {
			System.out.println("아이템 정보 반환 실패");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch (Exception e2) {
			}
		}
		
		return item;
		
		
	}

}
