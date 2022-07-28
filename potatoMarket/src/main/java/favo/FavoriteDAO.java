package favo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DbManager;



public class FavoriteDAO {
	
private static FavoriteDAO instance = new FavoriteDAO();
	
	public static FavoriteDAO getInstance() {
		return instance;
	}
	private Connection conn= null;
	private ResultSet rs =null;
	private PreparedStatement pstmt = null;
	
	
	// 관심 목록 가져오기 (mypage) 배열형태로 가져가야함
	public FavoriteDTO getFavoData(int usercode) {
		String sql ="select * from favo where user_code =?";
		conn= DbManager.getConnection("potatoMarket");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, usercode);
			rs=pstmt.executeQuery();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return  null;
	}
}
