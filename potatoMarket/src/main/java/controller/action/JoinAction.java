package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import user.UserDAO;
import user.UserDTO;

public class JoinAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO dao = UserDAO.getInstance();
		UserDTO user = null;

		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");

		String address_1 = request.getParameter("address_1");
		String address_2 = request.getParameter("address_2");
		String address = address_1 + "/" + address_2;
		String phone = request.getParameter("phone_number");

		user = new UserDTO(id, password, name, address, phone);
		String url = "";

		if (dao.joinMember(user)) {
			url = "./login_page";
		} else {
			System.out.println(id + name + address_1 + address_2 + phone);
			request.setAttribute("re_id",id);
			request.setAttribute("re_name",name);
			request.setAttribute("re_address_1", address_1);
			request.setAttribute("re_address_2", address_2);
			request.setAttribute("re_phone_number", phone);
			
			
			url = "./join_page?join_check=join_check";
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
