<%@page import="java.text.DecimalFormat"%>
<%@page import="user.UserDTO"%>
<%@page import="user.UserDAO"%>
<%@page import="item.ItemDTO"%>
<%@page import="item.ItemDAO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Title</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<link rel="stylesheet" href="./css/itemView.css">
</head>
<body>
	<%
	// 아이템 정보 읽어오기
	ItemDAO dao = ItemDAO.getInstance();
	String item_code = request.getParameter("code");
	ItemDTO thisItem = dao.getItem(Integer.parseInt(item_code));
	int sell = thisItem.getItem_seiling();
	
	// session에서 유저코드 읽어오기
	UserDAO uDao = UserDAO.getInstance();
	int user_code = dao.getdata(Integer.parseInt(item_code)).getUser_code();
	UserDTO loginUser = (UserDTO) session.getAttribute("log");
	int loginCode = -1;
	if (loginUser != null) {
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
				<div class="user_id_name">
					<%=uDao.getUser(user_code).getId()%>님 상품
				</div>
				<hr>
				<div class="detail_text_text">
					<%=thisItem.getItem_contents()%>
				</div>
				<div class="detail_item_price" id="result2">
				<%String str = decFormat.format(thisItem.getItem_price()); %>
					<%=str%>원
				</div>
				<div class="detail_item_name">
					<%=thisItem.getItem_tilte()%>
				</div>
			</div>
			<%
			System.out.println(loginCode);
			System.out.println(user_code);
			if (thisItem.getItem_seiling() == 2) {
			%>
			<h3>이미 판매가 완료된 상품입니다.</h3>
			<%
			} else if (user_code != loginCode && loginCode != -1 && sell != 1) {
			%>
			<form action="./action" method="post" class="submit_form">
				<input type="hidden" name="command" id="command" value="intoChatRoom"> 
				<input type="hidden" name="item_code" value="<%=item_code%>"> 
				<input type="hidden" name = "user_code" value="<%=loginCode %>">
				<input id="button_chat" type="submit" value="채팅하기">
				<button type="button" class="button_changeCommand" onclick="changeFavo()">찜하기</button>
			</form>
			<%
			} else if (user_code == loginCode) {
			%>
			<form action="./action" method="post" class="submit_form">
				<input type="hidden" name="command" value="deleteItem">
				<input type="hidden" name="item_code" value="<%=item_code%>">
				<input type="submit" value="삭제하기">
			</form>
			<%
			}
			%>
		</div>

	</div>
	<div class="footer">
		<%@include file="../modules/footer.jsp"%>
	</div>
</body>
<script src="script/itemView.js"></script>
</html>