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
			<button onclick="location='./profileUpdate'">내 정보 수정</button>
			<button onclick="location='./bookingList'">나의 예약 목록</button>
			<button onclick="location='./favoList'">나의 찜 목록</button>
		</div>
		<div class="orderds_wrap">
			<%
			for (int i = 0; i < list.size(); i++) {
			%>
			<div class="orderd-top">
				<a class="orderd_img_href" href="./itemView?code=<%=list.get(i).getItem_code()%>"
					style="cursor: pointer">
					<div class="orderd-photo">
						<img id="orderd_img_size" src="<%=list.get(i).getItem_pic()%>">
					</div>
					<div id="orderd_contents">
						<div><%=list.get(i).getItem_tilte()%></div>
						<div><%=list.get(i).getItem_price()%>원</div>
						<%
						if (list.get(i).getItem_seiling() == 0) {%>
						<div>판매중</div>
						<%} else if (list.get(i).getItem_seiling() == 1) {%>
						<div>예약중</div>
						<%}else if(list.get(i).getItem_seiling() == 2){%>
						<div>판매완료</div>
						<%
						}
						%>
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
