package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import user.UserDAO;

public class FindIdAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO uDao = UserDAO.getInstance();
		
		String help_name =request.getParameter("help_name");
		String help_phone = request.getParameter("help_phone");
		
		String dto = uDao.findId(help_name, help_phone);
		
		String url = "";
		
		if(dto != null) {
			
		}else {
			
		}
	}
	
}
