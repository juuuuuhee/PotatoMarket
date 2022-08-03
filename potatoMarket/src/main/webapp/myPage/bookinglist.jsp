<%@page import="item.ItemDTO"%>
<%@page import="booking.BookingDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="booking.BookingDAO"%>
<%@page import="item.ItemDAO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
int usercode = 1234;

ItemDAO idao = ItemDAO.getInstance();
BookingDAO bdao = BookingDAO.getInstance();

ArrayList<BookingDTO> list = bdao.getBookingList(usercode);

%>
<div class="wrap">
		<div>
			<%@include file="../modules/header.jsp"%>
		</div>
		<div class="contents_wrap">

 <div class="pagedetail"> 
        <div style="text-align : center;">
		</div>
<%for(int i =0; i<list.size(); i++){
	BookingDTO bdto = list.get(i);
	ItemDTO item = idao.getdata(bdto.getItem_code());
	
	String pic = item.getItem_pic();
	String title = item.getItem_tilte();
	int price = item.getItem_price();
	int sellchk = item.getItem_seiling();
	
	%>
<article onclick="" >
<div id="pic"><img src="<%=pic %>"></div>
<div><%=title %></div>
<div><%=price %></div>
<div><%=sellchk %></div>

</article>
<% 
}
%>



</div>	
			<button onclick="location='./mypage.jsp'">내 정보</button>
			<button onclick="location='./orderdlist.jsp'">구매 목록</button>
			<button onclick="location='./favolist.jsp'">찜 목록</button>
			</div>
   <div class="footer">
			<%@include file="../modules/footer.jsp"%>
		</div>
	</div>
</body>
</html>
