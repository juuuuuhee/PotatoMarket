package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import user.UserDAO;
import user.UserDTO;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO uDao = UserDAO.getInstance();

		String id = request.getParameter("id");
		String password = request.getParameter("password");

		UserDTO dto = uDao.getUser(id, password);

		String url = "";

		if (dto != null) {
			request.getSession().setAttribute("members", dto);
			url = "index.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		} else {
			url = "./login_page?check=check";
			request.getRequestDispatcher(url).forward(request, response);
		}
	}
}
