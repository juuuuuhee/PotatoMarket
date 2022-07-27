package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbManager {	
	
	public static Connection getConnection(String database) {
		Connection conn = null;
		String url = String.format("jdbc:mysql://database-1.cf3iiz1fyl1v.ap-northeast-2.rds.amazonaws.com:3306/%s?serverTimezone=UTC",database);
		String user = "admin";
		String password = "KU9K5s18g!";

		
		// DBManager.getConnection(); 
				// ㄴ 외부 클래스에서 디비 연동이 필요할때, 언제든 connection을 얻어갈수 있다.

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,password);
			System.out.println("DB연동 성공");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB연동 실패");
			return null;
		}
	}
}
