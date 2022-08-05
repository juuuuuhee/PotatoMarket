package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import favo.FavoriteDAO;

public class delfavoriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		int favocode =Integer.parseInt(request.getParameter("favocode")) ;
		FavoriteDAO dao =FavoriteDAO.getInstance();
		
		boolean chk = dao.delfavodata(favocode);
		System.out.println("chk"+chk);
		String url="./favolist?code="+favocode;
		request.getRequestDispatcher(url).forward(request, response);
		
	}

}
