<%@page import="user.UserDTO"%>
<%@page import="item.ItemDTO"%>
<%@page import="booking.BookingDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="booking.BookingDAO"%>
<%@page import="item.ItemDAO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>

<link rel="stylesheet" href="css/mypage.css">
<link rel="stylesheet" href="css/favolist.css">
<title>예약목록</title>
</head>
<body>
	<%
	UserDTO loginUser = (UserDTO) session.getAttribute("log");
	int loginCode = loginUser.getCode();

	ItemDAO idao = ItemDAO.getInstance();

	ArrayList<ItemDTO> list = idao.getBookingList(loginCode);
	%>
	<div>
		<%@include file="../modules/header.jsp"%>
	</div>
	<div class="mypage_bnt">
		<button class="mypage" onclick="location='./myPage'">내 정보</button>
		<input class="mypage" type="button" value="나의 판매 목록" onclick="location='./orderdList'">
		<input class="mypage selected" type="button" value="나의 구매 목록" onclick="location='./bookingList'">
		<input class="mypage" type="button" value="나의 찜 목록" onclick="location='./favoList'">
	</div>

	<p class="logo title">나의 구매목록</p>

	<div class="contents_wrap">
		
			<%
			for (int i = 0; i < list.size(); i++) {
				ItemDTO idto = list.get(i);
				ItemDTO item = idao.getdata(idto.getItem_code());

				String pic = item.getItem_pic();
				String title = item.getItem_tilte();
				int price = item.getItem_price();
				int sellchk = item.getItem_seiling();
				int code = item.getItem_code();
			%>
			<div class ="contents_top">
			<a class="contents_img_href" href="./itmeView?code=<%=code %>" style="cursor: pointer">
				<div class="contents-photo">
					<img id="contents_img_size"src="<%=pic%>">
				</div>
				<div id="contents">
				<div><%=title%></div>
				<div><%=price%>원</div>
				</div>
			</a>
			</div>
			<%
			}
			%>
		</div>	
	<div class="footer">
		<%@include file="../modules/footer.jsp"%>
	</div>
</body>
</html>
