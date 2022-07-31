package item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import util.DbManager;

public class ItemDAO {
private static ItemDAO instance = new ItemDAO();
	
	public static ItemDAO getInstance() {
		return instance;
	}
	private Connection conn= null;
	private ResultSet rs =null;
	private PreparedStatement pstmt = null;
	
	
	// mypage 찜 아이템 보이기
	public ItemDTO getdata(int itemcode) {
		String sql = "select * from items where item_code = ?";
		try {
			conn=DbManager.getConnection("potatoMarket");
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, itemcode);
			rs= pstmt.executeQuery();
			while(rs.next()) {
				int code = rs.getInt(1);
				int bcode = rs.getInt(2);
				int usercode = rs.getInt(3);
				String title = rs.getString(4);
				String contents = rs.getString(5);
				int price = rs.getInt(6);
				Timestamp ctime = rs.getTimestamp(7);
				Timestamp mtime = rs.getTimestamp(8);
				int sellchk = rs.getInt(9);
				String picture = rs.getString(10);
				int catecode = rs.getInt(11);
				
				ItemDTO dto = new ItemDTO(code, bcode, usercode, title, contents, price, sellchk, picture, catecode);
				
				return dto;
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
}
