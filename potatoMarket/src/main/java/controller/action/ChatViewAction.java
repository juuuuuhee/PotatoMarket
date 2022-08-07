package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import chat.ChatHistoryDAO;
import chat.ChatRoomDAO;
import controller.Action;
import user.UserDTO;

public class ChatViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int chatRoom_code = Integer.parseInt(request.getParameter("chatRoom_code"));

		// 유저 정보 가져온다
		UserDTO loginUser = (UserDTO) session.getAttribute("log");
		int loginCode = loginUser.getCode();
		
		// 읽음 처리되는 채팅의 개수를 구한다
		int cntChange = ChatRoomDAO.getInstance().getNotReadNumInChatRoom(chatRoom_code, loginCode);
		
		// chatHistory에서 채팅 읽음 처리를 한다(DB)
		int partnerCode = ChatRoomDAO.getInstance().bringPartnerCode(chatRoom_code, loginCode);
		ChatHistoryDAO chatHistoryDAO = new ChatHistoryDAO();
		chatHistoryDAO.changeReadChat(chatRoom_code, partnerCode);
		
		// chatRoom에서 notRead_num을 초기화한다
		chatHistoryDAO.changeNotRead_num(chatRoom_code, loginCode);
		
		// 이동
		String url = "/chatView";
		request.getRequestDispatcher(url).forward(request, response);
	}

}
