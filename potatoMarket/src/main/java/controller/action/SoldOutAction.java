package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import chat.ChatRoomDAO;
import controller.Action;

public class SoldOutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 채팅방에서 '판매완료' 버튼을 눌렀을때 상품이 '판매완료'처리가 된다

		int chatRoom_code = Integer.parseInt(request.getParameter("chatRoom_code"));

		boolean chk = ChatRoomDAO.getInstance().updateSoldOut(chatRoom_code);
		request.setAttribute("chk", chk);

		String url = "";
		if (chk) {
			url = "./chatList";
		} else {
			url = "/chatView";
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
