<%@page import="user.UserDTO"%>
<%@page import="item.ItemDTO"%>
<%@page import="booking.BookingDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="booking.BookingDAO"%>
<%@page import="item.ItemDAO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
	<link rel="stylesheet" href="css/orderdList.css">
	<link rel="stylesheet" href="css/mypage.css">
	<link rel="stylesheet" href="css/favolist.css">
	<title>예약목록</title>
</head>
<body>
	<%
	UserDTO loginUser = (UserDTO) session.getAttribute("log");
	int loginCode = loginUser.getCode();
	
	ItemDAO idao = ItemDAO.getInstance();
	BookingDAO bdao = BookingDAO.getInstance();
	
	ArrayList<BookingDTO> list = bdao.getBookingList(loginCode);
	
	%>
	<div>
		<%@include file="../modules/header.jsp"%>
	</div>
	<button class="mypage" onclick="location='./myPage'">내 정보</button>
	<button class="mypage" onclick="location='./orderdList'">나의 판매 목록</button>
	<button class="mypage" onclick="location='./favoList'">나의 찜 목록</button>
	
	<p class="logo title">나의 예약목록</p>

	<div class="contents_wrap">
		<div class="pagedetail">
			<%
			for (int i = 0; i < list.size(); i++) {
				BookingDTO bdto = list.get(i);
				ItemDTO item = idao.getdata(bdto.getItem_code());

				String pic = item.getItem_pic();
				String title = item.getItem_tilte();
				int price = item.getItem_price();
				int sellchk = item.getItem_seiling();
			%>
			<article onclick="">
				<div id="pic">
					<img src="<%=pic%>">
				</div>
				<div><%=title%></div>
				<div><%=price%></div>
				<div><%=sellchk%></div>

			</article>
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
