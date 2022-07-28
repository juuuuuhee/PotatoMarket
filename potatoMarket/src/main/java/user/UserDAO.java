package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DbManager;

public class UserDAO {

	private static UserDAO instance = new UserDAO();
	
	public static UserDAO getInstance() {
		return instance;
	}
	private Connection conn= null;
	private ResultSet rs =null;
	private PreparedStatement pstmt = null;
	
	//유저의 정보 가져오기(mypage)
	public UserDTO getUserData(int code) {
		String sql = "select * from users where user_code =?";
		conn = DbManager.getConnection("potatoMarket");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, code);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int ucode = rs.getInt(1);
				String id = rs.getString(2);
				//String pw = rs.getString(3);
				String name = rs.getString(4);
				String add = rs.getString(5);
				//String time = rs.getString(6);
				//String time2 = rs.getString(7);
				String phone = rs.getString(8);
				
				UserDTO dto = new UserDTO(ucode, id, name,add,phone);
				return dto;
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("실패");
		}
		finally {
			
		}try {
			conn.close();
			pstmt.close();
			rs.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
}
