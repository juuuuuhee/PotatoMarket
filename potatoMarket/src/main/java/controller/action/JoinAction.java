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
		UserDAO dao=UserDAO.getInstance();
		UserDTO user=null;
		
		String phone_1=request.getParameter("contact_1");
		String phone_2=request.getParameter("contact_2");
		String phone_3=request.getParameter("contact_3");
		String phone=phone_1+phone_2+phone_3;
		
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		String name=request.getParameter("name");
		
		String address_1=request.getParameter("address_1");
		String address_2=request.getParameter("address_2");
		String address=address_1+"/"+address_2;
		
		user = new UserDTO(id, password, name, address,phone );
		String url="";
		
		if(dao.joinMember(user)) {
			url="./login_page";
		}else {
			System.out.print("ddddddddd");
			url="./join_page";
		}
	}
	
}
