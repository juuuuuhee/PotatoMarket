
package favo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DbManager;

public class FavoriteDAO {

	private static FavoriteDAO instance = new FavoriteDAO();

	public static FavoriteDAO getInstance() {
		return instance;
	}

	private Connection conn = null;
	private ResultSet rs = null;
	private PreparedStatement pstmt = null;

	// 관심 목록 가져오기 (mypage) 배열형태로 가져가야함
	public ArrayList<FavoriteDTO> getFavoData(int usercode) {
		String sql = "select * from favo where user_code =?";
		ArrayList<FavoriteDTO> list = new ArrayList<FavoriteDTO>();
		conn = DbManager.getConnection("potatoMarket");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, usercode);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int fcode = rs.getInt(1);
				int icode = rs.getInt(2);
				int ucode = rs.getInt(3);

				FavoriteDTO dto = new FavoriteDTO(fcode, icode, ucode);
				list.add(dto);
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public boolean delfavodata(int favo_code) {
		String sql = "delete from favo where favo_code =? ";
		conn=DbManager.getConnection("potatoMarket");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, favo_code);
			boolean chk = pstmt.execute();
			if(!chk) {
				System.out.println("삭제 성공");
				return true;
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("삭제 실패");
			e.printStackTrace();
			
		}
		return false;
	}
	
}
