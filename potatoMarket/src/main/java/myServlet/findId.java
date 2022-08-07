package myServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import user.UserDAO;

/**
 * Servlet implementation class findId
 */
//@WebServlet("/findId")
public class findId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public findId() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject obj = new JSONObject();
		String name = request.getParameter("help_name");
		String phone = request.getParameter("help_phone");
		
		System.out.println(name);
		System.out.println(phone);
		UserDAO user = UserDAO.getInstance();
		if (user.findId(name, phone) == null) {
			obj.put("check", 1);
			return;
		} else {
			obj.put("check", 2);
		}
		response.setContentType("application/json");
		response.getWriter().print(obj);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
