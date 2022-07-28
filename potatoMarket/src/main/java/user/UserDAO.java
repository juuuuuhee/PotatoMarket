package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import util.Code; 
import util.DbManager;

public class UserDAO {
	private UserDAO() {
	}

	private static UserDAO instance = new UserDAO();
	private static int log;

	public static UserDAO getInstance() {
		return instance;
	}

	public int getLog() {
		return log;
	}

	private Code co = new Code();
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// join member
	public boolean joinMember(UserDTO userDto) {
		this.conn = DbManager.getConnection("potatoMarket");

		try {
			userDto.setCreatedAt(new Timestamp(System.currentTimeMillis()));

			String sql = "insert into users values(?,?,?,?,?,?,?)";
			this.pstmt = conn.prepareStatement(sql);
			// code, id, pw, name, add, create, modifi, phone
			pstmt.setInt(1, userDto.getCode());
			pstmt.setString(2, userDto.getId());
			pstmt.setString(3, userDto.getPw());
			pstmt.setString(4, userDto.getName());
			pstmt.setString(5, userDto.getAddress());
			pstmt.setTimestamp(6, userDto.getCreatedAt());
			pstmt.setString(8, userDto.getPhone());

			this.pstmt.execute();
			System.out.println("joinMember complite");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("joinMember fail");
		} finally {
			try {

				conn.close();
				this.pstmt.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}

	public int userCode() {
		conn = DbManager.getConnection("potatoMarket"); // ssm db에 접속
		int code = 0;
		try {
			String sql = "select * from users where code = ?";
			code = co.rCode(); // code 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, code);
			rs = pstmt.executeQuery(); // 쿼리를 날리고 그에 대한 답변을 RS에 저장
			while (rs.next()) {
				code = co.rCode();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, code); // code에 1000~9999번호 중 하나 추가
				rs = pstmt.executeQuery();
				// db에 있는 code를 가져오기
			}

			return code;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return -1;
	}

	// login member
	public UserDTO getUser(String id, String password) {
		UserDTO user = null;
		conn = DbManager.getConnection("potatoMarket");

		try {
			String sql = "select * from users where  id = ? and password = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int user_code = rs.getInt(1);
				id = rs.getString(2);
				password = rs.getString(3);
				String name = rs.getString(4);
				String address = rs.getString(5);
				String phone = rs.getString(8);

				user = new UserDTO(user_code, id, password, name, address, phone);

				log = user_code;
				return user;
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
		return user;
	}

	public boolean checkId(String id) {
		conn = DbManager.getConnection("potatoMarket");
		try {
			String sql = "select * from users where user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;
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
		return false;
	}

}
