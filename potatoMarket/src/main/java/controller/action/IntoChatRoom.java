package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import chat.ChatRoomDAO;
import controller.Action;
import user.UserDTO;

public class IntoChatRoom implements Action {

	// 제품 상세페이지에서 대화하기를 눌렀을때 실행된다
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		// 해당 아이템 코드를 불러온다
		int itemCode = Integer.parseInt(request.getParameter("item_code"));

		// session에 저장된 로그인된 '유저 정보'를 가져온다
		UserDTO loginUser = (UserDTO) session.getAttribute("log");
		int loginCode = loginUser.getCode();

		// test
		System.out.println("IntoChatRoom - itemCode : " + itemCode);
		System.out.println("IntoChatRoom - loginCode : " + loginCode);

		ChatRoomDAO chatRoomDAO = ChatRoomDAO.getInstance();
		int chatRoomCode = chatRoomDAO.getChatRoomCode(loginCode, itemCode);

		String url = "./chatView?chatRoom_code=" + chatRoomCode;
		request.getRequestDispatcher(url).forward(request, response);

	}

}
