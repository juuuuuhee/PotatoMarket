package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import favo.FavoriteDAO;
import favo.FavoriteDTO;

public class AddfavoriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		FavoriteDTO dto;
		FavoriteDAO dao = FavoriteDAO.getInstance();
		int usercode = Integer.parseInt(request.getParameter("user_code"));
		int itemcode = Integer.parseInt(request.getParameter("item_code"));
		System.out.println("itc"+itemcode);
		int chk = dao.chkfavo(itemcode, usercode);
		System.out.println("123141"+chk);
		if(chk==-1) {
			boolean addchk = dao.addfav(itemcode, usercode);
			System.out.println(addchk);
			if(addchk) {
				System.out.println("OOO");
			}
			else
				System.out.println("XXX");
		}
		String url="./itemView?code="+itemcode;
		request.getRequestDispatcher(url).forward(request, response);
		
	}

}
