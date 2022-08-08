package myServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import user.UserDAO;

/**
 * Servlet implementation class findPw
 */
//@WebServlet("/findPw")
public class findPw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public findPw() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject obj = new JSONObject();
		String id = request.getParameter("help_id");
		String phone = request.getParameter("help_phone1");
		
		UserDAO user = UserDAO.getInstance();
		String pw = user.findPw(id, phone);
		if (user.findPw(id, phone) == null) {
			obj.put("pw", "");
		} else {
			obj.put("pw", pw);
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
