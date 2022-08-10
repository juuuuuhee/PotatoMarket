package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import item.ItemDAO;

public class DeleteItemMypage implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int item_code = Integer.parseInt(request.getParameter("item_code"));
		int user_code = Integer.parseInt(request.getParameter("user_code"));
		System.out.println("item_code : " + item_code);
		System.out.println("user_code : " + user_code);
		
		boolean chk = ItemDAO.getInstance().deleteItem(item_code);
		request.setAttribute("delete", chk);
		
		
		request.getRequestDispatcher("./orderdList?code="+user_code).forward(request, response);
		
	}

}
