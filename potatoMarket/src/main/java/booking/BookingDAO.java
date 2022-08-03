package booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import util.DbManager;

public class BookingDAO {
	
private static BookingDAO instance = new BookingDAO();
	
	public static BookingDAO getInstance() {
		return instance;
	}
	
	private Connection conn = null;
	private ResultSet rs = null;
	private PreparedStatement pstmt = null;
	
	
	// 판매 목록
	public ArrayList<BookingDTO> getBookingList(int user_codeA){
		ArrayList<BookingDTO> bookingList = new ArrayList<BookingDTO>();
		String sql = "select * from booking where user_codeA = ?";
		conn = DbManager.getConnection("potatoMarket");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, user_codeA);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int bookingcode = rs.getInt(1);
				int item_code = rs.getInt(2);
				int x = rs.getInt(3);
				int y = rs.getInt(4);
				int ucodeA =rs.getInt(5);
				int ucodeB = rs.getInt(6);
				Timestamp created_at = rs.getTimestamp(7);
				Timestamp modified_at = rs.getTimestamp(8);
				
				BookingDTO dto = new BookingDTO(bookingcode, item_code, x,y,ucodeA,ucodeB,created_at,modified_at);
				
				bookingList.add(dto);
			}
			return bookingList;
		}catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	// 구매 목록
	public ArrayList<BookingDTO> getOrderList(int user_codeB){
		ArrayList<BookingDTO> bookingList = new ArrayList<BookingDTO>();
		String sql = "select * from booking where user_codeB = ?";
		conn = DbManager.getConnection("potatoMarket");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, user_codeB);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int bookingcode = rs.getInt(1);
				int item_code = rs.getInt(2);
				int x = rs.getInt(3);
				int y = rs.getInt(4);
				int ucodeA =rs.getInt(5);
				int ucodeB = rs.getInt(6);
				Timestamp created_at = rs.getTimestamp(7);
				Timestamp modified_at = rs.getTimestamp(8);
				
				BookingDTO dto = new BookingDTO(bookingcode, item_code, x,y,ucodeA,ucodeB,created_at,modified_at);
				
				bookingList.add(dto);
			}
			return bookingList;
		}catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	

}
