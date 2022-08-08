package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import item.ItemDAO;
import item.ItemDTO;

public class UpdatePicAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int item_code = Integer.parseInt(request.getParameter("item_code"));
		int user_code = Integer.parseInt(request.getParameter("user_code"));
		String item_title = request.getParameter("title");
		String item_contents = request.getParameter("contents");
		String item_pic = request.getParameter("img_Url");
		int item_price = Integer.parseInt(request.getParameter("price"));

		ItemDTO item = new ItemDTO(item_code, user_code, item_title, item_contents, item_pic, item_price);
		boolean chk = ItemDAO.getInstance().uploadItem(item);

		String url = "";
		if (chk) {
			url = "/itemView?code=" + item_code;
		} else {
			url = "/index";
		}

		request.setAttribute("chkWrite", chk);
		request.getRequestDispatcher(url).forward(request, response);
		
		
	}

}
