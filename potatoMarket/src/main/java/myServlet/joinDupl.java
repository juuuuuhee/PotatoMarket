package myServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import user.UserDAO;

/**
 * Servlet implementation class joinDupl
 */
//@WebServlet("/joinDupl")
public class joinDupl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public joinDupl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		JSONObject obj = new JSONObject();
		String id = request.getParameter("id");
//		System.out.println("id : "+id);

		UserDAO user = UserDAO.getInstance();
		if (id.equals("")) {
			obj.put("check", 3);
		}
		else if (user.checkId(id) != null) {
			obj.put("check", 1);
		} else if (user.checkId(id) == null) {
			obj.put("check", 2);
		}
		response.setContentType("application/json");
		response.getWriter().print(obj);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
