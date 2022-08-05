
package favo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
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
		finally {
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
	
	public boolean addfav(int itemcode, int usercode) {
		int favocode = lastcode();
		String sql ="insert into favo(favo_code, item_code, user_code, created_At) values(?,?,?,?)";
		conn=DbManager.getConnection("potatoMarket");
		Timestamp time = new Timestamp(System.currentTimeMillis());
		int item = itemcode;
		int user = usercode;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, favocode);
			pstmt.setInt(2, item);
			pstmt.setInt(3, user);
			pstmt.setTimestamp(4,time);
			
			int chk = pstmt.executeUpdate();
			if(chk!=-1) {
				System.out.println("추가 성공");
				return true;
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("실패");
		}
		finally {
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
	
	public int lastcode() {
		int code =-1 ;
		String sql ="select favo_code from favo ";
		conn = DbManager.getConnection("potatoMarket");
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int chk = rs.getInt(1);
				if(chk>=code) {
					code=chk;
				}
				
				System.out.println("code" + code);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		code++;
		return code;
	}
	public int chkfavo(int itemcode , int usercode) {
		int chk =-1;
		String sql = "select * from favo where item_code = ? and user_code = ? ";
		try {
			conn=DbManager.getConnection("potatoMarket");
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, itemcode);
			pstmt.setInt(2, usercode);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				chk =rs.getInt(1);
				System.out.println("chk1"+chk);
			}
			if(chk!=-1) {
				System.out.println("검색결과 O");
				return chk;
			}else
				System.out.println("검색결과x");
		}catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return -1;
	}
}
