<%@page import="user.UserDTO"%>
<%@page import="user.UserDAO"%>
<%@page import="item.ItemDTO"%>
<%@page import="item.ItemDAO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
	<link rel="stylesheet" href="css/itemView.css"><title>Title</title>
</head>
<body>
	<%
	ItemDAO dao = ItemDAO.getInstance();
	String item_code = request.getParameter("code");
	ItemDTO thisItem = dao.getItem(Integer.parseInt(item_code));
	UserDAO uDao = UserDAO.getInstance();
	int user_code = dao.getdata(Integer.parseInt(item_code)).getUser_code();
	UserDTO loginUser = (UserDTO) session.getAttribute("log");
	int loginCode = -1;
	if(loginUser != null) {
		loginCode = loginUser.getCode();
		
	}
	%>
	<div>
		<%@include file="../modules/header.jsp"%>
	</div>
	<div class="contents_detail">
		<div class="contents_wrap">
			<div>
				<div class="detail_img">
					<img id="img_size" alt="" src="<%=thisItem.getItem_pic()%>">
				</div>
			</div>
			<div id="contents_d">
				<div>
					<%=uDao.getUser(user_code).getId()%>님 물건
				</div>
				<div class="detail_text_text">
					<%=thisItem.getItem_contents()%>
				</div>
				<div class="detail_item_name">
					<%=thisItem.getItem_tilte()%>
				</div>
				<div class="detail_item_price">
					<span>₩<%=thisItem.getItem_price()%></span>
				</div>
			</div>
			<%
			System.out.println(loginCode);
			System.out.println(user_code);
			if (user_code != loginCode && loginCode != -1 && thisItem.getItem_seiling() != 2) {
			%>
		<form action="./action" >
			<input type="hidden" name="command" value="intoChatRoom"> <input
				type="hidden" name="item_code" value="<%=item_code%>">
			<input id="button_chat" type="submit" value="채팅하기">
			<%}%>
		</form>
		</div>

	</div>
	<div class="footer">
		<%@include file="../modules/footer.jsp"%>
	</div>
</body>
</html>
