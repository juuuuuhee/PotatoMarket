<%@page import="user.UserDTO"%>
<%@page import="item.ItemDTO"%>
<%@page import="booking.BookingDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="booking.BookingDAO"%>
<%@page import="item.ItemDAO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Title</title>
<link rel="stylesheet" href="css/orderdList.css">
<link rel="stylesheet" href="css/mypage.css">
</head>
<body>
	<%
	UserDTO loginUser = (UserDTO) session.getAttribute("log");
	int loginCode = loginUser.getCode();

	ItemDAO idao = ItemDAO.getInstance();

	ArrayList<ItemDTO> list = idao.getOrderList(loginCode);
	%>
	<div>
		<%@include file="../modules/header.jsp"%>
	</div>

	<div class="contents_wrap">
		<div>
			<button class="mypage" onclick="location='./myPage'">내 정보</button>
			<input class="mypage selected" type="button" value="나의 판매 목록" onclick="location='./orderdList'">
			<input class="mypage" type="button" value="나의 구매 목록" onclick="location='./bookingList'">
			<input class="mypage" type="button" value="나의 찜 목록" onclick="location='./favoList'">
		</div>
		<p class="title logo">나의 판매 목록</p>
		<div class="orderds_wrap">
			<%
			for (int i = 0; i < list.size(); i++) {
			%>
			<div class="orderd-top">
				<a class="orderd_img_href" href="./itemView?code=<%=list.get(i).getItem_code()%>" style="cursor: pointer">
					<div class="orderd-photo">
					<%
						if (list.get(i).getItem_seiling() == 0) {
						%>
						<img id="orderd_img_size" src="<%=list.get(i).getItem_pic()%>">
						<%} else if (list.get(i).getItem_seiling() == 1) {%>
						<img id="orderd_img_size" src="<%=list.get(i).getItem_pic()%>" style="filter: brightness(35%)">
						<%
						} 
						%>
					</div>
					<div id="orderd_contents">
						<div><%=list.get(i).getItem_tilte()%></div>
						<div><%=list.get(i).getItem_price()%>원</div>
						<%
						if (list.get(i).getItem_seiling() == 0) {
						%>
						<div>판매중</div>
						<%} else if (list.get(i).getItem_seiling() == 1) {%>
						<div>판매완료</div>
						<%
						} 
						%>
						<form action="./action" method="post">
							<input type="hidden" name="command" value="deleteItem">
							<input type="hidden" name="item_code" value="<%=list.get(i).getItem_code()%>">
							<input type="submit" value="삭제하기">
						</form>
					</div>
				</a>
			</div>
			<%
			}
			%>
		</div>
	</div>
	<div class="footer">
		<%@include file="../modules/footer.jsp"%>
	</div>


</body>

</html>
