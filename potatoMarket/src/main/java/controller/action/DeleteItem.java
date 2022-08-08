package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Action;
import item.ItemDAO;

public class DeleteItem implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int item_code = Integer.parseInt(request.getParameter("item_code"));
		System.out.println("item_code : " + item_code);
		
		boolean chk = ItemDAO.getInstance().deleteItem(item_code);
		request.setAttribute("delete", chk);
		
		request.getRequestDispatcher("/").forward(request, response);
		
	}

}
