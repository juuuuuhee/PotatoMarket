package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import user.UserDAO;
import user.UserDTO;

public class UpdatedataAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserDAO dao = UserDAO.getInstance();
		UserDTO user = null;
		String codeS = request.getParameter("code");
		int code =Integer.parseInt(codeS);
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
		user = new UserDTO(code,id, password, name, address,phone );
		
		String url ="";
		if(dao.updateUserData(user)) {
			System.out.println("성공");
			url="./myPage";
		}else {
			System.out.println("실패");
			url="./profileUpdate";
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	

}
