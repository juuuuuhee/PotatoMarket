package controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import chat.ChatRoomDAO;
import chat.ChatRoomDTO;
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
			request.getSession().setAttribute("log", dto);
			url = "./itemList";

			// 로그인할때 session에 채팅방 리스트들을 'rooms'로 올린다
			// header에서 채팅방 알람기능을 사용해서
			ArrayList<ChatRoomDTO> rooms = ChatRoomDAO.getInstance().bringAllChatRoom(dto.getCode());
			HttpSession session = request.getSession();

			// 안읽은 채팅 리스트를 session에 'cnt'로 저장한다
			session.setAttribute("rooms", rooms);
			int cnt = 0;
			for (int i = 0; i < rooms.size(); i++) {
				ChatRoomDTO room = rooms.get(i);
				cnt += ChatRoomDAO.getInstance().getNotReadNumInChatRoom(room.getChat_code(), dto.getCode());
			}
			session.setAttribute("cnt", cnt);

			request.getRequestDispatcher(url).forward(request, response);
		} else {
			url = "./login_page";
			request.getRequestDispatcher(url).forward(request, response);
		}
	}
}
